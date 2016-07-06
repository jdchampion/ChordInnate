package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * CompleteMeters whose time signature numerator is not displayed as a summation of integers.
 * This represents the most commonly-used time signatures.
 *
 * Created by Joseph on 7/5/16.
 */
public final class NonAdditiveCompleteMeter extends CompleteMeter {
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
         * Yes, it's as ugly as it looks.
         */
        if (this.numerator % 4 == 0) {
            groupBy(MeterSubdivision.QUADRUPLE);
        }
        else if (this.numerator % 3 == 0) {
            groupBy(MeterSubdivision.TRIPLE);
        }
        else if (this.numerator % 2 == 0) {
            groupBy(MeterSubdivision.DUPLE);
        }
        else if (is(MeterClassificationType.COMPLEX)) {
            if (this.numerator == 5) {
                /*
                 * Meters with a numerator of 5 are most often grouped as (3 + 2), so we'll use that convention.
                 */
                this.subdivisions = new MeterSubdivision[]{MeterSubdivision.TRIPLE, MeterSubdivision.DUPLE};
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

    /**
     * Constructor helper method to infer the subdivision groupings for the Meter.
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
