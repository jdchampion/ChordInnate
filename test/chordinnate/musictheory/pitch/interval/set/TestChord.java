package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.Interval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.junit.Test;

import static chordinnate.musictheory.pitch.notation.EnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/17/16.
 */
public class TestChord {
    @Test
    public void sanityCheck() throws Exception {
        // Basic arbitrary testing
        verifyChord(new Chord(C, ChordType.MAJOR), C, E, G);
        verifyChord(new Chord(F, ChordType.MAJOR), F, A, C);
        verifyChord(new Chord(F, ChordType.MAJOR_SEVEN), F, A, C, E);
        verifyChord(new Chord(F, ChordType.SEVEN), F, A, C, E_FLAT);
        verifyChord(new Chord(A_FLAT, ChordType.MINOR), A_FLAT, C_FLAT, E_FLAT);
        verifyChord(new Chord(C, ChordType.DIMINISHED), C, E_FLAT, G_FLAT);
    }

    @Test
    public void transposeToInterval() throws Exception {
        Chord transposed = new Chord(C, ChordType.MAJOR);
        transposed.transposeTo(Interval.MAJOR_SECOND_UP);
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transposeTo(Interval.MAJOR_SECOND_DOWN);
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        Chord transposed = new Chord(C, ChordType.MAJOR);
        transposed.transposeTo(PitchClass.D);
        verifyChord(transposed, D, F_SHARP, A);
        transposed.transposeTo(PitchClass.C);
        verifyChord(transposed, C, E, G);
    }

    @Test
    public void invert() throws Exception {
        Chord c = new Chord(C, ChordType.MAJOR);
        assertEquals(0, c.inversion);
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.invertedPitchesByOctave.get(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.name);
        c.invert();
        assertEquals(1, c.inversion);
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_0, Pitch.G_0}, c.invertedPitchesByOctave.get(Octave.OCTAVE_0));
        assertEquals("Cmaj/E", c.name);
        c.invert();
        assertEquals(2, c.inversion);
        assertArrayEquals(new Pitch[]{Pitch.C_1, Pitch.E_1, Pitch.G_0}, c.invertedPitchesByOctave.get(Octave.OCTAVE_0));
        assertEquals("Cmaj/G", c.name);
        c.invert();
        assertEquals(0, c.inversion);
        assertArrayEquals(new Pitch[]{Pitch.C_0, Pitch.E_0, Pitch.G_0}, c.invertedPitchesByOctave.get(Octave.OCTAVE_0));
        assertEquals("Cmaj", c.name);
    }

    @Test
    public void isDiatonicToKeySignature() throws Exception {
        Chord c = new Chord(C, ChordType.MAJOR);

        assertTrue(c.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(c.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(c.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Chord c = new Chord(C, ChordType.MAJOR);
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

        assertEquals("Chord length is not the expected length (bad test args?)", chord.chordType.length(), expected.length);

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals(expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals(expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }

        assertEquals(chord.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + chord.chordType.SYMBOL, chord.name);
    }

}