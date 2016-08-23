package chordinnate.musictheory.pitch.interval;

import chordinnate.musictheory.pitch.PitchClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static chordinnate.musictheory.pitch.interval.Interval.*;
import static org.junit.Assert.*;
import static chordinnate.musictheory.pitch.interval.IntervalQuality.*;

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
        assertEquals(12, new Interval("P8").NUM_SEMITONES);
        assertEquals(12, new Interval("d9").NUM_SEMITONES);
        assertEquals(13, new Interval("m9").NUM_SEMITONES);
        assertEquals(13, new Interval("A8").NUM_SEMITONES);
        assertEquals(14, new Interval("M9").NUM_SEMITONES);
        assertEquals(14, new Interval("D10").NUM_SEMITONES);
        assertEquals(15, new Interval("m. 10").NUM_SEMITONES);
        assertEquals(15, new Interval("aug.9").NUM_SEMITONES);
        assertEquals(16, new Interval("Major10").NUM_SEMITONES);
        assertEquals(16, new Interval("d11").NUM_SEMITONES);
        assertEquals(17, new Interval("Perfect 11").NUM_SEMITONES);
        assertEquals(17, new Interval("aug 10").NUM_SEMITONES);
        assertEquals(18, new Interval("dim. 12").NUM_SEMITONES);
        assertEquals(18, new Interval("augmented 11").NUM_SEMITONES);
        assertEquals(19, new Interval("perf. 12").NUM_SEMITONES);
        assertEquals(19, new Interval("d.13").NUM_SEMITONES);
        assertEquals(20, new Interval("Min13").NUM_SEMITONES);
        assertEquals(20, new Interval("Aug.12").NUM_SEMITONES);
        assertEquals(21, new Interval("Major 13").NUM_SEMITONES);
        assertEquals(21, new Interval("D 14").NUM_SEMITONES);
        assertEquals(22, new Interval("Minor 14").NUM_SEMITONES);
        assertEquals(22, new Interval("Augmented13").NUM_SEMITONES);
        assertEquals(23, new Interval("maj14").NUM_SEMITONES);
        assertEquals(23, new Interval("d.15").NUM_SEMITONES);
        assertEquals(24, new Interval("Perfect 15").NUM_SEMITONES);
        assertEquals(24, new Interval("a14").NUM_SEMITONES);
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
        assertEquals(8, new Interval("P8").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, new Interval("d9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, new Interval("m9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, new Interval("A8").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, new Interval("M9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, new Interval("D10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, new Interval("m. 10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(9, new Interval("aug.9").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, new Interval("Major10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, new Interval("d11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, new Interval("Perfect 11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(10, new Interval("aug 10").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, new Interval("dim. 12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(11, new Interval("augmented 11").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, new Interval("perf. 12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, new Interval("d.13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, new Interval("Min13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(12, new Interval("Aug.12").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, new Interval("Major 13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, new Interval("D 14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, new Interval("Minor 14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(13, new Interval("Augmented13").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, new Interval("maj14").COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, new Interval("d.15").COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, new Interval("Perfect 15").COMPOUND_DIATONIC_NUMBER);
        assertEquals(14, new Interval("a14").COMPOUND_DIATONIC_NUMBER);
    }

    @Test
    public void testField_INTERVAL_QUALITY() throws Exception {
        assertEquals(PERFECT, PERFECT_UNISON.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_SECOND.INTERVAL_QUALITY);
        assertEquals(MINOR, MINOR_SECOND.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_UNISON.INTERVAL_QUALITY);
        assertEquals(MAJOR, MAJOR_SECOND.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_THIRD.INTERVAL_QUALITY);
        assertEquals(MINOR, MINOR_THIRD.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_SECOND.INTERVAL_QUALITY);
        assertEquals(MAJOR, MAJOR_THIRD.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_FOURTH.INTERVAL_QUALITY);
        assertEquals(PERFECT, PERFECT_FOURTH.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_THIRD.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_FIFTH.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_FOURTH.INTERVAL_QUALITY);
        assertEquals(PERFECT, PERFECT_FIFTH.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_SIXTH.INTERVAL_QUALITY);
        assertEquals(MINOR, MINOR_SIXTH.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_FIFTH.INTERVAL_QUALITY);
        assertEquals(MAJOR, MAJOR_SIXTH.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_SEVENTH.INTERVAL_QUALITY);
        assertEquals(MINOR, MINOR_SEVENTH.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_SIXTH.INTERVAL_QUALITY);
        assertEquals(MAJOR, MAJOR_SEVENTH.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, DIMINISHED_OCTAVE.INTERVAL_QUALITY);
        assertEquals(PERFECT, PERFECT_OCTAVE.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, AUGMENTED_SEVENTH.INTERVAL_QUALITY);

        // Arbitrary check on dynamic Intervals
        assertEquals(PERFECT, new Interval("P8").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("d9").INTERVAL_QUALITY);
        assertEquals(MINOR, new Interval("m9").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("A8").INTERVAL_QUALITY);
        assertEquals(MAJOR, new Interval("M9").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("D10").INTERVAL_QUALITY);
        assertEquals(MINOR, new Interval("m. 10").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("aug.9").INTERVAL_QUALITY);
        assertEquals(MAJOR, new Interval("Major10").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("d11").INTERVAL_QUALITY);
        assertEquals(PERFECT, new Interval("Perfect 11").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("aug 10").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("dim. 12").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("augmented 11").INTERVAL_QUALITY);
        assertEquals(PERFECT, new Interval("perf. 12").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("d.13").INTERVAL_QUALITY);
        assertEquals(MINOR, new Interval("Min13").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("Aug.12").INTERVAL_QUALITY);
        assertEquals(MAJOR, new Interval("Major 13").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("D 14").INTERVAL_QUALITY);
        assertEquals(MINOR, new Interval("Minor 14").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("Augmented13").INTERVAL_QUALITY);
        assertEquals(MAJOR, new Interval("maj14").INTERVAL_QUALITY);
        assertEquals(DIMINISHED, new Interval("d.15").INTERVAL_QUALITY);
        assertEquals(PERFECT, new Interval("Perfect 15").INTERVAL_QUALITY);
        assertEquals(AUGMENTED, new Interval("a14").INTERVAL_QUALITY);
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
        assertEquals("P1", new Interval("P8").SIMPLE_SHORT_NAME);
        assertEquals("A1", new Interval("A8").SIMPLE_SHORT_NAME);
        assertEquals("d2", new Interval("d9").SIMPLE_SHORT_NAME);
        assertEquals("m2", new Interval("m9").SIMPLE_SHORT_NAME);
        assertEquals("M2", new Interval("M9").SIMPLE_SHORT_NAME);
        assertEquals("A2", new Interval("aug.9").SIMPLE_SHORT_NAME);
        assertEquals("d3", new Interval("D10").SIMPLE_SHORT_NAME);
        assertEquals("m3", new Interval("m. 10").SIMPLE_SHORT_NAME);
        assertEquals("M3", new Interval("Major10").SIMPLE_SHORT_NAME);
        assertEquals("A3", new Interval("aug 10").SIMPLE_SHORT_NAME);
        assertEquals("d4", new Interval("d11").SIMPLE_SHORT_NAME);
        assertEquals("P4", new Interval("Perfect 11").SIMPLE_SHORT_NAME);
        assertEquals("A4", new Interval("augmented 11").SIMPLE_SHORT_NAME);
        assertEquals("d5", new Interval("dim. 12").SIMPLE_SHORT_NAME);
        assertEquals("P5", new Interval("perf. 12").SIMPLE_SHORT_NAME);
        assertEquals("A5", new Interval("Aug.12").SIMPLE_SHORT_NAME);
        assertEquals("d6", new Interval("d.13").SIMPLE_SHORT_NAME);
        assertEquals("m6", new Interval("Min13").SIMPLE_SHORT_NAME);
        assertEquals("M6", new Interval("Major 13").SIMPLE_SHORT_NAME);
        assertEquals("A6", new Interval("Augmented13").SIMPLE_SHORT_NAME);
        assertEquals("d7", new Interval("D 14").SIMPLE_SHORT_NAME);
        assertEquals("m7", new Interval("Minor 14").SIMPLE_SHORT_NAME);
        assertEquals("M7", new Interval("maj14").SIMPLE_SHORT_NAME);
        assertEquals("A7", new Interval("a14").SIMPLE_SHORT_NAME);
        assertEquals("d1", new Interval("d.15").SIMPLE_SHORT_NAME);
        assertEquals("P1", new Interval("Perfect 15").SIMPLE_SHORT_NAME);
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
        assertEquals("P8", new Interval("P8").COMPOUND_SHORT_NAME);
        assertEquals("A8", new Interval("A8").COMPOUND_SHORT_NAME);
        assertEquals("d9", new Interval("d9").COMPOUND_SHORT_NAME);
        assertEquals("m9", new Interval("m9").COMPOUND_SHORT_NAME);
        assertEquals("M9", new Interval("M9").COMPOUND_SHORT_NAME);
        assertEquals("A9", new Interval("aug.9").COMPOUND_SHORT_NAME);
        assertEquals("d10", new Interval("D10").COMPOUND_SHORT_NAME);
        assertEquals("m10", new Interval("m. 10").COMPOUND_SHORT_NAME);
        assertEquals("M10", new Interval("Major10").COMPOUND_SHORT_NAME);
        assertEquals("A10", new Interval("aug 10").COMPOUND_SHORT_NAME);
        assertEquals("d11", new Interval("d11").COMPOUND_SHORT_NAME);
        assertEquals("P11", new Interval("Perfect 11").COMPOUND_SHORT_NAME);
        assertEquals("A11", new Interval("augmented 11").COMPOUND_SHORT_NAME);
        assertEquals("d12", new Interval("dim. 12").COMPOUND_SHORT_NAME);
        assertEquals("P12", new Interval("perf. 12").COMPOUND_SHORT_NAME);
        assertEquals("A12", new Interval("Aug.12").COMPOUND_SHORT_NAME);
        assertEquals("d13", new Interval("d.13").COMPOUND_SHORT_NAME);
        assertEquals("m13", new Interval("Min13").COMPOUND_SHORT_NAME);
        assertEquals("M13", new Interval("Major 13").COMPOUND_SHORT_NAME);
        assertEquals("A13", new Interval("Augmented13").COMPOUND_SHORT_NAME);
        assertEquals("d14", new Interval("D 14").COMPOUND_SHORT_NAME);
        assertEquals("m14", new Interval("Minor 14").COMPOUND_SHORT_NAME);
        assertEquals("M14", new Interval("maj14").COMPOUND_SHORT_NAME);
        assertEquals("A14", new Interval("a14").COMPOUND_SHORT_NAME);
        assertEquals("d15", new Interval("d.15").COMPOUND_SHORT_NAME);
        assertEquals("P15", new Interval("Perfect 15").COMPOUND_SHORT_NAME);
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
        assertEquals("I", new Interval("P8").ROMAN_NUMERAL_NAME);
        assertEquals("I+", new Interval("A8").ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", new Interval("d9").ROMAN_NUMERAL_NAME);
        assertEquals("ii", new Interval("m9").ROMAN_NUMERAL_NAME);
        assertEquals("II", new Interval("M9").ROMAN_NUMERAL_NAME);
        assertEquals("II+", new Interval("aug.9").ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", new Interval("D10").ROMAN_NUMERAL_NAME);
        assertEquals("iii", new Interval("m. 10").ROMAN_NUMERAL_NAME);
        assertEquals("III", new Interval("Major10").ROMAN_NUMERAL_NAME);
        assertEquals("III+", new Interval("aug 10").ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", new Interval("d11").ROMAN_NUMERAL_NAME);
        assertEquals("IV", new Interval("Perfect 11").ROMAN_NUMERAL_NAME);
        assertEquals("IV+", new Interval("augmented 11").ROMAN_NUMERAL_NAME);
        assertEquals("v˚", new Interval("dim. 12").ROMAN_NUMERAL_NAME);
        assertEquals("V", new Interval("perf. 12").ROMAN_NUMERAL_NAME);
        assertEquals("V+", new Interval("Aug.12").ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", new Interval("d.13").ROMAN_NUMERAL_NAME);
        assertEquals("vi", new Interval("Min13").ROMAN_NUMERAL_NAME);
        assertEquals("VI", new Interval("Major 13").ROMAN_NUMERAL_NAME);
        assertEquals("VI+", new Interval("Augmented13").ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", new Interval("D 14").ROMAN_NUMERAL_NAME);
        assertEquals("vii", new Interval("Minor 14").ROMAN_NUMERAL_NAME);
        assertEquals("VII", new Interval("maj14").ROMAN_NUMERAL_NAME);
        assertEquals("VII+", new Interval("a14").ROMAN_NUMERAL_NAME);
        assertEquals("i˚", new Interval("d.15").ROMAN_NUMERAL_NAME);
        assertEquals("I", new Interval("Perfect 15").ROMAN_NUMERAL_NAME);
    }

    @Test
    public void getInversion() throws Exception {
        // edge ase: P8 <-> P1 <-> P8 <-> P15
        assertEquals(PERFECT_OCTAVE, PERFECT_UNISON.getInversion());
        assertEquals(DIMINISHED_OCTAVE, AUGMENTED_UNISON.getInversion());
        assertEquals(PERFECT_UNISON, PERFECT_OCTAVE.getInversion());
        assertEquals(1, new Interval("P8").getInversion().COMPOUND_DIATONIC_NUMBER);
//
//        // edge case: P8 <-> P15 <-> P22
//        assertEquals(8, new Interval("P15").getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(22, new Interval("P15").getInversion().COMPOUND_DIATONIC_NUMBER);
//
//        assertEquals(2, MAJOR_SEVENTH.getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(9, MAJOR_SEVENTH.getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(2, MAJOR_SEVENTH.getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(9, MAJOR_SEVENTH.getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(7, new Interval("m9").getInversion().COMPOUND_DIATONIC_NUMBER);
//        assertEquals(MAJOR_SEVENTH, new Interval("m9").getInversion());
//        assertEquals(23, new Interval("m9").getInversion().NUM_SEMITONES);
//        assertEquals("M14", new Interval("m9").getInversion().COMPOUND_SHORT_NAME);
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