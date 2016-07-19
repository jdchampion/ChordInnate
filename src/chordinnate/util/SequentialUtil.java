package chordinnate.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SequentialUtil {
    private SequentialUtil() {}

    public static boolean hasNext(@NotNull Enum<?> type) {
        Enum<?>[] values = type.getClass().getEnumConstants();
        return type.ordinal() < values.length - 1;
    }

    public static boolean hasPrevious(@NotNull Enum<?> type) {
        return type.ordinal() > 0;
    }

    @Nullable
    public static Enum<?> getNext(@NotNull Enum<?> type) {
        Enum<?>[] values = type.getClass().getEnumConstants();
        int ordinal = type.ordinal();
        return ordinal < values.length - 1
                ? values[ordinal + 1]
                : null;
    }

    @Nullable
    public static Enum<?> getPrevious(@NotNull Enum<?> type) {
        Enum<?>[] values = type.getClass().getEnumConstants();
        int ordinal = type.ordinal();
        return ordinal > 0
                ? values[ordinal - 1]
                : null;
    }
}
