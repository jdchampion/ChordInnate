package chordinnate.musictheory.pitch.interval;


import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.Letter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.pitch.interval.IntervalQuality.*;


/**
 * Created by Joseph on 8/16/16.
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 *             https://en.wikipedia.org/wiki/Interval_(music)
 *             http://method-behind-the-music.com/theory/intervals/
 *             http://www.musictheory.net/lessons/31
 *             http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 *             http://music.tutsplus.com/tutorials/music-theory-intervals-and-how-to-derive-them--audio-4559
 */
public final class Interval {
    public int NUM_SEMITONES;
    int COMPOUND_DIATONIC_NUMBER, SIMPLE_DIATONIC_NUMBER;
    String COMPOUND_SHORT_NAME, SIMPLE_SHORT_NAME;
    String ROMAN_NUMERAL_NAME;

    IntervalType BASE_INTERVAL_TYPE;

    // The most common -- or "simple" -- intervals are predefined as constants
    public static final Interval PERFECT_UNISON = new Interval(IntervalType.PERFECT_UNISON, 0);
    public static final Interval AUGMENTED_UNISON = new Interval(IntervalType.AUGMENTED_UNISON, 0);

    public static final Interval DIMINISHED_SECOND = new Interval(IntervalType.DIMINISHED_SECOND, 0);
    public static final Interval MINOR_SECOND = new Interval(IntervalType.MINOR_SECOND, 0);
    public static final Interval MAJOR_SECOND = new Interval(IntervalType.MAJOR_SECOND, 0);
    public static final Interval AUGMENTED_SECOND = new Interval(IntervalType.AUGMENTED_SECOND, 0);

    public static final Interval DIMINISHED_THIRD = new Interval(IntervalType.DIMINISHED_THIRD, 0);
    public static final Interval MINOR_THIRD = new Interval(IntervalType.MINOR_THIRD, 0);
    public static final Interval MAJOR_THIRD = new Interval(IntervalType.MAJOR_THIRD, 0);
    public static final Interval AUGMENTED_THIRD = new Interval(IntervalType.AUGMENTED_THIRD, 0);

    public static final Interval DIMINISHED_FOURTH = new Interval(IntervalType.DIMINISHED_FOURTH, 0);
    public static final Interval PERFECT_FOURTH = new Interval(IntervalType.PERFECT_FOURTH, 0);
    public static final Interval AUGMENTED_FOURTH = new Interval(IntervalType.AUGMENTED_FOURTH, 0);

    public static final Interval DIMINISHED_FIFTH = new Interval(IntervalType.DIMINISHED_FIFTH, 0);
    public static final Interval PERFECT_FIFTH = new Interval(IntervalType.PERFECT_FIFTH, 0);
    public static final Interval AUGMENTED_FIFTH = new Interval(IntervalType.AUGMENTED_FIFTH, 0);

    public static final Interval DIMINISHED_SIXTH = new Interval(IntervalType.DIMINISHED_SIXTH, 0);
    public static final Interval MINOR_SIXTH = new Interval(IntervalType.MINOR_SIXTH, 0);
    public static final Interval MAJOR_SIXTH = new Interval(IntervalType.MAJOR_SIXTH, 0);
    public static final Interval AUGMENTED_SIXTH = new Interval(IntervalType.AUGMENTED_SIXTH, 0);

    public static final Interval DIMINISHED_SEVENTH = new Interval(IntervalType.DIMINISHED_SEVENTH, 0);
    public static final Interval MINOR_SEVENTH = new Interval(IntervalType.MINOR_SEVENTH, 0);
    public static final Interval MAJOR_SEVENTH = new Interval(IntervalType.MAJOR_SEVENTH, 0);
    public static final Interval AUGMENTED_SEVENTH = new Interval(IntervalType.AUGMENTED_SEVENTH, 0);

