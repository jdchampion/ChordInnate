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
        Pitch.C_0.isTransposable(PitchClass.C, null);
        Pitch.C_0.isTransposable(null, Octave.OCTAVE_0);
        Pitch.C_0.isTransposable(null, null);
        Pitch.C_0.transpose(PitchClass.C, null);
        Pitch.C_0.transpose(null, Octave.OCTAVE_0);
        Pitch.C_0.transpose(null, null);
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
    public void transposeInterval() throws Exception {
        // (expected values verified by http://www.musictheory.net/calculators/interval)
        assertEquals(Pitch.C_1, Pitch.C_1.transpose(true, INTERVAL_P1));
        assertEquals(Pitch.C_2, Pitch.C_1.transpose(true, INTERVAL_P8));
        assertEquals(Pitch.C_1, Pitch.C_1.transpose(false, INTERVAL_P1));
        assertEquals(Pitch.C_0, Pitch.C_1.transpose(false, INTERVAL_P8));

        assertEquals(Pitch.C_FLAT_4, Pitch.C_4.transpose(true, INTERVAL_d1));
        assertEquals(Pitch.C_FLAT_5, Pitch.C_4.transpose(true, INTERVAL_d8));
        assertEquals(Pitch.C_FLAT_6, Pitch.C_4.transpose(true, INTERVAL_d15));

        assertEquals(Pitch.C_SHARP_3, Pitch.C_4.transpose(false, INTERVAL_d1));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_4.transpose(false, INTERVAL_d8));
        assertEquals(Pitch.C_SHARP_1, Pitch.C_4.transpose(false, INTERVAL_d15));
        assertEquals(Pitch.C_2, Pitch.C_1.transpose(true, INTERVAL_P8));
        assertEquals(Pitch.C_3, Pitch.C_1.transpose(true, INTERVAL_P15));
        assertEquals(Pitch.C_0, Pitch.C_1.transpose(false, INTERVAL_P8));
        assertEquals(Pitch.C_0, Pitch.C_2.transpose(false, INTERVAL_P15));

        assertEquals(Pitch.C_SHARP_1, Pitch.C_1.transpose(true, INTERVAL_A1));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_1.transpose(true, INTERVAL_A8));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_1.transpose(false, INTERVAL_A1));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_2.transpose(false, INTERVAL_A8));

        // Cbb up d2 == Dbbbb, which isn't supported
        assertEquals(Pitch.C_DOUBLE_FLAT_4, Pitch.C_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        // Cb up d2 == Dbbb, which isn't supported
        assertEquals(Pitch.C_FLAT_4, Pitch.C_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.D_DOUBLE_FLAT_4, Pitch.C_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.D_FLAT_4, Pitch.C_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.D_4, Pitch.C_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        // Dbb up d2 == Ebbbb, which isn't supported
        assertEquals(Pitch.D_DOUBLE_FLAT_4, Pitch.D_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        // Db up d2 == Ebbb, which isn't supported
        assertEquals(Pitch.D_FLAT_4, Pitch.D_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.E_DOUBLE_FLAT_4, Pitch.D_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.E_FLAT_4, Pitch.D_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.E_4, Pitch.D_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        // Ebb up d2 == Fbbb, which isn't supported
        assertEquals(Pitch.E_DOUBLE_FLAT_4, Pitch.E_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.F_DOUBLE_FLAT_4, Pitch.E_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.F_FLAT_4, Pitch.E_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.F_4, Pitch.E_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.F_SHARP_4, Pitch.E_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        // Fbb up d2 == Gbbbb, which isn't supported
        assertEquals(Pitch.F_DOUBLE_FLAT_4, Pitch.F_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        // Fb up d2 == Gbbb, which isn't supported
        assertEquals(Pitch.F_FLAT_4, Pitch.F_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.G_DOUBLE_FLAT_4, Pitch.F_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.G_FLAT_4, Pitch.F_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.G_4, Pitch.F_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        // Gbb up d2 == Abbbb, which isn't supported
        assertEquals(Pitch.G_DOUBLE_FLAT_4, Pitch.G_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        // Gb up d2 == Abbb, which isn't supported
        assertEquals(Pitch.G_FLAT_4, Pitch.G_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.A_DOUBLE_FLAT_4, Pitch.G_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.A_FLAT_4, Pitch.G_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.A_4, Pitch.G_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        // Abb up d2 == Bbbbb, which isn't supported
        assertEquals(Pitch.A_DOUBLE_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transpose(true, INTERVAL_d2));
        // Ab up d2 == Bbbb, which isn't supported
        assertEquals(Pitch.A_FLAT_4, Pitch.A_FLAT_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.B_DOUBLE_FLAT_4, Pitch.A_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_SHARP_4.transpose(true, INTERVAL_d2));
        assertEquals(Pitch.B_4, Pitch.A_DOUBLE_SHARP_4.transpose(true, INTERVAL_d2));

        assertEquals(Pitch.D_FLAT_1, Pitch.C_1.transpose(true, INTERVAL_m2));
        assertEquals(Pitch.D_FLAT_2, Pitch.C_1.transpose(true, INTERVAL_m9));
        assertEquals(Pitch.B_0, Pitch.C_1.transpose(false, INTERVAL_m2));
        assertEquals(Pitch.B_0, Pitch.C_2.transpose(false, INTERVAL_m9));

        assertEquals(Pitch.D_1, Pitch.C_1.transpose(true, INTERVAL_M2));
        assertEquals(Pitch.D_2, Pitch.C_1.transpose(true, INTERVAL_M9));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_1.transpose(false, INTERVAL_M2));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_2.transpose(false, INTERVAL_M9));

        // Ax up A2 == Bxx, which isn't supported
        assertEquals(Pitch.C_DOUBLE_SHARP_5, Pitch.A_DOUBLE_SHARP_4.transpose(true, INTERVAL_A2));
        // Ax up A2 == Bxx, which isn't supported
        assertEquals(Pitch.C_DOUBLE_SHARP_6, Pitch.A_DOUBLE_SHARP_4.transpose(true, INTERVAL_A9));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transpose(true, INTERVAL_A2));
        assertEquals(Pitch.B_FLAT_5, Pitch.A_DOUBLE_FLAT_4.transpose(true, INTERVAL_A9));
        assertEquals(Pitch.F_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transpose(false, INTERVAL_A2));
        assertEquals(Pitch.F_FLAT_3, Pitch.A_DOUBLE_FLAT_4.transpose(false, INTERVAL_A9));

        assertEquals(Pitch.E_4, Pitch.C_4.transpose(true, INTERVAL_M3));
        assertEquals(Pitch.E_5, Pitch.C_4.transpose(true, INTERVAL_M10));
        assertEquals(Pitch.A_FLAT_3, Pitch.C_4.transpose(false, INTERVAL_M3));
        assertEquals(Pitch.A_FLAT_2, Pitch.C_4.transpose(false, INTERVAL_M10));

        assertEquals(Pitch.G_3, Pitch.C_4.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.G_3, Pitch.C_5.transpose(false, INTERVAL_P11));
        assertEquals(Pitch.G_3, Pitch.C_6.transpose(false, INTERVAL_P18));
        assertEquals(Pitch.G_3, Pitch.C_7.transpose(false, INTERVAL_P25));
        assertEquals(Pitch.G_3, Pitch.C_8.transpose(false, INTERVAL_P32));
        assertEquals(Pitch.G_3, Pitch.C_9.transpose(false, INTERVAL_P39));
        assertEquals(Pitch.G_3, Pitch.C_10.transpose(false, INTERVAL_P46));
        assertEquals(Pitch.F_4, Pitch.C_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.F_4, Pitch.C_3.transpose(true, INTERVAL_P11));
        assertEquals(Pitch.F_4, Pitch.C_2.transpose(true, INTERVAL_P18));
        assertEquals(Pitch.F_4, Pitch.C_1.transpose(true, INTERVAL_P25));
        assertEquals(Pitch.F_4, Pitch.C_0.transpose(true, INTERVAL_P32));
        assertEquals(Pitch.G_4, Pitch.C_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.G_4, Pitch.C_3.transpose(true, INTERVAL_P12));
        assertEquals(Pitch.G_4, Pitch.C_2.transpose(true, INTERVAL_P19));
        assertEquals(Pitch.G_4, Pitch.C_1.transpose(true, INTERVAL_P26));
        assertEquals(Pitch.G_4, Pitch.C_0.transpose(true, INTERVAL_P33));
        assertEquals(Pitch.F_3, Pitch.C_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.F_3, Pitch.C_5.transpose(false, INTERVAL_P12));
        assertEquals(Pitch.F_3, Pitch.C_6.transpose(false, INTERVAL_P19));
        assertEquals(Pitch.F_3, Pitch.C_7.transpose(false, INTERVAL_P26));
        assertEquals(Pitch.F_3, Pitch.C_8.transpose(false, INTERVAL_P33));
        assertEquals(Pitch.F_3, Pitch.C_9.transpose(false, INTERVAL_P40));
        assertEquals(Pitch.F_3, Pitch.C_10.transpose(false, INTERVAL_P47));
        assertEquals(Pitch.A_3, Pitch.D_4.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.G_4, Pitch.D_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.A_4, Pitch.D_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.G_3, Pitch.D_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.B_3, Pitch.E_4.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.A_4, Pitch.E_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.B_4, Pitch.E_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.A_3, Pitch.E_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.B_FLAT_4, Pitch.F_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.C_5, Pitch.F_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.B_FLAT_3, Pitch.F_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.D_4, Pitch.G_4.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.C_5, Pitch.G_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.D_5, Pitch.G_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.C_4, Pitch.G_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.E_4, Pitch.A_4.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.D_5, Pitch.A_4.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.E_5, Pitch.A_4.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.D_4, Pitch.A_4.transpose(false, INTERVAL_P5));
        assertEquals(Pitch.F_SHARP_3, Pitch.B_3.transpose(false, INTERVAL_P4));
        assertEquals(Pitch.E_4, Pitch.B_3.transpose(true, INTERVAL_P4));
        assertEquals(Pitch.F_SHARP_4, Pitch.B_3.transpose(true, INTERVAL_P5));
        assertEquals(Pitch.E_3, Pitch.B_3.transpose(false, INTERVAL_P5));

        assertEquals(Pitch.D_5, Pitch.F_SHARP_4.transpose(true, INTERVAL_m6));
        assertEquals(Pitch.D_6, Pitch.F_SHARP_4.transpose(true, INTERVAL_m13));
        assertEquals(Pitch.A_SHARP_3, Pitch.F_SHARP_4.transpose(false, INTERVAL_m6));
        assertEquals(Pitch.A_SHARP_2, Pitch.F_SHARP_4.transpose(false, INTERVAL_m13));
    }

    @Test
    public void isTransposablePitchClass() throws Exception {
        // Items out of MIDI range should not be transposable
        assertFalse(Pitch.A_0.isTransposable(PitchClass.A, Octave.OCTAVE_10));

        // Transposing to the same Pitch should still work
        assertTrue(Pitch.C_0.isTransposable(PitchClass.C, Octave.OCTAVE_0));
    }

    @Test
    public void transposePitchClass() throws Exception {
        // Cannot transpose to Pitches that don't exist
        expectedException.expect(RuntimeException.class);
        assertNull(Pitch.C_0.transpose(PitchClass.B, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.B_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.A_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.A, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.A_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.G_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.C_FLAT, Octave.OCTAVE_10));
        assertNull(Pitch.C_0.transpose(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_10));

        // Basic testing
        expectedException = ExpectedException.none();
        assertEquals(Pitch.C_3, Pitch.C_4.transpose(PitchClass.C, Octave.OCTAVE_3));
        assertEquals(Pitch.A_3, Pitch.C_4.transpose(PitchClass.A, Octave.OCTAVE_3));
        assertEquals(Pitch.C_5, Pitch.C_4.transpose(PitchClass.C, Octave.OCTAVE_5));
        assertEquals(Pitch.A_5, Pitch.C_4.transpose(PitchClass.A, Octave.OCTAVE_5));
    }

    @Test
    public void isTransposablePitch() throws Exception {
        // Pitches should always be transposable to another Pitch
        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) assertTrue(p1.isTransposable(p2));
        }
    }

    @Test
    public void transposePitch() throws Exception {
        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) assertEquals(p2, p1.transpose(p2));
        }
    }

}