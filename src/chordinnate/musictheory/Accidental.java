package chordinnate.musictheory;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Joseph on 4/14/16.
 */
public enum Accidental {
    DOUBLE_FLAT("\uD834\uDD2B", -2),
    FLAT("\u266d", -1),
    NATURAL("\u266e", 0),
    NONE("", 0),
    SHARP("\u266f", 1),
    DOUBLE_SHARP("\uD834\uDD2A", 2);

    private final String SYMBOL;
    private final int SEMITONE_MODIFIER;

    private static final Accidental[] VALUES = Accidental.values();

    Accidental(String symbol, int semitoneModifier) {
        this.SYMBOL = symbol;
        this.SEMITONE_MODIFIER = semitoneModifier;
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public int getSemitoneModifier() {
        return SEMITONE_MODIFIER;
    }

    @Nullable
    public Accidental getNext() {
        int ordinal = this.ordinal();
        return ordinal < VALUES.length - 1
                ? VALUES[ordinal + 1]
                : null;
    }

    @Nullable
    public Accidental getPrevious() {
        int ordinal = this.ordinal();
        return ordinal > 0
                ? VALUES[ordinal - 1]
                : null;
    }
}
