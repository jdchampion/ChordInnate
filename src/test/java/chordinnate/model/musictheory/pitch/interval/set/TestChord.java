package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.service.Services;
import chordinnate.service.musictheory.ChordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph on 7/17/16.
 */
@Slf4j
public class TestChord {

    @Test
    public void sanityCheck() {
        // Basic arbitrary testing
        verifyChord(new Chord("Cmaj"), C, E, G);
        verifyChord(new Chord("Fmaj"), F, A, C);
        verifyChord(new Chord("Fmaj7"), F, A, C, E);
        verifyChord(new Chord("F7"), F, A, C, E_FLAT);
        verifyChord(new Chord("Abm"), A_FLAT, C_FLAT, E_FLAT);
        verifyChord(new Chord("Cdim"), C, E_FLAT, G_FLAT);

        verifyChord(new Chord("Cx#maj7"),
                PitchClass.withName("Cx#", false),
                PitchClass.withName("Ex#", false),
                PitchClass.withName("Gx#", false),
                PitchClass.withName("Bx#", false));
    }

    @Test
    public void transposeToInterval() {
        Chord transposed = new Chord("Cmaj");
        transposed.transpose(true, Interval.withShortName("M2"));
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(false, Interval.withShortName("M2"));
        verifyChord(transposed, C, E, G);

        transposed = new Chord("Dxbbbmaj");
        verifyChord(transposed,
                PitchClass.withName("Dxbbb", false),
                PitchClass.withName("Fxbbb#", false),
                PitchClass.withName("Axbbb", false));
    }

    @Test
    public void transposeToPitchClass() {
        Chord transposed = new Chord("Cmaj");
        transposed.transpose(true, PitchClass.D);
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(true, PitchClass.C);
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void invert() {
        Chord c = new Chord("Cmaj");
        assertEquals(0, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.getInversionForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.getName());
        c.invert();
        assertEquals(1, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_0, Pitch.G_0}, c.getInversionForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/E", c.getName());
        c.invert();
        assertEquals(2, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_1, Pitch.G_0}, c.getInversionForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/G", c.getName());
        c.invert();
        assertEquals(0, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.getInversionForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.getName());
    }

    @Test
    public void isDiatonicToKeySignature() {
        Chord c = new Chord("Cmaj");

        assertTrue(c.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(c.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(c.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() {
        Chord c = new Chord("Cmaj");
        Scale cMajorScale = new Scale("C Major");
        Scale aNatualMinor = new Scale("A Melodic Minor descending");
        Scale bLocrian = new Scale("B Locrian");
        Scale bMajorScale = new Scale("B Major");

        assertTrue(c.isDiatonicTo(c));
        assertTrue(c.isDiatonicTo(cMajorScale));
        assertTrue(c.isDiatonicTo(aNatualMinor));
        assertTrue(c.isDiatonicTo(bLocrian));
        assertFalse(c.isDiatonicTo(bMajorScale));

        Chord b = new Chord("Bmaj");
        assertTrue(b.isDiatonicTo(b));
        assertFalse(b.isDiatonicTo(c));
        assertFalse(b.isDiatonicTo(cMajorScale));
        assertFalse(b.isDiatonicTo(aNatualMinor));
        assertFalse(b.isDiatonicTo(bLocrian));
        assertTrue(b.isDiatonicTo(bMajorScale));

        Chord weird = new Chord("C#bmaj");
        Scale weirdScale = new Scale("C#b Major");
        assertTrue(weird.isDiatonicTo(weird));
        assertFalse(weird.isDiatonicTo(b));
        assertFalse(weird.isDiatonicTo(c));
        assertFalse(weird.isDiatonicTo(cMajorScale));
        assertFalse(weird.isDiatonicTo(aNatualMinor));
        assertFalse(weird.isDiatonicTo(bLocrian));
        assertFalse(weird.isDiatonicTo(bMajorScale));
        assertTrue(weird.isDiatonicTo(weirdScale));
    }

    /**
     * Helper method for testing the returned Pitch values for a given ChordType.
     * @param chord
     * @param expected
     */
    private static void verifyChord(Chord chord, PitchClass... expected) {
        Pitch[]
                lowPitches = chord.getPitchesForOctave(Octave.OCTAVE_0),
                highPitches = chord.getPitchesForOctave(chord.maxPlayableOctave);

        assertEquals("Chord length is not the expected length (bad test args?)",
                expected.length,
                chord.getChordType().getIntervals().length);

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals(expected[i].getBaseName(), lowPitches[i].pitchClass.getBaseName());
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals(expected[i].getBaseName(), highPitches[i].pitchClass.getBaseName());
        }

        assertEquals(chord.lowestDiatonic.pitchClass.getName() + chord.getChordType().getSymbol(), chord.getName());
    }

    @Test
    public void select() {
        ChordTypeService service = Services.getChordTypeService();

        List<Interval[]> arrList = new ArrayList<>();
        arrList.add(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.PERFECT_5});
        arrList.add(new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        arrList.add(new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        arrList.add(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.PERFECT_5});

        List<ChordType> list = service.findAllByIntervals(arrList, false);

        list.forEach(c -> log.info(c.getSymbol()));
    }

}