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
        assertEquals(Letter.A, A.letter);
        assertEquals(Letter.A, A_DOUBLE_FLAT.letter);
        assertEquals(Letter.A, A_DOUBLE_SHARP.letter);
        assertEquals(Letter.A, A_FLAT.letter);
        assertEquals(Letter.A, A_NATURAL.letter);
        assertEquals(Letter.A, A_SHARP.letter);

        assertEquals(Letter.B, B.letter);
        assertEquals(Letter.B, B_DOUBLE_FLAT.letter);
        assertEquals(Letter.B, B_DOUBLE_SHARP.letter);
        assertEquals(Letter.B, B_FLAT.letter);
        assertEquals(Letter.B, B_NATURAL.letter);
        assertEquals(Letter.B, B_SHARP.letter);

        assertEquals(Letter.C, C.letter);
        assertEquals(Letter.C, C_DOUBLE_FLAT.letter);
        assertEquals(Letter.C, C_DOUBLE_SHARP.letter);
        assertEquals(Letter.C, C_FLAT.letter);
        assertEquals(Letter.C, C_NATURAL.letter);
        assertEquals(Letter.C, C_SHARP.letter);

        assertEquals(Letter.D, D.letter);
        assertEquals(Letter.D, D_DOUBLE_FLAT.letter);
        assertEquals(Letter.D, D_DOUBLE_SHARP.letter);
        assertEquals(Letter.D, D_FLAT.letter);
        assertEquals(Letter.D, D_NATURAL.letter);
        assertEquals(Letter.D, D_SHARP.letter);

        assertEquals(Letter.E, E.letter);
        assertEquals(Letter.E, E_DOUBLE_FLAT.letter);
        assertEquals(Letter.E, E_DOUBLE_SHARP.letter);
        assertEquals(Letter.E, E_FLAT.letter);
        assertEquals(Letter.E, E_NATURAL.letter);
        assertEquals(Letter.E, E_SHARP.letter);

        assertEquals(Letter.F, F.letter);
        assertEquals(Letter.F, F_DOUBLE_FLAT.letter);
        assertEquals(Letter.F, F_DOUBLE_SHARP.letter);
        assertEquals(Letter.F, F_FLAT.letter);
        assertEquals(Letter.F, F_NATURAL.letter);
        assertEquals(Letter.F, F_SHARP.letter);

        assertEquals(Letter.G, G.letter);
        assertEquals(Letter.G, G_DOUBLE_FLAT.letter);
        assertEquals(Letter.G, G_DOUBLE_SHARP.letter);
        assertEquals(Letter.G, G_FLAT.letter);
        assertEquals(Letter.G, G_NATURAL.letter);
        assertEquals(Letter.G, G_SHARP.letter);
    }

    @Test
    public void testField_ACCIDENTAL() throws Exception {
        assertEquals(NONE, A.accidental);
        assertEquals(DOUBLE_FLAT, A_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, A_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, A_FLAT.accidental);
        assertEquals(NATURAL, A_NATURAL.accidental);
        assertEquals(SHARP, A_SHARP.accidental);

        assertEquals(NONE, B.accidental);
        assertEquals(DOUBLE_FLAT, B_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, B_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, B_FLAT.accidental);
        assertEquals(NATURAL, B_NATURAL.accidental);
        assertEquals(SHARP, B_SHARP.accidental);

        assertEquals(NONE, C.accidental);
        assertEquals(DOUBLE_FLAT, C_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, C_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, C_FLAT.accidental);
        assertEquals(NATURAL, C_NATURAL.accidental);
        assertEquals(SHARP, C_SHARP.accidental);

        assertEquals(NONE, D.accidental);
        assertEquals(DOUBLE_FLAT, D_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, D_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, D_FLAT.accidental);
        assertEquals(NATURAL, D_NATURAL.accidental);
        assertEquals(SHARP, D_SHARP.accidental);

        assertEquals(NONE, E.accidental);
        assertEquals(DOUBLE_FLAT, E_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, E_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, E_FLAT.accidental);
        assertEquals(NATURAL, E_NATURAL.accidental);
        assertEquals(SHARP, E_SHARP.accidental);

        assertEquals(NONE, F.accidental);
        assertEquals(DOUBLE_FLAT, F_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, F_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, F_FLAT.accidental);
        assertEquals(NATURAL, F_NATURAL.accidental);
        assertEquals(SHARP, F_SHARP.accidental);

        assertEquals(NONE, G.accidental);
        assertEquals(DOUBLE_FLAT, G_DOUBLE_FLAT.accidental);
        assertEquals(DOUBLE_SHARP, G_DOUBLE_SHARP.accidental);
        assertEquals(FLAT, G_FLAT.accidental);
        assertEquals(NATURAL, G_NATURAL.accidental);
        assertEquals(SHARP, G_SHARP.accidental);
    }

    @Test
    public void testField_NAME() throws Exception {
        assertEquals("A", A.baseName);
        assertEquals("A𝄫", A_DOUBLE_FLAT.baseName);
        assertEquals("A𝄪", A_DOUBLE_SHARP.baseName);
        assertEquals("A♭", A_FLAT.baseName);
        assertEquals("A♮", A_NATURAL.baseName);
        assertEquals("A♯", A_SHARP.baseName);

        assertEquals("B", B.baseName);
        assertEquals("B𝄫", B_DOUBLE_FLAT.baseName);
        assertEquals("B𝄪", B_DOUBLE_SHARP.baseName);
        assertEquals("B♭", B_FLAT.baseName);
        assertEquals("B♮", B_NATURAL.baseName);
        assertEquals("B♯", B_SHARP.baseName);

        assertEquals("C", C.baseName);
        assertEquals("C𝄫", C_DOUBLE_FLAT.baseName);
        assertEquals("C𝄪", C_DOUBLE_SHARP.baseName);
        assertEquals("C♭", C_FLAT.baseName);
        assertEquals("C♮", C_NATURAL.baseName);
        assertEquals("C♯", C_SHARP.baseName);

        assertEquals("D", D.baseName);
        assertEquals("D𝄫", D_DOUBLE_FLAT.baseName);
        assertEquals("D𝄪", D_DOUBLE_SHARP.baseName);
        assertEquals("D♭", D_FLAT.baseName);
        assertEquals("D♮", D_NATURAL.baseName);
        assertEquals("D♯", D_SHARP.baseName);

        assertEquals("E", E.baseName);
        assertEquals("E𝄫", E_DOUBLE_FLAT.baseName);
        assertEquals("E𝄪", E_DOUBLE_SHARP.baseName);
        assertEquals("E♭", E_FLAT.baseName);
        assertEquals("E♮", E_NATURAL.baseName);
        assertEquals("E♯", E_SHARP.baseName);

        assertEquals("F", F.baseName);
        assertEquals("F𝄫", F_DOUBLE_FLAT.baseName);
        assertEquals("F𝄪", F_DOUBLE_SHARP.baseName);
        assertEquals("F♭", F_FLAT.baseName);
        assertEquals("F♮", F_NATURAL.baseName);
        assertEquals("F♯", F_SHARP.baseName);

        assertEquals("G", G.baseName);
        assertEquals("G𝄫", G_DOUBLE_FLAT.baseName);
        assertEquals("G𝄪", G_DOUBLE_SHARP.baseName);
        assertEquals("G♭", G_FLAT.baseName);
        assertEquals("G♮", G_NATURAL.baseName);
        assertEquals("G♯", G_SHARP.baseName);
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