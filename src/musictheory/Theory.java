package musictheory;

import static musictheory.NoteType.*;
import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/7/16.
 */
class Theory {

    /**
     * Gets all NoteTypes that are enharmonically equivalent to the specified NoteType.
     * @param noteType the NoteType to receive enharmonic equivalents for
     * @param wantNatural whether to include natural accidentals in the returned list
     * @param wantDoubleAccidentals whether to include double accidentals in the returned list
     * @return a list of enharmonic equivalents for the given NoteType
     */
    static final NoteType[] getEnharmonicEquivalents(NoteType noteType, boolean wantNatural, boolean wantDoubleAccidentals) {
        /*
         * The switch statement operates at O(n) in worst-case (e.g., reaching the default case),
         * but since I've already done some preprocessing to get the enharmonics for each case,
         * returning the result is Θ(1).
         *
         * While it's possible to have implemented the switch-case as a HashMap to allow
         * constant access time for all results, I would argue that it is (somewhat) easier to
         * read and debug this logic when structured as a switch-case.
         */
        switch (noteType.relativePitch) {
            case 0: { // B# | C | CNat | Dbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, C_NATURAL, D_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, D_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, C_NATURAL};
                }
                else {
                    return new NoteType[] {B_SHARP, C};
                }
            }
            case 1: { // Bx | C# | Db
                if (wantDoubleAccidentals) {
                    return new NoteType[] {B_DOUBLE_SHARP, C_SHARP, D_FLAT};
                }
                else {
                    return new NoteType[] {C_SHARP, D_FLAT};
                }
            }
            case 2: { // Cx | D | DNat | Ebb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {C_DOUBLE_SHARP, D, D_NATURAL, E_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {C_DOUBLE_SHARP, D, E_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {D, D_NATURAL};
                }
                else {
                    return new NoteType[] {D};
                }
            }
            case 3: { // D# | Eb | Fbb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {D_SHARP, E_FLAT, F_DOUBLE_FLAT};
                }
                else {
                    return new NoteType[] {D_SHARP, E_FLAT};
                }
            }
            case 4: { // Dx | E | ENat | Fb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {D_DOUBLE_SHARP, E, E_NATURAL, F_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {D_DOUBLE_SHARP, E, F_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {E, E_NATURAL, F_FLAT};
                }
                else {
                    return new NoteType[] {E, F_FLAT};
                }
            }
            case 5: { // E# | F | FNat | Gbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {E_SHARP, F, F_NATURAL, G_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new  NoteType[] {E_SHARP, F, G_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new  NoteType[] {E_SHARP, F, F_NATURAL};
                }
                else {
                    return new NoteType[] {E_SHARP, F};
                }
            }
            case 6: { // Ex | F# | Gb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {E_DOUBLE_SHARP, F_SHARP, G_FLAT};
                }
                else {
                    return new NoteType[] {F_SHARP, G_FLAT};
                }
            }
            case 7: { // Fx | G | GNat | Abb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {F_DOUBLE_SHARP, G, G_NATURAL, A_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {F_DOUBLE_SHARP, G, A_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {G, G_NATURAL};
                }
                else {
                    return new NoteType[] {G};
                }
            }
            case 8: { // G# | Ab
                return new NoteType[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {G_DOUBLE_SHARP, A, A_NATURAL, B_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {G_DOUBLE_SHARP, A, B_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {A, A_NATURAL};
                }
                else {
                    return new NoteType[] {A};
                }
            }
            case 10: { // A# | Bb | Cbb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {A_SHARP, B_FLAT, C_DOUBLE_FLAT};
                }
                else {
                    return new NoteType[] {A_SHARP, B_FLAT};
                }
            }
            case 11: { // Ax | B | BNat | Cb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {A_DOUBLE_SHARP, B, B_NATURAL, C_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {A_DOUBLE_SHARP, B, C_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {B, B_NATURAL, C_FLAT};
                }
                else {
                    return new NoteType[] {B, C_FLAT};
                }
            }
            default: { // Should not be reached: all NoteTypes have at least one enharmonic equivalent
                return new NoteType[] {};
            }
        }
    }

    /**
     *
     * @param root the NoteType of the 'base' Note to count up from
     * @param nashvilleNumber the desired interval above root
     * @return the letter for the Note at nashvilleNumber, with respect to root (irrespective of root's accidental)
     */
    static char getNoteLetterForNashvilleNumber(NoteType root, NashvilleNumber nashvilleNumber) {

        // Convert the ASCII value to get the correct char
        int offset = nashvilleNumber.isNextOctave
                ? nashvilleNumber.intervalNumber - 7
                : nashvilleNumber.intervalNumber;

        int comparison = (int) root.letter + offset - 1;
        if (comparison > 71) comparison = 65 + (comparison - 72);

        if ((char) comparison > 'G') comparison = (int) 'A' + comparison - (int) 'G';

        return (char) comparison;
    }

    /**
     *
     * @param currentNote the NoteType of the Note in question
     * @return the letter for the Note preceding currentNote
     */
    static char getPreviousNoteLetter(NoteType currentNote) {
        char currentChar = currentNote.letter;

        if (currentChar == 'A') return 'G';

        return (char) ((int) currentChar - 1);
    }

    /**
     *
     * @param currentNote the NoteType of the Note in question
     * @return the letter for the Note succeeding currentNote
     */
    static char getNextNoteLetter(NoteType currentNote) {
        char currentChar = currentNote.letter;

        if (currentChar == 'G') return 'A';

        return (char) ((int) currentChar + 1);
    }

    /**
     *
     * @param n1 the NoteType of the first Note
     * @param n2 the NoteType of the Note directly after n1
     * @return whether the letter for n2 comes after the letter for n1
     */
    static boolean noteLettersFollow(NoteType n1, NoteType n2) {
        return getNextNoteLetter(n1) == n2.letter;
    }

    /**
     * Gives back the NoteType that would be the enharmonic result from applying the specified Accidental
     * to the specified NoteType. This function does not perform any side effects to the
     * original NoteType.
     * @param original the NoteType in question
     * @param accidentalToApply the Accidental to apply to original
     * @return the NoteType that enharmonically matches the result from applying the specified accidental to it
     */
    static final NoteType applyAccidentalTo(NoteType original, Accidental accidentalToApply) {
        Accidental a = original.accidental, b = accidentalToApply;

        // ACCIDENTAL && (NATURAL || NONE) == ACCIDENTAL
        if (accidentalToApply.equals(NATURAL) || accidentalToApply.equals(NONE)) {
            return original; // return the same item back with no changes
        }

        // (NATURAL || NONE) && ACCIDENTAL == ACCIDENTAL
        if (original.isNatural() || original.accidental.equals(NONE)) {
            return getNoteType(original.letter, b); // whatever Accidental b is
        }

        // FLAT && SHARP == NATURAL
        else if ((a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_SHARP))  ||
                (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_FLAT))   ||
                (a.equals(FLAT) && b.equals(SHARP))                 ||
                (a.equals(SHARP) && b.equals(FLAT)))                    {
            return getNoteType(original.letter, NATURAL); // flats & sharps cancel out
        }

        // FLAT && FLAT = DOUBLE FLAT;
        // SHARP && SHARP =  DOUBLE SHARP
        else if ((a.equals(FLAT) && b.equals(FLAT)) || (a.equals(SHARP) && b.equals(SHARP))) {
            Accidental doubleAccidental = Enum.valueOf(Accidental.class, "DOUBLE_" + a.name());

            // flat + flat = double flat; sharp + sharp = double sharp
            return getNoteType(original.letter, doubleAccidental);
        }

        // TRIPLE FLAT
        else if ((a.equals(DOUBLE_FLAT) && b.equals(FLAT)) || (a.equals(FLAT) && b.equals(DOUBLE_FLAT))) {
            char letter = getNoteLetter(original, 6);

            // All results from triple flatting have a single flat, except for Bbb and Ebb
            if (letter == 'B' || letter == 'E') return getNoteType(letter, DOUBLE_FLAT);
            else return getNoteType(letter, FLAT);
        }

        // TRIPLE SHARP
        else if ((a.equals(DOUBLE_SHARP) && b.equals(SHARP)) || (a.equals(SHARP) && b.equals(DOUBLE_SHARP))) {
            char letter = getNoteLetter(original, 1);

            // All results from triple sharping have a single sharp, except for Cx and Fx
            if (letter == 'C' || letter == 'F') return getNoteType(letter, DOUBLE_SHARP);
            else return getNoteType(letter, SHARP);
        }

        // QUADRUPLE FLAT
        else if (a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_FLAT)) {
            if (!original.equals(C_DOUBLE_FLAT) && !original.equals(F_DOUBLE_FLAT)) {
                return getNoteType(getPreviousNoteLetter(original), DOUBLE_FLAT);
            }
            else {
                return getNoteType(getNoteLetter(original, 5), FLAT);
            }
        }

        // QUADRUPLE SHARP
        else if (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_SHARP)) {
            if (!original.equals(B_DOUBLE_SHARP) && !original.equals(E_DOUBLE_SHARP)) {
                return getNoteType(getNextNoteLetter(original), DOUBLE_SHARP);
            }
            else {
                return getNoteType(getNoteLetter(original, 2), SHARP);
            }
        }

        // DOUBLE SHARP && FLAT; DOUBLE FLAT && SHARP
        else {
            if (a.equals(SHARP) && b.equals(DOUBLE_FLAT)) {
                return getNoteType(original.letter, FLAT);
            }
            else if (a.equals(FLAT) && b.equals(DOUBLE_SHARP)) {
                return getNoteType(original.letter, SHARP);
            }
            else if (a.equals(DOUBLE_SHARP) && b.equals(FLAT)) {
                return getNoteType(original.letter, SHARP);
            }
            else if (a.equals(DOUBLE_FLAT) && b.equals(SHARP)) {
                return getNoteType(original.letter, FLAT);
            }
            else {
                return null; // error
            }
        }
    }

    /**
     *
     * @param note
     * @param letterShiftValue
     * @return
     */
    private static char getNoteLetter(NoteType note, int letterShiftValue) {
        int comparison = (int) note.letter + letterShiftValue;
        if (comparison > 71) comparison = 65 + (comparison - 72);
        return (char) comparison;
    }

    /**
     *
     * @param scale
     * @param note
     * @return
     */
    static final Scale transpose(Scale scale, NoteType note) throws Exception {
        return new Scale(note, scale.getScaleType());
    }

    /**
     * 
     * @param chord
     * @param note
     * @return
     */
    static final Chord transpose(Chord chord, NoteType note) throws Exception {
        return new Chord(note, chord.getChordType());
    }
}


