package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.interval.set.Scale;
import chordinnate.musictheory.pitch.interval.set.ScaleType;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
public interface ScalarTransposable<T> extends Transposable {
    T transposeScalarBy(@NotNull EnharmonicSpelling root, @NotNull ScaleType scaleType);
    T transposeScalarBy(@NotNull PitchClass root, @NotNull ScaleType scaleType);
    T transposeScalarBy(@NotNull Scale scale);
}
