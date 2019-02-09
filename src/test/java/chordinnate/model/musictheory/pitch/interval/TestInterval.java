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
    public void test() throws Exception {
//        Interval i = Interval.withShortName("A76");
        Interval i = Interval.withShortName("dd1");
    }

    @Test
    public void getSemitones() throws Exception {
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
    public void getSimpleDiatonic() throws Exception {
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
    public void testField_SIMPLE_SHORT_NAME() throws Exception {
        assertEquals("P1", PERFECT_1.simpleShortName);
        assertEquals("A1", AUGMENTED_1.simpleShortName);
        assertEquals("d2", DIMINISHED_2.simpleShortName);
        assertEquals("m2", MINOR_2.simpleShortName);
        assertEquals("M2", MAJOR_2.simpleShortName);
        assertEquals("A2", AUGMENTED_2.simpleShortName);
        assertEquals("d3", DIMINISHED_3.simpleShortName);
        assertEquals("m3", MINOR_3.simpleShortName);
        assertEquals("M3", MAJOR_3.simpleShortName);
        assertEquals("A3", AUGMENTED_3.simpleShortName);
        assertEquals("d4", DIMINISHED_4.simpleShortName);
        assertEquals("P4", PERFECT_4.simpleShortName);
        assertEquals("A4", AUGMENTED_4.simpleShortName);
        assertEquals("d5", DIMINISHED_5.simpleShortName);
        assertEquals("P5", PERFECT_5.simpleShortName);
        assertEquals("A5", AUGMENTED_5.simpleShortName);
        assertEquals("d6", DIMINISHED_6.simpleShortName);
        assertEquals("m6", MINOR_6.simpleShortName);
        assertEquals("M6", MAJOR_6.simpleShortName);
        assertEquals("A6", AUGMENTED_6.simpleShortName);
        assertEquals("d7", DIMINISHED_7.simpleShortName);
        assertEquals("m7", MINOR_7.simpleShortName);
        assertEquals("M7", MAJOR_7.simpleShortName);
        assertEquals("A7", AUGMENTED_7.simpleShortName);
        assertEquals("d1", DIMINISHED_8.simpleShortName);
        assertEquals("P1", PERFECT_8.simpleShortName);

        // Arbitrary check on dynamic Intervals
        assertEquals("P1", Interval.withShortName("P8").simpleShortName);
        assertEquals("A1", Interval.withShortName("A8").simpleShortName);
        assertEquals("d2", Interval.withShortName("d9").simpleShortName);
        assertEquals("m2", Interval.withShortName("m9").simpleShortName);
        assertEquals("M2", Interval.withShortName("M9").simpleShortName);
        assertEquals("A2", Interval.withShortName("A9").simpleShortName);
        assertEquals("d3", Interval.withShortName("d10").simpleShortName);
        assertEquals("m3", Interval.withShortName("m10").simpleShortName);
        assertEquals("M3", Interval.withShortName("M10").simpleShortName);
        assertEquals("A3", Interval.withShortName("A10").simpleShortName);
        assertEquals("d4", Interval.withShortName("d11").simpleShortName);
        assertEquals("P4", Interval.withShortName("P11").simpleShortName);
        assertEquals("A4", Interval.withShortName("A11").simpleShortName);
        assertEquals("d5", Interval.withShortName("d12").simpleShortName);
        assertEquals("P5", Interval.withShortName("P12").simpleShortName);
        assertEquals("A5", Interval.withShortName("A12").simpleShortName);
        assertEquals("d6", Interval.withShortName("d13").simpleShortName);
        assertEquals("m6", Interval.withShortName("m13").simpleShortName);
        assertEquals("M6", Interval.withShortName("M13").simpleShortName);
        assertEquals("A6", Interval.withShortName("A13").simpleShortName);
        assertEquals("d7", Interval.withShortName("d14").simpleShortName);
        assertEquals("m7", Interval.withShortName("m14").simpleShortName);
        assertEquals("M7", Interval.withShortName("M14").simpleShortName);
        assertEquals("A7", Interval.withShortName("A14").simpleShortName);
        assertEquals("d1", Interval.withShortName("d15").simpleShortName);
        assertEquals("P1", Interval.withShortName("P15").simpleShortName);
    }

    @Test
    public void testField_COMPOUND_SHORT_NAME() throws Exception {
        assertEquals("P1", PERFECT_1.compoundShortName);
        assertEquals("A1", AUGMENTED_1.compoundShortName);
        assertEquals("d2", DIMINISHED_2.compoundShortName);
        assertEquals("m2", MINOR_2.compoundShortName);
        assertEquals("M2", MAJOR_2.compoundShortName);
        assertEquals("A2", AUGMENTED_2.compoundShortName);
        assertEquals("d3", DIMINISHED_3.compoundShortName);
        assertEquals("m3", MINOR_3.compoundShortName);
        assertEquals("M3", MAJOR_3.compoundShortName);
        assertEquals("A3", AUGMENTED_3.compoundShortName);
        assertEquals("d4", DIMINISHED_4.compoundShortName);
        assertEquals("P4", PERFECT_4.compoundShortName);
        assertEquals("A4", AUGMENTED_4.compoundShortName);
        assertEquals("d5", DIMINISHED_5.compoundShortName);
        assertEquals("P5", PERFECT_5.compoundShortName);
        assertEquals("A5", AUGMENTED_5.compoundShortName);
        assertEquals("d6", DIMINISHED_6.compoundShortName);
        assertEquals("m6", MINOR_6.compoundShortName);
        assertEquals("M6", MAJOR_6.compoundShortName);
        assertEquals("A6", AUGMENTED_6.compoundShortName);
        assertEquals("d7", DIMINISHED_7.compoundShortName);
        assertEquals("m7", MINOR_7.compoundShortName);
        assertEquals("M7", MAJOR_7.compoundShortName);
        assertEquals("A7", AUGMENTED_7.compoundShortName);
        assertEquals("d8", DIMINISHED_8.compoundShortName);
        assertEquals("P8", PERFECT_8.compoundShortName);

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").compoundShortName);
        assertEquals("A8", Interval.withShortName("A8").compoundShortName);
        assertEquals("d9", Interval.withShortName("d9").compoundShortName);
        assertEquals("m9", Interval.withShortName("m9").compoundShortName);
        assertEquals("M9", Interval.withShortName("M9").compoundShortName);
        assertEquals("A9", Interval.withShortName("A9").compoundShortName);
        assertEquals("d10", Interval.withShortName("d10").compoundShortName);
        assertEquals("m10", Interval.withShortName("m10").compoundShortName);
        assertEquals("M10", Interval.withShortName("M10").compoundShortName);
        assertEquals("A10", Interval.withShortName("A10").compoundShortName);
        assertEquals("d11", Interval.withShortName("d11").compoundShortName);
        assertEquals("P11", Interval.withShortName("P11").compoundShortName);
        assertEquals("A11", Interval.withShortName("A11").compoundShortName);
        assertEquals("d12", Interval.withShortName("d12").compoundShortName);
        assertEquals("P12", Interval.withShortName("P12").compoundShortName);
        assertEquals("A12", Interval.withShortName("A12").compoundShortName);
        assertEquals("d13", Interval.withShortName("d13").compoundShortName);
        assertEquals("m13", Interval.withShortName("m13").compoundShortName);
        assertEquals("M13", Interval.withShortName("M13").compoundShortName);
        assertEquals("A13", Interval.withShortName("A13").compoundShortName);
        assertEquals("d14", Interval.withShortName("d14").compoundShortName);
        assertEquals("m14", Interval.withShortName("m14").compoundShortName);
        assertEquals("M14", Interval.withShortName("M14").compoundShortName);
        assertEquals("A14", Interval.withShortName("A14").compoundShortName);
        assertEquals("d15", Interval.withShortName("d15").compoundShortName);
        assertEquals("P15", Interval.withShortName("P15").compoundShortName);
    }



    @Test
    public void invert() throws Exception {
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
        assertEquals("P8", Interval.withShortName("P8").invert().compoundShortName);
        assertEquals("d8", Interval.withShortName("A8").invert().compoundShortName);
        assertEquals("A14", Interval.withShortName("d9").invert().compoundShortName);
        assertEquals("M14", Interval.withShortName("m9").invert().compoundShortName);
        assertEquals("m14", Interval.withShortName("M9").invert().compoundShortName);
        assertEquals("d14", Interval.withShortName("A9").invert().compoundShortName);
        assertEquals("A13", Interval.withShortName("d10").invert().compoundShortName);
        assertEquals("M13", Interval.withShortName("m10").invert().compoundShortName);
        assertEquals("m13", Interval.withShortName("M10").invert().compoundShortName);
        assertEquals("d13", Interval.withShortName("A10").invert().compoundShortName);
        assertEquals("A12", Interval.withShortName("d11").invert().compoundShortName);
        assertEquals("P12", Interval.withShortName("P11").invert().compoundShortName);
        assertEquals("d12", Interval.withShortName("A11").invert().compoundShortName);
        assertEquals("A11", Interval.withShortName("d12").invert().compoundShortName);
        assertEquals("P11", Interval.withShortName("P12").invert().compoundShortName);
        assertEquals("d11", Interval.withShortName("A12").invert().compoundShortName);
        assertEquals("A10", Interval.withShortName("d13").invert().compoundShortName);
        assertEquals("M10", Interval.withShortName("m13").invert().compoundShortName);
        assertEquals("m10", Interval.withShortName("M13").invert().compoundShortName);
        assertEquals("d10", Interval.withShortName("A13").invert().compoundShortName);
        assertEquals("A9", Interval.withShortName("d14").invert().compoundShortName);
        assertEquals("M9", Interval.withShortName("m14").invert().compoundShortName);
        assertEquals("m9", Interval.withShortName("M14").invert().compoundShortName);
        assertEquals("d9", Interval.withShortName("A14").invert().compoundShortName);
        assertEquals("A15", Interval.withShortName("d15").invert().compoundShortName);
        assertEquals("P15", Interval.withShortName("P15").invert().compoundShortName);
    }

    @Test
    public void getIntervalBetween() throws Exception {
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).simpleShortName);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).simpleShortName);
        assertEquals("A1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, true).simpleShortName);
        assertEquals("A1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, false).simpleShortName);

        assertEquals("d2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_DOUBLE_FLAT, true).simpleShortName);
        assertEquals("d2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_SHARP, false).simpleShortName);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, true).simpleShortName);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B, false).simpleShortName);
        assertEquals("M2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, true).simpleShortName);
        assertEquals("M2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_FLAT, false).simpleShortName);
        assertEquals("A2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_SHARP, true).simpleShortName);
        assertEquals("A2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_DOUBLE_FLAT, false).simpleShortName);

        assertEquals("d3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_DOUBLE_FLAT, true).simpleShortName);
        assertEquals("d3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_SHARP, false).simpleShortName);
        assertEquals("m3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_FLAT, true).simpleShortName);
        assertEquals("m3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A, false).simpleShortName);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, true).simpleShortName);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_FLAT, false).simpleShortName);
        assertEquals("A3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_SHARP, true).simpleShortName);
        assertEquals("A3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_FLAT, false).simpleShortName);

        assertEquals("d4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_FLAT, true).simpleShortName);
        assertEquals("d4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_SHARP, false).simpleShortName);
        assertEquals("P4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F, true).simpleShortName);
        assertEquals("P4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G, false).simpleShortName);
        assertEquals("A4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_SHARP, true).simpleShortName);
        assertEquals("A4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, false).simpleShortName);

        assertEquals("d5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, true).simpleShortName);
        assertEquals("d5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_SHARP, false).simpleShortName);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G, true).simpleShortName);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F, false).simpleShortName);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_SHARP, true).simpleShortName);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_FLAT, false).simpleShortName);

        assertEquals("d6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_FLAT, true).simpleShortName);
        assertEquals("d6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_SHARP, false).simpleShortName);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_FLAT, true).simpleShortName);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, false).simpleShortName);
        assertEquals("M6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A, true).simpleShortName);
        assertEquals("M6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_FLAT, false).simpleShortName);
        assertEquals("A6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_SHARP, true).simpleShortName);
        assertEquals("A6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_DOUBLE_FLAT, false).simpleShortName);

        assertEquals("d7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_DOUBLE_FLAT, true).simpleShortName);
        assertEquals("d7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_SHARP, false).simpleShortName);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_FLAT, true).simpleShortName);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, false).simpleShortName);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B, true).simpleShortName);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, false).simpleShortName);
        assertEquals("A7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_SHARP, true).simpleShortName);
        assertEquals("A7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_DOUBLE_FLAT, false).simpleShortName);

        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, true).simpleShortName);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).simpleShortName);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, true).compoundShortName);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).compoundShortName);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).compoundShortName);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).compoundShortName);
    }
}