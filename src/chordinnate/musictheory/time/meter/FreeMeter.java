package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Meters for which there are no measurement (i.e., subdivisions) of time.
 *
 * Created by Joseph on 7/2/16.
 */
public class FreeMeter extends TransientMeter {
    public FreeMeter() {
        this.meterProperties = new HashSet<>();
        meterProperties.add(MeterProperty.FREE);
        this.measureDuration = Double.POSITIVE_INFINITY;
        this.differentMeters = null;
        this.indexOfMeterBeingUsed = -1;
    }
}
