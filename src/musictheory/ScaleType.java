package musictheory;

import static musictheory.Tonality.*;
import static musictheory.NashvilleInterval.*;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public enum ScaleType {
    MAJOR(
            "Major",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MAJOR_TONALITY
    ),
    HARMONIC_MINOR(
            "Harmonic Minor",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_ASCENDING(
            "Melodic Minor (Ascending)",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_DESCENDING(
            "Melodic Minor (Descending)",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY
    ),
    CHROMATIC(
            "Chromatic",
            new NashvilleInterval[] {ONE, SHARP_ONE, TWO, SHARP_TWO, THREE, FOUR,
                    SHARP_FOUR, FIVE, SHARP_FIVE, SIX, SHARP_SIX, SEVEN
            },
            CHROMATIC_TONALITY
    ),
    WHOLE_TONE(
            "Whole Tone",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SHARP_SIX},
            NO_TONALITY
    ),
    PENTATONIC_MAJOR(
            "Pentatonic Major",
            new NashvilleInterval[] {ONE, TWO, THREE, FIVE, SIX},
            NO_TONALITY
    ),
    PENTATONIC_MINOR(
            "Pentatonic Minor",
            new NashvilleInterval[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    PENTATONIC_BLUES(
            "Pentatonic Blues",
            new NashvilleInterval[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    PENTATONIC_NEUTRAL(
            "Pentatonic Neutral",
            new NashvilleInterval[] {ONE, TWO, FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    BLUES(
            "Blues",
            new NashvilleInterval[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    OCTATONIC_HALF_WHOLE(
            "Octatonic (Half-Whole)",
            new NashvilleInterval[] {ONE, FLAT_TWO, FLAT_THREE, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    OCTATONIC_WHOLE_HALF(
            "Octatonic (Whole-Half)",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    IONIAN(
            "Ionian",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MODAL_TONALITY
    ),
    DORIAN(
            "Dorian",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    PHRYGIAN(
            "Phrygian",
            new NashvilleInterval[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    LYDIAN(
            "Lydian",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, SEVEN},
            MODAL_TONALITY
    ),
    MIXOLYDIAN(
            "Mixolydian",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    AEOLIAN(
            "Aeolian",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    LOCRIAN(
            "Locrian",
            new NashvilleInterval[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    ACOUSTIC(
            "Acoustic",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ADONAI_MALAKH(
            "Adonai Malakh",
            new NashvilleInterval[] {ONE, FLAT_TWO, TWO, FLAT_THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            ENNATONIC_TONALITY
    ),
    AEOLIAN_HARMONIC(
            "Aeolian Harmonic",
            new NashvilleInterval[] {ONE, SHARP_TWO, THREE, SHARP_FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AEOLIAN_MAJOR(
            "Aeolian Major",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    AEOLIAN_PENTATONIC(
            "Aeolian Pentatonic",
            new NashvilleInterval[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN},
            HEXATONIC_TONALITY
    ),
    AEOLIAN_B1(
            "Aeolian b1",
            new NashvilleInterval[] {ONE, SHARP_TWO, THREE, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AHAVA_RABBA(
            "Ahava Rabba",
            new NashvilleInterval[] {ONE, FLAT_TWO, SHARP_TWO, THREE, FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            ENNATONIC_TONALITY
    ),
    AKEBONO_I(
            "Akebono I",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FIVE, SIX},
            HEXATONIC_TONALITY
    ),
    AKEBONO_II(
            "Akebono II",
            new NashvilleInterval[] {ONE, FLAT_TWO, FOUR, FIVE, FLAT_SIX},
            HEXATONIC_TONALITY
    ),
    ALGERIAN(
            "Algerian",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SIX, SEVEN},
            ENNATONIC_TONALITY
    ),
    ALHIJAZ(
            "Alhijaz",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_DIMINISHED(
            "Altered Diminished",
            new NashvilleInterval[] {ONE, FLAT_TWO, FLAT_THREE, FLAT_FOUR, FLAT_FIVE, FLAT_SIX, DOUBLE_FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_DOMINIANT(
            "Altered Dominant",
            new NashvilleInterval[] {ONE, FLAT_TWO, FLAT_THREE, FLAT_FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_LYDIAN(
            "Altered Lydian",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_MIXOLYDIAN_I(
            "Altered Mixolydian I",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SHARP_FIVE, SIX, SEVEN},
            ENNATONIC_TONALITY
    ),
    AMBASSEL(
            "Ambassel",
            new NashvilleInterval[] {ONE, FLAT_TWO, FOUR, FIVE, FLAT_SIX},
            HEXATONIC_TONALITY
    ),
    ANCIENT_CHINESE(
            "Ancient Chinese",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    ANHEMITONIC_HEXATONIC(
            "Anhemitonic Hexatonic",
            new NashvilleInterval[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SHARP_SIX},
            HEPTATONIC_TONALITY
    ),
    ARABIC(
            "Arabic",
            new NashvilleInterval[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ARARAI(
            "Ararai",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AREZZO_MAJOR_DIATONIC_HEXACHORD(
            "Arezzo Major Diatonic Hexachord",
            new NashvilleInterval[] {ONE, TWO, THREE, FOUR, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    ASAWARI_THAAT(
            "Asawari Thaat",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    AUGMENTED_HEXATONIC(
            "Augmented Hexatonic",
            new NashvilleInterval[] {ONE, SHARP_TWO, THREE, FIVE, FLAT_SIX, SEVEN},
            HEPTATONIC_TONALITY
    ),
    AUXILIARY_DIMINISHED(
            "Auxiliary Diminished",
            new NashvilleInterval[] {ONE, TWO, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SIX, SIX, SEVEN},
            ENNATONIC_TONALITY
    ),
    ;

    final String name;
    final NashvilleInterval[] intervals;
    final Tonality tonality;

    ScaleType(String name, NashvilleInterval[] intervals, Tonality tonality) {
        this.name = name;
        this.intervals = intervals;
        this.tonality = tonality;
    }
}

enum Tonality {
    MAJOR_TONALITY,
    MINOR_TONALITY,
    NO_TONALITY,
    CHROMATIC_TONALITY,
    HEXATONIC_TONALITY,
    HEPTATONIC_TONALITY,
    OCTATONIC_TONALITY,
    ENNATONIC_TONALITY,
    MODAL_TONALITY
}
