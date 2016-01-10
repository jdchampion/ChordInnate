package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/7/16.
 *
 * References: https://en.wikipedia.org/wiki/Nashville_number_system
 *             https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 *             https://en.wikipedia.org/wiki/Interval_(music)
 *             http://method-behind-the-music.com/theory/intervals/
 *             http://www.musictheory.net/lessons/31
 *             http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 */
public enum NashvilleInterval {
    // Base one relative pitch from Major = 0
    DOUBLE_FLAT_ONE(10, DOUBLE_FLAT, 1),
    FLAT_ONE(11, FLAT, 1),
    ONE(0, NONE, 1),
    SHARP_ONE(1, SHARP, 1),
    DOUBLE_SHARP_ONE(2, SHARP, 1),

    // Base two relative pitch from Major = 2
    DOUBLE_FLAT_TWO(0, DOUBLE_FLAT, 2),
    FLAT_TWO(1, FLAT, 2),
    TWO(2, NONE, 2),
    SHARP_TWO(3, SHARP, 2),
    DOUBLE_SHARP_TWO(4, SHARP, 2),

    // Base three relative pitch from Major = 4
    DOUBLE_FLAT_THREE(2, DOUBLE_FLAT, 3),
    FLAT_THREE(3, FLAT, 3),
    THREE(4, NONE, 3),
    SHARP_THREE(5, SHARP, 3),
    DOUBLE_SHARP_THREE(6, DOUBLE_SHARP, 3),

    // Base four relative pitch from Major = 5
    DOUBLE_FLAT_FOUR(3, DOUBLE_FLAT, 4),
    FLAT_FOUR(4, FLAT, 4),
    FOUR(5, NONE, 4),
    SHARP_FOUR(6, SHARP, 4),
    DOUBLE_SHARP_FOUR(7, DOUBLE_SHARP, 4),

    // Base five relative pitch from Major = 7
    DOUBLE_FLAT_FIVE(5, DOUBLE_FLAT, 5),
    FLAT_FIVE(6, FLAT, 5),
    FIVE(7, NONE, 5),
    SHARP_FIVE(8, SHARP, 5),
    DOUBLE_SHARP_FIVE(9, DOUBLE_SHARP, 5),

    // Base six relative pitch from Major = 9
    DOUBLE_FLAT_SIX(7, DOUBLE_FLAT, 6),
    FLAT_SIX(8, FLAT, 6),
    SIX(9, NONE, 6),
    SHARP_SIX(10, SHARP, 6),
    DOUBLE_SHARP_SIX(11, DOUBLE_SHARP, 6),

    // Base seven relative pitch from Major = 11
    DOUBLE_FLAT_SEVEN(9, DOUBLE_FLAT, 7),
    FLAT_SEVEN(10, FLAT, 7),
    SEVEN(11, NONE, 7),
    SHARP_SEVEN(12, SHARP, 7),
    DOUBLE_SHARP_SEVEN(13, DOUBLE_SHARP, 7),

    // Base eight relative pitch from Major = 12
    DOUBLE_FLAT_OCTAVE(10, DOUBLE_FLAT, 8),
    FLAT_OCTAVE(11, FLAT, 8),
    OCTAVE(12, NONE, 8),
    SHARP_OCTAVE(13, SHARP, 8),
    DOUBLE_SHARP_OCTAVE(14, DOUBLE_SHARP, 8),

    // Base nine relative pitch from Major = 14
    DOUBLE_FLAT_NINE(12, DOUBLE_FLAT, 9),
    FLAT_NINE(13, FLAT, 9),
    NINE(14, NONE, 9),
    SHARP_NINE(15, SHARP, 9),
    DOUBLE_SHARP_NINE(16, DOUBLE_SHARP, 9),

    // Base ten relative pitch from Major = 16
    DOUBLE_FLAT_TEN(14, DOUBLE_FLAT, 10),
    FLAT_TEN(15, FLAT, 10),
    TEN(16, NONE, 10),
    SHARP_TEN(17, SHARP, 10),
    DOUBLE_SHARP_TEN(18, DOUBLE_SHARP, 10),

    // Base eleven relative pitch from Major = 17
    DOUBLE_FLAT_ELEVEN(15, DOUBLE_FLAT, 11),
    FLAT_ELEVEN(16, FLAT, 11),
    ELEVEN(17, NONE, 11),
    SHARP_ELEVEN(18, SHARP, 11),
    DOUBLE_SHARP_ELEVEN(19, DOUBLE_SHARP, 11),

    // Base twelve relative pitch from Major = 19
    DOUBLE_FLAT_TWELVE(17, DOUBLE_FLAT, 12),
    FLAT_TWELVE(18, FLAT, 12),
    TWELVE(19, NONE, 12),
    SHARP_TWELVE(20, SHARP, 12),
    DOUBLE_SHARP_TWELVE(21, DOUBLE_SHARP, 12),

    // Base thirteen relative pitch from Major = 21
    DOUBLE_FLAT_THIRTEEN(19, DOUBLE_FLAT, 13),
    FLAT_THIRTEEN(20, FLAT, 13),
    THIRTEEN(21, NONE, 13),
    SHARP_THIRTEEN(22, SHARP, 13),
    DOUBLE_SHARP_THIRTEEN(23, DOUBLE_SHARP, 13);

    int relativePitchDistance;
    Accidental quality;
    int intervalNumber;

    NashvilleInterval(int relativePitchDistance, Accidental quality, int intervalNumber) {
        this.relativePitchDistance = relativePitchDistance;
        this.quality = quality;
        this.intervalNumber = intervalNumber;
    }

    final String getShortName() {
        return quality.indicator + intervalNumber;
    }
}
