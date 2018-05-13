package chordinnate.model.musictheory.notation;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 4/14/16.
 */
public enum EnharmonicSpelling {
    C_DOUBLE_FLAT(Letter.C, Accidental.DOUBLE_FLAT),
    C_FLAT(Letter.C, Accidental.FLAT),
    C_NATURAL(Letter.C, Accidental.NATURAL),
    C(Letter.C, Accidental.NONE),
    C_SHARP(Letter.C, Accidental.SHARP),
    C_DOUBLE_SHARP(Letter.C, Accidental.DOUBLE_SHARP),

    D_DOUBLE_FLAT(Letter.D, Accidental.DOUBLE_FLAT),
    D_FLAT(Letter.D, Accidental.FLAT),
    D_NATURAL(Letter.D, Accidental.NATURAL),
    D(Letter.D, Accidental.NONE),
    D_SHARP(Letter.D, Accidental.SHARP),
    D_DOUBLE_SHARP(Letter.D, Accidental.DOUBLE_SHARP),

    E_DOUBLE_FLAT(Letter.E, Accidental.DOUBLE_FLAT),
    E_FLAT(Letter.E, Accidental.FLAT),
    E_NATURAL(Letter.E, Accidental.NATURAL),
    E(Letter.E, Accidental.NONE),
    E_SHARP(Letter.E, Accidental.SHARP),
    E_DOUBLE_SHARP(Letter.E, Accidental.DOUBLE_SHARP),

    F_DOUBLE_FLAT(Letter.F, Accidental.DOUBLE_FLAT),
    F_FLAT(Letter.F, Accidental.FLAT),
    F_NATURAL(Letter.F, Accidental.NATURAL),
    F(Letter.F, Accidental.NONE),
    F_SHARP(Letter.F, Accidental.SHARP),
    F_DOUBLE_SHARP(Letter.F, Accidental.DOUBLE_SHARP),

    G_DOUBLE_FLAT(Letter.G, Accidental.DOUBLE_FLAT),
    G_FLAT(Letter.G, Accidental.FLAT),
    G_NATURAL(Letter.G, Accidental.NATURAL),
    G(Letter.G, Accidental.NONE),
    G_SHARP(Letter.G, Accidental.SHARP),
    G_DOUBLE_SHARP(Letter.G, Accidental.DOUBLE_SHARP),

    A_DOUBLE_FLAT(Letter.A, Accidental.DOUBLE_FLAT),
    A_FLAT(Letter.A, Accidental.FLAT),
    A_NATURAL(Letter.A, Accidental.NATURAL),
    A(Letter.A, Accidental.NONE),
    A_SHARP(Letter.A, Accidental.SHARP),
    A_DOUBLE_SHARP(Letter.A, Accidental.DOUBLE_SHARP),

    B_DOUBLE_FLAT(Letter.B, Accidental.DOUBLE_FLAT),
    B_FLAT(Letter.B, Accidental.FLAT),
    B_NATURAL(Letter.B, Accidental.NATURAL),
    B(Letter.B, Accidental.NONE),
    B_SHARP(Letter.B, Accidental.SHARP),
    B_DOUBLE_SHARP(Letter.B, Accidental.DOUBLE_SHARP),;


    public final Letter LETTER;
    public final Accidental ACCIDENTAL;
    public final String NAME;

    private static final Map<EnharmonicSpelling, EnharmonicSpelling[]> ALL_ENHARMONIC_TRANSITIONS = new HashMap<>(EnharmonicSpelling.values().length);

    static {
        for (EnharmonicSpelling e : EnharmonicSpelling.values())
            ALL_ENHARMONIC_TRANSITIONS.put(e, e.getEnharmonicTransitions());
    }

    private static final Map<Accidental, Integer> ACCIDENTAL_INTEGER_MAP = new HashMap<>(Accidental.values().length);

    static {
        for (Accidental accidental : Accidental.values()) ACCIDENTAL_INTEGER_MAP.put(accidental, accidental.ordinal());
    }

    EnharmonicSpelling(Letter letter, Accidental accidental) {
        this.LETTER = letter;
        this.ACCIDENTAL = accidental;
        this.NAME = letter.toString() + accidental.SYMBOL;
    }

    public EnharmonicSpelling apply(Accidental accidental) {
        return ALL_ENHARMONIC_TRANSITIONS.get(this)[ACCIDENTAL_INTEGER_MAP.get(accidental)];
    }

