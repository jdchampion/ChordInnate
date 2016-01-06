package musictheory;

import static musictheory.Tonality.*;
import static musictheory.Interval.*;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public enum ScaleType {
    MAJOR(
            "Major",
            new int[] {0, 2, 4, 5, 7, 9, 11},
            new Interval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MAJOR_TONALITY
    ),
    HARMONIC_MINOR(
            "Harmonic Minor",
            new int[] {0, 2, 3, 5, 7, 8, 11},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_ASCENDING(
            "Melodic Minor (Ascending)",
            new int[] {0, 2, 3, 5, 7, 9, 11},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_DESCENDING(
            "Melodic Minor (Descending)",
            new int[] {0, 2, 3, 5, 7, 8, 10},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY
    ),
    CHROMATIC(
            "Chromatic",
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            new Interval[] {ONE, SHARP_ONE, TWO, SHARP_TWO, THREE, FOUR,
                    SHARP_FOUR, FIVE, SHARP_FIVE, SIX, SHARP_SIX, SEVEN
            },
            CHROMATIC_TONALITY
    ),
    WHOLE_TONE(
            "Whole Tone",
            new int[] {0, 2, 4, 6, 8, 10},
            new Interval[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SHARP_SIX},
            MINOR_TONALITY // TODO NONE
    ),
    PENTATONIC_MAJOR(
            "Pentatonic Major",
            new int[] {0, 2, 4, 7, 9},
            new Interval[] {ONE, TWO, THREE, FIVE, SIX},
            MAJOR_TONALITY // TODO NONE
    ),
    PENTATONIC_MINOR(
            "Pentatonic Minor",
            new int[] {0, 3, 5, 7, 10},
            new Interval[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN},
            MINOR_TONALITY // TODO NONE
    ),
    PENTATONIC_BLUES(
            "Pentatonic Blues",
            new int[] {0, 3, 5, 6, 7, 10},
            new Interval[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            MINOR_TONALITY // TODO NONE -- also identical to BLUES
    ),
    PENTATONIC_NEUTRAL(
            "Pentatonic Neutral",
            new int[] {0, 2, 5, 7, 10},
            new Interval[] {ONE, TWO, FOUR, FIVE, FLAT_SEVEN},
            MAJOR_TONALITY // TODO NONE
    ),
    BLUES(
            "Blues",
            new int[] {0, 3, 5, 6, 7, 10},
            new Interval[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            MINOR_TONALITY // TODO NONE
    ),
    OCTATONIC_HALF_WHOLE(
            "Octatonic (Half-Whole)",
            new int[] {0, 1, 3, 4, 6, 7, 9, 10},
            new Interval[] {ONE, FLAT_TWO, FLAT_THREE, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            MAJOR_TONALITY // TODO OCTATONIC
    ),
    OCTATONIC_WHOLE_HALF(
            "Octatonic (Whole-Half)",
            new int[] {0, 2, 3, 5, 6, 8, 9, 11},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            MINOR_TONALITY // TODO OCTATONIC
    ),
    IONIAN(
            "Ionian",
            new int[] {0, 2, 4, 5, 7, 9, 11},
            new Interval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MAJOR_TONALITY // TODO MODAL
    ),
    DORIAN(
            "Dorian",
            new int[] {0, 2, 3, 5, 7, 9, 10},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MINOR_TONALITY // TODO MODAL
    ),
    PHRYGIAN(
            "Phrygian",
            new int[] {0, 1, 3, 5, 7, 8, 10},
            new Interval[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY // TODO MODAL
    ),
    LYDIAN(
            "Lydian",
            new int[] {0, 2, 4, 6, 7, 9, 11},
            new Interval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, SEVEN},
            MAJOR_TONALITY // TODO MODAL
    ),
    MIXOLYDIAN(
            "Mixolydian",
            new int[] {0, 2, 4, 5, 7, 9, 10},
            new Interval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MAJOR_TONALITY // TODO MODAL
    ),
    AEOLIAN(
            "Aeolian",
            new int[] {0, 2, 3, 5, 7, 8, 10},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY // TODO MODAL
    ),
    LOCRIAN(
            "Locrian",
            new int[] {0, 1, 3, 5, 6, 8, 10},
            new Interval[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY // TODO MODAL
    ),
    ALGERIAN(
            "Algerian",
            new int[] {0, 2, 3, 5, 6, 7, 8, 11},
            new Interval[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SIX, SEVEN},
            MAJOR_TONALITY // TODO NONE
    );

    final String name;
    final int[] sequence;
    final Interval[] intervals;
    final Tonality tonality;

    ScaleType(String name, int[] sequence, Interval[] intervals, Tonality tonality) {
        this.name = name;
        this.sequence = sequence;
        this.intervals = intervals;
        this.tonality = tonality;
    }
}

enum Tonality {
    MAJOR_TONALITY, MINOR_TONALITY, NO_TONALITY,
    CHROMATIC_TONALITY, OCTATONIC_TONALITY, MODAL_TONALITY
}
