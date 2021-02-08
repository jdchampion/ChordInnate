package chordinnate.model.musictheory.notation;

import chordinnate.entity.ChordType;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.service.Services;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RomanNumeral implements IntervalNotation {
    private static final String[] EMPTY_AUX_SYMBOLS = {};
    private static final int[] EMPTY_AUX_NUMERALS = {};

    public static final RomanNumeral DIMINISHED_ONE = new RomanNumeral("{i˚}", "i˚", EMPTY_AUX_SYMBOLS, 1, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_ONE = new RomanNumeral("{i}", "i", EMPTY_AUX_SYMBOLS, 1, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_ONE = new RomanNumeral("{I}", "I", EMPTY_AUX_SYMBOLS, 1, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_ONE = new RomanNumeral("{I+}", "I+", EMPTY_AUX_SYMBOLS, 1, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_TWO = new RomanNumeral("{ii˚}", "ii˚", EMPTY_AUX_SYMBOLS, 2, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_TWO = new RomanNumeral("{ii}", "ii", EMPTY_AUX_SYMBOLS, 2, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral PERFECT_TWO = new RomanNumeral("{II}", "II", EMPTY_AUX_SYMBOLS, 2, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_TWO = new RomanNumeral("{II}", "II", EMPTY_AUX_SYMBOLS, 2, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_TWO = new RomanNumeral("{II+}", "II+", EMPTY_AUX_SYMBOLS, 2, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_THREE = new RomanNumeral("{iii˚}", "iii˚", EMPTY_AUX_SYMBOLS, 3, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_THREE = new RomanNumeral("{iii}", "iii", EMPTY_AUX_SYMBOLS, 3, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_THREE = new RomanNumeral("{III}", "III", EMPTY_AUX_SYMBOLS, 3, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_THREE = new RomanNumeral("{III+}", "III+", EMPTY_AUX_SYMBOLS, 3, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_FOUR = new RomanNumeral("{iv˚}", "iv˚", EMPTY_AUX_SYMBOLS, 4, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_FOUR = new RomanNumeral("{iv}", "iv", EMPTY_AUX_SYMBOLS, 4, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_FOUR = new RomanNumeral("{IV}", "IV", EMPTY_AUX_SYMBOLS, 4, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_FOUR = new RomanNumeral("{IV+}", "IV+", EMPTY_AUX_SYMBOLS, 4, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_FIVE = new RomanNumeral("{v˚}", "v˚", EMPTY_AUX_SYMBOLS, 5, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_FIVE = new RomanNumeral("{v}", "v", EMPTY_AUX_SYMBOLS, 5, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_FIVE = new RomanNumeral("{V}", "V", EMPTY_AUX_SYMBOLS, 5, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_FIVE = new RomanNumeral("{V+}", "V+", EMPTY_AUX_SYMBOLS, 5, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_SIX = new RomanNumeral("{vi˚}", "vi˚", EMPTY_AUX_SYMBOLS, 6, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_SIX = new RomanNumeral("{vi}", "vi", EMPTY_AUX_SYMBOLS, 6, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_SIX = new RomanNumeral("{VI}", "VI", EMPTY_AUX_SYMBOLS, 6, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_SIX = new RomanNumeral("{VI+}", "VI+", EMPTY_AUX_SYMBOLS, 6, EMPTY_AUX_NUMERALS);

    public static final RomanNumeral DIMINISHED_SEVEN = new RomanNumeral("{vii˚}", "vii˚", EMPTY_AUX_SYMBOLS, 7, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MINOR_SEVEN = new RomanNumeral("{vii}", "vii", EMPTY_AUX_SYMBOLS, 7, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral MAJOR_SEVEN = new RomanNumeral("{VII}", "VII", EMPTY_AUX_SYMBOLS, 7, EMPTY_AUX_NUMERALS);
    public static final RomanNumeral AUGMENTED_SEVEN = new RomanNumeral("{VII+}", "VII+", EMPTY_AUX_SYMBOLS, 7, EMPTY_AUX_NUMERALS);

    private static final Map<String, RomanNumeral> CACHE = Collections.unmodifiableMap(initializeMap());

    private static Map<String, RomanNumeral> initializeMap() {
        Map<String, RomanNumeral> map = new HashMap<>();
        try {
            for (Field field : Class.forName(RomanNumeral.class.getName()).getDeclaredFields()) {
                if (field.getType().isAssignableFrom(RomanNumeral.class)) {
                    RomanNumeral toAdd = (RomanNumeral) field.get(Class.forName(RomanNumeral.class.getName()));
                    map.put(toAdd.getSymbol(), toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }
    /**
     * Regex format examples:
     *      prepended accidentals: {bii} {bbIV} {#iv}
     *      major: {I} {VI}
     *      augmented: {IV+} {V++}
     *      minor: {ii} {iv}
     *      diminished: {viio} {vo} {iiiooo}
     *      7th indication: {V7} {iii7}
     *      inversion indication: {IV(6/4)}
     *      n-ary dominant: {V(6/5)}/{I}    {V7}/{V}/{IV}/{V}
     */
    private static final String ROMAN_NUMERAL_REGEX =
        // optional prepended accidentals
        "^(\\{((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)"
        // case 1: uppercase roman symbols. Perfect / Major (no +) or Augmented (+)
        + "(((?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})\\+*)"
        // case 2: lowercase roman symbols. Minor (no o symbol) or Diminished (o symbol)
        + "|(((?=[mdclxvi])m*(c[md]|d?c{0,3})(x[cl]|l?x{0,3})(i[xv]|v?i{0,3}))([˚ø⌀o]*)))"
        // optional chord symbol.
        + "((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[+\\-b#x˚ø⌀o]|\\w)*)"
        // optional inversion indication, or 7th indication.
        + "((\\(([1-7]|(([1-7]/)+)[1-7])\\))?)})"
        // optional n-ary dominant notation. Same pattern as all concatenations above.
        + "((/(\\{((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)" +
                "(((?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})\\+*)" +
                "|(((?=[mdclxvi])m*(c[md]|d?c{0,3})(x[cl]|l?x{0,3})(i[xv]|v?i{0,3}))([˚ø⌀o]*)))" +
                "((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[+\\-b#x˚ø⌀o]|\\w)*)" +
                "((\\(([1-7]|(([1-7]/)+)[1-7])\\))?)}))*)$";

    private static final String ROMAN_SYMBOLS_COMPLEMENT_REGEX = "[^IiVvXxLlCcDdMm]";

    private static final Pattern PATTERN = Pattern.compile(ROMAN_NUMERAL_REGEX);

    private static final Map<String, Integer> ROMAN_TO_INT = new LinkedHashMap<>();
    static {
        ROMAN_TO_INT.put("M", 1000);
        ROMAN_TO_INT.put("CM", 900);
        ROMAN_TO_INT.put("D", 500);
        ROMAN_TO_INT.put("CD", 400);
        ROMAN_TO_INT.put("C", 100);
        ROMAN_TO_INT.put("XC", 90);
        ROMAN_TO_INT.put("L", 50);
        ROMAN_TO_INT.put("XL", 40);
        ROMAN_TO_INT.put("X", 10);
        ROMAN_TO_INT.put("IX", 9);
        ROMAN_TO_INT.put("V", 5);
        ROMAN_TO_INT.put("IV", 4);
        ROMAN_TO_INT.put("I", 1);
    }

    private final String symbol;
    private final String primarySymbol;
    private final String[] auxiliarySymbols;
    private final int primaryNumeral;
    private final int[] auxiliaryNumerals;

    /**
     * Creates or returns a cached Roman Numeral matching the provided symbol.
     * @param symbol the descriptor for the Roman Numeral to be returned
     * @return a Roman Numeral matching the provided symbol
     */
    public static RomanNumeral withSymbol(String symbol) {
        Matcher matcher = PATTERN.matcher(symbol);

        if (matcher.matches()) {

            String primarySymbol = matcher.group(1).replace("{", "").replace("}", "");
            int primaryNumeral = romanToInt(primarySymbol);

            String[] split = matcher.group(22).split("}/\\{");
            String[] auxiliarySymbols = EMPTY_AUX_SYMBOLS;
            int[] auxiliaryNumerals = EMPTY_AUX_NUMERALS;

            if (split.length > 1) {
                auxiliarySymbols = new String[split.length];
                auxiliaryNumerals = new int[auxiliarySymbols.length];

                for (int i = 0; i < auxiliaryNumerals.length; i++) {
                    auxiliarySymbols[i] = split[i].replace("/{", "").replace("}", "");
                    auxiliaryNumerals[i] = romanToInt(auxiliarySymbols[i]);
                }
            }

            if (auxiliaryNumerals.length < 1) {
                RomanNumeral lookup = CACHE.get(symbol);
                if (lookup != null) return lookup;
            }

            return new RomanNumeral(symbol, primarySymbol, auxiliarySymbols, primaryNumeral, auxiliaryNumerals);
        }

        throw new IllegalArgumentException("Invalid roman numeral symbol [" + symbol + "]");
    }

    /**
     * Creates an auxiliary Roman Numeral,
     * with specified degree and slash-annotated auxiliary dominants.
     * @param primaryDegree an integer representing the degree of the primary Roman Numeral
     * @param intervals the intervals to be considered when constructing the primary Roman Numeral
     * @param auxiliaryDominants the auxiliary Roman Numerals to be considered as dominants of the primary
     * @return a Roman Numeral with primary and auxiliary components. e.g., V7/V
     */
    public static RomanNumeral from(int primaryDegree, @NotNull Interval[] intervals, RomanNumeral... auxiliaryDominants) {
        String primary = fromHelper(primaryDegree, intervals);

        String auxiliary;
        if (auxiliaryDominants != null && auxiliaryDominants.length > 0) {
            auxiliary = "/" + Arrays.stream(auxiliaryDominants)
                    .map(RomanNumeral::getSymbol)
                    .collect(Collectors.joining("/"));
        } else {
            auxiliary = "";
        }

        return withSymbol(primary + auxiliary);
    }

    /**
     * Creates a Roman Numeral based on the specified degree and intervals.
     * @param degree an integer representing the degree of the Roman Numeral
     * @param intervals the intervals to be considered when constructing the Roman Numeral
     * @return a Roman Numeral based on the specified degree and intervals
     */
    public static RomanNumeral from(int degree, @NotNull Interval... intervals) {
        return withSymbol(fromHelper(degree, intervals));
    }

    private static String fromHelper(int degree, @NotNull Interval... intervals) {

        // 1. Determine whether we're dealing with an inversion (not sorted => inverted)
        Interval[] sorted = new Interval[intervals.length];
        System.arraycopy(intervals, 0, sorted, 0, intervals.length);
        Arrays.sort(sorted);

        String inversion = "";
        if (!Arrays.deepEquals(intervals, sorted)) {

            /*
             * Transpose intervals down such that P1 is at the start.
             * Read the array backwards, grabbing the simple diatonic from each interval.
             * This will create the inversion notation for roman numerals.
             * i.e., (6/4)
             */
            Interval base = intervals[0];
            StringJoiner sj = new StringJoiner("/");
            for (int i = intervals.length - 1; i > 0; i--) {
                sj.add(Integer.toString(intervals[i].minus(base).getSimpleDiatonic()));
            }
            inversion = "(" + sj.toString() + ")";
        }

        // 2. Lookup any potential chord type by the provided intervals
        Interval[] shortNames = Arrays.stream(sorted)
                .map(i -> Interval.withShortName(i.getSimpleShortName()))
                .toArray(Interval[]::new);

        Optional<ChordType> optional = Services.getChordTypeService()
                .findByRomanNumeralCriteria(shortNames, intervals.length, 1);

        boolean capitalize;
        String chordSymbol;

        if (optional.isPresent()) {
            ChordType chordType = optional.get();
            capitalize = chordType.getRnCapital();
            chordSymbol = chordType.getRnSymbol();
        } else {
            capitalize = Arrays.asList(shortNames).contains(Interval.MINOR_3);
            chordSymbol = "";
        }

        return "{" +
                // TODO: flats / sharps preceding the numeral
                (capitalize ? intToRoman(degree).toUpperCase() : intToRoman(degree).toLowerCase()) +
                chordSymbol +
                inversion +
                "}";
    }

    static int romanToInt(String symbol) {
        if (symbol == null || symbol.length() < 1) return 1;
        String s = symbol.replaceAll(ROMAN_SYMBOLS_COMPLEMENT_REGEX, "").toUpperCase();
        int num = 0;
        int n = s.length();

        for (int i = 0; i < n - 1; i++) {
            int curr = ROMAN_TO_INT.getOrDefault("" + s.charAt(i), 0);
            int next = ROMAN_TO_INT.getOrDefault("" + s.charAt(i + 1), 0);
            num = curr < next ? num - curr : num + curr;
        }

        num += ROMAN_TO_INT.getOrDefault("" + s.charAt(n - 1), 0);

        return num == 0 ? 1 : num;
    }

    static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        // Based on awesome algorithm found at https://stackoverflow.com/a/41358305
        for (Map.Entry<String, Integer> entry : ROMAN_TO_INT.entrySet()) {
            int q = num / entry.getValue();
            num -= q * entry.getValue();
            sb.append(IntStream.range(0, q).mapToObj(j -> entry.getKey()).collect(Collectors.joining("")));
        }

        return sb.toString();
    }
}