    /**
     * Returns the list of all possible applications of Accidentals on the current EnharmonicSpelling.
     *
     * @return an array of EnharmonicSpellings that would result from applying an Accidental to it
     * (order by index: DOUBLE_FLAT, FLAT, NATURAL, NONE, SHARP, DOUBLE_SHARP).
     */
    EnharmonicSpelling[] getEnharmonicTransitions() {
        // Prepare the returned array to hold all possible applications of Accidentals
        // (e.g., bb, b, natural, none, #, rn_chord_analysis.sql)
        EnharmonicSpelling[] enharmonicSpellings = new EnharmonicSpelling[6];
        if (this.equals(F_DOUBLE_FLAT) || this.equals(C_DOUBLE_FLAT)) {
            // Unique items are the first two elements. Rest follow the same general pattern for DOUBLE_FLAT
            enharmonicSpellings[0] = getHelper(-2, Accidental.FLAT);
            enharmonicSpellings[1] = getHelper(-1, Accidental.DOUBLE_FLAT);
            getEnharmonicTransitionsHelper(ACCIDENTAL, enharmonicSpellings);
        } else if (this.equals(F_FLAT) || this.equals(C_FLAT)) {
            // Unique item is the first element. Rest follow the same general pattern for FLAT
            enharmonicSpellings[0] = getHelper(-1, Accidental.DOUBLE_FLAT);
            getEnharmonicTransitionsHelper(ACCIDENTAL, enharmonicSpellings);
        } else if (this.equals(B_SHARP) || this.equals(E_SHARP)) {
            // Unique items are the last two elements. Rest follow the same general pattern for SHARP
            enharmonicSpellings[4] = getHelper(0, Accidental.DOUBLE_SHARP);
            enharmonicSpellings[5] = getHelper(1, Accidental.DOUBLE_SHARP);
            getEnharmonicTransitionsHelper(ACCIDENTAL, enharmonicSpellings);
        } else if (this.equals(B_DOUBLE_SHARP) || this.equals(E_DOUBLE_SHARP)) {
            // Unique items are the last two elements. Rest follow the same general pattern for DOUBLE_SHARP
            enharmonicSpellings[4] = getHelper(1, Accidental.DOUBLE_SHARP);
            enharmonicSpellings[5] = getHelper(2, Accidental.SHARP);
            getEnharmonicTransitionsHelper(ACCIDENTAL, enharmonicSpellings);
        } else {
            // All items follow the general pattern
            getEnharmonicTransitionsHelper(ACCIDENTAL, enharmonicSpellings);
        }

        return enharmonicSpellings;
    }

    /**
     * Returns the EnharmonicSpelling that is 'vector' letters away from the current EnharonicSpelling, and containing
     * 'accidental' as its Accidental.
     *
     * @param vector     the total letters traversed to find the new EnharmonicSpelling's letter
     * @param accidental the Accidental that the new EnharmonicSpelling should contain
     * @return the EnharmonicSpelling matching the two properties in the above description
     */
    private EnharmonicSpelling getHelper(int vector, Accidental accidental) {
        String enumName = LETTER.getLetterByVectorTraversal(vector).name() + ((accidental.equals(Accidental.NONE)) ? "" : "_" + accidental.name());
        return EnharmonicSpelling.valueOf(enumName);
    }

    /**
     * Fills an array with the enharmonic transitions for the current EnharmonicSpelling, after an Accidental had been
     * applied to it. The conventional ordering of this array will be: DOUBLE_FLAT, FLAT, NATURAL, NONE, SHARP, DOUBLE_SHARP.
     *
     * @param accidental          the current Accidental for the EnharmonicSpelling
     * @param enharmonicSpellings the array to fill with transitions
     */
    private void getEnharmonicTransitionsHelper(Accidental accidental, EnharmonicSpelling[] enharmonicSpellings) {
        // Generic patterns for enharmonic transitions. One of these arrays will be chosen to read from
        EnharmonicSpelling[]
                genericDoubleFlatPattern = {getHelper(-1, Accidental.DOUBLE_FLAT), getHelper(-1, Accidental.FLAT), this, this, getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL)},
                genericFlatPattern = {getHelper(-1, Accidental.FLAT), getHelper(0, Accidental.DOUBLE_FLAT), this, this, getHelper(0, Accidental.NATURAL), getHelper(0, Accidental.SHARP)},
                genericNaturalPattern = {getHelper(0, Accidental.DOUBLE_FLAT), getHelper(0, Accidental.FLAT), this, getHelper(0, Accidental.NONE), getHelper(0, Accidental.SHARP), getHelper(0, Accidental.DOUBLE_SHARP)},
                genericNonAccidentalPattern = {getHelper(0, Accidental.DOUBLE_FLAT), getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL), this, getHelper(0, Accidental.SHARP), getHelper(0, Accidental.DOUBLE_SHARP)},
                genericSharpPattern = {getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL), this, this, getHelper(0, Accidental.DOUBLE_SHARP), getHelper(1, Accidental.SHARP)},
                genericDoubleSharpPattern = {getHelper(0, Accidental.NATURAL), getHelper(0, Accidental.SHARP), this, this, getHelper(1, Accidental.SHARP), getHelper(1, Accidental.DOUBLE_SHARP)};

        // Choose the array and begin reading
        switch (accidental) {
            case DOUBLE_FLAT: {
                readInto(enharmonicSpellings, genericDoubleFlatPattern);
                break;
            }
            case FLAT: {
                readInto(enharmonicSpellings, genericFlatPattern);
                break;
            }
            case NATURAL: {
                readInto(enharmonicSpellings, genericNaturalPattern);
                break;
            }
            case NONE: {
                readInto(enharmonicSpellings, genericNonAccidentalPattern);
                break;
            }
            case SHARP: {
                readInto(enharmonicSpellings, genericSharpPattern);
                break;
            }
            case DOUBLE_SHARP: {
                readInto(enharmonicSpellings, genericDoubleSharpPattern);
                break;
            }
        }
    }

    /**
     * A helper function to fill in the remaining elements of the array for getEnharmonicTransitionsHelper().
     *
     * @param destination the array to copy the contents of 'source' from
     * @param source      the array containing the desired elements to copy
     */
    private void readInto(EnharmonicSpelling[] destination, EnharmonicSpelling[] source) {
        for (int i = 0; i < source.length; i++) {

            /*
             * Fill in the array with the source pattern.
             * Do not overwrite the unique items from getEnharmonicTransitions().
             */
            if (destination[i] == null) destination[i] = source[i];
        }
    }

}