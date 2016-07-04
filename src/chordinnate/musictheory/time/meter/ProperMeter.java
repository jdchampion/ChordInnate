package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Tuplet;

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
         *
         * Denominator must possess the following properties:
         * - no greater than the Tuplet with the highest NUMBER field
         *   (this bounding is required to properly resolve it into a Beat object)
         */
        Tuplet highestSubdivision = Tuplet.values()[Tuplet.values().length - 1];

        int denom = denominator.intValue();

        return ((numerator.intValue() > 0 && denom > 0) &&
                (denom < highestSubdivision.NUMBER)
        );
    }

    @Override
    void inferMeterClassifications() {
        // TODO
    }
}
