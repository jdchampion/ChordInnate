package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/1/16.
 */
public enum Note {
    // DO NOT RE-ORDER THESE ITEMS!!

    B_SHARP('B', SHARP, 0),
    C('C', NONE, 0),
    C_NATURAL('C', NATURAL, 0),
    D_DOUBLE_FLAT('D', DOUBLE_FLAT, 0),

    B_DOUBLE_SHARP('B', DOUBLE_SHARP, 1),
    C_SHARP('C', SHARP, 1),
    D_FLAT('D', FLAT, 1),

    C_DOUBLE_SHARP('C', DOUBLE_SHARP, 2),
    D('D', NONE, 2),
    D_NATURAL('D', NATURAL, 2),
    E_DOUBLE_FLAT('E', DOUBLE_FLAT, 2),

    D_SHARP('D', SHARP, 3),
    E_FLAT('E', FLAT, 3),
    F_DOUBLE_FLAT('F', DOUBLE_FLAT, 3),

    D_DOUBLE_SHARP('D', DOUBLE_SHARP, 4),
    E('E', NONE, 4),
    E_NATURAL('E', NATURAL, 4),
    F_FLAT('F', FLAT, 4),

    E_SHARP('E', SHARP, 5),
    F('F', NONE, 5),
    F_NATURAL('F', NATURAL, 5),
    G_DOUBLE_FLAT('G', DOUBLE_FLAT, 5),

    E_DOUBLE_SHARP('E', DOUBLE_SHARP, 6),
    F_SHARP('F', SHARP, 6),
    G_FLAT('G', FLAT, 6),

    F_DOUBLE_SHARP('F', DOUBLE_SHARP, 7),
    G('G', NONE, 7),
    G_NATURAL('G', NATURAL, 7),
    A_DOUBLE_FLAT('A', DOUBLE_FLAT, 7),

    G_SHARP('G', SHARP, 8),
    A_FLAT('A', FLAT, 8),

    G_DOUBLE_SHARP('G', DOUBLE_SHARP, 9),
    A('A', NONE, 9),
    A_NATURAL('A', NATURAL, 9),
    B_DOUBLE_FLAT('B', DOUBLE_FLAT, 9),

    A_SHARP('A', SHARP, 10),
    B_FLAT('B', FLAT, 10),
    C_DOUBLE_FLAT('C', DOUBLE_FLAT, 10),

    A_DOUBLE_SHARP('A', DOUBLE_SHARP, 11),
    B('B', NONE, 11),
    B_NATURAL('B', NATURAL, 11),
    C_FLAT('C', FLAT, 11);

    private final char letter;
    private final Accidental accidental;
    private final String name;
    private final int relativePitch, octaveRange;

    Note(char letter, Accidental accidental, int relativePitch) {
        this.letter = letter;
        this.accidental = accidental;
        this.name = letter + accidental.getIndicator();
        this.relativePitch = relativePitch;
        this.octaveRange = (relativePitch < 8) ? 11 : 10;
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
        return !accidental.equals(NONE);
    }

    boolean isNatural() { return accidental.equals(NATURAL); }

    boolean isDoubleAccidental() { return accidental.equals(DOUBLE_FLAT)
            || accidental.equals(DOUBLE_SHARP); }

    final Note getNext() {
        return this.ordinal() < Note.values().length - 1
                ? Note.values()[this.ordinal() + 1]
                : null;
    }

    final Note getPrevious() {
        return this.ordinal() > 0
                ? Note.values()[this.ordinal() - 1]
                : null;
    }

    final Note getFromIndex(int index) {
        return (index > 0 || index < Note.values().length - 1)
                ? Note.values()[index]
                : null;
    }

    final static Note getFirstPracticalEnharmonicToRelativePitch(int relativePitch) {
        switch (relativePitch) {
            case 0: return B_SHARP;
            case 1: return C_SHARP;
            case 2: return D;
            case 3: return D_SHARP;
            case 4: return E;
            case 5: return F;
            case 6: return F_SHARP;
            case 7: return G;
            case 8: return G_SHARP;
            case 9: return A;
            case 10: return A_SHARP;
            case 11: return B;
            default: return null;
        }
    }

