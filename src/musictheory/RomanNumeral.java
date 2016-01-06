package musictheory;

/**
 * Created by Joseph on 1/5/16.
 */
enum RomanNumeral {
    I, II, III, IV, V, VI, VII, VIII,
    i, ii, iii, iv, v, vi, vii, viii;

    static RomanNumeral[] getBigRomanNumerals() {
        return new RomanNumeral[] {I, II, III, IV, V, VI, VII, VIII};
    }

    static RomanNumeral[] getSmallRomanNumerals() {
        return new RomanNumeral[] {i, ii, iii, iv, v, vi, vii, viii};
    }
}
