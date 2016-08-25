package chordinnate.musictheory.pitch.interval;

import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/3/16.
 */
public enum IntervalQuality {
    MAJOR("M", "", Pattern.compile("M([aA][jJ])?\\.?|([mM][aA][jJ])\\.?([oO][rR])?")),
    MINOR("m", "", Pattern.compile("m(([iI][nN])?\\.?)|((M)(([iI][nN])?\\.?)([oO][rR])?)|(([mM][iI][nN])([oO][rR])?)")),
    PERFECT("P", "", Pattern.compile("([pP](\\.|(([eE][rR][fF])\\.?([eE][cC][tT])?))?)")),
    AUGMENTED("A", "+", Pattern.compile("(([aA]([uU][gG])?)\\.?([mM][eE][nN][tT][eE][dD])?)")),
    DIMINISHED("d", "˚", Pattern.compile("(([dD]([iI][mM])?)\\.?([iI][nN][iI][sS][hH][eE][dD])?)")),

    ;

    final String SHORT_NAME_SYMBOL, ROMAN_NUMERAL_APPENDED_SYMBOL;
    private final Pattern ALIASES;

    IntervalQuality(String shortNameSymbol, String romanNumeralSymbol, Pattern aliases) {
        this.SHORT_NAME_SYMBOL = shortNameSymbol;
        this.ROMAN_NUMERAL_APPENDED_SYMBOL = romanNumeralSymbol;
        this.ALIASES = aliases;
    }

    static IntervalQuality getIntervalQuality(String s) {
        for (IntervalQuality i : IntervalQuality.values()) {
            if (i.ALIASES.matcher(s).matches()) return i;
        }
        return null;
    }
}