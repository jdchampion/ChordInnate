package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * Meters that follow a pattern for changing time.
 *
 * Created by Joseph on 7/3/16.
 */
public class AlternatingMeter extends MixedMeter {
    public AlternatingMeter(@NotNull FixedMeter... meters) {
        super(meters);
        meterProperties.add(MeterProperty.ALTERNATING);
    }
}
