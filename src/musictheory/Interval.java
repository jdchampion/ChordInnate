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
    PERFECT_UNISON(0, IntervalQuality.PERFECT, 1),
    DIMINISHED_SECOND(0, IntervalQuality.DIMINISHED, 2),

    MINOR_SECOND(1, IntervalQuality.MINOR, 2),
    AUGMENTED_UNISON(1, IntervalQuality.AUGMENTED, 1),

    MAJOR_SECOND(2, IntervalQuality.MAJOR, 2),
    DIMINISHED_THIRD(2, IntervalQuality.DIMINISHED, 3),

    MINOR_THIRD(3, IntervalQuality.MINOR, 3),
    AUGMENTED_SECOND(3, IntervalQuality.AUGMENTED, 2),

    MAJOR_THIRD(4, IntervalQuality.MAJOR, 3),
    DIMINISHED_FOURTH(4, IntervalQuality.DIMINISHED, 4),

    PERFECT_FOURTH(5, IntervalQuality.PERFECT, 4),
    AUGMENTED_THIRD(5, IntervalQuality.AUGMENTED, 3),

    DIMINISHED_FIFTH(6, IntervalQuality.DIMINISHED, 5),
    AUGMENTED_FOURTH(6, IntervalQuality.AUGMENTED, 4),

    PERFECT_FIFTH(7, IntervalQuality.PERFECT, 5),
    DIMINISHED_SIXTH(7, IntervalQuality.DIMINISHED, 6),

    MINOR_SIXTH(8, IntervalQuality.MINOR, 6),
    AUGMENTED_FIFTH(8, IntervalQuality.AUGMENTED, 5),

    MAJOR_SIXTH(9, IntervalQuality.MAJOR, 6),
    DIMINISHED_SEVENTH(9, IntervalQuality.DIMINISHED, 7),

    MINOR_SEVENTH(10, IntervalQuality.MINOR, 7),
    AUGMENTED_SIXTH(10, IntervalQuality.AUGMENTED, 6),

    MAJOR_SEVENTH(11, IntervalQuality.MAJOR, 7),
    DIMINISHED_OCTAVE(11, IntervalQuality.DIMINISHED, 8),

    PERFECT_OCTAVE(12, IntervalQuality.PERFECT, 8),
    AUGMENTED_SEVENTH(12, IntervalQuality.AUGMENTED, 7);

    int relativePitchDistance;
    IntervalQuality quality;
    int intervalNumber;
    String romanNumeral;

    Interval(int relativePitchDistance, IntervalQuality quality, int intervalNumber) {
        String[] bigRomanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII"};
        String[] smallRomanNumerals = {"i", "ii", "iii", "iv", "v", "vi", "vii"};
        this.relativePitchDistance = relativePitchDistance;
        this.quality = quality;
        this.intervalNumber = intervalNumber;
        this.romanNumeral = (quality.equals(IntervalQuality.MINOR) || quality.equals(IntervalQuality.DIMINISHED))
                ? smallRomanNumerals[(intervalNumber-1)%7]
                : bigRomanNumerals[(intervalNumber-1)%7];
    }

    String getRomanNumeralName() {
        return romanNumeral + quality.romanNumeralIdentifier;
    }

    String getShortName() {
        return quality.shortNameIdentifier + intervalNumber;
    }

    private enum IntervalQuality {
        MAJOR("M", ""),
        MINOR("m", ""),
        PERFECT("P", ""),
        AUGMENTED("A", "+"),
        DIMINISHED("d", "˚");

        String shortNameIdentifier, romanNumeralIdentifier;

        IntervalQuality(String shortNameIdentifier, String romanNumeralIdentifier) {
            this.shortNameIdentifier = shortNameIdentifier;
            this.romanNumeralIdentifier = romanNumeralIdentifier;
        }
    }
}

enum Step {
    W, H, WH, WW
}