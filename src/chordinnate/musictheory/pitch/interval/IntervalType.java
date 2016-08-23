package chordinnate.musictheory.pitch.interval;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.pitch.interval.IntervalQuality.*;
import static chordinnate.musictheory.pitch.interval.GeneralizedInterval.*;

/**
 * Created by Joseph on 8/22/16.
 */
enum IntervalType {
    DIMINISHED_UNISON(DIMINISHED, ONE),
    PERFECT_UNISON(PERFECT, ONE),
    AUGMENTED_UNISON(AUGMENTED, ONE),
    DIMINISHED_SECOND(DIMINISHED, TWO),
    MINOR_SECOND(MINOR, TWO),
    MAJOR_SECOND(MAJOR, TWO),
    AUGMENTED_SECOND(AUGMENTED, TWO),
    DIMINISHED_THIRD(DIMINISHED, THREE),
    MINOR_THIRD(MINOR, THREE),
    MAJOR_THIRD(MAJOR, THREE),
    AUGMENTED_THIRD(AUGMENTED, THREE),
    DIMINISHED_FOURTH(DIMINISHED, FOUR),
    PERFECT_FOURTH(PERFECT, FOUR),
    AUGMENTED_FOURTH(AUGMENTED, FOUR),
    DIMINISHED_FIFTH(DIMINISHED, FIVE),
    PERFECT_FIFTH(PERFECT, FIVE),
    AUGMENTED_FIFTH(AUGMENTED, FIVE),
    DIMINISHED_SIXTH(DIMINISHED, SIX),
    MINOR_SIXTH(MINOR, SIX),
    MAJOR_SIXTH(MAJOR, SIX),
    AUGMENTED_SIXTH(AUGMENTED, SIX),
    DIMINISHED_SEVENTH(DIMINISHED, SEVEN),
    MINOR_SEVENTH(MINOR, SEVEN),
    MAJOR_SEVENTH(MAJOR, SEVEN),
    AUGMENTED_SEVENTH(AUGMENTED, SEVEN),
    
    ;
    
    IntervalQuality INTERVAL_QUALITY;
    GeneralizedInterval GENERALIZED_INTERVAL;

    private static final Map<IntervalType, IntervalType> INVERSIONS = new EnumMap<>(IntervalType.class);
    static {
        INVERSIONS.put(DIMINISHED_UNISON, AUGMENTED_UNISON);
        INVERSIONS.put(PERFECT_UNISON, PERFECT_UNISON);
        INVERSIONS.put(AUGMENTED_UNISON, DIMINISHED_UNISON);

        INVERSIONS.put(DIMINISHED_SECOND, AUGMENTED_SEVENTH);
        INVERSIONS.put(MINOR_SECOND, MAJOR_SEVENTH);
        INVERSIONS.put(MAJOR_SECOND, MINOR_SEVENTH);
        INVERSIONS.put(AUGMENTED_SECOND, DIMINISHED_SEVENTH);

        INVERSIONS.put(DIMINISHED_THIRD, AUGMENTED_SIXTH);
        INVERSIONS.put(MINOR_THIRD, MAJOR_SIXTH);
        INVERSIONS.put(MAJOR_THIRD, MINOR_SIXTH);
        INVERSIONS.put(AUGMENTED_THIRD, DIMINISHED_SIXTH);

        INVERSIONS.put(DIMINISHED_FOURTH, AUGMENTED_FIFTH);
        INVERSIONS.put(PERFECT_FOURTH, PERFECT_FIFTH);
        INVERSIONS.put(AUGMENTED_FOURTH, DIMINISHED_FIFTH);

        INVERSIONS.put(DIMINISHED_FIFTH, AUGMENTED_FOURTH);
        INVERSIONS.put(PERFECT_FIFTH, PERFECT_FOURTH);
        INVERSIONS.put(AUGMENTED_FIFTH, DIMINISHED_FOURTH);

        INVERSIONS.put(DIMINISHED_SIXTH, AUGMENTED_THIRD);
        INVERSIONS.put(MINOR_SIXTH, MAJOR_THIRD);
        INVERSIONS.put(MAJOR_SIXTH, MINOR_THIRD);
        INVERSIONS.put(AUGMENTED_SIXTH, DIMINISHED_THIRD);

        INVERSIONS.put(DIMINISHED_SEVENTH, AUGMENTED_SECOND);
        INVERSIONS.put(MINOR_SEVENTH, MAJOR_SECOND);
        INVERSIONS.put(MAJOR_SEVENTH, MINOR_SECOND);
        INVERSIONS.put(AUGMENTED_SEVENTH, DIMINISHED_SECOND);
    }

    private static final Map<String, IntervalType> SHORT_NAME_TO_INTERVAL_TYPE = new HashMap<>();
    static {
        for (IntervalType intervalType : IntervalType.values()) {
            SHORT_NAME_TO_INTERVAL_TYPE
                    .put(intervalType.INTERVAL_QUALITY.SHORT_NAME_SYMBOL
                            + intervalType.GENERALIZED_INTERVAL.DIATONIC_NUMBER, intervalType);
        }
    }
    
    IntervalType(IntervalQuality intervalQuality, GeneralizedInterval generalizedInterval) {
        this.INTERVAL_QUALITY = intervalQuality;
        this.GENERALIZED_INTERVAL = generalizedInterval;
    }

    IntervalType getInversion() {
        return INVERSIONS.get(this);
    }

    int getSemitones() {
        return GENERALIZED_INTERVAL.getSemitones(INTERVAL_QUALITY);
    }

    String getRomanNumeralName() {
        return GENERALIZED_INTERVAL.getRomanNumeralName(INTERVAL_QUALITY);
    }

    static IntervalType getIntervalType(String shortName) {
        return SHORT_NAME_TO_INTERVAL_TYPE.get(shortName);
    }
}
