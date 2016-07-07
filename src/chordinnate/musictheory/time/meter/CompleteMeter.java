package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * NOTE: the time signature denominator MUST be a dyadic rational (i.e., a power of 2).
 *
 * Created by Joseph on 7/3/16.
 */
public abstract class CompleteMeter extends FixedMeter {
    int numerator; // Defined in constructor

    /*
     * Every instance of Duration is a power of 2,
     * so using it as the denominator field
     * ensures that the denominator will be a power of 2.
     */
    Duration denominator; // Defined in constructor

    MeterSubdivision[] subdivisions; // Defined in constructor

    /**
     * This method is intended for validating CompleteMeters at point of construction.
     * This validation is only necessary for CompleteMeter constructors that pass a
     * MeterSubdivision[] argument to this.subdivisions.
     * This method will check whether the summation of the subdivision pattern matches the numerator.
     * If not, an IllegalArgumentException is thrown.
     * @param numerator
     * @param subdivisions
     */
    void verifyMatch(int numerator, MeterSubdivision[] subdivisions) {
        if (subdivisions.length == 0) {
            throw new IllegalArgumentException("No subdivisions provided");
        }
        if (sumAll(subdivisions) != numerator) {
            throw new IllegalArgumentException("Numerator and subdivisons do not match");
        }
    }

    /**
     * Helper method for verifyMatch() to get the summation of MeterSubdivisions.
     * @param subdivisions
     * @return the summation of MeterSubdivisions.
     */
    int sumAll(MeterSubdivision[] subdivisions) {
        int sum = 0;
        for (MeterSubdivision subdivision : subdivisions) {
            sum += subdivision.GROUPING;
        }
        return sum;
    }

    /**
     * A logical sequence for classifying CompleteMeters by their common properties.
     */
    void inferCommonMeterClassifications() {
        boolean
                div2 = numerator % 2 == 0,
                div3 = numerator % 3 == 0;

        if (div2 || div3) {
            meterClassificationTypes.add(MeterClassificationType.MULTPLICATIVE);
            meterClassificationTypes.add(div2 ? MeterClassificationType.IMPERFECT : MeterClassificationType.ODD);
            if (numerator < 6) {
                meterClassificationTypes.add(MeterClassificationType.SIMPLE);
            }
            else if (div3) {
                meterClassificationTypes.add(MeterClassificationType.PERFECT);
                if (numerator > 3) {
                    meterClassificationTypes.add(MeterClassificationType.COMPOUND);
                }
            }
            // NOTE: additive cannot be evaluated from here. Do so in AdditiveCompleteMeter.
            else {
                // numerator >= 5 && !div3
                meterClassificationTypes.add(MeterClassificationType.COMPLEX);
            }
        }
        else {
            meterClassificationTypes.add(MeterClassificationType.ODD);
            if (numerator > 1) {
                meterClassificationTypes.add(MeterClassificationType.COMPLEX);
                meterClassificationTypes.add(MeterClassificationType.IRREGULAR);
                meterClassificationTypes.add(MeterClassificationType.ASYMMETRICAL);
            }
        }
    }
}
