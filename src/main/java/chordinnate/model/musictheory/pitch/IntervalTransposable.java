package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
import org.jetbrains.annotations.NotNull;

interface IntervalTransposable<T> {

    default boolean isTransposable(@NotNull IntervalDirection direction, @NotNull Interval interval) {
        return direction.getCompareTo() != 0;
    }

    T transpose(@NotNull IntervalDirection direction, @NotNull Interval interval);

}
