package chordinnate.model.musictheory.pitch.key;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static chordinnate.model.musictheory.pitch.key.KeySignatureType.*;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 *            https://en.wikipedia.org/wiki/Theoretical_key
 */
public class KeySignature {

    public static final KeySignature
    // No key signature
        NO_KEY_SIGNATURE = new KeySignature(null, NONE,true),

    // Major, non-accidental
        C_MAJOR = new KeySignature(C, MAJOR,false),

    // Major, flat
        F_MAJOR = new KeySignature(F, MAJOR, false),
        B_FLAT_MAJOR = new KeySignature(B_FLAT, MAJOR, false),
        E_FLAT_MAJOR = new KeySignature(E_FLAT, MAJOR, false),
        A_FLAT_MAJOR = new KeySignature(A_FLAT, MAJOR, false),
        D_FLAT_MAJOR = new KeySignature(D_FLAT, MAJOR, false),
        G_FLAT_MAJOR = new KeySignature(G_FLAT, MAJOR, false),
        C_FLAT_MAJOR = new KeySignature(C_FLAT, MAJOR, false),
        F_FLAT_MAJOR = new KeySignature(F_FLAT, MAJOR, true),
        B_DOUBLE_FLAT_MAJOR = new KeySignature(B_DOUBLE_FLAT, MAJOR, true),
        E_DOUBLE_FLAT_MAJOR = new KeySignature(E_DOUBLE_FLAT, MAJOR, true),
        A_DOUBLE_FLAT_MAJOR = new KeySignature(A_DOUBLE_FLAT, MAJOR, true),

    // Major, sharp
        G_MAJOR = new KeySignature(G, MAJOR,false),
        D_MAJOR = new KeySignature(D, MAJOR,false),
        A_MAJOR = new KeySignature(A, MAJOR,false),
        E_MAJOR = new KeySignature(E, MAJOR,false),
        B_MAJOR = new KeySignature(B, MAJOR,false),
        F_SHARP_MAJOR = new KeySignature(F_SHARP, MAJOR, false),
        C_SHARP_MAJOR = new KeySignature(C_SHARP, MAJOR, false),
        G_SHARP_MAJOR = new KeySignature(G_SHARP, MAJOR, true),
        D_SHARP_MAJOR = new KeySignature(D_SHARP, MAJOR, true),
        A_SHARP_MAJOR = new KeySignature(A_SHARP, MAJOR, true),
        E_SHARP_MAJOR = new KeySignature(E_SHARP, MAJOR, true),
        B_SHARP_MAJOR = new KeySignature(B_SHARP, MAJOR, true),

    // Minor, non-accidental
        A_MINOR = new KeySignature(A, MINOR, false),

    // Minor, flat
        D_MINOR = new KeySignature(D, MINOR, false),
        G_MINOR = new KeySignature(G, MINOR, false),
        C_MINOR = new KeySignature(C, MINOR, false),
        F_MINOR = new KeySignature(F, MINOR, false),
        B_FLAT_MINOR = new KeySignature(B_FLAT, MINOR, false),
        E_FLAT_MINOR = new KeySignature(E_FLAT, MINOR, false),
        A_FLAT_MINOR = new KeySignature(A_FLAT, MINOR, false),
        D_FLAT_MINOR = new KeySignature(D_FLAT, MINOR, true),
        G_FLAT_MINOR = new KeySignature(G_FLAT, MINOR, true),
        C_FLAT_MINOR = new KeySignature(C_FLAT, MINOR, true),
        F_FLAT_MINOR = new KeySignature(F_FLAT, MINOR, true),

    // Minor, sharp
        E_MINOR = new KeySignature(E, MINOR, false),
        B_MINOR = new KeySignature(B, MINOR, false),
        F_SHARP_MINOR = new KeySignature(F_SHARP, MINOR, false),
        C_SHARP_MINOR = new KeySignature(C_SHARP, MINOR, false),
        G_SHARP_MINOR = new KeySignature(G_SHARP, MINOR, false),
        D_SHARP_MINOR = new KeySignature(D_SHARP, MINOR, false),
        A_SHARP_MINOR = new KeySignature(A_SHARP, MINOR, false),
        E_SHARP_MINOR = new KeySignature(E_SHARP, MINOR, true),
        B_SHARP_MINOR = new KeySignature(B_SHARP, MINOR, true),
        F_DOUBLE_SHARP_MINOR = new KeySignature(F_DOUBLE_SHARP, MINOR, true),
        C_DOUBLE_SHARP_MINOR = new KeySignature(C_DOUBLE_SHARP, MINOR, true),
        G_DOUBLE_SHARP_MINOR = new KeySignature(G_DOUBLE_SHARP, MINOR, true);

