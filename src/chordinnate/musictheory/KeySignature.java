package chordinnate.musictheory;

import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.EnharmonicSpelling.*;
import static chordinnate.musictheory.KeySignatureType.*;

/**
 * Created by Joseph on 4/14/16.
 */
enum KeySignatureType {
    MAJOR,
    MINOR,
    THEORETICAL,
    NONE
}

/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 *            https://en.wikipedia.org/wiki/Theoretical_key
 */
public enum KeySignature implements Relative<KeySignature>, Parallel<KeySignature> {
    // No key signature
    NO_KEY_SIGNATURE(null, NONE, null),

    // Major, non-accidental
    C_MAJOR(C, MAJOR, new EnharmonicSpelling[]{}),

    // Major, flat
    F_MAJOR(F, MAJOR, new EnharmonicSpelling[]{B_FLAT}),
    B_FLAT_MAJOR(B_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT}),
    E_FLAT_MAJOR(E_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT}),
    A_FLAT_MAJOR(A_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),
    D_FLAT_MAJOR(D_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),
    G_FLAT_MAJOR(G_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),
    C_FLAT_MAJOR(C_FLAT, MAJOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    F_FLAT_MAJOR(F_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    B_DOUBLE_FLAT_MAJOR(B_DOUBLE_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    E_DOUBLE_FLAT_MAJOR(E_DOUBLE_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    A_DOUBLE_FLAT_MAJOR(A_DOUBLE_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_DOUBLE_FLAT, G_FLAT, C_FLAT, F_FLAT}),

    // Major, sharp
    G_MAJOR(G, MAJOR, new EnharmonicSpelling[]{F_SHARP}),
    D_MAJOR(D, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP}),
    A_MAJOR(A, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP}),
    E_MAJOR(E, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),
    B_MAJOR(B, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),
    F_SHARP_MAJOR(F_SHARP, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),
    C_SHARP_MAJOR(C_SHARP, MAJOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    G_SHARP_MAJOR(G_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    D_SHARP_MAJOR(D_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    A_SHARP_MAJOR(A_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    E_SHARP_MAJOR(E_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    B_SHARP_MAJOR(B_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_DOUBLE_SHARP, E_SHARP, B_SHARP}),

    // Minor, non-accidental
    A_MINOR(A, MINOR, new EnharmonicSpelling[]{}),

    // Minor, flat
    D_MINOR(D, MINOR, new EnharmonicSpelling[]{B_FLAT}),
    G_MINOR(G, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT}),
    C_MINOR(C, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT}),
    F_MINOR(F, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),
    B_FLAT_MINOR(B_FLAT, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),
    E_FLAT_MINOR(E_FLAT, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),
    A_FLAT_MINOR(A_FLAT, MINOR, new EnharmonicSpelling[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    D_FLAT_MINOR(D_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    G_FLAT_MINOR(G_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    C_FLAT_MINOR(C_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    F_FLAT_MINOR(F_FLAT, THEORETICAL, new EnharmonicSpelling[]{B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_DOUBLE_FLAT, G_FLAT, C_FLAT, F_FLAT}),

    // Minor, sharp
    E_MINOR(E, MINOR, new EnharmonicSpelling[]{F_SHARP}),
    B_MINOR(B, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP}),
    F_SHARP_MINOR(F_SHARP, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP}),
    C_SHARP_MINOR(C_SHARP, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),
    G_SHARP_MINOR(G_SHARP, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),
    D_SHARP_MINOR(D_SHARP, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),
    A_SHARP_MINOR(A_SHARP, MINOR, new EnharmonicSpelling[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    E_SHARP_MINOR(E_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    B_SHARP_MINOR(B_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    F_DOUBLE_SHARP_MINOR(F_DOUBLE_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    C_DOUBLE_SHARP_MINOR(C_DOUBLE_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    G_DOUBLE_SHARP_MINOR(G_DOUBLE_SHARP, THEORETICAL, new EnharmonicSpelling[]{F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_DOUBLE_SHARP, E_SHARP, B_SHARP}),

    ;

    private final EnharmonicSpelling KEY;
    private final KeySignatureType KEY_SIGNATURE_TYPE;
    private final EnharmonicSpelling[] SIGNATURE;

    private static final KeySignature[] VALUES = KeySignature.values();

    // There are certainly other ways to get the relatives, but I believe using a HashMap is the most straightforward.
    private static final Map<KeySignature, KeySignature> RELATIVE_KEY_SIGNATURES = new HashMap<>(VALUES.length);
        static {
            // Majors
            RELATIVE_KEY_SIGNATURES.put(B_FLAT_MAJOR, G_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_FLAT_MAJOR, C_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_FLAT_MAJOR, F_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_FLAT_MAJOR, B_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_FLAT_MAJOR, E_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_FLAT_MAJOR, A_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_FLAT_MAJOR, D_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_DOUBLE_FLAT_MAJOR, F_FLAT_MINOR);

            RELATIVE_KEY_SIGNATURES.put(B_MAJOR, G_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_MAJOR, C_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_MAJOR, F_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_MAJOR, B_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_MAJOR, E_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_MAJOR, A_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_MAJOR, D_MINOR);

            RELATIVE_KEY_SIGNATURES.put(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_SHARP_MAJOR, F_DOUBLE_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_SHARP_MAJOR, B_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_SHARP_MAJOR, E_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_SHARP_MAJOR, A_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_SHARP_MAJOR, D_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(B_SHARP_MAJOR, G_DOUBLE_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_SHARP_MAJOR, C_DOUBLE_SHARP_MINOR);

            // Minors
            RELATIVE_KEY_SIGNATURES.put(G_MINOR, B_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_MINOR, E_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_MINOR, A_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_FLAT_MINOR, D_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_FLAT_MINOR, G_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_FLAT_MINOR, C_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_FLAT_MINOR, F_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(G_FLAT_MINOR, B_DOUBLE_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_FLAT_MINOR, E_DOUBLE_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_FLAT_MINOR, A_DOUBLE_FLAT_MAJOR);

            RELATIVE_KEY_SIGNATURES.put(G_SHARP_MINOR, B_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_SHARP_MINOR, E_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_SHARP_MINOR, A_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_MINOR, D_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_MINOR, G_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_MINOR, C_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_MINOR, F_MAJOR);

            RELATIVE_KEY_SIGNATURES.put(G_DOUBLE_SHARP_MINOR, B_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_DOUBLE_SHARP_MINOR, E_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_DOUBLE_SHARP_MINOR, A_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_SHARP_MINOR, D_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_SHARP_MINOR, G_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_SHARP_MINOR, C_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_SHARP_MINOR, F_SHARP_MAJOR);

            // No key signature
            RELATIVE_KEY_SIGNATURES.put(NO_KEY_SIGNATURE, NO_KEY_SIGNATURE);
        }

    KeySignature(EnharmonicSpelling key, KeySignatureType keySignatureType, EnharmonicSpelling[] signature) {
        this.KEY = key;
        this.KEY_SIGNATURE_TYPE = keySignatureType;
        this.SIGNATURE = signature;
    }

    public EnharmonicSpelling getKey() {
        return KEY;
    }

    public KeySignatureType getKeySignatureType() {
        return KEY_SIGNATURE_TYPE;
    }

    public boolean contains(EnharmonicSpelling enharmonicSpelling) {
        if (this.equals(NO_KEY_SIGNATURE)) return false;

        for (EnharmonicSpelling e : SIGNATURE) {
            if (enharmonicSpelling.equals(e)) return true;
        }

        return false;
    }

    @Override
    public boolean isRelativeMajorTo(KeySignature other) {
        return getRelativeMajor().equals(other);
    }

    @Override
    public boolean isRelativeMinorTo(KeySignature other) {
        return getRelativeMinor().equals(other);
    }

    @Override
    public KeySignature getRelativeMajor() {
        return this.KEY_SIGNATURE_TYPE == MAJOR || (this.KEY_SIGNATURE_TYPE == THEORETICAL && this.name().contains("MAJOR"))
                ? this
                : RELATIVE_KEY_SIGNATURES.get(this);
    }

    @Override
    public KeySignature getRelativeMinor() {
        return this.KEY_SIGNATURE_TYPE == MINOR || (this.KEY_SIGNATURE_TYPE == THEORETICAL && this.name().contains("MINOR"))
                ? this
                : RELATIVE_KEY_SIGNATURES.get(this);
    }

    @Override
    public boolean isParallelMajorTo(KeySignature other) {
        return getParallelMajor().equals(other);
    }

    @Override
    public boolean isParallelMinorTo(KeySignature other) {
        return getParallelMinor().equals(other);
    }

    @Override
    public KeySignature getParallelMajor() {
        return this.KEY_SIGNATURE_TYPE == MAJOR || (this.KEY_SIGNATURE_TYPE == THEORETICAL && this.name().contains("MAJOR"))
                ? this
                : KeySignature.valueOf(KEY.toString() + "_" + MAJOR.toString());
    }

    @Override
    public KeySignature getParallelMinor() {
        return this.KEY_SIGNATURE_TYPE == MINOR || (this.KEY_SIGNATURE_TYPE == THEORETICAL && this.name().contains("MINOR"))
                ? this
                : KeySignature.valueOf(KEY.toString() + "_" + MINOR.toString());
    }

    public KeySignature getNext() {
        switch (KEY_SIGNATURE_TYPE) {
            case MAJOR: {
                switch (this) {
                    case C_MAJOR: return NO_KEY_SIGNATURE;
                    default: return VALUES[ordinal() + 1];
                }
            }
            case MINOR: {
                switch (this) {
                    case A_MINOR: return NO_KEY_SIGNATURE;
                    default: return VALUES[ordinal() + 1];
                }
            }
            case THEORETICAL: {
                switch (this) {
                    case A_DOUBLE_FLAT_MAJOR:
                    case B_SHARP_MAJOR:
                    case F_FLAT_MINOR:
                    case G_DOUBLE_SHARP_MINOR: return null;
                    default: return VALUES[ordinal() + 1];
                }
            }
            case NONE:
                return NO_KEY_SIGNATURE;
        }
        return null;
    }

    public KeySignature getPrevious() {
        switch (KEY_SIGNATURE_TYPE) {
            case MAJOR: {
                switch (this) {
                    case C_MAJOR: return NO_KEY_SIGNATURE;
                    case F_MAJOR:
                    case G_MAJOR: return null;
                    default: return VALUES[ordinal() - 1];
                }
            }
            case MINOR: {
                switch (this) {
                    case A_MINOR: return NO_KEY_SIGNATURE;
                    case D_MINOR:
                    case E_MINOR: return null;
                    default: return VALUES[ordinal() - 1];
                }
            }
            case THEORETICAL: {
                return VALUES[ordinal() - 1];
            }
            case NONE:
                return NO_KEY_SIGNATURE;
        }
        return null;
    }

}
