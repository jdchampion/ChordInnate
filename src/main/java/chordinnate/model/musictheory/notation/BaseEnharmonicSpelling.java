package chordinnate.model.musictheory.notation;


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 4/14/16.
 */
public enum BaseEnharmonicSpelling {
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


    public final Letter letter;
    public final Accidental accidental;
    public final String baseName;
    public final int baseMidiValue;

    private static final Map<BaseEnharmonicSpelling, BaseEnharmonicSpelling[]> ALL_ENHARMONIC_TRANSITIONS = Arrays.stream(BaseEnharmonicSpelling.values())
            .collect(Collectors.toMap(Function.identity(), BaseEnharmonicSpelling::getEnharmonicTransitions));

    BaseEnharmonicSpelling(Letter letter, Accidental accidental) {
        this.letter = letter;
        this.accidental = accidental;
        this.baseName = letter.toString() + accidental.symbol;
        this.baseMidiValue = (letter.baseMidiValue + accidental.semitoneModifier) % 12;
    }

    public BaseEnharmonicSpelling apply(Accidental accidental) {
        return ALL_ENHARMONIC_TRANSITIONS.get(this)[accidental.ordinal()];
    }

    /**
     * Returns the list of all possible applications of Accidentals on the current BaseEnharmonicSpelling.
     *
     * @return an array of EnharmonicSpellings that would result from applying an Accidental to it
     * (order by index: DOUBLE_FLAT, FLAT, NATURAL, NONE, SHARP, DOUBLE_SHARP).
     */
    BaseEnharmonicSpelling[] getEnharmonicTransitions() {
        // Prepare the returned array to hold all possible applications of Accidentals
        // (e.g., bb, b, natural, none, #, x)
        BaseEnharmonicSpelling[] baseEnharmonicSpellings = new BaseEnharmonicSpelling[6];
        if (this.equals(F_DOUBLE_FLAT) || this.equals(C_DOUBLE_FLAT)) {
            // Unique items are the first two elements. Rest follow the same general pattern for DOUBLE_FLAT
            baseEnharmonicSpellings[0] = getHelper(-2, Accidental.FLAT);
            baseEnharmonicSpellings[1] = getHelper(-1, Accidental.DOUBLE_FLAT);
            getEnharmonicTransitionsHelper(accidental, baseEnharmonicSpellings);
        } else if (this.equals(F_FLAT) || this.equals(C_FLAT)) {
            // Unique item is the first element. Rest follow the same general pattern for FLAT
            baseEnharmonicSpellings[0] = getHelper(-1, Accidental.DOUBLE_FLAT);
            getEnharmonicTransitionsHelper(accidental, baseEnharmonicSpellings);
        } else if (this.equals(B_SHARP) || this.equals(E_SHARP)) {
            // Unique items are the last two elements. Rest follow the same general pattern for SHARP
            baseEnharmonicSpellings[4] = getHelper(0, Accidental.DOUBLE_SHARP);
            baseEnharmonicSpellings[5] = getHelper(1, Accidental.DOUBLE_SHARP);
            getEnharmonicTransitionsHelper(accidental, baseEnharmonicSpellings);
        } else if (this.equals(B_DOUBLE_SHARP) || this.equals(E_DOUBLE_SHARP)) {
            // Unique items are the last two elements. Rest follow the same general pattern for DOUBLE_SHARP
            baseEnharmonicSpellings[4] = getHelper(1, Accidental.DOUBLE_SHARP);
            baseEnharmonicSpellings[5] = getHelper(2, Accidental.SHARP);
            getEnharmonicTransitionsHelper(accidental, baseEnharmonicSpellings);
        } else {
            // All items follow the general pattern
            getEnharmonicTransitionsHelper(accidental, baseEnharmonicSpellings);
        }

        return baseEnharmonicSpellings;
    }

