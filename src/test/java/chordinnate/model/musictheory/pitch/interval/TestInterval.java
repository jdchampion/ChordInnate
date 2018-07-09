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
        Interval i = Interval.withShortName("A76");
    }

    @Test
    public void getSemitones() throws Exception {
        assertEquals(0, P1.getSemitones());
        assertEquals(1, A1.getSemitones());
        assertEquals(11, d8.getSemitones());
        assertEquals(12, P8.getSemitones());

        assertEquals(0, d2.getSemitones());
        assertEquals(1, m2.getSemitones());
        assertEquals(2, M2.getSemitones());
        assertEquals(3, A2.getSemitones());

        assertEquals(2, d3.getSemitones());
        assertEquals(3, m3.getSemitones());
        assertEquals(4, M3.getSemitones());
        assertEquals(5, A3.getSemitones());

        assertEquals(4, d4.getSemitones());
        assertEquals(5, P4.getSemitones());
        assertEquals(6, A4.getSemitones());

        assertEquals(6, d5.getSemitones());
        assertEquals(7, P5.getSemitones());
        assertEquals(8, A5.getSemitones());

        assertEquals(7, d6.getSemitones());
        assertEquals(8, m6.getSemitones());
        assertEquals(9, M6.getSemitones());
        assertEquals(10, A6.getSemitones());

        assertEquals(9, d7.getSemitones());
        assertEquals(10, m7.getSemitones());
        assertEquals(11, M7.getSemitones());
        assertEquals(12, A7.getSemitones());

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
        assertEquals(1, P1.getSimpleDiatonic());
        assertEquals(1, A1.getSimpleDiatonic());
        assertEquals(1, d8.getSimpleDiatonic());
        assertEquals(1, P8.getSimpleDiatonic());

        assertEquals(2, d2.getSimpleDiatonic());
        assertEquals(2, m2.getSimpleDiatonic());
        assertEquals(2, M2.getSimpleDiatonic());
        assertEquals(2, A2.getSimpleDiatonic());

        assertEquals(3, d3.getSimpleDiatonic());
        assertEquals(3, m3.getSimpleDiatonic());
        assertEquals(3, M3.getSimpleDiatonic());
        assertEquals(3, A3.getSimpleDiatonic());

        assertEquals(4, d4.getSimpleDiatonic());
        assertEquals(4, P4.getSimpleDiatonic());
        assertEquals(4, A4.getSimpleDiatonic());

        assertEquals(5, d5.getSimpleDiatonic());
        assertEquals(5, P5.getSimpleDiatonic());
        assertEquals(5, A5.getSimpleDiatonic());

        assertEquals(6, d6.getSimpleDiatonic());
        assertEquals(6, m6.getSimpleDiatonic());
        assertEquals(6, M6.getSimpleDiatonic());
        assertEquals(6, A6.getSimpleDiatonic());

        assertEquals(7, d7.getSimpleDiatonic());
        assertEquals(7, m7.getSimpleDiatonic());
        assertEquals(7, M7.getSimpleDiatonic());
        assertEquals(7, A7.getSimpleDiatonic());

        assertEquals(1, P1.getCompoundDiatonic());
        assertEquals(1, A1.getCompoundDiatonic());
        assertEquals(8, d8.getCompoundDiatonic());
        assertEquals(8, P8.getCompoundDiatonic());

        assertEquals(2, d2.getCompoundDiatonic());
        assertEquals(2, m2.getCompoundDiatonic());
        assertEquals(2, M2.getCompoundDiatonic());
        assertEquals(2, A2.getCompoundDiatonic());

        assertEquals(3, d3.getCompoundDiatonic());
        assertEquals(3, m3.getCompoundDiatonic());
        assertEquals(3, M3.getCompoundDiatonic());
        assertEquals(3, A3.getCompoundDiatonic());

        assertEquals(4, d4.getCompoundDiatonic());
        assertEquals(4, P4.getCompoundDiatonic());
        assertEquals(4, A4.getCompoundDiatonic());

        assertEquals(5, d5.getCompoundDiatonic());
        assertEquals(5, P5.getCompoundDiatonic());
        assertEquals(5, A5.getCompoundDiatonic());

        assertEquals(6, d6.getCompoundDiatonic());
        assertEquals(6, m6.getCompoundDiatonic());
        assertEquals(6, M6.getCompoundDiatonic());
        assertEquals(6, A6.getCompoundDiatonic());

        assertEquals(7, d7.getCompoundDiatonic());
        assertEquals(7, m7.getCompoundDiatonic());
        assertEquals(7, M7.getCompoundDiatonic());
        assertEquals(7, A7.getCompoundDiatonic());

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
        assertEquals("P1", P1.SIMPLE_SHORT_NAME);
        assertEquals("A1", A1.SIMPLE_SHORT_NAME);
        assertEquals("d2", d2.SIMPLE_SHORT_NAME);
        assertEquals("m2", m2.SIMPLE_SHORT_NAME);
        assertEquals("M2", M2.SIMPLE_SHORT_NAME);
        assertEquals("A2", A2.SIMPLE_SHORT_NAME);
        assertEquals("d3", d3.SIMPLE_SHORT_NAME);
        assertEquals("m3", m3.SIMPLE_SHORT_NAME);
        assertEquals("M3", M3.SIMPLE_SHORT_NAME);
        assertEquals("A3", A3.SIMPLE_SHORT_NAME);
        assertEquals("d4", d4.SIMPLE_SHORT_NAME);
        assertEquals("P4", P4.SIMPLE_SHORT_NAME);
        assertEquals("A4", A4.SIMPLE_SHORT_NAME);
        assertEquals("d5", d5.SIMPLE_SHORT_NAME);
        assertEquals("P5", P5.SIMPLE_SHORT_NAME);
        assertEquals("A5", A5.SIMPLE_SHORT_NAME);
        assertEquals("d6", d6.SIMPLE_SHORT_NAME);
        assertEquals("m6", m6.SIMPLE_SHORT_NAME);
        assertEquals("M6", M6.SIMPLE_SHORT_NAME);
        assertEquals("A6", A6.SIMPLE_SHORT_NAME);
        assertEquals("d7", d7.SIMPLE_SHORT_NAME);
        assertEquals("m7", m7.SIMPLE_SHORT_NAME);
        assertEquals("M7", M7.SIMPLE_SHORT_NAME);
        assertEquals("A7", A7.SIMPLE_SHORT_NAME);
        assertEquals("d1", d8.SIMPLE_SHORT_NAME);
        assertEquals("P1", P8.SIMPLE_SHORT_NAME);

        // Arbitrary check on dynamic Intervals
        assertEquals("P1", Interval.withShortName("P8").SIMPLE_SHORT_NAME);
        assertEquals("A1", Interval.withShortName("A8").SIMPLE_SHORT_NAME);
        assertEquals("d2", Interval.withShortName("d9").SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.withShortName("m9").SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.withShortName("M9").SIMPLE_SHORT_NAME);
        assertEquals("A2", Interval.withShortName("A9").SIMPLE_SHORT_NAME);
        assertEquals("d3", Interval.withShortName("d10").SIMPLE_SHORT_NAME);
        assertEquals("m3", Interval.withShortName("m10").SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.withShortName("M10").SIMPLE_SHORT_NAME);
        assertEquals("A3", Interval.withShortName("A10").SIMPLE_SHORT_NAME);
        assertEquals("d4", Interval.withShortName("d11").SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.withShortName("P11").SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.withShortName("A11").SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.withShortName("d12").SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.withShortName("P12").SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.withShortName("A12").SIMPLE_SHORT_NAME);
        assertEquals("d6", Interval.withShortName("d13").SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.withShortName("m13").SIMPLE_SHORT_NAME);
        assertEquals("M6", Interval.withShortName("M13").SIMPLE_SHORT_NAME);
        assertEquals("A6", Interval.withShortName("A13").SIMPLE_SHORT_NAME);
        assertEquals("d7", Interval.withShortName("d14").SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.withShortName("m14").SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.withShortName("M14").SIMPLE_SHORT_NAME);
        assertEquals("A7", Interval.withShortName("A14").SIMPLE_SHORT_NAME);
        assertEquals("d1", Interval.withShortName("d15").SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.withShortName("P15").SIMPLE_SHORT_NAME);
    }

    @Test
    public void testField_COMPOUND_SHORT_NAME() throws Exception {
        assertEquals("P1", P1.COMPOUND_SHORT_NAME);
        assertEquals("A1", A1.COMPOUND_SHORT_NAME);
        assertEquals("d2", d2.COMPOUND_SHORT_NAME);
        assertEquals("m2", m2.COMPOUND_SHORT_NAME);
        assertEquals("M2", M2.COMPOUND_SHORT_NAME);
        assertEquals("A2", A2.COMPOUND_SHORT_NAME);
        assertEquals("d3", d3.COMPOUND_SHORT_NAME);
        assertEquals("m3", m3.COMPOUND_SHORT_NAME);
        assertEquals("M3", M3.COMPOUND_SHORT_NAME);
        assertEquals("A3", A3.COMPOUND_SHORT_NAME);
        assertEquals("d4", d4.COMPOUND_SHORT_NAME);
        assertEquals("P4", P4.COMPOUND_SHORT_NAME);
        assertEquals("A4", A4.COMPOUND_SHORT_NAME);
        assertEquals("d5", d5.COMPOUND_SHORT_NAME);
        assertEquals("P5", P5.COMPOUND_SHORT_NAME);
        assertEquals("A5", A5.COMPOUND_SHORT_NAME);
        assertEquals("d6", d6.COMPOUND_SHORT_NAME);
        assertEquals("m6", m6.COMPOUND_SHORT_NAME);
        assertEquals("M6", M6.COMPOUND_SHORT_NAME);
        assertEquals("A6", A6.COMPOUND_SHORT_NAME);
        assertEquals("d7", d7.COMPOUND_SHORT_NAME);
        assertEquals("m7", m7.COMPOUND_SHORT_NAME);
        assertEquals("M7", M7.COMPOUND_SHORT_NAME);
        assertEquals("A7", A7.COMPOUND_SHORT_NAME);
        assertEquals("d8", d8.COMPOUND_SHORT_NAME);
        assertEquals("P8", P8.COMPOUND_SHORT_NAME);

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").COMPOUND_SHORT_NAME);
        assertEquals("A8", Interval.withShortName("A8").COMPOUND_SHORT_NAME);
        assertEquals("d9", Interval.withShortName("d9").COMPOUND_SHORT_NAME);
        assertEquals("m9", Interval.withShortName("m9").COMPOUND_SHORT_NAME);
        assertEquals("M9", Interval.withShortName("M9").COMPOUND_SHORT_NAME);
        assertEquals("A9", Interval.withShortName("A9").COMPOUND_SHORT_NAME);
        assertEquals("d10", Interval.withShortName("d10").COMPOUND_SHORT_NAME);
        assertEquals("m10", Interval.withShortName("m10").COMPOUND_SHORT_NAME);
        assertEquals("M10", Interval.withShortName("M10").COMPOUND_SHORT_NAME);
        assertEquals("A10", Interval.withShortName("A10").COMPOUND_SHORT_NAME);
        assertEquals("d11", Interval.withShortName("d11").COMPOUND_SHORT_NAME);
        assertEquals("P11", Interval.withShortName("P11").COMPOUND_SHORT_NAME);
        assertEquals("A11", Interval.withShortName("A11").COMPOUND_SHORT_NAME);
        assertEquals("d12", Interval.withShortName("d12").COMPOUND_SHORT_NAME);
        assertEquals("P12", Interval.withShortName("P12").COMPOUND_SHORT_NAME);
        assertEquals("A12", Interval.withShortName("A12").COMPOUND_SHORT_NAME);
        assertEquals("d13", Interval.withShortName("d13").COMPOUND_SHORT_NAME);
        assertEquals("m13", Interval.withShortName("m13").COMPOUND_SHORT_NAME);
        assertEquals("M13", Interval.withShortName("M13").COMPOUND_SHORT_NAME);
        assertEquals("A13", Interval.withShortName("A13").COMPOUND_SHORT_NAME);
        assertEquals("d14", Interval.withShortName("d14").COMPOUND_SHORT_NAME);
        assertEquals("m14", Interval.withShortName("m14").COMPOUND_SHORT_NAME);
        assertEquals("M14", Interval.withShortName("M14").COMPOUND_SHORT_NAME);
        assertEquals("A14", Interval.withShortName("A14").COMPOUND_SHORT_NAME);
        assertEquals("d15", Interval.withShortName("d15").COMPOUND_SHORT_NAME);
        assertEquals("P15", Interval.withShortName("P15").COMPOUND_SHORT_NAME);
    }



    @Test
    public void invert() throws Exception {
        assertEquals(P1, P1.invert());
        assertEquals(d1, A1.invert());
        assertEquals(P1, P1.invert());
        assertEquals(M7, m2.invert());
        assertEquals(m7, M2.invert());
        assertEquals(d7, A2.invert());
        assertEquals(A6, d3.invert());
        assertEquals(M6, m3.invert());
        assertEquals(m6, M3.invert());
        assertEquals(d6, A3.invert());
        assertEquals(A5, d4.invert());
        assertEquals(P5, P4.invert());
        assertEquals(d5, A4.invert());
        assertEquals(A4, d5.invert());
        assertEquals(P4, P5.invert());
        assertEquals(d4, A5.invert());
        assertEquals(A3, d6.invert());
        assertEquals(M3, m6.invert());
        assertEquals(m3, M6.invert());
        assertEquals(d3, A6.invert());
        assertEquals(A2, d7.invert());
        assertEquals(M2, m7.invert());
        assertEquals(m2, M7.invert());
        assertEquals(d2, A7.invert());

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").invert().COMPOUND_SHORT_NAME);
        assertEquals("d8", Interval.withShortName("A8").invert().COMPOUND_SHORT_NAME);
        assertEquals("A14", Interval.withShortName("d9").invert().COMPOUND_SHORT_NAME);
        assertEquals("M14", Interval.withShortName("m9").invert().COMPOUND_SHORT_NAME);
        assertEquals("m14", Interval.withShortName("M9").invert().COMPOUND_SHORT_NAME);
        assertEquals("d14", Interval.withShortName("A9").invert().COMPOUND_SHORT_NAME);
        assertEquals("A13", Interval.withShortName("d10").invert().COMPOUND_SHORT_NAME);
        assertEquals("M13", Interval.withShortName("m10").invert().COMPOUND_SHORT_NAME);
        assertEquals("m13", Interval.withShortName("M10").invert().COMPOUND_SHORT_NAME);
        assertEquals("d13", Interval.withShortName("A10").invert().COMPOUND_SHORT_NAME);
        assertEquals("A12", Interval.withShortName("d11").invert().COMPOUND_SHORT_NAME);
        assertEquals("P12", Interval.withShortName("P11").invert().COMPOUND_SHORT_NAME);
        assertEquals("d12", Interval.withShortName("A11").invert().COMPOUND_SHORT_NAME);
        assertEquals("A11", Interval.withShortName("d12").invert().COMPOUND_SHORT_NAME);
        assertEquals("P11", Interval.withShortName("P12").invert().COMPOUND_SHORT_NAME);
        assertEquals("d11", Interval.withShortName("A12").invert().COMPOUND_SHORT_NAME);
        assertEquals("A10", Interval.withShortName("d13").invert().COMPOUND_SHORT_NAME);
        assertEquals("M10", Interval.withShortName("m13").invert().COMPOUND_SHORT_NAME);
        assertEquals("m10", Interval.withShortName("M13").invert().COMPOUND_SHORT_NAME);
        assertEquals("d10", Interval.withShortName("A13").invert().COMPOUND_SHORT_NAME);
        assertEquals("A9", Interval.withShortName("d14").invert().COMPOUND_SHORT_NAME);
        assertEquals("M9", Interval.withShortName("m14").invert().COMPOUND_SHORT_NAME);
        assertEquals("m9", Interval.withShortName("M14").invert().COMPOUND_SHORT_NAME);
        assertEquals("d9", Interval.withShortName("A14").invert().COMPOUND_SHORT_NAME);
        assertEquals("A15", Interval.withShortName("d15").invert().COMPOUND_SHORT_NAME);
        assertEquals("P15", Interval.withShortName("P15").invert().COMPOUND_SHORT_NAME);
    }

    @Test
    public void getIntervalBetween() throws Exception {
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).SIMPLE_SHORT_NAME);
        assertEquals("A1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_DOUBLE_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B, false).SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, true).SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("A2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A2", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_DOUBLE_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_DOUBLE_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("m3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A, false).SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, true).SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("A3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A3", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F, true).SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G, false).SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G, true).SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F, false).SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.C, PitchClass.F_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, false).SIMPLE_SHORT_NAME);
        assertEquals("M6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A, true).SIMPLE_SHORT_NAME);
        assertEquals("M6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("A6", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E_DOUBLE_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_DOUBLE_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, false).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B, true).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("A7", Interval.getIntervalBetween(PitchClass.C, PitchClass.B_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("A7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_DOUBLE_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, true).COMPOUND_SHORT_NAME);
        assertEquals("d1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).COMPOUND_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).COMPOUND_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).COMPOUND_SHORT_NAME);
    }
}