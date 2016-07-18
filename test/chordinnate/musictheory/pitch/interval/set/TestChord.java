package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.junit.Test;

import static chordinnate.musictheory.pitch.notation.EnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/17/16.
 */
public class TestChord {
    @Test
    public void getPitchesForOctave() throws Exception {
        // TODO
    }

    @Test
    public void isTransposableToPitchInterval() throws Exception {
        // TODO
    }

    @Test
    public void transposeToPitchInterval() throws Exception {
        // TODO
    }

    @Test
    public void isTransposableToPitchClass() throws Exception {
        // TODO
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        // TODO
    }

    @Test
    public void invert() throws Exception {
        // TODO
    }

    @Test
    public void verifyChord() throws Exception {
        this.verifyChord(new Chord(C, ChordType.MAJOR), C, E, G);
        this.verifyChord(new Chord(F, ChordType.MAJOR), F, A, C);
        this.verifyChord(new Chord(F, ChordType.MAJOR_SEVEN), F, A, C, E);
        this.verifyChord(new Chord(F, ChordType.SEVEN), F, A, C, E_FLAT);
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

        assertEquals("Chord length is not the expected length", chord.chordType.length(), expected.length);

        assertEquals(lowPitches.length, highPitches.length);
        int range = lowPitches.length;

        for (int i = 0; i < range; i++) {
            assertEquals(expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
            assertEquals(expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
    }

}