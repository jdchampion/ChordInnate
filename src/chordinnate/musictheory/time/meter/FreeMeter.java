package chordinnate.musictheory.time.meter;

/**
 * Meters for which there are no measurement (i.e., subdivisions) of time.
 *
 * Created by Joseph on 7/2/16.
 */
public class FreeMeter extends TransientMeter {
    public FreeMeter() {
        this.measureDuration = Double.POSITIVE_INFINITY;
        this.differentMeters = null;
        this.indexOfMeterBeingUsed = -1;
    }

    @Override
    public boolean is(MeterProperty meterProperty) {
        // Free meters are free. Obvious comment is obvious.
        return meterProperty.equals(MeterProperty.FREE);
    }
}
