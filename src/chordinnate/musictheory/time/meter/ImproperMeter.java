package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Tuplet;

/**
 * Meters whose time signature uses fractional, partial, or irrational numbers in its measurement.
 * These time signatures are rare.
 *
 * Created by Joseph on 7/3/16.
 */
public final class ImproperMeter extends FixedMeter {
    ImproperMeter(Number denominator, Number... numerator) {
        super(denominator, numerator);
    }

    @Override
    boolean hasValidArguments(Number[] numerator, Number denominator) {
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
        double d1 = super.sumAll(numerator), d2 = denominator.doubleValue();
        int i1 = (int) d1, i2 = denominator.intValue();
        Tuplet highestSubdivision = Tuplet.values()[Tuplet.values().length - 1];

        return (
                (d1 > 0 && d2 > 0) &&
                        ((d1 - i1 != 0.0) || (d2 - i2 != 0.0)) &&
                        (d2 <= highestSubdivision.NUMBER)
        );
    }

    @Override
    void inferMeterClassifications() {
        /*
         * By definition of an ImproperMeter (i.e., contains fractional / partial / irrational numbers),
         * we know that this Meter will be fractional, partial, and irregular.
         */
        meterClassificationTypes.add(MeterClassificationType.FRACTIONAL);
        meterClassificationTypes.add(MeterClassificationType.PARTIAL);
        meterClassificationTypes.add(MeterClassificationType.IRREGULAR);

        /*
         * Based on the flow chart, ImproperMeters are capable of possessing the following additional properties:
         * - additive
         * - irrational
         * - asymmetrical
         *
         * Figure out which ones this Meter has.
         */
        super.inferAdditiveMeter();
        super.inferIrrationalMeter();
        super.inferAsymmetricalMeter();
    }
}
