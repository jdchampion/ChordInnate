package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.Transposable;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import org.jetbrains.annotations.NotNull;

interface TransposableIntervalSet extends Transposable {
    /**
     * Performs a transposition to the specified PitchInterval in the specified direction,
     * with side effects to the IntervalSet.
     * @param pitchInterval the desired PitchInterval to transpose to
     * @param direction the direction of transposition (true = up; false = down)
     */
    void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);

    /**
     * Performs a transposition to the specified PitchClass,
     * with side effects to the IntervalSet.
     * @param pitchClass the desired PitchClass to transpose to
     */
    void transposeTo(@NotNull PitchClass pitchClass);
}
