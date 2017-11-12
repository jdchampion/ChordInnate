package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static chordinnate.model.musictheory.pitch.interval.Interval.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/18/16.
 */
public class TestPitch {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @SuppressWarnings("ConstantConditions")
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
    public void testField_NUMBER() throws Exception {
        /*
         * The Octave for each Pitch should match the number indicated in the name of the Pitch.
         * Example: the Pitch "F_SHARP_5" should contain OCTAVE_5
         */
        for (Pitch pitch : Pitch.values()) {
            String pitchName = pitch.name();
            assertEquals(
                    (int) Integer.valueOf(pitchName.substring(pitchName.lastIndexOf("_") + 1)),
                    pitch.OCTAVE.NUMBER
            );
        }
    }

    @Test
    public void testField_ABSOLUTE_PITCH() throws Exception {
        /*
         * All Pitches should have an absolute pitch (MIDI value) = (12 * octave) + base,
         * none of which should go beyond 127 as the highest pitch.
         */
        for (Pitch pitch : Pitch.values()) {
            assertEquals(
                    (12 * pitch.OCTAVE.NUMBER + pitch.PITCH_CLASS.BASE_MIDI_VALUE),
                    pitch.ABSOLUTE_PITCH
            );

            assertTrue(pitch.ABSOLUTE_PITCH < 128);
        }
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Scale cMajor = new Scale(PitchClass.C, "Major"),
                dMajor = new Scale(PitchClass.D, "Major");

        Chord cMaj = new Chord(PitchClass.C, "maj"),
                dMaj = new Chord(PitchClass.D, "maj");

        Pitch c0 = Pitch.C_0, c10 = Pitch.C_10;

        assertTrue(c0.isDiatonicTo(cMajor));
        assertTrue(c0.isDiatonicTo(cMaj));
        assertFalse(c0.isDiatonicTo(dMajor));
        assertFalse(c0.isDiatonicTo(dMaj));

        assertTrue(c10.isDiatonicTo(cMajor));
        assertTrue(c10.isDiatonicTo(cMaj));
        assertFalse(c10.isDiatonicTo(dMajor));
        assertFalse(c10.isDiatonicTo(dMaj));
    }

