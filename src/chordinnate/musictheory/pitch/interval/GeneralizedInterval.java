package chordinnate.musictheory.pitch.interval;


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

    int getSemitones(IntervalQuality intervalQuality) {
        return this.SEMITONES + getSemitoneModifier(this, intervalQuality);
    }

    String getRomanNumeralName(IntervalQuality intervalQuality) {
        return DEGREE.getRomanNumeral(intervalQuality).toString() + intervalQuality.ROMAN_NUMERAL_APPENDED_SYMBOL;
    }
}
