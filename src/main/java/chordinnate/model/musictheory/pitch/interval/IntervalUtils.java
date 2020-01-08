package chordinnate.model.musictheory.pitch.interval;

import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.IntervalQuality;
import chordinnate.model.musictheory.pitch.PitchClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper to reduce complexity in the Interval class.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class IntervalUtils {

    private static final int[] STANDARD_DIATONIC_LOOKUP = {0, 2, 4, 5, 7, 9, 11};
    private static final Map<String, Interval> STANDARD_INTERVAL_LOOKUP = Collections.unmodifiableMap(initMap1());
    private static final Map<String, Interval> INTERVAL_BETWEEN = Collections.unmodifiableMap(initMap2());
    private static final Map<String, Integer> SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE = Collections.unmodifiableMap(initMap3());
    private static final Map<String, Ratio> SIMPLE_SHORTNAME_TO_RATIO = Collections.unmodifiableMap(initMap4());

    private static Map<String, Interval> initMap1() {
        Map<String, Interval> map = new HashMap<>();

        try {
            Class intervalClass = Class.forName(Interval.class.getName());
            for (Field field : intervalClass.getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Interval.class)) {
                    Interval toAdd = (Interval) field.get(intervalClass);
                    map.put(toAdd.getCompoundShortName(), toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }

    private static Map<String, Interval> initMap2() {
        Map<String, Interval> map = new HashMap<>();
        map.put("CC", Interval.PERFECT_1);
        map.put("DD", Interval.PERFECT_1);
        map.put("EE", Interval.PERFECT_1);
        map.put("FF", Interval.PERFECT_1);
        map.put("GG", Interval.PERFECT_1);
        map.put("AA", Interval.PERFECT_1);
        map.put("BB", Interval.PERFECT_1);

        map.put("CD", Interval.MAJOR_2);
        map.put("DE", Interval.MAJOR_2);
        map.put("EF", Interval.MINOR_2);
        map.put("FG", Interval.MAJOR_2);
        map.put("GA", Interval.MAJOR_2);
        map.put("AB", Interval.MAJOR_2);
        map.put("BC", Interval.MINOR_2);
        map.put("DC", Interval.MINOR_7);
        map.put("ED", Interval.MINOR_7);
        map.put("FE", Interval.MAJOR_7);
        map.put("GF", Interval.MINOR_7);
        map.put("AG", Interval.MINOR_7);
        map.put("BA", Interval.MINOR_7);
        map.put("CB", Interval.MAJOR_7);

        map.put("CE", Interval.MAJOR_3);
        map.put("DF", Interval.MINOR_3);
        map.put("EG", Interval.MINOR_3);
        map.put("FA", Interval.MAJOR_3);
        map.put("GB", Interval.MAJOR_3);
        map.put("AC", Interval.MINOR_3);
        map.put("BD", Interval.MINOR_3);
        map.put("EC", Interval.MINOR_6);
        map.put("FD", Interval.MAJOR_6);
        map.put("GE", Interval.MAJOR_6);
        map.put("AF", Interval.MINOR_6);
        map.put("BG", Interval.MINOR_6);
        map.put("CA", Interval.MAJOR_6);
        map.put("DB", Interval.MAJOR_6);

        map.put("CF", Interval.PERFECT_4);
        map.put("DG", Interval.PERFECT_4);
        map.put("EA", Interval.PERFECT_4);
        map.put("FB", Interval.AUGMENTED_4);
        map.put("GC", Interval.PERFECT_4);
        map.put("AD", Interval.PERFECT_4);
        map.put("BE", Interval.PERFECT_4);
        map.put("FC", Interval.PERFECT_5);
        map.put("GD", Interval.PERFECT_5);
        map.put("AE", Interval.PERFECT_5);
        map.put("BF", Interval.DIMINISHED_5);
        map.put("CG", Interval.PERFECT_5);
        map.put("DA", Interval.PERFECT_5);
        map.put("EB", Interval.PERFECT_5);
        return map;
    }

    private static Map<String, Integer> initMap3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("d1", Interval.DIMINISHED_1.getSemitones());
        map.put("P1", Interval.PERFECT_1.getSemitones());
        map.put("A1", Interval.AUGMENTED_1.getSemitones());
        map.put("d2", Interval.DIMINISHED_2.getSemitones());
        map.put("m2", Interval.MINOR_2.getSemitones());
        map.put("M2", Interval.MAJOR_2.getSemitones());
        map.put("A2", Interval.AUGMENTED_2.getSemitones());
        map.put("d3", Interval.DIMINISHED_3.getSemitones());
        map.put("m3", Interval.MINOR_3.getSemitones());
        map.put("M3", Interval.MAJOR_3.getSemitones());
        map.put("A3", Interval.AUGMENTED_3.getSemitones());
        map.put("d4", Interval.DIMINISHED_4.getSemitones());
        map.put("P4", Interval.PERFECT_4.getSemitones());
        map.put("A4", Interval.AUGMENTED_4.getSemitones());
        map.put("d5", Interval.DIMINISHED_5.getSemitones());
        map.put("P5", Interval.PERFECT_5.getSemitones());
        map.put("A5", Interval.AUGMENTED_5.getSemitones());
        map.put("d6", Interval.DIMINISHED_6.getSemitones());
        map.put("m6", Interval.MINOR_6.getSemitones());
        map.put("M6", Interval.MAJOR_6.getSemitones());
        map.put("A6", Interval.AUGMENTED_6.getSemitones());
        map.put("d7", Interval.DIMINISHED_7.getSemitones());
        map.put("m7", Interval.MINOR_7.getSemitones());
        map.put("M7", Interval.MAJOR_7.getSemitones());
        map.put("A7", Interval.AUGMENTED_7.getSemitones());
        return map;
    }

    private static Map<String, Ratio> initMap4() {
        Map<String, Ratio> map = new LinkedHashMap<>();
        map.put("d1", buildRatio("25:24"));     // 1.0416666667
        map.put("P1", buildRatio("1:1"));       // 1
        map.put("A1", buildRatio("25:24"));     // 1.0416666667
        map.put("d2", buildRatio("128:125"));   // 1.024
        map.put("m2", buildRatio("10:9"));      // 1.111111111111111
        map.put("M2", buildRatio("9:8"));       // 1.125
        map.put("A2", buildRatio("75:64"));     // 1.171875
        map.put("d3", buildRatio("144:125"));   // 1.152
        map.put("m3", buildRatio("6:5"));       // 1.2
        map.put("M3", buildRatio("5:4"));       // 1.25
        map.put("A3", buildRatio("125:96"));    // 1.302083333333333
        map.put("d4", buildRatio("32:25"));     // 1.28
        map.put("P4", buildRatio("4:3"));       // 1.333333333333333
        map.put("A4", buildRatio("25:18"));     // 1.388888888888889
        map.put("d5", buildRatio("25:18"));     // 1.388888888888889
        map.put("P5", buildRatio("3:2"));       // 1.5
        map.put("A5", buildRatio("25:16"));     // 1.5625
        map.put("d6", buildRatio("192:125"));   // 1.536
        map.put("m6", buildRatio("8:5"));       // 1.6
        map.put("M6", buildRatio("5:3"));       // 1.666666666666667
        map.put("A6", buildRatio("125:72"));    // 1.736111111111111
        map.put("d7", buildRatio("128:75"));    // 1.706666666666667
        map.put("m7", buildRatio("16:9"));      // 1.777777777777778
        map.put("M7", buildRatio("9:5"));       // 1.8
        map.put("A7", buildRatio("125:64"));    // 1.953125
        map.put("d8", buildRatio("48:25"));     // 1.92
        map.put("P8", buildRatio("2:1"));       // 2
        map.put("A8", buildRatio("25:12"));     // 2.083333333333333
        return map;
    }

    private static Ratio buildRatio(String s) {
        String[] split = s.split(":");
        return new Ratio(new BigInteger(split[0]), new BigInteger(split[1]));
    }

    private static Ratio combineRatios(Ratio r1, Ratio r2, boolean invert) {
        BigInteger sumNumerator = r1.getNumerator().multiply(invert ? r2.getDenominator() : r2.getNumerator());
        BigInteger sumDenominator = r1.getDenominator().multiply(invert ? r2.getNumerator() : r2.getDenominator());

        return new Ratio(sumNumerator, sumDenominator);
    }

    private static Ratio addRatios(Ratio r1, Ratio r2) {
        return combineRatios(r1, r2, false);
    }

    private static Ratio subtractRatios(Ratio r1, Ratio r2) {
        return combineRatios(r1, r2, true);
    }

    private static Ratio multiplyRatio(int octaves, Ratio ratio) {
        if (octaves <= 0) return ratio;
        return new Ratio(ratio.getNumerator().multiply(new BigInteger("" + (2 * octaves))), ratio.getDenominator());
    }

    /**
     * Figures out the Just Intonation ratio for the given Interval.
     * @param interval
     * @return
     */
    private static Ratio determineRatio(Interval interval) {
        return determineRatio(extractQuality(interval.getCompoundShortName()), interval.getCompoundDiatonic(), interval.getOctaves());
    }

    /**
     * Figures out the Just Intonation ratio for the Interval with the given quality and diatonic.
     * @param quality
     * @param simpleDiatonic
     * @param octaves
     * @return
     */
    private static Ratio determineRatio(String quality, int simpleDiatonic, int octaves) {
        char[] tokens = quality.toCharArray();
        Ratio result = SIMPLE_SHORTNAME_TO_RATIO.get(Interval.PERFECT_1.getSimpleShortName());

        // For extreme diminished / augmented intervals: add d1 or A1 for as many d's / A's there are
        for (int i = 1; i < tokens.length; i++) {
            Ratio searchRatio = SIMPLE_SHORTNAME_TO_RATIO.get(String.valueOf(tokens[i]) + 1);
            if (searchRatio != null) {
                result = addRatios(result, searchRatio);
            }
        }

        // Look up the simple diatonic and apply any extra diminishing / augmenting as necessary
        Ratio searchRatio = SIMPLE_SHORTNAME_TO_RATIO.get(String.valueOf(tokens[0]) + simpleDiatonic);
        if (searchRatio != null) {
            result = addRatios(result, searchRatio);
        }

        // Shift the ratio up to account for the octave
        if (octaves > 1) {
            result = addRatios(result, buildRatio((2 * octaves) + ":1"));
        }

        return result;
    }

    static Interval determineSimpleInterval(String compoundInterval) {
        return STANDARD_INTERVAL_LOOKUP.get(compoundInterval);
    }

    static int determineSemitones(String quality, int simpleDiatonic, int octaves) {
        int total = IntStream.range(0, quality.length())
                .map(i -> {
                    if (quality.charAt(i) == 'd') {
                        return -1;
                    } else {
                        return 1;
                    }
                }).sum();

        int semitones;

        if (total < 0) {
            int simpleSemitone = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get(IntervalQuality.DIMINISHED.getSymbol() + simpleDiatonic);
            semitones = simpleSemitone + (12 * octaves) + (total + 1);
        } else if (total > 0) {
            int simpleSemitone = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get(IntervalQuality.AUGMENTED.getSymbol() + simpleDiatonic);
            semitones = simpleSemitone + (12 * octaves) + (total - 1);
        } else {
            if (simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5) {
                semitones = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get(IntervalQuality.PERFECT.getSymbol() + simpleDiatonic);
            } else {
                semitones = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get(IntervalQuality.MAJOR.getSymbol() + simpleDiatonic);
            }
        }

        return semitones;
    }

    /**
     * Helper method to retrieve the simple Interval between two PitchClasses, if possible.
     *
     * @param begin     the starting PitchClass
     * @param end       the ending PitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws ChordInnateException if no Interval could be determined
     */
    static Interval determineIntervalBetween(PitchClass begin, PitchClass end, boolean direction) {

        String beginName = begin.getName();
        String endName = end.getName();
        String beginLetter = String.valueOf(beginName.charAt(0));
        String endLetter = String.valueOf(endName.charAt(0));

        /*
         * 1. Simplify and cancel out accidentals on each side,
         * until 'begin' is natural (contains no accidentals).
         */
        StringBuilder beginAccidentals = new StringBuilder(Accidental.simplify((beginName.length() > 1 ? beginName.substring(1) : ""), false, true));
        StringBuilder endAccidentals = new StringBuilder(Accidental.simplify((endName.length() > 1 ? endName.substring(1) : ""), false, true));
        while (beginAccidentals.length() > 0) {
            if (beginAccidentals.indexOf(Accidental.FLAT.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.SHARP.utf8Symbol);
            } else if (beginAccidentals.indexOf(Accidental.SHARP.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.FLAT.utf8Symbol);
            } else if (beginAccidentals.indexOf(Accidental.DOUBLE_SHARP.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.DOUBLE_FLAT.utf8Symbol);
            }

            beginAccidentals.deleteCharAt(0);
        }
        endAccidentals = new StringBuilder(Accidental.simplify(endAccidentals.toString(), false, true));
        PitchClass newBegin = PitchClass.withName(beginLetter + beginAccidentals, false);
        PitchClass newEnd = PitchClass.withName(endLetter + endAccidentals, false);

        /*
         * 2. Count semitones between the two resulting pitch classes.
         *
         * If going UP: count LEFT TO RIGHT from 'begin' to 'end'
         * If going DOWN: count RIGHT TO LEFT from 'begin' to 'end'.
         */
        int semitonesBetween = direction
                ? PitchClass.getSemitoneDistanceBetween(newBegin, newEnd)
                : PitchClass.getSemitoneDistanceBetween(newEnd, newBegin);

        /*
         * 3. Lookup the "generalized" starting interval from hashmap
         * by combining letters of the two pitch classes.
         */
        String key = direction ? (beginLetter + endLetter) : (endLetter + beginLetter);
        Interval candidate = INTERVAL_BETWEEN.get(key);

        /*
         * 4. Compare the semitones gathered from step 2,
         * and the semitones on the interval from step 3.
         *
         * If they match, return the interval from step 3.
         * Otherwise, proceed to step 5.
         */
        if (semitonesBetween == candidate.getSemitones()) {
            return candidate;
        }

        /*
         * 5. Continue augmenting / diminishing the interval from step 3
         * and cancelling out the remaining accidentals of 'end'
         * until 'end' is natural.
         *
         * If going UP:
         *      If 'end' is FLAT: diminish
         *      If 'end' is SHARP: augment
         * If going DOWN:
         *      If 'end' is FLAT: augment
         *      If 'end' is SHARP: diminish
         */
        return applyAccidental(candidate, endAccidentals.toString(), direction);
    }


    private static Interval applyAccidental(Interval candidate, String accidentals, boolean direction) {
        String candidateQuality = String.valueOf(candidate.getSimpleShortName().charAt(0));
        int simpleDiatonic = candidate.getSimpleDiatonic();

        boolean isOneFourFive = simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5;

        for (char c : accidentals.toCharArray()) {
            candidateQuality = determineQualityByAccidental(candidateQuality, c, isOneFourFive, direction);
        }

        return Interval.withShortName(candidateQuality + simpleDiatonic);
    }

    private static String determineQualityByAccidental(String toReturn, char accidental, boolean isOneFourFive, boolean direction) {

        IntervalQuality[] qualities = IntervalQuality.values();

        if (!direction) {
            List<IntervalQuality> list = Arrays.asList(qualities);
            Collections.reverse(list);
            qualities = list.toArray(new IntervalQuality[0]);
        }

        if (Accidental.FLAT.equals(Accidental.getBySymbol(accidental))) {
            return handleFlatApplication(toReturn, isOneFourFive, qualities);
        } else if (Accidental.SHARP.equals(Accidental.getBySymbol(accidental))) {
            return handleSharpApplication(toReturn, isOneFourFive, false, qualities);
        } else if (Accidental.DOUBLE_SHARP.equals(Accidental.getBySymbol(accidental))) {
            return handleSharpApplication(toReturn, isOneFourFive, true, qualities);
        }

        return toReturn;
    }

    private static String handleFlatApplication(String toReturn, boolean isOneFourFive, IntervalQuality[] qualities) {

        if (toReturn.startsWith(qualities[0].getSymbol())) {
            toReturn += qualities[0].getSymbol();
        } else if (qualities[1].getSymbol().equals(toReturn) || qualities[2].getSymbol().equals(toReturn)) {
            toReturn = qualities[0].getSymbol();
        } else if (qualities[3].getSymbol().equals(toReturn)) {
            toReturn = qualities[1].getSymbol();
        } else if (toReturn.startsWith(qualities[4].getSymbol())) {
            if (toReturn.length() > 1) {
                toReturn = toReturn.substring(0, toReturn.length() - 1);
            } else {
                toReturn = qualities[isOneFourFive ? 2 : 3].getSymbol();
            }
        }

        return toReturn;
    }

    private static String handleSharpApplication(String toReturn, boolean isOneFourFive, boolean isDoubleSharp, IntervalQuality[] qualities) {
        if (toReturn.startsWith(qualities[0].getSymbol())) {
            if (isDoubleSharp && toReturn.length() > 2) {
                toReturn = toReturn.substring(0, toReturn.length() - 2);
            } else if (toReturn.length() > 1) {
                toReturn = toReturn.substring(0, toReturn.length() - 1);
            } else {
                toReturn = qualities[isOneFourFive ? 2 : 1].getSymbol();
            }
        } else if (qualities[1].getSymbol().equals(toReturn)) {
            toReturn = qualities[3].getSymbol();
        } else if (qualities[3].getSymbol().equals(toReturn) || qualities[2].getSymbol().equals(toReturn)) {
            toReturn = qualities[4].getSymbol();
        } else if (toReturn.startsWith(qualities[4].getSymbol())) {
            toReturn += qualities[4].getSymbol();
        }

        return toReturn;
    }

    static Interval addIntervals(Interval a, Interval b) {
        if (b.equals(Interval.PERFECT_1)) return a;
        if (a.equals(Interval.PERFECT_1)) return b;
        return combineIntervals(a, b, true);
    }

    static Interval subtractIntervals(Interval a, Interval b) {

        // Trivial cases: X - X, X - P1
        if (a.compareTo(b) == 0) return Interval.PERFECT_1;
        if (b.equals(Interval.PERFECT_1)) return a;

        // Negative intervals
        if (a.compareTo(b) < 0) {
            int aOctaves = b.getOctaves();
            if (a.getOctaves() == b.getOctaves()) {
                aOctaves += 1;
            }
            int aDiatonic = a.getSimpleDiatonic() + (7 * aOctaves);
            int bDiatonic = b.getSimpleDiatonic() + (7 * a.getOctaves());
            a = Interval.withShortName(extractQuality(a.getCompoundShortName()) + aDiatonic);
            b = Interval.withShortName(extractQuality(b.getCompoundShortName()) + bDiatonic);
        }

        // All other cases
        return combineIntervals(a, b, false);
    }

    private static Interval combineIntervals(Interval a, Interval b, boolean add) {

        boolean negative = !add && a.compareTo(b) < 0;

        int resultCompoundDiatonic;
        int resultCompoundSemitones;
        if (add) {
            resultCompoundDiatonic = a.getCompoundDiatonic() + (b.getCompoundDiatonic() - 1);
            resultCompoundSemitones = a.getSemitones() + b.getSemitones();
        } else {
            int lhsDiatonic = a.getCompoundDiatonic();
            int lhsSemitones = a.getSemitones();
            if (negative) {
                /*
                 * Move a's octaves / semitones up
                 * until they are above b's octaves / semitones
                 * by the same proportion.
                 */
                int lhsOctaves = b.getOctaves() + 1;
                lhsDiatonic += (8 * lhsOctaves);
                lhsSemitones += (12 * lhsOctaves);
            }
            resultCompoundDiatonic = lhsDiatonic - (b.getCompoundDiatonic() - 1);
            resultCompoundSemitones = lhsSemitones - b.getSemitones();
        }
        int resultSimpleDiatonic = determineSimpleDiatonic(resultCompoundDiatonic);
        int resultSimpleSemitones = resultCompoundSemitones % 12;

        int semitoneDistance = STANDARD_DIATONIC_LOOKUP[resultSimpleDiatonic - 1] - resultSimpleSemitones;

        StandardIntervalQualityList qualities = new StandardIntervalQualityList(resultSimpleDiatonic);

        IntervalQuality current = qualities.get();
        StringBuilder resultCompoundShortName = new StringBuilder();

        while (semitoneDistance != 0) {

            if (IntervalQuality.DIMINISHED.equals(current) || IntervalQuality.AUGMENTED.equals(current)) {
                resultCompoundShortName.append(current.getSymbol());
            }

            qualities.advance(semitoneDistance < 0);
            current = qualities.get();

            if (semitoneDistance < 0) {
                semitoneDistance++;
            } else {
                semitoneDistance--;
            }
        }
        // TODO: continue testing
//        if (resultCompoundShortName.length() < 1) {
        resultCompoundShortName.append(current.getSymbol());
//        }

        resultCompoundShortName.append(resultCompoundDiatonic);

        return negative
                ? Interval.withShortName(resultCompoundShortName.toString()).invert()
                : Interval.withShortName(resultCompoundShortName.toString());
    }

    static int determineSimpleDiatonic(int compoundDiatonic) {
        return compoundDiatonic % 7 == 0 ? 7 : compoundDiatonic % 7;
    }

    static int determineOctaves(int compoundDiatonic) {
        return (compoundDiatonic % 7 == 0 ? ((compoundDiatonic / 7) - 1) : (compoundDiatonic / 7));
    }

    static String determineInvertedCompoundShortName(String quality, int compoundDiatonic, int simpleDiatonic) {

        // Add as many d's / A's to the inversion as there are in the uninverted one.
        String inversion = IntStream.range(0, quality.length())
                .mapToObj(i -> quality.charAt(i) == 'd' ? IntervalQuality.AUGMENTED.getSymbol() : IntervalQuality.DIMINISHED.getSymbol())
                .collect(Collectors.joining(""));

        // Figure out the distance required to get the diatonic of the inversion (i.e.: 1 <-> 1, 2 <-> 7, 3 <-> 6, 4 <-> 5)
        int modifier = 9 - (2 * simpleDiatonic);

        inversion += (compoundDiatonic + modifier);

        return inversion;
    }

    static String extractQuality(@NotNull String shortName) {
        return shortName.replaceAll("\\d", "");
    }

    static int extractDiatonic(@NotNull String shortName) {
        return Integer.parseInt(shortName.replaceAll("\\D", ""));
    }

}

/**
 * A "helper" data structure for modeling the diminution / augmentation of a given Interval.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class StandardIntervalQualityList {
    private static final List<IntervalQuality> QUALITIES_1_4_5 = Arrays.asList(IntervalQuality.DIMINISHED, IntervalQuality.PERFECT, IntervalQuality.AUGMENTED);
    private static final List<IntervalQuality> QUALITIES_2_3_6_7 = Arrays.asList(IntervalQuality.DIMINISHED, IntervalQuality.MINOR, IntervalQuality.MAJOR, IntervalQuality.AUGMENTED);

    private List<IntervalQuality> list;
    private int index;

    StandardIntervalQualityList(int simpleDiatonic) {
        if (simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5) {
            this.list = QUALITIES_1_4_5;
            this.index = 1;
        } else {
            this.list = QUALITIES_2_3_6_7;
            this.index = 2;
        }
    }

    IntervalQuality get() {
        return list.get(index);
    }

    void advance(boolean direction) {
        if (index == 0 || index == list.size() - 1) {
            return;
        }
        if (direction) {
            index++;
        } else {
            index--;
        }
    }
}

@Getter
@AllArgsConstructor
class Ratio {
    private BigInteger numerator;
    private BigInteger denominator;
}
