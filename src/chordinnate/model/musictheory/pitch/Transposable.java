package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Transposable<T> {

    boolean isTransposable(boolean direction, @NotNull Interval interval);

    boolean isTransposable(boolean direction, @NotNull PitchClass pitchClass);

    boolean isTransposable(@NotNull Pitch pitch);

    boolean isTransposable(@NotNull PitchClass pitchClass, @NotNull Octave octave);

    T transpose(boolean direction, @NotNull Interval interval);

    T transpose(boolean direction, @NotNull PitchClass pitchClass);

    T transpose(@NotNull Pitch pitch);

    T transpose(@NotNull PitchClass pitchClass, @NotNull Octave octave);

}