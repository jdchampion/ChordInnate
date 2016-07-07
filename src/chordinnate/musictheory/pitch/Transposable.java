package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
interface Transposable<T> {
    boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction);
    T transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);
    boolean isTransposableTo(@NotNull Octave octave);
    T transposeTo(@NotNull Octave octave);
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    T transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    boolean isTransposableTo(@NotNull Pitch pitch);
    T transposeTo(@NotNull Pitch pitch);
}

interface TransposableIntervalSet<T> extends Transposable {
    T transposeChromaticBy(@NotNull PitchInterval pitchInterval, boolean direction);
    T transposeScalarBy(@NotNull PitchInterval pitchInterval, boolean direction);
}
