package chordinnate.musictheory.pitch.interval;

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

    final String SHORT_NAME_SYMBOL, ROMAN_NUMERAL_APPENDED_SYMBOL;
    final int SEMITONE_MODIFIER;
    private final Pattern ALIASES;

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
}