package chordinnate.model.musictheory.notation;

import static org.junit.Assert.*;

import chordinnate.model.musictheory.notation.RomanNumeral;
import chordinnate.model.musictheory.pitch.interval.Interval;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RomanNumeralTest {

    @Test
    public void intToRoman() {
        assertEquals("LIII", RomanNumeral.intToRoman(53));
        assertEquals("DCCXCII", RomanNumeral.intToRoman(792));
        assertEquals("CMXI", RomanNumeral.intToRoman(911));
        assertEquals("LXXII", RomanNumeral.intToRoman(72));
        assertEquals("VIII", (RomanNumeral.intToRoman(8)));
        assertEquals("CCLXXVI", RomanNumeral.intToRoman(276));
    }

    @Test
    public void withSymbol() {
        final String TEST_SYMBOL = "{bbbI+(6)}/{V}/{iii(5/4)}";
        final String EXPECTED_SYMBOL = TEST_SYMBOL;
        final String EXPECTED_PRIMARY_SYMBOL = "bbbI+(6)";
        final int EXPECTED_PRIMARY_NUM = 1;

        RomanNumeral romanNumeral = RomanNumeral.withSymbol(TEST_SYMBOL);
        final String[] EXPECTED_AUX_SYMBOLS = {"V", "iii(5/4)"};
        final int[] EXPECTED_AUX_NUMS = {5,3};

        assertEquals(EXPECTED_SYMBOL, romanNumeral.getSymbol());
        assertEquals(EXPECTED_PRIMARY_SYMBOL, romanNumeral.getPrimarySymbol());
        assertEquals(EXPECTED_PRIMARY_NUM, romanNumeral.getPrimaryNumeral());

        final int[] ACTUAL_AUX_NUMS = romanNumeral.getAuxiliaryNumerals();
        final String[] ACTUAL_AUX_SYMBOLS = romanNumeral.getAuxiliarySymbols();

        assertEquals("Auxiliary symbols and auxiliary numerals must be of same quantity", ACTUAL_AUX_SYMBOLS.length, ACTUAL_AUX_NUMS.length);

        for (int i = 0; i < ACTUAL_AUX_NUMS.length; i++) {
            assertEquals(EXPECTED_AUX_NUMS[i], ACTUAL_AUX_NUMS[i]);
            assertEquals(EXPECTED_AUX_SYMBOLS[i], ACTUAL_AUX_SYMBOLS[i]);
        }
    }

    @Test
    public void from_degree_Intervals() {
        final Interval[] TEST_INTERVALS = {Interval.PERFECT_1, Interval.MAJOR_3, Interval.PERFECT_5};
        final int TEST_DEGREE = 1;

        RomanNumeral expected = RomanNumeral.withSymbol("{I}");
        RomanNumeral actual = RomanNumeral.from(TEST_DEGREE, TEST_INTERVALS);

        assertEquals(expected, actual);
    }

    @Test
    public void from_degree_Intervals_RomanNumerals() {
        final Interval[] TEST_INTERVALS = {Interval.PERFECT_1, Interval.MAJOR_3, Interval.PERFECT_5, Interval.MINOR_7};
        final int TEST_DEGREE = 5;
        final RomanNumeral[] TEST_AUXILIARIES = {RomanNumeral.MAJOR_FIVE};

        RomanNumeral expected = RomanNumeral.withSymbol("{V7}/{V}");
        RomanNumeral actual = RomanNumeral.from(TEST_DEGREE, TEST_INTERVALS, TEST_AUXILIARIES);

        assertEquals(expected.getSymbol(), actual.getSymbol());
    }
}
