package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * All meters that maintain the same measurement of time.
 *
 * Created by Joseph on 7/3/16.
 */
public abstract class FixedMeter extends Meter {


    /**
     * Constructs a Meter whose time measurement remains constant.
     * @param denominator the denominator for the time signature of this Meter.
     *                    This represents the nominal Beat to measure time by.
     * @param numerator the numerator for the time signature of this Meter.
     *                  The summation of all varargs represents the number of Beats per Measure,
     *                  while doubling as the subdivision pattern for the Meter.
     */
    FixedMeter(@NotNull Number denominator, @NotNull Number... numerator) {
        if (!hasValidArguments(numerator, denominator)) {
            throw new IllegalArgumentException("Invalid numerator or denominator.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = (sumAll(numerator) * denominator.doubleValue());
        inferMeterClassifications();
    }

    double sumAll(Number... numbers) {
        double sum = 0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * Determines whether this Meter is irrational, and if so, classifies it as such.
     */
    void inferIrrationalMeter() {
        /*
         * Meters are considered irrational if their denominator is a power of 2.
         */
        double d_ln_Denom = Math.log(denominator.doubleValue()) / Math.log(2);
        if (Math.ceil(d_ln_Denom) != Math.floor(d_ln_Denom)) {
            meterClassificationTypes.add(MeterClassificationType.IRRATIONAL);
        }
    }

    /**
     * Determines whether this Meter is asymmetrical, and if so, classifies it as such.
     */
    void inferAsymmetricalMeter() {
        double num = sumAll(numerator);
        // TODO: check logic
        if ((num % 2 == 0) == (num % 3 == 0) && num > 6) {
            meterClassificationTypes.add(MeterClassificationType.ASYMMETRICAL);
        }
    }



    /**
     * Determines and classifies this Meter into musical terms, based on its current properties.
     */
    abstract void inferMeterClassifications();

//    public abstract void toggleAdditiveMeterClassification();

    /**
     * Gives back a new Meter based on the current Meter's denominator,
     * thereby avoiding potential side effects.
     * @param subdivisionPattern the new subdivision pattern.
     * @return a new Meter with the provided subdivision pattern.
     */
//    public abstract Meter setSubdivisionPattern(Number... subdivisionPattern);
}
