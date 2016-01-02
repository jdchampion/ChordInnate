package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public enum Note {
    B_SHARP("B#", 0, 11, true),
    C("C", 0, 11, false),
    C_NATURAL("C", 0, 11, true),
    D_DOUBLE_FLAT("Dbb", 0, 11, true),

    B_DOUBLE_SHARP("Bx", 1, 11, true),
    C_SHARP("C#", 1, 11, true),
    D_FLAT("Db", 1, 11, true),

    C_DOUBLE_SHARP("Cx", 2, 11, true),
    D("D", 2, 11, false),
    D_NATURAL("D", 2, 11, true),
    E_DOUBLE_FLAT("Ebb", 2, 11, true),

    D_SHARP("D#", 3, 11, true),
    E_FLAT("Eb", 3, 11, true),
    F_DOUBLE_FLAT("Fbb", 3, 11, true),

    D_DOUBLE_SHARP("Dx", 4, 11, true),
    E("E", 4, 11, false),
    E_NATURAL("E", 4, 11, true),
    F_FLAT("Fb", 4, 11, true),

    E_SHARP("E#", 5, 11, true),
    F("F", 5, 11, false),
    F_NATURAL("F", 5, 11, true),
    G_DOUBLE_FLAT("Gbb", 5, 11, true),

    E_DOUBLE_SHARP("Ex", 6, 11, true),
    F_SHARP("F#", 6, 11, true),
    G_FLAT("Gb", 6, 11, true),

    F_DOUBLE_SHARP("Fx", 7, 11, true),
    G("G", 7, 11, false),
    G_NATURAL("G", 7, 11, true),
    A_DOUBLE_FLAT("Abb", 7, 11, true),

    G_SHARP("G#", 8, 12, true),
    A_FLAT("Ab", 8, 12, true),

    G_DOUBLE_SHARP("Gx", 9, 12, true),
    A("A", 9, 12, false),
    A_NATURAL("A", 9, 12, true),
    B_DOUBLE_FLAT("Bbb", 9, 12, true),

    A_SHARP("A#", 10, 12, true),
    B_FLAT("Bb", 10, 12, true),
    C_DOUBLE_FLAT("Cbb", 10, 12, true),

    A_DOUBLE_SHARP("Ax", 11, 12, true),
    B("B", 11, 12, false),
    B_NATURAL("B", 11, 12, true),
    C_FLAT("Cb", 11, 12, true);

    private String name;
    private int relativePitch, octaveRange;
    private boolean hasAccidentalSymbol;

    Note(String name, int relativePitch, int octaveRange, boolean hasAccidentalSymbol) {
        this.name = name;
        this.relativePitch = relativePitch;
        this.octaveRange = octaveRange;
        this.hasAccidentalSymbol = hasAccidentalSymbol;
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

    boolean hasAccidentalSymbol() {
        return hasAccidentalSymbol;
    }
}
