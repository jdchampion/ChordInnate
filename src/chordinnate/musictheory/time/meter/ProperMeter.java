package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Tuplet;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * This represents the most common time signatures.
 *
 * Created by Joseph on 7/3/16.
 */
public final class ProperMeter extends FixedMeter {
    ProperMeter(Number denominator, Number... numerator) {
        super(denominator, numerator);
    }

    @Override
    boolean hasValidArguments(Number[] numerator, Number denominator) {
        /*
         * Numerator and denominator must both possess the following properties:
         * - nonzero
         * - nonnegative
         * - integer values
         *
         * Denominator must possess the following properties:
         * - no greater than the Tuplet with the highest NUMBER field
         *   (this bounding is required to properly resolve it into a Beat object)
         */
        double d1 = numeratorSum(), d2 = denominator.doubleValue();
        int i1 = (int) d1, i2 = denominator.intValue();
        Tuplet highestSubdivision = Tuplet.values()[Tuplet.values().length - 1];

        return ((i1 > 0 && i2 > 0) &&
                ((i1 == d1) && (i2 == d2)) &&
                (i2 < highestSubdivision.NUMBER)
        );
    }

    @Override
    void inferMeterClassifications() {
        // TODO
    }
}
