package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static org.junit.Assert.*;


/**
 * Created by Joseph on 7/17/16.
 */
@Slf4j
public class ChordTest {

    @Test
    public void sanity_verify_constructor() {
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
    public void transpose_Interval() {
        Chord transposed = new Chord("Cmaj");
        transposed.transpose(IntervalDirection.UP, Interval.withShortName("M2"));
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(IntervalDirection.DOWN, Interval.withShortName("M2"));
        verifyChord(transposed, C, E, G);

        transposed = new Chord("Dxbbbmaj");
        verifyChord(transposed,
                PitchClass.withName("Dxbbb", false),
                PitchClass.withName("Fxbbb#", false),
                PitchClass.withName("Axbbb", false));
    }

    @Test
    public void transpose_PitchClass() {
        Chord transposed = new Chord("Cmaj");
        transposed.transpose(IntervalDirection.UP, PitchClass.D);
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(IntervalDirection.UP, PitchClass.C);
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void invert() {
        // Sanity check
        Chord c = new Chord("Cmaj");
        assertEquals(1, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.getName());

        // Test inversions independently
        c.invert();
        assertEquals(2, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_0, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/E", c.getName());

        c.invert();
        assertEquals(3, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_1, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/G", c.getName());

        c.invert();
        assertEquals(1, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.getName());

        // Test chained inversions
        c.invert().invert();
        assertEquals(3, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_1, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/G", c.getName());

        c.invert().invert();
        assertEquals(2, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_0, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj/E", c.getName());

        c.invert().invert();
        assertEquals(1, c.getInversion());
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.getPitchesForOctave(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.getName());

    }

    @Test
    public void isDiatonicTo_KeySignature() {
        Chord c = new Chord("Cmaj");

        assertTrue(c.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(c.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(c.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicTo_IntervalSet() {
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

    private static void verifyChord(Chord chord, PitchClass... expected) {
        Pitch[]
                lowPitches = chord.getPitchesForOctave(Octave.OCTAVE_0),
                highPitches = chord.getPitchesForOctave(chord.maxPlayableOctave);

        assertEquals("Chord length is not the expected length (bad test args?)",
                expected.length,
                chord.getChordType().getIntervals().length);

        assertEquals("Chord grouping is incorrect", getExpectedGrouping(chord), chord.getGrouping());

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals(expected[i].getSimplifiedName(), lowPitches[i].pitchClass.getSimplifiedName());
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals(expected[i].getSimplifiedName(), highPitches[i].pitchClass.getSimplifiedName());
        }

        assertEquals(chord.lowestDiatonic.pitchClass.getName() + chord.getChordType().getSymbol(), chord.getName());
    }

    private static String getExpectedGrouping(Chord chord) {
        switch (chord.getVerticalSize()) {
            case 1: return "monad";
            case 2: return "dyad";
            case 3: return "triad";
            case 4: return "tetrad";
            case 5: return "pentad";
            case 6: return "hexad";
            case 7: return "heptad";
            case 8: return "octad";
            case 9: return "ennead";
            case 10: return "decad";
            case 11: return "hendecad";
            case 12: return "dodecad";
            default: return "UNIT TEST OUT OF RANGE";
        }
    }

}