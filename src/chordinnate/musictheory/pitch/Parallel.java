package chordinnate.musictheory.pitch;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/15/16.
 */
public interface Parallel<T> {
    boolean isParallelMajorTo(@NotNull T other);
    boolean isParallelMinorTo(@NotNull T other);
    T getParallelMajor();
    T getParallelMinor();
}
