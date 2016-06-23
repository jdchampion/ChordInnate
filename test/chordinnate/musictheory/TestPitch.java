package chordinnate.musictheory;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/18/16.
 */
public class TestPitch {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getPitchClass() throws Exception {

    }

    @Test
    public void getOctave() throws Exception {
        /*
         * The Octave for each Pitch should match the number indicated in the name of the Pitch.
         * Example: the Pitch "F_SHARP_5" should contain OCTAVE_5
         */
        for (Pitch pitch : Pitch.values()) {
            String pitchName = pitch.name();
            assertEquals(
                    (int) Integer.valueOf(pitchName.substring(pitchName.lastIndexOf("_") + 1)),
                            pitch.getOctave().getOctaveNumber()
            );
        }
    }

    @Test
    public void getAbsolutePitch() throws Exception {
        // All Pitches should have an absolute pitch (MIDI value) = (12 * octave) + base
        for (Pitch pitch : Pitch.values()) {
            assertEquals(
                    (12 * pitch.getOctave().getOctaveNumber() + pitch.getPitchClass().getBaseMidiValue()),
                    pitch.getAbsolutePitch()
            );
        }
    }

    @Test
    public void isDiatonicTo() throws Exception {

    }

    @Test
    public void isEnharmonicTo() throws Exception {

    }

    @Test
    public void getEnharmonics() throws Exception {

    }

    @Test
    public void transposeChromaticBy() throws Exception {

    }

    @Test
    public void transposeScalarBy() throws Exception {

    }

    @Test
    public void isTransposableToPitchInterval() throws Exception {

    }

