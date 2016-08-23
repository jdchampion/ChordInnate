package chordinnate.musictheory.pitch.interval;


import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.Letter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.pitch.interval.GeneralizedInterval.*;
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
public class Interval {
    public int NUM_SEMITONES;
    public int COMPOUND_DIATONIC_NUMBER, SIMPLE_DIATONIC_NUMBER;
    IntervalQuality INTERVAL_QUALITY;
    GeneralizedInterval GENERALIZED_INTERVAL;
    public String COMPOUND_SHORT_NAME, SIMPLE_SHORT_NAME;
    public String ROMAN_NUMERAL_NAME;

    // The most common -- or "simple" -- intervals are predefined as constants
    public static final Interval PERFECT_UNISON = new Interval(ONE, PERFECT, 0);
    public static final Interval AUGMENTED_UNISON = new Interval(ONE, AUGMENTED, 0);

    public static final Interval DIMINISHED_SECOND = new Interval(TWO, DIMINISHED, 0);
    public static final Interval MINOR_SECOND = new Interval(TWO, MINOR, 0);
    public static final Interval MAJOR_SECOND = new Interval(TWO, MAJOR, 0);
    public static final Interval AUGMENTED_SECOND = new Interval(TWO, AUGMENTED, 0);

    public static final Interval DIMINISHED_THIRD = new Interval(THREE, DIMINISHED, 0);
    public static final Interval MINOR_THIRD = new Interval(THREE, MINOR, 0);
    public static final Interval MAJOR_THIRD = new Interval(THREE, MAJOR, 0);
    public static final Interval AUGMENTED_THIRD = new Interval(THREE, AUGMENTED, 0);

    public static final Interval DIMINISHED_FOURTH = new Interval(FOUR, DIMINISHED, 0);
    public static final Interval PERFECT_FOURTH = new Interval(FOUR, PERFECT, 0);
    public static final Interval AUGMENTED_FOURTH = new Interval(FOUR, AUGMENTED, 0);

    public static final Interval DIMINISHED_FIFTH = new Interval(FIVE, DIMINISHED, 0);
    public static final Interval PERFECT_FIFTH = new Interval(FIVE, PERFECT, 0);
    public static final Interval AUGMENTED_FIFTH = new Interval(FIVE, AUGMENTED, 0);

    public static final Interval DIMINISHED_SIXTH = new Interval(SIX, DIMINISHED, 0);
    public static final Interval MINOR_SIXTH = new Interval(SIX, MINOR, 0);
    public static final Interval MAJOR_SIXTH = new Interval(SIX, MAJOR, 0);
    public static final Interval AUGMENTED_SIXTH = new Interval(SIX, AUGMENTED, 0);

    public static final Interval DIMINISHED_SEVENTH = new Interval(SEVEN, DIMINISHED, 0);
    public static final Interval MINOR_SEVENTH = new Interval(SEVEN, MINOR, 0);
    public static final Interval MAJOR_SEVENTH = new Interval(SEVEN, MAJOR, 0);
    public static final Interval AUGMENTED_SEVENTH = new Interval(SEVEN, AUGMENTED, 0);

    public static final Interval DIMINISHED_OCTAVE = new Interval(ONE, DIMINISHED, 1);
    public static final Interval PERFECT_OCTAVE = new Interval(ONE, PERFECT, 1);

