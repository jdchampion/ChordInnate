package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NashvilleInterval.*;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
public enum ChordType {
    MAJOR(
            "maj",
            new NashvilleInterval[] {ONE, THREE, FIVE}
    ),
    MAJOR_SEVEN(
            "maj7",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SEVEN}
    ),
    SEVEN(
            "7",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN}
    ),
    ADD_NINE(
            "add9",
            new NashvilleInterval[] {ONE, THREE, FIVE, TWO} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR(
            "m",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE}
    ),
    MINOR_SEVEN(
            "m7",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN}
    ),
    DIMINISHED(
            "dim",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE}
    ),
    DIMINISHED_SEVEN(
            "dim7",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE, NashvilleInterval.SIX}
    ),
    AUGMENTED(
            "aug",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE}
    ),
    SUSUSPENDED_FOUR(
            "sus4",
            new NashvilleInterval[] {ONE, FOUR, FIVE}
    ),
    SUSPENDED_TWO(
            "sus2",
            new NashvilleInterval[] {ONE, TWO, FIVE}
    ),
    MAJOR_NINE(
            "maj9",
            new NashvilleInterval[] {ONE, TWO, THREE, FIVE, NashvilleInterval.SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MAJ_THIRTEEN(
            "maj13",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, NashvilleInterval.SIX, NashvilleInterval.SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MAJOR_NINE_SHARP_ELEVEN(
            "maj9"+SHARP.getIndicator()+"11",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, NashvilleInterval.SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MAJOR_THIRTEEN_SHARP_ELEVEN(
            "maj13"+SHARP.getIndicator()+"11",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, NashvilleInterval.SIX, NashvilleInterval.SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SIX(
            "6",
            new NashvilleInterval[] {ONE, THREE, FIVE,  NashvilleInterval.SIX}
    ),
    SIX_ADD_NINE(
            "6add9",
            new NashvilleInterval[] {ONE, TWO, THREE, FIVE, NashvilleInterval.SIX} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MAJOR_SEVEN_SHARP_FIVE(
            "maj7"+SHARP.getIndicator()+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.SHARP_FIVE, NashvilleInterval.SEVEN}
    ),
    MAJOR_SEVEN_FLAT_FIVE(
            "maj7"+FLAT.getIndicator()+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, NashvilleInterval.SEVEN}
    ),
    MINOR_NINE(
            "m9",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR_ELEVEN(
            "m11",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR_THIRTEEN(
            "m13",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, NashvilleInterval.SIX, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR_SIX(
            "m6",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SIX}
    ),
    MINOR_ADD_NINE(
            "madd9",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FIVE} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR_SIX_ADD_NINE(
            "m6add9",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FIVE, NashvilleInterval.SIX} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    MINOR_MAJOR_SEVEN(
            "mM7",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SEVEN}
    ),
    MINOR_MAJOR_NINE(
            "mM9",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FIVE, NashvilleInterval.SEVEN}
    ),
    MINOR_SEVEN_FLAT_FIVE(
            "m7"+FLAT.getIndicator()+"5",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN}
    ),
    HALF_DIMINISHED(
            "ø",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN} // TODO identical to m7b5 but has different symbol
    ),
    MINOR_SEVEN_SHARP_FIVE(
            "m7"+SHARP.getIndicator()+"5",
            new NashvilleInterval[] {ONE, FLAT_THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    NINE(
            "9",
            new NashvilleInterval[] {ONE, TWO, THREE, FIVE, FLAT_SEVEN}
    ),
    ELEVEN(
            "11",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, FLAT_SEVEN}
    ),
    THIRTEEN(
            "13",
            new NashvilleInterval[] {ONE, TWO, THREE, FIVE, NashvilleInterval.SIX, FLAT_SEVEN}
    ),
    SEVEN_SUSPENDED_FOUR(
            "7sus4",
            new NashvilleInterval[] {ONE, FOUR, FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_FIVE(
            "7"+FLAT.getIndicator()+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN}
    ),
    SEVEN_SHARP_FIVE(
            "7"+SHARP.getIndicator()+"5",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_NINE(
            "7"+FLAT.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SEVEN_SHARP_NINE(
            "7"+SHARP.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_THREE, THREE, FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SEVEN_FLAT_FIVE_FLAT_NINE(
            "7"+FLAT.getIndicator()+"5"+FLAT.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SEVEN_FLAT_FIVE_SHARP_NINE(
            "7"+FLAT.getIndicator()+"5"+SHARP.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_THREE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SEVEN_SHARP_FIVE_FLAT_NINE(
            "7"+SHARP.getIndicator()+"5"+FLAT.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, SHARP_FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    SEVEN_SHARP_FIVE_SHARP_NINE(
            "7"+SHARP.getIndicator()+"5"+SHARP.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_THREE, THREE, SHARP_FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    NINE_FLAT_FIVE(
            "9"+FLAT.getIndicator()+"5",
            new NashvilleInterval[] {ONE, TWO, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN}
    ),
    NINE_SHARP_FIVE(
            "9"+SHARP.getIndicator()+"5",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    THIRTEEN_SHARP_ELEVEN(
            "13"+SHARP.getIndicator()+"11",
            new NashvilleInterval[] {ONE, TWO, THREE, NashvilleInterval.FLAT_FIVE, FIVE, NashvilleInterval.SIX, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    THIRTEEN_FLAT_NINE(
            "13"+FLAT.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, FIVE, NashvilleInterval.SEVEN, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    ELEVEN_FLAT_NINE(
            "11"+FLAT.getIndicator()+"9",
            new NashvilleInterval[] {ONE, FLAT_TWO, FOUR, FIVE, FLAT_SEVEN} // TODO chords with "nine" or above are inverted to fit one octave
    ),
    POWER_CHORD(
            "5",
            new NashvilleInterval[] {ONE, FIVE}
    ),
    SUSPENDED_TWO_SUSPENDED_FOUR(
            "sus2sus4",
            new NashvilleInterval[] {ONE, TWO, FOUR, FIVE}
    ),
    FLAT_FIVE(
            "-5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE}
    );

    String chordSymbol;
    NashvilleInterval[] relativePitches;

    ChordType(String chordSymbol, NashvilleInterval[] relativePitches) {
        this.chordSymbol = chordSymbol;
        this.relativePitches = relativePitches;
    }

    String getChordSymbol() {
        return chordSymbol;
    }
}
