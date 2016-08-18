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
    EIGHT(8, TONIC, 0)

    ;

    private static final EnumMap<GeneralizedInterval, GeneralizedInterval> INVERSIONS = new EnumMap<>(GeneralizedInterval.class);
        static {
            INVERSIONS.put(ONE, EIGHT);
            INVERSIONS.put(EIGHT, ONE);
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

    public GeneralizedInterval getInversion() {
        return INVERSIONS.get(this);
    }

    public static GeneralizedInterval getGeneralizedInterval(int index) {
        return GeneralizedInterval.values()[index - 1];
    }
}
