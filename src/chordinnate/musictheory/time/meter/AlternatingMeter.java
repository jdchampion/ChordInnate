package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/3/16.
 */
public class AlternatingMeter extends MixedMeter {
    public AlternatingMeter(@NotNull FixedMeter... meters) {
        super(meters);
    }

    @Override
    public boolean is(@NotNull MeterProperty meterProperty) {
        // TODO
        return false;
    }
}
