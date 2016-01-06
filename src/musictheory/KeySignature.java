package musictheory;

import static musictheory.Note.*;

/**
 * Created by Joseph on 1/2/16.
 *
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 */
public enum KeySignature {

    // Flat key signatures
    C_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),
    A_FLAT_MINOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT}),

    G_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),
    E_FLAT_MINOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT}),

    D_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),
    B_FLAT_MINOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT}),

    A_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),
    F_MINOR(new Note[]{B_FLAT, E_FLAT, A_FLAT, D_FLAT}),

    E_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT, A_FLAT}),
    C_MINOR(new Note[]{B_FLAT, E_FLAT, A_FLAT}),

    B_FLAT_MAJOR(new Note[]{B_FLAT, E_FLAT}),
    G_MINOR(new Note[]{B_FLAT, E_FLAT}),

    F_MAJOR(new Note[]{B_FLAT}),
    D_MINOR(new Note[]{B_FLAT}),

    // Non-accidental key signature
    C_MAJOR(new Note[]{}),
    A_MINOR(new Note[]{}),

    // Sharp key signatures
    G_MAJOR(new Note[]{F_SHARP}),
    E_MINOR(new Note[]{F_SHARP}),

    D_MAJOR(new Note[]{F_SHARP, C_SHARP}),
    B_MINOR(new Note[]{F_SHARP, C_SHARP}),

    A_MAJOR(new Note[]{F_SHARP, C_SHARP, G_SHARP}),
    F_SHARP_MINOR(new Note[]{F_SHARP, C_SHARP, G_SHARP}),

    E_MAJOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),
    C_SHARP_MINOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP}),

    B_MAJOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),
    G_SHARP_MINOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP}),

    F_SHARP_MAJOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),
    D_SHARP_MINOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP}),

    C_SHARP_MAJOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP}),
    A_SHARP_MINOR(new Note[]{F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP});

    final Note[] notes;

    KeySignature(Note[] notes) {
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

    static final KeySignature getMajorKeySignature(Note note) {
        switch(note) {
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
//            case G_SHARP: return F_MINOR;
//            case D_SHARP: return C_MINOR;
//            case A_SHARP: return F_SHARP_MINOR;
//            case E_SHARP: return D_MINOR;
//
//            case B_SHARP: return C_MAJOR;
//
//            case F_FLAT: return C_SHARP_MINOR;

            default: return null;
        }
    }

    static final KeySignature getMinorKeySignature(Note note) {
        switch(note) {
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
//            case D_FLAT: return E_MAJOR;
//            case G_FLAT: return A_MAJOR;
//            case C_FLAT: return D_MAJOR;
//            case F_FLAT: return G_MINOR;
//
//            case B_SHARP: return E_FLAT_MAJOR;
//            case E_SHARP: return A_FLAT_MAJOR;

            default: return null;
        }
    }
}
