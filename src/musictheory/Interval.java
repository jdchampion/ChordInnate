package musictheory;

import static musictheory.IntervalQuality.*;

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
    // DO NOT RE-ORDER THESE ITEMS!!

    PERFECT_UNISON(0, PERFECT, 1),
    DIMINISHED_SECOND(0, DIMINISHED, 2),

    MINOR_SECOND(1, MINOR, 2),
    AUGMENTED_UNISON(1, AUGMENTED, 1),

    MAJOR_SECOND(2, MAJOR, 2),
    DIMINISHED_THIRD(2, DIMINISHED, 3),

    MINOR_THIRD(3, MINOR, 3),
    AUGMENTED_SECOND(3, AUGMENTED, 2),

    MAJOR_THIRD(4, MAJOR, 3),
    DIMINISHED_FOURTH(4, DIMINISHED, 4),

    PERFECT_FOURTH(5, PERFECT, 4),
    AUGMENTED_THIRD(5, AUGMENTED, 3),

    DIMINISHED_FIFTH(6, DIMINISHED, 5),
    AUGMENTED_FOURTH(6, AUGMENTED, 4),

    PERFECT_FIFTH(7, PERFECT, 5),
    DIMINISHED_SIXTH(7, DIMINISHED, 6),

    MINOR_SIXTH(8, MINOR, 6),
    AUGMENTED_FIFTH(8, AUGMENTED, 5),

    MAJOR_SIXTH(9, MAJOR, 6),
    DIMINISHED_SEVENTH(9, DIMINISHED, 7),

    MINOR_SEVENTH(10, MINOR, 7),
    AUGMENTED_SIXTH(10, AUGMENTED, 6),

    MAJOR_SEVENTH(11, MAJOR, 7),
    DIMINISHED_OCTAVE(11, DIMINISHED, 8),

    PERFECT_OCTAVE(12, PERFECT, 8),
    AUGMENTED_SEVENTH(12, AUGMENTED, 7);

    final int relativePitchDistance;
    final IntervalQuality intervalQuality;
    final int intervalNumber;
    final RomanNumeral romanNumeral;

    Interval(int relativePitchDistance, IntervalQuality quality, int intervalNumber) {
        this.relativePitchDistance = relativePitchDistance;
        this.intervalQuality = quality;
        this.intervalNumber = intervalNumber;
        this.romanNumeral = (quality.equals(MINOR) || quality.equals(DIMINISHED))
                ? RomanNumeral.getSmallRomanNumerals()[(intervalNumber-1)%7]
                : RomanNumeral.getBigRomanNumerals()[(intervalNumber-1)%7];
    }

    String getRomanNumeralName() {
        return romanNumeral + intervalQuality.romanNumeralIdentifier;
    }

    String getShortName() {
        return intervalQuality.shortNameIdentifier + intervalNumber;
    }
}

enum IntervalQuality {
    MAJOR("M", ""),
    MINOR("m", ""),
    PERFECT("P", ""),
    AUGMENTED("A", "+"),
    DIMINISHED("d", "˚");

    final String shortNameIdentifier, romanNumeralIdentifier;

    IntervalQuality(String shortNameIdentifier, String romanNumeralIdentifier) {
        this.shortNameIdentifier = shortNameIdentifier;
        this.romanNumeralIdentifier = romanNumeralIdentifier;
    }
}

enum Step {
    W, H, WH, WW
}