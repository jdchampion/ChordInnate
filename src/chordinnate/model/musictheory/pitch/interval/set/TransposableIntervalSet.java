package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.Transposable;
import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;

interface TransposableIntervalSet extends Transposable {
    /**
     * Performs a transposition to the specified Interval,
     * with side effects to the IntervalSet.
     * @param interval the desired Interval to transpose to
     */
    void transposeTo(@NotNull Interval interval, boolean direction);

    /**
     * Performs a transposition to the specified PitchClass,
     * with side effects to the IntervalSet.
     * @param pitchClass the desired PitchClass to transpose to
     */
    void transposeTo(@NotNull PitchClass pitchClass);
}
