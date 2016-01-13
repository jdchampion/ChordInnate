package musictheory;

import static musictheory.NoteType.*;

/**
 * Created by Joseph on 1/2/16.
 *
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 */
public enum KeySignature {

    // Flat key signatures
    C_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    A_FLAT_MINOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),

    G_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),
    E_FLAT_MINOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),

    D_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),
    B_FLAT_MINOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),

    A_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),
    F_MINOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),

    E_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT}),
    C_MINOR(new NoteType[]{B_FLAT, E_FLAT, A_FLAT}),

    B_FLAT_MAJOR(new NoteType[]{B_FLAT, E_FLAT}),
    G_MINOR(new NoteType[]{B_FLAT, E_FLAT}),

    F_MAJOR(new NoteType[]{B_FLAT}),
    D_MINOR(new NoteType[]{B_FLAT}),

    // Non-accidental key signature
    C_MAJOR(new NoteType[]{}),
    A_MINOR(new NoteType[]{}),

    // Sharp key signatures
    G_MAJOR(new NoteType[]{F_SHARP}),
    E_MINOR(new NoteType[]{F_SHARP}),

    D_MAJOR(new NoteType[]{F_SHARP, C_SHARP}),
    B_MINOR(new NoteType[]{F_SHARP, C_SHARP}),

    A_MAJOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP}),
    F_SHARP_MINOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP}),

    E_MAJOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),
    C_SHARP_MINOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),

    B_MAJOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),
    G_SHARP_MINOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),

    F_SHARP_MAJOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),
    D_SHARP_MINOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),

    C_SHARP_MAJOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    A_SHARP_MINOR(new NoteType[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),

    NO_KEY_SIGNATURE(new NoteType[]{}),

    THEORETICAL_KEY_SIGNATURE(new NoteType[]{});

    final NoteType[] notes;

    KeySignature(NoteType[] notes) {
        this.notes = notes;
    }

    boolean isFlatKeySignature() {
        if (this.equals(C_MAJOR) || this.equals(A_MINOR)) {
            return false;   // C Major and A Minor are neither Flat nor Sharp
        }
        return this.notes[0].equals(B_FLAT);
    }

    boolean isSharpKeySignature() {
        if (this.equals(C_MAJOR) || this.equals(A_MINOR)) {
            return false;   // C Major and A Minor are neither Flat nor Sharp
        }
        return this.notes[0].equals(F_SHARP);
    }

    static final KeySignature getMajorKeySignatureWithRoot(NoteType root) {
        switch(root) {
            // Flat key signatures
            case C_FLAT: return C_FLAT_MAJOR;
            case G_FLAT: return G_FLAT_MAJOR;
            case D_FLAT: return D_FLAT_MAJOR;
            case A_FLAT: return A_FLAT_MAJOR;
            case E_FLAT: return E_FLAT_MAJOR;
            case B_FLAT: return B_FLAT_MAJOR;
            case F: return F_MAJOR;

            // Non-accidental key signatures
            case C: return C_MAJOR;

            // Sharp key signatures
            case G: return G_MAJOR;
            case D: return D_MAJOR;
            case A: return A_MAJOR;
            case E: return E_MAJOR;
            case B: return B_MAJOR;
            case F_SHARP: return F_SHARP_MAJOR;
            case C_SHARP: return C_SHARP_MAJOR;

            // TODO weird cases
            // Reference: https://en.wikipedia.org/wiki/Theoretical_key
            case G_SHARP: return THEORETICAL_KEY_SIGNATURE; /*A_FLAT_MAJOR;*/
            case D_SHARP: return THEORETICAL_KEY_SIGNATURE; /*E_FLAT_MAJOR;*/
            case A_SHARP: return THEORETICAL_KEY_SIGNATURE; /*B_FLAT_MAJOR;*/
            case E_SHARP: return THEORETICAL_KEY_SIGNATURE; /*F_MAJOR;*/

            case B_SHARP: return THEORETICAL_KEY_SIGNATURE; /*C_MAJOR;*/

            case F_FLAT: return THEORETICAL_KEY_SIGNATURE; /*E_MAJOR;*/

            default: return NO_KEY_SIGNATURE;
        }
    }

    static final KeySignature getMinorKeySignatureWithRoot(NoteType root) {
        switch(root) {
            // Flat key signatures
            case A_FLAT: return A_FLAT_MINOR;
            case E_FLAT: return E_FLAT_MINOR;
            case B_FLAT: return B_FLAT_MINOR;
            case F: return F_MINOR;
            case C: return C_MINOR;
            case G: return G_MINOR;
            case D: return D_MINOR;

            // Non-accidental key signatures
            case A: return A_MINOR;

            // Sharp key signatures
            case E: return E_MINOR;
            case B: return B_MINOR;
            case F_SHARP: return F_SHARP_MINOR;
            case C_SHARP: return C_SHARP_MINOR;
            case G_SHARP: return G_SHARP_MINOR;
            case D_SHARP: return D_SHARP_MINOR;
            case A_SHARP: return A_SHARP_MINOR;

            // TODO weird cases
            // Reference: https://en.wikipedia.org/wiki/Theoretical_key
            case E_SHARP: return THEORETICAL_KEY_SIGNATURE; /*F_MINOR;*/

            case B_SHARP: return THEORETICAL_KEY_SIGNATURE; /*C_MINOR;*/

            case F_FLAT: return THEORETICAL_KEY_SIGNATURE; /*E_MINOR;*/
            case C_FLAT: return THEORETICAL_KEY_SIGNATURE; /*B_MINOR;*/
            case G_FLAT: return THEORETICAL_KEY_SIGNATURE; /*A_FLAT_MINOR;*/
            case D_FLAT: return THEORETICAL_KEY_SIGNATURE; /*C_SHARP_MINOR;*/

            default: return NO_KEY_SIGNATURE;
        }
    }
}
