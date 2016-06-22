package chordinnate.musictheory;

import com.sun.istack.internal.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
interface Relative<T> {
    boolean isRelativeMajorTo(@NotNull T other);
    boolean isRelativeMinorTo(@NotNull T other);
    @NotNull T getRelativeMajor();
    @NotNull T getRelativeMinor();
}
