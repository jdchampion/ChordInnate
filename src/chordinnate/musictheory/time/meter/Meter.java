package chordinnate.musictheory.time.meter;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    protected double measureDuration;

    public double getMeasureDuration() {
        return measureDuration;
    }

    public abstract boolean is(MeterProperty meterProperty);
}