    public static final String KEY_SIGNATURE_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*) ([Mm])(ajor|inor)$";
    public static final Pattern PATTERN = Pattern.compile(KEY_SIGNATURE_REGEX);

    private static final Map<String, KeySignature> STANDARD_KEYSIG_LOOKUP = Collections.unmodifiableMap(initializeMap());

    private static Map<String, KeySignature> initializeMap() {
        Map<String, KeySignature> map = new HashMap<>();

        try {
            for (Field field : Class.forName(KeySignature.class.getName()).getDeclaredFields()) {
                if (field.getType().isAssignableFrom(KeySignature.class)) {
                    KeySignature toAdd = (KeySignature) field.get(Class.forName(KeySignature.class.getName()));
                    String name = Accidental.convertToUTF8Symbols(toAdd.getName());
                    map.put(name, toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }

    private final PitchClass KEY;
    private final KeySignatureType KEY_SIGNATURE_TYPE;
    private final boolean IS_THEORETICAL;
    private final List<PitchClass> SIGNATURE;
    private final String NAME;

    // Helper variables
    private final boolean IS_PURE_FLAT;
    private final boolean IS_PURE_SHARP;
    private final boolean IS_MIXED_FLAT_SHARP; // whether the KeySignature has PitchClasses with Accidentals like #b or b#
    private int indexToChange; // index of which item in SIGNATURE to change when modulating

    KeySignature(PitchClass key, KeySignatureType keySignatureType, boolean isTheoretical) {
        this.KEY = key;
        this.KEY_SIGNATURE_TYPE = keySignatureType;
        this.IS_THEORETICAL = isTheoretical;
        this.IS_PURE_FLAT = determineIsFlat(KEY, KEY_SIGNATURE_TYPE);
        this.IS_PURE_SHARP = determineIsSharp(KEY, KEY_SIGNATURE_TYPE);
        this.IS_MIXED_FLAT_SHARP = determineIsMixed(KEY, KEY_SIGNATURE_TYPE);

        if (KEY == null) {
            this.SIGNATURE = Collections.emptyList();
            this.NAME = "No Key Signature";
        } else {
            this.NAME = KEY.getName() + " " + (KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Major" : "Minor");

            // Determine the starting point for accidentals (always some enharmonic of Bb or F#)
            PitchClass p = KEY;
            Interval interval = Interval.getIntervalBetween(KEY, (IS_PURE_FLAT ? PitchClass.B_FLAT : PitchClass.F_SHARP), true);
            p = p.transpose(true, interval);

            // Figure out number of accidentals in the key signature
            int numAccidentals = getNumberOfAccidentals();
            this.indexToChange = numAccidentals % 7;

            // Add accidentals
            String[] accs = new String[7];
            if (IS_MIXED_FLAT_SHARP) {
                /*
                 * Mixed accidentals are a special case,
                 * because we want to preserve the original accidentals.
                 * To do this, we pre-load the 'accs' array
                 * with letter + (original accidentals).
                 */
                String[] flats = {"B", "E", "A", "D", "G", "C", "F"};
                String[] sharps = {"F", "C", "G", "D", "A", "E", "B"};

                String accidentals = KEY.getName().substring(1);

                int semitones = Accidental.sumAccidentalsToSemitoneModifier(accidentals);

                for (int i = 0; i < accs.length; i++) {
                    accs[i] = (semitones < 0 ? flats[i] : sharps[i]) + accidentals;
                }
            }

            for (int i = 0; i < numAccidentals; i++) {
                int j = i % accs.length;

                if (accs[j] == null) {
                    String utf8Temp = Accidental.convertToUTF8Symbols(p.getName());
                    String letterAndFirstAccidental = utf8Temp.length() > 1 ? utf8Temp.substring(0, 2) : utf8Temp;
                    accs[j] = letterAndFirstAccidental;
                    p = p.transpose(true, IS_PURE_FLAT ? Interval.P4 : Interval.P5);
                } else {
                    String temp = accs[j];
                    accs[j] = String.valueOf(temp.charAt(0))
                            + temp.substring(1, temp.length() - 1)
                            + Accidental.simplify(temp.substring(temp.length() - 1) + (IS_PURE_FLAT ? "b" : "#"), false, true);
                }
            }

            this.SIGNATURE = Arrays.stream(accs)
                    .filter(a -> a != null && !a.isEmpty())
                    .map(a -> PitchClass.withName(a, false))
                    .collect(Collectors.toList());
        }
    }

    private KeySignature(PitchClass key,
                         KeySignatureType keySignatureType,
                         List<PitchClass> signature,
                         int indexToChange,
                         boolean isTheoretical) {
        this.KEY = key;
        this.KEY_SIGNATURE_TYPE = keySignatureType;
        this.IS_THEORETICAL = isTheoretical;
        this.SIGNATURE = signature;
        this.indexToChange = indexToChange;
        this.IS_PURE_FLAT = determineIsFlat(KEY, KEY_SIGNATURE_TYPE);
        this.IS_PURE_SHARP = determineIsSharp(KEY, KEY_SIGNATURE_TYPE);
        this.IS_MIXED_FLAT_SHARP = determineIsMixed(KEY, KEY_SIGNATURE_TYPE);
        this.NAME = KEY.getName() + " " + (KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Major" : "Minor");
    }

    private static boolean determineIsFlat(PitchClass key, KeySignatureType type) {
        return determineAccidentalHelper(key, type, 1);
    }

    private static boolean determineIsSharp(PitchClass key, KeySignatureType type) {
        return determineAccidentalHelper(key, type, 2);
    }

    private static boolean determineIsMixed(PitchClass key, KeySignatureType type) {
        return determineAccidentalHelper(key, type, 3);
    }

    private static boolean determineAccidentalHelper(PitchClass key, KeySignatureType type, int flatSharpMixed) {
        if (key == null) return false;
        int accidentalSemitones = Accidental.sumAccidentalsToSemitoneModifier(key.getName());

        /*
         * MAJOR:
         *  FLATS: F, Bb, Eb, Ab, Db, Gb, Cb
         *  SHARPS: G, D, A, E, B, C#, F#
         *
         * MINOR:
         *  FLATS: Dm Gm, Cm, Fm, Bbm, Ebm, Abm
         *  SHARPS: Em Bm, F#m, C#m, G#m, D#m, A#m
         */
        String regex;
        boolean accidentalSemitoneDirection;
        if (flatSharpMixed == 1) {
            regex = "^(((Fm?)|([BEA]b))m?)|([DGC][bm])$";
            accidentalSemitoneDirection = accidentalSemitones < 0;
        } else if (flatSharpMixed == 2) {
            regex = "^(([EB]m?)|([CF]#m?)|([GDA](#m)?))$";
            accidentalSemitoneDirection = accidentalSemitones > 0;
        } else {
            // mixture of flats and sharps
            String utf8Symbols = Accidental.convertToUTF8Symbols(key.getName());
            regex = "^[A-G].*((b[#x])|([#x]b)).*$";
            return utf8Symbols.matches(regex);
        }

        String letterAndQuality = String.valueOf(key.getName().charAt(0));
        if (accidentalSemitones < 0) {
            letterAndQuality += "b";
        } else if (accidentalSemitones > 0) {
            letterAndQuality += "#";
        }

        if (type.equals(MINOR)) {
            letterAndQuality += "m";
        }

        return accidentalSemitoneDirection || letterAndQuality.matches(regex);

    }

    private int getNumberOfAccidentals() {
        if (this.KEY == null || "C Major".equals(this.NAME) || "A Minor".equals(this.NAME)) {
            return 0;
        }

        String key = Accidental.convertToUTF8Symbols(KEY.getName());
        String letter = String.valueOf(key.charAt(0));

        String prefixKey = String.valueOf(key.charAt(0));

        if (KEY_SIGNATURE_TYPE.equals(MINOR)) {
            prefixKey += "m";
        }

        String accidentals = key.substring(1);
        int semitones = Math.abs(Accidental.sumAccidentalsToSemitoneModifier(accidentals));
        String lastAcc = (accidentals.isEmpty() ? "" : String.valueOf(accidentals.charAt(accidentals.length() - 1)));

        /*
         * MAJOR:
         *  FLATS: F(1), Bb(2), Eb(3), Ab(4), Db(5), Gb(6), Cb(7)
         *  SHARPS: G(1), D(2), A(3), E(4), B(5), F#(6), C#(7)
         *
         * MINOR:
         *  FLATS: Dm(1) Gm(2), Cm(3), Fm(4), Bbm(5), Ebm(6), Abm(7)
         *  SHARPS: Em(1), Bm(2), F#m(3), C#m(4), G#m(5), D#m(6), A#m(7)
         */
        String one, two, three, four, five, six, seven;
        if (IS_PURE_FLAT || (IS_MIXED_FLAT_SHARP && "b".equals(lastAcc))) {
            one = "F|Dm|";
            two = "B|Gm|";
            three = "E|Cm|";
            four = "A|Fm|";
            five = "D|Bm|";
            six = "G|Em|";
            seven = "C|Am|";
        } else {
            one = "G|Em|";
            two = "D|Bm|";
            three = "A|Fm|";
            four = "E|Cm|";
            five = "B|Gm|";
            six = "F|Dm|";
            seven = "C|Am|";
        }

        prefixKey += "|";
        int lookup;
        if (one.contains(prefixKey)) {
            lookup = 1;
        } else if (two.contains(prefixKey)) {
            lookup = 2;
        } else if (three.contains(prefixKey)) {
            lookup = 3;
        } else if (four.contains(prefixKey)) {
            lookup = 4;
        } else if (five.contains(prefixKey)) {
            lookup = 5;
        } else if (six.contains(prefixKey)) {
            lookup = 6;
        } else if (seven.contains(prefixKey)) {
            lookup = 7;
        } else {
            lookup = -1;
            semitones = 0;
        }

        if (KEY_SIGNATURE_TYPE.equals(MAJOR)) {
            return getNumAccHelper(letter, lookup, semitones, "F", "[^FC]");
        } else {
            return getNumAccHelper(letter, lookup, semitones, "[DGCF]", "[EB]");
        }
    }

    private int getNumAccHelper(String letter,
                                int lookup,
                                int semitones,
                                String regex1,
                                String regex2) {
        if (IS_PURE_FLAT) {
            if (letter.matches(regex1)) {
                return semitones == 0 ? lookup : (lookup + (7 * (semitones)));
            }
            return semitones == 0 ? lookup : (lookup + (7 * (semitones - 1)));
        } else if (IS_PURE_SHARP) {
            if (letter.matches(regex2)) {
                return semitones == 0 ? lookup : (lookup + (7 * (semitones)));
            }
            return semitones == 0 ? lookup : (lookup + (7 * (semitones - 1)));
        } else {
            return semitones;
        }
    }

    /**
     * 
     * @param name
     * @return
     */
    public static KeySignature withName(String name) {
        Matcher matcher = PATTERN.matcher(name);

        if (matcher.matches()) {

            String scrubbedName = Accidental.convertToUTF8Symbols(name);

            KeySignature cachedKeySig = STANDARD_KEYSIG_LOOKUP.get(scrubbedName);
            if (cachedKeySig != null) {
                return cachedKeySig;
            }

            String letter = matcher.group(1);
            String accidentals = Accidental.convertToUTF8Symbols(matcher.group(2) == null ? "" : matcher.group(2));
            String type = matcher.group(4) + matcher.group(5);

            PitchClass key = PitchClass.withName(letter + accidentals, false);
            return new KeySignature(key, "Major".equals(type) ? MAJOR : MINOR, true);
        }

        throw new RuntimeException();
    }

    public PitchClass getKey() {
        return KEY;
    }

    public List<PitchClass> getSignature() {
        return new ArrayList<>(SIGNATURE);
    }

    public KeySignatureType getType() {
        return KEY_SIGNATURE_TYPE;
    }

    public boolean isTheoretical() {
        return IS_THEORETICAL;
    }

    public String getName() {
        return NAME;
    }

    public boolean contains(@NotNull PitchClass pitchClass) {
        return SIGNATURE.contains(pitchClass);
    }

    /**
     *
     * @return
     */
    public KeySignature getRelativeKey() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }
        PitchClass relativePitch = KEY.transpose(KEY_SIGNATURE_TYPE.equals(MINOR), Interval.m3);
        return KeySignature.withName(relativePitch.getName() + " " + (KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Minor" : "Major"));
    }

    /**
     *
     * @return
     */
    public KeySignature getParallelKey() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }
        return KeySignature.withName(KEY.getName() + " " + (this.KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Minor" : "Major"));
    }

    /**
     * Advance the {@link KeySignature} by one flat.
     *      C -> F -> Bb -> Eb -> Ab -> Db -> Gb -> Cb, etc.
     * @return the resulting {@link KeySignature} after modulation
     */
    public KeySignature modulateFlat() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }

        // Try to find from cache
        PitchClass newKey = KEY.transpose(true, Interval.P4);
        KeySignature cached = STANDARD_KEYSIG_LOOKUP.get(Accidental.convertToUTF8Symbols(newKey.getName()) + " " + (this.KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Major" : "Minor"));
        if (cached != null) {
            return cached;
        }

        // Modify the signature as necessary and return a non-standard KeySignature
        List<PitchClass> newSignature = new ArrayList<>(SIGNATURE);
        int newIndexToChange;
        if (IS_PURE_FLAT) {

            // Add the next flat to the signature
            if (newSignature.size() == 7) {
                newSignature.set(indexToChange, PitchClass.withName(newSignature.get(indexToChange).getName() + "b", false));
            } else {
                PitchClass added = PitchClass.withName(newSignature.get(indexToChange).getName(), false)
                        .transpose(true, Interval.P4);
                newSignature.add(added);
            }

            newIndexToChange = (indexToChange + 1) % 7;
        } else {

            // Remove the last sharp from the signature
            newIndexToChange = (indexToChange == 0 ? 6 : indexToChange - 1);
            String current = Accidental.convertToUTF8Symbols(newSignature.get(newIndexToChange).getName());
            if (current.length() >= 2) {
                current = current.substring(0, current.length() - 1);
                newSignature.set(newIndexToChange, PitchClass.withName(current, false));
            } else {
                newSignature.remove(newIndexToChange);
            }
        }

        return new KeySignature(newKey, KEY_SIGNATURE_TYPE, newSignature, newIndexToChange, true);
    }

    /**
     * Advance the {@link KeySignature} by one sharp.
     *      C -> G -> D -> A -> E -> B -> F# -> C#, etc.
     * @return the resulting {@link KeySignature} after modulation
     */
    public KeySignature modulateSharp() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }

        // Try to find from cache
        PitchClass newKey = KEY.transpose(true, Interval.P5);
        KeySignature cached = STANDARD_KEYSIG_LOOKUP.get(Accidental.convertToUTF8Symbols(newKey.getName()) + " " + (this.KEY_SIGNATURE_TYPE.equals(MAJOR) ? "Major" : "Minor"));
        if (cached != null) {
            return cached;
        }

        // Modify the signature as necessary and return a non-standard KeySignature
        List<PitchClass> newSignature = new ArrayList<>(SIGNATURE);
        int newIndexToChange;
        if (IS_PURE_FLAT) {

            // Remove the last flat from the signature
            newIndexToChange = (indexToChange == 0 ? 6 : indexToChange - 1);
            String current = Accidental.convertToUTF8Symbols(newSignature.get(newIndexToChange).getName());
            if (current.length() >= 2) {
                current = current.substring(0, current.length() - 1);
                newSignature.set(newIndexToChange, PitchClass.withName(current, false));
            } else {
                newSignature.remove(newIndexToChange);
            }
        } else {

            // Add the next sharp to the signature
            if (newSignature.size() == 7) {
                String current = Accidental.convertToUTF8Symbols(newSignature.get(indexToChange).getName());
                if (current.charAt(current.length() - 1) == '#') {
                    current = current.substring(0, current.length() - 1) + "x";
                } else {
                    current += "#";
                }
                newSignature.set(indexToChange, PitchClass.withName(current, false));
            } else {
                PitchClass added = PitchClass.withName(newSignature.get(indexToChange).getName(), false)
                        .transpose(true, Interval.P5);
                newSignature.add(added);
            }

            newIndexToChange = (indexToChange + 1) % 7;
        }

        return new KeySignature(newKey, KEY_SIGNATURE_TYPE, newSignature, newIndexToChange, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !other.getClass().isAssignableFrom(KeySignature.class)) {
            return false;
        }

        KeySignature comparison = (KeySignature) other;

        if (comparison.KEY == null && this.KEY == null) {
            return true;
        }

        if (comparison.KEY == null || this.KEY == null) {
            return false;
        }

        return comparison.KEY.equals(this.KEY)
                && comparison.KEY_SIGNATURE_TYPE.equals(this.KEY_SIGNATURE_TYPE)
                && comparison.IS_PURE_FLAT == this.IS_PURE_FLAT
                && comparison.IS_THEORETICAL == this.IS_THEORETICAL
                && comparison.SIGNATURE.containsAll(this.SIGNATURE);
    }

}