    public static final Interval DIMINISHED_OCTAVE = new Interval(IntervalType.DIMINISHED_UNISON, 1);
    public static final Interval PERFECT_OCTAVE = new Interval(IntervalType.PERFECT_UNISON, 1);

    private static final Map<Interval, Interval> INVERSIONS = new HashMap<>(26);
    static {
        INVERSIONS.put(PERFECT_UNISON, PERFECT_OCTAVE);
        INVERSIONS.put(AUGMENTED_UNISON, DIMINISHED_OCTAVE);
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
        INVERSIONS.put(DIMINISHED_OCTAVE, AUGMENTED_UNISON);
        INVERSIONS.put(PERFECT_OCTAVE, PERFECT_UNISON);
    }

    private static final Map<String, Interval> COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL = new HashMap<>(26);
    static {
        for (Interval interval : INVERSIONS.keySet()) {
            COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(interval.COMPOUND_SHORT_NAME, interval);
        }
    }

    /**
     * Checks whether the provided IntervalQuality and diatonic number are legal combinations.
     * @param intervalQuality
     * @param diatonic
     * @throws IllegalArgumentException if validation fails
     */
    private static void validateForNewInterval(IntervalQuality intervalQuality, int diatonic) throws IllegalArgumentException {
        boolean valid;
        if (intervalQuality == null) {
            throw new IllegalArgumentException("Missing qualifier for the interval");
        }
        if (intervalQuality.equals(PERFECT)) {
            valid = diatonic == 1 || diatonic == 4 || diatonic == 5;
        }
        if (intervalQuality.equals(MAJOR) || intervalQuality.equals(MINOR)) {
            valid = diatonic == 2 || diatonic == 3 || diatonic == 6 || diatonic == 7;
        }
        valid = intervalQuality.equals(DIMINISHED) || intervalQuality.equals(AUGMENTED);
        if (!valid) {
            throw new IllegalArgumentException(
                    "Improper match for interval quality and diatonic number: " + intervalQuality.SHORT_NAME_SYMBOL + diatonic);
        }
    }

    private static Interval getStaticInterval(int semitoneDistance, int letterDistance, boolean direction) {
        if (letterDistance == 0) {
            switch (semitoneDistance) {
                case 0: return PERFECT_OCTAVE;
                case 1: return direction ? AUGMENTED_UNISON : DIMINISHED_OCTAVE;
                case 11: return direction ? DIMINISHED_OCTAVE : AUGMENTED_UNISON;
                default: return null;
            }
        }
        if (semitoneDistance == 0) {
            switch (letterDistance) {
                case 1: return direction ? DIMINISHED_SECOND : AUGMENTED_SEVENTH;
                case 6: return direction ? AUGMENTED_SEVENTH : DIMINISHED_SECOND;
                default: return null;
            }
        }
        if (semitoneDistance == 1) {
            switch (letterDistance) {
                case 1: return direction ? MINOR_SECOND : MAJOR_SEVENTH;
                default: return null;
            }
        }
        if (semitoneDistance == 2) {
            switch (letterDistance) {
                case 1: return direction ? MAJOR_SECOND : MINOR_SEVENTH;
                case 2: return direction ? DIMINISHED_THIRD : AUGMENTED_SIXTH;
                default: return null;
            }
        }
        if (semitoneDistance == 3) {
            switch (letterDistance) {
                case 1: return direction ? AUGMENTED_SECOND : DIMINISHED_SEVENTH;
                case 2: return direction ? MINOR_THIRD : MAJOR_SIXTH;
                default: return null;
            }
        }
        if (semitoneDistance == 4) {
            switch (letterDistance) {
                case 2: return direction ? MAJOR_THIRD : MINOR_SIXTH;
                case 3: return direction ? DIMINISHED_FOURTH : AUGMENTED_FIFTH;
            }
        }
        if (semitoneDistance == 5) {
            switch (letterDistance) {
                case 2: return direction ? AUGMENTED_THIRD : DIMINISHED_SIXTH;
                case 3: return direction ? PERFECT_FOURTH : PERFECT_FIFTH;
                default: return null;
            }
        }
        if (semitoneDistance == 6) {
            switch (letterDistance) {
                case 3: return direction ? AUGMENTED_FOURTH : DIMINISHED_FIFTH;
                case 4: return direction ? DIMINISHED_FIFTH : AUGMENTED_FOURTH;
                default: return null;
            }
        }
        if (semitoneDistance == 7) {
            switch (letterDistance) {
                case 4: return direction ? PERFECT_FIFTH : PERFECT_FOURTH;
                case 5: return direction ? DIMINISHED_SIXTH : AUGMENTED_THIRD;
                default: return null;
            }
        }
        if (semitoneDistance == 8) {
            switch (letterDistance) {
                case 4: return direction ? AUGMENTED_FIFTH : DIMINISHED_FOURTH;
                case 5: return direction ? MINOR_SIXTH : MAJOR_THIRD;
                default: return null;
            }
        }
        if (semitoneDistance == 9) {
            switch (letterDistance) {
                case 5: return direction ? MAJOR_SIXTH : MINOR_THIRD;
                case 6: return direction ? DIMINISHED_SEVENTH : AUGMENTED_SECOND;
                default: return null;
            }
        }
        if (semitoneDistance == 10) {
            switch (letterDistance) {
                case 5: return direction ? AUGMENTED_SIXTH : DIMINISHED_THIRD;
                case 6: return direction ? MINOR_SEVENTH : MAJOR_SECOND;
                default: return null;
            }
        }
        if (semitoneDistance == 11) {
            switch (letterDistance) {
                case 6: return direction ? MAJOR_SEVENTH : MINOR_SECOND;
                default: return null;
            }
        }
        else return null;
    }

