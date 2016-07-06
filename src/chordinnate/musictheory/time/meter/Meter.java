package chordinnate.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    HashSet<MeterClassificationType> meterClassificationTypes;
    double measureDuration;

    Meter() {
        this.meterClassificationTypes = new HashSet<>();
    }

    public boolean is(@NotNull MeterClassificationType meterClassificationType) {
        return meterClassificationTypes.contains(meterClassificationType);
    }
}
