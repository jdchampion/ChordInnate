package chordinnate.model.musictheory.notation;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 4/14/16.
 */
public enum Accidental {
    DOUBLE_FLAT("\uD834\uDD2B", "bb", -2),
    FLAT("\u266d", "b", -1),
    NATURAL("\u266e", "_", 0),
    NONE("", "", 0),
    SHARP("\u266f", "#", 1),
    DOUBLE_SHARP("\uD834\uDD2A", "x", 2);

    private static final Map<String, Accidental> UTF8_SYMBOL_TO_ACCIDENTAL = Arrays.stream(values())
            .collect(Collectors.toMap(a -> a.utf8Symbol, Function.identity()));

    public static final String SINGLE_ACCIDENTAL_REGEX = "^([\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2A_#x]|bb?)$";
    public static final String MULTIPLE_ACCIDENTALS_REGEX = "^([\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2Ab_#x])*$";
    public static final String ACCIDENTALS_REGEX_COMPLEMENT = "([^\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2Ab_#x])+";

    public final String symbol;
    public final String utf8Symbol;
    public final int semitoneModifier;

    Accidental(String symbol, String utf8Symbol, int semitoneModifier) {
        this.symbol = symbol;
        this.utf8Symbol = utf8Symbol;
        this.semitoneModifier = semitoneModifier;
    }

    public boolean matchesSymbol(String s) {
        return symbol.equals(s) || utf8Symbol.equals(s);
    }

    public boolean matchesSymbol(char c) {
        return utf8Symbol.length() == 1 && utf8Symbol.charAt(0) == c;
    }

    public boolean isWithinString(String accidentals) {

        // Edge case for no accidental
        if (StringUtils.isBlank(accidentals) && Accidental.NONE.equals(this)) {
            return true;
        }

        // Edge case for flat accidental
        if (Accidental.FLAT.equals(this)) {

            // Find the first substring with a single 'b'
            char[] chars = accidentals.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'b') {
                    if (i + 1 >= chars.length) {
                        return true; // located at the very end of the string
                    } else if (chars[i + 1] != 'b') {
                        return true; // located anywhere else in the string
                    }
                }
            }

