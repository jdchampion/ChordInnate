package chordinnate.musictheory.pitch.interval;


import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.notation.RomanNumeral;
import chordinnate.musictheory.pitch.notation.Letter;
import org.jetbrains.annotations.NotNull;



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
    public boolean DIRECTION;
    public int DIR;

    // The most common -- or "simple" -- intervals are predefined as constants
    public static final Interval PERFECT_UNISON = new Interval(1, 0);
    public static final Interval PERFECT_OCTAVE_UP = new Interval(8, 1);
    public static final Interval PERFECT_OCTAVE_DOWN = new Interval(8, -1);

    public static final Interval DIMINISHED_UNISON_UP = new Interval("d1", true);
    public static final Interval DIMINISHED_SECOND_UP = new Interval("d2", true);
    public static final Interval MINOR_SECOND_UP = new Interval("m2", true);
    public static final Interval AUGMENTED_UNISON_UP = new Interval("A1", true);
    public static final Interval MAJOR_SECOND_UP = new Interval("M2", true);
    public static final Interval DIMINISHED_THIRD_UP = new Interval("d3", true);
    public static final Interval MINOR_THIRD_UP = new Interval("m3", true);
    public static final Interval AUGMENTED_SECOND_UP = new Interval("A2", true);
    public static final Interval MAJOR_THIRD_UP = new Interval("M3", true);
    public static final Interval DIMINISHED_FOURTH_UP = new Interval("d4", true);
    public static final Interval PERFECT_FOURTH_UP = new Interval("P4", true);
    public static final Interval AUGMENTED_THIRD_UP = new Interval("A3", true);
    public static final Interval DIMINISHED_FIFTH_UP = new Interval("d5", true);
    public static final Interval AUGMENTED_FOURTH_UP = new Interval("A4", true);
    public static final Interval PERFECT_FIFTH_UP = new Interval("P5", true);
    public static final Interval DIMINISHED_SIXTH_UP = new Interval("d6", true);
    public static final Interval MINOR_SIXTH_UP = new Interval("m6", true);
    public static final Interval AUGMENTED_FIFTH_UP = new Interval("A5", true);
    public static final Interval MAJOR_SIXTH_UP = new Interval("M6", true);
    public static final Interval DIMINISHED_SEVENTH_UP = new Interval("D7", true);
    public static final Interval MINOR_SEVENTH_UP = new Interval("m7", true);
    public static final Interval AUGMENTED_SIXTH_UP = new Interval("A6", true);
    public static final Interval MAJOR_SEVENTH_UP = new Interval("M7", true);
    public static final Interval DIMINISHED_OCTAVE_UP = new Interval("d8", true);
    public static final Interval AUGMENTED_SEVENTH_UP = new Interval("A7", true);
    public static final Interval AUGMENTED_OCTAVE_UP = new Interval("A8", true);

    public static final Interval DIMINISHED_UNISON_DOWN = new Interval("d1", false);
    public static final Interval DIMINISHED_SECOND_DOWN = new Interval("d2", false);
    public static final Interval MINOR_SECOND_DOWN = new Interval("m2", false);
    public static final Interval AUGMENTED_UNISON_DOWN = new Interval("A1", false);
    public static final Interval MAJOR_SECOND_DOWN = new Interval("M2", false);
    public static final Interval DIMINISHED_THIRD_DOWN = new Interval("d3", false);
    public static final Interval MINOR_THIRD_DOWN = new Interval("m3", false);
    public static final Interval AUGMENTED_SECOND_DOWN = new Interval("A2", false);
    public static final Interval MAJOR_THIRD_DOWN = new Interval("M3", false);
    public static final Interval DIMINISHED_FOURTH_DOWN = new Interval("d4", false);
    public static final Interval PERFECT_FOURTH_DOWN = new Interval("P4", false);
    public static final Interval AUGMENTED_THIRD_DOWN = new Interval("A3", false);
    public static final Interval DIMINISHED_FIFTH_DOWN = new Interval("d5", false);
    public static final Interval AUGMENTED_FOURTH_DOWN = new Interval("A4", false);
    public static final Interval PERFECT_FIFTH_DOWN = new Interval("P5", false);
    public static final Interval DIMINISHED_SIXTH_DOWN = new Interval("d6", false);
    public static final Interval MINOR_SIXTH_DOWN = new Interval("m6", false);
    public static final Interval AUGMENTED_FIFTH_DOWN = new Interval("A5", false);
    public static final Interval MAJOR_SIXTH_DOWN = new Interval("M6", false);
    public static final Interval DIMINISHED_SEVENTH_DOWN = new Interval("D7", false);
    public static final Interval MINOR_SEVENTH_DOWN = new Interval("m7", false);
    public static final Interval AUGMENTED_SIXTH_DOWN = new Interval("A6", false);
    public static final Interval MAJOR_SEVENTH_DOWN = new Interval("M7", false);
    public static final Interval DIMINISHED_OCTAVE_DOWN = new Interval("d8", false);
    public static final Interval AUGMENTED_SEVENTH_DOWN = new Interval("A7", false);
    public static final Interval AUGMENTED_OCTAVE_DOWN = new Interval("A8", false);

    private Interval(int diatonic, int dir) {
        this.INTERVAL_QUALITY = IntervalQuality.PERFECT;
        this.NUM_SEMITONES = diatonic == 8 ? 12 : 0;
        this.COMPOUND_DIATONIC_NUMBER = diatonic;
        this.SIMPLE_DIATONIC_NUMBER =  diatonic < 8 ? diatonic : 1;
        this.INTERVAL_QUALITY = IntervalQuality.PERFECT;
        this.GENERALIZED_INTERVAL = GeneralizedInterval.ONE;
        this.COMPOUND_SHORT_NAME = diatonic == 8 ? "P8" : "P1";
        this.SIMPLE_SHORT_NAME = "P1";
        this.ROMAN_NUMERAL_NAME = "I";
        this.DIRECTION = dir >= 0;
        this.DIR = dir;
    }

    /**
     * Top-level constructor for Intervals.
     * @param name
     * @param direction
     */
    public Interval(String name, boolean direction) throws IllegalArgumentException {
        String[] quality = name.split("\\s?[0-9]"),
                number = name.split("[a-zA-z]\\.?");
        if (quality.length < 1 || number.length < 1) {
            throw new IllegalArgumentException("Not a valid or recognized interval name: " + name);
        }
        try {
            int diatonic = Integer.parseInt(number[number.length - 1].replace(" ", ""));
            IntervalQuality intervalQuality = IntervalQuality.getIntervalQuality(quality[0]);
            int simpleDiatonic = getSimpleDiatonic(diatonic);
            if (IntervalQuality.isValid(intervalQuality, simpleDiatonic)) {
                this.INTERVAL_QUALITY = intervalQuality;
                this.SIMPLE_DIATONIC_NUMBER = simpleDiatonic;
                this.COMPOUND_DIATONIC_NUMBER = diatonic;
                this.GENERALIZED_INTERVAL = GeneralizedInterval.getGeneralizedInterval(SIMPLE_DIATONIC_NUMBER);
                this.SIMPLE_SHORT_NAME = intervalQuality.SHORT_NAME_SYMBOL + GENERALIZED_INTERVAL.DIATONIC_NUMBER;
                this.COMPOUND_SHORT_NAME = intervalQuality.SHORT_NAME_SYMBOL + diatonic;
                this.ROMAN_NUMERAL_NAME = inferRomanNumeral(intervalQuality, GENERALIZED_INTERVAL).toString()
                        + intervalQuality.ROMAN_NUMERAL_APPENDED_SYMBOL;
                this.NUM_SEMITONES = ((12 * (COMPOUND_DIATONIC_NUMBER / 8)) + getSemitones()) * (direction ? 1 : -1);
                this.DIRECTION = direction;
                this.DIR = direction ? 1 : -1;
            }
            else throw new IllegalArgumentException(
                    "Improper match for interval quality and diatonic number: " + intervalQuality.SHORT_NAME_SYMBOL + diatonic);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Integer value must be defined after interval quality.");
        }
    }

    private int getSemitones() {
        return IntervalUtil.SIMPLE_SHORT_NAME_TO_SEMITONES.get(this.SIMPLE_SHORT_NAME);
    }

    private int getSimpleDiatonic(int compoundDiatonic) {
        int temp = compoundDiatonic;
        while (temp >= 8) {
            temp -= 7;
        }
        return temp;
    }

    private RomanNumeral inferRomanNumeral(IntervalQuality intervalQuality, GeneralizedInterval generalizedInterval) {
        switch (intervalQuality) {
            case MAJOR:
            case PERFECT:
            case AUGMENTED: return generalizedInterval.DEGREE.MAJOR_SYMBOL;
            case MINOR:
            case DIMINISHED: return generalizedInterval.DEGREE.MINOR_SYMBOL;
            default: return generalizedInterval.DEGREE.MAJOR_SYMBOL;
        }
    }

    private int getNextInvertedDiatonic(int diatonic, boolean direction) {
        int[][] patterns = {{7, 7}, {5, 2}, {3, 4}, {1, 6}, {6, 1}, {4, 3}, {2, 5}}; // sequence of inversion
        int index = direction ? 0 : 1;
        int simpleDiatonic = getSimpleDiatonic(diatonic);
        simpleDiatonic = diatonic + (patterns[simpleDiatonic - 1][index] * DIR);
        return simpleDiatonic > 0 ? simpleDiatonic : 7 + simpleDiatonic;
    }

    public Interval getInversion(boolean direction) {
        if (this.equals(PERFECT_UNISON)) {
            return direction ? PERFECT_OCTAVE_UP : PERFECT_OCTAVE_DOWN;
        }
//        if (direction && this.equals(PERFECT_OCTAVE_DOWN) || !direction && this.equals(PERFECT_OCTAVE_UP)) {
//            return PERFECT_UNISON;
//        }
        if (this.DIRECTION != direction && getSimpleDiatonic(this.COMPOUND_DIATONIC_NUMBER) == 1) {
            return this;
        }
        IntervalQuality nextIntervalQuality = this.INTERVAL_QUALITY.getInversion();
        int nextDiatonic = getNextInvertedDiatonic(this.COMPOUND_DIATONIC_NUMBER, direction);
        try {
            return new Interval(nextIntervalQuality.SHORT_NAME_SYMBOL + nextDiatonic, (direction == this.DIRECTION));
        }
        catch (Exception ex) {
            return new Interval(nextIntervalQuality.SHORT_NAME_SYMBOL + this.GENERALIZED_INTERVAL.getInversion().DIATONIC_NUMBER, (direction == this.DIRECTION));
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
        int semitoneDistance = direction
                ? PitchClass.getSemitoneDistanceBetween(lhs, rhs)
                : PitchClass.getSemitoneDistanceBetween(rhs, lhs);

        int generalizedDiatonic = 1 + (direction
                ? getVectorDistanceTo(lhs.ENHARMONIC_SPELLING.LETTER, rhs.ENHARMONIC_SPELLING.LETTER)
                : getVectorDistanceTo(rhs.ENHARMONIC_SPELLING.LETTER, lhs.ENHARMONIC_SPELLING.LETTER)) % 7;

        IntervalQuality generalizedIntervalQuality;
        if (generalizedDiatonic == 1 || generalizedDiatonic == 4 || generalizedDiatonic == 5 || generalizedDiatonic == 8) {
            generalizedIntervalQuality = IntervalQuality.PERFECT;
        }
        else {
            generalizedIntervalQuality = IntervalQuality.MAJOR;
        }
        if (generalizedIntervalQuality.equals(IntervalQuality.PERFECT)) {
            if (generalizedDiatonic < semitoneDistance) {
                if (generalizedDiatonic == 4) {
                    // [P4 (4 < 5)] -- correct
                    // [A4 (4 < 6)] -- P4
                    return new Interval((semitoneDistance == 5 ? "P" : "A") + generalizedDiatonic, direction);
                }
                else if (generalizedDiatonic == 5) {
                    // [d5 (5 < 6)] -- P5
                    // [P5 (5 < 7)] -- correct
                    // [A5 (5 < 8)] -- P5
                    if (semitoneDistance == 6) return new Interval("d" + generalizedDiatonic, direction);
                    else if (semitoneDistance == 7) return new Interval("P" + generalizedDiatonic, direction);
                    else return new Interval("A" + generalizedDiatonic, direction);
                }
                else {
                    // [M7 (1 < 11)] -- P1
                    return new Interval("M7", direction);
                }
            }
            else if (generalizedDiatonic > semitoneDistance) {
                // [P1 (1 > 0)] -- correct
                return new Interval("P" + generalizedDiatonic, direction);
            }
            else {
                // [d4 (4 == 4)] -- P4
                // [m2 (1 == 1)] -- P1
                if (semitoneDistance == 4) return new Interval("d" + generalizedDiatonic, direction);
                else return new Interval("m2", direction);
            }
        }
        else {
            if (generalizedDiatonic < semitoneDistance) {
                // [m7 (7 < 10)] -- M7
                // [M7 (7 < 11)] -- correct
                // [M3 (3 < 4)] -- correct
                // [m6 (6 < 8)] -- M6
                // [M7 (6 < 11)] -- M6
                if (generalizedDiatonic == 6) return new Interval((semitoneDistance == 8 ? "m6" : "M7"), direction);
                else if (semitoneDistance - generalizedDiatonic == 3) return new Interval("m" + generalizedDiatonic, direction);
                else return new Interval("M" + generalizedDiatonic, direction);
            }
            else if (generalizedDiatonic > semitoneDistance) {
                // [m2 (2 > 1)] -- M2
                // [m2 (3 > 1)] -- M3
                return new Interval("m2", direction);
            }
            else {
                // [M2 (2 == 2)] -- correct
                return new Interval("M2", direction);
            }
        }
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
