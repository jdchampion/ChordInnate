package chordinnate.model.musictheory.time.meter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class Meter {
    protected HashSet<MeterType> meterTypes;
    protected double measureDuration;

    protected Meter() {
        this.meterTypes = new HashSet<>();
    }

    public boolean isType(@NotNull MeterType meterType) {
        return meterTypes.contains(meterType);
    }
}
