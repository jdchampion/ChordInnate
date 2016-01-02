package musictheory;

/**
 * Created by Joseph on 1/2/16.
 */
public enum KeySignature {

    // Flat key signatures
    C_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT, Note.C_FLAT, Note.F_FLAT}),
    A_FLAT_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT, Note.C_FLAT, Note.F_FLAT}),

    G_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT, Note.C_FLAT}),
    E_FLAT_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT, Note.C_FLAT}),

    D_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT}),
    B_FLAT_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT, Note.G_FLAT}),

    A_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT}),
    F_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT, Note.D_FLAT}),

    E_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT}),
    C_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT, Note.A_FLAT}),

    B_FLAT_MAJOR(new Note[]{Note.B_FLAT, Note.E_FLAT}),
    G_MINOR(new Note[]{Note.B_FLAT, Note.E_FLAT}),

    F_MAJOR(new Note[]{Note.B_FLAT}),
    D_MINOR(new Note[]{Note.B_FLAT}),

    // Non-accidental key signature
    C_MAJOR(new Note[]{}),
    A_MINOR(new Note[]{}),

    // Sharp key signatures
    G_MAJOR(new Note[]{Note.F_SHARP}),
    E_MINOR(new Note[]{Note.F_SHARP}),

    D_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP}),
    B_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP}),

    A_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP}),
    F_SHARP_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP}),

    E_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP}),
    C_SHARP_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP}),

    B_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP}),
    G_SHARP_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP}),

    F_SHARP_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP, Note.E_SHARP}),
    D_SHARP_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP, Note.E_SHARP}),

    C_SHARP_MAJOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP, Note.E_SHARP, Note.B_SHARP}),
    A_SHARP_MINOR(new Note[]{Note.F_SHARP, Note.C_SHARP, Note.G_SHARP, Note.D_SHARP, Note.A_SHARP, Note.E_SHARP, Note.B_SHARP});

    Note[] notes;

    KeySignature(Note[] notes) {
        this.notes = notes;
    }
}
