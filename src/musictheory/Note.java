package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public enum Note {
    // DO NOT RE-ORDER THESE ITEMS!!

    B_SHARP('B', Accidental.SHARP, 0),
    C('C', Accidental.NONE, 0),
    C_NATURAL('C', Accidental.NATURAL, 0),
    D_DOUBLE_FLAT('D', Accidental.DOUBLE_FLAT, 0),

    B_DOUBLE_SHARP('B', Accidental.DOUBLE_SHARP, 1),
    C_SHARP('C', Accidental.SHARP, 1),
    D_FLAT('D', Accidental.FLAT, 1),

    C_DOUBLE_SHARP('C', Accidental.DOUBLE_SHARP, 2),
    D('D', Accidental.NONE, 2),
    D_NATURAL('D', Accidental.NATURAL, 2),
    E_DOUBLE_FLAT('E', Accidental.DOUBLE_FLAT, 2),

    D_SHARP('D', Accidental.SHARP, 3),
    E_FLAT('E', Accidental.FLAT, 3),
    F_DOUBLE_FLAT('F', Accidental.DOUBLE_FLAT, 3),

    D_DOUBLE_SHARP('D', Accidental.DOUBLE_SHARP, 4),
    E('E', Accidental.NONE, 4),
    E_NATURAL('E', Accidental.NATURAL, 4),
    F_FLAT('F', Accidental.FLAT, 4),

    E_SHARP('E', Accidental.SHARP, 5),
    F('F', Accidental.NONE, 5),
    F_NATURAL('F', Accidental.NATURAL, 5),
    G_DOUBLE_FLAT('G', Accidental.DOUBLE_FLAT, 5),

    E_DOUBLE_SHARP('E', Accidental.DOUBLE_SHARP, 6),
    F_SHARP('F', Accidental.SHARP, 6),
    G_FLAT('G', Accidental.FLAT, 6),

    F_DOUBLE_SHARP('F', Accidental.DOUBLE_SHARP, 7),
    G('G', Accidental.NONE, 7),
    G_NATURAL('G', Accidental.NATURAL, 7),
    A_DOUBLE_FLAT('A', Accidental.DOUBLE_FLAT, 7),

    G_SHARP('G', Accidental.SHARP, 8),
    A_FLAT('A', Accidental.FLAT, 8),

    G_DOUBLE_SHARP('G', Accidental.DOUBLE_SHARP, 9),
    A('A', Accidental.NONE, 9),
    A_NATURAL('A', Accidental.NATURAL, 9),
    B_DOUBLE_FLAT('B', Accidental.DOUBLE_FLAT, 9),

    A_SHARP('A', Accidental.SHARP, 10),
    B_FLAT('B', Accidental.FLAT, 10),
    C_DOUBLE_FLAT('C', Accidental.DOUBLE_FLAT, 10),

    A_DOUBLE_SHARP('A', Accidental.DOUBLE_SHARP, 11),
    B('B', Accidental.NONE, 11),
    B_NATURAL('B', Accidental.NATURAL, 11),
    C_FLAT('C', Accidental.FLAT, 11);

    private char letter;
    private Accidental accidental;
    private String name;
    private int relativePitch, octaveRange;

    Note(char letter, Accidental accidental, int relativePitch) {
        this.letter = letter;
        this.accidental = accidental;
        this.name = letter + accidental.indicator;
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

    Note getFromIndex(int index) {
        return index < Note.values().length - 1
                ? Note.values()[index]
                : null;
    }

    static Note getFirstPracticalEnharmonicToRelativePitch(int relativePitch) {
        switch (relativePitch) {
            case 0: return Note.B_SHARP;
            case 1: return Note.C_SHARP;
            case 2: return Note.D;
            case 3: return Note.D_SHARP;
            case 4: return Note.E;
            case 5: return Note.F;
            case 6: return Note.F_SHARP;
            case 7: return Note.G;
            case 8: return Note.G_SHARP;
            case 9: return Note.A;
            case 10: return Note.A_SHARP;
            case 11: return Note.B;
            default: return null;
        }
    }

    Note[] getPracticalEnharmonicEquivalents(boolean wantNatural) {
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

    static Note[] getAllOtherNotesToFitKeySignature(KeySignature keySignature) {
        // TODO finish
        switch (keySignature) {
            // Flat key signatures
            case C_FLAT_MAJOR: case A_FLAT_MINOR:
                return new Note[] {C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT, A_FLAT, B_FLAT};

            case G_FLAT_MAJOR: case E_FLAT_MINOR:
                return new Note[] {G_FLAT, A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F};

            case D_FLAT_MAJOR: case B_FLAT_MINOR:
                return new Note[] {D_FLAT, E_FLAT, F, G_FLAT, A_FLAT, B_FLAT, C};

            case A_FLAT_MAJOR: case E_FLAT_MAJOR: case C_MINOR:
                return new Note[] {A_FLAT, B_FLAT, C, D_FLAT, E_FLAT, F, G};

            case B_FLAT_MAJOR: case G_MINOR:
                return new Note[] {B_FLAT, C, D, E_FLAT, F, G, A};

            case F_MAJOR: case D_MINOR:
                return new Note[] {D, E, F, G, A, B_FLAT};

                // Non-accidental key signature
            case C_MAJOR: case A_MINOR:
                return new Note[] {C, D, E, F, G, A, B};

                // Sharp key signatures
            case G_MAJOR: case E_MINOR:
                return new Note[] {G, A, B, C, D, E, F_SHARP};

            case D_MAJOR: case B_MINOR:
                return new Note[] {D, E, F_SHARP, G, A, B, C_SHARP};

            case A_MAJOR: case F_SHARP_MINOR:
                return new Note[] {A, B, C_SHARP, D, E, F_SHARP, G_SHARP};

            case E_MAJOR: case C_SHARP_MINOR:
                return new Note[] {E, F_SHARP, G_SHARP, A, B, C_SHARP, D_SHARP};

            case B_MAJOR: case G_SHARP_MINOR:
                return new Note[] {B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP};

            case F_SHARP_MAJOR: case D_SHARP_MINOR:
                return new Note[] {F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP};

            case C_SHARP_MAJOR: case A_SHARP_MINOR:
                return new Note[] {C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP, A_SHARP, B_SHARP};

            default: return null;
        }
    }

    Interval[] getEnharmonicIntervalsToRelativeDegree(int relativeDegree) {
        int index = (2*relativeDegree) % 12;
        return new Interval[] {Interval.values()[index], Interval.values()[index+1]};
    }
}

enum Accidental {
    DOUBLE_FLAT("\u266d\u266d"),
    FLAT("\u266d"),
    NATURAL("\u266e"),
    SHARP("\u266f"),
    DOUBLE_SHARP("x"),
    NONE("");

    String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }
}