            return false;
        }

        return accidentals.contains(this.utf8Symbol);
    }

    public static String simplify(String allAccidentals, boolean wantNaturalSymbol, boolean returnUTF8) {
        int semitoneModifier = sumAccidentalsToSemitoneModifier(allAccidentals);

        if (semitoneModifier == 0) {
            Accidental accidental = wantNaturalSymbol ? NATURAL : NONE;
            return returnUTF8 ? accidental.utf8Symbol : accidental.symbol;
        }

        return convertSemitoneModifierToAccidentals(semitoneModifier, wantNaturalSymbol, returnUTF8);

    }

    public static List<Accidental> simplify(List<Accidental> allAccidentals, boolean wantNaturalSymbol) {
        int semitoneModifier = sumAccidentalsToSemitoneModifier(allAccidentals);

        List<Accidental> simplified = new ArrayList<>();

        if (semitoneModifier < 0) {
            simplifyHelper(simplified, semitoneModifier, DOUBLE_FLAT, FLAT);
        } else if (semitoneModifier > 0) {
            simplifyHelper(simplified, semitoneModifier, DOUBLE_SHARP, SHARP);
        } else {
            if (wantNaturalSymbol) {
                simplified.add(NATURAL);
            }
        }

        return simplified;
    }

    public static int sumAccidentalsToSemitoneModifier(String allAccidentals) {

        String acc = convertToUTF8Symbols(allAccidentals);

        if (StringUtils.isBlank(acc)) {
            return 0;
        }

        int total = 0;

        for (char c : acc.toCharArray()) {
            String token = String.valueOf(c);
            if (token.matches(SINGLE_ACCIDENTAL_REGEX)) {
                Accidental toCheck = getBySymbol(token);
                total += toCheck.semitoneModifier;
            }
        }

        return total;
    }

    public static int sumAccidentalsToSemitoneModifier(List<Accidental> allAccidentals) {
        return allAccidentals.stream().mapToInt(a -> a.semitoneModifier).sum();
    }

    private static String convertSemitoneModifierToAccidentals(int semitoneModifier, boolean wantNaturalSymbol, boolean returnUTF8) {
        if (semitoneModifier < 0) {
            return convertFlatsOrSharps(semitoneModifier, -2, -1, returnUTF8, DOUBLE_FLAT, FLAT);
        } else if (semitoneModifier > 0) {
            return convertFlatsOrSharps(semitoneModifier, 2, 1, returnUTF8, DOUBLE_SHARP, SHARP);
        } else {
            Accidental accidental = wantNaturalSymbol ? NATURAL : NONE;
            return returnUTF8 ? accidental.utf8Symbol : accidental.symbol;
        }
    }

    private static String convertFlatsOrSharps(int semitoneModifier,
                                               int doubleMod,
                                               int singleMod,
                                               boolean returnUTF8,
                                               Accidental doubleAcc,
                                               Accidental singleAcc) {
        if (semitoneModifier == doubleMod) {
            return returnUTF8 ? doubleAcc.utf8Symbol : doubleAcc.symbol;
        } else if (semitoneModifier == singleMod) {
            return returnUTF8 ? singleAcc.utf8Symbol : singleAcc.symbol;
        } else {
            return convertFlatsOrSharps(semitoneModifier, returnUTF8, doubleAcc, singleAcc);
        }
    }

    private static String convertFlatsOrSharps(int semitoneModifier, boolean returnUTF8, Accidental doubleAcc, Accidental singleAcc) {
        StringBuilder simplifiedAccidental = new StringBuilder();
        int numDoubleAccs = Math.abs(semitoneModifier) / 2;
        boolean appendSingleAcc = Math.abs(semitoneModifier) % 2 == 1;
        for (int i = 0; i < numDoubleAccs; i++) {
            simplifiedAccidental.append(returnUTF8 ? doubleAcc.utf8Symbol : doubleAcc.symbol);
        }
        if (appendSingleAcc) {
            simplifiedAccidental.append(returnUTF8 ? singleAcc.utf8Symbol : singleAcc.symbol);
        }
        return simplifiedAccidental.toString();
    }

    private static void simplifyHelper(List<Accidental> simplified, int semitoneModifier, Accidental doubleAcc, Accidental singleAcc) {
        if (semitoneModifier == doubleAcc.semitoneModifier) {
            simplified.add(doubleAcc);
        } else if (semitoneModifier == singleAcc.semitoneModifier) {
            simplified.add(singleAcc);
        } else {
            int numDoubleAccs = Math.abs(semitoneModifier) / 2;
            boolean appendSingleAcc = Math.abs(semitoneModifier) % 2 == 1;
            for (int i = 0; i < numDoubleAccs; i++) {
                simplified.add(doubleAcc);
            }
            if (appendSingleAcc) {
                simplified.add(singleAcc);
            }
        }
    }

    @Nullable
    public static Accidental getBySymbol(char symbol) {
        return getBySymbol("" + symbol);
    }

    @Nullable
    public static Accidental getBySymbol(String symbol) {
        return UTF8_SYMBOL_TO_ACCIDENTAL.get(convertToUTF8Symbols(symbol));
    }

    public static List<Accidental> convert(String allAccidentals) {
        if (StringUtils.isBlank(allAccidentals)) {
            return Collections.emptyList();
        }

        List<Accidental> list = new ArrayList<>();

        char[] chars = allAccidentals.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            if (chars[i] != 'b' && chars[i] != '_' && chars[i] != '#' && chars[i] != 'x') {
                continue;
            }

            if (chars[i] == 'x') {
                list.add(DOUBLE_SHARP);
                continue;
            }

            if (i > 0 && chars[i - 1] == chars[i]) {
                // special handling for double accidentals
                int previous = list.size() - 1;
                if (chars[i - 1] == 'b') {
                    if (DOUBLE_FLAT.equals(list.get(previous))) {
                        list.add(FLAT);
                    } else {
                        list.set(previous, DOUBLE_FLAT);
                    }
                } else {
                    if (DOUBLE_SHARP.equals(list.get(previous))) {
                        list.add(SHARP);
                    } else {
                        list.set(previous, DOUBLE_SHARP);
                    }
                }
            } else {
                if (chars[i] == 'b') {
                    list.add(FLAT);
                } else {
                    list.add(SHARP);
                }
            }

        }

        return list;
    }

    public static String convertToUTF8Symbols(String allAccidentals) {
        if (StringUtils.isBlank(allAccidentals)) {
            return "";
        }

        return allAccidentals.replaceAll("\\uD834\\uDD2B", DOUBLE_FLAT.utf8Symbol)
                .replaceAll("\\u266d", FLAT.utf8Symbol)
                .replaceAll("\\u266e", NATURAL.utf8Symbol)
                .replaceAll("\\u266f", SHARP.utf8Symbol)
                .replaceAll("\\uD834\\uDD2A", DOUBLE_SHARP.utf8Symbol);
    }

    public static String convertToDisplaySymbols(String allAccidentals, boolean wantNaturalSymbol) {
        if (StringUtils.isBlank(allAccidentals) && wantNaturalSymbol) {
            return NATURAL.symbol;
        }

        return allAccidentals.replaceAll(DOUBLE_FLAT.utf8Symbol, DOUBLE_FLAT.symbol)
                .replaceAll(FLAT.utf8Symbol, FLAT.symbol)
                .replaceAll(NATURAL.utf8Symbol, wantNaturalSymbol ? NATURAL.symbol : "")
                .replaceAll(SHARP.utf8Symbol, SHARP.symbol)
                .replaceAll(DOUBLE_SHARP.utf8Symbol, DOUBLE_SHARP.symbol);
    }

}
