package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.interval.PitchInterval;

import static chordinnate.musictheory.pitch.interval.PitchInterval.*;

enum ScaleTypeProperty {
    MAJOR,
    MINOR,
    MODAL,
    NON_WESTERN
}

/**
 * Created by Joseph on 1/1/16.
 *
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public enum ScaleType {
    // NOTE: SCALE TYPES MUST BE LISTED IN ASCENDING ORDER (EVEN IF THEY ARE CONSIDERED "DESCENDING" SCALES)

    MAJOR(
            "Major",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.MAJOR
    ),
    HARMONIC_MINOR(
            "Harmonic Minor",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.MINOR
    ),
    MELODIC_MINOR_ASCENDING(
            "Melodic Minor (Ascending)",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.MINOR
    ),
    MELODIC_MINOR_DESCENDING( // TODO: write in descending order
            "Melodic Minor (Descending)",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MINOR
    ),
    WHOLE_TONE(
            "Whole Tone",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, AUGMENTED_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    PENTATONIC_MAJOR(
            "Pentatonic Major",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    PENTATONIC_MINOR(
            "Pentatonic Minor",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    PENTATONIC_BLUES(
            "Pentatonic Blues",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    PENTATONIC_NEUTRAL(
            "Pentatonic Neutral",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES(
            "Blues",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    OCTATONIC_HALF_WHOLE(
            "Octatonic (Half-Whole)",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    OCTATONIC_WHOLE_HALF(
            "Octatonic (Whole-Half)",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    IONIAN(
            "Ionian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    DORIAN(
            "Dorian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    PHRYGIAN(
            "Phrygian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    LYDIAN(
            "Lydian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    MIXOLYDIAN(
            "Mixolydian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    AEOLIAN(
            "Aeolian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    LOCRIAN(
            "Locrian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.MODAL
    ),
    ACOUSTIC(
            "Acoustic",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ADONAI_MALAKH(
            "Adonai Malakh",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AEOLIAN_HARMONIC(
            "Aeolian Harmonic",
            new PitchInterval[] {PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AEOLIAN_MAJOR(
            "Aeolian Major",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AEOLIAN_PENTATONIC(
            "Aeolian Pentatonic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AEOLIAN_B1(
            "Aeolian b1",
            new PitchInterval[] {PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AHAVA_RABBA(
            "Ahava Rabba",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AKEBONO_I(
            "Akebono I",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AKEBONO_II(
            "Akebono II",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALGERIAN(
            "Algerian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALHIJAZ(
            "Alhijaz",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALTERED_DIMINISHED(
            "Altered Diminished",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, DIMINISHED_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALTERED_DOMINANT(
            "Altered Dominant",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALTERED_LYDIAN(
            "Altered Lydian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ALTERED_MIXOLYDIAN_I(
            "Altered Mixolydian I",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AMBASSEL(
            "Ambassel",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ANCIENT_CHINESE(
            "Ancient Chinese",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ANHEMITONIC_HEXATONIC(
            "Anhemitonic Hexatonic",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, AUGMENTED_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ARABIC(
            "Arabic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ARARAI(
            "Ararai",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AREZZO_MAJOR_DIATONIC_HEXACHORD(
            "Arezzo Major Diatonic Hexachord",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    ASAWARI_THAAT(
            "Asawari Thaat",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AUGMENTED_HEXATONIC(
            "Augmented Hexatonic",
            new PitchInterval[] {PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    AUXILIARY_DIMINISHED(
            "Auxiliary Diminished",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BACOVIA(
            "Bacovia",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BALINESE(
            "Balinese",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BANSHIKICHO(
            "Banshikicho",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BARTOK(
            "Bartok",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BATI(
            "Bati",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BEBOP_DOMINANT(
            "Bebop Dominant",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BEBOP_HALF_DIMINISHED(
            "Bebop Half-Diminished",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BEBOP_MAJOR_I(
            "Bebop Major I",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BHAIRAVI_THAAT(
            "Bhairavi Thaat",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BHAIRAV_THAAT(
            "Bhairav Thaat",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BHAIRUBAHAR_THAAT(
            "Bhairubahar Thaat",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BILAWAL_THAAT(
            "Bilawal Thaat",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BI_YU(
            "Bi Yu",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLACK_KEY_PENTATONIC(
            "\"Black Key\" Pentatonic",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_NINE_NOTE(
            "Blues Nine-Note",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_ENNEATONIC(
            "Blues Enneatonic",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_HEPTATONIC(
            "Blues Heptatonic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_HEXATONIC(
            "Blues Hexatonic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_PENTATONIC(
            "Blues Pentatonic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_I(
            "Blues I",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_MAJOR_ASCENDING(
            "Blues Major (Ascending)",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_MAJOR_DESCENDING( // TODO: write in descending order
            "Blues Major (Descending)",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_MINOR_ASCENDING(
            "Blues Minor (Ascending)",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_MINOR_DESCENDING( // TODO: write in descending order
            "Blues Minor (Descending)",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_NONATONIC(
            "Blues Nonatonic",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_III(
            "Blues III",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_IV(
            "Blues IV",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_V(
            "Blues V",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUES_VI(
            "Blues VI",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BLUESY_R_AND_R(
            "Bluesy R and R",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BYZANTINE_LITURGICAL_CHROMATIC(
            "Byzantine Liturgical Chromatic",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    BYZANTINE(
            "Byzantine",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHAD_GADYO(
            "Chad Gadyo",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHIAO(
            "Chiao",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHAIO_TWO(
            "Chaio Two",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHIN(
            "Chin",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHINESE(
            "Chinese",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHING(
            "Ching",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC(
            "Chromatic",
            new PitchInterval[] {PERFECT_UNISON, AUGMENTED_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH,
                    AUGMENTED_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, AUGMENTED_SIXTH, MAJOR_SEVENTH
            },
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HARMONIC_ASCENDING(
            "Chromatic (Harmonic) Ascending",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH
            },
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HARMONIC_DESCENDING( // TODO: write in descending order
            "Chromatic (Harmonic) Descending",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
            },
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_MELODIC_ASCENDING(
            "Chromatic (Melodic) Ascending",
            new PitchInterval[] {PERFECT_UNISON, AUGMENTED_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH,
                    PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, AUGMENTED_SIXTH
            },
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_MELODIC_DESCENDING( // TODO: write in descending order
            "Chromatic (Melodic) Descending",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
            },
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_AND_DIATONIC_DORIAN_MIXED(
            "Chromatic and Diatonic Dorian Mixed",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_PERMUTED_DIATONIC_DORIAN_MIXED(
            "Chromatic Permuted Diatonic Dorian Mixed",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_DORIAN(
            "Chromatic Dorian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPODORIAN(
            "Chromatic Hypodorian",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPODORIAN_INVERSE(
            "Chromatic Hypodorian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPOLYDIAN(
            "Chromatic Hypolydian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPOLYDIAN_INVERSE(
            "Chromatic Hypolydian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPOPHRYGIAN(
            "Chromatic Hypophrygian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_HYPOPHRYGIAN_INVERSE(
            "Chromatic Hypophrygian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_LYDIAN(
            "Chromatic Lydian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_LYDIAN_INVERSE(
            "Chromatic Lydian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_MIXOLYDIAN(
            "Chromatic Mixolydian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_MIXOLYDIAN_INVERSE(
            "Chromatic Mixolydian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_PHRYGIAN(
            "Chromatic Phrygian",
            new PitchInterval[] {PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CHROMATIC_PHRYGIAN_INVERSE(
            "Chromatic Phrygian Inverse",
            new PitchInterval[] {PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH},
            ScaleTypeProperty.NON_WESTERN
    ),
    CUSHAK(
            "Cushak",
            new PitchInterval[] {PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH},
            ScaleTypeProperty.NON_WESTERN
    ),

    ;

    public final String NAME;
    private final PitchInterval[] PITCH_INTERVALS;
    public final ScaleTypeProperty SCALE_TYPE_PROPERTY;

    ScaleType(String name, PitchInterval[] pitchIntervals, ScaleTypeProperty scaleTypeProperty) {
        this.NAME = name;
        this.PITCH_INTERVALS = pitchIntervals;
        this.SCALE_TYPE_PROPERTY = scaleTypeProperty;
    }

    public PitchInterval[] getPitchIntervals() {
        // Return a copy of the array (to protect against mutation)
        PitchInterval[] pitchIntervals = new PitchInterval[PITCH_INTERVALS.length];
        System.arraycopy(PITCH_INTERVALS, 0, pitchIntervals, 0, PITCH_INTERVALS.length);
        return pitchIntervals;
    }
}
