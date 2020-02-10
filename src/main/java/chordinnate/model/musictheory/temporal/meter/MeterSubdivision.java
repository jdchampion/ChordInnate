package chordinnate.model.musictheory.temporal.meter;

import lombok.AllArgsConstructor;

/**
 * Created by Joseph on 7/1/16.
 */
@AllArgsConstructor
public enum MeterSubdivision {
    DUPLE(2),
    TRIPLE(3),
    QUADRUPLE(4),

    ;

    public final int grouping;
}
