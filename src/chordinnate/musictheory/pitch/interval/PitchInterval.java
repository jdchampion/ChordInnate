package chordinnate.musictheory.pitch.interval;

import chordinnate.musictheory.pitch.Enharmonic;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.notation.Letter;
import chordinnate.musictheory.pitch.interval.notation.RomanNumeral;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.pitch.interval.notation.RomanNumeral.*;


/**
 * Created by Joseph on 4/14/16.
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 *             https://en.wikipedia.org/wiki/Interval_(music)
 *             http://method-behind-the-music.com/theory/intervals/
 *             http://www.musictheory.net/lessons/31
 *             http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 *             http://music.tutsplus.com/tutorials/music-theory-intervals-and-how-to-derive-them--audio-4559
 */
public enum PitchInterval implements Enharmonic<PitchInterval> {
    PERFECT_UNISON(0, PitchIntervalQuality.PERFECT, 1, I),
    DIMINISHED_SECOND(0, PitchIntervalQuality.DIMINISHED, 2, ii),

    MINOR_SECOND(1, PitchIntervalQuality.MINOR, 2, ii),
    AUGMENTED_UNISON(1, PitchIntervalQuality.AUGMENTED, 1, I),

    MAJOR_SECOND(2, PitchIntervalQuality.MAJOR, 2, II),
    DIMINISHED_THIRD(2, PitchIntervalQuality.DIMINISHED, 3, iii),

    MINOR_THIRD(3, PitchIntervalQuality.MINOR, 3, iii),
    AUGMENTED_SECOND(3, PitchIntervalQuality.AUGMENTED, 2, II),

    MAJOR_THIRD(4, PitchIntervalQuality.MAJOR, 3, III),
    DIMINISHED_FOURTH(4, PitchIntervalQuality.DIMINISHED, 4, iv),

    PERFECT_FOURTH(5, PitchIntervalQuality.PERFECT, 4, IV),
    AUGMENTED_THIRD(5, PitchIntervalQuality.AUGMENTED, 3, III),

    DIMINISHED_FIFTH(6, PitchIntervalQuality.DIMINISHED, 5, v),
    AUGMENTED_FOURTH(6, PitchIntervalQuality.AUGMENTED, 4, IV),

    PERFECT_FIFTH(7, PitchIntervalQuality.PERFECT, 5, V),
    DIMINISHED_SIXTH(7, PitchIntervalQuality.DIMINISHED, 6, vi),

    MINOR_SIXTH(8, PitchIntervalQuality.MINOR, 6, vi),
    AUGMENTED_FIFTH(8, PitchIntervalQuality.AUGMENTED, 5, V),

    MAJOR_SIXTH(9, PitchIntervalQuality.MAJOR, 6, VI),
    DIMINISHED_SEVENTH(9, PitchIntervalQuality.DIMINISHED, 7, vii),

    MINOR_SEVENTH(10, PitchIntervalQuality.MINOR, 7, vii),
    AUGMENTED_SIXTH(10, PitchIntervalQuality.AUGMENTED, 6, VI),

    MAJOR_SEVENTH(11, PitchIntervalQuality.MAJOR, 7, VII),
    DIMINISHED_OCTAVE(11, PitchIntervalQuality.DIMINISHED, 8, viii),

    PERFECT_OCTAVE(12, PitchIntervalQuality.PERFECT, 8, VIII),
    AUGMENTED_SEVENTH(12, PitchIntervalQuality.AUGMENTED, 7, VII);

    public final int NUM_SEMITONES;
    public final PitchIntervalQuality PITCH_INTERVAL_QUALITY;
    public final int NUMBER;
    public final RomanNumeral ROMAN_NUMERAL;
    public final String SHORT_NAME, ROMAN_NUMERAL_NAME;

    private static final Map<Integer, ArrayList<PitchInterval>> ENHARMONICS = new HashMap<>(12);
        static {
            for (int i = 0; i < 12; i++) {
                ENHARMONICS.put(i, new ArrayList<>());
            }

            for (PitchInterval pitchInterval : PitchInterval.values()) {
                ENHARMONICS.get(pitchInterval.NUM_SEMITONES % 12)
                        .add(pitchInterval);
            }
        }

    private static final Map<PitchInterval, PitchInterval> INVERSIONS = new EnumMap<>(PitchInterval.class);
        static {
            for (PitchInterval pitchInterval : PitchInterval.values()) {
                PitchIntervalQuality invertedPitchIntervalQuality = pitchInterval.PITCH_INTERVAL_QUALITY.getInversion();

                String invertedEnumSuffix = "";
                switch (pitchInterval.ROMAN_NUMERAL) {
                    case I: case i: invertedEnumSuffix = "OCTAVE"; break;
                    case II: case ii: invertedEnumSuffix = "SEVENTH"; break;
                    case III: case iii: invertedEnumSuffix = "SIXTH"; break;
                    case IV: case iv: invertedEnumSuffix = "FIFTH"; break;
                    case V: case v: invertedEnumSuffix = "FOURTH"; break;
                    case VI: case vi: invertedEnumSuffix = "THIRD"; break;
                    case VII: case vii: invertedEnumSuffix = "SECOND"; break;
                    case VIII: case viii: invertedEnumSuffix = "UNISON"; break;
                }
                INVERSIONS.put(pitchInterval, PitchInterval.valueOf(invertedPitchIntervalQuality + "_" + invertedEnumSuffix));
            }
        }

    PitchInterval(int numSemitones, PitchIntervalQuality pitchIntervalQuality, int number, RomanNumeral romanNumeral) {
        this.NUM_SEMITONES = numSemitones;
        this.PITCH_INTERVAL_QUALITY = pitchIntervalQuality;
        this.NUMBER = number;
        this.ROMAN_NUMERAL = romanNumeral;
        this.SHORT_NAME = pitchIntervalQuality.SHORT_NAME_SYMBOL + number;
        this.ROMAN_NUMERAL_NAME = romanNumeral.toString() + pitchIntervalQuality.ROMAN_NUMERAL_SYMBOL;
    }

    public PitchInterval getInversion() {
        return INVERSIONS.get(this);
    }

    /**
     * Finds the PitchInterval starting from lhs and ending at rhs.
     * @param lhs the starting PitchClass
     * @param rhs the ending PitchClass
     * @return the PitchInterval between lhs and rhs
     */
    @Nullable
    public static PitchInterval getPitchIntervalBetween(@NotNull PitchClass lhs, @NotNull PitchClass rhs) {
        int semitoneDistance = PitchClass.getSemitoneDistanceBetween(lhs, rhs);
        ArrayList<PitchInterval> candidates = ENHARMONICS.get(semitoneDistance);

        int letterDistance = 1
                + getVectorDistanceTo(lhs.ENHARMONIC_SPELLING.LETTER,
                rhs.ENHARMONIC_SPELLING.LETTER);

        for (PitchInterval candidate : candidates) {
            if (candidate.NUMBER == letterDistance) {
                return candidate;
            }
        }

        return null; // returned on error
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

    @Override
    public boolean isEnharmonicTo(@NotNull PitchInterval other) {
        return this.NUM_SEMITONES == other.NUM_SEMITONES;
    }

    @Override
    public PitchInterval[] getEnharmonics() {
        PitchInterval[] enharmonics = new PitchInterval[ENHARMONICS.get(NUM_SEMITONES).size()];
        return ENHARMONICS.get(NUM_SEMITONES).toArray(enharmonics);
    }
}
