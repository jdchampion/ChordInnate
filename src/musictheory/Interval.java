package musictheory;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 *             https://en.wikipedia.org/wiki/Interval_(music)
 *             http://method-behind-the-music.com/theory/intervals/
 *             http://www.musictheory.net/lessons/31
 *             http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 */
public enum Interval {
    PERFECT_UNISON(0, Quality.PERFECT, 1, RomanNumeral.PERFECT_UNISON),
    DIMINISHED_SECOND(0, Quality.DIMINISHED, 2, RomanNumeral.DIMINISHED_SECOND),

    MINOR_SECOND(1, Quality.MINOR, 2, RomanNumeral.MINOR_SECOND),
    AUGMENTED_UNISON(1, Quality.AUGMENTED, 1, RomanNumeral.AUGMENTED_UNISON),

    MAJOR_SECOND(2, Quality.MAJOR, 2, RomanNumeral.MAJOR_SECOND),
    DIMINISHED_THIRD(2, Quality.DIMINISHED, 3, RomanNumeral.DIMINISHED_THIRD),

    MINOR_THIRD(3, Quality.MINOR, 3, RomanNumeral.MINOR_THIRD),
    AUGMENTED_SECOND(3, Quality.AUGMENTED, 2, RomanNumeral.AUGMENTED_SECOND),

    MAJOR_THIRD(4, Quality.MAJOR, 3, RomanNumeral.MAJOR_THIRD),
    DIMINISHED_FOURTH(4, Quality.DIMINISHED, 4, RomanNumeral.DIMINISHED_FOURTH),

    PERFECT_FOURTH(5, Quality.PERFECT, 4, RomanNumeral.PERFECT_FOURTH),
    AUGMENTED_THIRD(5, Quality.AUGMENTED, 3, RomanNumeral.AUGMENTED_THIRD),

    DIMINISHED_FIFTH(6, Quality.DIMINISHED, 5, RomanNumeral.DIMINISHED_FIFTH),
    AUGMENTED_FOURTH(6, Quality.AUGMENTED, 4, RomanNumeral.AUGMENTED_FOURTH),

    PERFECT_FIFTH(7, Quality.PERFECT, 5, RomanNumeral.PERFECT_FIFTH),
    DIMINISHED_SIXTH(7, Quality.DIMINISHED, 6, RomanNumeral.DIMINISHED_SIXTH),

    MINOR_SIXTH(8, Quality.MINOR, 6, RomanNumeral.MINOR_SIXTH),
    AUGMENTED_FIFTH(8, Quality.AUGMENTED, 5, RomanNumeral.AUGMENTED_FIFTH),

    MAJOR_SIXTH(9, Quality.MAJOR, 6, RomanNumeral.MAJOR_SIXTH),
    DIMINISHED_SEVENTH(9, Quality.DIMINISHED, 7, RomanNumeral.DIMINISHED_SEVENTH),

    MINOR_SEVENTH(10, Quality.MINOR, 7, RomanNumeral.MINOR_SEVENTH),
    AUGMENTED_SIXTH(10, Quality.MINOR, 6, RomanNumeral.AUGMENTED_SIXTH),

    MAJOR_SEVENTH(11, Quality.MAJOR, 7, RomanNumeral.MAJOR_SEVENTH),
    DIMINISHED_OCTAVE(11, Quality.DIMINISHED, 8, RomanNumeral.DIMINISHED_OCTAVE),

    PERFECT_OCTAVE(12, Quality.PERFECT, 8, RomanNumeral.PERFECT_OCTAVE),
    AUGMENTED_SEVENTH(12, Quality.AUGMENTED, 7, RomanNumeral.AUGMENTED_SEVENTH);

    int relativePitchDistance;
    Quality quality;
    int intervalNumber;
    RomanNumeral romanNumeral;

    Interval(int relativePitchDistance, Quality quality, int intervalNumber,
             RomanNumeral romanNumeral) {
        this.relativePitchDistance = relativePitchDistance;
        this.quality = quality;
        this.intervalNumber = intervalNumber;
        this.romanNumeral = romanNumeral;
    }
}

enum Quality {
    MAJOR("M"),
    MINOR("m"),
    PERFECT("P"),
    AUGMENTED("A"),
    DIMINISHED("d");

    String identifier;

    Quality(String identifier) {
        this.identifier = identifier;
    }
}

enum RomanNumeral {
    // Uppercase letters for Major, Perfect, and Augmented
    PERFECT_UNISON("I"),
    AUGMENTED_UNISON("I+"),
    MAJOR_SECOND("II"),
    AUGMENTED_SECOND("II+"),
    MAJOR_THIRD("III"),
    PERFECT_FOURTH("IV"),
    AUGMENTED_THIRD("III+"),
    AUGMENTED_FOURTH("IV+"),
    PERFECT_FIFTH("V"),
    AUGMENTED_FIFTH("V+"),
    MAJOR_SIXTH("VI"),
    AUGMENTED_SIXTH("VI+"),
    MAJOR_SEVENTH("VII"),
    PERFECT_OCTAVE("I"),
    AUGMENTED_SEVENTH("VII+"),

    // Lowercase letters for Minor and Diminished
    DIMINISHED_SECOND("ii*"),
    MINOR_SECOND("ii"),
    DIMINISHED_THIRD("iii*"),
    MINOR_THIRD("iii"),
    DIMINISHED_FOURTH("iv*"),
    DIMINISHED_FIFTH("v*"),
    DIMINISHED_SIXTH("vi*"),
    MINOR_SIXTH("vi"),
    DIMINISHED_SEVENTH("vii*"),
    MINOR_SEVENTH("vii"),
    DIMINISHED_OCTAVE("i*");

    String identifier;

    RomanNumeral(String identifier) {
        this.identifier = identifier;
    }
}

enum Step {
    W, H, WH, WW
}