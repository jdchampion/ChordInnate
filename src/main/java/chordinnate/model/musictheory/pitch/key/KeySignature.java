package chordinnate.model.musictheory.pitch.key;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static chordinnate.model.musictheory.pitch.key.KeySignatureType.*;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.Letter;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 *
 * @see <a href=https://en.wikipedia.org/wiki/Relative_key>1</a>
 * @see <a href=https://en.wikipedia.org/wiki/Theoretical_key>2</a>
 */
@Slf4j
public class KeySignature {

    // No key signature
    public static final KeySignature NO_KEY_SIGNATURE = new KeySignature(null, NONE,true);

    // Major, non-accidental
    public static final KeySignature C_MAJOR = new KeySignature(C, MAJOR,false);

    // Major, flat
    public static final KeySignature F_MAJOR = new KeySignature(F, MAJOR, false);
    public static final KeySignature B_FLAT_MAJOR = new KeySignature(B_FLAT, MAJOR, false);
    public static final KeySignature E_FLAT_MAJOR = new KeySignature(E_FLAT, MAJOR, false);
    public static final KeySignature A_FLAT_MAJOR = new KeySignature(A_FLAT, MAJOR, false);
    public static final KeySignature D_FLAT_MAJOR = new KeySignature(D_FLAT, MAJOR, false);
    public static final KeySignature G_FLAT_MAJOR = new KeySignature(G_FLAT, MAJOR, false);
    public static final KeySignature C_FLAT_MAJOR = new KeySignature(C_FLAT, MAJOR, false);
    public static final KeySignature F_FLAT_MAJOR = new KeySignature(F_FLAT, MAJOR, true);
    public static final KeySignature B_DOUBLE_FLAT_MAJOR = new KeySignature(B_DOUBLE_FLAT, MAJOR, true);
    public static final KeySignature E_DOUBLE_FLAT_MAJOR = new KeySignature(E_DOUBLE_FLAT, MAJOR, true);
    public static final KeySignature A_DOUBLE_FLAT_MAJOR = new KeySignature(A_DOUBLE_FLAT, MAJOR, true);

    // Major, sharp
    public static final KeySignature G_MAJOR = new KeySignature(G, MAJOR,false);
    public static final KeySignature D_MAJOR = new KeySignature(D, MAJOR,false);
    public static final KeySignature A_MAJOR = new KeySignature(A, MAJOR,false);
    public static final KeySignature E_MAJOR = new KeySignature(E, MAJOR,false);
    public static final KeySignature B_MAJOR = new KeySignature(B, MAJOR,false);
    public static final KeySignature F_SHARP_MAJOR = new KeySignature(F_SHARP, MAJOR, false);
    public static final KeySignature C_SHARP_MAJOR = new KeySignature(C_SHARP, MAJOR, false);
    public static final KeySignature G_SHARP_MAJOR = new KeySignature(G_SHARP, MAJOR, true);
    public static final KeySignature D_SHARP_MAJOR = new KeySignature(D_SHARP, MAJOR, true);
    public static final KeySignature A_SHARP_MAJOR = new KeySignature(A_SHARP, MAJOR, true);
    public static final KeySignature E_SHARP_MAJOR = new KeySignature(E_SHARP, MAJOR, true);
    public static final KeySignature B_SHARP_MAJOR = new KeySignature(B_SHARP, MAJOR, true);

    // Minor, non-accidental
    public static final KeySignature A_MINOR = new KeySignature(A, MINOR, false);

    // Minor, flat
    public static final KeySignature D_MINOR = new KeySignature(D, MINOR, false);
    public static final KeySignature G_MINOR = new KeySignature(G, MINOR, false);
    public static final KeySignature C_MINOR = new KeySignature(C, MINOR, false);
    public static final KeySignature F_MINOR = new KeySignature(F, MINOR, false);
    public static final KeySignature B_FLAT_MINOR = new KeySignature(B_FLAT, MINOR, false);
    public static final KeySignature E_FLAT_MINOR = new KeySignature(E_FLAT, MINOR, false);
    public static final KeySignature A_FLAT_MINOR = new KeySignature(A_FLAT, MINOR, false);
    public static final KeySignature D_FLAT_MINOR = new KeySignature(D_FLAT, MINOR, true);
    public static final KeySignature G_FLAT_MINOR = new KeySignature(G_FLAT, MINOR, true);
    public static final KeySignature C_FLAT_MINOR = new KeySignature(C_FLAT, MINOR, true);
    public static final KeySignature F_FLAT_MINOR = new KeySignature(F_FLAT, MINOR, true);

