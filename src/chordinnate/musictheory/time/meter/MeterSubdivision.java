package chordinnate.musictheory.time.meter;

/**
 * Created by Joseph on 7/1/16.
 */
public enum MeterSubdivision {
    DUPLE(2),
    TRIPLE(3),
    QUADRUPLE(4),
    QUINTUPLE(5),
    SEXTUPLE(6),
    SEPTUPLE(7),

    ;

    public final int GROUPING;

    MeterSubdivision(int grouping) {
        this.GROUPING = grouping;
    }
}
