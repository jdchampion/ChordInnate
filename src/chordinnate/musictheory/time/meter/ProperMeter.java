package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * This represents the most common time signatures.
 *
 * Created by Joseph on 7/3/16.
 */
public class ProperMeter extends FixedMeter {
    @Override
    public boolean is(@NotNull MeterProperty meterProperty) {
        // TODO
        return false;
    }
}
