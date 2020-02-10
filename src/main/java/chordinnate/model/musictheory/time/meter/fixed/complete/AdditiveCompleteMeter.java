package chordinnate.model.musictheory.time.meter.fixed.complete;

import chordinnate.model.musictheory.time.meter.MeterType;
import chordinnate.model.musictheory.time.meter.MeterSubdivision;
import chordinnate.model.musictheory.time.rhythm.BeatDuration;
import org.jetbrains.annotations.NotNull;

/**
 * CompleteMeters whose time signature numerator is displayed as a summation of integers.
 *
 * Created by Joseph on 7/5/16.
 */
public final class AdditiveCompleteMeter extends CompleteMeter {
    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * The numerator will be displayed as a summation of integers, matching the provided subdivision pattern.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     * @param subdivisions the subdivision pattern for this Meter
     */
    public AdditiveCompleteMeter(int numerator, @NotNull BeatDuration denominator, @NotNull MeterSubdivision... subdivisions) {
        super(numerator, denominator, subdivisions, true, true);
        this.meterTypes.add(MeterType.ADDITIVE); // By definition of an additive meter
    }
}
