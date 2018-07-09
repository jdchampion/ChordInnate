package chordinnate.model.musictheory.notation;

import org.junit.Test;

import static chordinnate.model.musictheory.notation.Accidental.*;
import static chordinnate.model.musictheory.notation.BaseEnharmonicSpelling.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestBaseEnharmonicSpelling {
    @Test
    public void testField_LETTER() throws Exception {
        assertEquals(Letter.A, A.LETTER);
        assertEquals(Letter.A, A_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.A, A_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.A, A_FLAT.LETTER);
        assertEquals(Letter.A, A_NATURAL.LETTER);
        assertEquals(Letter.A, A_SHARP.LETTER);

        assertEquals(Letter.B, B.LETTER);
        assertEquals(Letter.B, B_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.B, B_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.B, B_FLAT.LETTER);
        assertEquals(Letter.B, B_NATURAL.LETTER);
        assertEquals(Letter.B, B_SHARP.LETTER);

        assertEquals(Letter.C, C.LETTER);
        assertEquals(Letter.C, C_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.C, C_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.C, C_FLAT.LETTER);
        assertEquals(Letter.C, C_NATURAL.LETTER);
        assertEquals(Letter.C, C_SHARP.LETTER);

        assertEquals(Letter.D, D.LETTER);
        assertEquals(Letter.D, D_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.D, D_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.D, D_FLAT.LETTER);
        assertEquals(Letter.D, D_NATURAL.LETTER);
        assertEquals(Letter.D, D_SHARP.LETTER);

        assertEquals(Letter.E, E.LETTER);
        assertEquals(Letter.E, E_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.E, E_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.E, E_FLAT.LETTER);
        assertEquals(Letter.E, E_NATURAL.LETTER);
        assertEquals(Letter.E, E_SHARP.LETTER);

        assertEquals(Letter.F, F.LETTER);
        assertEquals(Letter.F, F_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.F, F_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.F, F_FLAT.LETTER);
        assertEquals(Letter.F, F_NATURAL.LETTER);
        assertEquals(Letter.F, F_SHARP.LETTER);

        assertEquals(Letter.G, G.LETTER);
        assertEquals(Letter.G, G_DOUBLE_FLAT.LETTER);
        assertEquals(Letter.G, G_DOUBLE_SHARP.LETTER);
        assertEquals(Letter.G, G_FLAT.LETTER);
        assertEquals(Letter.G, G_NATURAL.LETTER);
        assertEquals(Letter.G, G_SHARP.LETTER);
    }

    @Test
    public void testField_ACCIDENTAL() throws Exception {
        assertEquals(NONE, A.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, A_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, A_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, A_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, A_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, A_SHARP.ACCIDENTAL);

        assertEquals(NONE, B.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, B_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, B_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, B_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, B_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, B_SHARP.ACCIDENTAL);

        assertEquals(NONE, C.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, C_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, C_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, C_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, C_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, C_SHARP.ACCIDENTAL);

        assertEquals(NONE, D.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, D_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, D_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, D_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, D_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, D_SHARP.ACCIDENTAL);

        assertEquals(NONE, E.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, E_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, E_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, E_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, E_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, E_SHARP.ACCIDENTAL);

        assertEquals(NONE, F.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, F_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, F_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, F_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, F_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, F_SHARP.ACCIDENTAL);

        assertEquals(NONE, G.ACCIDENTAL);
        assertEquals(DOUBLE_FLAT, G_DOUBLE_FLAT.ACCIDENTAL);
        assertEquals(DOUBLE_SHARP, G_DOUBLE_SHARP.ACCIDENTAL);
        assertEquals(FLAT, G_FLAT.ACCIDENTAL);
        assertEquals(NATURAL, G_NATURAL.ACCIDENTAL);
        assertEquals(SHARP, G_SHARP.ACCIDENTAL);
    }

    @Test
    public void testField_NAME() throws Exception {
        assertEquals("A", A.NAME);
        assertEquals("A𝄫", A_DOUBLE_FLAT.NAME);
        assertEquals("A𝄪", A_DOUBLE_SHARP.NAME);
        assertEquals("A♭", A_FLAT.NAME);
        assertEquals("A♮", A_NATURAL.NAME);
        assertEquals("A♯", A_SHARP.NAME);

        assertEquals("B", B.NAME);
        assertEquals("B𝄫", B_DOUBLE_FLAT.NAME);
        assertEquals("B𝄪", B_DOUBLE_SHARP.NAME);
        assertEquals("B♭", B_FLAT.NAME);
        assertEquals("B♮", B_NATURAL.NAME);
        assertEquals("B♯", B_SHARP.NAME);

        assertEquals("C", C.NAME);
        assertEquals("C𝄫", C_DOUBLE_FLAT.NAME);
        assertEquals("C𝄪", C_DOUBLE_SHARP.NAME);
        assertEquals("C♭", C_FLAT.NAME);
        assertEquals("C♮", C_NATURAL.NAME);
        assertEquals("C♯", C_SHARP.NAME);

        assertEquals("D", D.NAME);
        assertEquals("D𝄫", D_DOUBLE_FLAT.NAME);
        assertEquals("D𝄪", D_DOUBLE_SHARP.NAME);
        assertEquals("D♭", D_FLAT.NAME);
        assertEquals("D♮", D_NATURAL.NAME);
        assertEquals("D♯", D_SHARP.NAME);

        assertEquals("E", E.NAME);
        assertEquals("E𝄫", E_DOUBLE_FLAT.NAME);
        assertEquals("E𝄪", E_DOUBLE_SHARP.NAME);
        assertEquals("E♭", E_FLAT.NAME);
        assertEquals("E♮", E_NATURAL.NAME);
        assertEquals("E♯", E_SHARP.NAME);

        assertEquals("F", F.NAME);
        assertEquals("F𝄫", F_DOUBLE_FLAT.NAME);
        assertEquals("F𝄪", F_DOUBLE_SHARP.NAME);
        assertEquals("F♭", F_FLAT.NAME);
        assertEquals("F♮", F_NATURAL.NAME);
        assertEquals("F♯", F_SHARP.NAME);

        assertEquals("G", G.NAME);
        assertEquals("G𝄫", G_DOUBLE_FLAT.NAME);
        assertEquals("G𝄪", G_DOUBLE_SHARP.NAME);
        assertEquals("G♭", G_FLAT.NAME);
        assertEquals("G♮", G_NATURAL.NAME);
        assertEquals("G♯", G_SHARP.NAME);
    }

    @Test
    public void getEnharmonicTransitions() throws Exception {
        // All edge cases (8)
        BaseEnharmonicSpelling[]
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