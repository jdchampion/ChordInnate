package chordinnate.musictheory;

/**
 * Created by Joseph on 6/16/16.
 * References: https://en.wikipedia.org/wiki/Tuplet
 */
enum Tuplet {
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

    private final int NUMBER;

    Tuplet(int number) {
        this.NUMBER = number;
    }

    public int getNumber() {
        return NUMBER;
    }
}
