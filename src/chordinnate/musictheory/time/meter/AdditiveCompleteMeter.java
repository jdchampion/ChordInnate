package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * CompleteMeters whose time signature numerator is displayed as a summation of integers.
 *
 * Created by Joseph on 7/5/16.
 */
final class AdditiveCompleteMeter extends CompleteMeter {
    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * The numerator will be displayed as a summation of integers, matching the provided subdivision pattern.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     * @param subdivisions the subdivision pattern for this Meter
     */
    AdditiveCompleteMeter(int numerator, @NotNull Duration denominator, @NotNull MeterSubdivision... subdivisions) {
        verifyMatch(numerator, subdivisions);

        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = this.numerator * denominator.RATIO;
        this.subdivisions = subdivisions;

        inferCommonMeterClassifications();
        this.meterClassificationTypes.add(MeterClassificationType.ADDITIVE); // By definition of an additive meter
        inferSubdivisions();
    }
}
