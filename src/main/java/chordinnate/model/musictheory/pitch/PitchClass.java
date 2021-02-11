package chordinnate.model.musictheory.pitch;

import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.notation.Letter;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
import chordinnate.model.musictheory.pitch.interval.set.IntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph on 5/13/18.
 *
 * @see <a href=https://en.wikipedia.org/wiki/Pitch_class>1</a>
 */
public class PitchClass
        implements IntervalTransposable<PitchClass>, Enharmonic<PitchClass>, Diatonic {

    public static final PitchClass C_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.C_DOUBLE_FLAT, -2);
    public static final PitchClass C_FLAT = new PitchClass(EnharmonicSpelling.C_FLAT, -1);
    public static final PitchClass C = new PitchClass(EnharmonicSpelling.C, 0);
    public static final PitchClass C_NATURAL = new PitchClass(EnharmonicSpelling.C_NATURAL, 0);
    public static final PitchClass C_SHARP = new PitchClass(EnharmonicSpelling.C_SHARP, 1);
    public static final PitchClass C_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.C_DOUBLE_SHARP, 2);

    public static final PitchClass D_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.D_DOUBLE_FLAT, 0);
    public static final PitchClass D_FLAT = new PitchClass(EnharmonicSpelling.D_FLAT, 1);
    public static final PitchClass D = new PitchClass(EnharmonicSpelling.D, 2);
    public static final PitchClass D_NATURAL = new PitchClass(EnharmonicSpelling.D_NATURAL, 2);
    public static final PitchClass D_SHARP = new PitchClass(EnharmonicSpelling.D_SHARP, 3);
    public static final PitchClass D_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.D_DOUBLE_SHARP, 4);

    public static final PitchClass E_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.E_DOUBLE_FLAT, 2);
    public static final PitchClass E_FLAT = new PitchClass(EnharmonicSpelling.E_FLAT, 3);
    public static final PitchClass E = new PitchClass(EnharmonicSpelling.E, 4);
    public static final PitchClass E_NATURAL = new PitchClass(EnharmonicSpelling.E_NATURAL, 4);
    public static final PitchClass E_SHARP = new PitchClass(EnharmonicSpelling.E_SHARP, 5);
    public static final PitchClass E_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.E_DOUBLE_SHARP, 6);

    public static final PitchClass F_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.F_DOUBLE_FLAT, 3);
    public static final PitchClass F_FLAT = new PitchClass(EnharmonicSpelling.F_FLAT, 4);
    public static final PitchClass F = new PitchClass(EnharmonicSpelling.F, 5);
    public static final PitchClass F_NATURAL = new PitchClass(EnharmonicSpelling.F_NATURAL, 5);
    public static final PitchClass F_SHARP = new PitchClass(EnharmonicSpelling.F_SHARP, 6);
    public static final PitchClass F_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.F_DOUBLE_SHARP, 7);

    public static final PitchClass G_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.G_DOUBLE_FLAT, 5);
    public static final PitchClass G_FLAT = new PitchClass(EnharmonicSpelling.G_FLAT, 6);
    public static final PitchClass G = new PitchClass(EnharmonicSpelling.G, 7);
    public static final PitchClass G_NATURAL = new PitchClass(EnharmonicSpelling.G_NATURAL, 7);
    public static final PitchClass G_SHARP = new PitchClass(EnharmonicSpelling.G_SHARP, 8);
    public static final PitchClass G_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.G_DOUBLE_SHARP, 9);

    public static final PitchClass A_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.A_DOUBLE_FLAT, 7);
    public static final PitchClass A_FLAT = new PitchClass(EnharmonicSpelling.A_FLAT, 8);
    public static final PitchClass A = new PitchClass(EnharmonicSpelling.A, 9);
    public static final PitchClass A_NATURAL = new PitchClass(EnharmonicSpelling.A_NATURAL, 9);
    public static final PitchClass A_SHARP = new PitchClass(EnharmonicSpelling.A_SHARP, 10);
    public static final PitchClass A_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.A_DOUBLE_SHARP, 11);

    public static final PitchClass B_DOUBLE_FLAT = new PitchClass(EnharmonicSpelling.B_DOUBLE_FLAT, 9);
    public static final PitchClass B_FLAT = new PitchClass(EnharmonicSpelling.B_FLAT, 10);
    public static final PitchClass B = new PitchClass(EnharmonicSpelling.B, 11);
    public static final PitchClass B_NATURAL = new PitchClass(EnharmonicSpelling.B_NATURAL, 11);
    public static final PitchClass B_SHARP = new PitchClass(EnharmonicSpelling.B_SHARP, 12);
    public static final PitchClass B_DOUBLE_SHARP = new PitchClass(EnharmonicSpelling.B_DOUBLE_SHARP, 13);

    static final Map<String, PitchClass> STANDARD_PITCH_CLASSES = new HashMap<>();

    static {
        STANDARD_PITCH_CLASSES.put("Cbb", PitchClass.C_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Cb", PitchClass.C_FLAT);
        STANDARD_PITCH_CLASSES.put("C", PitchClass.C);
        STANDARD_PITCH_CLASSES.put("C_", PitchClass.C_NATURAL);
        STANDARD_PITCH_CLASSES.put("C#", PitchClass.C_SHARP);
        STANDARD_PITCH_CLASSES.put("Cx", PitchClass.C_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Dbb", PitchClass.D_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Db", PitchClass.D_FLAT);
        STANDARD_PITCH_CLASSES.put("D", PitchClass.D);
        STANDARD_PITCH_CLASSES.put("D_", PitchClass.D_NATURAL);
        STANDARD_PITCH_CLASSES.put("D#", PitchClass.D_SHARP);
        STANDARD_PITCH_CLASSES.put("Dx", PitchClass.D_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Ebb", PitchClass.E_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Eb", PitchClass.E_FLAT);
        STANDARD_PITCH_CLASSES.put("E", PitchClass.E);
        STANDARD_PITCH_CLASSES.put("E_", PitchClass.E_NATURAL);
        STANDARD_PITCH_CLASSES.put("E#", PitchClass.E_SHARP);
        STANDARD_PITCH_CLASSES.put("Ex", PitchClass.E_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Fbb", PitchClass.F_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Fb", PitchClass.F_FLAT);
        STANDARD_PITCH_CLASSES.put("F", PitchClass.F);
        STANDARD_PITCH_CLASSES.put("F_", PitchClass.F_NATURAL);
        STANDARD_PITCH_CLASSES.put("F#", PitchClass.F_SHARP);
        STANDARD_PITCH_CLASSES.put("Fx", PitchClass.F_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Gbb", PitchClass.G_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Gb", PitchClass.G_FLAT);
        STANDARD_PITCH_CLASSES.put("G", PitchClass.G);
        STANDARD_PITCH_CLASSES.put("G_", PitchClass.G_NATURAL);
        STANDARD_PITCH_CLASSES.put("G#", PitchClass.G_SHARP);
        STANDARD_PITCH_CLASSES.put("Gx", PitchClass.G_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Abb", PitchClass.A_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Ab", PitchClass.A_FLAT);
        STANDARD_PITCH_CLASSES.put("A", PitchClass.A);
        STANDARD_PITCH_CLASSES.put("A_", PitchClass.A_NATURAL);
        STANDARD_PITCH_CLASSES.put("A#", PitchClass.A_SHARP);
        STANDARD_PITCH_CLASSES.put("Ax", PitchClass.A_DOUBLE_SHARP);

        STANDARD_PITCH_CLASSES.put("Bbb", PitchClass.B_DOUBLE_FLAT);
        STANDARD_PITCH_CLASSES.put("Bb", PitchClass.B_FLAT);
        STANDARD_PITCH_CLASSES.put("B", PitchClass.B);
        STANDARD_PITCH_CLASSES.put("B_", PitchClass.B_NATURAL);
        STANDARD_PITCH_CLASSES.put("B#", PitchClass.B_SHARP);
        STANDARD_PITCH_CLASSES.put("Bx", PitchClass.B_DOUBLE_SHARP);
    }

    final EnharmonicSpelling enharmonicSpelling; // the simplified spelling of this pitch class
    final Letter aliasLetter;   // the 'true' letter for the way this pitch class is being spelled
    final List<Accidental> aliasAccidentals; // the 'true' accidentals for the way this pitch class is being spelled
    final int aliasBaseMidiValue; // lowest MIDI value for the given pitch class
    final boolean isAliasSimplified; // whether the accidentals for this pitch class are simplified
    final Octave octaveRange;

    private PitchClass(EnharmonicSpelling enharmonicSpelling, int baseMidiValue) {
        this.enharmonicSpelling = enharmonicSpelling;
        this.aliasLetter = enharmonicSpelling.letter;
        this.aliasAccidentals = Accidental.NONE.equals(enharmonicSpelling.accidental) ? Collections.emptyList() : Collections.singletonList(enharmonicSpelling.accidental);
        this.aliasBaseMidiValue = (12 + baseMidiValue) % 12;
        this.isAliasSimplified = true;

        /*
         * The playable MIDI range is 0 (C0) to 127 (G10).
         * Since each Octave spans 12 semitones, only ten can fit completely.
         * The eleventh (highest) Octave is cut off at G10 (highest possible note),
         * so anything higher than G10 must be prevented.
         */
        this.octaveRange = baseMidiValue < 8 ? Octave.OCTAVE_10 : Octave.OCTAVE_9;
    }

    private PitchClass(EnharmonicSpelling enharmonicSpelling, String alias) {
        this.enharmonicSpelling = enharmonicSpelling;
        String letter = alias.substring(0, 1).toUpperCase();
        String originalAccs = Accidental.convertToUTF8Symbols(alias.substring(1));
        this.aliasLetter = Letter.valueOf(letter);
        this.aliasAccidentals = originalAccs.isBlank() ? Collections.emptyList() : Accidental.convert(originalAccs);
        this.aliasBaseMidiValue = (12 + aliasLetter.baseMidiValue + Accidental.sumAccidentalsToSemitoneModifier(aliasAccidentals)) % 12;
        String simplifiedAccs = Accidental.simplify(originalAccs, originalAccs.contains(Accidental.NATURAL.utf8Symbol), true);
        this.isAliasSimplified = originalAccs.equals(simplifiedAccs);

        /*
         * The playable MIDI range is 0 (C0) to 127 (G10).
         * Since each Octave spans 12 semitones, only ten can fit completely.
         * The eleventh (highest) Octave is cut off at G10 (highest possible note),
         * so anything higher than G10 must be prevented.
         */
        this.octaveRange = aliasBaseMidiValue < 8 ? Octave.OCTAVE_10 : Octave.OCTAVE_9;
    }

    /**
     *
     * @param name
     * @param wantNaturalSymbol
     * @return
     */
    public static PitchClass withName(String name, boolean wantNaturalSymbol) {

        if (!EnharmonicSpelling.PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid pitch class name [" + name + "]");
        } else {

            String letter = String.valueOf(name.charAt(0));
            String simplifiedAccidentals = "";
            if (name.length() > 1) {
                simplifiedAccidentals = Accidental.simplify(name.substring(1), wantNaturalSymbol, true);
            } else if (wantNaturalSymbol) {
                simplifiedAccidentals = Accidental.NATURAL.utf8Symbol;
                name += Accidental.NATURAL.utf8Symbol;
            }

            // Try looking up a standard pitch class and return the static instance if possible
            if (name.substring(1).equals(simplifiedAccidentals) && STANDARD_PITCH_CLASSES.get(name) != null) {
                return STANDARD_PITCH_CLASSES.get(name);
            }

            // Create a new (non-standard) PitchClass
            try {
                return new PitchClass(determineEnharmonicSpelling(letter, simplifiedAccidentals), Accidental.convertToUTF8Symbols(name));
            } catch (Exception e) {
                throw new ChordInnateException("Could not create pitch class with name: " + name, e);
            }

        }
    }

    /**
     *
     * @param letter
     * @param simplifiedAccidentals
     * @return
     */
    private static EnharmonicSpelling determineEnharmonicSpelling(String letter, String simplifiedAccidentals) {
        return EnharmonicSpelling.valueOf(determineEnharmonicSpellingEnumName(letter, simplifiedAccidentals));
    }

    /**
     *
     * @param simplifiedAccidentals
     * @return
     */
    private static String determineEnharmonicSpellingEnumName(String startingLetter, String simplifiedAccidentals) {

        if (StringUtils.isBlank(simplifiedAccidentals)) {
            return startingLetter;
        } else if (simplifiedAccidentals.length() == 1) {
            // bb, b, natural, #, or x
            return startingLetter + "_" + Accidental.getBySymbol(simplifiedAccidentals).name();
        } else {
            // Figure out an enharmonically-equivalent BasePitchClass would be. This may require changing the letter.
            EnharmonicSpelling enharmonicSpelling = EnharmonicSpelling.valueOf(startingLetter);
            for (char c : simplifiedAccidentals.toCharArray()) {
                String token = String.valueOf(c);
                enharmonicSpelling = enharmonicSpelling.apply(Accidental.getBySymbol(token));
            }

            return enharmonicSpelling.name();
        }

    }

    /**
     * Finds the number of semitones between lhs and rhs.
     * @param lhs the starting PitchClass
     * @param rhs the ending PitchClass
     * @return the difference in semitones between lhs and rhs
     */
    public static int getSemitoneDistanceBetween(@NotNull PitchClass lhs, @NotNull PitchClass rhs) {
        int r = (rhs.enharmonicSpelling.baseMidiValue < 0 ? 12 + rhs.enharmonicSpelling.baseMidiValue : rhs.enharmonicSpelling.baseMidiValue) % 12;
        int l = (lhs.enharmonicSpelling.baseMidiValue < 0 ? 12 + lhs.enharmonicSpelling.baseMidiValue : lhs.enharmonicSpelling.baseMidiValue) % 12;
        int semitoneDistance = r - l;
        return semitoneDistance >= 0 ? semitoneDistance : 12 + semitoneDistance;
    }

    @Override
    public PitchClass transpose(@NotNull IntervalDirection direction, @NotNull Interval interval) {
        if (isTransposable(direction, interval)) {

            // 1. Get the simple interval (use the inversion if going downward)
            Interval simpleInterval = direction.getCompareTo() == 1
                    ? Interval.withShortName(interval.getSimpleShortName())
                    : Interval.withShortName(interval.getSimpleShortName()).invert();

            // 2. Determine the expected letter of the candidate Pitch.
            Letter beginLetter = aliasLetter;
            int idx = beginLetter.ordinal();
            int expectedLetterIndex = direction.getCompareTo() == 1
                    ? (idx + (interval.getSimpleDiatonic() - 1)) % 7
                    : (idx + (7 - (interval.getSimpleDiatonic() - 1))) % 7;
            Letter expectedLetter = Letter.values()[expectedLetterIndex];

            // 3. Get the semitones between the two letters.
            int semitonesBetweenLetters = Math.abs(beginLetter.baseMidiValue - (12 + expectedLetter.baseMidiValue)) % 12;

            // 4. Setup the candidate PitchClass. Add accidentals as necessary.
            String candidateName = determineTransposedName(semitonesBetweenLetters, simpleInterval, expectedLetter);

            return PitchClass.withName(candidateName, false);
        } else {
            throw new ChordInnateException(
                    "Cannot transpose pitch class "
                            + getName()
                            + (direction.name().toLowerCase() + " ")
                            + "by interval "
                            + interval.getCompoundShortName()
            );
        }
    }

    private String determineTransposedName(int semitonesBetweenLetters, Interval simpleInterval, Letter expectedLetter) {
        StringBuilder sb = new StringBuilder();
        while (semitonesBetweenLetters != simpleInterval.getSemitones()) {
            if (semitonesBetweenLetters < simpleInterval.getSemitones()) {
                sb.append(Accidental.SHARP.utf8Symbol);
                semitonesBetweenLetters++;
            } else {
                sb.append(Accidental.FLAT.utf8Symbol);
                semitonesBetweenLetters--;
            }
        }

        String partition1 = accsToSymbols(aliasAccidentals);
        String partition2 = Accidental.simplify(sb.toString(), false, true);
        return expectedLetter.name()
                + (isAliasSimplified
                ? mergeAccidentals(partition1, partition2)
                : (partition1 + sb.toString()));
    }

    private static String accsToSymbols(List<Accidental> accidentals) {
        StringBuilder accsToSymbol = new StringBuilder();
        accidentals.forEach(acc -> accsToSymbol.append(acc.utf8Symbol));
        return accsToSymbol.toString();
    }

    /**
     * Merges two sets of accidentals, starting from acc1 and ending with acc2.
     * @param lhs
     * @param rhs
     * @return the union string of both accidentals (intersection is simplified)
     */
    private static String mergeAccidentals(String lhs, String rhs) {
        String lastOfLHS = extractAccidental(lhs, false);
        String firstOfRHS = extractAccidental(rhs, true);
        String a1 = lhs.substring(0, lhs.length() - lastOfLHS.length());
        String a2 = rhs.substring(firstOfRHS.length());

        String simplified = Accidental.simplify(lastOfLHS + firstOfRHS, lhs.contains(Accidental.NATURAL.utf8Symbol), true);

        // TODO: reorder x# to #x ?

        return a1 + simplified + a2;
    }

    /**
     * Helper method to get either the first or last accidental from a string of accidentals.
     * @param allAccidentals {@link String} containing all accidentals
     * @param firstOrLast whether to extract the first ({@code true}) or last ({@code false}) accidental in the string
     * @return the first or last accidental from the string
     */
    private static String extractAccidental(String allAccidentals, boolean firstOrLast) {
        if (StringUtils.isBlank(allAccidentals)) {
            return "";
        }
        if (allAccidentals.length() < 2) {
            return allAccidentals;
        } else {
            String temp = firstOrLast
                    ? allAccidentals.substring(0, 2)
                    : allAccidentals.substring(allAccidentals.length() - 2);

            if (Accidental.DOUBLE_FLAT.equals(Accidental.getBySymbol(temp))) {
                return temp;
            }

            return firstOrLast ? String.valueOf(temp.charAt(0)) : temp.substring(1);
        }
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        if (keySignature.equals(KeySignature.NO_KEY_SIGNATURE) || keySignature.contains(this)) {
            return true;
        }

        boolean accidentalIsFlatOrSharp = !this.aliasAccidentals.isEmpty();

        /*
         * Key signatures inherit their 'bias' toward flatness / sharpness
         * from the first accidental they contain. This means the key signature
         * will contain either ALL flats or ALL sharps.
         *
         * If there's nothing in the key signature,
         * but the enharmonic spelling contains an accidental,
         * it can't be diatonic to this key signature.
         */
        if (CollectionUtils.isEmpty(keySignature.getKey().aliasAccidentals) && CollectionUtils.isEmpty(keySignature.getSignature()) && accidentalIsFlatOrSharp) {
            return false;
        }

        if (keySignature.getSignature().stream().anyMatch(a -> a.getName().equals(this.getName()))) {
            return true;
        }

        /*
         * A property of key signatures is that
         * all enharmonic spellings must have a unique letter.
         *
         * We've already checked the key signature for a match on PitchClass,
         * and failed, so we know that the specific enharmonic spelling isn't there.
         *
         * It is possible at this point for PitchClass's letter
         * to match a letter in the key signature.
         * By considering such a PitchClass as diatonic, we'd be
         * violating the key signature property of unique letters,
         * therefore any such PitchClass matching a letter from the key signature
         * must be considered NOT diatonic.
         */
        for (PitchClass p : keySignature.getSignature()) {
            if (aliasLetter.equals(p.aliasLetter)) {
                return false;
            }
        }

        /*
         * By the time we get here, the only valid PitchClasses
         * would be those that contain no accidental.
         *
         * We've already searched for all the ones matching the key signature bias,
         * and we've already filtered out any that were not adhering to the bias.
         */
        return !accidentalIsFlatOrSharp;
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return intervalSet.getDiatonics().contains(this.getName());
    }

    @Override
    public boolean isEnharmonicTo(@NotNull PitchClass other) {
        int r = (other.aliasBaseMidiValue < 0 ? 12 + other.aliasBaseMidiValue : other.aliasBaseMidiValue) % 12;
        int l = (this.aliasBaseMidiValue < 0 ? 12 + this.aliasBaseMidiValue : this.aliasBaseMidiValue) % 12;
        return r == l;
    }

    public String getName() {
        return this.aliasLetter.name() + accsToSymbols(this.aliasAccidentals);
    }

    public String getSimplifiedName() {
        return this.enharmonicSpelling.name;
    }

    public Octave getOctaveRange() {
        return octaveRange;
    }

    public int getMidiValue() {
        return aliasBaseMidiValue;
    }

    public List<Accidental> getAccidentals() {
        return aliasAccidentals;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        PitchClass comparison = (PitchClass) other;

        return enharmonicSpelling.equals(comparison.enharmonicSpelling)
                && aliasLetter.equals(comparison.aliasLetter)
                && aliasAccidentals.equals(comparison.aliasAccidentals)
                && aliasBaseMidiValue == comparison.aliasBaseMidiValue;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
