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
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.NINE}
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
    SUSPENDED_FOUR(
            "sus4",
            new NashvilleInterval[] {ONE, FOUR, FIVE}
    ),
    SUSPENDED_TWO(
            "sus2",
            new NashvilleInterval[] {ONE, TWO, FIVE}
    ),
    MAJOR_NINE(
            "maj9",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SEVEN, NashvilleInterval.NINE}
    ),
    MAJOR_THIRTEEN(
            "maj13",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SEVEN,
                    NashvilleInterval.NINE, NashvilleInterval.ELEVEN, NashvilleInterval.THIRTEEN}
    ),
    MAJOR_NINE_SHARP_ELEVEN(
            "maj9"+SHARP.indicator+"11",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SEVEN,
                    NashvilleInterval.NINE, SHARP_ELEVEN}
    ),
    MAJOR_THIRTEEN_SHARP_ELEVEN(
            "maj13"+SHARP.indicator+"11",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SEVEN,
                    NashvilleInterval.NINE, SHARP_ELEVEN, NashvilleInterval.THIRTEEN}
    ),
    SIX(
            "6",
            new NashvilleInterval[] {ONE, THREE, FIVE,  NashvilleInterval.SIX}
    ),
    SIX_ADD_NINE(
            "6add9",
            new NashvilleInterval[] {ONE, THREE, FIVE, NashvilleInterval.SIX,
                    NashvilleInterval.NINE}
    ),
    MAJOR_SEVEN_SHARP_FIVE(
            "maj7"+SHARP.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.SHARP_FIVE, NashvilleInterval.SEVEN}
    ),
    MAJOR_SEVEN_FLAT_FIVE(
            "maj7"+FLAT.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, NashvilleInterval.SEVEN}
    ),
    MINOR_NINE(
            "m9",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE}
    ),
    MINOR_ELEVEN(
            "m11",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE, NashvilleInterval.ELEVEN}
    ),
    MINOR_THIRTEEN(
            "m13",
            new NashvilleInterval[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE, NashvilleInterval.THIRTEEN}
    ),
    MINOR_SIX(
            "m6",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SIX}
    ),
    MINOR_ADD_NINE(
            "madd9",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE,
                    NashvilleInterval.NINE}
    ),
    MINOR_SIX_ADD_NINE(
            "m6add9",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SIX,
                    NashvilleInterval.NINE}
    ),
    MINOR_MAJOR_SEVEN(
            "mM7",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SEVEN}
    ),
    MINOR_MAJOR_NINE(
            "mM9",
            new NashvilleInterval[] {ONE, FLAT_THREE, FIVE, NashvilleInterval.SEVEN,
                    NashvilleInterval.NINE}
    ),
    MINOR_SEVEN_FLAT_FIVE(
            "m7"+FLAT.indicator+"5",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN}
    ),
    HALF_DIMINISHED(
            "ø",
            new NashvilleInterval[] {ONE, FLAT_THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN} // TODO identical to m7b5 but has different symbol
    ),
    MINOR_SEVEN_SHARP_FIVE(
            "m7"+SHARP.indicator+"5",
            new NashvilleInterval[] {ONE, FLAT_THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    NINE(
            "9",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE}
    ),
    ELEVEN(
            "11",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE, NashvilleInterval.ELEVEN}
    ),
    THIRTEEN(
            "13",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE, NashvilleInterval.ELEVEN, NashvilleInterval.THIRTEEN}
    ),
    SEVEN_SUSPENDED_FOUR(
            "7sus4",
            new NashvilleInterval[] {ONE, FOUR, FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_FIVE(
            "7"+FLAT.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN}
    ),
    SEVEN_SHARP_FIVE(
            "7"+SHARP.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_NINE(
            "7"+FLAT.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.FLAT_NINE}
    ),
    SEVEN_SHARP_NINE(
            "7"+SHARP.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.SHARP_NINE}
    ),
    SEVEN_FLAT_FIVE_FLAT_NINE(
            "7"+FLAT.indicator+"5"+FLAT.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleInterval.FLAT_NINE}
    ),
    SEVEN_FLAT_FIVE_SHARP_NINE(
            "7"+FLAT.indicator+"5"+SHARP.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleInterval.SHARP_NINE}
    ),
    SEVEN_SHARP_FIVE_FLAT_NINE(
            "7"+SHARP.indicator+"5"+FLAT.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleInterval.FLAT_NINE}
    ),
    SEVEN_SHARP_FIVE_SHARP_NINE(
            "7"+SHARP.indicator+"5"+SHARP.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleInterval.SHARP_NINE}
    ),
    NINE_FLAT_FIVE(
            "9"+FLAT.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, NashvilleInterval.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE}
    ),
    NINE_SHARP_FIVE(
            "9"+SHARP.indicator+"5",
            new NashvilleInterval[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE}
    ),
    THIRTEEN_SHARP_ELEVEN(
            "13"+SHARP.indicator+"11",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.NINE, SHARP_ELEVEN, NashvilleInterval.THIRTEEN}
    ),
    THIRTEEN_FLAT_NINE(
            "13"+FLAT.indicator+"9",
            new NashvilleInterval[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.FLAT_NINE, NashvilleInterval.THIRTEEN}
    ),
    ELEVEN_FLAT_NINE(
            "11"+FLAT.indicator+"9",
            new NashvilleInterval[] {ONE, FIVE, FLAT_SEVEN,
                    NashvilleInterval.FLAT_NINE, NashvilleInterval.ELEVEN}
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

    final String chordSymbol;
    final NashvilleInterval[] nashvilleIntervals;

    ChordType(String chordSymbol, NashvilleInterval[] nashvilleIntervals) {
        this.chordSymbol = chordSymbol;
        this.nashvilleIntervals = nashvilleIntervals;
    }
}
