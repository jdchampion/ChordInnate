package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
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
         * Denominator must possess AT LEAST ONE of following properties:
         * - is a factor of the shortest supported Duration
         * - no greater than the Tuplet with the highest NUMBER field
         *   (this bounding is required to properly resolve it into a Beat object)
         */
        double d1 = super.sumAll(numerator), d2 = denominator.doubleValue();
        int i1 = (int) d1, i2 = denominator.intValue();
        Tuplet highestSubdivision = Tuplet.values()[Tuplet.values().length - 1];

        return ((i1 > 0 && i2 > 0) &&
                ((i1 == d1) && (i2 == d2)) &&
                ((1 / Duration.shortest().RATIO % i2 == 0) || i2 < highestSubdivision.NUMBER)
        );
    }

    @Override
    void inferMeterClassifications() {
        /*
         * By definition, ProperMeters possess integer values for both numerator and denominator.
         * Some sequential math / logic checks are required to add the properties exclusive to ProperMeters.
         */
        double num = sumAll(numerator);
        if(!inferCompoundMeter(num)) {
            inferImperfectOrOddMeter(num);
            inferPerfectMeter(num);
            inferSimpleOrComplexMeter(num);
        }

        /*
         * Based on the flow chart, ProperMeters are capable of possessing the following additional properties:
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

    /**
     * Determines whether this Meter is compound, and if so, classifies it as such.
     */
    boolean inferCompoundMeter(double num) {
        if (num % 6 == 0) {
            meterClassificationTypes.add(MeterClassificationType.COMPOUND);
            return true;
        }
        return false;
    }

    /**
     * Determines whether the numerator for this Meter is divisible by 2.
     * If so, then the Meter is considered imperfect.
     * If not, then the meter is considered odd.
     * @param num
     */
    void inferImperfectOrOddMeter(double num) {
        if (num % 2 == 0) {
            meterClassificationTypes.add(MeterClassificationType.IMPERFECT);
        }
        else {
            meterClassificationTypes.add(MeterClassificationType.ODD); // saves a redundant check
        }
    }

    /**
     * Determines whether this Meter is perfect, and if so, classifies it as such.
     */
    void inferPerfectMeter(double num) {
        if (num % 3 == 0) {
            meterClassificationTypes.add(MeterClassificationType.PERFECT);
        }
    }

    /**
     * Determines whether the numerator for this Meter <= 5.
     * If so, then the Meter is considered simple.
     * If not, then the meter is considered complex.
     * @param num
     */
    void inferSimpleOrComplexMeter(double num) {
        if (num <= 5) {
            meterClassificationTypes.add(MeterClassificationType.SIMPLE);
        }
        else {
            meterClassificationTypes.add(MeterClassificationType.COMPLEX);
        }
    }
}
