package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
interface TransposableEnum<T extends Enum<T>> extends Transposable {
    boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction);
    Enum<T> transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);
    boolean isTransposableTo(@NotNull Octave octave);
    Enum<T> transposeTo(@NotNull Octave octave);
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    Enum<T> transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    boolean isTransposableTo(@NotNull Pitch pitch);
    Enum<T> transposeTo(@NotNull Pitch pitch);
}
