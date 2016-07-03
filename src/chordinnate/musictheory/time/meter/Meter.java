package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    HashSet<MeterProperty> meterProperties;
    double measureDuration;

    public double getMeasureDuration() {
        return measureDuration;
    }

    public boolean is(@NotNull MeterProperty meterProperty) {
        return meterProperties.contains(meterProperty);
    }
}
