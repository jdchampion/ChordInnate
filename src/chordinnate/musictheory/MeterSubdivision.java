package chordinnate.musictheory;

/**
 * Created by Joseph on 7/1/16.
 */
public enum MeterSubdivision {
    NONE(0),
    DUPLE(2),
    TRIPLE(3),
    QUADRUPLE(4),

    ;

    private final int SUBDIVISIONS;

    MeterSubdivision(int subdivisions) {
        this.SUBDIVISIONS = subdivisions;
    }

    public int getSubdivisions() {
        return SUBDIVISIONS;
    }
}
