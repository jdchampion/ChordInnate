package chordinnate.musictheory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.PitchIntervalQuality.*;
import static chordinnate.musictheory.RomanNumeral.*;


enum PitchIntervalQuality {
    MAJOR("M", ""),
    MINOR("m", ""),
    PERFECT("P", ""),
    AUGMENTED("A", "+"),
    DIMINISHED("d", "˚");

    private final String SHORT_NAME_SYMBOL, ROMAN_NUMERAL_SYMBOL;

    private static final Map<PitchIntervalQuality, PitchIntervalQuality> INVERSIONS = new HashMap<>(PitchIntervalQuality.values().length);
        static {
            INVERSIONS.put(MAJOR, MINOR);
            INVERSIONS.put(MINOR, MAJOR);
            INVERSIONS.put(PERFECT, PERFECT);
            INVERSIONS.put(AUGMENTED, DIMINISHED);
            INVERSIONS.put(DIMINISHED, AUGMENTED);
        }

    PitchIntervalQuality(String shortNameSymbol, String romanNumeralSymbol) {
        this.SHORT_NAME_SYMBOL = shortNameSymbol;
        this.ROMAN_NUMERAL_SYMBOL = romanNumeralSymbol;
    }

    public String getShortNameSymbol() {
        return SHORT_NAME_SYMBOL;
    }

    public String getRomanNumeralSymbol() {
        return ROMAN_NUMERAL_SYMBOL;
    }

    public PitchIntervalQuality getInversion() {
        return INVERSIONS.get(this);
    }
}

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
    PERFECT_UNISON(0, PERFECT, 1, I),
    DIMINISHED_SECOND(0, DIMINISHED, 2, ii),

    MINOR_SECOND(1, MINOR, 2, ii),
    AUGMENTED_UNISON(1, AUGMENTED, 1, I),

    MAJOR_SECOND(2, MAJOR, 2, II),
    DIMINISHED_THIRD(2, DIMINISHED, 3, iii),

    MINOR_THIRD(3, MINOR, 3, iii),
    AUGMENTED_SECOND(3, AUGMENTED, 2, II),

    MAJOR_THIRD(4, MAJOR, 3, III),
    DIMINISHED_FOURTH(4, DIMINISHED, 4, iv),

    PERFECT_FOURTH(5, PERFECT, 4, IV),
    AUGMENTED_THIRD(5, AUGMENTED, 3, III),

    DIMINISHED_FIFTH(6, DIMINISHED, 5, v),
    AUGMENTED_FOURTH(6, AUGMENTED, 4, IV),

    PERFECT_FIFTH(7, PERFECT, 5, V),
    DIMINISHED_SIXTH(7, DIMINISHED, 6, vi),

    MINOR_SIXTH(8, MINOR, 6, vi),
    AUGMENTED_FIFTH(8, AUGMENTED, 5, V),

    MAJOR_SIXTH(9, MAJOR, 6, VI),
    DIMINISHED_SEVENTH(9, DIMINISHED, 7, vii),

    MINOR_SEVENTH(10, MINOR, 7, vii),
    AUGMENTED_SIXTH(10, AUGMENTED, 6, VI),

    MAJOR_SEVENTH(11, MAJOR, 7, VII),
    DIMINISHED_OCTAVE(11, DIMINISHED, 8, viii),

    PERFECT_OCTAVE(12, PERFECT, 8, VIII),
    AUGMENTED_SEVENTH(12, AUGMENTED, 7, VII);

    private final int NUM_SEMITONES;
    private final PitchIntervalQuality PITCH_INTERVAL_QUALITY;
    private final int NUMBER;
    private final RomanNumeral ROMAN_NUMERAL;
    private final String SHORT_NAME, ROMAN_NUMERAL_NAME;

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

    private static final Map<PitchInterval, PitchInterval> INVERSIONS = new HashMap<>(PitchInterval.values().length);
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
        this.SHORT_NAME = pitchIntervalQuality.getShortNameSymbol() + number;
        this.ROMAN_NUMERAL_NAME = romanNumeral.toString() + pitchIntervalQuality.getRomanNumeralSymbol();
    }

    public int getNumSemitones() {
        return NUM_SEMITONES;
    }

    public int getNumber() {
        return NUMBER;
    }

    public PitchIntervalQuality getPitchIntervalQuality() {
        return PITCH_INTERVAL_QUALITY;
    }

    public RomanNumeral getRomanNumeral() {
        return ROMAN_NUMERAL;
    }

    public String getShortName() {
        return SHORT_NAME;
    }

    public String getRomanNumeralName() {
        return ROMAN_NUMERAL_NAME;
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
    public static PitchInterval getPitchIntervalBetween(PitchClass lhs, PitchClass rhs) {
        int intervallicDistance = PitchClass.getIntervallicDistanceBetween(lhs, rhs);
        ArrayList<PitchInterval> candidates = ENHARMONICS.get(intervallicDistance);

        int letterDistance = 1 + lhs.getEnharmonicSpelling().getLetter()
                .getVectorDistanceTo(rhs.getEnharmonicSpelling().getLetter(), true);

        for (PitchInterval candidate : candidates) {
            if (candidate.getNumber() == letterDistance) {
                return candidate;
            }
        }

        return null;
    }

    public static PitchInterval getPitchIntervalBetween(Pitch lhs, Pitch rhs) {
        /*
         * WARNING: although this wrapper function returns the expected PitchInterval,
         * it does NOT take into account the Octave difference between the two Pitches.
         * This is because the PitchInterval enum values do not extend beyond the scope
         * of a single octave.
         *
         * Example: C4 -> E4 will return the same PitchInterval as C8 -> E4 or C4 -> E8
         *
         * Depending on the use of this function, this may be undesired behavior
         * or an unexpected result. Use Octave.getNumOctavesBetween() in conjunction
         * with the results of this method to account for the Octave difference.
         */
        return getPitchIntervalBetween(lhs.getPitchClass(), rhs.getPitchClass());
    }

    @Override
    public boolean isEnharmonicTo(PitchInterval other) {
        return this.NUM_SEMITONES == other.NUM_SEMITONES;
    }

    @Override
    public PitchInterval[] getEnharmonics() {
        PitchInterval[] enharmonics = new PitchInterval[ENHARMONICS.get(NUM_SEMITONES).size()];
        return ENHARMONICS.get(NUM_SEMITONES).toArray(enharmonics);
    }
}
