package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

/**
 * All meters that maintain the same measurement of time.
 *
 * Created by Joseph on 7/3/16.
 */
public abstract class FixedMeter extends Meter {
    private Number numerator;
    private Number denominator;
    private double measureDuration;

    FixedMeter(@NotNull Number numerator, @NotNull Number denominator) {
        if (!hasValidArguments(numerator, denominator)) {
            throw new IllegalArgumentException("Invalid numerator or denominator.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = (numerator.doubleValue() * denominator.doubleValue());
        inferMeterClassifications();
    }

    abstract boolean hasValidArguments(Number numerator, Number denominator);

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
