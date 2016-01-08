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
    NONE(""),
    SHARP("\u266f"),
    DOUBLE_SHARP("x");

    private final String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }

    final String getIndicator() { return indicator; }
}
