package chordinnate.musictheory.pitch.interval;

import chordinnate.musictheory.pitch.PitchClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.pitch.interval.PitchIntervalQuality.*;
import static chordinnate.musictheory.pitch.interval.notation.RomanNumeral.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestPitchInterval {
    @Test
    public void getNumSemitones() throws Exception {
        assertEquals(0, PitchInterval.PERFECT_UNISON.NUM_SEMITONES);
        assertEquals(0, PitchInterval.DIMINISHED_SECOND.NUM_SEMITONES);
        assertEquals(1, PitchInterval.MINOR_SECOND.NUM_SEMITONES);
        assertEquals(1, PitchInterval.AUGMENTED_UNISON.NUM_SEMITONES);
        assertEquals(2, PitchInterval.MAJOR_SECOND.NUM_SEMITONES);
        assertEquals(2, PitchInterval.DIMINISHED_THIRD.NUM_SEMITONES);
        assertEquals(3, PitchInterval.MINOR_THIRD.NUM_SEMITONES);
        assertEquals(3, PitchInterval.AUGMENTED_SECOND.NUM_SEMITONES);
        assertEquals(4, PitchInterval.MAJOR_THIRD.NUM_SEMITONES);
        assertEquals(4, PitchInterval.DIMINISHED_FOURTH.NUM_SEMITONES);
        assertEquals(5, PitchInterval.PERFECT_FOURTH.NUM_SEMITONES);
        assertEquals(5, PitchInterval.AUGMENTED_THIRD.NUM_SEMITONES);
        assertEquals(6, PitchInterval.DIMINISHED_FIFTH.NUM_SEMITONES);
        assertEquals(6, PitchInterval.AUGMENTED_FOURTH.NUM_SEMITONES);
        assertEquals(7, PitchInterval.PERFECT_FIFTH.NUM_SEMITONES);
        assertEquals(7, PitchInterval.DIMINISHED_SIXTH.NUM_SEMITONES);
        assertEquals(8, PitchInterval.MINOR_SIXTH.NUM_SEMITONES);
        assertEquals(8, PitchInterval.AUGMENTED_FIFTH.NUM_SEMITONES);
        assertEquals(9, PitchInterval.MAJOR_SIXTH.NUM_SEMITONES);
        assertEquals(9, PitchInterval.DIMINISHED_SEVENTH.NUM_SEMITONES);
        assertEquals(10, PitchInterval.MINOR_SEVENTH.NUM_SEMITONES);
        assertEquals(10, PitchInterval.AUGMENTED_SIXTH.NUM_SEMITONES);
        assertEquals(11, PitchInterval.MAJOR_SEVENTH.NUM_SEMITONES);
        assertEquals(11, PitchInterval.DIMINISHED_OCTAVE.NUM_SEMITONES);
        assertEquals(12, PitchInterval.PERFECT_OCTAVE.NUM_SEMITONES);
        assertEquals(12, PitchInterval.AUGMENTED_SEVENTH.NUM_SEMITONES);
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(1, PitchInterval.PERFECT_UNISON.NUMBER);
        assertEquals(2, PitchInterval.DIMINISHED_SECOND.NUMBER);
        assertEquals(2, PitchInterval.MINOR_SECOND.NUMBER);
        assertEquals(1, PitchInterval.AUGMENTED_UNISON.NUMBER);
        assertEquals(2, PitchInterval.MAJOR_SECOND.NUMBER);
        assertEquals(3, PitchInterval.DIMINISHED_THIRD.NUMBER);
        assertEquals(3, PitchInterval.MINOR_THIRD.NUMBER);
        assertEquals(2, PitchInterval.AUGMENTED_SECOND.NUMBER);
        assertEquals(3, PitchInterval.MAJOR_THIRD.NUMBER);
        assertEquals(4, PitchInterval.DIMINISHED_FOURTH.NUMBER);
        assertEquals(4, PitchInterval.PERFECT_FOURTH.NUMBER);
        assertEquals(3, PitchInterval.AUGMENTED_THIRD.NUMBER);
        assertEquals(5, PitchInterval.DIMINISHED_FIFTH.NUMBER);
        assertEquals(4, PitchInterval.AUGMENTED_FOURTH.NUMBER);
        assertEquals(5, PitchInterval.PERFECT_FIFTH.NUMBER);
        assertEquals(6, PitchInterval.DIMINISHED_SIXTH.NUMBER);
        assertEquals(6, PitchInterval.MINOR_SIXTH.NUMBER);
        assertEquals(5, PitchInterval.AUGMENTED_FIFTH.NUMBER);
        assertEquals(6, PitchInterval.MAJOR_SIXTH.NUMBER);
        assertEquals(7, PitchInterval.DIMINISHED_SEVENTH.NUMBER);
        assertEquals(7, PitchInterval.MINOR_SEVENTH.NUMBER);
        assertEquals(6, PitchInterval.AUGMENTED_SIXTH.NUMBER);
        assertEquals(7, PitchInterval.MAJOR_SEVENTH.NUMBER);
        assertEquals(8, PitchInterval.DIMINISHED_OCTAVE.NUMBER);
        assertEquals(8, PitchInterval.PERFECT_OCTAVE.NUMBER);
        assertEquals(7, PitchInterval.AUGMENTED_SEVENTH.NUMBER);
    }

    @Test
    public void getPitchIntervalQuality() throws Exception {
        assertEquals(PERFECT, PitchInterval.PERFECT_UNISON.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SECOND.PITCH_INTERVAL_QUALITY);
        assertEquals(MINOR, PitchInterval.MINOR_SECOND.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_UNISON.PITCH_INTERVAL_QUALITY);
        assertEquals(MAJOR, PitchInterval.MAJOR_SECOND.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_THIRD.PITCH_INTERVAL_QUALITY);
        assertEquals(MINOR, PitchInterval.MINOR_THIRD.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SECOND.PITCH_INTERVAL_QUALITY);
        assertEquals(MAJOR, PitchInterval.MAJOR_THIRD.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_FOURTH.PITCH_INTERVAL_QUALITY);
        assertEquals(PERFECT, PitchInterval.PERFECT_FOURTH.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_THIRD.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_FIFTH.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_FOURTH.PITCH_INTERVAL_QUALITY);
        assertEquals(PERFECT, PitchInterval.PERFECT_FIFTH.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SIXTH.PITCH_INTERVAL_QUALITY);
        assertEquals(MINOR, PitchInterval.MINOR_SIXTH.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_FIFTH.PITCH_INTERVAL_QUALITY);
        assertEquals(MAJOR, PitchInterval.MAJOR_SIXTH.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SEVENTH.PITCH_INTERVAL_QUALITY);
        assertEquals(MINOR, PitchInterval.MINOR_SEVENTH.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SIXTH.PITCH_INTERVAL_QUALITY);
        assertEquals(MAJOR, PitchInterval.MAJOR_SEVENTH.PITCH_INTERVAL_QUALITY);
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_OCTAVE.PITCH_INTERVAL_QUALITY);
        assertEquals(PERFECT, PitchInterval.PERFECT_OCTAVE.PITCH_INTERVAL_QUALITY);
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SEVENTH.PITCH_INTERVAL_QUALITY);
    }

    @Test
    public void getRomanNumeral() throws Exception {
        assertEquals(I, PitchInterval.PERFECT_UNISON.ROMAN_NUMERAL);
        assertEquals(ii, PitchInterval.DIMINISHED_SECOND.ROMAN_NUMERAL);
        assertEquals(ii, PitchInterval.MINOR_SECOND.ROMAN_NUMERAL);
        assertEquals(I, PitchInterval.AUGMENTED_UNISON.ROMAN_NUMERAL);
        assertEquals(II, PitchInterval.MAJOR_SECOND.ROMAN_NUMERAL);
        assertEquals(iii, PitchInterval.DIMINISHED_THIRD.ROMAN_NUMERAL);
        assertEquals(iii, PitchInterval.MINOR_THIRD.ROMAN_NUMERAL);
        assertEquals(II, PitchInterval.AUGMENTED_SECOND.ROMAN_NUMERAL);
        assertEquals(III, PitchInterval.MAJOR_THIRD.ROMAN_NUMERAL);
        assertEquals(iv, PitchInterval.DIMINISHED_FOURTH.ROMAN_NUMERAL);
        assertEquals(IV, PitchInterval.PERFECT_FOURTH.ROMAN_NUMERAL);
        assertEquals(III, PitchInterval.AUGMENTED_THIRD.ROMAN_NUMERAL);
        assertEquals(v, PitchInterval.DIMINISHED_FIFTH.ROMAN_NUMERAL);
        assertEquals(IV, PitchInterval.AUGMENTED_FOURTH.ROMAN_NUMERAL);
        assertEquals(V, PitchInterval.PERFECT_FIFTH.ROMAN_NUMERAL);
        assertEquals(vi, PitchInterval.DIMINISHED_SIXTH.ROMAN_NUMERAL);
        assertEquals(vi, PitchInterval.MINOR_SIXTH.ROMAN_NUMERAL);
        assertEquals(V, PitchInterval.AUGMENTED_FIFTH.ROMAN_NUMERAL);
        assertEquals(VI, PitchInterval.MAJOR_SIXTH.ROMAN_NUMERAL);
        assertEquals(vii, PitchInterval.DIMINISHED_SEVENTH.ROMAN_NUMERAL);
        assertEquals(vii, PitchInterval.MINOR_SEVENTH.ROMAN_NUMERAL);
        assertEquals(VI, PitchInterval.AUGMENTED_SIXTH.ROMAN_NUMERAL);
        assertEquals(VII, PitchInterval.MAJOR_SEVENTH.ROMAN_NUMERAL);
        assertEquals(i, PitchInterval.DIMINISHED_OCTAVE.ROMAN_NUMERAL);
        assertEquals(I, PitchInterval.PERFECT_OCTAVE.ROMAN_NUMERAL);
        assertEquals(VII, PitchInterval.AUGMENTED_SEVENTH.ROMAN_NUMERAL);
    }

    @Test
    public void getShortName() throws Exception {
        assertEquals("P1", PitchInterval.PERFECT_UNISON.SHORT_NAME);
        assertEquals("d2", PitchInterval.DIMINISHED_SECOND.SHORT_NAME);
        assertEquals("m2", PitchInterval.MINOR_SECOND.SHORT_NAME);
        assertEquals("A1", PitchInterval.AUGMENTED_UNISON.SHORT_NAME);
        assertEquals("M2", PitchInterval.MAJOR_SECOND.SHORT_NAME);
        assertEquals("d3", PitchInterval.DIMINISHED_THIRD.SHORT_NAME);
        assertEquals("m3", PitchInterval.MINOR_THIRD.SHORT_NAME);
        assertEquals("A2", PitchInterval.AUGMENTED_SECOND.SHORT_NAME);
        assertEquals("M3", PitchInterval.MAJOR_THIRD.SHORT_NAME);
        assertEquals("d4", PitchInterval.DIMINISHED_FOURTH.SHORT_NAME);
        assertEquals("P4", PitchInterval.PERFECT_FOURTH.SHORT_NAME);
        assertEquals("A3", PitchInterval.AUGMENTED_THIRD.SHORT_NAME);
        assertEquals("d5", PitchInterval.DIMINISHED_FIFTH.SHORT_NAME);
        assertEquals("A4", PitchInterval.AUGMENTED_FOURTH.SHORT_NAME);
        assertEquals("P5", PitchInterval.PERFECT_FIFTH.SHORT_NAME);
        assertEquals("d6", PitchInterval.DIMINISHED_SIXTH.SHORT_NAME);
        assertEquals("m6", PitchInterval.MINOR_SIXTH.SHORT_NAME);
        assertEquals("A5", PitchInterval.AUGMENTED_FIFTH.SHORT_NAME);
        assertEquals("M6", PitchInterval.MAJOR_SIXTH.SHORT_NAME);
        assertEquals("d7", PitchInterval.DIMINISHED_SEVENTH.SHORT_NAME);
        assertEquals("m7", PitchInterval.MINOR_SEVENTH.SHORT_NAME);
        assertEquals("A6", PitchInterval.AUGMENTED_SIXTH.SHORT_NAME);
        assertEquals("M7", PitchInterval.MAJOR_SEVENTH.SHORT_NAME);
        assertEquals("d8", PitchInterval.DIMINISHED_OCTAVE.SHORT_NAME);
        assertEquals("P8", PitchInterval.PERFECT_OCTAVE.SHORT_NAME);
        assertEquals("A7", PitchInterval.AUGMENTED_SEVENTH.SHORT_NAME);
    }

    @Test
    public void getRomanNumeralName() throws Exception {
        assertEquals("I", PitchInterval.PERFECT_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("ii˚", PitchInterval.DIMINISHED_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("ii", PitchInterval.MINOR_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("I+", PitchInterval.AUGMENTED_UNISON.ROMAN_NUMERAL_NAME);
        assertEquals("II", PitchInterval.MAJOR_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("iii˚", PitchInterval.DIMINISHED_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("iii", PitchInterval.MINOR_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("II+", PitchInterval.AUGMENTED_SECOND.ROMAN_NUMERAL_NAME);
        assertEquals("III", PitchInterval.MAJOR_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("iv˚", PitchInterval.DIMINISHED_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("IV", PitchInterval.PERFECT_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("III+", PitchInterval.AUGMENTED_THIRD.ROMAN_NUMERAL_NAME);
        assertEquals("v˚", PitchInterval.DIMINISHED_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("IV+", PitchInterval.AUGMENTED_FOURTH.ROMAN_NUMERAL_NAME);
        assertEquals("V", PitchInterval.PERFECT_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("vi˚", PitchInterval.DIMINISHED_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("vi", PitchInterval.MINOR_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("V+", PitchInterval.AUGMENTED_FIFTH.ROMAN_NUMERAL_NAME);
        assertEquals("VI", PitchInterval.MAJOR_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("vii˚", PitchInterval.DIMINISHED_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("vii", PitchInterval.MINOR_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("VI+", PitchInterval.AUGMENTED_SIXTH.ROMAN_NUMERAL_NAME);
        assertEquals("VII", PitchInterval.MAJOR_SEVENTH.ROMAN_NUMERAL_NAME);
        assertEquals("i˚", PitchInterval.DIMINISHED_OCTAVE.ROMAN_NUMERAL_NAME);
        assertEquals("I", PitchInterval.PERFECT_OCTAVE.ROMAN_NUMERAL_NAME);
        assertEquals("VII+", PitchInterval.AUGMENTED_SEVENTH.ROMAN_NUMERAL_NAME);
    }

    @Test
    public void isEnharmonicTo() throws Exception {
        assertTrue(PitchInterval.PERFECT_UNISON.isEnharmonicTo(PitchInterval.DIMINISHED_SECOND));
        assertTrue(PitchInterval.MINOR_SECOND.isEnharmonicTo(PitchInterval.AUGMENTED_UNISON));
        assertTrue(PitchInterval.MAJOR_SECOND.isEnharmonicTo(PitchInterval.DIMINISHED_THIRD));
        assertTrue(PitchInterval.MINOR_THIRD.isEnharmonicTo(PitchInterval.AUGMENTED_SECOND));
        assertTrue(PitchInterval.MAJOR_THIRD.isEnharmonicTo(PitchInterval.DIMINISHED_FOURTH));
        assertTrue(PitchInterval.PERFECT_FOURTH.isEnharmonicTo(PitchInterval.AUGMENTED_THIRD));
        assertTrue(PitchInterval.DIMINISHED_FIFTH.isEnharmonicTo(PitchInterval.AUGMENTED_FOURTH));
        assertTrue(PitchInterval.PERFECT_FIFTH.isEnharmonicTo(PitchInterval.DIMINISHED_SIXTH));
        assertTrue(PitchInterval.MINOR_SIXTH.isEnharmonicTo(PitchInterval.AUGMENTED_FIFTH));
        assertTrue(PitchInterval.MAJOR_SIXTH.isEnharmonicTo(PitchInterval.DIMINISHED_SEVENTH));
        assertTrue(PitchInterval.MINOR_SEVENTH.isEnharmonicTo(PitchInterval.AUGMENTED_SIXTH));
        assertTrue(PitchInterval.MAJOR_SEVENTH.isEnharmonicTo(PitchInterval.DIMINISHED_OCTAVE));
        assertTrue(PitchInterval.PERFECT_OCTAVE.isEnharmonicTo(PitchInterval.AUGMENTED_SEVENTH));
    }

    @Test
    public void getEnharmonics() throws Exception {

        // EDGE CASE: {P1, d2, P8, A7}
        PitchInterval[]
                expected =
                new PitchInterval[] {
                        PitchInterval.PERFECT_UNISON,
                        PitchInterval.DIMINISHED_SECOND,
                        PitchInterval.PERFECT_OCTAVE,
                        PitchInterval.AUGMENTED_SEVENTH
                },
                actual = PitchInterval.PERFECT_UNISON.getEnharmonics();

        // All items should match
        assertArrayEquals(expected, actual);

        // BASIC CASE: {m2, A1}
        expected =
                new PitchInterval[] {
                        PitchInterval.MINOR_SECOND,
                        PitchInterval.AUGMENTED_UNISON
                };
        // Both m2 and A1 should return matching arrays for this expected array.
        actual = PitchInterval.MINOR_SECOND.getEnharmonics();
        assertArrayEquals(expected, actual);

        actual = PitchInterval.AUGMENTED_UNISON.getEnharmonics();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getInversion() throws Exception {
        assertEquals(PitchInterval.PERFECT_OCTAVE, PitchInterval.PERFECT_UNISON.getInversion());
        assertEquals(PitchInterval.AUGMENTED_SEVENTH, PitchInterval.DIMINISHED_SECOND.getInversion());
        assertEquals(PitchInterval.MAJOR_SEVENTH, PitchInterval.MINOR_SECOND.getInversion());
        assertEquals(PitchInterval.DIMINISHED_OCTAVE, PitchInterval.AUGMENTED_UNISON.getInversion());
        assertEquals(PitchInterval.MINOR_SEVENTH, PitchInterval.MAJOR_SECOND.getInversion());
        assertEquals(PitchInterval.AUGMENTED_SIXTH, PitchInterval.DIMINISHED_THIRD.getInversion());
        assertEquals(PitchInterval.MAJOR_SIXTH, PitchInterval.MINOR_THIRD.getInversion());
        assertEquals(PitchInterval.DIMINISHED_SEVENTH, PitchInterval.AUGMENTED_SECOND.getInversion());
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.MAJOR_THIRD.getInversion());
        assertEquals(PitchInterval.AUGMENTED_FIFTH, PitchInterval.DIMINISHED_FOURTH.getInversion());
        assertEquals(PitchInterval.PERFECT_FIFTH, PitchInterval.PERFECT_FOURTH.getInversion());
        assertEquals(PitchInterval.DIMINISHED_SIXTH, PitchInterval.AUGMENTED_THIRD.getInversion());
        assertEquals(PitchInterval.AUGMENTED_FOURTH, PitchInterval.DIMINISHED_FIFTH.getInversion());
        assertEquals(PitchInterval.DIMINISHED_FIFTH, PitchInterval.AUGMENTED_FOURTH.getInversion());
        assertEquals(PitchInterval.PERFECT_FOURTH, PitchInterval.PERFECT_FIFTH.getInversion());
        assertEquals(PitchInterval.AUGMENTED_THIRD, PitchInterval.DIMINISHED_SIXTH.getInversion());
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.MINOR_SIXTH.getInversion());
        assertEquals(PitchInterval.DIMINISHED_FOURTH, PitchInterval.AUGMENTED_FIFTH.getInversion());
        assertEquals(PitchInterval.MINOR_THIRD, PitchInterval.MAJOR_SIXTH.getInversion());
        assertEquals(PitchInterval.AUGMENTED_SECOND, PitchInterval.DIMINISHED_SEVENTH.getInversion());
        assertEquals(PitchInterval.MAJOR_SECOND, PitchInterval.MINOR_SEVENTH.getInversion());
        assertEquals(PitchInterval.DIMINISHED_THIRD, PitchInterval.AUGMENTED_SIXTH.getInversion());
        assertEquals(PitchInterval.MINOR_SECOND, PitchInterval.MAJOR_SEVENTH.getInversion());
        assertEquals(PitchInterval.AUGMENTED_UNISON, PitchInterval.DIMINISHED_OCTAVE.getInversion());
        assertEquals(PitchInterval.PERFECT_UNISON, PitchInterval.PERFECT_OCTAVE.getInversion());
        assertEquals(PitchInterval.DIMINISHED_SECOND, PitchInterval.AUGMENTED_SEVENTH.getInversion());
    }

    @Test
    public void getPitchIntervalBetween() throws Exception {
        assertEquals(PitchInterval.PERFECT_OCTAVE, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.C, true));
        assertEquals(PitchInterval.PERFECT_OCTAVE, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.C, false));

        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.E, true));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.E, false));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.E, PitchClass.C, true));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.E, PitchClass.C, false));

        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.C_SHARP, PitchClass.E_SHARP, true));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.C_SHARP, PitchClass.E_SHARP, false));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.E_SHARP, PitchClass.C_SHARP, true));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.E_SHARP, PitchClass.C_SHARP, false));

        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.B_FLAT, true));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.B_FLAT, false));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.B_FLAT, PitchClass.G_FLAT, true));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.B_FLAT, PitchClass.G_FLAT, false));

        assertEquals(PitchInterval.PERFECT_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_FLAT, true));
        assertEquals(PitchInterval.PERFECT_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_FLAT, false));
        assertEquals(PitchInterval.PERFECT_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.C_FLAT, PitchClass.G_FLAT, true));
        assertEquals(PitchInterval.PERFECT_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.C_FLAT, PitchClass.G_FLAT, false));

        assertEquals(PitchInterval.AUGMENTED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C, true));
        assertEquals(PitchInterval.DIMINISHED_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C, false));
        assertEquals(PitchInterval.DIMINISHED_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.G_FLAT, true));
        assertEquals(PitchInterval.AUGMENTED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.G_FLAT, false));

        assertEquals(PitchInterval.DIMINISHED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_DOUBLE_FLAT, true));
        assertEquals(PitchInterval.AUGMENTED_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_DOUBLE_FLAT, false));
        assertEquals(PitchInterval.AUGMENTED_FIFTH, PitchInterval.getPitchIntervalBetween(PitchClass.C_DOUBLE_FLAT, PitchClass.G_FLAT, true));
        assertEquals(PitchInterval.DIMINISHED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.C_DOUBLE_FLAT, PitchClass.G_FLAT, false));
    }
}