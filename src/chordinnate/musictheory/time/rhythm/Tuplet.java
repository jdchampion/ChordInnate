package chordinnate.musictheory.time.rhythm;

/**
 * Created by Joseph on 6/16/16.
 * References: https://en.wikipedia.org/wiki/Tuplet
 */
enum Tuplet {
    NONE(0),
    DUPLET(2),
    TRIPLET(3),
    QUADRUPLET(4),
    QUINTUPLET(5),
    SEXTUPLET(6),
    SEPTUPLET(7),
    OCTUPLET(8),
    NONUPLET(9),
    DECUPLET(10),
    UNDECUPLET(11),
    DODECUPLET(12),
    TREDECUPLET(13),

    ;

    public final int NUMBER;

    Tuplet(int number) {
        this.NUMBER = number;
    }
}
