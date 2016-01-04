package musictheory;

/**
 * Created by Joseph on 1/1/16.
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
    BLUES("Blues",
            new int[] {0, 3, 5, 6, 7, 10},
            Tonality.MINOR
    ),
    PENTATONIC_MAJOR("Pentatonic Major",
            new int[] {0, 2, 4, 7, 9},
            Tonality.MAJOR
    ),
    LOCRIAN("Locrian",
            new int[] {0, 1, 3, 5, 6, 8, 10},
            Tonality.MINOR
    ),
    CHROMATIC("Chromatic",
            new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            Tonality.MAJOR);

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
    MAJOR, MINOR
}
