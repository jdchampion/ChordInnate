package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/15/16.
 */
interface Parallel<T> {
    boolean isParallelMajorTo(@NotNull T other);
    boolean isParallelMinorTo(@NotNull T other);
    @NotNull T getParallelMajor();
    @NotNull T getParallelMinor();
}
