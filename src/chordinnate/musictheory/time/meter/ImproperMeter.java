package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * Meters whose time signature uses fractional, partial, or irrational numbers in its measurement.
 * These time signatures are rare.
 *
 * Created by Joseph on 7/3/16.
 */
public class ImproperMeter extends FixedMeter {
    @Override
    public boolean is(@NotNull MeterProperty meterProperty) {
        // TODO
        return false;
    }
}
