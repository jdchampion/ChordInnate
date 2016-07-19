package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * CompleteMeters whose time signature numerator is not displayed as a summation of integers.
 * This represents the most commonly-used time signatures.
 *
 * Created by Joseph on 7/5/16.
 */
final class NonAdditiveCompleteMeter extends CompleteMeter {
    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * The beat subdivision will be automatically inferred.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     */
    NonAdditiveCompleteMeter(int numerator, @NotNull Duration denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = this.numerator * denominator.RATIO;

        inferCommonMeterClassifications();

        /*
         * If we use this constructor, no subdivision pattern has been defined
         * for this Meter. Therefore, we have to make one up based on conventions.
         */
        inferSubdivisions();
    }

    /**
     * Constructs a Meter with integers for both numerator and denominator.
     * @param numerator the number of beats per measure
     * @param denominator the duration value for each beat
     * @param subdivisions the subdivision pattern for this Meter
     */
    NonAdditiveCompleteMeter(int numerator, Duration denominator, MeterSubdivision... subdivisions) {
        verifyMatch(numerator, subdivisions);

        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = this.numerator * denominator.RATIO;
        this.subdivisions = subdivisions;

        inferCommonMeterClassifications();
    }
}
