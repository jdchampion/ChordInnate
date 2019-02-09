package chordinnate.model.musictheory.time.meter.fixed.complete;

import chordinnate.model.musictheory.time.meter.MeterClassificationType;
import chordinnate.model.musictheory.time.meter.MeterSubdivision;
import chordinnate.model.musictheory.time.meter.fixed.FixedMeter;
import chordinnate.model.musictheory.time.rhythm.Duration;

import java.util.ArrayList;
import java.util.List;

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

    CompleteMeter() {

    }

    CompleteMeter(int numerator, Duration denominator, MeterSubdivision[] subdivisions, boolean verifyMatch, boolean inferSubdivisions) {

        if (verifyMatch) {
            verifyMatch(numerator, subdivisions);
        }

        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = this.numerator * denominator.ratio;
        this.subdivisions = subdivisions;

        inferCommonMeterClassifications();

        /*
         * If no subdivision pattern has been defined for this Meter,
         * then we have to make one up based on conventions.
         */
        if (inferSubdivisions) {
            inferSubdivisions();
        }

    }

    /**
     * This method is intended for validating CompleteMeters at point of construction.
     * This validation is only necessary for CompleteMeter constructors that pass a
     * MeterSubdivision[] argument to this.subdivisions.
     * This method will check whether the summation of the subdivision pattern matches the numerator.
     * If not, an IllegalArgumentException is thrown.
     * @param numerator
     * @param subdivisions
     */
    private void verifyMatch(int numerator, MeterSubdivision[] subdivisions) {
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
    private int sumAll(MeterSubdivision[] subdivisions) {
        int sum = 0;
        for (MeterSubdivision subdivision : subdivisions) {
            sum += subdivision.grouping;
        }
        return sum;
    }

    /**
     * A logical sequence for classifying CompleteMeters by their common properties.
     */
    private void inferCommonMeterClassifications() {
        boolean div2 = numerator % 2 == 0;
        boolean div3 = numerator % 3 == 0;

        if (div2 || div3) {
            meterClassificationTypes.add(MeterClassificationType.MULTPLICATIVE);
            meterClassificationTypes.add(div2 ? MeterClassificationType.IMPERFECT : MeterClassificationType.ODD);
            if (numerator < 6) {
                meterClassificationTypes.add(MeterClassificationType.SIMPLE);
            } else if (div3) {
                meterClassificationTypes.add(MeterClassificationType.PERFECT);
                if (numerator > 3) {
                    meterClassificationTypes.add(MeterClassificationType.COMPOUND);
                }
            } else {
                // NOTE: additive cannot be evaluated from here. Do so in AdditiveCompleteMeter.
                // numerator >= 5 && !div3
                meterClassificationTypes.add(MeterClassificationType.COMPLEX);
            }
        } else {
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
    private void inferSubdivisions() {
        if (this.numerator == 1) {
            return;
        }

        if (this.numerator % 4 == 0) {
            groupBy(MeterSubdivision.QUADRUPLE);
        } else if (this.numerator % 3 == 0) {
            groupBy(MeterSubdivision.TRIPLE);
        } else if (this.numerator % 2 == 0) {
            groupBy(MeterSubdivision.DUPLE);
        } else {
            if (this.numerator == 5) {
                /*
                 * Meters with a numerator of 5 are most often grouped as (3 + 2), so we'll use that convention.
                 */
                this.subdivisions = new MeterSubdivision[]{MeterSubdivision.TRIPLE, MeterSubdivision.DUPLE};
                return;
            }

            inferSubdivisionsHelper();
        }
    }

    private void inferSubdivisionsHelper() {
        /*
         * Here's where we have to adopt our own convention, based on the numerator:
         * 1. "Front-load" the pattern with N * (3 + 2) groupings,
         *     such that numerator - (N * (3 + 2)) != 1.
         *
         * 2. Afterwards, fill remaining index/indices of the pattern with 2, 3, or (2 + 2).
         *
         * Example: 11/16 --> (3 + 2) + 3 + 3
         */
        int divisor = this.numerator / 5;
        int maxIndexesFilled = divisor * 5;
        int lookAheadIndex = this.numerator - 6;
        int actualIndexesFilled = 0;
        List<MeterSubdivision> meterSubdivisions = new ArrayList<>();
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
        } else {
            for (; actualIndexesFilled < this.numerator; actualIndexesFilled += 2) {
                meterSubdivisions.add(MeterSubdivision.DUPLE);
            }
        }
        this.subdivisions = meterSubdivisions.toArray(new MeterSubdivision[meterSubdivisions.size()]);
    }

    /**
     * Helper method for inferSubdivisions()
     * @param meterSubdivision
     */
    private void groupBy(MeterSubdivision meterSubdivision) {
        int factor = this.numerator / meterSubdivision.grouping;
        this.subdivisions = new MeterSubdivision[factor];
        for (int i = 0; i < factor; i++) {
            this.subdivisions[i] = meterSubdivision;
        }
        if (isType(MeterClassificationType.SIMPLE)) {
            meterClassificationTypes.add(MeterClassificationType.valueOf("SIMPLE_"+meterSubdivision));
        } else {
            meterClassificationTypes.add(MeterClassificationType.valueOf("COMPOUND_"+meterSubdivision));
        }
    }

    public MeterSubdivision[] getSubdivisions() {
        return subdivisions;
    }
}
