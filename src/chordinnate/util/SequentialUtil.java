package chordinnate.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SequentialUtil {
    private SequentialUtil() {}

    @Nullable
    public static Enum<?> getNext(@NotNull Enum<?> type, @NotNull Enum<?>[] values) {
        int ordinal = type.ordinal();
        return ordinal < values.length - 1
                ? values[ordinal + 1]
                : null;
    }

    @Nullable
    public static Enum<?> getPrevious(@NotNull Enum<?> type, @NotNull Enum<?>[] values) {
        int ordinal = type.ordinal();
        return ordinal > 0
                ? values[ordinal - 1]
                : null;
    }

    @Nullable
    public static <T> T get(int index, @NotNull T[] values) {
        return (index >= 0 && index <= values.length - 1)
                ? values[index]
                : null;
    }
}
