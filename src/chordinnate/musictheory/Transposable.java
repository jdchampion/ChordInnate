package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
interface Transposable<T> {
    boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction);
    @NotNull T transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);
    boolean isTransposableTo(@NotNull Octave octave);
    @NotNull T transposeTo(@NotNull Octave octave);
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    @NotNull T transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    boolean isTransposableTo(@NotNull Pitch pitch);
    @NotNull T transposeTo(@NotNull Pitch pitch);
}

interface TransposableIntervalSet<T> extends Transposable {
    @NotNull T transposeChromaticBy(@NotNull PitchInterval pitchInterval, boolean direction);
    @NotNull T transposeScalarBy(@NotNull PitchInterval pitchInterval, boolean direction);
}
