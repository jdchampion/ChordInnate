package musictheory;

/**
 * Created by Joseph on 1/2/16.
 *
 * Reference: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 */
enum Degree {
    ROOT,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH,
    SIXTH,
    SEVENTH,
    OCTAVE,
    NINTH,
    TENTH,
    ELEVENTH,
    TWELFTH,
    THIRTEENTH;

    Degree getNext() {
        return this.ordinal() < Degree.values().length - 1
                ? Degree.values()[this.ordinal() + 1]
                : null;
    }

    Degree getPrevious() {
        return this.ordinal() > 0
                ? Degree.values()[this.ordinal() - 1]
                : null;
    }


}
