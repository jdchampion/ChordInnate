package chordinnate.musictheory.pitch.interval;

/**
 * Created by Joseph on 8/16/16.
 */
public enum Degree {
    TONIC(RomanNumeral.I, RomanNumeral.i),
    SUPERTONIC(RomanNumeral.II, RomanNumeral.ii),
    MEDIANT(RomanNumeral.III, RomanNumeral.iii),
    SUBDOMINANT(RomanNumeral.IV, RomanNumeral.iv),
    DOMINANT(RomanNumeral.V, RomanNumeral.v),
    SUBMEDIANT(RomanNumeral.VI, RomanNumeral.vi),
    LEADING_TONE(RomanNumeral.VII, RomanNumeral.vii),

    ;

    public RomanNumeral MAJOR_SYMBOL, MINOR_SYMBOL;

    Degree(RomanNumeral majorSymbol, RomanNumeral minorSymbol) {
        this.MAJOR_SYMBOL = majorSymbol;
        this.MINOR_SYMBOL = minorSymbol;
    }
}
