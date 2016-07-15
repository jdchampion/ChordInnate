package chordinnate.musictheory.general;

import chordinnate.util.Sequential;
import chordinnate.util.SequentialUtil;

/**
 * Created by Joseph on 4/14/16.
 */
public enum Accidental implements Sequential {
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

    @SuppressWarnings("unchecked")
    @Override
    public Accidental getNext() {
        return (Accidental) SequentialUtil.getNext(this, Accidental.values());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Accidental getPrevious() {
        return (Accidental) SequentialUtil.getPrevious(this, Accidental.values());
    }
}
