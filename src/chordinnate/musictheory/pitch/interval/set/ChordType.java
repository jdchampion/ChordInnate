package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.interval.Interval;
import chordinnate.musictheory.pitch.interval.Octave;

import static chordinnate.musictheory.pitch.Accidental.*;
import static chordinnate.musictheory.pitch.interval.Interval.*;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
public enum ChordType {
    MAJOR(
            "maj",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP
    ),
    MAJOR_SEVEN(
            "maj7",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP
    ),
    SEVEN(
            "7",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    ADD_NINE(
            "add9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SECOND_UP
    ),
    MINOR(
            "m",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP
    ),
    MINOR_SEVEN(
            "m7",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    DIMINISHED(
            "dim",
            PERFECT_UNISON, MINOR_THIRD_UP, DIMINISHED_FIFTH_UP
    ),
    DIMINISHED_SEVEN(
            "dim7",
            PERFECT_UNISON, MINOR_THIRD_UP, DIMINISHED_FIFTH_UP, MAJOR_SIXTH_UP
    ),
    AUGMENTED(
            "aug",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP
    ),
    SUSPENDED_FOUR(
            "sus4",
            PERFECT_UNISON, PERFECT_FOURTH_UP, PERFECT_FIFTH_UP
    ),
    SUSPENDED_TWO(
            "sus2",
            PERFECT_UNISON, MAJOR_SECOND_UP, PERFECT_FIFTH_UP
    ),
    MAJOR_NINE(
            "maj9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    MAJOR_THIRTEEN(
            "maj13",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP, MAJOR_SECOND_UP, PERFECT_FOURTH_UP, MAJOR_SIXTH_UP
    ),
    MAJOR_NINE_SHARP_ELEVEN(
            "maj9"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP, MAJOR_SECOND_UP, AUGMENTED_FOURTH_UP
    ),
    MAJOR_THIRTEEN_SHARP_ELEVEN(
            "maj13"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP, MAJOR_SECOND_UP, AUGMENTED_FOURTH_UP, MAJOR_SIXTH_UP
    ),
    SIX(
            "6",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SIXTH_UP
    ),
    SIX_ADD_NINE(
            "6add9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SIXTH_UP, MAJOR_SECOND_UP
    ),
    MAJOR_SEVEN_SHARP_FIVE(
            "maj7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP, MAJOR_SEVENTH_UP
    ),
    MAJOR_SEVEN_FLAT_FIVE(
            "maj7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP, MAJOR_SEVENTH_UP
    ),
    MINOR_NINE(
            "m9",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    MINOR_ELEVEN(
            "m11",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP, PERFECT_FOURTH_UP
    ),
    MINOR_THIRTEEN(
            "m13",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP, PERFECT_FOURTH_UP, MAJOR_SIXTH_UP
    ),
    MINOR_SIX(
            "m6",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SIXTH_UP
    ),
    MINOR_ADD_NINE(
            "madd9",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SECOND_UP
    ),
    MINOR_SIX_ADD_NINE(
            "m6add9",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SIXTH_UP, MAJOR_SECOND_UP
    ),
    MINOR_MAJOR_SEVEN(
            "mM7",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP
    ),
    MINOR_MAJOR_NINE(
            "mM9",
            PERFECT_UNISON, MINOR_THIRD_UP, PERFECT_FIFTH_UP, MAJOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    MINOR_SEVEN_FLAT_FIVE(
            "m7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MINOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    HALF_DIMINISHED(
            "ø",
            PERFECT_UNISON, MINOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    MINOR_SEVEN_SHARP_FIVE(
            "m7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MINOR_THIRD_UP, AUGMENTED_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    NINE(
            "9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    ELEVEN(
            "11",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP, PERFECT_FOURTH_UP
    ),
    THIRTEEN(
            "13",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP, PERFECT_FOURTH_UP, MAJOR_SIXTH_UP
    ),
    SEVEN_SUSPENDED_FOUR(
            "7sus4",
            PERFECT_UNISON, PERFECT_FOURTH_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    SEVEN_FLAT_FIVE(
            "7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    SEVEN_SHARP_FIVE(
            "7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP, MINOR_SEVENTH_UP
    ),
    SEVEN_FLAT_NINE(
            "7"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, DIMINISHED_SECOND_UP
    ),
    SEVEN_SHARP_NINE(
            "7"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, AUGMENTED_SECOND_UP
    ),
    SEVEN_FLAT_FIVE_FLAT_NINE(
            "7"+FLAT.SYMBOL+"5"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP, DIMINISHED_SECOND_UP
    ),
    SEVEN_FLAT_FIVE_SHARP_NINE(
            "7"+FLAT.SYMBOL+"5"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP, AUGMENTED_SECOND_UP
    ),
    SEVEN_SHARP_FIVE_FLAT_NINE(
            "7"+SHARP.SYMBOL+"5"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP, MINOR_SEVENTH_UP, DIMINISHED_SECOND_UP
    ),
    SEVEN_SHARP_FIVE_SHARP_NINE(
            "7"+SHARP.SYMBOL+"5"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP, MINOR_SEVENTH_UP, AUGMENTED_SECOND_UP
    ),
    NINE_FLAT_FIVE(
            "9"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    NINE_SHARP_FIVE(
            "9"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD_UP, AUGMENTED_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP
    ),
    THIRTEEN_SHARP_ELEVEN(
            "13"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, MAJOR_SECOND_UP, AUGMENTED_FOURTH_UP, MAJOR_SIXTH_UP
    ),
    THIRTEEN_FLAT_NINE(
            "13"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD_UP, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, DIMINISHED_SECOND_UP, MAJOR_SIXTH_UP
    ),
    ELEVEN_FLAT_NINE(
            "11"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, PERFECT_FIFTH_UP, MINOR_SEVENTH_UP, DIMINISHED_SECOND_UP, PERFECT_FOURTH_UP
    ),
    POWER_CHORD(
            "5",
            PERFECT_UNISON, PERFECT_FIFTH_UP
    ),
    SUSPENDED_TWO_SUSPENDED_FOUR(
            "sus2sus4",
            PERFECT_UNISON, MAJOR_SECOND_UP, PERFECT_FOURTH_UP, PERFECT_FIFTH_UP
    ),
    FLAT_FIVE(
            "-5",
            PERFECT_UNISON, MAJOR_THIRD_UP, DIMINISHED_FIFTH_UP
    ),

    ;

    public final String SYMBOL;
    private final Interval[] PITCH_INTERVALS;
    private final Octave[] BASE_OCTAVES;

    ChordType(String chordSymbol, Interval... pitchIntervals) {
        this.SYMBOL = chordSymbol;
        this.PITCH_INTERVALS = pitchIntervals;
        this.BASE_OCTAVES = new Octave[pitchIntervals.length];
        BASE_OCTAVES[0] = Octave.OCTAVE_0;
        for (int i = 1; i < BASE_OCTAVES.length; i++) {
            if (PITCH_INTERVALS[i].NUM_SEMITONES < PITCH_INTERVALS[i - 1].NUM_SEMITONES) {
                BASE_OCTAVES[i] = Octave.OCTAVE_1;
            }
            else {
                BASE_OCTAVES[i] = Octave.OCTAVE_0;
            }
        }
    }

    public Interval[] getPitchIntervals() {
        // Return a copy of the array (to protect against mutation)
        Interval[] pitchIntervals = new Interval[PITCH_INTERVALS.length];
        System.arraycopy(PITCH_INTERVALS, 0, pitchIntervals, 0, PITCH_INTERVALS.length);
        return pitchIntervals;
    }

    public Octave[] getBaseOctaves() {
        // Return a copy of the array (to protect against mutation)
        Octave[] octaves = new Octave[BASE_OCTAVES.length];
        System.arraycopy(BASE_OCTAVES, 0, octaves, 0, BASE_OCTAVES.length);
        return octaves;
    }

    public int length() {
        return PITCH_INTERVALS.length;
    }
}