    final Note[] getPracticalEnharmonicEquivalents(boolean wantNatural) {
        switch (this.relativePitch) {
            case 0: { // B# | C | CNat | Dbb
                return wantNatural
                        ? new Note[] {B_SHARP, C, C_NATURAL}
                        : new Note[] {B_SHARP, C};
            }
            case 1: { // Bx | C# | Db
                return new Note[] {C_SHARP, D_FLAT};
            }
            case 2: { // Cx | D | DNat | Ebb
                return wantNatural
                        ? new Note[] {D, D_NATURAL}
                        : new Note[] {D};
            }
            case 3: { // D# | Eb | Fbb
                return new Note[] {D_SHARP, E_FLAT};
            }
            case 4: { // Dx | E | ENat | Fb
                return wantNatural
                        ? new Note[] {E, E_NATURAL}
                        : new Note[] {E};
            }
            case 5: { // E# | F | FNat | Gbb
                return wantNatural
                        ? new Note[] {E_SHARP, F, F_NATURAL}
                        : new Note[] {E_SHARP, F};
            }
            case 6: { // Ex | F# | Gb
                return new Note[] {F_SHARP, G_FLAT};
            }
            case 7: { // Fx | G | GNat | Abb
                return wantNatural
                        ? new Note[] {G, G_NATURAL}
                        : new Note[] {G};
            }
            case 8: { // G# | Ab
                return new Note[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                return wantNatural
                        ? new Note[] {A, A_NATURAL}
                        : new Note[] {A};
            }
            case 10: { // A# | Bb | Cbb
                return new Note[] {A_SHARP, B_FLAT};
            }
            case 11: { // Ax | B | BNat | Cb
                return wantNatural
                        ? new Note[] {B, B_NATURAL, C_FLAT}
                        : new Note[] {B, C_FLAT};
            }
            default: return new Note[] {};
        }
    }

    final Note[] getEnharmonicEquivalents(boolean wantNatural, boolean wantDoubleAccidentals) {
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

    static Note[] getAllSharps(boolean wantDoubleSharps) {
        return wantDoubleSharps
                ? new Note[] {A_DOUBLE_SHARP, B_DOUBLE_SHARP, C_DOUBLE_SHARP,
                        D_DOUBLE_SHARP, E_DOUBLE_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP,
                        A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP}

                : new Note[] {A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP};
    }

    static Note[] getAllFlats(boolean wantDoubleFlats) {
        return wantDoubleFlats
                ? new Note[] {A_DOUBLE_FLAT, B_DOUBLE_FLAT, C_DOUBLE_FLAT,
                        D_DOUBLE_FLAT, E_DOUBLE_FLAT, F_DOUBLE_FLAT, G_DOUBLE_FLAT,
                        A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT}
                : new Note[] {A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT};
     }

    static Note[] getFlatChromaticNoteArray() {
        return new Note[] {C, D_FLAT, D, E_FLAT, E, F, G_FLAT, G, A_FLAT, A, B_FLAT, C_FLAT};
    }

    static Note[] getSharpChromaticNoteArray() {
        return new Note[] {C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, B};
    }

    static Note getNote(char c, Accidental accidental) {
        switch (accidental) {
            case DOUBLE_FLAT:
            case FLAT:
            case SHARP:
            case DOUBLE_SHARP:
            case NATURAL: return Enum.valueOf(Note.class, c + "_" + accidental);
            case NONE: return Enum.valueOf(Note.class, c + "");
            default: return null;
        }
    }
}

enum Accidental {
    DOUBLE_FLAT("\u266d\u266d"),
    FLAT("\u266d"),
    NATURAL("\u266e"),
    SHARP("\u266f"),
    DOUBLE_SHARP("x"),
    NONE("");

    private final String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }

    final String getIndicator() { return indicator; }
}
