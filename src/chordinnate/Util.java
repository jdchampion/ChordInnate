package chordinnate;

import org.jetbrains.annotations.Nullable;

public final class Util {
    @Nullable
    public static Enum<?> getNext(Enum<?> type, Enum<?>[] values) {
        int ordinal = type.ordinal();
        return ordinal < values.length - 1
                ? values[ordinal + 1]
                : null;
    }

    @Nullable
    public static Enum<?> getPrevious(Enum<?> type, Enum<?>[] values) {
        int ordinal = type.ordinal();
        return ordinal > 0
                ? values[ordinal - 1]
                : null;
    }

    @Nullable
    public static <T> T get(int index, T[] values) {
        return (index >= 0 && index <= values.length - 1)
                ? values[index]
                : null;
    }
}