    @Test
    public void transposeToPitchInterval() throws Exception {
        // Basic test cases
        assertEquals(Pitch.G_3, Pitch.C_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.F_4, Pitch.C_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.G_4, Pitch.C_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.F_3, Pitch.C_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.A_3, Pitch.D_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.G_4, Pitch.D_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.A_4, Pitch.D_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.G_3, Pitch.D_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.B_3, Pitch.E_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.A_4, Pitch.E_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.B_4, Pitch.E_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.A_3, Pitch.E_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.C_4, Pitch.F_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.B_FLAT_4, Pitch.F_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.C_5, Pitch.F_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.B_FLAT_3, Pitch.F_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.D_4, Pitch.G_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.C_5, Pitch.G_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.D_5, Pitch.G_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.C_4, Pitch.G_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.E_4, Pitch.A_4.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.D_5, Pitch.A_4.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.E_5, Pitch.A_4.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.D_4, Pitch.A_4.transposeTo(PitchInterval.PERFECT_FIFTH, false));
        assertEquals(Pitch.F_SHARP_3, Pitch.B_3.transposeTo(PitchInterval.PERFECT_FOURTH, false));
        assertEquals(Pitch.E_4, Pitch.B_3.transposeTo(PitchInterval.PERFECT_FOURTH, true));
        assertEquals(Pitch.F_SHARP_4, Pitch.B_3.transposeTo(PitchInterval.PERFECT_FIFTH, true));
        assertEquals(Pitch.E_3, Pitch.B_3.transposeTo(PitchInterval.PERFECT_FIFTH, false));

        // Random test cases (expected values verified by http://www.musictheory.net/calculators/interval)
        assertEquals(Pitch.B_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transposeTo(PitchInterval.AUGMENTED_SECOND, true));
        assertEquals(Pitch.F_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transposeTo(PitchInterval.AUGMENTED_SECOND, false));

        assertEquals(Pitch.D_5, Pitch.F_SHARP_4.transposeTo(PitchInterval.MINOR_SIXTH, true));
        assertEquals(Pitch.A_SHARP_3, Pitch.F_SHARP_4.transposeTo(PitchInterval.MINOR_SIXTH, false));

        assertEquals(Pitch.C_DOUBLE_FLAT_5, Pitch.C_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.C_FLAT_5, Pitch.C_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.D_DOUBLE_FLAT_5, Pitch.C_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.D_FLAT_5, Pitch.C_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.D_5, Pitch.C_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

        assertEquals(Pitch.D_DOUBLE_FLAT_5, Pitch.D_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.D_FLAT_5, Pitch.D_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.E_DOUBLE_FLAT_5, Pitch.D_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.E_FLAT_5, Pitch.D_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.E_5, Pitch.D_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

        assertEquals(Pitch.E_DOUBLE_FLAT_5, Pitch.E_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.F_DOUBLE_FLAT_5, Pitch.E_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.F_FLAT_5, Pitch.E_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.F_5, Pitch.E_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.F_SHARP_5, Pitch.E_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

        assertEquals(Pitch.F_DOUBLE_FLAT_5, Pitch.F_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.F_FLAT_5, Pitch.F_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.G_DOUBLE_FLAT_5, Pitch.F_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.G_FLAT_5, Pitch.F_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.G_5, Pitch.F_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

        assertEquals(Pitch.G_DOUBLE_FLAT_5, Pitch.G_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.G_FLAT_5, Pitch.G_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.A_DOUBLE_FLAT_5, Pitch.G_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.A_FLAT_5, Pitch.G_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.A_5, Pitch.G_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

        assertEquals(Pitch.A_DOUBLE_FLAT_5, Pitch.A_DOUBLE_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.A_FLAT_5, Pitch.A_FLAT_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.B_DOUBLE_FLAT_5, Pitch.A_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.B_FLAT_5, Pitch.A_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));
        assertEquals(Pitch.B_5, Pitch.A_DOUBLE_SHARP_4.transposeTo(PitchInterval.DIMINISHED_SECOND, true));

    }

    @Test
    public void isTransposableToOctave() throws Exception {
        // Basic tests
        assertTrue(Pitch.C_0.isTransposableTo(Octave.OCTAVE_0));
        assertTrue(Pitch.C_0.isTransposableTo(Octave.OCTAVE_10));
        assertTrue(Pitch.C_0.isTransposableTo(Octave.OCTAVE_4));
        assertTrue(Pitch.C_10.isTransposableTo(Octave.OCTAVE_0));
        assertTrue(Pitch.C_10.isTransposableTo(Octave.OCTAVE_10));
        assertTrue(Pitch.C_10.isTransposableTo(Octave.OCTAVE_4));

        // Edge cases that should not be transposable (outside MIDI range)
        assertFalse(Pitch.G_SHARP_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.G_DOUBLE_SHARP_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.A_FLAT_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.A_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.A_SHARP_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.A_DOUBLE_SHARP_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.B_DOUBLE_FLAT_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.B_FLAT_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.B_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.C_DOUBLE_FLAT_9.isTransposableTo(Octave.OCTAVE_10));
        assertFalse(Pitch.C_FLAT_9.isTransposableTo(Octave.OCTAVE_10));
    }

    @Test
    public void transposeToOctave() throws Exception {
        // Basic tests
        assertEquals(Pitch.C_0, Pitch.C_0.transposeTo(Octave.OCTAVE_0));
        assertEquals(Pitch.C_10, Pitch.C_0.transposeTo(Octave.OCTAVE_10));
        assertEquals(Pitch.C_4, Pitch.C_0.transposeTo(Octave.OCTAVE_4));
        assertEquals(Pitch.C_0, Pitch.C_10.transposeTo(Octave.OCTAVE_0));
        assertEquals(Pitch.C_10, Pitch.C_10.transposeTo(Octave.OCTAVE_10));
        assertEquals(Pitch.C_4, Pitch.C_10.transposeTo(Octave.OCTAVE_4));
    }

    @Test
    public void illegalArguments() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        // Transposition on null items is impossible
        Pitch.C_0.isTransposableTo(PitchClass.C, null);
        Pitch.C_0.isTransposableTo(null, Octave.OCTAVE_0);
        Pitch.C_0.isTransposableTo(null, null);
        Pitch.C_0.transposeTo(PitchClass.C, null);
        Pitch.C_0.transposeTo(null, Octave.OCTAVE_0);
        Pitch.C_0.transposeTo(null, null);
    }

    @Test
    public void isTransposableToPitchClass() throws Exception {
        // Items out of MIDI range should not be transposable
        assertFalse(Pitch.A_0.isTransposableTo(PitchClass.A, Octave.OCTAVE_10));

        // Transposing to the same Pitch should still work
        assertTrue(Pitch.C_0.isTransposableTo(PitchClass.C, Octave.OCTAVE_0));
    }

    @Test
    public void transposeToPitchClass() throws Exception {
        // Cannot transpose to Pitches that don't exist
        assertNull(Pitch.C_0.transposeTo(PitchClass.B, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.B_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.G_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.C_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_10));

        // Basic testing
        assertEquals(Pitch.C_3, Pitch.C_4.transposeTo(PitchClass.C, Octave.OCTAVE_3));
        assertEquals(Pitch.A_3, Pitch.C_4.transposeTo(PitchClass.A, Octave.OCTAVE_3));
        assertEquals(Pitch.C_5, Pitch.C_4.transposeTo(PitchClass.C, Octave.OCTAVE_5));
        assertEquals(Pitch.A_5, Pitch.C_4.transposeTo(PitchClass.A, Octave.OCTAVE_5));
    }

    @Test
    public void isTransposableToPitch() throws Exception {

    }

    @Test
    public void transposeToPitch() throws Exception {

    }

}