package chordinnate.model.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    protected HashSet<MeterClassificationType> meterClassificationTypes;
    protected double measureDuration;

    protected Meter() {
        this.meterClassificationTypes = new HashSet<>();
    }

    public boolean isType(@NotNull MeterClassificationType meterClassificationType) {
        return meterClassificationTypes.contains(meterClassificationType);
    }
}
