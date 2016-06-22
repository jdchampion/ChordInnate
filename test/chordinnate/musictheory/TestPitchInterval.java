package chordinnate.musictheory;

import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.PitchIntervalQuality.*;
import static chordinnate.musictheory.RomanNumeral.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestPitchInterval {
    @Test
    public void getNumSemitones() throws Exception {
        assertEquals(0, PitchInterval.PERFECT_UNISON.getNumSemitones());
        assertEquals(0, PitchInterval.DIMINISHED_SECOND.getNumSemitones());
        assertEquals(1, PitchInterval.MINOR_SECOND.getNumSemitones());
        assertEquals(1, PitchInterval.AUGMENTED_UNISON.getNumSemitones());
        assertEquals(2, PitchInterval.MAJOR_SECOND.getNumSemitones());
        assertEquals(2, PitchInterval.DIMINISHED_THIRD.getNumSemitones());
        assertEquals(3, PitchInterval.MINOR_THIRD.getNumSemitones());
        assertEquals(3, PitchInterval.AUGMENTED_SECOND.getNumSemitones());
        assertEquals(4, PitchInterval.MAJOR_THIRD.getNumSemitones());
        assertEquals(4, PitchInterval.DIMINISHED_FOURTH.getNumSemitones());
        assertEquals(5, PitchInterval.PERFECT_FOURTH.getNumSemitones());
        assertEquals(5, PitchInterval.AUGMENTED_THIRD.getNumSemitones());
        assertEquals(6, PitchInterval.DIMINISHED_FIFTH.getNumSemitones());
        assertEquals(6, PitchInterval.AUGMENTED_FOURTH.getNumSemitones());
        assertEquals(7, PitchInterval.PERFECT_FIFTH.getNumSemitones());
        assertEquals(7, PitchInterval.DIMINISHED_SIXTH.getNumSemitones());
        assertEquals(8, PitchInterval.MINOR_SIXTH.getNumSemitones());
        assertEquals(8, PitchInterval.AUGMENTED_FIFTH.getNumSemitones());
        assertEquals(9, PitchInterval.MAJOR_SIXTH.getNumSemitones());
        assertEquals(9, PitchInterval.DIMINISHED_SEVENTH.getNumSemitones());
        assertEquals(10, PitchInterval.MINOR_SEVENTH.getNumSemitones());
        assertEquals(10, PitchInterval.AUGMENTED_SIXTH.getNumSemitones());
        assertEquals(11, PitchInterval.MAJOR_SEVENTH.getNumSemitones());
        assertEquals(11, PitchInterval.DIMINISHED_OCTAVE.getNumSemitones());
        assertEquals(12, PitchInterval.PERFECT_OCTAVE.getNumSemitones());
        assertEquals(12, PitchInterval.AUGMENTED_SEVENTH.getNumSemitones());
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(1, PitchInterval.PERFECT_UNISON.getNumber());
        assertEquals(2, PitchInterval.DIMINISHED_SECOND.getNumber());
        assertEquals(2, PitchInterval.MINOR_SECOND.getNumber());
        assertEquals(1, PitchInterval.AUGMENTED_UNISON.getNumber());
        assertEquals(2, PitchInterval.MAJOR_SECOND.getNumber());
        assertEquals(3, PitchInterval.DIMINISHED_THIRD.getNumber());
        assertEquals(3, PitchInterval.MINOR_THIRD.getNumber());
        assertEquals(2, PitchInterval.AUGMENTED_SECOND.getNumber());
        assertEquals(3, PitchInterval.MAJOR_THIRD.getNumber());
        assertEquals(4, PitchInterval.DIMINISHED_FOURTH.getNumber());
        assertEquals(4, PitchInterval.PERFECT_FOURTH.getNumber());
        assertEquals(3, PitchInterval.AUGMENTED_THIRD.getNumber());
        assertEquals(5, PitchInterval.DIMINISHED_FIFTH.getNumber());
        assertEquals(4, PitchInterval.AUGMENTED_FOURTH.getNumber());
        assertEquals(5, PitchInterval.PERFECT_FIFTH.getNumber());
        assertEquals(6, PitchInterval.DIMINISHED_SIXTH.getNumber());
        assertEquals(6, PitchInterval.MINOR_SIXTH.getNumber());
        assertEquals(5, PitchInterval.AUGMENTED_FIFTH.getNumber());
        assertEquals(6, PitchInterval.MAJOR_SIXTH.getNumber());
        assertEquals(7, PitchInterval.DIMINISHED_SEVENTH.getNumber());
        assertEquals(7, PitchInterval.MINOR_SEVENTH.getNumber());
        assertEquals(6, PitchInterval.AUGMENTED_SIXTH.getNumber());
        assertEquals(7, PitchInterval.MAJOR_SEVENTH.getNumber());
        assertEquals(8, PitchInterval.DIMINISHED_OCTAVE.getNumber());
        assertEquals(8, PitchInterval.PERFECT_OCTAVE.getNumber());
        assertEquals(7, PitchInterval.AUGMENTED_SEVENTH.getNumber());
    }

    @Test
    public void getPitchIntervalQuality() throws Exception {
        assertEquals(PERFECT, PitchInterval.PERFECT_UNISON.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SECOND.getPitchIntervalQuality());
        assertEquals(MINOR, PitchInterval.MINOR_SECOND.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_UNISON.getPitchIntervalQuality());
        assertEquals(MAJOR, PitchInterval.MAJOR_SECOND.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_THIRD.getPitchIntervalQuality());
        assertEquals(MINOR, PitchInterval.MINOR_THIRD.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SECOND.getPitchIntervalQuality());
        assertEquals(MAJOR, PitchInterval.MAJOR_THIRD.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_FOURTH.getPitchIntervalQuality());
        assertEquals(PERFECT, PitchInterval.PERFECT_FOURTH.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_THIRD.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_FIFTH.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_FOURTH.getPitchIntervalQuality());
        assertEquals(PERFECT, PitchInterval.PERFECT_FIFTH.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SIXTH.getPitchIntervalQuality());
        assertEquals(MINOR, PitchInterval.MINOR_SIXTH.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_FIFTH.getPitchIntervalQuality());
        assertEquals(MAJOR, PitchInterval.MAJOR_SIXTH.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_SEVENTH.getPitchIntervalQuality());
        assertEquals(MINOR, PitchInterval.MINOR_SEVENTH.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SIXTH.getPitchIntervalQuality());
        assertEquals(MAJOR, PitchInterval.MAJOR_SEVENTH.getPitchIntervalQuality());
        assertEquals(DIMINISHED, PitchInterval.DIMINISHED_OCTAVE.getPitchIntervalQuality());
        assertEquals(PERFECT, PitchInterval.PERFECT_OCTAVE.getPitchIntervalQuality());
        assertEquals(AUGMENTED, PitchInterval.AUGMENTED_SEVENTH.getPitchIntervalQuality());
    }

    @Test
    public void getRomanNumeral() throws Exception {
        assertEquals(I, PitchInterval.PERFECT_UNISON.getRomanNumeral());
        assertEquals(ii, PitchInterval.DIMINISHED_SECOND.getRomanNumeral());
        assertEquals(ii, PitchInterval.MINOR_SECOND.getRomanNumeral());
        assertEquals(I, PitchInterval.AUGMENTED_UNISON.getRomanNumeral());
        assertEquals(II, PitchInterval.MAJOR_SECOND.getRomanNumeral());
        assertEquals(iii, PitchInterval.DIMINISHED_THIRD.getRomanNumeral());
        assertEquals(iii, PitchInterval.MINOR_THIRD.getRomanNumeral());
        assertEquals(II, PitchInterval.AUGMENTED_SECOND.getRomanNumeral());
        assertEquals(III, PitchInterval.MAJOR_THIRD.getRomanNumeral());
        assertEquals(iv, PitchInterval.DIMINISHED_FOURTH.getRomanNumeral());
        assertEquals(IV, PitchInterval.PERFECT_FOURTH.getRomanNumeral());
        assertEquals(III, PitchInterval.AUGMENTED_THIRD.getRomanNumeral());
        assertEquals(v, PitchInterval.DIMINISHED_FIFTH.getRomanNumeral());
        assertEquals(IV, PitchInterval.AUGMENTED_FOURTH.getRomanNumeral());
        assertEquals(V, PitchInterval.PERFECT_FIFTH.getRomanNumeral());
        assertEquals(vi, PitchInterval.DIMINISHED_SIXTH.getRomanNumeral());
        assertEquals(vi, PitchInterval.MINOR_SIXTH.getRomanNumeral());
        assertEquals(V, PitchInterval.AUGMENTED_FIFTH.getRomanNumeral());
        assertEquals(VI, PitchInterval.MAJOR_SIXTH.getRomanNumeral());
        assertEquals(vii, PitchInterval.DIMINISHED_SEVENTH.getRomanNumeral());
        assertEquals(vii, PitchInterval.MINOR_SEVENTH.getRomanNumeral());
        assertEquals(VI, PitchInterval.AUGMENTED_SIXTH.getRomanNumeral());
        assertEquals(VII, PitchInterval.MAJOR_SEVENTH.getRomanNumeral());
        assertEquals(viii, PitchInterval.DIMINISHED_OCTAVE.getRomanNumeral());
        assertEquals(VIII, PitchInterval.PERFECT_OCTAVE.getRomanNumeral());
        assertEquals(VII, PitchInterval.AUGMENTED_SEVENTH.getRomanNumeral());
    }

    @Test
    public void getShortName() throws Exception {
        assertEquals("P1", PitchInterval.PERFECT_UNISON.getShortName());
        assertEquals("d2", PitchInterval.DIMINISHED_SECOND.getShortName());
        assertEquals("m2", PitchInterval.MINOR_SECOND.getShortName());
        assertEquals("A1", PitchInterval.AUGMENTED_UNISON.getShortName());
        assertEquals("M2", PitchInterval.MAJOR_SECOND.getShortName());
        assertEquals("d3", PitchInterval.DIMINISHED_THIRD.getShortName());
        assertEquals("m3", PitchInterval.MINOR_THIRD.getShortName());
        assertEquals("A2", PitchInterval.AUGMENTED_SECOND.getShortName());
        assertEquals("M3", PitchInterval.MAJOR_THIRD.getShortName());
        assertEquals("d4", PitchInterval.DIMINISHED_FOURTH.getShortName());
        assertEquals("P4", PitchInterval.PERFECT_FOURTH.getShortName());
        assertEquals("A3", PitchInterval.AUGMENTED_THIRD.getShortName());
        assertEquals("d5", PitchInterval.DIMINISHED_FIFTH.getShortName());
        assertEquals("A4", PitchInterval.AUGMENTED_FOURTH.getShortName());
        assertEquals("P5", PitchInterval.PERFECT_FIFTH.getShortName());
        assertEquals("d6", PitchInterval.DIMINISHED_SIXTH.getShortName());
        assertEquals("m6", PitchInterval.MINOR_SIXTH.getShortName());
        assertEquals("A5", PitchInterval.AUGMENTED_FIFTH.getShortName());
        assertEquals("M6", PitchInterval.MAJOR_SIXTH.getShortName());
        assertEquals("d7", PitchInterval.DIMINISHED_SEVENTH.getShortName());
        assertEquals("m7", PitchInterval.MINOR_SEVENTH.getShortName());
        assertEquals("A6", PitchInterval.AUGMENTED_SIXTH.getShortName());
        assertEquals("M7", PitchInterval.MAJOR_SEVENTH.getShortName());
        assertEquals("d8", PitchInterval.DIMINISHED_OCTAVE.getShortName());
        assertEquals("P8", PitchInterval.PERFECT_OCTAVE.getShortName());
        assertEquals("A7", PitchInterval.AUGMENTED_SEVENTH.getShortName());
    }

    @Test
    public void getRomanNumeralName() throws Exception {
        assertEquals("I", PitchInterval.PERFECT_UNISON.getRomanNumeralName());
        assertEquals("ii˚", PitchInterval.DIMINISHED_SECOND.getRomanNumeralName());
        assertEquals("ii", PitchInterval.MINOR_SECOND.getRomanNumeralName());
        assertEquals("I+", PitchInterval.AUGMENTED_UNISON.getRomanNumeralName());
        assertEquals("II", PitchInterval.MAJOR_SECOND.getRomanNumeralName());
        assertEquals("iii˚", PitchInterval.DIMINISHED_THIRD.getRomanNumeralName());
        assertEquals("iii", PitchInterval.MINOR_THIRD.getRomanNumeralName());
        assertEquals("II+", PitchInterval.AUGMENTED_SECOND.getRomanNumeralName());
        assertEquals("III", PitchInterval.MAJOR_THIRD.getRomanNumeralName());
        assertEquals("iv˚", PitchInterval.DIMINISHED_FOURTH.getRomanNumeralName());
        assertEquals("IV", PitchInterval.PERFECT_FOURTH.getRomanNumeralName());
        assertEquals("III+", PitchInterval.AUGMENTED_THIRD.getRomanNumeralName());
        assertEquals("v˚", PitchInterval.DIMINISHED_FIFTH.getRomanNumeralName());
        assertEquals("IV+", PitchInterval.AUGMENTED_FOURTH.getRomanNumeralName());
        assertEquals("V", PitchInterval.PERFECT_FIFTH.getRomanNumeralName());
        assertEquals("vi˚", PitchInterval.DIMINISHED_SIXTH.getRomanNumeralName());
        assertEquals("vi", PitchInterval.MINOR_SIXTH.getRomanNumeralName());
        assertEquals("V+", PitchInterval.AUGMENTED_FIFTH.getRomanNumeralName());
        assertEquals("VI", PitchInterval.MAJOR_SIXTH.getRomanNumeralName());
        assertEquals("vii˚", PitchInterval.DIMINISHED_SEVENTH.getRomanNumeralName());
        assertEquals("vii", PitchInterval.MINOR_SEVENTH.getRomanNumeralName());
        assertEquals("VI+", PitchInterval.AUGMENTED_SIXTH.getRomanNumeralName());
        assertEquals("VII", PitchInterval.MAJOR_SEVENTH.getRomanNumeralName());
        assertEquals("viii˚", PitchInterval.DIMINISHED_OCTAVE.getRomanNumeralName());
        assertEquals("VIII", PitchInterval.PERFECT_OCTAVE.getRomanNumeralName());
        assertEquals("VII+", PitchInterval.AUGMENTED_SEVENTH.getRomanNumeralName());
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
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(Pitch.C_4, Pitch.E_4));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.C, PitchClass.E));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.E, PitchClass.C));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.C_SHARP, PitchClass.E_SHARP));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.E_SHARP, PitchClass.C_SHARP));
        assertEquals(PitchInterval.MAJOR_THIRD, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.B_FLAT));
        assertEquals(PitchInterval.MINOR_SIXTH, PitchInterval.getPitchIntervalBetween(PitchClass.B_FLAT, PitchClass.G_FLAT));
        assertEquals(PitchInterval.PERFECT_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_FLAT));
        assertEquals(PitchInterval.AUGMENTED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C));
        assertEquals(PitchInterval.DIMINISHED_FOURTH, PitchInterval.getPitchIntervalBetween(PitchClass.G_FLAT, PitchClass.C_DOUBLE_FLAT));
    }
}