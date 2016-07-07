package chordinnate.musictheory.pitch.notation;

import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.pitch.notation.KeySignature.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestKeySignature {
    @Test
    public void getKey() throws Exception {

    }

    @Test
    public void getKeySignatureType() throws Exception {

    }

    @Test
    public void contains() throws Exception {

    }

    @Test
    public void getRelativeMajorTo() throws Exception {
        assertEquals(B_SHARP_MAJOR, B_SHARP_MAJOR.getRelativeMajor());
        assertEquals(B_SHARP_MAJOR, G_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(C_MAJOR, C_MAJOR.getRelativeMajor());
        assertEquals(C_MAJOR, A_MINOR.getRelativeMajor());
//        assertEquals(D_DOUBLE_FLAT_MAJOR, D_DOUBLE_FLAT_MAJOR.getRelativeMajor());
//        assertEquals(D_DOUBLE_FLAT_MAJOR, B_DOUBLE_FLAT_MINOR.getRelativeMajor());

        assertEquals(E_SHARP_MAJOR, E_SHARP_MAJOR.getRelativeMajor());
        assertEquals(E_SHARP_MAJOR, C_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(F_MAJOR, F_MAJOR.getRelativeMajor());
        assertEquals(F_MAJOR, D_MINOR.getRelativeMajor());
//        assertEquals(G_DOUBLE_FLAT_MAJOR, G_DOUBLE_FLAT_MAJOR.getRelativeMajor());
//        assertEquals(G_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MINOR.getRelativeMajor());

        assertEquals(A_SHARP_MAJOR, A_SHARP_MAJOR.getRelativeMajor());
        assertEquals(A_SHARP_MAJOR, F_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(B_FLAT_MAJOR, B_FLAT_MAJOR.getRelativeMajor());
        assertEquals(B_FLAT_MAJOR, G_MINOR.getRelativeMajor());
//        assertEquals(C_DOUBLE_FLAT_MAJOR, C_DOUBLE_FLAT_MAJOR.getRelativeMajor());
//        assertEquals(C_DOUBLE_FLAT_MAJOR, A_DOUBLE_FLAT_MINOR.getRelativeMajor());

        assertEquals(D_SHARP_MAJOR, D_SHARP_MAJOR.getRelativeMajor());
        assertEquals(D_SHARP_MAJOR, B_SHARP_MINOR.getRelativeMajor());
        assertEquals(E_FLAT_MAJOR, E_FLAT_MAJOR.getRelativeMajor());
        assertEquals(E_FLAT_MAJOR, C_MINOR.getRelativeMajor());
//        assertEquals(F_DOUBLE_FLAT_MAJOR, F_DOUBLE_FLAT_MAJOR.getRelativeMajor());
//        assertEquals(F_DOUBLE_FLAT_MAJOR, D_DOUBLE_FLAT_MINOR.getRelativeMajor());

        assertEquals(G_SHARP_MAJOR, G_SHARP_MAJOR.getRelativeMajor());
        assertEquals(G_SHARP_MAJOR, E_SHARP_MINOR.getRelativeMajor());
        assertEquals(A_FLAT_MAJOR, A_FLAT_MAJOR.getRelativeMajor());
        assertEquals(A_FLAT_MAJOR, F_MINOR.getRelativeMajor());

//        assertEquals(B_DOUBLE_SHARP_MAJOR, B_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(B_DOUBLE_SHARP_MAJOR, G_TRIPLE_SHARP_MINOR.getRelativeMajor());      // Too theoretical
        assertEquals(C_SHARP_MAJOR, C_SHARP_MAJOR.getRelativeMajor());
        assertEquals(C_SHARP_MAJOR, A_SHARP_MINOR.getRelativeMajor());
        assertEquals(D_FLAT_MAJOR, D_FLAT_MAJOR.getRelativeMajor());
        assertEquals(D_FLAT_MAJOR, B_FLAT_MINOR.getRelativeMajor());

//        assertEquals(E_DOUBLE_SHARP_MAJOR, E_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(E_DOUBLE_SHARP_MAJOR, C_TRIPLE_SHARP_MINOR.getRelativeMajor());      // Too theoretical
        assertEquals(F_SHARP_MAJOR, F_SHARP_MAJOR.getRelativeMajor());
        assertEquals(F_SHARP_MAJOR, D_SHARP_MINOR.getRelativeMajor());
        assertEquals(G_FLAT_MAJOR, G_FLAT_MAJOR.getRelativeMajor());
        assertEquals(G_FLAT_MAJOR, E_FLAT_MINOR.getRelativeMajor());

//        assertEquals(A_DOUBLE_SHARP_MAJOR, A_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(A_DOUBLE_SHARP_MAJOR, F_TRIPLE_SHARP_MINOR.getRelativeMajor());      // Too theoretical
        assertEquals(B_MAJOR, B_MAJOR.getRelativeMajor());
        assertEquals(B_MAJOR, G_SHARP_MINOR.getRelativeMajor());
        assertEquals(C_FLAT_MAJOR, C_FLAT_MAJOR.getRelativeMajor());
        assertEquals(C_FLAT_MAJOR, A_FLAT_MINOR.getRelativeMajor());

//        assertEquals(D_DOUBLE_SHARP_MAJOR, D_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(D_DOUBLE_SHARP_MAJOR, B_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(E_MAJOR, E_MAJOR.getRelativeMajor());
        assertEquals(E_MAJOR, C_SHARP_MINOR.getRelativeMajor());
        assertEquals(F_FLAT_MAJOR, F_FLAT_MAJOR.getRelativeMajor());
        assertEquals(F_FLAT_MAJOR, D_FLAT_MINOR.getRelativeMajor());

//        assertEquals(G_DOUBLE_SHARP_MAJOR, G_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(G_DOUBLE_SHARP_MAJOR, E_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(A_MAJOR, A_MAJOR.getRelativeMajor());
        assertEquals(A_MAJOR, F_SHARP_MINOR.getRelativeMajor());
        assertEquals(B_DOUBLE_FLAT_MAJOR, B_DOUBLE_FLAT_MAJOR.getRelativeMajor());
        assertEquals(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR.getRelativeMajor());

//        assertEquals(C_DOUBLE_SHARP_MAJOR, C_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(C_DOUBLE_SHARP_MAJOR, A_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(D_MAJOR, D_MAJOR.getRelativeMajor());
        assertEquals(D_MAJOR, B_MINOR.getRelativeMajor());
        assertEquals(E_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MAJOR.getRelativeMajor());
        assertEquals(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR.getRelativeMajor());

//        assertEquals(F_DOUBLE_SHARP_MAJOR, F_DOUBLE_SHARP_MAJOR.getRelativeMajor());
//        assertEquals(F_DOUBLE_SHARP_MAJOR, D_DOUBLE_SHARP_MINOR.getRelativeMajor());
        assertEquals(G_MAJOR, G_MAJOR.getRelativeMajor());
        assertEquals(G_MAJOR, E_MINOR.getRelativeMajor());
        assertEquals(A_DOUBLE_FLAT_MAJOR, A_DOUBLE_FLAT_MAJOR.getRelativeMajor());
        assertEquals(A_DOUBLE_FLAT_MAJOR, F_FLAT_MINOR.getRelativeMajor());
    }

    @Test
    public void getRelativeMinorTo() throws Exception {
        assertEquals(B_SHARP_MINOR, B_SHARP_MINOR.getRelativeMinor());
        assertEquals(B_SHARP_MINOR, D_SHARP_MAJOR.getRelativeMinor());
        assertEquals(C_MINOR, C_MINOR.getRelativeMinor());
        assertEquals(C_MINOR, E_FLAT_MAJOR.getRelativeMinor());
//        assertEquals(D_DOUBLE_FLAT_MINOR, D_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(D_DOUBLE_FLAT_MINOR, F_DOUBLE_FLAT_MAJOR.getRelativeMinor());

        assertEquals(E_SHARP_MINOR, E_SHARP_MINOR.getRelativeMinor());
        assertEquals(E_SHARP_MINOR, G_SHARP_MAJOR.getRelativeMinor());
        assertEquals(F_MINOR, F_MINOR.getRelativeMinor());
        assertEquals(F_MINOR, A_FLAT_MAJOR.getRelativeMinor());
//        assertEquals(G_DOUBLE_FLAT_MINOR, G_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(G_DOUBLE_FLAT_MINOR, B_TRIPLE_FLAT_MAJOR.getRelativeMinor());        // Too theoretical

        assertEquals(A_SHARP_MINOR, A_SHARP_MINOR.getRelativeMinor());
        assertEquals(A_SHARP_MINOR, C_SHARP_MAJOR.getRelativeMinor());
        assertEquals(B_FLAT_MINOR, B_FLAT_MINOR.getRelativeMinor());
        assertEquals(B_FLAT_MINOR, D_FLAT_MAJOR.getRelativeMinor());
//        assertEquals(C_DOUBLE_FLAT_MINOR, C_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(C_DOUBLE_FLAT_MINOR, E_TRIPLE_FLAT_MAJOR.getRelativeMinor());        // Too theoretical

        assertEquals(D_SHARP_MINOR, D_SHARP_MINOR.getRelativeMinor());
        assertEquals(D_SHARP_MINOR, F_SHARP_MAJOR.getRelativeMinor());
        assertEquals(E_FLAT_MINOR, E_FLAT_MINOR.getRelativeMinor());
        assertEquals(E_FLAT_MINOR, G_FLAT_MAJOR.getRelativeMinor());
//        assertEquals(F_DOUBLE_FLAT_MINOR, F_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(F_DOUBLE_FLAT_MINOR, A_TRIPLE_FLAT_MINOR.getRelativeMinor());        // Too theoretical

        assertEquals(G_SHARP_MINOR, G_SHARP_MINOR.getRelativeMinor());
        assertEquals(G_SHARP_MINOR, B_MAJOR.getRelativeMinor());
        assertEquals(A_FLAT_MINOR, A_FLAT_MINOR.getRelativeMinor());
        assertEquals(A_FLAT_MINOR, C_FLAT_MAJOR.getRelativeMinor());

//        assertEquals(B_DOUBLE_SHARP_MINOR, B_DOUBLE_SHARP_MINOR.getRelativeMinor());
//        assertEquals(B_DOUBLE_SHARP_MINOR, D_DOUBLE_SHARP_MAJOR.getRelativeMinor());
        assertEquals(C_SHARP_MINOR, C_SHARP_MINOR.getRelativeMinor());
        assertEquals(C_SHARP_MINOR, E_MAJOR.getRelativeMinor());
        assertEquals(D_FLAT_MINOR, D_FLAT_MINOR.getRelativeMinor());
        assertEquals(D_FLAT_MINOR, F_FLAT_MAJOR.getRelativeMinor());

//        assertEquals(E_DOUBLE_SHARP_MINOR, E_DOUBLE_SHARP_MINOR.getRelativeMinor());
//        assertEquals(E_DOUBLE_SHARP_MINOR, G_DOUBLE_SHARP_MAJOR.getRelativeMinor());
        assertEquals(F_SHARP_MINOR, F_SHARP_MINOR.getRelativeMinor());
        assertEquals(F_SHARP_MINOR, A_MAJOR.getRelativeMinor());
        assertEquals(G_FLAT_MINOR, G_FLAT_MINOR.getRelativeMinor());
        assertEquals(G_FLAT_MINOR, B_DOUBLE_FLAT_MAJOR.getRelativeMinor());

//        assertEquals(A_DOUBLE_SHARP_MINOR, A_DOUBLE_SHARP_MINOR.getRelativeMinor());
//        assertEquals(A_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MAJOR.getRelativeMinor());
        assertEquals(B_MINOR, B_MINOR.getRelativeMinor());
        assertEquals(B_MINOR, D_MAJOR.getRelativeMinor());
        assertEquals(C_FLAT_MINOR, C_FLAT_MINOR.getRelativeMinor());
        assertEquals(C_FLAT_MINOR, E_DOUBLE_FLAT_MAJOR.getRelativeMinor());

//        assertEquals(D_DOUBLE_SHARP_MINOR, D_DOUBLE_SHARP_MINOR.getRelativeMinor());
//        assertEquals(D_DOUBLE_SHARP_MINOR, F_DOUBLE_SHARP_MAJOR.getRelativeMinor());
        assertEquals(E_MINOR, E_MINOR.getRelativeMinor());
        assertEquals(E_MINOR, G_MAJOR.getRelativeMinor());
        assertEquals(F_FLAT_MINOR, F_FLAT_MINOR.getRelativeMinor());
        assertEquals(F_FLAT_MINOR, A_DOUBLE_FLAT_MAJOR.getRelativeMinor());

        assertEquals(G_DOUBLE_SHARP_MINOR, G_DOUBLE_SHARP_MINOR.getRelativeMinor());
        assertEquals(G_DOUBLE_SHARP_MINOR, B_SHARP_MAJOR.getRelativeMinor());
        assertEquals(A_MINOR, A_MINOR.getRelativeMinor());
        assertEquals(A_MINOR, C_MAJOR.getRelativeMinor());
//        assertEquals(B_DOUBLE_FLAT_MINOR, B_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(B_DOUBLE_FLAT_MINOR, D_DOUBLE_FLAT_MAJOR.getRelativeMinor());

        assertEquals(C_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MINOR.getRelativeMinor());
        assertEquals(C_DOUBLE_SHARP_MINOR, E_SHARP_MAJOR.getRelativeMinor());
        assertEquals(D_MINOR, D_MINOR.getRelativeMinor());
        assertEquals(D_MINOR, F_MAJOR.getRelativeMinor());
//        assertEquals(E_DOUBLE_FLAT_MINOR, E_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(E_DOUBLE_FLAT_MINOR, G_DOUBLE_SHARP_MAJOR.getRelativeMinor());

        assertEquals(F_DOUBLE_SHARP_MINOR, F_DOUBLE_SHARP_MINOR.getRelativeMinor());
        assertEquals(F_DOUBLE_SHARP_MINOR, A_SHARP_MAJOR.getRelativeMinor());
        assertEquals(G_MINOR, G_MINOR.getRelativeMinor());
        assertEquals(G_MINOR, B_FLAT_MAJOR.getRelativeMinor());
//        assertEquals(A_DOUBLE_FLAT_MINOR, A_DOUBLE_FLAT_MINOR.getRelativeMinor());
//        assertEquals(A_DOUBLE_FLAT_MINOR, C_DOUBLE_FLAT_MAJOR.getRelativeMinor());
    }

    @Test
    public void getParallelMajorTo() throws Exception {
        assertEquals(C_FLAT_MAJOR, C_FLAT_MAJOR.getParallelMajor());
        assertEquals(C_FLAT_MAJOR, C_FLAT_MINOR.getParallelMajor());
        assertEquals(C_MAJOR, C_MAJOR.getParallelMajor());
        assertEquals(C_MAJOR, C_MINOR.getParallelMajor());
        assertEquals(C_SHARP_MAJOR, C_SHARP_MAJOR.getParallelMajor());
        assertEquals(C_SHARP_MAJOR, C_SHARP_MINOR.getParallelMajor());

        assertEquals(D_FLAT_MAJOR, D_FLAT_MAJOR.getParallelMajor());
        assertEquals(D_FLAT_MAJOR, D_FLAT_MINOR.getParallelMajor());
        assertEquals(D_MAJOR, D_MAJOR.getParallelMajor());
        assertEquals(D_MAJOR, D_MINOR.getParallelMajor());
        assertEquals(D_SHARP_MAJOR, D_SHARP_MAJOR.getParallelMajor());
        assertEquals(D_SHARP_MAJOR, D_SHARP_MINOR.getParallelMajor());

        assertEquals(E_FLAT_MAJOR, E_FLAT_MAJOR.getParallelMajor());
        assertEquals(E_FLAT_MAJOR, E_FLAT_MINOR.getParallelMajor());
        assertEquals(E_MAJOR, E_MAJOR.getParallelMajor());
        assertEquals(E_MAJOR, E_MINOR.getParallelMajor());
        assertEquals(E_SHARP_MAJOR, E_SHARP_MAJOR.getParallelMajor());
        assertEquals(E_SHARP_MAJOR, E_SHARP_MINOR.getParallelMajor());

        assertEquals(F_FLAT_MAJOR, F_FLAT_MAJOR.getParallelMajor());
        assertEquals(F_FLAT_MAJOR, F_FLAT_MINOR.getParallelMajor());
        assertEquals(F_MAJOR, F_MAJOR.getParallelMajor());
        assertEquals(F_MAJOR, F_MINOR.getParallelMajor());
        assertEquals(F_SHARP_MAJOR, F_SHARP_MAJOR.getParallelMajor());
        assertEquals(F_SHARP_MAJOR, F_SHARP_MINOR.getParallelMajor());

        assertEquals(G_FLAT_MAJOR, G_FLAT_MAJOR.getParallelMajor());
        assertEquals(G_FLAT_MAJOR, G_FLAT_MINOR.getParallelMajor());
        assertEquals(G_MAJOR, G_MAJOR.getParallelMajor());
        assertEquals(G_MAJOR, G_MINOR.getParallelMajor());
        assertEquals(G_SHARP_MAJOR, G_SHARP_MAJOR.getParallelMajor());
        assertEquals(G_SHARP_MAJOR, G_SHARP_MINOR.getParallelMajor());

        assertEquals(A_FLAT_MAJOR, A_FLAT_MAJOR.getParallelMajor());
        assertEquals(A_FLAT_MAJOR, A_FLAT_MINOR.getParallelMajor());
        assertEquals(A_MAJOR, A_MAJOR.getParallelMajor());
        assertEquals(A_MAJOR, A_MINOR.getParallelMajor());
        assertEquals(A_SHARP_MAJOR, A_SHARP_MAJOR.getParallelMajor());
        assertEquals(A_SHARP_MAJOR, A_SHARP_MINOR.getParallelMajor());

        assertEquals(B_FLAT_MAJOR, B_FLAT_MAJOR.getParallelMajor());
        assertEquals(B_FLAT_MAJOR, B_FLAT_MINOR.getParallelMajor());
        assertEquals(B_MAJOR, B_MAJOR.getParallelMajor());
        assertEquals(B_MAJOR, B_MINOR.getParallelMajor());
        assertEquals(B_SHARP_MAJOR, B_SHARP_MAJOR.getParallelMajor());
        assertEquals(B_SHARP_MAJOR, B_SHARP_MINOR.getParallelMajor());
    }

    @Test
    public void getParallelMinorTo() throws Exception {
        assertEquals(C_FLAT_MINOR, C_FLAT_MINOR.getParallelMinor());
        assertEquals(C_FLAT_MINOR, C_FLAT_MAJOR.getParallelMinor());
        assertEquals(C_MINOR, C_MINOR.getParallelMinor());
        assertEquals(C_MINOR, C_MAJOR.getParallelMinor());
        assertEquals(C_SHARP_MINOR, C_SHARP_MINOR.getParallelMinor());
        assertEquals(C_SHARP_MINOR, C_SHARP_MAJOR.getParallelMinor());

        assertEquals(D_FLAT_MINOR, D_FLAT_MINOR.getParallelMinor());
        assertEquals(D_FLAT_MINOR, D_FLAT_MAJOR.getParallelMinor());
        assertEquals(D_MINOR, D_MINOR.getParallelMinor());
        assertEquals(D_MINOR, D_MAJOR.getParallelMinor());
        assertEquals(D_SHARP_MINOR, D_SHARP_MINOR.getParallelMinor());
        assertEquals(D_SHARP_MINOR, D_SHARP_MAJOR.getParallelMinor());

        assertEquals(E_FLAT_MINOR, E_FLAT_MINOR.getParallelMinor());
        assertEquals(E_FLAT_MINOR, E_FLAT_MAJOR.getParallelMinor());
        assertEquals(E_MINOR, E_MINOR.getParallelMinor());
        assertEquals(E_MINOR, E_MAJOR.getParallelMinor());
        assertEquals(E_SHARP_MINOR, E_SHARP_MINOR.getParallelMinor());
        assertEquals(E_SHARP_MINOR, E_SHARP_MAJOR.getParallelMinor());

        assertEquals(F_FLAT_MINOR, F_FLAT_MINOR.getParallelMinor());
        assertEquals(F_FLAT_MINOR, F_FLAT_MAJOR.getParallelMinor());
        assertEquals(F_MINOR, F_MINOR.getParallelMinor());
        assertEquals(F_MINOR, F_MAJOR.getParallelMinor());
        assertEquals(F_SHARP_MINOR, F_SHARP_MINOR.getParallelMinor());
        assertEquals(F_SHARP_MINOR, F_SHARP_MAJOR.getParallelMinor());

        assertEquals(G_FLAT_MINOR, G_FLAT_MINOR.getParallelMinor());
        assertEquals(G_FLAT_MINOR, G_FLAT_MAJOR.getParallelMinor());
        assertEquals(G_MINOR, G_MINOR.getParallelMinor());
        assertEquals(G_MINOR, G_MAJOR.getParallelMinor());
        assertEquals(G_SHARP_MINOR, G_SHARP_MINOR.getParallelMinor());
        assertEquals(G_SHARP_MINOR, G_SHARP_MAJOR.getParallelMinor());

        assertEquals(A_FLAT_MINOR, A_FLAT_MINOR.getParallelMinor());
        assertEquals(A_FLAT_MINOR, A_FLAT_MAJOR.getParallelMinor());
        assertEquals(A_MINOR, A_MINOR.getParallelMinor());
        assertEquals(A_MINOR, A_MAJOR.getParallelMinor());
        assertEquals(A_SHARP_MINOR, A_SHARP_MINOR.getParallelMinor());
        assertEquals(A_SHARP_MINOR, A_SHARP_MAJOR.getParallelMinor());

        assertEquals(B_FLAT_MINOR, B_FLAT_MINOR.getParallelMinor());
        assertEquals(B_FLAT_MINOR, B_FLAT_MAJOR.getParallelMinor());
        assertEquals(B_MINOR, B_MINOR.getParallelMinor());
        assertEquals(B_MINOR, B_MAJOR.getParallelMinor());
        assertEquals(B_SHARP_MINOR, B_SHARP_MINOR.getParallelMinor());
        assertEquals(B_SHARP_MINOR, B_SHARP_MAJOR.getParallelMinor());
    }

    @Test
    public void getNext() throws Exception {
        /*
         * Empty KeySignatures have no next KeySignature,
         * because they have an ambiguous choice whether to
         * advance by sharps or flats with getNext().
         *
         * Basically, getNext() can't read your mind.
         */
        assertEquals(NO_KEY_SIGNATURE, C_MAJOR.getNext());
        assertEquals(NO_KEY_SIGNATURE, A_MINOR.getNext());

        // Basic test cases
        assertEquals(B_FLAT_MAJOR, F_MAJOR.getNext());
        assertEquals(E_FLAT_MAJOR, B_FLAT_MAJOR.getNext());
        assertEquals(A_FLAT_MAJOR, E_FLAT_MAJOR.getNext());
        assertEquals(D_FLAT_MAJOR, A_FLAT_MAJOR.getNext());
        assertEquals(G_FLAT_MAJOR, D_FLAT_MAJOR.getNext());
        assertEquals(C_FLAT_MAJOR, G_FLAT_MAJOR.getNext());
        assertEquals(F_FLAT_MAJOR, C_FLAT_MAJOR.getNext());
        assertEquals(B_DOUBLE_FLAT_MAJOR, F_FLAT_MAJOR.getNext());
        assertEquals(E_DOUBLE_FLAT_MAJOR, B_DOUBLE_FLAT_MAJOR.getNext());
        assertEquals(A_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MAJOR.getNext());
        assertEquals(D_MAJOR, G_MAJOR.getNext());
        assertEquals(A_MAJOR, D_MAJOR.getNext());
        assertEquals(E_MAJOR, A_MAJOR.getNext());
        assertEquals(B_MAJOR, E_MAJOR.getNext());
        assertEquals(F_SHARP_MAJOR, B_MAJOR.getNext());
        assertEquals(C_SHARP_MAJOR, F_SHARP_MAJOR.getNext());
        assertEquals(G_SHARP_MAJOR, C_SHARP_MAJOR.getNext());
        assertEquals(D_SHARP_MAJOR, G_SHARP_MAJOR.getNext());
        assertEquals(A_SHARP_MAJOR, D_SHARP_MAJOR.getNext());
        assertEquals(E_SHARP_MAJOR, A_SHARP_MAJOR.getNext());
        assertEquals(B_SHARP_MAJOR, E_SHARP_MAJOR.getNext());

        assertEquals(G_MINOR, D_MINOR.getNext());
        assertEquals(C_MINOR, G_MINOR.getNext());
        assertEquals(F_MINOR, C_MINOR.getNext());
        assertEquals(B_FLAT_MINOR, F_MINOR.getNext());
        assertEquals(E_FLAT_MINOR, B_FLAT_MINOR.getNext());
        assertEquals(A_FLAT_MINOR, E_FLAT_MINOR.getNext());
        assertEquals(D_FLAT_MINOR, A_FLAT_MINOR.getNext());
        assertEquals(G_FLAT_MINOR, D_FLAT_MINOR.getNext());
        assertEquals(C_FLAT_MINOR, G_FLAT_MINOR.getNext());
        assertEquals(F_FLAT_MINOR, C_FLAT_MINOR.getNext());
        assertEquals(B_MINOR, E_MINOR.getNext());
        assertEquals(F_SHARP_MINOR, B_MINOR.getNext());
        assertEquals(C_SHARP_MINOR, F_SHARP_MINOR.getNext());
        assertEquals(G_SHARP_MINOR, C_SHARP_MINOR.getNext());
        assertEquals(D_SHARP_MINOR, G_SHARP_MINOR.getNext());
        assertEquals(A_SHARP_MINOR, D_SHARP_MINOR.getNext());
        assertEquals(E_SHARP_MINOR, A_SHARP_MINOR.getNext());
        assertEquals(B_SHARP_MINOR, E_SHARP_MINOR.getNext());
        assertEquals(F_DOUBLE_SHARP_MINOR, B_SHARP_MINOR.getNext());
        assertEquals(C_DOUBLE_SHARP_MINOR, F_DOUBLE_SHARP_MINOR.getNext());
        assertEquals(G_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MINOR.getNext());


        /*
         * Edge cases should return null (i.e., no wrap-around possible)
         * because there would be an ambiguous choice of the following:
         *
         * -    continue adding sharps or flats
         *      (with extreme theoretical KeySignatures as the result,
         *       and ultimately impossible due to finite enum values)
         *
         * -    simply advance by ordinal, regardless of Accidental
         *      (unwanted in nearly all cases because it's not musically 'smart')
         *
         * -    wrap around to the first KeySignature with the matching Accidental
         *      (which isn't technically the "next" KeySignature that would be derived)
         */
        assertNull(A_DOUBLE_FLAT_MAJOR.getNext());
        assertNull(B_SHARP_MAJOR.getNext());
        assertNull(F_FLAT_MINOR.getNext());
        assertNull(G_DOUBLE_SHARP_MINOR.getNext());
    }

    @Test
    public void getPrevious() throws Exception {
        /*
         * Empty KeySignatures have no next KeySignature,
         * because they have an ambiguous choice whether to
         * advance by sharps or flats with getPrevious().
         *
         * Basically, getPrevious() can't read your mind.
         */
        assertEquals(NO_KEY_SIGNATURE, C_MAJOR.getPrevious());
        assertEquals(NO_KEY_SIGNATURE, A_MINOR.getPrevious());

        // Basic test cases
        assertEquals(F_MAJOR, B_FLAT_MAJOR.getPrevious());
        assertEquals(B_FLAT_MAJOR, E_FLAT_MAJOR.getPrevious());
        assertEquals(E_FLAT_MAJOR, A_FLAT_MAJOR.getPrevious());
        assertEquals(A_FLAT_MAJOR, D_FLAT_MAJOR.getPrevious());
        assertEquals(D_FLAT_MAJOR, G_FLAT_MAJOR.getPrevious());
        assertEquals(G_FLAT_MAJOR, C_FLAT_MAJOR.getPrevious());
        assertEquals(C_FLAT_MAJOR, F_FLAT_MAJOR.getPrevious());
        assertEquals(F_FLAT_MAJOR, B_DOUBLE_FLAT_MAJOR.getPrevious());
        assertEquals(B_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MAJOR.getPrevious());
        assertEquals(E_DOUBLE_FLAT_MAJOR, A_DOUBLE_FLAT_MAJOR.getPrevious());
        assertEquals(G_MAJOR, D_MAJOR.getPrevious());
        assertEquals(D_MAJOR, A_MAJOR.getPrevious());
        assertEquals(A_MAJOR, E_MAJOR.getPrevious());
        assertEquals(E_MAJOR, B_MAJOR.getPrevious());
        assertEquals(B_MAJOR, F_SHARP_MAJOR.getPrevious());
        assertEquals(F_SHARP_MAJOR, C_SHARP_MAJOR.getPrevious());
        assertEquals(C_SHARP_MAJOR, G_SHARP_MAJOR.getPrevious());
        assertEquals(G_SHARP_MAJOR, D_SHARP_MAJOR.getPrevious());
        assertEquals(D_SHARP_MAJOR, A_SHARP_MAJOR.getPrevious());
        assertEquals(A_SHARP_MAJOR, E_SHARP_MAJOR.getPrevious());
        assertEquals(E_SHARP_MAJOR, B_SHARP_MAJOR.getPrevious());

        assertEquals(D_MINOR, G_MINOR.getPrevious());
        assertEquals(G_MINOR, C_MINOR.getPrevious());
        assertEquals(C_MINOR, F_MINOR.getPrevious());
        assertEquals(F_MINOR, B_FLAT_MINOR.getPrevious());
        assertEquals(B_FLAT_MINOR, E_FLAT_MINOR.getPrevious());
        assertEquals(E_FLAT_MINOR, A_FLAT_MINOR.getPrevious());
        assertEquals(A_FLAT_MINOR, D_FLAT_MINOR.getPrevious());
        assertEquals(D_FLAT_MINOR, G_FLAT_MINOR.getPrevious());
        assertEquals(G_FLAT_MINOR, C_FLAT_MINOR.getPrevious());
        assertEquals(C_FLAT_MINOR, F_FLAT_MINOR.getPrevious());
        assertEquals(E_MINOR, B_MINOR.getPrevious());
        assertEquals(B_MINOR, F_SHARP_MINOR.getPrevious());
        assertEquals(F_SHARP_MINOR, C_SHARP_MINOR.getPrevious());
        assertEquals(C_SHARP_MINOR, G_SHARP_MINOR.getPrevious());
        assertEquals(G_SHARP_MINOR, D_SHARP_MINOR.getPrevious());
        assertEquals(D_SHARP_MINOR, A_SHARP_MINOR.getPrevious());
        assertEquals(A_SHARP_MINOR, E_SHARP_MINOR.getPrevious());
        assertEquals(E_SHARP_MINOR, B_SHARP_MINOR.getPrevious());
        assertEquals(B_SHARP_MINOR, F_DOUBLE_SHARP_MINOR.getPrevious());
        assertEquals(F_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MINOR.getPrevious());
        assertEquals(C_DOUBLE_SHARP_MINOR, G_DOUBLE_SHARP_MINOR.getPrevious());

        /*
         * Edge cases should return null (i.e., no wrap-around possible)
         * because there would be an ambiguous choice of the following:
         *
         * -    continue adding sharps or flats
         *      (with extreme theoretical KeySignatures as the result,
         *       and ultimately impossible due to finite enum values)
         *
         * -    simply advance by ordinal, regardless of Accidental
         *      (unwanted in nearly all cases because it's not musically 'smart')
         *
         * -    wrap around to the first KeySignature with the matching Accidental
         *      (which isn't technically the "previous" KeySignature that would be derived)
         */
        assertNull(F_MAJOR.getPrevious());
        assertNull(G_MAJOR.getPrevious());
        assertNull(D_MINOR.getPrevious());
        assertNull(E_MINOR.getPrevious());
    }

}