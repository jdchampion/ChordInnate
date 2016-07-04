package chordinnate.musictheory.time.meter;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * This represents the most common time signatures.
 *
 * Created by Joseph on 7/3/16.
 */
public final class ProperMeter extends FixedMeter {
    ProperMeter(Number numerator, Number denominator) {
        super(numerator, denominator);
    }

    @Override
    boolean hasValidArguments(Number numerator, Number denominator) {
        /*
         * Numerator and denominator must both possess the following properties:
         * - nonzero
         * - nonnegative
         */
        return numerator.intValue() > 0 && denominator.intValue() > 0;
    }

    @Override
    public void inferMeterClassifications() {
        // TODO
    }
}