    private static final Map<String, Interval> COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL = new HashMap<>(26);
    static {
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(PERFECT_UNISON.COMPOUND_SHORT_NAME, PERFECT_UNISON);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_UNISON.COMPOUND_SHORT_NAME, AUGMENTED_UNISON);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_SECOND.COMPOUND_SHORT_NAME, DIMINISHED_SECOND);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MINOR_SECOND.COMPOUND_SHORT_NAME, MINOR_SECOND);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MAJOR_SECOND.COMPOUND_SHORT_NAME, MAJOR_SECOND);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_SECOND.COMPOUND_SHORT_NAME, AUGMENTED_SECOND);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_THIRD.COMPOUND_SHORT_NAME, DIMINISHED_THIRD);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MINOR_THIRD.COMPOUND_SHORT_NAME, MINOR_THIRD);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MAJOR_THIRD.COMPOUND_SHORT_NAME, MAJOR_THIRD);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_THIRD.COMPOUND_SHORT_NAME, AUGMENTED_THIRD);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_FOURTH.COMPOUND_SHORT_NAME, DIMINISHED_FOURTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(PERFECT_FOURTH.COMPOUND_SHORT_NAME, PERFECT_FOURTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_FOURTH.COMPOUND_SHORT_NAME, AUGMENTED_FOURTH);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_FIFTH.COMPOUND_SHORT_NAME, DIMINISHED_FIFTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(PERFECT_FIFTH.COMPOUND_SHORT_NAME, PERFECT_FIFTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_FIFTH.COMPOUND_SHORT_NAME, AUGMENTED_FIFTH);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_SIXTH.COMPOUND_SHORT_NAME, DIMINISHED_SIXTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MINOR_SIXTH.COMPOUND_SHORT_NAME, MINOR_SIXTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MAJOR_SIXTH.COMPOUND_SHORT_NAME, MAJOR_SIXTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_SIXTH.COMPOUND_SHORT_NAME, AUGMENTED_SIXTH);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_SEVENTH.COMPOUND_SHORT_NAME, DIMINISHED_SEVENTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MINOR_SEVENTH.COMPOUND_SHORT_NAME, MINOR_SEVENTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(MAJOR_SEVENTH.COMPOUND_SHORT_NAME, MAJOR_SEVENTH);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(AUGMENTED_SEVENTH.COMPOUND_SHORT_NAME, AUGMENTED_SEVENTH);

        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(DIMINISHED_OCTAVE.COMPOUND_SHORT_NAME, DIMINISHED_OCTAVE);
        COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.put(PERFECT_OCTAVE.COMPOUND_SHORT_NAME, PERFECT_OCTAVE);
    }

    private static void validateForNewInterval(IntervalQuality intervalQuality, int diatonic) {
        boolean valid;
        if (intervalQuality == null) {
            throw new IllegalArgumentException("Missing qualifier for the interval");
        }
        if (intervalQuality.equals(PERFECT)) {
            valid = diatonic == 1 || diatonic == 4 || diatonic == 5;
        }
        else if (intervalQuality.equals(MAJOR) || intervalQuality.equals(MINOR)) {
            valid = diatonic == 2 || diatonic == 3 || diatonic == 6 || diatonic == 7;
        }
        else valid = intervalQuality.equals(DIMINISHED) || intervalQuality.equals(AUGMENTED);
        if (!valid) {
            throw new IllegalArgumentException(
                    "Improper match for interval quality and diatonic number: " + intervalQuality.SHORT_NAME_SYMBOL + diatonic);
        }
    }

    public static Interval getStaticInterval(String name) {
        return COMPOUND_SHORT_NAME_TO_STATIC_INTERVAL.get(name);
    }

    private static Interval getStaticInterval(int semitoneDistance, int letterDistance, boolean direction, boolean isCompound) {
        boolean identicalLetters = letterDistance == 0;
        if (identicalLetters) {
            switch (semitoneDistance) {
                case 0: {
                    if (isCompound) return direction ? PERFECT_OCTAVE : PERFECT_OCTAVE;
                    else return PERFECT_UNISON;
                }
                case 1: {
                    if (isCompound) return direction ? AUGMENTED_UNISON : DIMINISHED_OCTAVE;
                    else return AUGMENTED_UNISON;
                }
                case 11: {
                    if (isCompound) return direction ? DIMINISHED_OCTAVE : AUGMENTED_UNISON;
                    else return DIMINISHED_OCTAVE;
                }
                default: return null;
            }
        }
        switch (semitoneDistance) {
            case 0: {
                switch (letterDistance) {
                    case 1: return direction ? DIMINISHED_SECOND : AUGMENTED_SEVENTH;
                    case 6: return direction ? AUGMENTED_SEVENTH : DIMINISHED_SECOND;
                }
            }
            case 1: {
                switch (letterDistance) {
                    case 1: return direction ? MINOR_SECOND : MAJOR_SEVENTH;
                }
            }
            case 2: {
                switch (letterDistance) {
                    case 1: return direction ? MAJOR_SECOND : MINOR_SEVENTH;
                    case 2: return direction ? DIMINISHED_THIRD : AUGMENTED_SIXTH;
                }
            }
            case 3: {
                switch (letterDistance) {
                    case 1: return direction ? AUGMENTED_SECOND : DIMINISHED_SEVENTH;
                    case 2: return direction ? MINOR_THIRD : MAJOR_SIXTH;
                }
            }
            case 4: {
                switch (letterDistance) {
                    case 2: return direction ? MAJOR_THIRD : MINOR_SIXTH;
                    case 3: return direction ? DIMINISHED_FOURTH : AUGMENTED_FIFTH;
                }
            }
            case 5: {
                switch (letterDistance) {
                    case 2: return direction ? AUGMENTED_THIRD : DIMINISHED_SIXTH;
                    case 3: return direction ? PERFECT_FOURTH : PERFECT_FIFTH;
                }
            }
            case 6: {
                switch (letterDistance) {
                    case 3: return direction ? AUGMENTED_FOURTH : DIMINISHED_FIFTH;
                    case 4: return direction ? DIMINISHED_FIFTH : AUGMENTED_FOURTH;
                }
            }
            case 7: {
                switch (letterDistance) {
                    case 4: return direction ? PERFECT_FIFTH : PERFECT_FOURTH;
                    case 5: return direction ? DIMINISHED_SIXTH : AUGMENTED_THIRD;
                }
            }
            case 8: {
                switch (letterDistance) {
                    case 4: return direction ? AUGMENTED_FIFTH : DIMINISHED_FOURTH;
                    case 5: return direction ? MINOR_SIXTH : MAJOR_THIRD;
                }
            }
            case 9: {
                switch (letterDistance) {
                    case 5: return direction ? MAJOR_SIXTH : MINOR_THIRD;
                    case 6: return direction ? DIMINISHED_SEVENTH : AUGMENTED_SECOND;
                }
            }
            case 10: {
                switch (letterDistance) {
                    case 5: return direction ? AUGMENTED_SIXTH : DIMINISHED_THIRD;
                    case 6: return direction ? MINOR_SEVENTH : MAJOR_SECOND;
                }
            }
            case 11: {
                switch (letterDistance) {
                    case 6: return direction ? MAJOR_SEVENTH : MINOR_SECOND;
                }
            }
            default: return null;
        }
    }

    /**
     * Special constructor reserved for static Intervals
     */
    private Interval(GeneralizedInterval generalizedInterval, IntervalQuality intervalQuality, int numOctaves) {
        this.GENERALIZED_INTERVAL = generalizedInterval;
        this.INTERVAL_QUALITY = intervalQuality;
        this.SIMPLE_DIATONIC_NUMBER = GENERALIZED_INTERVAL.DIATONIC_NUMBER;
        this.COMPOUND_DIATONIC_NUMBER = numOctaves == 0 ? SIMPLE_DIATONIC_NUMBER : (numOctaves * 7) + SIMPLE_DIATONIC_NUMBER;
        this.NUM_SEMITONES = 12 * numOctaves + GENERALIZED_INTERVAL.getSemitones(INTERVAL_QUALITY);
        this.SIMPLE_SHORT_NAME = INTERVAL_QUALITY.SHORT_NAME_SYMBOL + SIMPLE_DIATONIC_NUMBER;
        this.COMPOUND_SHORT_NAME = INTERVAL_QUALITY.SHORT_NAME_SYMBOL + COMPOUND_DIATONIC_NUMBER;
        this.ROMAN_NUMERAL_NAME = GENERALIZED_INTERVAL.getRomanNumeralName(INTERVAL_QUALITY);
    }

    /**
     * Top-level constructor for dynamically-created Intervals.
     * @param name
     */
    public Interval(String name) throws IllegalArgumentException {
        String[] quality = name.split("\\.?\\s?[0-9]"),
                number = name.split("[a-zA-z]\\.?\\s?");
        if (quality.length < 1 || number.length < 1) {
            throw new IllegalArgumentException("Not a valid or recognized interval name: " + name);
        }
        int diatonic;
        try {
            diatonic = Integer.parseInt(number[number.length - 1]);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Integer value must be defined after interval quality.");
        }
        IntervalQuality intervalQuality = IntervalQuality.getIntervalQuality(quality[0]);
        int simpleDiatonic = getSimpleDiatonic(diatonic);
        validateForNewInterval(intervalQuality, simpleDiatonic);
        this.INTERVAL_QUALITY = intervalQuality;
        this.SIMPLE_DIATONIC_NUMBER = simpleDiatonic;
        this.COMPOUND_DIATONIC_NUMBER = diatonic;
        this.GENERALIZED_INTERVAL = GeneralizedInterval.getByDiatonic(SIMPLE_DIATONIC_NUMBER);
        this.SIMPLE_SHORT_NAME = INTERVAL_QUALITY.SHORT_NAME_SYMBOL + SIMPLE_DIATONIC_NUMBER;
        this.COMPOUND_SHORT_NAME = INTERVAL_QUALITY.SHORT_NAME_SYMBOL + COMPOUND_DIATONIC_NUMBER;
        this.ROMAN_NUMERAL_NAME = GENERALIZED_INTERVAL.getRomanNumeralName(INTERVAL_QUALITY);
        this.NUM_SEMITONES = 12 * getNumberOfOctaves(SIMPLE_DIATONIC_NUMBER, COMPOUND_DIATONIC_NUMBER)
                + GENERALIZED_INTERVAL.getSemitones(INTERVAL_QUALITY);
    }

    private int getNumberOfOctaves(int simpleDiatonic, int compoundDiatonic) {
        int temp = simpleDiatonic;
        int octave = 0;
        while (temp < compoundDiatonic) {
            temp += 8;
            octave++;
        }
        return octave;
    }

    private int getSimpleDiatonic(int compoundDiatonic) {
        int temp = compoundDiatonic;
        while (temp >= 8) {
            temp -= 7;
        }
        return temp;
    }

    private int getNextInvertedDiatonic(int diatonic) {
        int simpleDiatonic = getSimpleDiatonic(diatonic);
        int[][] patterns = {{7, 7}, {5, 2}, {3, 4}, {1, 6}, {6, 1}, {4, 3}, {2, 5}}; // sequence of inversion
        int index = simpleDiatonic > 4 ? 0 : 1;
        simpleDiatonic = diatonic + (patterns[simpleDiatonic - 1][index]);
        return simpleDiatonic;
    }

    public Interval getInversion() {
        GeneralizedInterval nextGeneralizedInterval = GENERALIZED_INTERVAL.getInversion();
        IntervalQuality nextIntervalQuality = this.INTERVAL_QUALITY.getInversion();
        int nextDiatonic = getNextInvertedDiatonic(this.COMPOUND_DIATONIC_NUMBER);
        try {
            Interval staticInterval = getStaticInterval(nextIntervalQuality.SHORT_NAME_SYMBOL + nextDiatonic);
            if (staticInterval != null) return staticInterval;
            return new Interval(nextIntervalQuality.SHORT_NAME_SYMBOL + nextDiatonic);
        }
        catch (Exception ex) {
            return new Interval(nextIntervalQuality.SHORT_NAME_SYMBOL + nextGeneralizedInterval.DIATONIC_NUMBER);
        }
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
        return getStaticInterval(semitoneDistance, letterDistance, direction, true);
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
        return COMPOUND_DIATONIC_NUMBER / 8;
    }

}
