package chordinnate.musictheory.pitch.interval;

import chordinnate.musictheory.pitch.PitchClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        assertEquals(0, Interval.PERFECT_UNISON.NUM_SEMITONES);
        assertEquals(0, Interval.DIMINISHED_SECOND_UP.NUM_SEMITONES);
        assertEquals(1, Interval.MINOR_SECOND_UP.NUM_SEMITONES);
        assertEquals(1, Interval.AUGMENTED_UNISON_UP.NUM_SEMITONES);
        assertEquals(2, Interval.MAJOR_SECOND_UP.NUM_SEMITONES);
        assertEquals(2, Interval.DIMINISHED_THIRD_UP.NUM_SEMITONES);
        assertEquals(3, Interval.MINOR_THIRD_UP.NUM_SEMITONES);
        assertEquals(3, Interval.AUGMENTED_SECOND_UP.NUM_SEMITONES);
        assertEquals(4, Interval.MAJOR_THIRD_UP.NUM_SEMITONES);
        assertEquals(4, Interval.DIMINISHED_FOURTH_UP.NUM_SEMITONES);
        assertEquals(5, Interval.PERFECT_FOURTH_UP.NUM_SEMITONES);
        assertEquals(5, Interval.AUGMENTED_THIRD_UP.NUM_SEMITONES);
        assertEquals(6, Interval.DIMINISHED_FIFTH_UP.NUM_SEMITONES);
        assertEquals(6, Interval.AUGMENTED_FOURTH_UP.NUM_SEMITONES);
        assertEquals(7, Interval.PERFECT_FIFTH_UP.NUM_SEMITONES);
        assertEquals(7, Interval.DIMINISHED_SIXTH_UP.NUM_SEMITONES);
        assertEquals(8, Interval.MINOR_SIXTH_UP.NUM_SEMITONES);
        assertEquals(8, Interval.AUGMENTED_FIFTH_UP.NUM_SEMITONES);
        assertEquals(9, Interval.MAJOR_SIXTH_UP.NUM_SEMITONES);
        assertEquals(9, Interval.DIMINISHED_SEVENTH_UP.NUM_SEMITONES);
        assertEquals(10, Interval.MINOR_SEVENTH_UP.NUM_SEMITONES);
        assertEquals(10, Interval.AUGMENTED_SIXTH_UP.NUM_SEMITONES);
        assertEquals(11, Interval.MAJOR_SEVENTH_UP.NUM_SEMITONES);
        assertEquals(11, Interval.DIMINISHED_OCTAVE_UP.NUM_SEMITONES);
        assertEquals(12, Interval.PERFECT_OCTAVE_UP.NUM_SEMITONES);
        assertEquals(12, Interval.AUGMENTED_SEVENTH_UP.NUM_SEMITONES);
    }

    @Test
    public void testField_NUMBER() throws Exception {
        assertEquals(1, Interval.PERFECT_UNISON.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, Interval.DIMINISHED_SECOND_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, Interval.MINOR_SECOND_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, Interval.AUGMENTED_UNISON_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, Interval.MAJOR_SECOND_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, Interval.DIMINISHED_THIRD_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, Interval.MINOR_THIRD_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(2, Interval.AUGMENTED_SECOND_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, Interval.MAJOR_THIRD_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(4, Interval.DIMINISHED_FOURTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(4, Interval.PERFECT_FOURTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(3, Interval.AUGMENTED_THIRD_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(5, Interval.DIMINISHED_FIFTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(4, Interval.AUGMENTED_FOURTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(5, Interval.PERFECT_FIFTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, Interval.DIMINISHED_SIXTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, Interval.MINOR_SIXTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(5, Interval.AUGMENTED_FIFTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, Interval.MAJOR_SIXTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, Interval.DIMINISHED_SEVENTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, Interval.MINOR_SEVENTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(6, Interval.AUGMENTED_SIXTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, Interval.MAJOR_SEVENTH_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, Interval.DIMINISHED_OCTAVE_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(1, Interval.PERFECT_OCTAVE_UP.SIMPLE_DIATONIC_NUMBER);
        assertEquals(7, Interval.AUGMENTED_SEVENTH_UP.SIMPLE_DIATONIC_NUMBER);
    }

    @Test
    public void testField_INTERVAL_QUALITY() throws Exception {
        assertEquals(PERFECT, Interval.PERFECT_UNISON.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_SECOND_UP.INTERVAL_QUALITY);
        assertEquals(MINOR, Interval.MINOR_SECOND_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_UNISON_UP.INTERVAL_QUALITY);
        assertEquals(MAJOR, Interval.MAJOR_SECOND_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_THIRD_UP.INTERVAL_QUALITY);
        assertEquals(MINOR, Interval.MINOR_THIRD_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_SECOND_UP.INTERVAL_QUALITY);
        assertEquals(MAJOR, Interval.MAJOR_THIRD_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_FOURTH_UP.INTERVAL_QUALITY);
        assertEquals(PERFECT, Interval.PERFECT_FOURTH_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_THIRD_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_FIFTH_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_FOURTH_UP.INTERVAL_QUALITY);
        assertEquals(PERFECT, Interval.PERFECT_FIFTH_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_SIXTH_UP.INTERVAL_QUALITY);
        assertEquals(MINOR, Interval.MINOR_SIXTH_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_FIFTH_UP.INTERVAL_QUALITY);
        assertEquals(MAJOR, Interval.MAJOR_SIXTH_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_SEVENTH_UP.INTERVAL_QUALITY);
        assertEquals(MINOR, Interval.MINOR_SEVENTH_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_SIXTH_UP.INTERVAL_QUALITY);
        assertEquals(MAJOR, Interval.MAJOR_SEVENTH_UP.INTERVAL_QUALITY);
        assertEquals(DIMINISHED, Interval.DIMINISHED_OCTAVE_UP.INTERVAL_QUALITY);
        assertEquals(PERFECT, Interval.PERFECT_OCTAVE_UP.INTERVAL_QUALITY);
        assertEquals(AUGMENTED, Interval.AUGMENTED_SEVENTH_UP.INTERVAL_QUALITY);
    }

    @Test
    public void testField_SIMPLE_SHORT_NAME() throws Exception {
        assertEquals("P1", Interval.PERFECT_UNISON.SIMPLE_SHORT_NAME);
        assertEquals("d2", Interval.DIMINISHED_SECOND_UP.SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.MINOR_SECOND_UP.SIMPLE_SHORT_NAME);
        assertEquals("A1", Interval.AUGMENTED_UNISON_UP.SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.MAJOR_SECOND_UP.SIMPLE_SHORT_NAME);
        assertEquals("d3", Interval.DIMINISHED_THIRD_UP.SIMPLE_SHORT_NAME);
        assertEquals("m3", Interval.MINOR_THIRD_UP.SIMPLE_SHORT_NAME);
        assertEquals("A2", Interval.AUGMENTED_SECOND_UP.SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.MAJOR_THIRD_UP.SIMPLE_SHORT_NAME);
        assertEquals("d4", Interval.DIMINISHED_FOURTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.PERFECT_FOURTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("A3", Interval.AUGMENTED_THIRD_UP.SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.DIMINISHED_FIFTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.AUGMENTED_FOURTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.PERFECT_FIFTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("d6", Interval.DIMINISHED_SIXTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.MINOR_SIXTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.AUGMENTED_FIFTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("M6", Interval.MAJOR_SIXTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("d7", Interval.DIMINISHED_SEVENTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.MINOR_SEVENTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("A6", Interval.AUGMENTED_SIXTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.MAJOR_SEVENTH_UP.SIMPLE_SHORT_NAME);
        assertEquals("d1", Interval.DIMINISHED_OCTAVE_UP.SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.PERFECT_OCTAVE_UP.SIMPLE_SHORT_NAME);
        assertEquals("A7", Interval.AUGMENTED_SEVENTH_UP.SIMPLE_SHORT_NAME);
    }

    @Test
    public void testField_ROMAN_NUMERAL_NAME() throws Exception {
        assertEquals("I", Interval.PERFECT_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", Interval.DIMINISHED_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("ii", Interval.MINOR_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("I+", Interval.AUGMENTED_UNISON_UP.ROMAN_NUMERAL_NAME);
        assertEquals("II", Interval.MAJOR_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", Interval.DIMINISHED_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iii", Interval.MINOR_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("II+", Interval.AUGMENTED_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("III", Interval.MAJOR_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", Interval.DIMINISHED_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("IV", Interval.PERFECT_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("III+", Interval.AUGMENTED_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("v˚", Interval.DIMINISHED_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("IV+", Interval.AUGMENTED_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("V", Interval.PERFECT_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", Interval.DIMINISHED_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vi", Interval.MINOR_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("V+", Interval.AUGMENTED_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VI", Interval.MAJOR_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", Interval.DIMINISHED_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vii", Interval.MINOR_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VI+", Interval.AUGMENTED_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VII", Interval.MAJOR_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("i˚", Interval.DIMINISHED_OCTAVE_UP.ROMAN_NUMERAL_NAME);
        assertEquals("I", Interval.PERFECT_OCTAVE_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VII+", Interval.AUGMENTED_SEVENTH_UP.ROMAN_NUMERAL_NAME);
    }

    @Test
    public void testInterval() throws Exception {
        assertEquals("I", Interval.PERFECT_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", Interval.DIMINISHED_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("ii", Interval.MINOR_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("I+", Interval.AUGMENTED_UNISON_UP.ROMAN_NUMERAL_NAME);
        assertEquals("II", Interval.MAJOR_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", Interval.DIMINISHED_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iii", Interval.MINOR_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("II+", Interval.AUGMENTED_SECOND_UP.ROMAN_NUMERAL_NAME);
        assertEquals("III", Interval.MAJOR_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", Interval.DIMINISHED_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("IV", Interval.PERFECT_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("III+", Interval.AUGMENTED_THIRD_UP.ROMAN_NUMERAL_NAME);
        assertEquals("v˚", Interval.DIMINISHED_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("IV+", Interval.AUGMENTED_FOURTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("V", Interval.PERFECT_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", Interval.DIMINISHED_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vi", Interval.MINOR_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("V+", Interval.AUGMENTED_FIFTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VI", Interval.MAJOR_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", Interval.DIMINISHED_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("vii", Interval.MINOR_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VI+", Interval.AUGMENTED_SIXTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VII", Interval.MAJOR_SEVENTH_UP.ROMAN_NUMERAL_NAME);
        assertEquals("i˚", Interval.DIMINISHED_OCTAVE_UP.ROMAN_NUMERAL_NAME);
        assertEquals("I", Interval.PERFECT_OCTAVE_UP.ROMAN_NUMERAL_NAME);
        assertEquals("VII+", Interval.AUGMENTED_SEVENTH_UP.ROMAN_NUMERAL_NAME);

        // Arbitrary regex check on dynamic Intervals
        assertEquals("I", new Interval("P8", true).ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", new Interval("d9", true).ROMAN_NUMERAL_NAME);
        assertEquals("ii", new Interval("m9", true).ROMAN_NUMERAL_NAME);
        assertEquals("I+", new Interval("A8", true).ROMAN_NUMERAL_NAME);
        assertEquals("II", new Interval("M9", true).ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", new Interval("D10", true).ROMAN_NUMERAL_NAME);
        assertEquals("iii", new Interval("m. 10", true).ROMAN_NUMERAL_NAME);
        assertEquals("II+", new Interval("aug.9", true).ROMAN_NUMERAL_NAME);
        assertEquals("III", new Interval("Major10", true).ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", new Interval("d11", true).ROMAN_NUMERAL_NAME);
        assertEquals("IV", new Interval("Perfect 11", true).ROMAN_NUMERAL_NAME);
        assertEquals("III+", new Interval("aug 10", true).ROMAN_NUMERAL_NAME);
        assertEquals("v˚", new Interval("dim. 12", true).ROMAN_NUMERAL_NAME);
        assertEquals("IV+", new Interval("augmented 11", true).ROMAN_NUMERAL_NAME);
        assertEquals("V", new Interval("perf. 12", true).ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", new Interval("d.13", true).ROMAN_NUMERAL_NAME);
        assertEquals("vi", new Interval("Min13", true).ROMAN_NUMERAL_NAME);
        assertEquals("V+", new Interval("Aug.12", true).ROMAN_NUMERAL_NAME);
        assertEquals("VI", new Interval("Major 13", true).ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", new Interval("D 14", true).ROMAN_NUMERAL_NAME);
        assertEquals("vii", new Interval("Minor 14", true).ROMAN_NUMERAL_NAME);
        assertEquals("VI+", new Interval("Augmented13", true).ROMAN_NUMERAL_NAME);
        assertEquals("VII", new Interval("maj14", true).ROMAN_NUMERAL_NAME);
        assertEquals("i˚", new Interval("d.15", true).ROMAN_NUMERAL_NAME);
        assertEquals("I", new Interval("Perfect 15", true).ROMAN_NUMERAL_NAME);
        assertEquals("VII+", new Interval("a14", true).ROMAN_NUMERAL_NAME);
    }

    @Test
    public void getInversion() throws Exception {
        assertEquals(Interval.PERFECT_OCTAVE_UP, Interval.PERFECT_UNISON.getInversion(true));
        assertEquals(Interval.PERFECT_OCTAVE_DOWN, Interval.PERFECT_UNISON.getInversion(false));

        // edge ase: P8 <-> P1 <-> P8 <-> P15
        assertEquals(8, Interval.PERFECT_UNISON.getInversion(true).COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, Interval.PERFECT_UNISON.getInversion(false).COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, new Interval("P1", true).getInversion(true).COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, new Interval("P8", true).getInversion(false).COMPOUND_DIATONIC_NUMBER);
        assertEquals(8, Interval.PERFECT_OCTAVE_UP.getInversion(false).COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, Interval.PERFECT_OCTAVE_UP.getInversion(true).COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, new Interval("P8", true).getInversion(true).COMPOUND_DIATONIC_NUMBER);

        // edge case: P8 <-> P15 <-> P22
        assertEquals(8, new Interval("P15", false).getInversion(false).COMPOUND_DIATONIC_NUMBER);
        assertEquals(15, new Interval("P15", true).getInversion(false).COMPOUND_DIATONIC_NUMBER);
        assertEquals(22, new Interval("P15", true).getInversion(true).COMPOUND_DIATONIC_NUMBER);

        // TODO: other edge cases

    }

    @Test
    public void getIntervalBetween() throws Exception {
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).SIMPLE_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, true).COMPOUND_SHORT_NAME);
        assertEquals("P1", Interval.getIntervalBetween(PitchClass.C, PitchClass.C, false).COMPOUND_SHORT_NAME);

        assertEquals("m2", Interval.getIntervalBetween(PitchClass.A_DOUBLE_SHARP, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.A_DOUBLE_SHARP, PitchClass.C, false).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.A_DOUBLE_SHARP, false).SIMPLE_SHORT_NAME);

        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.C_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C_SHARP, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C_SHARP, PitchClass.C, false).SIMPLE_SHORT_NAME);

        assertEquals("m2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("M7", Interval.getIntervalBetween(PitchClass.D, PitchClass.D_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m2", Interval.getIntervalBetween(PitchClass.D, PitchClass.D_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("M2", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, true).SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.C, PitchClass.D, false).SIMPLE_SHORT_NAME);
        assertEquals("m7", Interval.getIntervalBetween(PitchClass.D, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("M2", Interval.getIntervalBetween(PitchClass.D, PitchClass.C, false).SIMPLE_SHORT_NAME);

        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, true).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C, PitchClass.E, false).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.E, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.E, PitchClass.C, false).SIMPLE_SHORT_NAME);

        assertEquals("M3", Interval.getIntervalBetween(PitchClass.C_SHARP, PitchClass.E_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.C_SHARP, PitchClass.E_SHARP, false).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.E_SHARP, PitchClass.C_SHARP, true).SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.E_SHARP, PitchClass.C_SHARP, false).SIMPLE_SHORT_NAME);

        assertEquals("M3", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.B_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.B_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("m6", Interval.getIntervalBetween(PitchClass.B_FLAT, PitchClass.G_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("M3", Interval.getIntervalBetween(PitchClass.B_FLAT, PitchClass.G_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("P4", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("P5", Interval.getIntervalBetween(PitchClass.C_FLAT, PitchClass.G_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("P4", Interval.getIntervalBetween(PitchClass.C_FLAT, PitchClass.G_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("A4", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C, true).SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C, false).SIMPLE_SHORT_NAME);
        assertEquals("d5", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("A4", Interval.getIntervalBetween(PitchClass.C, PitchClass.G_FLAT, false).SIMPLE_SHORT_NAME);

        assertEquals("d4", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C_DOUBLE_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.G_FLAT, PitchClass.C_DOUBLE_FLAT, false).SIMPLE_SHORT_NAME);
        assertEquals("A5", Interval.getIntervalBetween(PitchClass.C_DOUBLE_FLAT, PitchClass.G_FLAT, true).SIMPLE_SHORT_NAME);
        assertEquals("d4", Interval.getIntervalBetween(PitchClass.C_DOUBLE_FLAT, PitchClass.G_FLAT, false).SIMPLE_SHORT_NAME);
    }
}