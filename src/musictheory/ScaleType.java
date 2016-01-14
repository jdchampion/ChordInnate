package musictheory;

import static musictheory.Tonality.*;
import static musictheory.NashvilleNumber.*;

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
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MAJOR_TONALITY
    ),
    HARMONIC_MINOR(
            "Harmonic Minor",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_ASCENDING(
            "Melodic Minor (Ascending)",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, SEVEN},
            MINOR_TONALITY
    ),
    MELODIC_MINOR_DESCENDING( // TODO: write in descending order when Scale.setSteps() supports descending order
            "Melodic Minor (Descending)",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MINOR_TONALITY
    ),
    CHROMATIC(
            "Chromatic",
            new NashvilleNumber[] {ONE, SHARP_ONE, TWO, SHARP_TWO, THREE, FOUR,
                    SHARP_FOUR, FIVE, SHARP_FIVE, SIX, SHARP_SIX, SEVEN
            },
            CHROMATIC_TONALITY
    ),
    WHOLE_TONE(
            "Whole Tone",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SHARP_SIX},
            NO_TONALITY
    ),
    PENTATONIC_MAJOR(
            "Pentatonic Major",
            new NashvilleNumber[] {ONE, TWO, THREE, FIVE, SIX},
            NO_TONALITY
    ),
    PENTATONIC_MINOR(
            "Pentatonic Minor",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    PENTATONIC_BLUES(
            "Pentatonic Blues",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    PENTATONIC_NEUTRAL(
            "Pentatonic Neutral",
            new NashvilleNumber[] {ONE, TWO, FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    BLUES(
            "Blues",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SEVEN},
            NO_TONALITY
    ),
    OCTATONIC_HALF_WHOLE(
            "Octatonic (Half-Whole)",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    OCTATONIC_WHOLE_HALF(
            "Octatonic (Whole-Half)",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    IONIAN(
            "Ionian",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            MODAL_TONALITY
    ),
    DORIAN(
            "Dorian",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    PHRYGIAN(
            "Phrygian",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    LYDIAN(
            "Lydian",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, SEVEN},
            MODAL_TONALITY
    ),
    MIXOLYDIAN(
            "Mixolydian",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    AEOLIAN(
            "Aeolian",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    LOCRIAN(
            "Locrian",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            MODAL_TONALITY
    ),
    ACOUSTIC(
            "Acoustic",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ADONAI_MALAKH(
            "Adonai Malakh",
            new NashvilleNumber[] {ONE, FLAT_TWO, TWO, FLAT_THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            ENNEATONIC_TONALITY
    ),
    AEOLIAN_HARMONIC(
            "Aeolian Harmonic",
            new NashvilleNumber[] {ONE, SHARP_TWO, THREE, SHARP_FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AEOLIAN_MAJOR(
            "Aeolian Major",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    AEOLIAN_PENTATONIC(
            "Aeolian Pentatonic",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FIVE, FLAT_SEVEN},
            HEXATONIC_TONALITY
    ),
    AEOLIAN_B1(
            "Aeolian b1",
            new NashvilleNumber[] {ONE, SHARP_TWO, THREE, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AHAVA_RABBA(
            "Ahava Rabba",
            new NashvilleNumber[] {ONE, FLAT_TWO, SHARP_TWO, THREE, FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            ENNEATONIC_TONALITY
    ),
    AKEBONO_I(
            "Akebono I",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FIVE, SIX},
            HEXATONIC_TONALITY
    ),
    AKEBONO_II(
            "Akebono II",
            new NashvilleNumber[] {ONE, FLAT_TWO, FOUR, FIVE, FLAT_SIX},
            HEXATONIC_TONALITY
    ),
    ALGERIAN(
            "Algerian",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, SHARP_FOUR, FIVE, FLAT_SIX, SEVEN},
            ENNEATONIC_TONALITY
    ),
    ALHIJAZ(
            "Alhijaz",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_DIMINISHED(
            "Altered Diminished",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FLAT_FOUR, FLAT_FIVE, FLAT_SIX, DOUBLE_FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_DOMINIANT(
            "Altered Dominant",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FLAT_FOUR, FLAT_FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_LYDIAN(
            "Altered Lydian",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    ALTERED_MIXOLYDIAN_I(
            "Altered Mixolydian I",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SHARP_FIVE, SIX, SEVEN},
            ENNEATONIC_TONALITY
    ),
    AMBASSEL(
            "Ambassel",
            new NashvilleNumber[] {ONE, FLAT_TWO, FOUR, FIVE, FLAT_SIX},
            HEXATONIC_TONALITY
    ),
    ANCIENT_CHINESE(
            "Ancient Chinese",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    ANHEMITONIC_HEXATONIC(
            "Anhemitonic Hexatonic",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, SHARP_FIVE, SHARP_SIX},
            HEPTATONIC_TONALITY
    ),
    ARABIC(
            "Arabic",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    ARARAI(
            "Ararai",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    AREZZO_MAJOR_DIATONIC_HEXACHORD(
            "Arezzo Major Diatonic Hexachord",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    ASAWARI_THAAT(
            "Asawari Thaat",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    AUGMENTED_HEXATONIC(
            "Augmented Hexatonic",
            new NashvilleNumber[] {ONE, SHARP_TWO, THREE, FIVE, FLAT_SIX, SEVEN},
            HEPTATONIC_TONALITY
    ),
    AUXILIARY_DIMINISHED(
            "Auxiliary Diminished",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SIX, SIX, SEVEN},
            ENNEATONIC_TONALITY
    ),
    BACOVIA(
            "Bacovia",
            new NashvilleNumber[] {ONE, THREE, FOUR, FLAT_SIX, SEVEN},
            HEPTATONIC_TONALITY
    ),
    BALINESE(
            "Balinese",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FIVE, FLAT_SIX},
            HEXATONIC_TONALITY
    ),
    BANSHIKICHO(
            "Banshikicho",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FLAT_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    BARTOK(
            "Bartok",
            new NashvilleNumber[] {ONE, TWO, THREE, SHARP_FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    BATI(
            "Bati",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FIVE},
            HEXATONIC_TONALITY
    ),
    BEBOP_DOMINANT(
            "Bebop Dominant",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SIX, FLAT_SEVEN, SEVEN},
            ENNEATONIC_TONALITY
    ),
    BEBOP_HALF_DIMINISHED(
            "Bebop Half-Diminished",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SIX, SEVEN},
            ENNEATONIC_TONALITY
    ),
    BEBOP_MAJOR_I(
            "Bebop Major I",
            new NashvilleNumber[] {ONE, TWO, THREE, FOUR, FIVE, SHARP_FIVE, SIX, SEVEN},
            ENNEATONIC_TONALITY
    ),
    BHAIRAVI_THAAT(
            "Bhairavi Thaat",
            new NashvilleNumber[] {ONE, FLAT_TWO, FLAT_THREE, FOUR, FIVE, FLAT_SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    BHAIRAV_THAAT(
            "Bhairav Thaat",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    BHAIRUBAHAR_THAAT(
            "Bhairubahar Thaat",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    BILAWAL_THAAT(
            "Bilawal Thaat",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FIVE, SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    BI_YU(
            "Bi Yu",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN},
            PENTATONIC_TONALITY
    ),
    BLACK_KEY_PENTATONIC(
            "\"Black Key\" Pentatonic",
            new NashvilleNumber[] {ONE, TWO, FOUR, FIVE, SIX},
            HEXATONIC_TONALITY
    ),
    BLUES_NINE_NOTE(
            "Blues Nine-Note",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN},
            DECATONIC_TONALITY
    ),
    BLUES_ENNEATONIC(
            "Blues Enneatonic",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN},
            DECATONIC_TONALITY
    ),
    BLUES_HEPTATONIC(
            "Blues Heptatonic",
            new NashvilleNumber[] {ONE, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    BLUES_HEXATONIC(
            "Blues Hexatonic",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SEVEN},
            HEPTATONIC_TONALITY
    ),
    BLUES_PENTATONIC(
            "Blues Pentatonic",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SEVEN},
            HEXATONIC_TONALITY
    ),
    BLUES_I(
            "Blues I",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FLAT_FIVE, FLAT_SEVEN},
            HEXATONIC_TONALITY
    ),
    BLUES_MAJOR_ASCENDING(
            "Blues Major (Ascending)",
            new NashvilleNumber[] {ONE, TWO, SHARP_TWO, THREE, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    BLUES_MAJOR_DESCENDING( // TODO: write in descending order when Scale.setSteps() supports descending order
            "Blues Major (Descending)",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, THREE, FIVE, SIX},
            HEPTATONIC_TONALITY
    ),
    BLUES_MINOR_ASCENDING(
            "Blues Minor (Ascending)",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SEVEN},
            HEPTATONIC_TONALITY
    ),
    BLUES_MINOR_DESCENDING( // TODO: write in descending order when Scale.setSteps() supports descending order
            "Blues Minor (Descending)",
            new NashvilleNumber[] {ONE, FLAT_THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SEVEN},
            HEPTATONIC_TONALITY
    ),
    BLUES_NONATONIC(
            "Blues Nonatonic",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN},
            DECATONIC_TONALITY
    ),
    BLUES_III(
            "Blues III",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN},
            DECATONIC_TONALITY
    ),
    BLUES_IV(
            "Blues IV",
            new NashvilleNumber[] {ONE, TWO, FLAT_THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN},
            ENNEATONIC_TONALITY
    ),
    BLUES_V(
            "Blues V",
            new NashvilleNumber[] {ONE, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, FLAT_SEVEN, SEVEN},
            ENNEATONIC_TONALITY
    ),
    BLUES_VI(
            "Blues VI",
            new NashvilleNumber[] {ONE, FLAT_THREE, THREE, FOUR, FLAT_FIVE, FIVE, SIX, FLAT_SEVEN, SEVEN},
            DECATONIC_TONALITY
    ),
    BLUESY_R_AND_R(
            "Bluesy R and R",
            new NashvilleNumber[] {ONE, FLAT_THREE, THREE, FOUR, FIVE, SIX, FLAT_SEVEN},
            OCTATONIC_TONALITY
    ),
    BYZANTINE_LITURGICAL_CHROMATIC(
            "Byzantine Liturgical Chromatic",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            OCTATONIC_TONALITY
    ),
    BYZANTINE(
            "Byzantine",
            new NashvilleNumber[] {ONE, FLAT_TWO, THREE, FOUR, FIVE, FLAT_SIX, SEVEN},
            OCTATONIC_TONALITY
    ),

    ;

    final String name;
    final NashvilleNumber[] nashvilleNumbers;
    final Tonality tonality;

    ScaleType(String name, NashvilleNumber[] nashvilleNumbers, Tonality tonality) {
        this.name = name;
        this.nashvilleNumbers = nashvilleNumbers;
        this.tonality = tonality;
    }
}

enum Tonality {
    MAJOR_TONALITY,
    MINOR_TONALITY,
    NO_TONALITY,
    CHROMATIC_TONALITY,
    PENTATONIC_TONALITY,
    HEXATONIC_TONALITY,
    HEPTATONIC_TONALITY,
    OCTATONIC_TONALITY,
    ENNEATONIC_TONALITY,
    DECATONIC_TONALITY,
    MODAL_TONALITY
}
