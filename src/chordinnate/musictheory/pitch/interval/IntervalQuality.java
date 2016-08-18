package chordinnate.musictheory.pitch.interval;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/3/16.
 */
public enum IntervalQuality {
    MAJOR("M", "", 0, Pattern.compile("M([aA][jJ])?\\.?|([mM][aA][jJ])\\.?([oO][rR])?")),
    MINOR("m", "", -1, Pattern.compile("m(([iI][nN])?\\.?)|((M)(([iI][nN])?\\.?)([oO][rR])?)|(([mM][iI][nN])([oO][rR])?)")),
    PERFECT("P", "", 0, Pattern.compile("([pP](\\.|(([eE][rR][fF])\\.?([eE][cC][tT])?))?)")),
    AUGMENTED("A", "+", 1, Pattern.compile("(([aA]([uU][gG])?)\\.?([mM][eE][nN][tT][eE][dD])?)")),
    DIMINISHED("d", "˚", -2, Pattern.compile("(([dD]([iI][mM])?)\\.?([iI][nN][iI][sS][hH][eE][dD])?)")),

    ;

    public final String SHORT_NAME_SYMBOL, ROMAN_NUMERAL_APPENDED_SYMBOL;
    private final Pattern ALIASES;
    public final int SEMITONE_MODIFIER;

    private static final Map<IntervalQuality, IntervalQuality> INVERSIONS = new EnumMap<>(IntervalQuality.class);
    static {
        INVERSIONS.put(MAJOR, MINOR);
        INVERSIONS.put(MINOR, MAJOR);
        INVERSIONS.put(PERFECT, PERFECT);
        INVERSIONS.put(AUGMENTED, DIMINISHED);
        INVERSIONS.put(DIMINISHED, AUGMENTED);
    }

    IntervalQuality(String shortNameSymbol, String romanNumeralSymbol, int semitoneModifier, Pattern aliases) {
        this.SHORT_NAME_SYMBOL = shortNameSymbol;
        this.ROMAN_NUMERAL_APPENDED_SYMBOL = romanNumeralSymbol;
        this.SEMITONE_MODIFIER = semitoneModifier;
        this.ALIASES = aliases;
    }

    static IntervalQuality getIntervalQuality(String s) {
        for (IntervalQuality i : IntervalQuality.values()) {
            if (i.ALIASES.matcher(s).matches()) return i;
        }
        return null;
    }

    static boolean isValid(IntervalQuality intervalQuality, int diatonic) {
        if (intervalQuality == null) return false;
        if (intervalQuality.equals(PERFECT)) {
            return diatonic == 1 || diatonic == 4 || diatonic == 5;
        }
        else if (intervalQuality.equals(MAJOR) || intervalQuality.equals(MINOR)) {
            return diatonic == 2 || diatonic == 3 || diatonic == 6 || diatonic == 7;
        }
        else return intervalQuality.equals(DIMINISHED) || intervalQuality.equals(AUGMENTED);
    }

    public IntervalQuality getInversion() {
        return INVERSIONS.get(this);
    }
}