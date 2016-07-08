package chordinnate.musictheory.general;

import chordinnate.SequentiallyOrdered;
import chordinnate.Util;

/**
 * Created by Joseph on 4/14/16.
 */
public enum Accidental implements SequentiallyOrdered {
    DOUBLE_FLAT("\uD834\uDD2B", -2),
    FLAT("\u266d", -1),
    NATURAL("\u266e", 0),
    NONE("", 0),
    SHARP("\u266f", 1),
    DOUBLE_SHARP("\uD834\uDD2A", 2);

    public final String SYMBOL;
    public final int SEMITONE_MODIFIER;

    Accidental(String symbol, int semitoneModifier) {
        this.SYMBOL = symbol;
        this.SEMITONE_MODIFIER = semitoneModifier;
    }

    @Override
    public Accidental getNext() {
        return (Accidental) Util.getNext(this, Accidental.values());
    }

    @Override
    public Accidental getPrevious() {
        return (Accidental) Util.getPrevious(this, Accidental.values());
    }
}
