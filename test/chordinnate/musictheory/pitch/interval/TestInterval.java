package chordinnate.musictheory.pitch.interval;

import chordinnate.musictheory.pitch.PitchClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static chordinnate.musictheory.pitch.interval.Interval.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestInterval {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testField_NUM_SEMITONES() throws Exception {
        assertEquals(0, PERFECT_UNISON.NUM_SEMITONES);
        assertEquals(1, AUGMENTED_UNISON.NUM_SEMITONES);
        assertEquals(11, DIMINISHED_OCTAVE.NUM_SEMITONES);
        assertEquals(12, PERFECT_OCTAVE.NUM_SEMITONES);

        assertEquals(0, DIMINISHED_SECOND.NUM_SEMITONES);
        assertEquals(1, MINOR_SECOND.NUM_SEMITONES);
        assertEquals(2, MAJOR_SECOND.NUM_SEMITONES);
        assertEquals(3, AUGMENTED_SECOND.NUM_SEMITONES);

        assertEquals(2, DIMINISHED_THIRD.NUM_SEMITONES);
        assertEquals(3, MINOR_THIRD.NUM_SEMITONES);
        assertEquals(4, MAJOR_THIRD.NUM_SEMITONES);
        assertEquals(5, AUGMENTED_THIRD.NUM_SEMITONES);

        assertEquals(4, DIMINISHED_FOURTH.NUM_SEMITONES);
        assertEquals(5, PERFECT_FOURTH.NUM_SEMITONES);
        assertEquals(6, AUGMENTED_FOURTH.NUM_SEMITONES);

        assertEquals(6, DIMINISHED_FIFTH.NUM_SEMITONES);
        assertEquals(7, PERFECT_FIFTH.NUM_SEMITONES);
        assertEquals(8, AUGMENTED_FIFTH.NUM_SEMITONES);

        assertEquals(7, DIMINISHED_SIXTH.NUM_SEMITONES);
        assertEquals(8, MINOR_SIXTH.NUM_SEMITONES);
        assertEquals(9, MAJOR_SIXTH.NUM_SEMITONES);
        assertEquals(10, AUGMENTED_SIXTH.NUM_SEMITONES);

        assertEquals(9, DIMINISHED_SEVENTH.NUM_SEMITONES);
        assertEquals(10, MINOR_SEVENTH.NUM_SEMITONES);
        assertEquals(11, MAJOR_SEVENTH.NUM_SEMITONES);
        assertEquals(12, AUGMENTED_SEVENTH.NUM_SEMITONES);

        // Arbitrary check on dynamic Intervals
        assertEquals(12, Interval.withShortName("P8").NUM_SEMITONES);
        assertEquals(12, Interval.withShortName("d9").NUM_SEMITONES);
        assertEquals(13, Interval.withShortName("m9").NUM_SEMITONES);
        assertEquals(13, Interval.withShortName("A8").NUM_SEMITONES);
        assertEquals(14, Interval.withShortName("M9").NUM_SEMITONES);
        assertEquals(14, Interval.withShortName("D10").NUM_SEMITONES);
        assertEquals(15, Interval.withShortName("m. 10").NUM_SEMITONES);
        assertEquals(15, Interval.withShortName("aug.9").NUM_SEMITONES);
        assertEquals(16, Interval.withShortName("Major10").NUM_SEMITONES);
        assertEquals(16, Interval.withShortName("d11").NUM_SEMITONES);
        assertEquals(17, Interval.withShortName("Perfect 11").NUM_SEMITONES);
        assertEquals(17, Interval.withShortName("aug 10").NUM_SEMITONES);
        assertEquals(18, Interval.withShortName("dim. 12").NUM_SEMITONES);
        assertEquals(18, Interval.withShortName("augmented 11").NUM_SEMITONES);
        assertEquals(19, Interval.withShortName("perf. 12").NUM_SEMITONES);
        assertEquals(19, Interval.withShortName("d.13").NUM_SEMITONES);
        assertEquals(20, Interval.withShortName("Min13").NUM_SEMITONES);
        assertEquals(20, Interval.withShortName("Aug.12").NUM_SEMITONES);
        assertEquals(21, Interval.withShortName("Major 13").NUM_SEMITONES);
        assertEquals(21, Interval.withShortName("D 14").NUM_SEMITONES);
        assertEquals(22, Interval.withShortName("Minor 14").NUM_SEMITONES);
        assertEquals(22, Interval.withShortName("Augmented13").NUM_SEMITONES);
        assertEquals(23, Interval.withShortName("maj14").NUM_SEMITONES);
        assertEquals(23, Interval.withShortName("d.15").NUM_SEMITONES);
        assertEquals(24, Interval.withShortName("Perfect 15").NUM_SEMITONES);
        assertEquals(24, Interval.withShortName("a14").NUM_SEMITONES);
    }

    @Test
    public void testField_NUMBER() throws Exception {
        assertEquals(1, PERFECT_UNISON.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, AUGMENTED_UNISON.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, DIMINISHED_OCTAVE.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, PERFECT_OCTAVE.SIMPLE_DIATONIC_NUMBER);

        assertEquals(2, DIMINISHED_SECOND.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, MINOR_SECOND.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, MAJOR_SECOND.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, AUGMENTED_SECOND.SIMPLE_DIATONIC_NUMBER);

        assertEquals(3, DIMINISHED_THIRD.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, MINOR_THIRD.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, MAJOR_THIRD.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, AUGMENTED_THIRD.SIMPLE_DIATONIC_NUMBER);

        assertEquals(4, DIMINISHED_FOURTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(4, PERFECT_FOURTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(4, AUGMENTED_FOURTH.SIMPLE_DIATONIC_NUMBER);

        assertEquals(5, DIMINISHED_FIFTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(5, PERFECT_FIFTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(5, AUGMENTED_FIFTH.SIMPLE_DIATONIC_NUMBER);

        assertEquals(6, DIMINISHED_SIXTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, MINOR_SIXTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, MAJOR_SIXTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, AUGMENTED_SIXTH.SIMPLE_DIATONIC_NUMBER);

        assertEquals(7, DIMINISHED_SEVENTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, MINOR_SEVENTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, MAJOR_SEVENTH.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, AUGMENTED_SEVENTH.SIMPLE_DIATONIC_NUMBER);

        assertEquals(1, PERFECT_UNISON.COMPOUND_DIATONIC_NUMBER);
        assertEquals(1, AUGMENTED_UNISON.COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, DIMINISHED_OCTAVE.COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, PERFECT_OCTAVE.COMPOUND_DIATONIC_NUMBER);

        assertEquals(2, DIMINISHED_SECOND.COMPOUND_DIATONIC_NUMBER);
        assertEquals(2, MINOR_SECOND.COMPOUND_DIATONIC_NUMBER);
        assertEquals(2, MAJOR_SECOND.COMPOUND_DIATONIC_NUMBER);
        assertEquals(2, AUGMENTED_SECOND.COMPOUND_DIATONIC_NUMBER);

        assertEquals(3, DIMINISHED_THIRD.COMPOUND_DIATONIC_NUMBER);
        assertEquals(3, MINOR_THIRD.COMPOUND_DIATONIC_NUMBER);
        assertEquals(3, MAJOR_THIRD.COMPOUND_DIATONIC_NUMBER);
        assertEquals(3, AUGMENTED_THIRD.COMPOUND_DIATONIC_NUMBER);

        assertEquals(4, DIMINISHED_FOURTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(4, PERFECT_FOURTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(4, AUGMENTED_FOURTH.COMPOUND_DIATONIC_NUMBER);

        assertEquals(5, DIMINISHED_FIFTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(5, PERFECT_FIFTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(5, AUGMENTED_FIFTH.COMPOUND_DIATONIC_NUMBER);

        assertEquals(6, DIMINISHED_SIXTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(6, MINOR_SIXTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(6, MAJOR_SIXTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(6, AUGMENTED_SIXTH.COMPOUND_DIATONIC_NUMBER);

        assertEquals(7, DIMINISHED_SEVENTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(7, MINOR_SEVENTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(7, MAJOR_SEVENTH.COMPOUND_DIATONIC_NUMBER);
        assertEquals(7, AUGMENTED_SEVENTH.COMPOUND_DIATONIC_NUMBER);

        // Arbitrary check on dynamic Intervals
        assertEquals(8, Interval.withShortName("P8").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, Interval.withShortName("d9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, Interval.withShortName("m9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, Interval.withShortName("A8").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, Interval.withShortName("M9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, Interval.withShortName("D10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, Interval.withShortName("m. 10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, Interval.withShortName("aug.9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, Interval.withShortName("Major10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, Interval.withShortName("d11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, Interval.withShortName("Perfect 11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, Interval.withShortName("aug 10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, Interval.withShortName("dim. 12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, Interval.withShortName("augmented 11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, Interval.withShortName("perf. 12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, Interval.withShortName("d.13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, Interval.withShortName("Min13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, Interval.withShortName("Aug.12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, Interval.withShortName("Major 13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, Interval.withShortName("D 14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, Interval.withShortName("Minor 14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, Interval.withShortName("Augmented13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, Interval.withShortName("maj14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, Interval.withShortName("d.15").COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, Interval.withShortName("Perfect 15").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, Interval.withShortName("a14").COMPOUND_DIATONIC_NUMBER);
    }

    @Test
    public void testField_SIMPLE_SHORT_NAME() throws Exception {
        assertEquals("P1", PERFECT_UNISON.SIMPLE_SHORT_NAME);
        assertEquals("A1", AUGMENTED_UNISON.SIMPLE_SHORT_NAME);
        assertEquals("d2", DIMINISHED_SECOND.SIMPLE_SHORT_NAME);
        assertEquals("m2", MINOR_SECOND.SIMPLE_SHORT_NAME);
        assertEquals("M2", MAJOR_SECOND.SIMPLE_SHORT_NAME);
        assertEquals("A2", AUGMENTED_SECOND.SIMPLE_SHORT_NAME);
        assertEquals("d3", DIMINISHED_THIRD.SIMPLE_SHORT_NAME);
        assertEquals("m3", MINOR_THIRD.SIMPLE_SHORT_NAME);
        assertEquals("M3", MAJOR_THIRD.SIMPLE_SHORT_NAME);
        assertEquals("A3", AUGMENTED_THIRD.SIMPLE_SHORT_NAME);
        assertEquals("d4", DIMINISHED_FOURTH.SIMPLE_SHORT_NAME);
        assertEquals("P4", PERFECT_FOURTH.SIMPLE_SHORT_NAME);
        assertEquals("A4", AUGMENTED_FOURTH.SIMPLE_SHORT_NAME);
        assertEquals("d5", DIMINISHED_FIFTH.SIMPLE_SHORT_NAME);
        assertEquals("P5", PERFECT_FIFTH.SIMPLE_SHORT_NAME);
        assertEquals("A5", AUGMENTED_FIFTH.SIMPLE_SHORT_NAME);
        assertEquals("d6", DIMINISHED_SIXTH.SIMPLE_SHORT_NAME);
        assertEquals("m6", MINOR_SIXTH.SIMPLE_SHORT_NAME);
        assertEquals("M6", MAJOR_SIXTH.SIMPLE_SHORT_NAME);
        assertEquals("A6", AUGMENTED_SIXTH.SIMPLE_SHORT_NAME);
        assertEquals("d7", DIMINISHED_SEVENTH.SIMPLE_SHORT_NAME);
        assertEquals("m7", MINOR_SEVENTH.SIMPLE_SHORT_NAME);
        assertEquals("M7", MAJOR_SEVENTH.SIMPLE_SHORT_NAME);
        assertEquals("A7", AUGMENTED_SEVENTH.SIMPLE_SHORT_NAME);
        assertEquals("d1", DIMINISHED_OCTAVE.SIMPLE_SHORT_NAME);
        assertEquals("P1", PERFECT_OCTAVE.SIMPLE_SHORT_NAME);

        // Arbitrary check on dynamic Intervals
        assertEquals("P1", Interval.withShortName("P8").SIMPLE_SHORT_NAME);
        assertEquals("A1", Interval.withShortName("A8").SIMPLE_SHORT_NAME);
        assertEquals("d2", Interval.withShortName("d9").SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.withShortName("m9").SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.withShortName("M9").SIMPLE_SHORT_NAME);
        assertEquals("A2", Interval.withShortName("aug.9").SIMPLE_SHORT_NAME);
        assertEquals("d3", Interval.withShortName("D10").SIMPLE_SHORT_NAME);
        assertEquals("m3", Interval.withShortName("m. 10").SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.withShortName("Major10").SIMPLE_SHORT_NAME);
        assertEquals("A3", Interval.withShortName("aug 10").SIMPLE_SHORT_NAME);
        assertEquals("d4", Interval.withShortName("d11").SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.withShortName("Perfect 11").SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.withShortName("augmented 11").SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.withShortName("dim. 12").SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.withShortName("perf. 12").SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.withShortName("Aug.12").SIMPLE_SHORT_NAME);
        assertEquals("d6", Interval.withShortName("d.13").SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.withShortName("Min13").SIMPLE_SHORT_NAME);
        assertEquals("M6", Interval.withShortName("Major 13").SIMPLE_SHORT_NAME);
        assertEquals("A6", Interval.withShortName("Augmented13").SIMPLE_SHORT_NAME);
        assertEquals("d7", Interval.withShortName("D 14").SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.withShortName("Minor 14").SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.withShortName("maj14").SIMPLE_SHORT_NAME);
        assertEquals("A7", Interval.withShortName("a14").SIMPLE_SHORT_NAME);
        assertEquals("d1", Interval.withShortName("d.15").SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.withShortName("Perfect 15").SIMPLE_SHORT_NAME);
    }

    @Test
    public void testField_COMPOUND_SHORT_NAME() throws Exception {
        assertEquals("P1", PERFECT_UNISON.COMPOUND_SHORT_NAME);
        assertEquals("A1", AUGMENTED_UNISON.COMPOUND_SHORT_NAME);
        assertEquals("d2", DIMINISHED_SECOND.COMPOUND_SHORT_NAME);
        assertEquals("m2", MINOR_SECOND.COMPOUND_SHORT_NAME);
        assertEquals("M2", MAJOR_SECOND.COMPOUND_SHORT_NAME);
        assertEquals("A2", AUGMENTED_SECOND.COMPOUND_SHORT_NAME);
        assertEquals("d3", DIMINISHED_THIRD.COMPOUND_SHORT_NAME);
        assertEquals("m3", MINOR_THIRD.COMPOUND_SHORT_NAME);
        assertEquals("M3", MAJOR_THIRD.COMPOUND_SHORT_NAME);
        assertEquals("A3", AUGMENTED_THIRD.COMPOUND_SHORT_NAME);
        assertEquals("d4", DIMINISHED_FOURTH.COMPOUND_SHORT_NAME);
        assertEquals("P4", PERFECT_FOURTH.COMPOUND_SHORT_NAME);
        assertEquals("A4", AUGMENTED_FOURTH.COMPOUND_SHORT_NAME);
        assertEquals("d5", DIMINISHED_FIFTH.COMPOUND_SHORT_NAME);
        assertEquals("P5", PERFECT_FIFTH.COMPOUND_SHORT_NAME);
        assertEquals("A5", AUGMENTED_FIFTH.COMPOUND_SHORT_NAME);
        assertEquals("d6", DIMINISHED_SIXTH.COMPOUND_SHORT_NAME);
        assertEquals("m6", MINOR_SIXTH.COMPOUND_SHORT_NAME);
        assertEquals("M6", MAJOR_SIXTH.COMPOUND_SHORT_NAME);
        assertEquals("A6", AUGMENTED_SIXTH.COMPOUND_SHORT_NAME);
        assertEquals("d7", DIMINISHED_SEVENTH.COMPOUND_SHORT_NAME);
        assertEquals("m7", MINOR_SEVENTH.COMPOUND_SHORT_NAME);
        assertEquals("M7", MAJOR_SEVENTH.COMPOUND_SHORT_NAME);
        assertEquals("A7", AUGMENTED_SEVENTH.COMPOUND_SHORT_NAME);
        assertEquals("d8", DIMINISHED_OCTAVE.COMPOUND_SHORT_NAME);
        assertEquals("P8", PERFECT_OCTAVE.COMPOUND_SHORT_NAME);

        // Arbitrary check on dynamic Intervals
        assertEquals("P8", Interval.withShortName("P8").COMPOUND_SHORT_NAME);
        assertEquals("A8", Interval.withShortName("A8").COMPOUND_SHORT_NAME);
        assertEquals("d9", Interval.withShortName("d9").COMPOUND_SHORT_NAME);
        assertEquals("m9", Interval.withShortName("m9").COMPOUND_SHORT_NAME);
        assertEquals("M9", Interval.withShortName("M9").COMPOUND_SHORT_NAME);
        assertEquals("A9", Interval.withShortName("aug.9").COMPOUND_SHORT_NAME);
        assertEquals("d10", Interval.withShortName("D10").COMPOUND_SHORT_NAME);
        assertEquals("m10", Interval.withShortName("m. 10").COMPOUND_SHORT_NAME);
        assertEquals("M10", Interval.withShortName("Major10").COMPOUND_SHORT_NAME);
        assertEquals("A10", Interval.withShortName("aug 10").COMPOUND_SHORT_NAME);
        assertEquals("d11", Interval.withShortName("d11").COMPOUND_SHORT_NAME);
        assertEquals("P11", Interval.withShortName("Perfect 11").COMPOUND_SHORT_NAME);
        assertEquals("A11", Interval.withShortName("augmented 11").COMPOUND_SHORT_NAME);
        assertEquals("d12", Interval.withShortName("dim. 12").COMPOUND_SHORT_NAME);
        assertEquals("P12", Interval.withShortName("perf. 12").COMPOUND_SHORT_NAME);
        assertEquals("A12", Interval.withShortName("Aug.12").COMPOUND_SHORT_NAME);
        assertEquals("d13", Interval.withShortName("d.13").COMPOUND_SHORT_NAME);
        assertEquals("m13", Interval.withShortName("Min13").COMPOUND_SHORT_NAME);
        assertEquals("M13", Interval.withShortName("Major 13").COMPOUND_SHORT_NAME);
        assertEquals("A13", Interval.withShortName("Augmented13").COMPOUND_SHORT_NAME);
        assertEquals("d14", Interval.withShortName("D 14").COMPOUND_SHORT_NAME);
        assertEquals("m14", Interval.withShortName("Minor 14").COMPOUND_SHORT_NAME);
        assertEquals("M14", Interval.withShortName("maj14").COMPOUND_SHORT_NAME);
        assertEquals("A14", Interval.withShortName("a14").COMPOUND_SHORT_NAME);
        assertEquals("d15", Interval.withShortName("d.15").COMPOUND_SHORT_NAME);
        assertEquals("P15", Interval.withShortName("Perfect 15").COMPOUND_SHORT_NAME);
    }


    @Test
    public void testField_ROMAN_NUMERAL_NAME() throws Exception {
        assertEquals("I", PERFECT_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("I+", AUGMENTED_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", DIMINISHED_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("ii", MINOR_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("II", MAJOR_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("II+", AUGMENTED_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", DIMINISHED_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("iii", MINOR_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("III", MAJOR_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("III+", AUGMENTED_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", DIMINISHED_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("IV", PERFECT_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("IV+", AUGMENTED_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("v˚", DIMINISHED_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("V", PERFECT_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("V+", AUGMENTED_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", DIMINISHED_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("vi", MINOR_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("VI", MAJOR_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("VI+", AUGMENTED_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", DIMINISHED_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("vii", MINOR_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("VII", MAJOR_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("VII+", AUGMENTED_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("i˚", DIMINISHED_OCTAVE.ROMAN_NUMERAL_NAME);
        assertEquals("I", PERFECT_OCTAVE.ROMAN_NUMERAL_NAME);

        // Arbitrary check on dynamic Intervals
        assertEquals("I", Interval.withShortName("P8").ROMAN_NUMERAL_NAME);
        assertEquals("I+", Interval.withShortName("A8").ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", Interval.withShortName("d9").ROMAN_NUMERAL_NAME);
        assertEquals("ii", Interval.withShortName("m9").ROMAN_NUMERAL_NAME);
        assertEquals("II", Interval.withShortName("M9").ROMAN_NUMERAL_NAME);
        assertEquals("II+", Interval.withShortName("aug.9").ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", Interval.withShortName("D10").ROMAN_NUMERAL_NAME);
        assertEquals("iii", Interval.withShortName("m. 10").ROMAN_NUMERAL_NAME);
        assertEquals("III", Interval.withShortName("Major10").ROMAN_NUMERAL_NAME);
        assertEquals("III+", Interval.withShortName("aug 10").ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", Interval.withShortName("d11").ROMAN_NUMERAL_NAME);
        assertEquals("IV", Interval.withShortName("Perfect 11").ROMAN_NUMERAL_NAME);
        assertEquals("IV+", Interval.withShortName("augmented 11").ROMAN_NUMERAL_NAME);
        assertEquals("v˚", Interval.withShortName("dim. 12").ROMAN_NUMERAL_NAME);
        assertEquals("V", Interval.withShortName("perf. 12").ROMAN_NUMERAL_NAME);
        assertEquals("V+", Interval.withShortName("Aug.12").ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", Interval.withShortName("d.13").ROMAN_NUMERAL_NAME);
        assertEquals("vi", Interval.withShortName("Min13").ROMAN_NUMERAL_NAME);
        assertEquals("VI", Interval.withShortName("Major 13").ROMAN_NUMERAL_NAME);
        assertEquals("VI+", Interval.withShortName("Augmented13").ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", Interval.withShortName("D 14").ROMAN_NUMERAL_NAME);
        assertEquals("vii", Interval.withShortName("Minor 14").ROMAN_NUMERAL_NAME);
        assertEquals("VII", Interval.withShortName("maj14").ROMAN_NUMERAL_NAME);
        assertEquals("VII+", Interval.withShortName("a14").ROMAN_NUMERAL_NAME);
        assertEquals("i˚", Interval.withShortName("d.15").ROMAN_NUMERAL_NAME);
        assertEquals("I", Interval.withShortName("Perfect 15").ROMAN_NUMERAL_NAME);
    }

    @Test
    public void getInversion() throws Exception {
        assertEquals(PERFECT_OCTAVE, PERFECT_UNISON.getInversion());
        assertEquals(DIMINISHED_OCTAVE, AUGMENTED_UNISON.getInversion());
        assertEquals(PERFECT_UNISON, PERFECT_OCTAVE.getInversion());
        assertEquals(MAJOR_SEVENTH, MINOR_SECOND.getInversion());
        assertEquals(MINOR_SEVENTH, MAJOR_SECOND.getInversion());
        assertEquals(DIMINISHED_SEVENTH, AUGMENTED_SECOND.getInversion());
        assertEquals(AUGMENTED_SIXTH, DIMINISHED_THIRD.getInversion());
        assertEquals(MAJOR_SIXTH, MINOR_THIRD.getInversion());
        assertEquals(MINOR_SIXTH, MAJOR_THIRD.getInversion());
        assertEquals(DIMINISHED_SIXTH, AUGMENTED_THIRD.getInversion());
        assertEquals(AUGMENTED_FIFTH, DIMINISHED_FOURTH.getInversion());
        assertEquals(PERFECT_FIFTH, PERFECT_FOURTH.getInversion());
        assertEquals(DIMINISHED_FIFTH, AUGMENTED_FOURTH.getInversion());
        assertEquals(AUGMENTED_FOURTH, DIMINISHED_FIFTH.getInversion());
        assertEquals(PERFECT_FOURTH, PERFECT_FIFTH.getInversion());
        assertEquals(DIMINISHED_FOURTH, AUGMENTED_FIFTH.getInversion());
        assertEquals(AUGMENTED_THIRD, DIMINISHED_SIXTH.getInversion());
        assertEquals(MAJOR_THIRD, MINOR_SIXTH.getInversion());
        assertEquals(MINOR_THIRD, MAJOR_SIXTH.getInversion());
        assertEquals(DIMINISHED_THIRD, AUGMENTED_SIXTH.getInversion());
        assertEquals(AUGMENTED_SECOND, DIMINISHED_SEVENTH.getInversion());
        assertEquals(MAJOR_SECOND, MINOR_SEVENTH.getInversion());
        assertEquals(MINOR_SECOND, MAJOR_SEVENTH.getInversion());
        assertEquals(DIMINISHED_SECOND, AUGMENTED_SEVENTH.getInversion());

        // Arbitrary check on dynamic Intervals
        assertEquals(PERFECT_UNISON, Interval.withShortName("P8").getInversion());
        assertEquals("d15", Interval.withShortName("A8").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A14", Interval.withShortName("d9").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("M14", Interval.withShortName("m9").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("m14", Interval.withShortName("M9").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d14", Interval.withShortName("aug.9").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A13", Interval.withShortName("D10").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("M13", Interval.withShortName("m. 10").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("m13", Interval.withShortName("Major10").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d13", Interval.withShortName("aug 10").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A12", Interval.withShortName("d11").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("P12", Interval.withShortName("Perfect 11").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d12", Interval.withShortName("augmented 11").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A11", Interval.withShortName("dim. 12").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("P11", Interval.withShortName("perf. 12").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d11", Interval.withShortName("Aug.12").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A10", Interval.withShortName("d.13").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("M10", Interval.withShortName("Min13").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("m10", Interval.withShortName("Major 13").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d10", Interval.withShortName("Augmented13").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A9", Interval.withShortName("D 14").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("M9", Interval.withShortName("Minor 14").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("m9", Interval.withShortName("maj14").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("d9", Interval.withShortName("a14").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("A22", Interval.withShortName("d.15").getInversion().COMPOUND_SHORT_NAME);
        assertEquals("P22", Interval.withShortName("Perfect 15").getInversion().COMPOUND_SHORT_NAME);
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
        assertEquals("d8", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_FLAT, true).COMPOUND_SHORT_NAME);
        assertEquals("d8", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).COMPOUND_SHORT_NAME);
        assertEquals("P8", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).COMPOUND_SHORT_NAME);
        assertEquals("P8", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).COMPOUND_SHORT_NAME);
    }
}