package chordinnate.musictheory.pitch.notation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 5/21/16.
 */
public class TestLetter {
    @Test
    public void getLetterByVectorTraversal() throws Exception {
        // Left edge case: C (forwards)
        assertEquals(Letter.C, Letter.C.getLetterByVectorTraversal(0));
        assertEquals(Letter.D, Letter.C.getLetterByVectorTraversal(1));
        assertEquals(Letter.E, Letter.C.getLetterByVectorTraversal(2));
        assertEquals(Letter.F, Letter.C.getLetterByVectorTraversal(3));
        assertEquals(Letter.G, Letter.C.getLetterByVectorTraversal(4));
        assertEquals(Letter.A, Letter.C.getLetterByVectorTraversal(5));
        assertEquals(Letter.B, Letter.C.getLetterByVectorTraversal(6));
        assertEquals(Letter.C, Letter.C.getLetterByVectorTraversal(7));

        // Left edge case: C (backwards)
        assertEquals(Letter.B, Letter.C.getLetterByVectorTraversal(-1));
        assertEquals(Letter.A, Letter.C.getLetterByVectorTraversal(-2));
        assertEquals(Letter.G, Letter.C.getLetterByVectorTraversal(-3));
        assertEquals(Letter.F, Letter.C.getLetterByVectorTraversal(-4));
        assertEquals(Letter.E, Letter.C.getLetterByVectorTraversal(-5));
        assertEquals(Letter.D, Letter.C.getLetterByVectorTraversal(-6));
        assertEquals(Letter.C, Letter.C.getLetterByVectorTraversal(-7));

        // Right edge case: B (forwards)
        assertEquals(Letter.B, Letter.B.getLetterByVectorTraversal(0));
        assertEquals(Letter.C, Letter.B.getLetterByVectorTraversal(1));
        assertEquals(Letter.D, Letter.B.getLetterByVectorTraversal(2));
        assertEquals(Letter.E, Letter.B.getLetterByVectorTraversal(3));
        assertEquals(Letter.F, Letter.B.getLetterByVectorTraversal(4));
        assertEquals(Letter.G, Letter.B.getLetterByVectorTraversal(5));
        assertEquals(Letter.A, Letter.B.getLetterByVectorTraversal(6));
        assertEquals(Letter.B, Letter.B.getLetterByVectorTraversal(7));

        // Right edge case: B (backwards)
        assertEquals(Letter.A, Letter.B.getLetterByVectorTraversal(-1));
        assertEquals(Letter.G, Letter.B.getLetterByVectorTraversal(-2));
        assertEquals(Letter.F, Letter.B.getLetterByVectorTraversal(-3));
        assertEquals(Letter.E, Letter.B.getLetterByVectorTraversal(-4));
        assertEquals(Letter.D, Letter.B.getLetterByVectorTraversal(-5));
        assertEquals(Letter.C, Letter.B.getLetterByVectorTraversal(-6));
        assertEquals(Letter.B, Letter.B.getLetterByVectorTraversal(-7));

        // Middle case: F (forwards)
        assertEquals(Letter.F, Letter.F.getLetterByVectorTraversal(0));
        assertEquals(Letter.G, Letter.F.getLetterByVectorTraversal(1));
        assertEquals(Letter.A, Letter.F.getLetterByVectorTraversal(2));
        assertEquals(Letter.B, Letter.F.getLetterByVectorTraversal(3));
        assertEquals(Letter.C, Letter.F.getLetterByVectorTraversal(4));
        assertEquals(Letter.D, Letter.F.getLetterByVectorTraversal(5));
        assertEquals(Letter.E, Letter.F.getLetterByVectorTraversal(6));
        assertEquals(Letter.F, Letter.F.getLetterByVectorTraversal(7));

        // Middle case: F (backwards)
        assertEquals(Letter.E, Letter.F.getLetterByVectorTraversal(-1));
        assertEquals(Letter.D, Letter.F.getLetterByVectorTraversal(-2));
        assertEquals(Letter.C, Letter.F.getLetterByVectorTraversal(-3));
        assertEquals(Letter.B, Letter.F.getLetterByVectorTraversal(-4));
        assertEquals(Letter.A, Letter.F.getLetterByVectorTraversal(-5));
        assertEquals(Letter.G, Letter.F.getLetterByVectorTraversal(-6));
        assertEquals(Letter.F, Letter.F.getLetterByVectorTraversal(-7));
    }

}