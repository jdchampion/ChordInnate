package musictheory;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public enum ScaleType {
    MAJOR("Major",
            new int[] {0, 2, 4, 5, 7, 9, 11},
            Tonality.MAJOR
    ),
    HARMONIC_MINOR("Harmonic Minor",
            new int[] {0, 2, 3, 5, 7, 8, 11},
            Tonality.MINOR
    ),
    MELODIC_MINOR_ASCENDING("Melodic Minor (Ascending)",
            new int[] {0, 2, 3, 5, 7, 9, 11},
            Tonality.MINOR
    ),
    MELODIC_MINOR_DESCENDING("Melodic Minor (Descending)",
            new int[] {0, 2, 3, 5, 7, 8, 10},
            Tonality.MINOR
    ),
    CHROMATIC("Chromatic",
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            Tonality.MAJOR // TODO Tonality.NONE
    ),
    WHOLE_TONE("Whole Tone",
            new int[] {0, 2, 4, 6, 8, 10},
            Tonality.MINOR // TODO Tonality.NONE
    ),
    PENTATONIC_MAJOR("Pentatonic Major",
            new int[] {0, 2, 4, 7, 9},
            Tonality.MAJOR // TODO Tonality.NONE
    ),
    PENTATONIC_MINOR("Pentatonic Minor",
            new int[] {0, 3, 5, 7, 10},
            Tonality.MINOR // TODO Tonality.NONE
    ),
    PENTATONIC_BLUES("Pentatonic Blues",
            new int[] {0, 3, 5, 6, 7, 10},
            Tonality.MINOR // TODO Tonality.NONE -- also identical to BLUES
    ),
    PENTATONIC_NEUTRAL("Pentatonic Neutral",
            new int[] {0, 2, 5, 7, 10},
            Tonality.MAJOR // TODO Tonality.NONE
    ),
    BLUES("Blues",
            new int[] {0, 3, 5, 6, 7, 10},
            Tonality.MINOR // TODO Tonality.NONE
    ),
    OCTATONIC_HALF_WHOLE("Octatonic (Half-Whole)",
            new int[] {0, 1, 3, 4, 6, 7, 9, 10},
            Tonality.MAJOR // TODO Tonality.OCTATONIC
    ),
    OCTATONIC_WHOLE_HALF("Octatonic (Whole-Half)",
            new int[] {0, 2, 3, 5, 6, 8, 9, 11},
            Tonality.MINOR // TODO Tonality.OCTATONIC
    ),
    IONIAN("Ionian",
            new int[] {0, 2, 4, 5, 7, 9, 11},
            Tonality.MAJOR // TODO Tonality.MODAL
    ),
    DORIAN("Dorian",
            new int[] {0, 2, 3, 5, 7, 9, 10},
            Tonality.MINOR // TODO Tonality.MODAL
    ),
    PHRYGIAN("Phrygian",
            new int[] {0, 1, 3, 5, 7, 8, 10},
            Tonality.MINOR // TODO Tonality.MODAL
    ),
    LYDIAN("Lydian",
            new int[] {0, 2, 4, 6, 7, 9, 11},
            Tonality.MAJOR // TODO Tonality.MODAL
    ),
    MIXOLYDIAN("Mixolydian",
            new int[] {0, 2, 4, 5, 7, 9, 10},
            Tonality.MAJOR // TODO Tonality.MODAL
    ),
    AEOLIAN("Aeolian",
            new int[] {0, 2, 3, 5, 7, 8, 10},
            Tonality.MINOR // TODO Tonality.MODAL
    ),
    LOCRIAN("Locrian",
            new int[] {0, 1, 3, 5, 6, 8, 10},
            Tonality.MINOR // TODO Tonality.MODAL
    ),
    ALGERIAN("Algerian",
            new int[] {0, 2, 3, 5, 6, 7, 8, 11},
            Tonality.MAJOR // TODO Tonality.NONE
    );

    String name;
    int[] sequence;
    Tonality tonality;

    ScaleType(String name, int[] sequence, Tonality tonality) {
        this.name = name;
        this.sequence = sequence;
        this.tonality = tonality;
    }
}

enum Tonality {
    MAJOR, MINOR, NONE, OCTATONIC, MODAL
}
