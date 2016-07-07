package chordinnate.musictheory.time.rhythm;

/**
 * Created by Joseph on 6/16/16.
 * References: https://en.wikipedia.org/wiki/Tuplet
 */
public enum Tuplet {
    NONE(0, 1.0),
    DUPLET(2, 0.5),
    TRIPLET(3, 1.0/3.0),
    QUADRUPLET(4, 0.25),
    QUINTUPLET(5, 0.2),
    SEXTUPLET(6, 1.0/6.0),
    SEPTUPLET(7, 1.0/7.0),
    OCTUPLET(8, 0.125),
    NONUPLET(9, 1.0/9.0),
    DECUPLET(10, 0.1),
    UNDECUPLET(11, 1.0/11.0),
    DODECUPLET(12, 1.0/12.0),
    TREDECUPLET(13, 1.0/13.0),

    ;

    public final int NUMBER;
    public final double RATIO;

    Tuplet(int number, double ratio) {
        this.NUMBER = number;
        this.RATIO = ratio;
    }
}
