package chordinnate.model.musictheory.temporal.meter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface Metered {

    List<TimeSignature> getAllTimeSignatures();

    default Set<MeterType> getMeterTypes() {
        return Collections.unmodifiableSet(MeterType.classify(this));
    }

    default boolean isMeterType(MeterType... meterTypes) {
        return getMeterTypes().containsAll(Arrays.asList(meterTypes));
    }
}