    /**
     * Constructor for all Intervals.
     * @param intervalType
     * @param numOctaves
     */
    private Interval(IntervalType intervalType, int numOctaves) {
        this.BASE_INTERVAL_TYPE = intervalType;
        this.SIMPLE_DIATONIC_NUMBER = BASE_INTERVAL_TYPE.GENERALIZED_INTERVAL.DIATONIC_NUMBER;
        this.COMPOUND_DIATONIC_NUMBER = (numOctaves * 7) + SIMPLE_DIATONIC_NUMBER;
        this.NUM_SEMITONES = 12 * numOctaves + BASE_INTERVAL_TYPE.getSemitones();
        this.SIMPLE_SHORT_NAME = BASE_INTERVAL_TYPE.INTERVAL_QUALITY.SHORT_NAME_SYMBOL + SIMPLE_DIATONIC_NUMBER;
        this.COMPOUND_SHORT_NAME = BASE_INTERVAL_TYPE.INTERVAL_QUALITY.SHORT_NAME_SYMBOL + COMPOUND_DIATONIC_NUMBER;
        this.ROMAN_NUMERAL_NAME = BASE_INTERVAL_TYPE.getRomanNumeralName();
    }

    /**
     * Top-level factory method.
     * @param name the Interval's label
     * @return a static instance of Interval if one exists with the same name.
     * Otherwise, a dynamically-created Interval.
     */
    public static Interval withShortName(String name) throws IllegalArgumentException {
        String[] quality = name.split("\\.?\\s?[0-9]"),
                number = name.split("[a-zA-z]\\.?\\s?");
        if (quality.length < 1 || number.length < 1) {
            throw new IllegalArgumentException("Not a valid or recognized interval name: " + name);
        }
        int compoundDiatonic;
        try {
            compoundDiatonic = Integer.parseInt(number[number.length - 1]);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Integer value must be defined after interval quality.");
        }
        IntervalQuality intervalQuality = IntervalQuality.getIntervalQuality(quality[0]);
        int simpleDiatonic = getSimpleDiatonic(compoundDiatonic);
        validateForNewInterval(intervalQuality, simpleDiatonic);
        Interval toReturn = COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.get(name);
        if (toReturn != null) return toReturn;
        int numOctaves = getNumberOfOctaves(simpleDiatonic, compoundDiatonic);
        IntervalType intervalType = IntervalType.getIntervalType(intervalQuality.SHORT_NAME_SYMBOL + simpleDiatonic);
        return new Interval(intervalType, numOctaves);
    }

