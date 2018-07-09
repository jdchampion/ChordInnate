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
    public void test_illegalArgumentException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        // Transposition on null items is impossible
        Pitch.C_0.transpose(PitchClass.C, null);
        Pitch.C_0.transpose(null, Octave.OCTAVE_0);
        Pitch.C_0.transpose(null, null);
    }

    @Test
    public void testField_OCTAVE() throws Exception {
        /*
         * The Octave for each Pitch should match the number indicated in the name of the Pitch.
         * Example: the Pitch "F_SHARP_5" should contain OCTAVE_5
         */
        for (Pitch pitch : Pitch.STANDARD_PITCH_LOOKUP.values()) {
            assertEquals(
                    pitch.OCTAVE.getNumber(),
                    (int) Integer.valueOf(pitch.getName().replaceAll("\\D*", ""))
            );
        }

        // Also test for non-standard pitches
        Pitch test = Pitch.withName("Fx#bbb4");
        assertEquals(test.OCTAVE.getNumber(), (int) Integer.valueOf(test.getName().replaceAll("\\D*", "")));
    }

    @Test
    public void testField_ABSOLUTE_PITCH() throws Exception {
        /*
         * All Pitches should have an absolute pitch (MIDI value) = (12 * octave) + base,
         * none of which should go beyond 127 as the highest pitch.
         */
        for (Pitch pitch : Pitch.STANDARD_PITCH_LOOKUP.values()) {
            int base = pitch.PITCH_CLASS.BASE_PITCH_CLASS.BASE_MIDI_VALUE;
            assertEquals("Pitch " + pitch.getName() + " failed to match the expected MIDI value.",
                    // Use a formula that handles edge cases (like Cb (base = -1), B# (base = 12)...)
                    (12 * pitch.OCTAVE.getNumber() + (base < 0 ? 12 + base : (base >= 12 ? base % 12 : base))),
                    pitch.ABSOLUTE_PITCH
            );

            assertTrue("Pitch " + pitch.getName() + " is not within playable MIDI range.",
                    pitch.ABSOLUTE_PITCH >= 0 && pitch.ABSOLUTE_PITCH < 128);
        }

        // Also test for non-standard pitches
        Pitch test = Pitch.withName("Fx#bbb4");
        assertEquals(
                (12 * test.OCTAVE.getNumber() + test.PITCH_CLASS.BASE_PITCH_CLASS.BASE_MIDI_VALUE),
                test.ABSOLUTE_PITCH
        );

    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Scale cMajor = new Scale("C Major"),
                dMajor = new Scale("D Major"),
               fMajor = new Scale("Fx#bbb Major");

        Chord cMaj = new Chord("Cmaj"),
                dMaj = new Chord("Dmaj"),
                fMaj = new Chord("Fx#bbbmaj");

        Pitch c0 = Pitch.C_0, c10 = Pitch.C_10;
        Pitch f4 = Pitch.withName("Fx#bbb4");

        assertTrue(c0.isDiatonicTo(cMajor));
        assertTrue(c0.isDiatonicTo(cMaj));
        assertFalse(c0.isDiatonicTo(dMajor));
        assertFalse(c0.isDiatonicTo(dMaj));

        assertTrue(c10.isDiatonicTo(cMajor));
        assertTrue(c10.isDiatonicTo(cMaj));
        assertFalse(c10.isDiatonicTo(dMajor));
        assertFalse(c10.isDiatonicTo(dMaj));

        assertFalse(f4.isDiatonicTo(cMajor));
        assertFalse(f4.isDiatonicTo(cMaj));
        assertTrue(f4.isDiatonicTo(fMajor));
        assertTrue(f4.isDiatonicTo(fMaj));
    }

    @Test
    public void isEnharmonicTo() throws Exception {
        // White keys
        assertTrue(Pitch.C_1.isEnharmonicTo(Pitch.B_SHARP_0));
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
        assertTrue(Pitch.A_SHARP_0.isEnharmonicTo(Pitch.C_DOUBLE_FLAT_1));
        assertTrue(Pitch.B_FLAT_0.isEnharmonicTo(Pitch.A_SHARP_0));
        assertTrue(Pitch.B_FLAT_0.isEnharmonicTo(Pitch.C_DOUBLE_FLAT_1));
        assertTrue(Pitch.C_DOUBLE_FLAT_1.isEnharmonicTo(Pitch.A_SHARP_0));
        assertTrue(Pitch.C_DOUBLE_FLAT_1.isEnharmonicTo(Pitch.B_FLAT_0));

        // Also test for non-standard pitches
        assertTrue(Pitch.withName("Fx#bbb0").isEnharmonicTo(Pitch.F_0));
    }

    @Test
    public void transposeInterval() throws Exception {
        // (expected values verified by http://www.musictheory.net/calculators/interval)
        assertEquals(Pitch.C_1, Pitch.C_1.transpose(true, P1));
        assertEquals(Pitch.C_2, Pitch.C_1.transpose(true, P8));
        assertEquals(Pitch.C_1, Pitch.C_1.transpose(false, P1));
        assertEquals(Pitch.C_0, Pitch.C_1.transpose(false, P8));

        assertEquals(Pitch.C_FLAT_3, Pitch.C_4.transpose(true, d1));
        assertEquals(Pitch.C_FLAT_4, Pitch.C_4.transpose(true, d8));
        assertEquals(Pitch.C_FLAT_5, Pitch.C_4.transpose(true, d15));

        assertEquals(Pitch.C_SHARP_4, Pitch.C_4.transpose(false, d1));
        assertEquals(Pitch.C_SHARP_3, Pitch.C_4.transpose(false, d8));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_4.transpose(false, d15));
        assertEquals(Pitch.C_2, Pitch.C_1.transpose(true, P8));
        assertEquals(Pitch.C_3, Pitch.C_1.transpose(true, P15));
        assertEquals(Pitch.C_0, Pitch.C_1.transpose(false, P8));
        assertEquals(Pitch.C_0, Pitch.C_2.transpose(false, P15));

        assertEquals(Pitch.C_SHARP_1, Pitch.C_1.transpose(true, A1));
        assertEquals(Pitch.C_SHARP_2, Pitch.C_1.transpose(true, A8));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_1.transpose(false, A1));
        assertEquals(Pitch.C_FLAT_0, Pitch.C_2.transpose(false, A8));

        assertEquals(Pitch.withName("Dbbbb4"), Pitch.C_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.withName("Dbbb4"), Pitch.C_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.D_DOUBLE_FLAT_4, Pitch.C_4.transpose(true, d2));
        assertEquals(Pitch.D_FLAT_4, Pitch.C_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.D_4, Pitch.C_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.withName("Ebbbb4"), Pitch.D_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.withName("Ebbb4"), Pitch.D_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.E_DOUBLE_FLAT_4, Pitch.D_4.transpose(true, d2));
        assertEquals(Pitch.E_FLAT_4, Pitch.D_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.E_4, Pitch.D_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.withName("Fbbb4"), Pitch.E_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.F_DOUBLE_FLAT_4, Pitch.E_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.F_FLAT_4, Pitch.E_4.transpose(true, d2));
        assertEquals(Pitch.F_4, Pitch.E_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.F_SHARP_4, Pitch.E_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.withName("Gbbbb4"), Pitch.F_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.withName("Gbbb4"), Pitch.F_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.G_DOUBLE_FLAT_4, Pitch.F_4.transpose(true, d2));
        assertEquals(Pitch.G_FLAT_4, Pitch.F_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.G_4, Pitch.F_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.withName("Abbbb4"), Pitch.G_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.withName("Abbb4"), Pitch.G_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.A_DOUBLE_FLAT_4, Pitch.G_4.transpose(true, d2));
        assertEquals(Pitch.A_FLAT_4, Pitch.G_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.A_4, Pitch.G_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.withName("Bbbbb4"), Pitch.A_DOUBLE_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.withName("Bbbb4"), Pitch.A_FLAT_4.transpose(true, d2));
        assertEquals(Pitch.B_DOUBLE_FLAT_4, Pitch.A_4.transpose(true, d2));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_SHARP_4.transpose(true, d2));
        assertEquals(Pitch.B_4, Pitch.A_DOUBLE_SHARP_4.transpose(true, d2));

        assertEquals(Pitch.D_FLAT_1, Pitch.C_1.transpose(true, m2));
        assertEquals(Pitch.D_FLAT_2, Pitch.C_1.transpose(true, m9));
        assertEquals(Pitch.B_0, Pitch.C_1.transpose(false, m2));
        assertEquals(Pitch.B_0, Pitch.C_2.transpose(false, m9));

        assertEquals(Pitch.D_1, Pitch.C_1.transpose(true, M2));
        assertEquals(Pitch.D_2, Pitch.C_1.transpose(true, M9));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_1.transpose(false, M2));
        assertEquals(Pitch.B_FLAT_0, Pitch.C_2.transpose(false, M9));

        assertEquals(Pitch.withName("Bx#5"), Pitch.A_DOUBLE_SHARP_4.transpose(true, A2));
        assertEquals(Pitch.withName("Bx#6"), Pitch.A_DOUBLE_SHARP_4.transpose(true, A9));
        assertEquals(Pitch.B_FLAT_4, Pitch.A_DOUBLE_FLAT_4.transpose(true, A2));
        assertEquals(Pitch.B_FLAT_5, Pitch.A_DOUBLE_FLAT_4.transpose(true, A9));
        assertEquals(Pitch.withName("Gbbb4"), Pitch.A_DOUBLE_FLAT_4.transpose(false, A2));
        assertEquals(Pitch.withName("Gbbb3"), Pitch.A_DOUBLE_FLAT_4.transpose(false, A9));

        assertEquals(Pitch.E_4, Pitch.C_4.transpose(true, M3));
        assertEquals(Pitch.E_5, Pitch.C_4.transpose(true, M10));
        assertEquals(Pitch.A_FLAT_3, Pitch.C_4.transpose(false, M3));
        assertEquals(Pitch.A_FLAT_2, Pitch.C_4.transpose(false, M10));

        assertEquals(Pitch.G_3, Pitch.C_4.transpose(false, P4));
        assertEquals(Pitch.G_3, Pitch.C_5.transpose(false, P11));
        assertEquals(Pitch.G_3, Pitch.C_6.transpose(false, P18));
        assertEquals(Pitch.G_3, Pitch.C_7.transpose(false, P25));
        assertEquals(Pitch.G_3, Pitch.C_8.transpose(false, P32));
        assertEquals(Pitch.G_3, Pitch.C_9.transpose(false, P39));
        assertEquals(Pitch.G_3, Pitch.C_10.transpose(false, P46));
        assertEquals(Pitch.F_4, Pitch.C_4.transpose(true, P4));
        assertEquals(Pitch.F_4, Pitch.C_3.transpose(true, P11));
        assertEquals(Pitch.F_4, Pitch.C_2.transpose(true, P18));
        assertEquals(Pitch.F_4, Pitch.C_1.transpose(true, P25));
        assertEquals(Pitch.F_4, Pitch.C_0.transpose(true, P32));
        assertEquals(Pitch.G_4, Pitch.C_4.transpose(true, P5));
        assertEquals(Pitch.G_4, Pitch.C_3.transpose(true, P12));
        assertEquals(Pitch.G_4, Pitch.C_2.transpose(true, P19));
        assertEquals(Pitch.G_4, Pitch.C_1.transpose(true, P26));
        assertEquals(Pitch.G_4, Pitch.C_0.transpose(true, P33));
        assertEquals(Pitch.F_3, Pitch.C_4.transpose(false, P5));
        assertEquals(Pitch.F_3, Pitch.C_5.transpose(false, P12));
        assertEquals(Pitch.F_3, Pitch.C_6.transpose(false, P19));
        assertEquals(Pitch.F_3, Pitch.C_7.transpose(false, P26));
        assertEquals(Pitch.F_3, Pitch.C_8.transpose(false, P33));
        assertEquals(Pitch.F_3, Pitch.C_9.transpose(false, P40));
        assertEquals(Pitch.F_3, Pitch.C_10.transpose(false, P47));
        assertEquals(Pitch.A_3, Pitch.D_4.transpose(false, P4));
        assertEquals(Pitch.G_4, Pitch.D_4.transpose(true, P4));
        assertEquals(Pitch.A_4, Pitch.D_4.transpose(true, P5));
        assertEquals(Pitch.G_3, Pitch.D_4.transpose(false, P5));
        assertEquals(Pitch.B_3, Pitch.E_4.transpose(false, P4));
        assertEquals(Pitch.A_4, Pitch.E_4.transpose(true, P4));
        assertEquals(Pitch.B_4, Pitch.E_4.transpose(true, P5));
        assertEquals(Pitch.A_3, Pitch.E_4.transpose(false, P5));
        assertEquals(Pitch.B_FLAT_4, Pitch.F_4.transpose(true, P4));
        assertEquals(Pitch.C_5, Pitch.F_4.transpose(true, P5));
        assertEquals(Pitch.B_FLAT_3, Pitch.F_4.transpose(false, P5));
        assertEquals(Pitch.D_4, Pitch.G_4.transpose(false, P4));
        assertEquals(Pitch.C_5, Pitch.G_4.transpose(true, P4));
        assertEquals(Pitch.D_5, Pitch.G_4.transpose(true, P5));
        assertEquals(Pitch.C_4, Pitch.G_4.transpose(false, P5));
        assertEquals(Pitch.E_4, Pitch.A_4.transpose(false, P4));
        assertEquals(Pitch.D_5, Pitch.A_4.transpose(true, P4));
        assertEquals(Pitch.E_5, Pitch.A_4.transpose(true, P5));
        assertEquals(Pitch.D_4, Pitch.A_4.transpose(false, P5));
        assertEquals(Pitch.F_SHARP_3, Pitch.B_3.transpose(false, P4));
        assertEquals(Pitch.E_4, Pitch.B_3.transpose(true, P4));
        assertEquals(Pitch.F_SHARP_4, Pitch.B_3.transpose(true, P5));
        assertEquals(Pitch.E_3, Pitch.B_3.transpose(false, P5));

        assertEquals(Pitch.D_5, Pitch.F_SHARP_4.transpose(true, m6));
        assertEquals(Pitch.D_6, Pitch.F_SHARP_4.transpose(true, m13));
        assertEquals(Pitch.A_SHARP_3, Pitch.F_SHARP_4.transpose(false, m6));
        assertEquals(Pitch.A_SHARP_2, Pitch.F_SHARP_4.transpose(false, m13));

        // Also test for non-standard pitches
        assertEquals(Pitch.withName("Bxbbx#bb1"), Pitch.withName("Fxbbx#b0").transpose(true, P4));
        assertEquals(Pitch.withName("B#1"), Pitch.withName("Fx0").transpose(true, P4));
        assertEquals(Pitch.withName("Abbbbx4"), Pitch.withName("Fbbbbx4").transpose(true, M3));
    }

    @Test
    public void isTransposablePitchClass() throws Exception {
        // Items out of MIDI range should not be transposable
        assertFalse(Pitch.A_0.isTransposable(PitchClass.A, Octave.OCTAVE_10));
        assertFalse(Pitch.withName("Fx#0").isTransposable(PitchClass.withName("Fx#", false), Octave.OCTAVE_10));

        // Transposing to the same Pitch should still work
        assertTrue(Pitch.C_0.isTransposable(PitchClass.C, Octave.OCTAVE_0));
        assertTrue(Pitch.withName("Fx#0").isTransposable(PitchClass.withName("Fx#", false), Octave.OCTAVE_0));
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

        // Also test for non-standard pitches
        assertEquals(Pitch.withName("Cxx4"), Pitch.C_4.transpose(PitchClass.withName("Cxx", false), Octave.OCTAVE_4));
        assertEquals(Pitch.withName("Bx#b4"), Pitch.C_4.transpose(PitchClass.withName("Bx#b", false), Octave.OCTAVE_4));
    }

    @Test
    public void isTransposablePitch() throws Exception {
        // Pitches should always be transposable to another Pitch
        for (Pitch p1 : Pitch.STANDARD_PITCH_LOOKUP.values()) {
            for (Pitch p2 : Pitch.STANDARD_PITCH_LOOKUP.values()) assertTrue(p1.isTransposable(p2));
        }
    }

    @Test
    public void transposePitch() throws Exception {
        for (Pitch p1 : Pitch.STANDARD_PITCH_LOOKUP.values()) {
            for (Pitch p2 : Pitch.STANDARD_PITCH_LOOKUP.values()) assertEquals(p2, p1.transpose(p2));
        }
    }

}