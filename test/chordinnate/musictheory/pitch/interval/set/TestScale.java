package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.junit.Test;

import static chordinnate.musictheory.pitch.notation.EnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/15/16.
 */
public class TestScale {
    @Test
    public void sanityCheck() throws Exception {
        // Basic arbitrary testing
        verifyScale(new Scale(C, ScaleType.MAJOR), C, D, E, F, G, A, B);
        verifyScale(new Scale(F_SHARP, ScaleType.MAJOR), F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP);
        verifyScale(new Scale(G, ScaleType.MAJOR), G, A, B, C, D, E, F_SHARP);
        verifyScale(new Scale(B, ScaleType.MAJOR), B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP);
        verifyScale(new Scale(C, ScaleType.HARMONIC_MINOR), C, D, E_FLAT, F, G, A_FLAT, B);
        verifyScale(new Scale(B_FLAT, ScaleType.HARMONIC_MINOR), B_FLAT, C, D_FLAT, E_FLAT, F, G_FLAT, A);
        verifyScale(new Scale(C, ScaleType.BLUES), C, E_FLAT, F, F_SHARP, G, B_FLAT);
    }

    @Test
    public void transposeToPitchInterval() throws Exception {
        Scale transposed = new Scale(C, ScaleType.MAJOR);
        transposed.transposeTo(PitchInterval.MAJOR_SECOND, true);
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transposeTo(PitchInterval.MAJOR_SECOND, false);
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        Scale transposed = new Scale(C, ScaleType.MAJOR);
        transposed.transposeTo(PitchClass.D);
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transposeTo(PitchClass.C);
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void isDiatonicToKeySignature() throws Exception {
        Scale s = new Scale(C, ScaleType.MAJOR);

        assertTrue(s.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(s.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(s.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Scale s = new Scale(C, ScaleType.MAJOR);
        Chord c = new Chord(C, ChordType.MAJOR);
        Scale aNaturalMinor = new Scale(A, ScaleType.MELODIC_MINOR_DESCENDING);

        assertTrue(s.isDiatonicTo(s));
        assertFalse(s.isDiatonicTo(c));     // Scales are not diatonic to Chords that contain fewer Pitches than them
        assertTrue(s.isDiatonicTo(aNaturalMinor));
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

        assertEquals("Scale length is not the expected length (bad test args?)", scale.scaleType.length(), expected.length);

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals(expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals(expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }

        assertEquals(scale.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scale.scaleType.NAME, scale.name);
    }
}