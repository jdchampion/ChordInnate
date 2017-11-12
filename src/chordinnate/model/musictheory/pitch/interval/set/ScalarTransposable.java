package chordinnate.model.musictheory.pitch.interval.set;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
interface ScalarTransposable<T> extends TransposableIntervalSet {
    T transposeScalarBy(@NotNull Scale scale);
}
