package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;

import static chordinnate.musictheory.pitch.notation.Accidental.*;
import static chordinnate.musictheory.pitch.interval.PitchInterval.*;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
public enum ChordType {
    MAJOR(
            "maj",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH
    ),
    MAJOR_SEVEN(
            "maj7",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH
    ),
    SEVEN(
            "7",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    ADD_NINE(
            "add9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SECOND
    ),
    MINOR(
            "m",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH
    ),
    MINOR_SEVEN(
            "m7",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    DIMINISHED(
            "dim",
            PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH
    ),
    DIMINISHED_SEVEN(
            "dim7",
            PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH, MAJOR_SIXTH
    ),
    AUGMENTED(
            "aug",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH
    ),
    SUSPENDED_FOUR(
            "sus4",
            PERFECT_UNISON, PERFECT_FOURTH, PERFECT_FIFTH
    ),
    SUSPENDED_TWO(
            "sus2",
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FIFTH
    ),
    MAJOR_NINE(
            "maj9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND
    ),
    MAJOR_THIRTEEN(
            "maj13",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH
    ),
    MAJOR_NINE_SHARP_ELEVEN(
            "maj9"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, AUGMENTED_FOURTH
    ),
    MAJOR_THIRTEEN_SHARP_ELEVEN(
            "maj13"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, AUGMENTED_FOURTH, MAJOR_SIXTH
    ),
    SIX(
            "6",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    SIX_ADD_NINE(
            "6add9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SECOND
    ),
    MAJOR_SEVEN_SHARP_FIVE(
            "maj7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH, MAJOR_SEVENTH
    ),
    MAJOR_SEVEN_FLAT_FIVE(
            "maj7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH, MAJOR_SEVENTH
    ),
    MINOR_NINE(
            "m9",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND
    ),
    MINOR_ELEVEN(
            "m11",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH
    ),
    MINOR_THIRTEEN(
            "m13",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH
    ),
    MINOR_SIX(
            "m6",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    MINOR_ADD_NINE(
            "madd9",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SECOND
    ),
    MINOR_SIX_ADD_NINE(
            "m6add9",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SECOND
    ),
    MINOR_MAJOR_SEVEN(
            "mM7",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH
    ),
    MINOR_MAJOR_NINE(
            "mM9",
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND
    ),
    MINOR_SEVEN_FLAT_FIVE(
            "m7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    HALF_DIMINISHED(
            "ø",
            PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    MINOR_SEVEN_SHARP_FIVE(
            "m7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MINOR_THIRD, AUGMENTED_FIFTH, MINOR_SEVENTH
    ),
    NINE(
            "9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND
    ),
    ELEVEN(
            "11",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH
    ),
    THIRTEEN(
            "13",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH
    ),
    SEVEN_SUSPENDED_FOUR(
            "7sus4",
            PERFECT_UNISON, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    SEVEN_FLAT_FIVE(
            "7"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    SEVEN_SHARP_FIVE(
            "7"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH, MINOR_SEVENTH
    ),
    SEVEN_FLAT_NINE(
            "7"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, DIMINISHED_SECOND
    ),
    SEVEN_SHARP_NINE(
            "7"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, AUGMENTED_SECOND
    ),
    SEVEN_FLAT_FIVE_FLAT_NINE(
            "7"+FLAT.SYMBOL+"5"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH, DIMINISHED_SECOND
    ),
    SEVEN_FLAT_FIVE_SHARP_NINE(
            "7"+FLAT.SYMBOL+"5"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH, AUGMENTED_SECOND
    ),
    SEVEN_SHARP_FIVE_FLAT_NINE(
            "7"+SHARP.SYMBOL+"5"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH, MINOR_SEVENTH, DIMINISHED_SECOND
    ),
    SEVEN_SHARP_FIVE_SHARP_NINE(
            "7"+SHARP.SYMBOL+"5"+SHARP.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH, MINOR_SEVENTH, AUGMENTED_SECOND
    ),
    NINE_FLAT_FIVE(
            "9"+FLAT.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH, MAJOR_SECOND
    ),
    NINE_SHARP_FIVE(
            "9"+SHARP.SYMBOL+"5",
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH, MINOR_SEVENTH, MAJOR_SECOND
    ),
    THIRTEEN_SHARP_ELEVEN(
            "13"+SHARP.SYMBOL+"11",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, AUGMENTED_FOURTH, MAJOR_SIXTH
    ),
    THIRTEEN_FLAT_NINE(
            "13"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, DIMINISHED_SECOND, MAJOR_SIXTH
    ),
    ELEVEN_FLAT_NINE(
            "11"+FLAT.SYMBOL+"9",
            PERFECT_UNISON, PERFECT_FIFTH, MINOR_SEVENTH, DIMINISHED_SECOND, PERFECT_FOURTH
    ),
    POWER_CHORD(
            "5",
            PERFECT_UNISON, PERFECT_FIFTH
    ),
    SUSPENDED_TWO_SUSPENDED_FOUR(
            "sus2sus4",
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH
    ),
    FLAT_FIVE(
            "-5",
            PERFECT_UNISON, MAJOR_THIRD, DIMINISHED_FIFTH
    ),

    ;

    public final String SYMBOL;
    private final PitchInterval[] PITCH_INTERVALS;
    private final Octave[] BASE_OCTAVES;

    ChordType(String chordSymbol, PitchInterval... pitchIntervals) {
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

    public PitchInterval[] getPitchIntervals() {
        // Return a copy of the array (to protect against mutation)
        PitchInterval[] pitchIntervals = new PitchInterval[PITCH_INTERVALS.length];
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
