package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public enum Note {
    // DO NOT RE-ORDER THESE ITEMS!!

    B_SHARP('B', Accidental.SHARP, 0, 11),
    C('C', Accidental.NONE, 0, 11),
    C_NATURAL('C', Accidental.NATURAL, 0, 11),
    D_DOUBLE_FLAT('D', Accidental.DOUBLE_FLAT, 0, 11),

    B_DOUBLE_SHARP('B', Accidental.DOUBLE_SHARP, 1, 11),
    C_SHARP('C', Accidental.SHARP, 1, 11),
    D_FLAT('D', Accidental.FLAT, 1, 11),

    C_DOUBLE_SHARP('C', Accidental.DOUBLE_SHARP, 2, 11),
    D('D', Accidental.NONE, 2, 11),
    D_NATURAL('D', Accidental.NATURAL, 2, 11),
    E_DOUBLE_FLAT('E', Accidental.DOUBLE_FLAT, 2, 11),

    D_SHARP('D', Accidental.SHARP, 3, 11),
    E_FLAT('E', Accidental.FLAT, 3, 11),
    F_DOUBLE_FLAT('F', Accidental.DOUBLE_FLAT, 3, 11),

    D_DOUBLE_SHARP('D', Accidental.DOUBLE_SHARP, 4, 11),
    E('E', Accidental.NONE, 4, 11),
    E_NATURAL('E', Accidental.NATURAL, 4, 11),
    F_FLAT('F', Accidental.FLAT, 4, 11),

    E_SHARP('E', Accidental.SHARP, 5, 11),
    F('F', Accidental.NONE, 5, 11),
    F_NATURAL('F', Accidental.NATURAL, 5, 11),
    G_DOUBLE_FLAT('G', Accidental.DOUBLE_FLAT, 5, 11),

    E_DOUBLE_SHARP('E', Accidental.DOUBLE_SHARP, 6, 11),
    F_SHARP('F', Accidental.SHARP, 6, 11),
    G_FLAT('G', Accidental.FLAT, 6, 11),

    F_DOUBLE_SHARP('F', Accidental.DOUBLE_SHARP, 7, 11),
    G('G', Accidental.NONE, 7, 11),
    G_NATURAL('G', Accidental.NATURAL, 7, 11),
    A_DOUBLE_FLAT('A', Accidental.DOUBLE_FLAT, 7, 11),

    G_SHARP('G', Accidental.SHARP, 8, 12),
    A_FLAT('A', Accidental.FLAT, 8, 12),

    G_DOUBLE_SHARP('G', Accidental.DOUBLE_SHARP, 9, 12),
    A('A', Accidental.NONE, 9, 12),
    A_NATURAL('A', Accidental.NATURAL, 9, 12),
    B_DOUBLE_FLAT('B', Accidental.DOUBLE_FLAT, 9, 12),

    A_SHARP('A', Accidental.SHARP, 10, 12),
    B_FLAT('B', Accidental.FLAT, 10, 12),
    C_DOUBLE_FLAT('C', Accidental.DOUBLE_FLAT, 10, 12),

    A_DOUBLE_SHARP('A', Accidental.DOUBLE_SHARP, 11, 12),
    B('B', Accidental.NONE, 11, 12),
    B_NATURAL('B', Accidental.NATURAL, 11, 12),
    C_FLAT('C', Accidental.FLAT, 11, 12);

    private char letter;
    private Accidental accidental;
    private String name;
    private int relativePitch, octaveRange;

    Note(char letter, Accidental accidental, int relativePitch, int octaveRange) {
        this.letter = letter;
        this.accidental = accidental;
        this.name = letter + accidental.indicator;
        this.relativePitch = relativePitch;
        this.octaveRange = octaveRange;
    }

    String getName() {
        return name;
    }

    int getRelativePitch() {
        return relativePitch;
    }

    int getOctaveRange() {
        return octaveRange;
    }

    char getLetter() { return letter; }

    Accidental getAccidental() {
        return accidental;
    }

    boolean hasAccidentalSymbol() {
        return !accidental.equals(Accidental.NONE);
    }

    boolean isNatural() { return accidental.equals(Accidental.NATURAL); }

    boolean isDoubleAccidental() { return accidental.equals(Accidental.DOUBLE_FLAT)
            || accidental.equals(Accidental.DOUBLE_SHARP); }

    Note getNext() {
        return this.ordinal() < Note.values().length - 1
                ? Note.values()[this.ordinal() + 1]
                : null;
    }

    Note getPrevious() {
        return this.ordinal() > 0
                ? Note.values()[this.ordinal() - 1]
                : null;
    }

    Note[] getPracticalEnharmonicEquivalents(boolean wantNatural) {
        switch (this.relativePitch) {
            case 0: { // B# | C | CNat | Dbb
                if (wantNatural) {
                    return new Note[] {B_SHARP, C, C_NATURAL};
                }
                else {
                    return new Note[] {B_SHARP, C};
                }
            }
            case 1: { // Bx | C# | Db
                return new Note[] {C_SHARP, D_FLAT};
            }
            case 2: { // Cx | D | DNat | Ebb
                if (wantNatural) {
                    return new Note[] {D, D_NATURAL};
                }
                else {
                    return new Note[] {D};
                }
            }
            case 3: { // D# | Eb | Fbb
                return new Note[] {D_SHARP, E_FLAT};
            }
            case 4: { // Dx | E | ENat | Fb
                if (wantNatural) {
                    return new Note[] {E, E_NATURAL};
                }

                return new Note[] {E};
            }
            case 5: { // E# | F | FNat | Gbb
                if (wantNatural) {
                    return new Note[] {E_SHARP, F, F_NATURAL};
                }
                else {
                    return new Note[] {E_SHARP, F};
                }
            }
            case 6: { // Ex | F# | Gb
                return new Note[] {F_SHARP, G_FLAT};
            }
            case 7: { // Fx | G | GNat | Abb
                if (wantNatural) {
                    return new Note[] {G, G_NATURAL};
                }
                else {
                    return new Note[] {G};
                }
            }
            case 8: { // G# | Ab
                return new Note[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                if (wantNatural) {
                    return new Note[] {A, A_NATURAL};
                }
                else {
                    return new Note[] {A};
                }
            }
            case 10: { // A# | Bb | Cbb
                return new Note[] {A_SHARP, B_FLAT};
            }
            case 11: { // Ax | B | BNat | Cb
                if (wantNatural) {
                    return new Note[] {B, B_NATURAL, C_FLAT};
                }
                else {
                    return new Note[] {B, C_FLAT};
                }
            }
            default: return new Note[] {};
        }
    }

    Note[] getEnharmonicEquivalents(boolean wantNatural, boolean wantDoubleAccidentals) {
        switch (this.relativePitch) {
            case 0: { // B# | C | CNat | Dbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, C_NATURAL, D_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, D_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, C_NATURAL};
                }
                else {
                    return new Note[] {B_SHARP, C};
                }
            }
            case 1: { // Bx | C# | Db
                if (wantDoubleAccidentals) {
                    return new Note[] {B_DOUBLE_SHARP, C_SHARP, D_FLAT};
                }
                else {
                    return new Note[] {C_SHARP, D_FLAT};
                }
            }
            case 2: { // Cx | D | DNat | Ebb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {C_DOUBLE_SHARP, D, D_NATURAL, E_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {C_DOUBLE_SHARP, D, E_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {D, D_NATURAL};
                }
                else {
                    return new Note[] {D};
                }
            }
            case 3: { // D# | Eb | Fbb
                if (wantDoubleAccidentals) {
                    return new Note[] {D_SHARP, E_FLAT, F_DOUBLE_FLAT};
                }
                else {
                    return new Note[] {D_SHARP, E_FLAT};
                }
            }
            case 4: { // Dx | E | ENat | Fb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {D_DOUBLE_SHARP, E, E_NATURAL, F_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {D_DOUBLE_SHARP, E, F_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {E, E_NATURAL, F_FLAT};
                }
                else {
                    return new Note[] {E, F_FLAT};
                }
            }
            case 5: { // E# | F | FNat | Gbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {E_SHARP, F, F_NATURAL, G_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new  Note[] {E_SHARP, F, G_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new  Note[] {E_SHARP, F, F_NATURAL};
                }
                else {
                    return new Note[] {E_SHARP, F};
                }
            }
            case 6: { // Ex | F# | Gb
                if (wantDoubleAccidentals) {
                    return new Note[] {E_DOUBLE_SHARP, F_SHARP, G_FLAT};
                }
                else {
                    return new Note[] {F_SHARP, G_FLAT};
                }
            }
            case 7: { // Fx | G | GNat | Abb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {F_DOUBLE_SHARP, G, G_NATURAL, A_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {F_DOUBLE_SHARP, G, A_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {G, G_NATURAL};
                }
                else {
                    return new Note[] {G};
                }
            }
            case 8: { // G# | Ab
                return new Note[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {G_DOUBLE_SHARP, A, A_NATURAL, B_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {G_DOUBLE_SHARP, A, B_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {A, A_NATURAL};
                }
                else {
                    return new Note[] {A};
                }
            }
            case 10: { // A# | Bb | Cbb
                if (wantDoubleAccidentals) {
                    return new Note[] {A_SHARP, B_FLAT, C_DOUBLE_FLAT};
                }
                else {
                    return new Note[] {A_SHARP, B_FLAT};
                }
            }
            case 11: { // Ax | B | BNat | Cb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {A_DOUBLE_SHARP, B, B_NATURAL, C_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {A_DOUBLE_SHARP, B, C_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {B, B_NATURAL, C_FLAT};
                }
                else {
                    return new Note[] {B, C_FLAT};
                }
            }
            default: return new Note[] {};
        }





//        Vector<Note> tmp = new Vector<>();
//        for (Note note: Note.values()) {
//            if (note.getRelativePitch() == this.relativePitch
//                    && !note.equals(this)) {
//                if (!wantNatural && note.isNatural()) {
//                    continue;
//                }
//                if (!wantDoubleAccidentals && note.isDoubleAccidental()) {
//                    continue;
//                }
//
//                tmp.add(note);
//            }
//        }
//
//        // Find the enharmonics and return the set
//        Note[] enharmonics = new Note[tmp.size()];
//        for (int i = 0; i < enharmonics.length; i++) {
//            enharmonics[i] = tmp.get(i);
//        }
//
//        return enharmonics;
    }

    boolean isEnharmonicallyEquivalentTo(Note comparisonNote) {
        return this.relativePitch == comparisonNote.getRelativePitch();
    }
}

enum Accidental {
    DOUBLE_FLAT("\u266d\u266d"),
    FLAT("\u266d"),
    NATURAL("\u266e"),       // TODO Not sure what to use as an indicator for natural...
    SHARP("\u266f"),
    DOUBLE_SHARP("x"),
    NONE("");

    String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }
}
