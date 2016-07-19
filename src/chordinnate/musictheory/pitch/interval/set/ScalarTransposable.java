package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
interface ScalarTransposable<T> extends TransposableIntervalSet {
    T transposeScalarBy(@NotNull EnharmonicSpelling root, @NotNull ScaleType scaleType);
    T transposeScalarBy(@NotNull PitchClass root, @NotNull ScaleType scaleType);
    T transposeScalarBy(@NotNull Scale scale);
}
