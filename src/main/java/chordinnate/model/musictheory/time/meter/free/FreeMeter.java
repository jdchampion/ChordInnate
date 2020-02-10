package chordinnate.model.musictheory.time.meter.free;


import chordinnate.model.musictheory.time.meter.Meter;
import chordinnate.model.musictheory.time.meter.MeterType;

/**
 * Meters for which there are no measurement (i.e., subdivisions) of time.
 *
 * Created by Joseph on 7/2/16.
 */
public final class FreeMeter extends Meter {
    public FreeMeter() {
        super.measureDuration = Double.POSITIVE_INFINITY;
        super.meterTypes.add(MeterType.FREE);
    }
}
