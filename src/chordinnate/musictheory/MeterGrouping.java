package chordinnate.musictheory;

/**
 * Created by Joseph on 7/1/16.
 */
public enum MeterGrouping {
    DUPLE(2),
    TRIPLE(3),
    QUADRUPLE(4),

    ;

    private final int GROUPING;

    MeterGrouping(int grouping) {
        this.GROUPING = grouping;
    }

    public int getGrouping() {
        return GROUPING;
    }
}
