package chordinnate.musictheory.pitch.interval;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Joseph on 7/3/16.
 */
public enum PitchIntervalQuality {
    MAJOR("M", ""),
    MINOR("m", ""),
    PERFECT("P", ""),
    AUGMENTED("A", "+"),
    DIMINISHED("d", "˚");

    public final String SHORT_NAME_SYMBOL, ROMAN_NUMERAL_SYMBOL;

    private static final Map<PitchIntervalQuality, PitchIntervalQuality> INVERSIONS = new EnumMap<>(PitchIntervalQuality.class);
        static {
            INVERSIONS.put(MAJOR, MINOR);
            INVERSIONS.put(MINOR, MAJOR);
            INVERSIONS.put(PERFECT, PERFECT);
            INVERSIONS.put(AUGMENTED, DIMINISHED);
            INVERSIONS.put(DIMINISHED, AUGMENTED);
        }

    PitchIntervalQuality(String shortNameSymbol, String romanNumeralSymbol) {
        this.SHORT_NAME_SYMBOL = shortNameSymbol;
        this.ROMAN_NUMERAL_SYMBOL = romanNumeralSymbol;
    }

    public PitchIntervalQuality getInversion() {
        return INVERSIONS.get(this);
    }
}
