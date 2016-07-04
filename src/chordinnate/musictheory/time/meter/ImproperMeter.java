package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Tuplet;

/**
 * Meters whose time signature uses fractional, partial, or irrational numbers in its measurement.
 * These time signatures are rare.
 *
 * Created by Joseph on 7/3/16.
 */
public final class ImproperMeter extends FixedMeter {
    ImproperMeter(Number numerator, Number denominator) {
        super(numerator, denominator);
    }

    @Override
    boolean hasValidArguments(Number numerator, Number denominator) {
        /*
         * Numerator and denominator must both possess the following properties:
         * - nonzero
         * - nonnegative
         *
         * One or both must possess the following properties:
         * - nonzero mantissa (i.e., the decimal value after the floating point)
         *
         * Denominator must possess the following properties:
         * - no greater than the Tuplet with the highest NUMBER field
         *   (this bounding is required to properly resolve it into a Beat object)
         */
        double d1 = numerator.doubleValue(), d2 = denominator.doubleValue();
        int i1 = numerator.intValue(), i2 = denominator.intValue();

        Tuplet highestSubdivision = Tuplet.values()[Tuplet.values().length - 1];

        return (
                (d1 > 0 && d2 > 0) &&
                        (((double) i1 != d1) || ((double) i2 != d2)) &&
                        (d2 <= highestSubdivision.NUMBER)
        );
    }

    @Override
    void inferMeterClassifications() {
        // TODO
    }
}
