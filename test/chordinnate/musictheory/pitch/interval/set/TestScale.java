package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.junit.Test;

import static chordinnate.musictheory.pitch.notation.EnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/15/16.
 */
public class TestScale {
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
    public void verifyScale() throws Exception {
        verifyScale(new Scale(C, ScaleType.MAJOR), C, D, E, F, G, A, B);
        verifyScale(new Scale(F_SHARP, ScaleType.MAJOR), F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP);
        verifyScale(new Scale(G, ScaleType.MAJOR), G, A, B, C, D, E, F_SHARP);
        verifyScale(new Scale(B, ScaleType.MAJOR), B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP);
        verifyScale(new Scale(C, ScaleType.HARMONIC_MINOR), C, D, E_FLAT, F, G, A_FLAT, B);
        verifyScale(new Scale(B_FLAT, ScaleType.HARMONIC_MINOR), B_FLAT, C, D_FLAT, E_FLAT, F, G_FLAT, A);
        verifyScale(new Scale(C, ScaleType.BLUES), C, E_FLAT, F, F_SHARP, G, B_FLAT);
    }

    /**
     * Helper method for testing the returned Pitch values for a given ScaleType.
     * @param scale
     * @param expected
     */
    private void verifyScale(Scale scale, EnharmonicSpelling... expected) {
        Pitch[]
                lowPitches = scale.getPitchesForOctave(Octave.OCTAVE_0),
                highPitches = scale.getPitchesForOctave(scale.maxPlayableOctave);

        assertEquals("Scale length is not the expected length", scale.scaleType.length(), expected.length);

        assertEquals(lowPitches.length, highPitches.length);
        int range = lowPitches.length;

        for (int i = 0; i < range; i++) {
            assertEquals(expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
            assertEquals(expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
    }
}