package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.general.Accidental;
import chordinnate.musictheory.pitch.interval.PitchInterval;

import static chordinnate.musictheory.pitch.interval.PitchInterval.*;
import static chordinnate.musictheory.pitch.interval.set.ScaleTypeProperty.*;

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
            ScaleTypeProperty.MAJOR,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    HARMONIC_MINOR(
            "Harmonic Minor",
            MINOR,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    MELODIC_MINOR_ASCENDING(
            "Melodic Minor (Ascending)",
            MINOR,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    MELODIC_MINOR_DESCENDING( // TODO: write in descending order
            "Melodic Minor (Descending)",
            MINOR,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    WHOLE_TONE(
            "Whole Tone",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, AUGMENTED_SIXTH
    ),
    PENTATONIC_MAJOR(
            "Pentatonic Major",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    PENTATONIC_MINOR(
            "Pentatonic Minor",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    PENTATONIC_BLUES(
            "Pentatonic Blues",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    PENTATONIC_NEUTRAL(
            "Pentatonic Neutral",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLUES(
            "Blues",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    OCTATONIC_HALF_WHOLE(
            "Octatonic (Half-Whole)",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    OCTATONIC_WHOLE_HALF(
            "Octatonic (Whole-Half)",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    IONIAN(
            "Ionian",
            MODAL,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    DORIAN(
            "Dorian",
            MODAL,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    PHRYGIAN(
            "Phrygian",
            MODAL,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    LYDIAN(
            "Lydian",
            MODAL,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    MIXOLYDIAN(
            "Mixolydian",
            MODAL,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    AEOLIAN(
            "Aeolian",
            MODAL,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    LOCRIAN(
            "Locrian",
            MODAL,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    ACOUSTIC(
            "Acoustic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    ADONAI_MALAKH(
            "Adonai Malakh",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    AEOLIAN_HARMONIC(
            "Aeolian Harmonic",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    AEOLIAN_MAJOR(
            "Aeolian Major",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    AEOLIAN_PENTATONIC(
            "Aeolian Pentatonic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    AEOLIAN_B1(
            "Aeolian b1",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    AHAVA_RABBA(
            "Ahava Rabba",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    AKEBONO_I(
            "Akebono I",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    AKEBONO_II(
            "Akebono II",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH
    ),
    ALGERIAN(
            "Algerian",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    ALHIJAZ(
            "Alhijaz",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    ALTERED_DIMINISHED(
            "Altered Diminished",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, DIMINISHED_SEVENTH
    ),
    ALTERED_DOMINANT(
            "Altered Dominant",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    ALTERED_LYDIAN(
            "Altered Lydian",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    ALTERED_MIXOLYDIAN_I(
            "Altered Mixolydian I",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    AMBASSEL(
            "Ambassel",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH
    ),
    ANCIENT_CHINESE(
            "Ancient Chinese",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    ANHEMITONIC_HEXATONIC(
            "Anhemitonic Hexatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, AUGMENTED_SIXTH
    ),
    ARABIC(
            "Arabic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    ARARAI(
            "Ararai",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    AREZZO_MAJOR_DIATONIC_HEXACHORD(
            "Arezzo Major Diatonic Hexachord",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    ASAWARI_THAAT(
            "Asawari Thaat",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    AUGMENTED_HEXATONIC(
            "Augmented Hexatonic",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    AUXILIARY_DIMINISHED(
            "Auxiliary Diminished",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    BACOVIA(
            "Bacovia",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    BALINESE(
            "Balinese",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH
    ),
    BANSHIKICHO(
            "Banshikicho",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BARTOK(
            "Bartok",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BATI(
            "Bati",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH
    ),
    BEBOP_DOMINANT(
            "Bebop Dominant",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    BEBOP_HALF_DIMINISHED(
            "Bebop Half-Diminished",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    BEBOP_MAJOR_I(
            "Bebop Major I",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    BHAIRAVI_THAAT(
            "Bhairavi Thaat",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    BHAIRAV_THAAT(
            "Bhairav Thaat",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    BHAIRUBAHAR_THAAT(
            "Bhairubahar Thaat",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    BILAWAL_THAAT(
            "Bilawal Thaat",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    BI_YU(
            "Bi Yu",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLACK_KEY_PENTATONIC(
            "\"Black Key\" Pentatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    BLUES_NINE_NOTE(
            "Blues Nine-Note",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BLUES_ENNEATONIC(
            "Blues Enneatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BLUES_HEPTATONIC(
            "Blues Heptatonic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLUES_HEXATONIC(
            "Blues Hexatonic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLUES_PENTATONIC(
            "Blues Pentatonic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    BLUES_I(
            "Blues I",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    BLUES_MAJOR_ASCENDING(
            "Blues Major (Ascending)",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    BLUES_MAJOR_DESCENDING( // TODO: write in descending order
            "Blues Major (Descending)",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    BLUES_MINOR_ASCENDING(
            "Blues Minor (Ascending)",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLUES_MINOR_DESCENDING( // TODO: write in descending order
            "Blues Minor (Descending)",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    BLUES_NONATONIC(
            "Blues Nonatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BLUES_III(
            "Blues III",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BLUES_IV(
            "Blues IV",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BLUES_V(
            "Blues V",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    BLUES_VI(
            "Blues VI",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    BLUESY_R_AND_R(
            "Bluesy R and R",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    BYZANTINE_LITURGICAL_CHROMATIC(
            "Byzantine Liturgical Chromatic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    BYZANTINE(
            "Byzantine",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    CHAD_GADYO(
            "Chad Gadyo",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH
    ),
    CHIAO(
            "Chiao",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    CHAIO_TWO(
            "Chaio Two",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    CHIN(
            "Chin",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    CHINESE(
            "Chinese",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SEVENTH
    ),
    CHING(
            "Ching",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SEVENTH
    ),
    CHROMATIC(
            "Chromatic",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH,
                    AUGMENTED_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, AUGMENTED_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_HARMONIC_ASCENDING(
            "Chromatic (Harmonic) Ascending",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    CHROMATIC_HARMONIC_DESCENDING( // TODO: write in descending order
            "Chromatic (Harmonic) Descending",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, AUGMENTED_FOURTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    CHROMATIC_MELODIC_ASCENDING(
            "Chromatic (Melodic) Ascending",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_UNISON, MAJOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH,
                    AUGMENTED_FOURTH, PERFECT_FIFTH, AUGMENTED_FIFTH, MAJOR_SIXTH, AUGMENTED_SIXTH
    ),
    CHROMATIC_MELODIC_DESCENDING( // TODO: write in descending order
            "Chromatic (Melodic) Descending",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH,
                    PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    CHROMATIC_AND_DIATONIC_DORIAN_MIXED(
            "Chromatic and Diatonic Dorian Mixed",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    CHROMATIC_PERMUTED_DIATONIC_DORIAN_MIXED(
            "Chromatic Permuted Diatonic Dorian Mixed",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_DORIAN(
            "Chromatic Dorian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH
    ),
    CHROMATIC_HYPODORIAN(
            "Chromatic Hypodorian",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH
    ),
    CHROMATIC_HYPODORIAN_INVERSE(
            "Chromatic Hypodorian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    CHROMATIC_HYPOLYDIAN(
            "Chromatic Hypolydian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_HYPOLYDIAN_INVERSE(
            "Chromatic Hypolydian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_HYPOPHRYGIAN(
            "Chromatic Hypophrygian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    CHROMATIC_HYPOPHRYGIAN_INVERSE(
            "Chromatic Hypophrygian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MAJOR_SIXTH
    ),
    CHROMATIC_LYDIAN(
            "Chromatic Lydian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_LYDIAN_INVERSE(
            "Chromatic Lydian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    CHROMATIC_MIXOLYDIAN(
            "Chromatic Mixolydian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    CHROMATIC_MIXOLYDIAN_INVERSE(
            "Chromatic Mixolydian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, DIMINISHED_FIFTH, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    CHROMATIC_PHRYGIAN(
            "Chromatic Phrygian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, MINOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    CHROMATIC_PHRYGIAN_INVERSE(
            "Chromatic Phrygian Inverse",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH
    ),
    CUSHAK(
            "Cushak",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DASTGAH_MAHUR(
            "Dastgah Mahur",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    DASTGAH_RAST_PANJGAH(
            "Dastgah Rast Panjgah",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    DEUTERUS_AUTHENTICUS(
            "Deuterus Authenticus",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DEUTERUS_PLAGIS(
            "Deuterus Plagis",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DIATONIC_MAJOR(
            "Diatonic Major",
            ScaleTypeProperty.MAJOR,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    DIATONIC_MINOR(
            "Diatonic Minor",
            ScaleTypeProperty.MINOR,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DIMINISHED(
            "Diminished",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    DIMINISHED_BLUES(
            "Diminished Blues",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DIMINISHED_FLAT_NINE(
            "Diminished "+ Accidental.FLAT.SYMBOL + "9", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, DIMINISHED_SEVENTH
    ),
    DIMINISHED_LOCRIAN(
            "Diminished Locrian",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, DIMINISHED_SEVENTH
    ),
    DIMINISHED_WHOLE_TONE(
            "Diminished Whole-Tone",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, DIMINISHED_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DIMINISHED_WHOLE_TONE_II(
            "Diminished Whole-Tone II",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, MINOR_SEVENTH
    ),
    DOMINANT_SEVENTH(
            "Dominant 7th",  // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DOMINANT_BEBOP(
            "Dominant Bebop",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    DOMINANT_DIMINISHED(
            "Dominant Diminished",
            NON_WESTERN,
            PERFECT_UNISON, AUGMENTED_UNISON, AUGMENTED_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_AEOLIAN_MIXED(
            "Dorian/Aeolian Mixed",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_BEBOP(
            "Dorian Bebop",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_FLAT_TWO(
            "Dorian " + Accidental.FLAT.SYMBOL + "2", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_FLAT_TWO_FLAT_FIVE(
            "Dorian " + Accidental.FLAT.SYMBOL + "2" + Accidental.FLAT.SYMBOL + "5", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_FLAT_FIVE(
            "Dorian " + Accidental.FLAT.SYMBOL + "5", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_FLAT_NINE(
            "Dorian " + Accidental.FLAT.SYMBOL + "9", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_GREEK(
            "Dorian Greek",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_MINOR(
            "Dorian Minor",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_PENTATONIC(
            "Dorian Pentatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    DORIAN_SHARP_FOUR(
            "Dorian " + Accidental.SHARP.SYMBOL + "4", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    DORIAN_SHARP_ELEVEN(
            "Dorian " + Accidental.SHARP.SYMBOL + "11", // FIXME(?): this name will throw an exception in the test class because it doesn't match the enum name
            NON_WESTERN,
            MAJOR_SECOND, MINOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH

    ),
    DORICO_FLAMENCO(
            "Dorico Flamenco",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    DOUBLE_HARMONIC_MAJOR(
            "Double Harmonic Major",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    DOUBLE_HARMONIC_MINOR(
            "Double Harmonic Minor",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, AUGMENTED_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    DOUBLE_PHRYGIAN_HEXATONIC(
            "Double-Phrygian Hexatonic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MAJOR_SIXTH
    ),
    EGYPTIAN(
            "Egyptian",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    EIGHT_NOTE_CHINESE(
            "Eight-Note Chinese",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),
    EIGHT_NOTE_DIMINISHED(
            "Eight-Note Diminished",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MAJOR_SEVENTH
    ),
    EIGHT_NOTE_SPANISH(
            "Eight-Note Spanish",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    ENIGMATIC(
            "Enigmatic",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MAJOR_THIRD, AUGMENTED_FOURTH, AUGMENTED_FIFTH, AUGMENTED_SIXTH, MAJOR_SEVENTH
    ),
    ETHIOPIAN(
            "Ethiopian",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    ESKIMO_HEPTATONIC(
            "Eskimo Heptatonic",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MAJOR_SIXTH, MINOR_SEVENTH
    ),
    ESKIMO_HEXATONIC_I(
            "Eskimo Hexatonic I",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SEVENTH
    ),
    ESKIMO_HEXATONIC_II_ALASKA_POINT_HOPE(
            "Eskimo Hexatonic II (Alaska: Point Hope)",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SIXTH, MAJOR_SEVENTH
    ),
    ESKIMO_TETRATONIC_ALASKA_BETHEL(
            "Eskimo Tetratonic (Alaska: Bethel)",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FIFTH
    ),
    ESPLAS(
            "Esplá's",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, MINOR_THIRD, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    EZEL(
            "Ezel",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    FIFTH_MODE(
            "Fifth Mode",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, AUGMENTED_SIXTH
    ),
    FIVE_NOTE_PROMETHEUS(
            "Five-Note Prometheus",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MAJOR_THIRD, DIMINISHED_FIFTH, MINOR_SEVENTH
    ),
    FOUR_SEMITONE_I(
            "Four Semitone I",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_THIRD, MINOR_SIXTH
    ),
    FOUR_SEMITONE_II(
            "Four Semitone II",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_THIRD, AUGMENTED_FIFTH
    ),
    FREYGISH(
            "Freygish",
            NON_WESTERN,
            PERFECT_UNISON, MINOR_SECOND, AUGMENTED_SECOND, MAJOR_THIRD, PERFECT_FOURTH, DIMINISHED_FIFTH, MINOR_SIXTH, MINOR_SEVENTH
    ),
    FULL_MINOR(
            "Full Minor",
            NON_WESTERN,
            PERFECT_UNISON, MAJOR_SECOND, MINOR_THIRD, PERFECT_FOURTH, PERFECT_FIFTH, MINOR_SIXTH, MAJOR_SIXTH, MINOR_SEVENTH, MAJOR_SEVENTH
    ),

    ;

    public final String NAME;
    private final PitchInterval[] PITCH_INTERVALS;
    public final ScaleTypeProperty SCALE_TYPE_PROPERTY;

    ScaleType(String name, ScaleTypeProperty scaleTypeProperty, PitchInterval... pitchIntervals) {
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

    public int length() {
        return PITCH_INTERVALS.length;
    }
}