package chordinnate.musictheory;

import com.sun.istack.internal.NotNull;

/**
 * Created by Joseph on 4/15/16.
 */
interface Parallel<T> {
    boolean isParallelMajorTo(@NotNull T other);
    boolean isParallelMinorTo(@NotNull T other);
    @NotNull T getParallelMajor();
    @NotNull T getParallelMinor();
}
