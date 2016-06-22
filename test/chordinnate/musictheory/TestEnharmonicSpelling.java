package chordinnate.musictheory;

import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.Accidental.*;
import static chordinnate.musictheory.EnharmonicSpelling.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestEnharmonicSpelling {
    @Test
    public void getLetter() throws Exception {
        assertEquals(Letter.A, A.getLetter());
        assertEquals(Letter.A, A_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.A, A_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.A, A_FLAT.getLetter());
        assertEquals(Letter.A, A_NATURAL.getLetter());
        assertEquals(Letter.A, A_SHARP.getLetter());

        assertEquals(Letter.B, B.getLetter());
        assertEquals(Letter.B, B_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.B, B_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.B, B_FLAT.getLetter());
        assertEquals(Letter.B, B_NATURAL.getLetter());
        assertEquals(Letter.B, B_SHARP.getLetter());

        assertEquals(Letter.C, C.getLetter());
        assertEquals(Letter.C, C_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.C, C_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.C, C_FLAT.getLetter());
        assertEquals(Letter.C, C_NATURAL.getLetter());
        assertEquals(Letter.C, C_SHARP.getLetter());

        assertEquals(Letter.D, D.getLetter());
        assertEquals(Letter.D, D_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.D, D_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.D, D_FLAT.getLetter());
        assertEquals(Letter.D, D_NATURAL.getLetter());
        assertEquals(Letter.D, D_SHARP.getLetter());

        assertEquals(Letter.E, E.getLetter());
        assertEquals(Letter.E, E_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.E, E_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.E, E_FLAT.getLetter());
        assertEquals(Letter.E, E_NATURAL.getLetter());
        assertEquals(Letter.E, E_SHARP.getLetter());

        assertEquals(Letter.F, F.getLetter());
        assertEquals(Letter.F, F_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.F, F_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.F, F_FLAT.getLetter());
        assertEquals(Letter.F, F_NATURAL.getLetter());
        assertEquals(Letter.F, F_SHARP.getLetter());

        assertEquals(Letter.G, G.getLetter());
        assertEquals(Letter.G, G_DOUBLE_FLAT.getLetter());
        assertEquals(Letter.G, G_DOUBLE_SHARP.getLetter());
        assertEquals(Letter.G, G_FLAT.getLetter());
        assertEquals(Letter.G, G_NATURAL.getLetter());
        assertEquals(Letter.G, G_SHARP.getLetter());
    }

    @Test
    public void getAccidental() throws Exception {
        assertEquals(NONE, A.getAccidental());
        assertEquals(DOUBLE_FLAT, A_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, A_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, A_FLAT.getAccidental());
        assertEquals(NATURAL, A_NATURAL.getAccidental());
        assertEquals(SHARP, A_SHARP.getAccidental());

        assertEquals(NONE, B.getAccidental());
        assertEquals(DOUBLE_FLAT, B_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, B_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, B_FLAT.getAccidental());
        assertEquals(NATURAL, B_NATURAL.getAccidental());
        assertEquals(SHARP, B_SHARP.getAccidental());

        assertEquals(NONE, C.getAccidental());
        assertEquals(DOUBLE_FLAT, C_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, C_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, C_FLAT.getAccidental());
        assertEquals(NATURAL, C_NATURAL.getAccidental());
        assertEquals(SHARP, C_SHARP.getAccidental());

        assertEquals(NONE, D.getAccidental());
        assertEquals(DOUBLE_FLAT, D_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, D_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, D_FLAT.getAccidental());
        assertEquals(NATURAL, D_NATURAL.getAccidental());
        assertEquals(SHARP, D_SHARP.getAccidental());

        assertEquals(NONE, E.getAccidental());
        assertEquals(DOUBLE_FLAT, E_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, E_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, E_FLAT.getAccidental());
        assertEquals(NATURAL, E_NATURAL.getAccidental());
        assertEquals(SHARP, E_SHARP.getAccidental());

        assertEquals(NONE, F.getAccidental());
        assertEquals(DOUBLE_FLAT, F_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, F_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, F_FLAT.getAccidental());
        assertEquals(NATURAL, F_NATURAL.getAccidental());
        assertEquals(SHARP, F_SHARP.getAccidental());

        assertEquals(NONE, G.getAccidental());
        assertEquals(DOUBLE_FLAT, G_DOUBLE_FLAT.getAccidental());
        assertEquals(DOUBLE_SHARP, G_DOUBLE_SHARP.getAccidental());
        assertEquals(FLAT, G_FLAT.getAccidental());
        assertEquals(NATURAL, G_NATURAL.getAccidental());
        assertEquals(SHARP, G_SHARP.getAccidental());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("A", A.getName());
        assertEquals("A♭♭", A_DOUBLE_FLAT.getName());
        assertEquals("Ax", A_DOUBLE_SHARP.getName());
        assertEquals("A♭", A_FLAT.getName());
        assertEquals("A♮", A_NATURAL.getName());
        assertEquals("A♯", A_SHARP.getName());

        assertEquals("B", B.getName());
        assertEquals("B♭♭", B_DOUBLE_FLAT.getName());
        assertEquals("Bx", B_DOUBLE_SHARP.getName());
        assertEquals("B♭", B_FLAT.getName());
        assertEquals("B♮", B_NATURAL.getName());
        assertEquals("B♯", B_SHARP.getName());

        assertEquals("C", C.getName());
        assertEquals("C♭♭", C_DOUBLE_FLAT.getName());
        assertEquals("Cx", C_DOUBLE_SHARP.getName());
        assertEquals("C♭", C_FLAT.getName());
        assertEquals("C♮", C_NATURAL.getName());
        assertEquals("C♯", C_SHARP.getName());

        assertEquals("D", D.getName());
        assertEquals("D♭♭", D_DOUBLE_FLAT.getName());
        assertEquals("Dx", D_DOUBLE_SHARP.getName());
        assertEquals("D♭", D_FLAT.getName());
        assertEquals("D♮", D_NATURAL.getName());
        assertEquals("D♯", D_SHARP.getName());

        assertEquals("E", E.getName());
        assertEquals("E♭♭", E_DOUBLE_FLAT.getName());
        assertEquals("Ex", E_DOUBLE_SHARP.getName());
        assertEquals("E♭", E_FLAT.getName());
        assertEquals("E♮", E_NATURAL.getName());
        assertEquals("E♯", E_SHARP.getName());

        assertEquals("F", F.getName());
        assertEquals("F♭♭", F_DOUBLE_FLAT.getName());
        assertEquals("Fx", F_DOUBLE_SHARP.getName());
        assertEquals("F♭", F_FLAT.getName());
        assertEquals("F♮", F_NATURAL.getName());
        assertEquals("F♯", F_SHARP.getName());

        assertEquals("G", G.getName());
        assertEquals("G♭♭", G_DOUBLE_FLAT.getName());
        assertEquals("Gx", G_DOUBLE_SHARP.getName());
        assertEquals("G♭", G_FLAT.getName());
        assertEquals("G♮", G_NATURAL.getName());
        assertEquals("G♯", G_SHARP.getName());
    }

    @Test
    public void getEnharmonicTransitions() throws Exception {
        // All edge cases (8)
        EnharmonicSpelling[]
                expectedForFDoubleFlat = {D_FLAT, E_DOUBLE_FLAT, F_DOUBLE_FLAT, F_DOUBLE_FLAT, F_FLAT, F_NATURAL},
                expectedForFFlat = {E_DOUBLE_FLAT, F_DOUBLE_FLAT, F_FLAT, F_FLAT, F_NATURAL, F_SHARP},
                expectedForBSharp = {B_FLAT, B_NATURAL, B_SHARP, B_SHARP, B_DOUBLE_SHARP, C_DOUBLE_SHARP},
                expectedForBDoubleSharp = {B_NATURAL, B_SHARP, B_DOUBLE_SHARP, B_DOUBLE_SHARP, C_DOUBLE_SHARP, D_SHARP},
                expectedForCDoubleFlat = {A_FLAT, B_DOUBLE_FLAT, C_DOUBLE_FLAT, C_DOUBLE_FLAT, C_FLAT, C_NATURAL},
                expectedForCFlat = {B_DOUBLE_FLAT, C_DOUBLE_FLAT, C_FLAT, C_FLAT, C_NATURAL, C_SHARP},
                expectedForESharp = {E_FLAT, E_NATURAL, E_SHARP, E_SHARP, E_DOUBLE_SHARP, F_DOUBLE_SHARP},
                expectedForEDoubleSharp = {E_NATURAL, E_SHARP, E_DOUBLE_SHARP, E_DOUBLE_SHARP, F_DOUBLE_SHARP, G_SHARP},

                actualForFDoubleFlat = F_DOUBLE_FLAT.getEnharmonicTransitions(),
                actualForFFlat = F_FLAT.getEnharmonicTransitions(),
                actualForBSharp = B_SHARP.getEnharmonicTransitions(),
                actualForBDoubleSharp = B_DOUBLE_SHARP.getEnharmonicTransitions(),
                actualForCDoubleFlat = C_DOUBLE_FLAT.getEnharmonicTransitions(),
                actualForCFlat = C_FLAT.getEnharmonicTransitions(),
                actualForESharp = E_SHARP.getEnharmonicTransitions(),
                actualForEDoubleSharp = E_DOUBLE_SHARP.getEnharmonicTransitions();

        assertArrayEquals(expectedForFDoubleFlat, actualForFDoubleFlat);
        assertArrayEquals(expectedForFFlat, actualForFFlat);
        assertArrayEquals(expectedForBSharp, actualForBSharp);
        assertArrayEquals(expectedForBDoubleSharp, actualForBDoubleSharp);
        assertArrayEquals(expectedForCDoubleFlat, actualForCDoubleFlat);
        assertArrayEquals(expectedForCFlat, actualForCFlat);
        assertArrayEquals(expectedForESharp, actualForESharp);
        assertArrayEquals(expectedForEDoubleSharp, actualForEDoubleSharp);
    }

    @Test
    public void testApply() throws Exception {
        // Testing all for Fbb (edge case 1 of 8)
        assertEquals(D_FLAT, F_DOUBLE_FLAT.apply(DOUBLE_FLAT));
        assertEquals(E_DOUBLE_FLAT, F_DOUBLE_FLAT.apply(FLAT));
        assertEquals(F_DOUBLE_FLAT, F_DOUBLE_FLAT.apply(NATURAL));
        assertEquals(F_DOUBLE_FLAT, F_DOUBLE_FLAT.apply(NONE));
        assertEquals(F_FLAT, F_DOUBLE_FLAT.apply(SHARP));
        assertEquals(F_NATURAL, F_DOUBLE_FLAT.apply(DOUBLE_SHARP));

        // Testing all for Fb (edge case 2 of 8)
        assertEquals(E_DOUBLE_FLAT, F_FLAT.apply(DOUBLE_FLAT));
        assertEquals(F_DOUBLE_FLAT, F_FLAT.apply(FLAT));
        assertEquals(F_FLAT, F_FLAT.apply(NATURAL));
        assertEquals(F_FLAT, F_FLAT.apply(NONE));
        assertEquals(F_NATURAL, F_FLAT.apply(SHARP));
        assertEquals(F_SHARP, F_FLAT.apply(DOUBLE_SHARP));

        // Testing all for B# (edge case 3 of 8)
        assertEquals(B_FLAT, B_SHARP.apply(DOUBLE_FLAT));
        assertEquals(B_NATURAL, B_SHARP.apply(FLAT));
        assertEquals(B_SHARP, B_SHARP.apply(NATURAL));
        assertEquals(B_SHARP, B_SHARP.apply(NONE));
        assertEquals(B_DOUBLE_SHARP, B_SHARP.apply(SHARP));
        assertEquals(C_DOUBLE_SHARP, B_SHARP.apply(DOUBLE_SHARP));

        // Testing all for Bx (edge case 4 of 8)
        assertEquals(B_NATURAL, B_DOUBLE_SHARP.apply(DOUBLE_FLAT));
        assertEquals(B_SHARP, B_DOUBLE_SHARP.apply(FLAT));
        assertEquals(B_DOUBLE_SHARP, B_DOUBLE_SHARP.apply(NATURAL));
        assertEquals(B_DOUBLE_SHARP, B_DOUBLE_SHARP.apply(NONE));
        assertEquals(C_DOUBLE_SHARP, B_DOUBLE_SHARP.apply(SHARP));
        assertEquals(D_SHARP, B_DOUBLE_SHARP.apply(DOUBLE_SHARP));

        // Testing all for Cbb (edge case 5 of 8)
        assertEquals(A_FLAT, C_DOUBLE_FLAT.apply(DOUBLE_FLAT));
        assertEquals(B_DOUBLE_FLAT, C_DOUBLE_FLAT.apply(FLAT));
        assertEquals(C_DOUBLE_FLAT, C_DOUBLE_FLAT.apply(NATURAL));
        assertEquals(C_DOUBLE_FLAT, C_DOUBLE_FLAT.apply(NONE));
        assertEquals(C_FLAT, C_DOUBLE_FLAT.apply(SHARP));
        assertEquals(C_NATURAL, C_DOUBLE_FLAT.apply(DOUBLE_SHARP));

        // Testing all for Cb (edge case 6 of 8)
        assertEquals(B_DOUBLE_FLAT, C_FLAT.apply(DOUBLE_FLAT));
        assertEquals(C_DOUBLE_FLAT, C_FLAT.apply(FLAT));
        assertEquals(C_FLAT, C_FLAT.apply(NATURAL));
        assertEquals(C_FLAT, C_FLAT.apply(NONE));
        assertEquals(C_NATURAL, C_FLAT.apply(SHARP));
        assertEquals(C_SHARP, C_FLAT.apply(DOUBLE_SHARP));

        // Testing all for E# (edge case 7 of 8)
        assertEquals(E_FLAT, E_SHARP.apply(DOUBLE_FLAT));
        assertEquals(E_NATURAL, E_SHARP.apply(FLAT));
        assertEquals(E_SHARP, E_SHARP.apply(NATURAL));
        assertEquals(E_SHARP, E_SHARP.apply(NONE));
        assertEquals(E_DOUBLE_SHARP, E_SHARP.apply(SHARP));
        assertEquals(F_DOUBLE_SHARP, E_SHARP.apply(DOUBLE_SHARP));

        // Testing all for Ex (edge case 8 of 8)
        assertEquals(E_NATURAL, E_DOUBLE_SHARP.apply(DOUBLE_FLAT));
        assertEquals(E_SHARP, E_DOUBLE_SHARP.apply(FLAT));
        assertEquals(E_DOUBLE_SHARP, E_DOUBLE_SHARP.apply(NATURAL));
        assertEquals(E_DOUBLE_SHARP, E_DOUBLE_SHARP.apply(NONE));
        assertEquals(F_DOUBLE_SHARP, E_DOUBLE_SHARP.apply(SHARP));
        assertEquals(G_SHARP, E_DOUBLE_SHARP.apply(DOUBLE_SHARP));
    }

}