package chordinnate.musictheory.pitch;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Relative<T> {
    boolean isRelativeMajorTo(@NotNull T other);
    boolean isRelativeMinorTo(@NotNull T other);
    T getRelativeMajor();
    T getRelativeMinor();
}
