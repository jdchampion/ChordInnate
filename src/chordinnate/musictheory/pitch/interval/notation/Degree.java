package chordinnate.musictheory.pitch.interval.notation;

import static chordinnate.musictheory.pitch.interval.notation.RomanNumeral.*;

/**
 * Created by Joseph on 8/16/16.
 */
public enum Degree {
    TONIC(I, i),
    SUPERTONIC(II, ii),
    MEDIANT(III, iii),
    SUBDOMINANT(IV, iv),
    DOMINANT(V, v),
    SUBMEDIANT(VI, vi),
    LEADING_TONE(VII, vii),

    ;

    public RomanNumeral MAJOR_SYMBOL, MINOR_SYMBOL;

    Degree(RomanNumeral majorSymbol, RomanNumeral minorSymbol) {
        this.MAJOR_SYMBOL = majorSymbol;
        this.MINOR_SYMBOL = minorSymbol;
    }
}