    /**
     * Returns the BaseEnharmonicSpelling that is {@code vector} letters away from the current BaseEnharonicSpelling, and containing
     * {@code accidental} as its Accidental.
     *
     * @param vector     the total letters traversed to find the new BaseEnharmonicSpelling's letter
     * @param accidental the Accidental that the new BaseEnharmonicSpelling should contain
     * @return the BaseEnharmonicSpelling matching the two properties in the above description
     */
    private BaseEnharmonicSpelling getHelper(int vector, Accidental accidental) {
        String enumName = letter.getLetterByVectorTraversal(vector).name() + ((accidental.equals(Accidental.NONE)) ? "" : "_" + accidental.name());
        return BaseEnharmonicSpelling.valueOf(enumName);
    }

    /**
     * Fills an array with the enharmonic transitions for the current BaseEnharmonicSpelling, after an Accidental had been
     * applied to it. The conventional ordering of this array will be: DOUBLE_FLAT, FLAT, NATURAL, NONE, SHARP, DOUBLE_SHARP.
     *
     * @param accidental          the current Accidental for the BaseEnharmonicSpelling
     * @param baseEnharmonicSpellings the array to fill with transitions
     */
    private void getEnharmonicTransitionsHelper(Accidental accidental, BaseEnharmonicSpelling[] baseEnharmonicSpellings) {
        // Generic patterns for enharmonic transitions. One of these arrays will be chosen to read from

        // Choose the array and begin reading
        switch (accidental) {
            case DOUBLE_FLAT: 
                BaseEnharmonicSpelling[] genericDoubleFlatPattern = {getHelper(-1, Accidental.DOUBLE_FLAT), getHelper(-1, Accidental.FLAT), this, this, getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL)};
                readInto(baseEnharmonicSpellings, genericDoubleFlatPattern);
                break;
            case FLAT:
                BaseEnharmonicSpelling[] genericFlatPattern = {getHelper(-1, Accidental.FLAT), getHelper(0, Accidental.DOUBLE_FLAT), this, this, getHelper(0, Accidental.NATURAL), getHelper(0, Accidental.SHARP)};
                readInto(baseEnharmonicSpellings, genericFlatPattern);
                break;
            case NATURAL:
                BaseEnharmonicSpelling[] genericNaturalPattern = {getHelper(0, Accidental.DOUBLE_FLAT), getHelper(0, Accidental.FLAT), this, getHelper(0, Accidental.NONE), getHelper(0, Accidental.SHARP), getHelper(0, Accidental.DOUBLE_SHARP)};
                readInto(baseEnharmonicSpellings, genericNaturalPattern);
                break;
            case NONE:
                BaseEnharmonicSpelling[] genericNonAccidentalPattern = {getHelper(0, Accidental.DOUBLE_FLAT), getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL), this, getHelper(0, Accidental.SHARP), getHelper(0, Accidental.DOUBLE_SHARP)};
                readInto(baseEnharmonicSpellings, genericNonAccidentalPattern);
                break;
            case SHARP:
                BaseEnharmonicSpelling[] genericSharpPattern = {getHelper(0, Accidental.FLAT), getHelper(0, Accidental.NATURAL), this, this, getHelper(0, Accidental.DOUBLE_SHARP), getHelper(1, Accidental.SHARP)};
                readInto(baseEnharmonicSpellings, genericSharpPattern);
                break;
            case DOUBLE_SHARP:
                BaseEnharmonicSpelling[] genericDoubleSharpPattern = {getHelper(0, Accidental.NATURAL), getHelper(0, Accidental.SHARP), this, this, getHelper(1, Accidental.SHARP), getHelper(1, Accidental.DOUBLE_SHARP)};
                readInto(baseEnharmonicSpellings, genericDoubleSharpPattern);
                break;
        }
    }

    /**
     * A helper function to fill in the remaining elements of the array for getEnharmonicTransitionsHelper().
     *
     * @param destination the array to copy the contents of 'source' from
     * @param source      the array containing the desired elements to copy
     */
    private void readInto(BaseEnharmonicSpelling[] destination, BaseEnharmonicSpelling[] source) {
        for (int i = 0; i < source.length; i++) {

            /*
             * Fill in the array with the source pattern.
             * Do not overwrite the unique items from getEnharmonicTransitions().
             */
            if (destination[i] == null) destination[i] = source[i];
        }
    }

}