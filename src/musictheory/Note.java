package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public enum Note {
    B_SHARP("B", Accidental.SHARP, 0, 11),
    C("C", Accidental.NONE, 0, 11),
    C_NATURAL("C", Accidental.NATURAL, 0, 11),
    D_DOUBLE_FLAT("D", Accidental.DOUBLE_FLAT, 0, 11),

    B_DOUBLE_SHARP("B", Accidental.DOUBLE_SHARP, 1, 11),
    C_SHARP("C", Accidental.SHARP, 1, 11),
    D_FLAT("D", Accidental.FLAT, 1, 11),

    C_DOUBLE_SHARP("C", Accidental.DOUBLE_SHARP, 2, 11),
    D("D", Accidental.NONE, 2, 11),
    D_NATURAL("D", Accidental.NATURAL, 2, 11),
    E_DOUBLE_FLAT("E", Accidental.DOUBLE_FLAT, 2, 11),

    D_SHARP("D", Accidental.SHARP, 3, 11),
    E_FLAT("E", Accidental.FLAT, 3, 11),
    F_DOUBLE_FLAT("F", Accidental.DOUBLE_FLAT, 3, 11),

    D_DOUBLE_SHARP("D", Accidental.DOUBLE_SHARP, 4, 11),
    E("E", Accidental.NONE, 4, 11),
    E_NATURAL("E", Accidental.NATURAL, 4, 11),
    F_FLAT("F", Accidental.FLAT, 4, 11),

    E_SHARP("E", Accidental.SHARP, 5, 11),
    F("F", Accidental.NONE, 5, 11),
    F_NATURAL("F", Accidental.NATURAL, 5, 11),
    G_DOUBLE_FLAT("G", Accidental.DOUBLE_FLAT, 5, 11),

    E_DOUBLE_SHARP("E", Accidental.DOUBLE_SHARP, 6, 11),
    F_SHARP("F", Accidental.SHARP, 6, 11),
    G_FLAT("G", Accidental.FLAT, 6, 11),

    F_DOUBLE_SHARP("F", Accidental.DOUBLE_SHARP, 7, 11),
    G("G", Accidental.NONE, 7, 11),
    G_NATURAL("G", Accidental.NATURAL, 7, 11),
    A_DOUBLE_FLAT("A", Accidental.DOUBLE_FLAT, 7, 11),

    G_SHARP("G", Accidental.SHARP, 8, 12),
    A_FLAT("A", Accidental.FLAT, 8, 12),

    G_DOUBLE_SHARP("G", Accidental.DOUBLE_SHARP, 9, 12),
    A("A", Accidental.NONE, 9, 12),
    A_NATURAL("A", Accidental.NATURAL, 9, 12),
    B_DOUBLE_FLAT("B", Accidental.DOUBLE_FLAT, 9, 12),

    A_SHARP("A", Accidental.SHARP, 10, 12),
    B_FLAT("B", Accidental.FLAT, 10, 12),
    C_DOUBLE_FLAT("C", Accidental.DOUBLE_FLAT, 10, 12),

    A_DOUBLE_SHARP("A", Accidental.DOUBLE_SHARP, 11, 12),
    B("B", Accidental.NONE, 11, 12),
    B_NATURAL("B", Accidental.NATURAL, 11, 12),
    C_FLAT("C", Accidental.FLAT, 11, 12);

    private String name;
    private Accidental accidental;
    private int relativePitch, octaveRange;

    Note(String name, Accidental accidental, int relativePitch, int octaveRange) {
        this.name = name;
        this.accidental = accidental;
        this.relativePitch = relativePitch;
        this.octaveRange = octaveRange;
    }

    String getName() {
        return name + accidental.indicator;
    }

    int getRelativePitch() {
        return relativePitch;
    }

    int getOctaveRange() {
        return octaveRange;
    }

    boolean hasAccidentalSymbol() {
        return !accidental.equals(Accidental.NONE);
    }

    boolean isNatural() { return accidental.equals(Accidental.NATURAL); }
}

enum Accidental {
    DOUBLE_FLAT("bb"),
    FLAT("b"),
    NATURAL(""),       // TODO Not sure what to use as an indicator for natural...
    SHARP("#"),
    DOUBLE_SHARP("x"),
    NONE("");

    String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }
}
