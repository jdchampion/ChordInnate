package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;

import java.util.LinkedList;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * NOTE: the time signature denominator MUST be a dyadic rational (i.e., a power of 2).
 *
 * Created by Joseph on 7/3/16.
 */
abstract class CompleteMeter extends FixedMeter {
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
        int sum = sumAll(subdivisions);
        if (sum != numerator) {
            if (numerator == 1 && sum == 0) return; // Meter with 1 as its numerator cannot be subdivided
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

    /**
     * Constructor helper method to infer the subdivision groupings for the Meter.
     */
    void inferSubdivisions() {
        if (this.numerator % 4 == 0) {
            groupBy(MeterSubdivision.QUADRUPLE);
        }
        else if (this.numerator % 3 == 0) {
            groupBy(MeterSubdivision.TRIPLE);
        }
        else if (this.numerator % 2 == 0) {
            groupBy(MeterSubdivision.DUPLE);
        }
        else {
            if (this.numerator == 5) {
                /*
                 * Meters with a numerator of 5 are most often grouped as (3 + 2), so we'll use that convention.
                 */
                this.subdivisions = new MeterSubdivision[]{MeterSubdivision.TRIPLE, MeterSubdivision.DUPLE};
                return;
            }
            else if (this.numerator == 1) {
                return;
            }

            /*
             * Here's where we have to adopt our own convention, based on the numerator:
             * 1. "Front-load" the pattern with N * (3 + 2) groupings,
             *     such that numerator - (N * (3 + 2)) != 1.
             *
             * 2. Afterwards, fill remaining index/indices of the pattern with 2, 3, or (2 + 2).
             *
             * Example: 11/16 --> (3 + 2) + 3 + 3
             */
            int
                    divisor = this.numerator / 5,
                    maxIndexesFilled = divisor * 5,
                    lookAheadIndex = this.numerator - 6,
                    actualIndexesFilled = 0;
            LinkedList<MeterSubdivision> meterSubdivisions = new LinkedList<>();
            // Fill the max amount of groupings with (3 + 2)
            for (; actualIndexesFilled < maxIndexesFilled
                    && (actualIndexesFilled < lookAheadIndex); actualIndexesFilled += 5) {
                meterSubdivisions.add(MeterSubdivision.TRIPLE);
                meterSubdivisions.add(MeterSubdivision.DUPLE);
            }
            // Fill in the remaining groupings with a triple (3) or a duple (2)
            int indexesLeft = this.numerator - actualIndexesFilled;
            if (indexesLeft % 3 == 0) {
                for (; actualIndexesFilled < this.numerator; actualIndexesFilled += 3) {
                    meterSubdivisions.add(MeterSubdivision.TRIPLE);
                }
            }
            else {
                for (; actualIndexesFilled < this.numerator; actualIndexesFilled += 2) {
                    meterSubdivisions.add(MeterSubdivision.DUPLE);
                }
            }
            this.subdivisions = meterSubdivisions.toArray(new MeterSubdivision[meterSubdivisions.size()]);
        }
    }

    /**
     * Helper method for inferSubdivisions()
     * @param meterSubdivision
     */
    private void groupBy(MeterSubdivision meterSubdivision) {
        int factor = this.numerator / meterSubdivision.GROUPING;
        this.subdivisions = new MeterSubdivision[factor];
        for (int i = 0; i < factor; i++) {
            this.subdivisions[i] = meterSubdivision;
        }
        if (is(MeterClassificationType.SIMPLE)) {
            meterClassificationTypes.add(MeterClassificationType.valueOf("SIMPLE_"+meterSubdivision));
        }
        else {
            meterClassificationTypes.add(MeterClassificationType.valueOf("COMPOUND_"+meterSubdivision));
        }
    }
}
