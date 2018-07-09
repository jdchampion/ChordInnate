package chordinnate.model.musictheory.notation;

import java.util.ArrayList;
import java.util.Arrays;
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
            .collect(Collectors.toMap(a -> a.UTF8_SYMBOL, Function.identity()));

    public static final String SINGLE_ACCIDENTAL_REGEX = "^([\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2A_#x]|bb?)$";
    public static final String MULTIPLE_ACCIDENTALS_REGEX = "^([\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2Ab_#x])*$";
    public static final String ACCIDENTALS_REGEX_COMPLEMENT = "([^\\uD834\\uDD2B\\u266d\\u266e\\u266f\\uD834\\uDD2Ab_#x])+";

    public final String SYMBOL;
    public final String UTF8_SYMBOL;
    public final int SEMITONE_MODIFIER;

    Accidental(String symbol, String utf8Symbol, int semitoneModifier) {
        this.SYMBOL = symbol;
        this.UTF8_SYMBOL = utf8Symbol;
        this.SEMITONE_MODIFIER = semitoneModifier;
    }

    public boolean matchesSymbol(String s) {
        return SYMBOL.equals(s) || UTF8_SYMBOL.equals(s);
    }

    public boolean matchesSymbol(char c) {
        return UTF8_SYMBOL.length() == 1 && UTF8_SYMBOL.charAt(0) == c;
    }

    public static int sumAccidentalsToSemitoneModifier(String allAccidentals) {

        String acc = convertToUTF8Symbols(allAccidentals);

        if (acc.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (char c : acc.toCharArray()) {
            String token = String.valueOf(c);
            if (token.matches(SINGLE_ACCIDENTAL_REGEX)) {
                Accidental toCheck = getBySymbol(token);
                total += toCheck.SEMITONE_MODIFIER;
            }
        }

        return total;
    }

    public static String simplify(String allAccidentals, boolean wantNaturalSymbol, boolean returnUTF8) {

        if (allAccidentals.isEmpty() || NATURAL.UTF8_SYMBOL.equals(allAccidentals) || NATURAL.SYMBOL.equals(allAccidentals)) {
            if (returnUTF8) {
                return wantNaturalSymbol ? NATURAL.UTF8_SYMBOL : NONE.UTF8_SYMBOL;
            } else {
                return wantNaturalSymbol ? NATURAL.SYMBOL : NONE.SYMBOL;
            }
        }

        int semitoneModifier = sumAccidentalsToSemitoneModifier(allAccidentals);

        return convertSemitoneModifierToAccidentals(semitoneModifier, wantNaturalSymbol, returnUTF8);

    }

    public static String convertSemitoneModifierToAccidentals(int semitoneModifier, boolean wantNaturalSymbol, boolean returnUTF8) {
        if (semitoneModifier < 0) {
            if (semitoneModifier == -2) {
                return returnUTF8 ? DOUBLE_FLAT.UTF8_SYMBOL : DOUBLE_FLAT.SYMBOL;
            } else if (semitoneModifier == -1) {
                return returnUTF8 ? FLAT.UTF8_SYMBOL : FLAT.SYMBOL;
            } else {
                StringBuilder simplifiedAccidental = new StringBuilder();
                int numDoubleFlats = Math.abs(semitoneModifier) / 2;
                boolean appendFlat = Math.abs(semitoneModifier) % 2 == 1;
                for (int i = 0; i < numDoubleFlats; i++) {
                    simplifiedAccidental.append(returnUTF8 ? DOUBLE_FLAT.UTF8_SYMBOL : DOUBLE_FLAT.SYMBOL);
                }
                if (appendFlat) {
                    simplifiedAccidental.append(returnUTF8 ? FLAT.UTF8_SYMBOL : FLAT.SYMBOL);
                }
                return simplifiedAccidental.toString();
            }
        } else if (semitoneModifier > 0) {
            if (semitoneModifier == 2) {
                return returnUTF8 ? DOUBLE_SHARP.UTF8_SYMBOL : DOUBLE_SHARP.SYMBOL;
            } else if (semitoneModifier == 1) {
                return returnUTF8 ? SHARP.UTF8_SYMBOL : SHARP.SYMBOL;
            } else {
                StringBuilder simplifiedAccidental = new StringBuilder();
                int numDoubleSharps = semitoneModifier / 2;
                boolean appendSharp = semitoneModifier % 2 == 1;
                for (int i = 0; i < numDoubleSharps; i++) {
                    simplifiedAccidental.append(returnUTF8 ? DOUBLE_SHARP.UTF8_SYMBOL : DOUBLE_SHARP.SYMBOL);
                }
                if (appendSharp) {
                    simplifiedAccidental.append(returnUTF8 ? SHARP.UTF8_SYMBOL : SHARP.SYMBOL);
                }
                return simplifiedAccidental.toString();
            }
        } else {
            if (returnUTF8) {
                return wantNaturalSymbol ? NATURAL.UTF8_SYMBOL : NONE.UTF8_SYMBOL;
            } else {
                return wantNaturalSymbol ? NATURAL.SYMBOL : NONE.SYMBOL;
            }
        }
    }

    public static List<Accidental> simplify(List<Accidental> allAccidentals, boolean wantNaturalSymbol) {

        int semitoneModifier = allAccidentals.stream().mapToInt(a -> a.SEMITONE_MODIFIER).sum();

        List<Accidental> simplified = new ArrayList<>();

        if (semitoneModifier < 0) {
            if (semitoneModifier == -2) {
                simplified.add(DOUBLE_FLAT);
            } else if (semitoneModifier == -1) {
                simplified.add(FLAT);
            } else {
                int numDoubleFlats = Math.abs(semitoneModifier) / 2;
                boolean appendFlat = Math.abs(semitoneModifier) % 2 == 1;
                for (int i = 0; i < numDoubleFlats; i++) {
                    simplified.add(DOUBLE_FLAT);
                }
                if (appendFlat) {
                    simplified.add(FLAT);
                }
            }
        } else if (semitoneModifier > 0) {
            if (semitoneModifier == 2) {
                simplified.add(DOUBLE_SHARP);
            } else if (semitoneModifier == 1) {
                simplified.add(SHARP);
            } else {
                int numDoubleSharps = semitoneModifier / 2;
                boolean appendSharp = semitoneModifier % 2 == 1;
                for (int i = 0; i < numDoubleSharps; i++) {
                    simplified.add(DOUBLE_SHARP);
                }
                if (appendSharp) {
                    simplified.add(SHARP);
                }
            }
        } else {
            simplified.add(wantNaturalSymbol ? NATURAL : NONE);
        }

        return simplified;
    }

    public static Accidental getBySymbol(String symbol) {
        return UTF8_SYMBOL_TO_ACCIDENTAL.get(convertToUTF8Symbols(symbol));
    }

    public static String convertToUTF8Symbols(String allAccidentals) {
        if (allAccidentals == null || allAccidentals.isEmpty()) {
            return "";
        }

        return allAccidentals.replaceAll("\\uD834\\uDD2B", DOUBLE_FLAT.UTF8_SYMBOL)
                .replaceAll("\\u266d", FLAT.UTF8_SYMBOL)
                .replaceAll("\\u266e", NATURAL.UTF8_SYMBOL)
                .replaceAll("\\u266f", SHARP.UTF8_SYMBOL)
                .replaceAll("\\uD834\\uDD2A", DOUBLE_SHARP.UTF8_SYMBOL);
    }

    public static String convertToDisplaySymbols(String allAccidentals, boolean wantNaturalSymbol) {
        if (allAccidentals.isEmpty() && wantNaturalSymbol) {
            return "\u266e";
        }

        return allAccidentals.replaceAll(DOUBLE_FLAT.UTF8_SYMBOL, DOUBLE_FLAT.SYMBOL)
                .replaceAll(FLAT.UTF8_SYMBOL, FLAT.SYMBOL)
                .replaceAll(NATURAL.UTF8_SYMBOL, wantNaturalSymbol ? NATURAL.SYMBOL : "")
                .replaceAll(SHARP.UTF8_SYMBOL, SHARP.SYMBOL)
                .replaceAll(DOUBLE_SHARP.UTF8_SYMBOL, DOUBLE_SHARP.SYMBOL);
    }

}
