package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Beat;
import org.jetbrains.annotations.NotNull;

/**
 * Meters whose time signature uses exclusively integers as its measurement of time.
 * This represents the most common time signatures.
 *
 * Created by Joseph on 7/3/16.
 */
public class ProperMeter extends Meter {
    public ProperMeter(int numerator, @NotNull Beat denominator) {

    }

    public ProperMeter(int numerator, @NotNull Beat denominator, @NotNull int... stressPattern) {

    }

    public ProperMeter(int numerator, @NotNull Beat denominator, @NotNull boolean... stressPattern) {

    }

    public void setStressPattern(@NotNull int... stressPattern) {

    }

    public void setStressPattern(@NotNull boolean... stressPattern) {

    }

    public void toggleAdditiveProperty() {
        if (is(MeterProperty.ADDITIVE)) {
            meterProperties.remove(MeterProperty.ADDITIVE);
        }
        else {
            meterProperties.add(MeterProperty.ADDITIVE);
        }
    }
}