    // Minor, sharp
    public static final KeySignature E_MINOR = new KeySignature(E, MINOR, false);
    public static final KeySignature B_MINOR = new KeySignature(B, MINOR, false);
    public static final KeySignature F_SHARP_MINOR = new KeySignature(F_SHARP, MINOR, false);
    public static final KeySignature C_SHARP_MINOR = new KeySignature(C_SHARP, MINOR, false);
    public static final KeySignature G_SHARP_MINOR = new KeySignature(G_SHARP, MINOR, false);
    public static final KeySignature D_SHARP_MINOR = new KeySignature(D_SHARP, MINOR, false);
    public static final KeySignature A_SHARP_MINOR = new KeySignature(A_SHARP, MINOR, false);
    public static final KeySignature E_SHARP_MINOR = new KeySignature(E_SHARP, MINOR, true);
    public static final KeySignature B_SHARP_MINOR = new KeySignature(B_SHARP, MINOR, true);
    public static final KeySignature F_DOUBLE_SHARP_MINOR = new KeySignature(F_DOUBLE_SHARP, MINOR, true);
    public static final KeySignature C_DOUBLE_SHARP_MINOR = new KeySignature(C_DOUBLE_SHARP, MINOR, true);
    public static final KeySignature G_DOUBLE_SHARP_MINOR = new KeySignature(G_DOUBLE_SHARP, MINOR, true);

