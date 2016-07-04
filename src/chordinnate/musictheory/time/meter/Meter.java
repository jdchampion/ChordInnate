package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    HashSet<MeterClassificationType> meterClassificationTypes;
    Number[] numerator;
    Number denominator;
    double measureDuration;

    Meter() {
        this.meterClassificationTypes = new HashSet<>();
    }

    public boolean is(@NotNull MeterClassificationType meterClassificationType) {
        return meterClassificationTypes.contains(meterClassificationType);
    }

    /**
     * Determines whether this Meter is additive, and if so, classifies it as such.
     */
    void inferAdditiveMeter() {
        if (numerator != null) {
            /*
             * If the array size for "numerator" > 1,
             * we'll consider the Meter to be additive.
             */
            if (numerator.length > 1) meterClassificationTypes.add(MeterClassificationType.ADDITIVE);
        }
    }

    public boolean toggleAdditiveMeterClassification() {
        MeterClassificationType additive = MeterClassificationType.ADDITIVE;
        if (is(additive)) {
            meterClassificationTypes.remove(additive);
            return false;
        }
        else {
            inferAdditiveMeter();
            return is(MeterClassificationType.ADDITIVE);
        }
    }

    /**
     * Determines whether the constructor arguments were valid to construct this
     * type of Meter when they were passed to it.
     * @param numerator
     * @param denominator
     * @return true if valid, false otherwise
     */
    abstract boolean hasValidArguments(Number[] numerator, Number denominator);

    /**
     * Gives back a new Meter based on the current Meter's denominator,
     * thereby avoiding potential side effects.
     * @param subdivisionPattern the new subdivision pattern.
     * @return a new Meter with the provided subdivision pattern.
     */
    public Meter setSubdivisionPattern(Number... subdivisionPattern) {
        /*
         * Rather than loading this function up with the logic necessary
         * to update the current Meter object (error-prone),
         * we can instead simply start back at the constructor level
         * and return a new Meter object.
         */
        if (hasValidArguments(subdivisionPattern, denominator)) {
            return new ProperMeter(denominator, subdivisionPattern);
        }
        else {
            return new ImproperMeter(denominator, numerator);
        }
    }
}
