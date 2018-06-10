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
 * Created by Joseph on 7/15/16.
 */
public class TestScale {
    @Test
    public void sanityCheck() throws Exception {
        // Basic arbitrary testing
        verifyScale(new Scale(C, "Major"), C, D, E, F, G, A, B);
        verifyScale(new Scale(F_SHARP, "Major"), F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP);
        verifyScale(new Scale(G, "Major"), G, A, B, C, D, E, F_SHARP);
        verifyScale(new Scale(B, "Major"), B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP);
        verifyScale(new Scale(C, "Harmonic Minor"), C, D, E_FLAT, F, G, A_FLAT, B);
        verifyScale(new Scale(B_FLAT, "Harmonic Minor"), B_FLAT, C, D_FLAT, E_FLAT, F, G_FLAT, A);
        verifyScale(new Scale(C, "Blues"), C, E_FLAT, F, F_SHARP, G, B_FLAT);
    }

    @Test
    public void transposeToInterval() throws Exception {
        Scale transposed = new Scale(C, "Major");
        transposed.transpose(true, Interval.withShortName("M2"));
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transpose(false, Interval.withShortName("M2"));
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        Scale transposed = new Scale(C, "Major");
        transposed.transpose(true, PitchClass.D);
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transpose(true, PitchClass.C);
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void isDiatonicToKeySignature() throws Exception {
        Scale s = new Scale(C, "Major");

        assertTrue(s.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(s.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(s.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Scale s = new Scale(C, "Major");
        Chord c = new Chord(C, "maj");
        Scale aNaturalMinor = new Scale(A, "Melodic Minor descending");

        assertTrue(s.isDiatonicTo(s));
        assertFalse(s.isDiatonicTo(c));     // Scales are not diatonic to Chords that contain fewer Pitches than them
        assertTrue(s.isDiatonicTo(aNaturalMinor));
    }

    /**
     * Helper method for testing the returned Pitch values for a given Scale.
     * @param scale
     * @param expected
     */
    private void verifyScale(Scale scale, EnharmonicSpelling... expected) {
        Pitch[]
                lowPitches = scale.getPitchesForOctave(Octave.OCTAVE_0),
                highPitches = scale.getPitchesForOctave(scale.maxPlayableOctave);

        assertEquals("Scale length for scale [" + scale.getFullName() + "] is not the expected length (bad SCALE_TYPE.INTERVALS column value?)", scale.length(), expected.length);

        int lowRange = lowPitches.length, highRange = highPitches.length;

        for (int i = 0; i < lowRange; i++) {
            assertEquals("Lowest octave for scale [" + scale.getFullName() + "] has the wrong values", expected[i], lowPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }
        for (int i = 0; i < highRange; i++) {
            assertEquals("Highest octave for scale [" + scale.getFullName() + "] has the wrong values", expected[i], highPitches[i].PITCH_CLASS.ENHARMONIC_SPELLING);
        }

        assertEquals(scale.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scale.getScaleType().getName(), scale.getFullName());
    }
}