package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/1/16.
 */
public enum NoteType {
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

    final char letter;
    final Accidental accidental;
    final String name;
    final int relativePitch, octaveRange;

    NoteType(char letter, Accidental accidental, int relativePitch) {
        this.letter = letter;
        this.accidental = accidental;
        this.name = letter + accidental.indicator;
        this.relativePitch = relativePitch;
        this.octaveRange = (relativePitch < 8) ? 11 : 10;
    }

    /**
     *
     * @return
     */
    boolean hasAccidentalSymbol() {
        return !accidental.equals(NONE);
    }

    /**
     *
     * @return
     */
    boolean isNatural() { return accidental.equals(NATURAL); }

    /**
     *
     * @return
     */
    boolean isDoubleAccidental() { return accidental.equals(DOUBLE_FLAT)
            || accidental.equals(DOUBLE_SHARP); }

    /**
     *
     * @return
     */
    final NoteType getNext() {
        return this.ordinal() < NoteType.values().length - 1
                ? NoteType.values()[this.ordinal() + 1]
                : null;
    }

    /**
     *
     * @return
     */
    final NoteType getPrevious() {
        return this.ordinal() > 0
                ? NoteType.values()[this.ordinal() - 1]
                : null;
    }

    /**
     *
     * @param index
     * @return
     */
    final NoteType getFromIndex(int index) {
        return (index > 0 || index < NoteType.values().length - 1)
                ? NoteType.values()[index]
                : null;
    }

    /**
     *
     * @param comparisonNote
     * @return
     */
    boolean isEnharmonicallyEquivalentTo(NoteType comparisonNote) {
        return this.relativePitch == comparisonNote.relativePitch;
    }

    /**
     *
     * @param wantDoubleSharps
     * @return
     */
    static NoteType[] getAllSharps(boolean wantDoubleSharps) {
        return wantDoubleSharps
                ? new NoteType[] {A_DOUBLE_SHARP, B_DOUBLE_SHARP, C_DOUBLE_SHARP,
                        D_DOUBLE_SHARP, E_DOUBLE_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP,
                        A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP}

                : new NoteType[] {A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP};
    }

    /**
     *
     * @param wantDoubleFlats
     * @return
     */
    static NoteType[] getAllFlats(boolean wantDoubleFlats) {
        return wantDoubleFlats
                ? new NoteType[] {A_DOUBLE_FLAT, B_DOUBLE_FLAT, C_DOUBLE_FLAT,
                        D_DOUBLE_FLAT, E_DOUBLE_FLAT, F_DOUBLE_FLAT, G_DOUBLE_FLAT,
                        A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT}
                : new NoteType[] {A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT};
     }

    /**
     *
     * @return
     */
    static NoteType[] getFlatChromaticNoteArray() {
        return new NoteType[] {C, D_FLAT, D, E_FLAT, E, F, G_FLAT, G, A_FLAT, A, B_FLAT, C_FLAT};
    }

    /**
     *
     * @return
     */
    static NoteType[] getSharpChromaticNoteArray() {
        return new NoteType[] {C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, B};
    }

    /**
     *
     * @param c
     * @param accidental
     * @return
     */
    static NoteType getNoteType(char c, Accidental accidental) {
        switch (accidental) {
            case DOUBLE_FLAT:
            case FLAT:
            case SHARP:
            case DOUBLE_SHARP:
            case NATURAL: return Enum.valueOf(NoteType.class, c + "_" + accidental);
            case NONE: return Enum.valueOf(NoteType.class, c + "");
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

    final String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }
}
