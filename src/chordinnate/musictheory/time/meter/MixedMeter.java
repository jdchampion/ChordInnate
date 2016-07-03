package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Meters that change time.
 *
 * Created by Joseph on 7/3/16.
 */
public class MixedMeter extends TransientMeter {
    public MixedMeter(@NotNull FixedMeter... meters) {
        this.meterProperties = new HashSet<>();
        meterProperties.add(MeterProperty.MIXED);
        this.differentMeters = new ArrayList<FixedMeter>(Arrays.asList(meters));
        this.indexOfMeterBeingUsed = 0;
        this.measureDuration = updateMeasureDuration();
    }
}