    @Test
    public void isEnharmonicTo() throws Exception {
        // White keys
        assertTrue(Pitch.C_0.isEnharmonicTo(Pitch.B_SHARP_0));
        assertTrue(Pitch.C_0.isEnharmonicTo(Pitch.D_DOUBLE_FLAT_0));
        assertTrue(Pitch.D_0.isEnharmonicTo(Pitch.C_DOUBLE_SHARP_0));
        assertTrue(Pitch.D_0.isEnharmonicTo(Pitch.E_DOUBLE_FLAT_0));
        assertTrue(Pitch.E_0.isEnharmonicTo(Pitch.D_DOUBLE_SHARP_0));
        assertTrue(Pitch.E_0.isEnharmonicTo(Pitch.F_FLAT_0));
        assertTrue(Pitch.G_0.isEnharmonicTo(Pitch.F_DOUBLE_SHARP_0));
        assertTrue(Pitch.G_0.isEnharmonicTo(Pitch.A_DOUBLE_FLAT_0));
        assertTrue(Pitch.A_0.isEnharmonicTo(Pitch.G_DOUBLE_SHARP_0));
        assertTrue(Pitch.A_0.isEnharmonicTo(Pitch.B_DOUBLE_FLAT_0));

        // Black keys

        // C# == Db
        assertTrue(Pitch.C_SHARP_0.isEnharmonicTo(Pitch.D_FLAT_0));
        assertTrue(Pitch.D_FLAT_0.isEnharmonicTo(Pitch.C_SHARP_0));

        // D# == Eb == Fbb
        assertTrue(Pitch.D_SHARP_0.isEnharmonicTo(Pitch.E_FLAT_0));
        assertTrue(Pitch.D_SHARP_0.isEnharmonicTo(Pitch.F_DOUBLE_FLAT_0));
        assertTrue(Pitch.E_FLAT_0.isEnharmonicTo(Pitch.D_SHARP_0));
        assertTrue(Pitch.E_FLAT_0.isEnharmonicTo(Pitch.F_DOUBLE_FLAT_0));
        assertTrue(Pitch.F_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.D_SHARP_0));
        assertTrue(Pitch.F_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.E_FLAT_0));

        // E# == F == Gbb
        assertTrue(Pitch.E_SHARP_0.isEnharmonicTo(Pitch.F_0));
        assertTrue(Pitch.E_SHARP_0.isEnharmonicTo(Pitch.G_DOUBLE_FLAT_0));
        assertTrue(Pitch.F_0.isEnharmonicTo(Pitch.E_SHARP_0));
        assertTrue(Pitch.F_0.isEnharmonicTo(Pitch.G_DOUBLE_FLAT_0));
        assertTrue(Pitch.G_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.E_SHARP_0));
        assertTrue(Pitch.G_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.F_0));

        // Ex == F# == Gb
        assertTrue(Pitch.E_DOUBLE_SHARP_0.isEnharmonicTo(Pitch.F_SHARP_0));
        assertTrue(Pitch.E_DOUBLE_SHARP_0.isEnharmonicTo(Pitch.G_FLAT_0));
        assertTrue(Pitch.F_SHARP_0.isEnharmonicTo(Pitch.E_DOUBLE_SHARP_0));
        assertTrue(Pitch.F_SHARP_0.isEnharmonicTo(Pitch.G_FLAT_0));
        assertTrue(Pitch.G_FLAT_0.isEnharmonicTo(Pitch.E_DOUBLE_SHARP_0));
        assertTrue(Pitch.G_FLAT_0.isEnharmonicTo(Pitch.F_SHARP_0));

        // G# == Ab
        assertTrue(Pitch.G_SHARP_0.isEnharmonicTo(Pitch.A_FLAT_0));
        assertTrue(Pitch.A_FLAT_0.isEnharmonicTo(Pitch.G_SHARP_0));

        // A# == Bb == Cbb
        assertTrue(Pitch.A_SHARP_0.isEnharmonicTo(Pitch.B_FLAT_0));
        assertTrue(Pitch.A_SHARP_0.isEnharmonicTo(Pitch.C_DOUBLE_FLAT_0));
        assertTrue(Pitch.B_FLAT_0.isEnharmonicTo(Pitch.A_SHARP_0));
        assertTrue(Pitch.B_FLAT_0.isEnharmonicTo(Pitch.C_DOUBLE_FLAT_0));
        assertTrue(Pitch.C_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.A_SHARP_0));
        assertTrue(Pitch.C_DOUBLE_FLAT_0.isEnharmonicTo(Pitch.B_FLAT_0));
    }

    @Test
    public void transposeToInterval() throws Exception {
        // (expected values verified by http://www.musictheory.net/calculators/interval)
        assertEquals(Pitch.C_1, Pitch.C_1.transposeTo(INTERVAL_P1, true));
        assertEquals(Pitch.C_2, Pitch.C_1.transposeTo(INTERVAL_P8, true));
        assertEquals(Pitch.C_1, Pitch.C_1.transposeTo(INTERVAL_P1, false));
        assertEquals(Pitch.C_0, Pitch.C_1.transposeTo(INTERVAL_P8, false));

        assertEquals(Pitch.C_FLAT_4, Pitch.C_4.transposeTo(INTERVAL_d1, true));
        assertEquals(Pitch.C_FLAT_5, Pitch.C_4.transposeTo(INTERVAL_d8, true));
        assertEquals(Pitch.C_FLAT_6, Pitch.C_4.transposeTo(INTERVAL_d15, true));

        assertEquals(Pitch.C_SHARP_3, Pitch.C_4.transposeTo(INTERVAL_d1, false));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_4.transposeTo(INTERVAL_d8, false));
        assertEquals(Pitch.C_SHARP_1, Pitch.C_4.transposeTo(INTERVAL_d15, false));
        assertEquals(Pitch.C_2, Pitch.C_1.transposeTo(INTERVAL_P8, true));
        assertEquals(Pitch.C_3, Pitch.C_1.transposeTo(INTERVAL_P15, true));
        assertEquals(Pitch.C_0, Pitch.C_1.transposeTo(INTERVAL_P8, false));
        assertEquals(Pitch.C_0, Pitch.C_2.transposeTo(INTERVAL_P15, false));

        assertEquals(Pitch.C_SHARP_1, Pitch.C_1.transposeTo(INTERVAL_A1, true));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_1.transposeTo(INTERVAL_A8, true));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_1.transposeTo(INTERVAL_A1, false));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_2.transposeTo(INTERVAL_A8, false));

        // Cbb up d2 == Dbbbb, which isn't supported
        assertEquals(Pitch.C_DOUBLE_FLAT_4, Pitch.C_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        // Cb up d2 == Dbbb, which isn't supported
        assertEquals(Pitch.C_FLAT_4, Pitch.C_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.D_DOUBLE_FLAT_4, Pitch.C_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.D_FLAT_4, Pitch.C_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.D_4, Pitch.C_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        // Dbb up d2 == Ebbbb, which isn't supported
        assertEquals(Pitch.D_DOUBLE_FLAT_4, Pitch.D_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        // Db up d2 == Ebbb, which isn't supported
        assertEquals(Pitch.D_FLAT_4, Pitch.D_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.E_DOUBLE_FLAT_4, Pitch.D_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.E_FLAT_4, Pitch.D_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.E_4, Pitch.D_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        // Ebb up d2 == Fbbb, which isn't supported
        assertEquals(Pitch.E_DOUBLE_FLAT_4, Pitch.E_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.F_DOUBLE_FLAT_4, Pitch.E_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.F_FLAT_4, Pitch.E_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.F_4, Pitch.E_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.F_SHARP_4, Pitch.E_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        // Fbb up d2 == Gbbbb, which isn't supported
        assertEquals(Pitch.F_DOUBLE_FLAT_4, Pitch.F_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        // Fb up d2 == Gbbb, which isn't supported
        assertEquals(Pitch.F_FLAT_4, Pitch.F_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.G_DOUBLE_FLAT_4, Pitch.F_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.G_FLAT_4, Pitch.F_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.G_4, Pitch.F_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        // Gbb up d2 == Abbbb, which isn't supported
        assertEquals(Pitch.G_DOUBLE_FLAT_4, Pitch.G_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        // Gb up d2 == Abbb, which isn't supported
        assertEquals(Pitch.G_FLAT_4, Pitch.G_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.A_DOUBLE_FLAT_4, Pitch.G_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.A_FLAT_4, Pitch.G_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.A_4, Pitch.G_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        // Abb up d2 == Bbbbb, which isn't supported
        assertEquals(Pitch.A_DOUBLE_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transposeTo(INTERVAL_d2, true));
        // Ab up d2 == Bbbb, which isn't supported
        assertEquals(Pitch.A_FLAT_4, Pitch.A_FLAT_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.B_DOUBLE_FLAT_4, Pitch.A_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_SHARP_4.transposeTo(INTERVAL_d2, true));
        assertEquals(Pitch.B_4, Pitch.A_DOUBLE_SHARP_4.transposeTo(INTERVAL_d2, true));

        assertEquals(Pitch.D_FLAT_1, Pitch.C_1.transposeTo(INTERVAL_m2, true));
        assertEquals(Pitch.D_FLAT_2, Pitch.C_1.transposeTo(INTERVAL_m9, true));
        assertEquals(Pitch.B_0, Pitch.C_1.transposeTo(INTERVAL_m2, false));
        assertEquals(Pitch.B_0, Pitch.C_2.transposeTo(INTERVAL_m9, false));

        assertEquals(Pitch.D_1, Pitch.C_1.transposeTo(INTERVAL_M2, true));
        assertEquals(Pitch.D_2, Pitch.C_1.transposeTo(INTERVAL_M9, true));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_1.transposeTo(INTERVAL_M2, false));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_2.transposeTo(INTERVAL_M9, false));

        // Ax up A2 == Bxx, which isn't supported
        assertEquals(Pitch.C_DOUBLE_SHARP_5, Pitch.A_DOUBLE_SHARP_4.transposeTo(INTERVAL_A2, true));
        // Ax up A2 == Bxx, which isn't supported
        assertEquals(Pitch.C_DOUBLE_SHARP_6, Pitch.A_DOUBLE_SHARP_4.transposeTo(INTERVAL_A9, true));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transposeTo(INTERVAL_A2, true));
        assertEquals(Pitch.B_FLAT_5, Pitch.A_DOUBLE_FLAT_4.transposeTo(INTERVAL_A9, true));
        assertEquals(Pitch.F_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transposeTo(INTERVAL_A2, false));
        assertEquals(Pitch.F_FLAT_3, Pitch.A_DOUBLE_FLAT_4.transposeTo(INTERVAL_A9, false));

        assertEquals(Pitch.E_4, Pitch.C_4.transposeTo(INTERVAL_M3, true));
        assertEquals(Pitch.E_5, Pitch.C_4.transposeTo(INTERVAL_M10, true));
        assertEquals(Pitch.A_FLAT_3, Pitch.C_4.transposeTo(INTERVAL_M3, false));
        assertEquals(Pitch.A_FLAT_2, Pitch.C_4.transposeTo(INTERVAL_M10, false));

        assertEquals(Pitch.G_3, Pitch.C_4.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.G_3, Pitch.C_5.transposeTo(INTERVAL_P11, false));
        assertEquals(Pitch.G_3, Pitch.C_6.transposeTo(INTERVAL_P18, false));
        assertEquals(Pitch.G_3, Pitch.C_7.transposeTo(INTERVAL_P25, false));
        assertEquals(Pitch.G_3, Pitch.C_8.transposeTo(INTERVAL_P32, false));
        assertEquals(Pitch.G_3, Pitch.C_9.transposeTo(INTERVAL_P39, false));
        assertEquals(Pitch.G_3, Pitch.C_10.transposeTo(INTERVAL_P46, false));
        assertEquals(Pitch.F_4, Pitch.C_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.F_4, Pitch.C_3.transposeTo(INTERVAL_P11, true));
        assertEquals(Pitch.F_4, Pitch.C_2.transposeTo(INTERVAL_P18, true));
        assertEquals(Pitch.F_4, Pitch.C_1.transposeTo(INTERVAL_P25, true));
        assertEquals(Pitch.F_4, Pitch.C_0.transposeTo(INTERVAL_P32, true));
        assertEquals(Pitch.G_4, Pitch.C_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.G_4, Pitch.C_3.transposeTo(INTERVAL_P12, true));
        assertEquals(Pitch.G_4, Pitch.C_2.transposeTo(INTERVAL_P19, true));
        assertEquals(Pitch.G_4, Pitch.C_1.transposeTo(INTERVAL_P26, true));
        assertEquals(Pitch.G_4, Pitch.C_0.transposeTo(INTERVAL_P33, true));
        assertEquals(Pitch.F_3, Pitch.C_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.F_3, Pitch.C_5.transposeTo(INTERVAL_P12, false));
        assertEquals(Pitch.F_3, Pitch.C_6.transposeTo(INTERVAL_P19, false));
        assertEquals(Pitch.F_3, Pitch.C_7.transposeTo(INTERVAL_P26, false));
        assertEquals(Pitch.F_3, Pitch.C_8.transposeTo(INTERVAL_P33, false));
        assertEquals(Pitch.F_3, Pitch.C_9.transposeTo(INTERVAL_P40, false));
        assertEquals(Pitch.F_3, Pitch.C_10.transposeTo(INTERVAL_P47, false));
        assertEquals(Pitch.A_3, Pitch.D_4.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.G_4, Pitch.D_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.A_4, Pitch.D_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.G_3, Pitch.D_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.B_3, Pitch.E_4.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.A_4, Pitch.E_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.B_4, Pitch.E_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.A_3, Pitch.E_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.B_FLAT_4, Pitch.F_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.C_5, Pitch.F_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.B_FLAT_3, Pitch.F_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.D_4, Pitch.G_4.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.C_5, Pitch.G_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.D_5, Pitch.G_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.C_4, Pitch.G_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.E_4, Pitch.A_4.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.D_5, Pitch.A_4.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.E_5, Pitch.A_4.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.D_4, Pitch.A_4.transposeTo(INTERVAL_P5, false));
        assertEquals(Pitch.F_SHARP_3, Pitch.B_3.transposeTo(INTERVAL_P4, false));
        assertEquals(Pitch.E_4, Pitch.B_3.transposeTo(INTERVAL_P4, true));
        assertEquals(Pitch.F_SHARP_4, Pitch.B_3.transposeTo(INTERVAL_P5, true));
        assertEquals(Pitch.E_3, Pitch.B_3.transposeTo(INTERVAL_P5, false));

        assertEquals(Pitch.D_5, Pitch.F_SHARP_4.transposeTo(INTERVAL_m6, true));
        assertEquals(Pitch.D_6, Pitch.F_SHARP_4.transposeTo(INTERVAL_m13, true));
        assertEquals(Pitch.A_SHARP_3, Pitch.F_SHARP_4.transposeTo(INTERVAL_m6, false));
        assertEquals(Pitch.A_SHARP_2, Pitch.F_SHARP_4.transposeTo(INTERVAL_m13, false));
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
        expectedException.expect(RuntimeException.class);
        assertNull(Pitch.C_0.transposeTo(PitchClass.B, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.B_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.G_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.C_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transposeTo(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_10));

        // Basic testing
        expectedException = ExpectedException.none();
        assertEquals(Pitch.C_3, Pitch.C_4.transposeTo(PitchClass.C, Octave.OCTAVE_3));
        assertEquals(Pitch.A_3, Pitch.C_4.transposeTo(PitchClass.A, Octave.OCTAVE_3));
        assertEquals(Pitch.C_5, Pitch.C_4.transposeTo(PitchClass.C, Octave.OCTAVE_5));
        assertEquals(Pitch.A_5, Pitch.C_4.transposeTo(PitchClass.A, Octave.OCTAVE_5));
    }

    @Test
    public void isTransposableToPitch() throws Exception {
        // Pitches should always be transposable to another Pitch
        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) assertTrue(p1.isTransposableTo(p2));
        }
    }

    @Test
    public void transposeToPitch() throws Exception {
        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) assertEquals(p2, p1.transposeTo(p2));
        }
    }

}