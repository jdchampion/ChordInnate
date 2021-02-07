package chordinnate.model.musictheory.temporal.meter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Joseph on 7/1/16.
 */
@AllArgsConstructor
@Getter
public enum MeterSubdivision {
    DUPLE(2),
    TRIPLE(3),
    QUADRUPLE(4),

    ;

    private final int grouping;
}