    public static final String KEY_SIGNATURE_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*) ([Mm])(ajor|inor)$";
    public static final Pattern PATTERN = Pattern.compile(KEY_SIGNATURE_REGEX);

    private static final String PURE_FLAT_KEY_SIGNATURE_REGEX = "^(([BEA]b+)m?)|([DGC]b+m?)|([DGC]m)|(Fb*m?)$";
    private static final String PURE_SHARP_KEY_SIGNATURE_REGEX = "^(([FC][#x]+)m?)|([GD](([#x]+m)|[#x]*))|([AEB][#x]*m?)$";
    private static final String MIXED_FLAT_SHARP_KEY_SIGNATURE_REGEX = "^[A-G]([b#x]*((b[#x])|([#x]b))[b#x]*)m?$";

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
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }

    private final PitchClass key;
    private final KeySignatureType keySignatureType;
    private final boolean isTheoretical;
    private final List<PitchClass> signature;
    private final String name;

    // Helper variables
    private final boolean isPureFlat;
    private final boolean isPureSharp;
    private final boolean isMixedFlatSharp; // whether the KeySignature has PitchClasses with Accidentals like #b or b#
    private int indexToChange; // index of which item in signature to change when modulating

    KeySignature(PitchClass key, KeySignatureType keySignatureType, boolean isTheoretical) {
        this.key = key;
        this.keySignatureType = keySignatureType;
        this.isTheoretical = isTheoretical;
        this.isPureFlat = determineIsFlat(this.key, this.keySignatureType);
        this.isPureSharp = determineIsSharp(this.key, this.keySignatureType);
        this.isMixedFlatSharp = determineIsMixed(this.key, this.keySignatureType);

        if (this.key == null) {
            this.signature = Collections.emptyList();
            this.name = "No Key Signature";
        } else {
            this.name = determineName(this.key, this.keySignatureType, false);

            // Add accidentals
            if (isMixedFlatSharp) {
                this.signature = getMixedFlatSharpSignature();
            } else {
                this.signature = getStandardSignature();
            }
        }
    }

    private List<PitchClass> getMixedFlatSharpSignature() {
        /*
         * Mixed accidentals are a special case,
         * because we want to preserve the original accidentals.
         */
        PitchClass[] pitchClasses = new PitchClass[7];

        Letter letter = Letter.valueOf(this.key.getName().substring(0, 1));
        String accidentals = this.key.getName().substring(1);

        boolean isFlat = Accidental.sumAccidentalsToSemitoneModifier(accidentals) < 0
                || (letter.name() + Accidental.simplify(accidentals, false, true))
                .matches(PURE_FLAT_KEY_SIGNATURE_REGEX);

        Interval[] intervalsToUse = {Interval.PERFECT_1, Interval.MAJOR_2, this.keySignatureType.equals(MAJOR) ? Interval.MAJOR_3 : Interval.MINOR_3, Interval.PERFECT_4, Interval.PERFECT_5, this.keySignatureType.equals(MAJOR) ? Interval.MAJOR_6 : Interval.MINOR_6, this.keySignatureType.equals(MAJOR) ? Interval.MAJOR_7 : Interval.MINOR_7};

        int startingIndex = Letter.getVectorDistanceBetween(letter, isFlat ? Letter.B : Letter.F) % 7;
        int idx = isFlat ? 3 : 4;

        for (int i = 0, j = startingIndex; i < pitchClasses.length; i++, j = (j + idx) % pitchClasses.length) {
            pitchClasses[i] = this.key.transpose(IntervalDirection.UP, intervalsToUse[j]);
        }

        return Arrays.stream(pitchClasses).collect(Collectors.toList());
    }

    private List<PitchClass> getStandardSignature() {

        // Figure out number of accidentals in the key signature (could be <= 7)
        int numAccidentals = getNumberOfAccidentals();
        this.indexToChange = numAccidentals % 7;

        // Determine the starting point for accidentals (always some enharmonic of Bb or F#)
        Interval startInterval = Interval.between(this.key, (isPureFlat ? PitchClass.B_FLAT : PitchClass.F_SHARP), true);
        PitchClass currentPC = this.key.transpose(IntervalDirection.UP, startInterval);

        String[] accs = new String[7];
        for (int i = 0; i < numAccidentals; i++) {
            int j = i % accs.length;

            if (accs[j] == null) {
                String utf8Temp = Accidental.convertToUTF8Symbols(currentPC.getName());
                String letterAndFirstAccidental = utf8Temp.length() > 1 ? utf8Temp.substring(0, 2) : utf8Temp;
                accs[j] = letterAndFirstAccidental;
                currentPC = currentPC.transpose(IntervalDirection.UP, isPureFlat ? Interval.PERFECT_4 : Interval.PERFECT_5);
            } else {
                String temp = accs[j];
                accs[j] = temp.charAt(0)
                        + temp.substring(1, temp.length() - 1)
                        + Accidental.simplify(temp.substring(temp.length() - 1) + (isPureFlat ? Accidental.FLAT.utf8Symbol : Accidental.SHARP.utf8Symbol), false, true);
            }
        }

        return Arrays.stream(accs)
                .filter(StringUtils::isNotBlank)
                .map(a -> PitchClass.withName(a, false))
                .collect(Collectors.toList());
    }

    private KeySignature(@NotNull PitchClass key,
                         KeySignatureType keySignatureType,
                         List<PitchClass> signature,
                         int indexToChange,
                         boolean isTheoretical) {
        this.key = key;
        this.keySignatureType = keySignatureType;
        this.isTheoretical = isTheoretical;
        this.signature = signature;
        this.indexToChange = indexToChange;
        this.isPureFlat = determineIsFlat(this.key, this.keySignatureType);
        this.isPureSharp = determineIsSharp(this.key, this.keySignatureType);
        this.isMixedFlatSharp = determineIsMixed(this.key, this.keySignatureType);
        this.name = determineName(this.key, this.keySignatureType, false);
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
        if (flatSharpMixed == 1) {
            regex = PURE_FLAT_KEY_SIGNATURE_REGEX;
        } else if (flatSharpMixed == 2) {
            regex = PURE_SHARP_KEY_SIGNATURE_REGEX;
        } else {
            // mixture of flats and sharps
            regex = MIXED_FLAT_SHARP_KEY_SIGNATURE_REGEX;
        }

        String utf8Name = Accidental.convertToUTF8Symbols(key.getName()) + (type.equals(MINOR) ? "m" : "");

        return utf8Name.matches(regex);

    }

    private static String determineName(PitchClass pitchClass, KeySignatureType keySignatureType, boolean flipMajorMinor) {
        if (flipMajorMinor) {
            return pitchClass.getName() + " " + (MAJOR.equals(keySignatureType) ? MINOR.label : MAJOR.label);
        } else {
            return pitchClass.getName() + " " + (MAJOR.equals(keySignatureType) ? MAJOR.label : MINOR.label);
        }
    }

    private int getNumberOfAccidentals() {
        if (this.key == null || "C Major".equals(this.name) || "A Minor".equals(this.name)) {
            return 0;
        }

        String keyName = Accidental.convertToUTF8Symbols(this.key.getName());
        String letter = String.valueOf(keyName.charAt(0));

        String keyNamePrefix = String.valueOf(keyName.charAt(0));

        if (keySignatureType.equals(MINOR)) {
            keyNamePrefix += "m";
        }

        String accidentals = keyName.substring(1);
        int semitones = Accidental.sumAccidentalsToSemitoneModifier(accidentals);

        /*
         * MAJOR:
         *  FLATS: F(1), Bb(2), Eb(3), Ab(4), Db(5), Gb(6), Cb(7)
         *  SHARPS: G(1), D(2), A(3), E(4), B(5), F#(6), C#(7)
         *
         * MINOR:
         *  FLATS: Dm(1) Gm(2), Cm(3), Fm(4), Bbm(5), Ebm(6), Abm(7)
         *  SHARPS: Em(1), Bm(2), F#m(3), C#m(4), G#m(5), D#m(6), A#m(7)
         */
        String[] codes;
        if (isPureFlat || (semitones < 0 || (semitones == 0 && "F".equals(letter)))) {
            codes = new String[]{"F|Dm|", "B|Gm|", "E|Cm|", "A|Fm|", "D|Bm|", "G|Em|", "C|Am|"};
        } else {
            codes = new String[]{"G|Em|", "D|Bm|", "A|Fm|", "E|Cm|", "B|Gm|", "F|Dm|", "C|Am|"};
        }
        keyNamePrefix += "|";
        int lookup = -1;
        for (int i = 0; i < codes.length; i++) {
            if (codes[i].contains(keyNamePrefix)) {
                lookup = i + 1;
                break;
            }
        }
        if (lookup < 0) {
            semitones = 0;
        }

        if (keySignatureType.equals(MAJOR)) {
            return determineNumAccidentals(letter, lookup, semitones, "F", "[^FC]");
        } else {
            return determineNumAccidentals(letter, lookup, semitones, "[DGCF]", "[EB]");
        }
    }

    private int determineNumAccidentals(String letter,
                                        int lookup,
                                        int semitones,
                                        String regex1,
                                        String regex2) {
        if (isPureFlat || (semitones < 0 || (semitones == 0 && "F".equals(letter)))) {
            return determineNumAccidentals(letter, regex1, semitones, lookup);
        } else if (isPureSharp || semitones > 0) {
            return determineNumAccidentals(letter, regex2, semitones, lookup);
        } else {
            return Math.abs(semitones);
        }
    }

    private int determineNumAccidentals(String letter, String regex, int semitones, int lookup) {
        if (semitones == 0) {
            return lookup;
        }

        return (lookup + (7 * (Math.abs(semitones) - (letter.matches(regex) ? 0 : 1))));
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
            String type = matcher.group(4).toUpperCase() + matcher.group(5);

            PitchClass key = PitchClass.withName(letter + accidentals, false);
            return new KeySignature(key, MAJOR.label.equals(type) ? MAJOR : MINOR, true);
        }

        throw new IllegalArgumentException("Invalid key signature name [" + name + "]");
    }

    public PitchClass getKey() {
        return key;
    }

    public List<PitchClass> getSignature() {
        return new ArrayList<>(signature);
    }

    public KeySignatureType getType() {
        return keySignatureType;
    }

    public boolean isTheoretical() {
        return isTheoretical;
    }

    public String getName() {
        return name;
    }

    public boolean contains(@NotNull PitchClass pitchClass) {
        return signature.contains(pitchClass);
    }

    /**
     *
     * @return
     */
    public KeySignature getRelativeKey() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }
        PitchClass relativePitch = key.transpose(keySignatureType.equals(MINOR) ? IntervalDirection.UP : IntervalDirection.DOWN, Interval.MINOR_3);
        return KeySignature.withName(determineName(relativePitch, keySignatureType, true));
    }

    /**
     *
     * @return
     */
    public KeySignature getParallelKey() {
        if (this.equals(NO_KEY_SIGNATURE)) {
            return NO_KEY_SIGNATURE;
        }
        return KeySignature.withName(determineName(key, keySignatureType, true));
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
        PitchClass newKey = key.transpose(IntervalDirection.UP, Interval.PERFECT_4);
        KeySignature cached = STANDARD_KEYSIG_LOOKUP.get(Accidental.convertToUTF8Symbols(determineName(newKey, keySignatureType, false)));
        if (cached != null) {
            return cached;
        }

        if (isMixedFlatSharp) {
            return KeySignature.withName(determineName(newKey, keySignatureType,false));
        }

        // Modify the signature as necessary and return a non-standard KeySignature
        List<PitchClass> newSignature = new ArrayList<>(signature);
        int newIndexToChange;
        if (isPureFlat) {

            // Add the next flat to the signature
            if (newSignature.size() == 7) {
                newSignature.set(indexToChange, PitchClass.withName(newSignature.get(indexToChange).getName() + Accidental.FLAT.symbol, false));
            } else {
                PitchClass added = PitchClass.withName(newSignature.get(indexToChange).getName(), false)
                        .transpose(IntervalDirection.UP, Interval.PERFECT_4);
                newSignature.add(added);
            }

            newIndexToChange = (indexToChange + 1) % 7;
        } else {

            // Remove the last sharp from the signature
            newIndexToChange = (indexToChange == 0 ? 6 : indexToChange - 1);
            removeLastAcc(newIndexToChange, newSignature);
        }

        return new KeySignature(newKey, keySignatureType, newSignature, newIndexToChange, true);
    }

    private void removeLastAcc(int newIndexToChange, List<PitchClass> newSignature) {
        String current = Accidental.convertToUTF8Symbols(newSignature.get(newIndexToChange).getName());
        if (current.length() >= 2) {
            current = current.substring(0, current.length() - 1);
            newSignature.set(newIndexToChange, PitchClass.withName(current, false));
        } else {
            newSignature.remove(newIndexToChange);
        }
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
        PitchClass newKey = key.transpose(IntervalDirection.UP, Interval.PERFECT_5);
        KeySignature cached = STANDARD_KEYSIG_LOOKUP.get(Accidental.convertToUTF8Symbols(determineName(newKey, keySignatureType, false)));
        if (cached != null) {
            return cached;
        }

        if (isMixedFlatSharp) {
            return KeySignature.withName(determineName(newKey, keySignatureType, false));
        }

        // Modify the signature as necessary and return a non-standard KeySignature
        List<PitchClass> newSignature = new ArrayList<>(signature);
        int newIndexToChange = indexToChange;
        if (isPureFlat) {

            // Remove the last flat from the signature
            newIndexToChange = (indexToChange == 0 ? 6 : indexToChange - 1);
            removeLastAcc(newIndexToChange, newSignature);
        } else if (isPureSharp) {

            // Add the next sharp to the signature
            if (newSignature.size() == 7) {
                String current = Accidental.convertToUTF8Symbols(newSignature.get(indexToChange).getName());
                if (Accidental.SHARP.equals(Accidental.getBySymbol(current.charAt(current.length() - 1)))) {
                    current = current.substring(0, current.length() - 1) + Accidental.DOUBLE_SHARP.utf8Symbol;
                } else {
                    current += Accidental.SHARP.utf8Symbol;
                }
                newSignature.set(indexToChange, PitchClass.withName(current, false));
            } else {
                PitchClass added = PitchClass.withName(newSignature.get(indexToChange).getName(), false)
                        .transpose(IntervalDirection.UP, Interval.PERFECT_5);
                newSignature.add(added);
            }

            newIndexToChange = (indexToChange + 1) % 7;
        }

        return new KeySignature(newKey, keySignatureType, newSignature, newIndexToChange, true);
    }

}
