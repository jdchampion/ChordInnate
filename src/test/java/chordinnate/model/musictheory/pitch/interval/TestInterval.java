package chordinnate.model.musictheory.pitch.interval;

import chordinnate.model.musictheory.pitch.PitchClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static chordinnate.model.musictheory.pitch.interval.Interval.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestInterval {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getSemitones() {
        assertEquals(0, PERFECT_1.getSemitones());
        assertEquals(1, AUGMENTED_1.getSemitones());
        assertEquals(11, DIMINISHED_8.getSemitones());
        assertEquals(12, PERFECT_8.getSemitones());

        assertEquals(0, DIMINISHED_2.getSemitones());
        assertEquals(1, MINOR_2.getSemitones());
        assertEquals(2, MAJOR_2.getSemitones());
        assertEquals(3, AUGMENTED_2.getSemitones());

        assertEquals(2, DIMINISHED_3.getSemitones());
        assertEquals(3, MINOR_3.getSemitones());
        assertEquals(4, MAJOR_3.getSemitones());
        assertEquals(5, AUGMENTED_3.getSemitones());

        assertEquals(4, DIMINISHED_4.getSemitones());
        assertEquals(5, PERFECT_4.getSemitones());
        assertEquals(6, AUGMENTED_4.getSemitones());

        assertEquals(6, DIMINISHED_5.getSemitones());
        assertEquals(7, PERFECT_5.getSemitones());
        assertEquals(8, AUGMENTED_5.getSemitones());

        assertEquals(7, DIMINISHED_6.getSemitones());
        assertEquals(8, MINOR_6.getSemitones());
        assertEquals(9, MAJOR_6.getSemitones());
        assertEquals(10, AUGMENTED_6.getSemitones());

        assertEquals(9, DIMINISHED_7.getSemitones());
        assertEquals(10, MINOR_7.getSemitones());
        assertEquals(11, MAJOR_7.getSemitones());
        assertEquals(12, AUGMENTED_7.getSemitones());

        // Arbitrary check on dynamic Intervals
        assertEquals(12, Interval.withShortName("P8").getSemitones());
        assertEquals(12, Interval.withShortName("d9").getSemitones());
        assertEquals(13, Interval.withShortName("m9").getSemitones());
        assertEquals(13, Interval.withShortName("A8").getSemitones());
        assertEquals(14, Interval.withShortName("M9").getSemitones());
        assertEquals(14, Interval.withShortName("d10").getSemitones());
        assertEquals(15, Interval.withShortName("m10").getSemitones());
        assertEquals(15, Interval.withShortName("A9").getSemitones());
        assertEquals(16, Interval.withShortName("M10").getSemitones());
        assertEquals(16, Interval.withShortName("d11").getSemitones());
        assertEquals(17, Interval.withShortName("P11").getSemitones());
        assertEquals(17, Interval.withShortName("A10").getSemitones());
        assertEquals(18, Interval.withShortName("d12").getSemitones());
        assertEquals(18, Interval.withShortName("A11").getSemitones());
        assertEquals(19, Interval.withShortName("P12").getSemitones());
        assertEquals(19, Interval.withShortName("d13").getSemitones());
        assertEquals(20, Interval.withShortName("m13").getSemitones());
        assertEquals(20, Interval.withShortName("A12").getSemitones());
        assertEquals(21, Interval.withShortName("M13").getSemitones());
        assertEquals(21, Interval.withShortName("d14").getSemitones());
        assertEquals(22, Interval.withShortName("m14").getSemitones());
        assertEquals(22, Interval.withShortName("A13").getSemitones());
        assertEquals(23, Interval.withShortName("M14").getSemitones());
        assertEquals(23, Interval.withShortName("d15").getSemitones());
        assertEquals(24, Interval.withShortName("P15").getSemitones());
        assertEquals(24, Interval.withShortName("A14").getSemitones());
    }

    @Test
    public void getOctaves() {
        assertEquals(4, Interval.withShortName("A35").getOctaves());
        assertEquals(1, Interval.withShortName("d13").getOctaves());
    }

    @Test
    public void getSimpleDiatonic() {
        assertEquals(1, PERFECT_1.getSimpleDiatonic());
        assertEquals(1, AUGMENTED_1.getSimpleDiatonic());
        assertEquals(1, DIMINISHED_8.getSimpleDiatonic());
        assertEquals(1, PERFECT_8.getSimpleDiatonic());

        assertEquals(2, DIMINISHED_2.getSimpleDiatonic());
        assertEquals(2, MINOR_2.getSimpleDiatonic());
        assertEquals(2, MAJOR_2.getSimpleDiatonic());
        assertEquals(2, AUGMENTED_2.getSimpleDiatonic());

        assertEquals(3, DIMINISHED_3.getSimpleDiatonic());
        assertEquals(3, MINOR_3.getSimpleDiatonic());
        assertEquals(3, MAJOR_3.getSimpleDiatonic());
        assertEquals(3, AUGMENTED_3.getSimpleDiatonic());

        assertEquals(4, DIMINISHED_4.getSimpleDiatonic());
        assertEquals(4, PERFECT_4.getSimpleDiatonic());
        assertEquals(4, AUGMENTED_4.getSimpleDiatonic());

        assertEquals(5, DIMINISHED_5.getSimpleDiatonic());
        assertEquals(5, PERFECT_5.getSimpleDiatonic());
        assertEquals(5, AUGMENTED_5.getSimpleDiatonic());

        assertEquals(6, DIMINISHED_6.getSimpleDiatonic());
        assertEquals(6, MINOR_6.getSimpleDiatonic());
        assertEquals(6, MAJOR_6.getSimpleDiatonic());
        assertEquals(6, AUGMENTED_6.getSimpleDiatonic());

        assertEquals(7, DIMINISHED_7.getSimpleDiatonic());
        assertEquals(7, MINOR_7.getSimpleDiatonic());
        assertEquals(7, MAJOR_7.getSimpleDiatonic());
        assertEquals(7, AUGMENTED_7.getSimpleDiatonic());

        assertEquals(1, PERFECT_1.getCompoundDiatonic());
        assertEquals(1, AUGMENTED_1.getCompoundDiatonic());
        assertEquals(8, DIMINISHED_8.getCompoundDiatonic());
        assertEquals(8, PERFECT_8.getCompoundDiatonic());

        assertEquals(2, DIMINISHED_2.getCompoundDiatonic());
        assertEquals(2, MINOR_2.getCompoundDiatonic());
        assertEquals(2, MAJOR_2.getCompoundDiatonic());
        assertEquals(2, AUGMENTED_2.getCompoundDiatonic());

        assertEquals(3, DIMINISHED_3.getCompoundDiatonic());
        assertEquals(3, MINOR_3.getCompoundDiatonic());
        assertEquals(3, MAJOR_3.getCompoundDiatonic());
        assertEquals(3, AUGMENTED_3.getCompoundDiatonic());

        assertEquals(4, DIMINISHED_4.getCompoundDiatonic());
        assertEquals(4, PERFECT_4.getCompoundDiatonic());
        assertEquals(4, AUGMENTED_4.getCompoundDiatonic());

        assertEquals(5, DIMINISHED_5.getCompoundDiatonic());
        assertEquals(5, PERFECT_5.getCompoundDiatonic());
        assertEquals(5, AUGMENTED_5.getCompoundDiatonic());

        assertEquals(6, DIMINISHED_6.getCompoundDiatonic());
        assertEquals(6, MINOR_6.getCompoundDiatonic());
        assertEquals(6, MAJOR_6.getCompoundDiatonic());
        assertEquals(6, AUGMENTED_6.getCompoundDiatonic());

        assertEquals(7, DIMINISHED_7.getCompoundDiatonic());
        assertEquals(7, MINOR_7.getCompoundDiatonic());
        assertEquals(7, MAJOR_7.getCompoundDiatonic());
        assertEquals(7, AUGMENTED_7.getCompoundDiatonic());

        // Arbitrary check on dynamic Intervals
        assertEquals(8, Interval.withShortName("P8").getCompoundDiatonic());
        assertEquals(9, Interval.withShortName("d9").getCompoundDiatonic());
        assertEquals(9, Interval.withShortName("m9").getCompoundDiatonic());
        assertEquals(8, Interval.withShortName("A8").getCompoundDiatonic());
        assertEquals(9, Interval.withShortName("M9").getCompoundDiatonic());
        assertEquals(10, Interval.withShortName("d10").getCompoundDiatonic());
        assertEquals(10, Interval.withShortName("m10").getCompoundDiatonic());
        assertEquals(9, Interval.withShortName("A9").getCompoundDiatonic());
        assertEquals(10, Interval.withShortName("M10").getCompoundDiatonic());
        assertEquals(11, Interval.withShortName("d11").getCompoundDiatonic());
        assertEquals(11, Interval.withShortName("P11").getCompoundDiatonic());
        assertEquals(10, Interval.withShortName("A10").getCompoundDiatonic());
        assertEquals(12, Interval.withShortName("d12").getCompoundDiatonic());
        assertEquals(11, Interval.withShortName("A11").getCompoundDiatonic());
        assertEquals(12, Interval.withShortName("P12").getCompoundDiatonic());
        assertEquals(13, Interval.withShortName("d13").getCompoundDiatonic());
        assertEquals(13, Interval.withShortName("m13").getCompoundDiatonic());
        assertEquals(12, Interval.withShortName("A12").getCompoundDiatonic());
        assertEquals(13, Interval.withShortName("M13").getCompoundDiatonic());
        assertEquals(14, Interval.withShortName("d14").getCompoundDiatonic());
        assertEquals(14, Interval.withShortName("m14").getCompoundDiatonic());
        assertEquals(13, Interval.withShortName("A13").getCompoundDiatonic());
        assertEquals(14, Interval.withShortName("M14").getCompoundDiatonic());
        assertEquals(15, Interval.withShortName("d15").getCompoundDiatonic());
        assertEquals(15, Interval.withShortName("P15").getCompoundDiatonic());
        assertEquals(14, Interval.withShortName("A14").getCompoundDiatonic());
    }

    @Test
    public void getSimpleShortName() {
        assertEquals("P1", PERFECT_1.getSimpleShortName());
        assertEquals("A1", AUGMENTED_1.getSimpleShortName());
        assertEquals("d2", DIMINISHED_2.getSimpleShortName());
        assertEquals("m2", MINOR_2.getSimpleShortName());
        assertEquals("M2", MAJOR_2.getSimpleShortName());
        assertEquals("A2", AUGMENTED_2.getSimpleShortName());
        assertEquals("d3", DIMINISHED_3.getSimpleShortName());
        assertEquals("m3", MINOR_3.getSimpleShortName());
        assertEquals("M3", MAJOR_3.getSimpleShortName());
        assertEquals("A3", AUGMENTED_3.getSimpleShortName());
        assertEquals("d4", DIMINISHED_4.getSimpleShortName());
        assertEquals("P4", PERFECT_4.getSimpleShortName());
        assertEquals("A4", AUGMENTED_4.getSimpleShortName());
        assertEquals("d5", DIMINISHED_5.getSimpleShortName());
        assertEquals("P5", PERFECT_5.getSimpleShortName());
        assertEquals("A5", AUGMENTED_5.getSimpleShortName());
        assertEquals("d6", DIMINISHED_6.getSimpleShortName());
        assertEquals("m6", MINOR_6.getSimpleShortName());
        assertEquals("M6", MAJOR_6.getSimpleShortName());
        assertEquals("A6", AUGMENTED_6.getSimpleShortName());
        assertEquals("d7", DIMINISHED_7.getSimpleShortName());
        assertEquals("m7", MINOR_7.getSimpleShortName());
        assertEquals("M7", MAJOR_7.getSimpleShortName());
        assertEquals("A7", AUGMENTED_7.getSimpleShortName());
        assertEquals("d1", DIMINISHED_8.getSimpleShortName());
        assertEquals("P1", PERFECT_8.getSimpleShortName());

        // Arbitrary check on dynamic Intervals
        assertEquals("P1", Interval.withShortName("P8").getSimpleShortName());
        assertEquals("A1", Interval.withShortName("A8").getSimpleShortName());
        assertEquals("d2", Interval.withShortName("d9").getSimpleShortName());
        assertEquals("m2", Interval.withShortName("m9").getSimpleShortName());
        assertEquals("M2", Interval.withShortName("M9").getSimpleShortName());
        assertEquals("A2", Interval.withShortName("A9").getSimpleShortName());
        assertEquals("d3", Interval.withShortName("d10").getSimpleShortName());
        assertEquals("m3", Interval.withShortName("m10").getSimpleShortName());
        assertEquals("M3", Interval.withShortName("M10").getSimpleShortName());
        assertEquals("A3", Interval.withShortName("A10").getSimpleShortName());
        assertEquals("d4", Interval.withShortName("d11").getSimpleShortName());
        assertEquals("P4", Interval.withShortName("P11").getSimpleShortName());
        assertEquals("A4", Interval.withShortName("A11").getSimpleShortName());
        assertEquals("d5", Interval.withShortName("d12").getSimpleShortName());
        assertEquals("P5", Interval.withShortName("P12").getSimpleShortName());
        assertEquals("A5", Interval.withShortName("A12").getSimpleShortName());
        assertEquals("d6", Interval.withShortName("d13").getSimpleShortName());
        assertEquals("m6", Interval.withShortName("m13").getSimpleShortName());
        assertEquals("M6", Interval.withShortName("M13").getSimpleShortName());
        assertEquals("A6", Interval.withShortName("A13").getSimpleShortName());
        assertEquals("d7", Interval.withShortName("d14").getSimpleShortName());
        assertEquals("m7", Interval.withShortName("m14").getSimpleShortName());
        assertEquals("M7", Interval.withShortName("M14").getSimpleShortName());
        assertEquals("A7", Interval.withShortName("A14").getSimpleShortName());
        assertEquals("d1", Interval.withShortName("d15").getSimpleShortName());
        assertEquals("P1", Interval.withShortName("P15").getSimpleShortName());
    }

    @Test
    public void getCompoundShortName() {
        assertEquals("P1", PERFECT_1.getCompoundShortName());
        assertEquals("A1", AUGMENTED_1.getCompoundShortName());
        assertEquals("d2", DIMINISHED_2.getCompoundShortName());
        assertEquals("m2", MINOR_2.getCompoundShortName());
        assertEquals("M2", MAJOR_2.getCompoundShortName());
        assertEquals("A2", AUGMENTED_2.getCompoundShortName());
        assertEquals("d3", DIMINISHED_3.getCompoundShortName());
        assertEquals("m3", MINOR_3.getCompoundShortName());
        assertEquals("M3", MAJOR_3.getCompoundShortName());
        assertEquals("A3", AUGMENTED_3.getCompoundShortName());
        assertEquals("d4", DIMINISHED_4.getCompoundShortName());
        assertEquals("P4", PERFECT_4.getCompoundShortName());
        assertEquals("A4", AUGMENTED_4.getCompoundShortName());
        assertEquals("d5", DIMINISHED_5.getCompoundShortName());
        assertEquals("P5", PERFECT_5.getCompoundShortName());
        assertEquals("A5", AUGMENTED_5.getCompoundShortName());
        assertEquals("d6", DIMINISHED_6.getCompoundShortName());
        assertEquals("m6", MINOR_6.getCompoundShortName());
        assertEquals("M6", MAJOR_6.getCompoundShortName());
        assertEquals("A6", AUGMENTED_6.getCompoundShortName());
        assertEquals("d7", DIMINISHED_7.getCompoundShortName());
        assertEquals("m7", MINOR_7.getCompoundShortName());
        assertEquals("M7", MAJOR_7.getCompoundShortName());
        assertEquals("A7", AUGMENTED_7.getCompoundShortName());
        assertEquals("d8", DIMINISHED_8.getCompoundShortName());
        assertEquals("P8", PERFECT_8.getCompoundShortName());

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").getCompoundShortName());
        assertEquals("A8", Interval.withShortName("A8").getCompoundShortName());
        assertEquals("d9", Interval.withShortName("d9").getCompoundShortName());
        assertEquals("m9", Interval.withShortName("m9").getCompoundShortName());
        assertEquals("M9", Interval.withShortName("M9").getCompoundShortName());
        assertEquals("A9", Interval.withShortName("A9").getCompoundShortName());
        assertEquals("d10", Interval.withShortName("d10").getCompoundShortName());
        assertEquals("m10", Interval.withShortName("m10").getCompoundShortName());
        assertEquals("M10", Interval.withShortName("M10").getCompoundShortName());
        assertEquals("A10", Interval.withShortName("A10").getCompoundShortName());
        assertEquals("d11", Interval.withShortName("d11").getCompoundShortName());
        assertEquals("P11", Interval.withShortName("P11").getCompoundShortName());
        assertEquals("A11", Interval.withShortName("A11").getCompoundShortName());
        assertEquals("d12", Interval.withShortName("d12").getCompoundShortName());
        assertEquals("P12", Interval.withShortName("P12").getCompoundShortName());
        assertEquals("A12", Interval.withShortName("A12").getCompoundShortName());
        assertEquals("d13", Interval.withShortName("d13").getCompoundShortName());
        assertEquals("m13", Interval.withShortName("m13").getCompoundShortName());
        assertEquals("M13", Interval.withShortName("M13").getCompoundShortName());
        assertEquals("A13", Interval.withShortName("A13").getCompoundShortName());
        assertEquals("d14", Interval.withShortName("d14").getCompoundShortName());
        assertEquals("m14", Interval.withShortName("m14").getCompoundShortName());
        assertEquals("M14", Interval.withShortName("M14").getCompoundShortName());
        assertEquals("A14", Interval.withShortName("A14").getCompoundShortName());
        assertEquals("d15", Interval.withShortName("d15").getCompoundShortName());
        assertEquals("P15", Interval.withShortName("P15").getCompoundShortName());
    }

    @Test
    public void invert() {
        assertEquals(PERFECT_1, PERFECT_1.invert());
        assertEquals(DIMINISHED_1, AUGMENTED_1.invert());
        assertEquals(PERFECT_1, PERFECT_1.invert());
        assertEquals(MAJOR_7, MINOR_2.invert());
        assertEquals(MINOR_7, MAJOR_2.invert());
        assertEquals(DIMINISHED_7, AUGMENTED_2.invert());
        assertEquals(AUGMENTED_6, DIMINISHED_3.invert());
        assertEquals(MAJOR_6, MINOR_3.invert());
        assertEquals(MINOR_6, MAJOR_3.invert());
        assertEquals(DIMINISHED_6, AUGMENTED_3.invert());
        assertEquals(AUGMENTED_5, DIMINISHED_4.invert());
        assertEquals(PERFECT_5, PERFECT_4.invert());
        assertEquals(DIMINISHED_5, AUGMENTED_4.invert());
        assertEquals(AUGMENTED_4, DIMINISHED_5.invert());
        assertEquals(PERFECT_4, PERFECT_5.invert());
        assertEquals(DIMINISHED_4, AUGMENTED_5.invert());
        assertEquals(AUGMENTED_3, DIMINISHED_6.invert());
        assertEquals(MAJOR_3, MINOR_6.invert());
        assertEquals(MINOR_3, MAJOR_6.invert());
        assertEquals(DIMINISHED_3, AUGMENTED_6.invert());
        assertEquals(AUGMENTED_2, DIMINISHED_7.invert());
        assertEquals(MAJOR_2, MINOR_7.invert());
        assertEquals(MINOR_2, MAJOR_7.invert());
        assertEquals(DIMINISHED_2, AUGMENTED_7.invert());

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").invert().getCompoundShortName());
        assertEquals("d8", Interval.withShortName("A8").invert().getCompoundShortName());
        assertEquals("A14", Interval.withShortName("d9").invert().getCompoundShortName());
        assertEquals("M14", Interval.withShortName("m9").invert().getCompoundShortName());
        assertEquals("m14", Interval.withShortName("M9").invert().getCompoundShortName());
        assertEquals("d14", Interval.withShortName("A9").invert().getCompoundShortName());
        assertEquals("A13", Interval.withShortName("d10").invert().getCompoundShortName());
        assertEquals("M13", Interval.withShortName("m10").invert().getCompoundShortName());
        assertEquals("m13", Interval.withShortName("M10").invert().getCompoundShortName());
        assertEquals("d13", Interval.withShortName("A10").invert().getCompoundShortName());
        assertEquals("A12", Interval.withShortName("d11").invert().getCompoundShortName());
        assertEquals("P12", Interval.withShortName("P11").invert().getCompoundShortName());
        assertEquals("d12", Interval.withShortName("A11").invert().getCompoundShortName());
        assertEquals("A11", Interval.withShortName("d12").invert().getCompoundShortName());
        assertEquals("P11", Interval.withShortName("P12").invert().getCompoundShortName());
        assertEquals("d11", Interval.withShortName("A12").invert().getCompoundShortName());
        assertEquals("A10", Interval.withShortName("d13").invert().getCompoundShortName());
        assertEquals("M10", Interval.withShortName("m13").invert().getCompoundShortName());
        assertEquals("m10", Interval.withShortName("M13").invert().getCompoundShortName());
        assertEquals("d10", Interval.withShortName("A13").invert().getCompoundShortName());
        assertEquals("A9", Interval.withShortName("d14").invert().getCompoundShortName());
        assertEquals("M9", Interval.withShortName("m14").invert().getCompoundShortName());
        assertEquals("m9", Interval.withShortName("M14").invert().getCompoundShortName());
        assertEquals("d9", Interval.withShortName("A14").invert().getCompoundShortName());
        assertEquals("A15", Interval.withShortName("d15").invert().getCompoundShortName());
        assertEquals("P15", Interval.withShortName("P15").invert().getCompoundShortName());
    }

    @Test
    public void getIntervalBetween() {
        assertEquals("P1", Interval.between(PitchClass.C, PitchClass.C, true).getSimpleShortName());
        assertEquals("P1", Interval.between(PitchClass.C, PitchClass.C, false).getSimpleShortName());
        assertEquals("A1", Interval.between(PitchClass.C, PitchClass.C_SHARP, true).getSimpleShortName());
        assertEquals("A1", Interval.between(PitchClass.C, PitchClass.C_FLAT, false).getSimpleShortName());

        assertEquals("d2", Interval.between(PitchClass.C, PitchClass.D_DOUBLE_FLAT, true).getSimpleShortName());
        assertEquals("d2", Interval.between(PitchClass.C, PitchClass.B_SHARP, false).getSimpleShortName());
        assertEquals("m2", Interval.between(PitchClass.C, PitchClass.D_FLAT, true).getSimpleShortName());
        assertEquals("m2", Interval.between(PitchClass.C, PitchClass.B, false).getSimpleShortName());
        assertEquals("M2", Interval.between(PitchClass.C, PitchClass.D, true).getSimpleShortName());
        assertEquals("M2", Interval.between(PitchClass.C, PitchClass.B_FLAT, false).getSimpleShortName());
        assertEquals("A2", Interval.between(PitchClass.C, PitchClass.D_SHARP, true).getSimpleShortName());
        assertEquals("A2", Interval.between(PitchClass.C, PitchClass.B_DOUBLE_FLAT, false).getSimpleShortName());

        assertEquals("d3", Interval.between(PitchClass.C, PitchClass.E_DOUBLE_FLAT, true).getSimpleShortName());
        assertEquals("d3", Interval.between(PitchClass.C, PitchClass.A_SHARP, false).getSimpleShortName());
        assertEquals("m3", Interval.between(PitchClass.C, PitchClass.E_FLAT, true).getSimpleShortName());
        assertEquals("m3", Interval.between(PitchClass.C, PitchClass.A, false).getSimpleShortName());
        assertEquals("M3", Interval.between(PitchClass.C, PitchClass.E, true).getSimpleShortName());
        assertEquals("M3", Interval.between(PitchClass.C, PitchClass.A_FLAT, false).getSimpleShortName());
        assertEquals("A3", Interval.between(PitchClass.C, PitchClass.E_SHARP, true).getSimpleShortName());
        assertEquals("A3", Interval.between(PitchClass.C, PitchClass.A_DOUBLE_FLAT, false).getSimpleShortName());

        assertEquals("d4", Interval.between(PitchClass.C, PitchClass.F_FLAT, true).getSimpleShortName());
        assertEquals("d4", Interval.between(PitchClass.C, PitchClass.G_SHARP, false).getSimpleShortName());
        assertEquals("P4", Interval.between(PitchClass.C, PitchClass.F, true).getSimpleShortName());
        assertEquals("P4", Interval.between(PitchClass.C, PitchClass.G, false).getSimpleShortName());
        assertEquals("A4", Interval.between(PitchClass.C, PitchClass.F_SHARP, true).getSimpleShortName());
        assertEquals("A4", Interval.between(PitchClass.C, PitchClass.G_FLAT, false).getSimpleShortName());

        assertEquals("d5", Interval.between(PitchClass.C, PitchClass.G_FLAT, true).getSimpleShortName());
        assertEquals("d5", Interval.between(PitchClass.C, PitchClass.F_SHARP, false).getSimpleShortName());
        assertEquals("P5", Interval.between(PitchClass.C, PitchClass.G, true).getSimpleShortName());
        assertEquals("P5", Interval.between(PitchClass.C, PitchClass.F, false).getSimpleShortName());
        assertEquals("A5", Interval.between(PitchClass.C, PitchClass.G_SHARP, true).getSimpleShortName());
        assertEquals("A5", Interval.between(PitchClass.C, PitchClass.F_FLAT, false).getSimpleShortName());

        assertEquals("d6", Interval.between(PitchClass.C, PitchClass.A_DOUBLE_FLAT, true).getSimpleShortName());
        assertEquals("d6", Interval.between(PitchClass.C, PitchClass.E_SHARP, false).getSimpleShortName());
        assertEquals("m6", Interval.between(PitchClass.C, PitchClass.A_FLAT, true).getSimpleShortName());
        assertEquals("m6", Interval.between(PitchClass.C, PitchClass.E, false).getSimpleShortName());
        assertEquals("M6", Interval.between(PitchClass.C, PitchClass.A, true).getSimpleShortName());
        assertEquals("M6", Interval.between(PitchClass.C, PitchClass.E_FLAT, false).getSimpleShortName());
        assertEquals("A6", Interval.between(PitchClass.C, PitchClass.A_SHARP, true).getSimpleShortName());
        assertEquals("A6", Interval.between(PitchClass.C, PitchClass.E_DOUBLE_FLAT, false).getSimpleShortName());

        assertEquals("d7", Interval.between(PitchClass.C, PitchClass.B_DOUBLE_FLAT, true).getSimpleShortName());
        assertEquals("d7", Interval.between(PitchClass.C, PitchClass.D_SHARP, false).getSimpleShortName());
        assertEquals("m7", Interval.between(PitchClass.C, PitchClass.B_FLAT, true).getSimpleShortName());
        assertEquals("m7", Interval.between(PitchClass.C, PitchClass.D, false).getSimpleShortName());
        assertEquals("M7", Interval.between(PitchClass.C, PitchClass.B, true).getSimpleShortName());
        assertEquals("M7", Interval.between(PitchClass.C, PitchClass.D_FLAT, false).getSimpleShortName());
        assertEquals("A7", Interval.between(PitchClass.C, PitchClass.B_SHARP, true).getSimpleShortName());
        assertEquals("A7", Interval.between(PitchClass.C, PitchClass.D_DOUBLE_FLAT, false).getSimpleShortName());

        assertEquals("d1", Interval.between(PitchClass.C, PitchClass.C_FLAT, true).getSimpleShortName());
        assertEquals("d1", Interval.between(PitchClass.C, PitchClass.C_SHARP, false).getSimpleShortName());
        assertEquals("d1", Interval.between(PitchClass.C, PitchClass.C_FLAT, true).getCompoundShortName());
        assertEquals("d1", Interval.between(PitchClass.C, PitchClass.C_SHARP, false).getCompoundShortName());
        assertEquals("P1", Interval.between(PitchClass.C, PitchClass.C, true).getCompoundShortName());
        assertEquals("P1", Interval.between(PitchClass.C, PitchClass.C, false).getCompoundShortName());
    }

    @Test
    public void plus() {
        assertEquals(String.format("P1 + P1 should equal P1 but was %s", PERFECT_1.plus(PERFECT_1).getCompoundShortName()), PERFECT_1, PERFECT_1.plus(PERFECT_1));
        assertEquals(String.format("P1 + P8 should equal P8 but was %s", PERFECT_1.plus(PERFECT_8).getCompoundShortName()), PERFECT_8, PERFECT_1.plus(PERFECT_8));
        assertEquals(String.format("P4 + P5 should equal P8 but was %s", PERFECT_4.plus(PERFECT_5).getCompoundShortName()), PERFECT_8, PERFECT_4.plus(PERFECT_5));
        assertEquals(String.format("P11 + P5 should equal P15 but was %s", PERFECT_11.plus(PERFECT_5).getCompoundShortName()), PERFECT_15, PERFECT_11.plus(PERFECT_5));
        assertEquals(String.format("m2 + m2 should equal d3 but was %s", MINOR_2.plus(MINOR_2).getCompoundShortName()), DIMINISHED_3, MINOR_2.plus(MINOR_2));
        assertEquals(String.format("P5 + M2 should equal M6 but was %s", PERFECT_5.plus(MAJOR_2).getCompoundShortName()), MAJOR_6, PERFECT_5.plus(MAJOR_2));
        assertEquals(String.format("A2 + A3 should equal AAA4 but was %s", AUGMENTED_2.plus(AUGMENTED_3).getCompoundShortName()), Interval.withShortName("AAA4"), AUGMENTED_2.plus(AUGMENTED_3));
    }

    @Test
    public void minus() {
        assertEquals(String.format("P1 - P1 should equal P1 but was %s", PERFECT_1.minus(PERFECT_1).getCompoundShortName()), PERFECT_1, PERFECT_1.minus(PERFECT_1));
        assertEquals(String.format("P8 - P1 should equal P8 but was %s", PERFECT_8.minus(PERFECT_1).getCompoundShortName()), PERFECT_8, PERFECT_8.minus(PERFECT_1));
        assertEquals(String.format("P1 - P8 should equal P8 but was %s", PERFECT_1.minus(PERFECT_8).getCompoundShortName()), PERFECT_8, PERFECT_1.minus(PERFECT_8));
        assertEquals(String.format("P1 - P15 should equal P15 but was %s", PERFECT_1.minus(PERFECT_15).getCompoundShortName()), PERFECT_15, PERFECT_1.minus(PERFECT_15));
        assertEquals(String.format("P8 - P15 should equal P8 but was %s", PERFECT_8.minus(PERFECT_15).getCompoundShortName()), PERFECT_8, PERFECT_8.minus(PERFECT_15));
        assertEquals(String.format("P8 - P5 should equal P4 but was %s", PERFECT_8.minus(PERFECT_5).getCompoundShortName()), PERFECT_4, PERFECT_8.minus(PERFECT_5));
        assertEquals(String.format("P1 - P5 should equal P4 but was %s", PERFECT_1.minus(PERFECT_5).getCompoundShortName()), PERFECT_4, PERFECT_1.minus(PERFECT_5));
        assertEquals(String.format("m3 - m7 should equal P4 but was %s", MINOR_3.minus(MINOR_7).getCompoundShortName()), PERFECT_4, MINOR_3.minus(MINOR_7));
        assertEquals(String.format("m7 - m2 should equal M6 but was %s", MINOR_7.minus(MINOR_2).getCompoundShortName()), MAJOR_6, MINOR_7.minus(MINOR_2));
        assertEquals(String.format("P5 - m3 should equal M3 but was %s", PERFECT_5.minus(MINOR_3).getCompoundShortName()), MAJOR_3, PERFECT_5.minus(MINOR_3));
        assertEquals(String.format("m7 - A3 should equal dd5 but was %s", MINOR_7.minus(AUGMENTED_3).getCompoundShortName()), Interval.withShortName("dd5"), MINOR_7.minus(AUGMENTED_3));
    }
}