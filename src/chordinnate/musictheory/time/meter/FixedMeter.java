package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * All meters that maintain the same measurement of time.
 *
 * Created by Joseph on 7/3/16.
 */
public abstract class FixedMeter extends Meter {
    private Number[] numerator;
    private Number denominator;
    private double measureDuration;

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
        this.measureDuration = (numeratorSum() * denominator.doubleValue());
        inferMeterClassifications();
    }

    double numeratorSum() {
        double sum = 0;
        for (Number number : numerator) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * Determines whether the constructor arguments were valid to construct this
     * type of Meter when they were passed to it.
     * @param numerator
     * @param denominator
     * @return
     */
    abstract boolean hasValidArguments(Number[] numerator, Number denominator);

    abstract void inferMeterClassifications();

    public void toggleAdditiveMeterClassification() {
        MeterClassificationType additive = MeterClassificationType.ADDITIVE;
        if (is(additive)) {
            meterClassificationTypes.remove(additive);
        }
        else {
            meterClassificationTypes.add(additive);
        }
    }
}
