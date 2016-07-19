package chordinnate.musictheory.time.rhythm;

import chordinnate.util.SequentialUtil;

/**
 * Created by Joseph on 6/22/16.
 */
enum DotValue {
    /*
     * NOTE: Keep these in order from least to greatest.
     */
    NONE(1.0),
    ONE(1.5),
    TWO(1.75),
    THREE(1.875),
    FOUR(1.9375),
    FIVE(1.96875),
    SIX(1.984375),
    SEVEN(1.9921875),

    ;

    /*
     * Ratio of the total amount of the associated Beat.
     * Formula for calculating: summation(i = 0 to n) [0.5 ^ n]
     */
    final double RATIO;

    DotValue(double ratio) {
        this.RATIO = ratio;
    }

    @SuppressWarnings("unchecked")
    public DotValue getNext() {
        return (DotValue) SequentialUtil.getNext(this);
    }

    @SuppressWarnings("unchecked")
    public DotValue getPrevious() {
        return (DotValue) SequentialUtil.getPrevious(this);
    }
}
