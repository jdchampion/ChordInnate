package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;

interface IntervalTransposable<T> {

    default boolean isTransposable(boolean direction, @NotNull Interval interval) {
        return true;
    }

    T transpose(boolean direction, @NotNull Interval interval);

}
