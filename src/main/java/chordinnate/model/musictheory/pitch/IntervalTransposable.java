package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;

interface IntervalTransposable<T> {

    boolean isTransposable(boolean direction, @NotNull Interval interval);

    T transpose(boolean direction, @NotNull Interval interval);

}
