package chordinnate.musictheory.pitch.interval;


import java.util.EnumMap;

import static chordinnate.musictheory.pitch.interval.Degree.*;

/**
 * Created by Joseph on 8/16/16.
 */
enum GeneralizedInterval {
    ONE(1, TONIC, 0),
    TWO(2, SUPERTONIC, 2),
    THREE(3, MEDIANT, 4),
    FOUR(4, SUBDOMINANT, 5),
    FIVE(5, DOMINANT, 7),
    SIX(6, SUBMEDIANT, 9),
    SEVEN(7, LEADING_TONE, 11),

    ;

    private static final EnumMap<GeneralizedInterval, GeneralizedInterval> INVERSIONS = new EnumMap<>(GeneralizedInterval.class);
    static {
        INVERSIONS.put(ONE, ONE);
        INVERSIONS.put(TWO, SEVEN);
        INVERSIONS.put(SEVEN, TWO);
        INVERSIONS.put(THREE, SIX);
        INVERSIONS.put(SIX, THREE);
        INVERSIONS.put(FOUR, FIVE);
        INVERSIONS.put(FIVE, FOUR);
    }

    final int DIATONIC_NUMBER;
    final Degree DEGREE;
    final int SEMITONES;

    GeneralizedInterval(int diatonicNumber, Degree degree, int semitones) {
        this.DIATONIC_NUMBER = diatonicNumber;
        this.DEGREE = degree;
        this.SEMITONES = semitones;
    }

    private static int getSemitoneModifier(GeneralizedInterval generalizedInterval, IntervalQuality intervalQuality) {
        if (generalizedInterval.equals(ONE) || generalizedInterval.equals(FOUR) || generalizedInterval.equals(FIVE)) {
            switch (intervalQuality) {
                case PERFECT: return 0;
                case AUGMENTED: return 1;
                case DIMINISHED: return -1;
            }
        }
        else {
            switch (intervalQuality) {
                case MAJOR: return 0;
                case MINOR: return -1;
                case AUGMENTED: return 1;
                case DIMINISHED: return -2;
            }
        }
        return Integer.MIN_VALUE;
    }

    public int getSemitones(IntervalQuality intervalQuality) {
        return this.SEMITONES + getSemitoneModifier(this, intervalQuality);
    }

    public String getRomanNumeralName(IntervalQuality intervalQuality) {
        return getRomanNumeral(this, intervalQuality).toString() + intervalQuality.ROMAN_NUMERAL_APPENDED_SYMBOL;
    }

    private static RomanNumeral getRomanNumeral(GeneralizedInterval generalizedInterval, IntervalQuality intervalQuality) {
        if (generalizedInterval.equals(ONE) || generalizedInterval.equals(FOUR) || generalizedInterval.equals(FIVE)) {
            switch (intervalQuality) {
                case PERFECT:
                case AUGMENTED: return generalizedInterval.DEGREE.MAJOR_SYMBOL;
                case DIMINISHED: return generalizedInterval.DEGREE.MINOR_SYMBOL;
            }
        }
        else {
            switch (intervalQuality) {
                case MAJOR:
                case AUGMENTED: return generalizedInterval.DEGREE.MAJOR_SYMBOL;
                case MINOR:
                case DIMINISHED: return generalizedInterval.DEGREE.MINOR_SYMBOL;
            }
        }
        return null;
    }

    public GeneralizedInterval getInversion() {
        return INVERSIONS.get(this);
    }

    public static GeneralizedInterval getByDiatonic(int index) {
        return GeneralizedInterval.values()[index - 1];
    }

}
