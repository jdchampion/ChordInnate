package chordinnate.model.musictheory.time.meter.fixed.complete;

import chordinnate.model.musictheory.time.meter.MeterSubdivision;
import chordinnate.model.musictheory.time.rhythm.BeatDuration;
import org.jetbrains.annotations.NotNull;

/**
 * CompleteMeters whose time signature numerator is not displayed as a summation of integers.
 * This represents the most commonly-used time signatures.
 *
 * Created by Joseph on 7/5/16.
 */
public final class NonAdditiveCompleteMeter extends CompleteMeter {
    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * The beat subdivision will be automatically inferred.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     */
    public NonAdditiveCompleteMeter(int numerator, @NotNull BeatDuration denominator) {
        super(numerator, denominator, null, false, true);
    }

    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     * @param subdivisions the subdivision pattern for this Meter
     */
    public NonAdditiveCompleteMeter(int numerator, BeatDuration denominator, MeterSubdivision... subdivisions) {
        super(numerator, denominator, subdivisions, true, false);
    }
}
