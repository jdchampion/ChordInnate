package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.junit.Test;

import static chordinnate.model.musictheory.notation.EnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/17/16.
 */
public class TestChord {

    @Test
    public void sanityCheck() throws Exception {
        // Basic arbitrary testing
        verifyChord(new Chord(C, "maj"), C, E, G);
        verifyChord(new Chord(F, "maj"), F, A, C);
        verifyChord(new Chord(F, "maj7"), F, A, C, E);
        verifyChord(new Chord(F, "7"), F, A, C, E_FLAT);
        verifyChord(new Chord(A_FLAT, "m"), A_FLAT, C_FLAT, E_FLAT);
        verifyChord(new Chord(C, "dim"), C, E_FLAT, G_FLAT);
    }

    @Test
    public void transposeToInterval() throws Exception {
        Chord transposed = new Chord(C, "maj");
        transposed.transpose(true, Interval.withShortName("M2"));
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(false, Interval.withShortName("M2"));
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        Chord transposed = new Chord(C, "maj");
        transposed.transpose(true, PitchClass.D);
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transpose(true, PitchClass.C);
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void invert() throws Exception {
        Chord c = new Chord(C, "maj");
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
    public void isDiatonicToKeySignature() throws Exception {
        Chord c = new Chord(C, "maj");

        assertTrue(c.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(c.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(c.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Chord c = new Chord(C, "maj");
        Scale cMajorScale = new Scale(C, "Major");
        Scale aNatualMinor = new Scale(A, "Melodic Minor descending");

        assertTrue(c.isDiatonicTo(c));
        assertTrue(c.isDiatonicTo(cMajorScale));
        assertTrue(c.isDiatonicTo(aNatualMinor));
    }

    /**
     * Helper method for testing the returned Pitch values for a given ChordType.
     * @param chord
     * @param expected
     */
    private void verifyChord(Chord chord, EnharmonicSpelling... expected) {
        Pitch[]
                lowPitches = chord.getPitchesForOctave(Octave.OCTAVE_0),
                highPitches = chord.getPitchesForOctave(chord.maxPlayableOctave);

        assertEquals("Chord length is not the expected length (bad view_rn_analysis.sql args?)",
                expected.length,
                chord.getChordType().getIntervals().split(",").length);

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals(expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals(expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }

        assertEquals(chord.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + chord.getChordType().getSymbol(), chord.getName());
    }

}