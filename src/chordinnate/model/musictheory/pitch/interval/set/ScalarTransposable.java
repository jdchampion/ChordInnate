package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Transposable;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
interface ScalarTransposable<T> extends Transposable<T> {

    boolean isTransposable(@NotNull Scale scale);

    T transpose(@NotNull Scale scale);
}
