package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Transposable<T> extends IntervalTransposable<T> {

    default boolean isTransposable(@NotNull IntervalDirection direction, @NotNull PitchClass pitchClass) {
        return true;
    }

    default boolean isTransposable(@NotNull Pitch pitch) {
        return true;
    }

    default boolean isTransposable(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return true;
    }

    T transpose(@NotNull IntervalDirection direction, @NotNull PitchClass pitchClass);

    T transpose(@NotNull Pitch pitch);

    T transpose(@NotNull PitchClass pitchClass, @NotNull Octave octave);

}