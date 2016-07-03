package chordinnate.musictheory.time.meter;

/**
 * Created by Joseph on 7/2/16.
 */
public final class FreeMeter extends TransientMeter {
    public FreeMeter() {
        this.measureDuration = Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean is(MeterProperty meterProperty) {
        // Free meters are free. Obvious comment is obvious.
        return meterProperty.equals(MeterProperty.FREE);
    }
}