    private static int getNumberOfOctaves(int simpleDiatonic, int compoundDiatonic) {
        int temp = simpleDiatonic;
        int octave = 0;
        while (temp < compoundDiatonic) {
            temp += 8;
            octave++;
        }
        return octave;
    }

    private static int getSimpleDiatonic(int compoundDiatonic) {
        int temp = compoundDiatonic;
        while (temp >= 8) {
            temp -= 7;
        }
        return temp;
    }

    private int getNextInvertedDiatonic(int diatonic) {
        int simpleDiatonic = getSimpleDiatonic(diatonic);
        int[] patterns = {7, 5, 3, 1, -1, -3, -5, -7}; // sequence of inversion
        simpleDiatonic = diatonic + (patterns[simpleDiatonic - 1]);
        return simpleDiatonic;
    }

    public Interval getInversion() {
        Interval staticInterval = INVERSIONS.get(this);
        if (staticInterval != null) return staticInterval;
        IntervalType nextIntervalType = this.BASE_INTERVAL_TYPE.getInversion();
        int nextDiatonic = getNextInvertedDiatonic(this.COMPOUND_DIATONIC_NUMBER);
        return Interval.withShortName(nextIntervalType.INTERVAL_QUALITY.SHORT_NAME_SYMBOL + nextDiatonic);
    }

    /**
     * Finds the Interval starting from lhs and ending at rhs.
     * @param lhs the starting PitchClass
     * @param rhs the ending PitchClass
     * @param direction the desired direction to transpose from lhs to rhs
     * @return the Interval between lhs and rhs.
     * Identical PitchClasses for lhs and rhs will always return Interval.PERFECT_OCTAVE
     */
    public static Interval getIntervalBetween(@NotNull PitchClass lhs, @NotNull PitchClass rhs, boolean direction) {
        int semitoneDistance = PitchClass.getSemitoneDistanceBetween(lhs, rhs);
        int letterDistance = (getVectorDistanceTo(lhs.ENHARMONIC_SPELLING.LETTER, rhs.ENHARMONIC_SPELLING.LETTER)) % 7;
        return getStaticInterval(semitoneDistance, letterDistance, direction);
    }

    /**
     * Finds the vector distance required to travel left to right on the enumerated list
     * to reach the other Letter.
     * @param lhs the starting Letter
     * @param rhs the ending Letter
     * @return the number of indices to move left or right to get to the next Letter
     */
    private static int getVectorDistanceTo(Letter lhs, Letter rhs) {
        int thisOrdinal = lhs.ordinal(), otherOrdinal = rhs.ordinal();
        return (thisOrdinal < otherOrdinal) ? otherOrdinal - thisOrdinal : 7 - (thisOrdinal - otherOrdinal);
    }

    public int getOctaveSpan() {
        return getNumberOfOctaves(SIMPLE_DIATONIC_NUMBER, COMPOUND_DIATONIC_NUMBER);
    }

    public int getCompoundDiatonic() {
        return COMPOUND_DIATONIC_NUMBER;
    }

    public int getSimpleDiatonic() {
        return SIMPLE_DIATONIC_NUMBER;
    }

    public IntervalQuality getIntervalQuality() {
        return BASE_INTERVAL_TYPE.INTERVAL_QUALITY;
    }

    public boolean isCompoundInterval() {
        return COMPOUND_DIATONIC_NUMBER > SIMPLE_DIATONIC_NUMBER;
    }

}
