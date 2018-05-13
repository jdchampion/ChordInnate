package chordinnate.model.musictheory.pitch.key;

import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.model.musictheory.pitch.key.KeySignature.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestKeySignature {
    @Test
    public void getRelativeKey() throws Exception {
        assertEquals(B_SHARP_MAJOR, G_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(C_MAJOR, A_MINOR.getRelativeKey());
//        assertEquals(D_DOUBLE_FLAT_MAJOR, B_DOUBLE_FLAT_MINOR.getRelativeKey());

        assertEquals(E_SHARP_MAJOR, C_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(F_MAJOR, D_MINOR.getRelativeKey());
//        assertEquals(G_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MINOR.getRelativeKey());

        assertEquals(A_SHARP_MAJOR, F_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(B_FLAT_MAJOR, G_MINOR.getRelativeKey());
//        assertEquals(C_DOUBLE_FLAT_MAJOR, A_DOUBLE_FLAT_MINOR.getRelativeKey());

        assertEquals(D_SHARP_MAJOR, B_SHARP_MINOR.getRelativeKey());
        assertEquals(E_FLAT_MAJOR, C_MINOR.getRelativeKey());
//        assertEquals(F_DOUBLE_FLAT_MAJOR, D_DOUBLE_FLAT_MINOR.getRelativeKey());

        assertEquals(G_SHARP_MAJOR, E_SHARP_MINOR.getRelativeKey());
        assertEquals(A_FLAT_MAJOR, F_MINOR.getRelativeKey());

//        assertEquals(B_DOUBLE_SHARP_MAJOR, G_TRIPLE_SHARP_MINOR.getRelativeKey());      // Too theoretical
        assertEquals(C_SHARP_MAJOR, A_SHARP_MINOR.getRelativeKey());
        assertEquals(D_FLAT_MAJOR, B_FLAT_MINOR.getRelativeKey());

//        assertEquals(E_DOUBLE_SHARP_MAJOR, C_TRIPLE_SHARP_MINOR.getRelativeKey());      // Too theoretical
        assertEquals(F_SHARP_MAJOR, D_SHARP_MINOR.getRelativeKey());
        assertEquals(G_FLAT_MAJOR, E_FLAT_MINOR.getRelativeKey());

//        assertEquals(A_DOUBLE_SHARP_MAJOR, F_TRIPLE_SHARP_MINOR.getRelativeKey());      // Too theoretical
        assertEquals(B_MAJOR, G_SHARP_MINOR.getRelativeKey());
        assertEquals(C_FLAT_MAJOR, A_FLAT_MINOR.getRelativeKey());

//        assertEquals(D_DOUBLE_SHARP_MAJOR, B_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(E_MAJOR, C_SHARP_MINOR.getRelativeKey());
        assertEquals(F_FLAT_MAJOR, D_FLAT_MINOR.getRelativeKey());

//        assertEquals(G_DOUBLE_SHARP_MAJOR, E_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(A_MAJOR, F_SHARP_MINOR.getRelativeKey());
        assertEquals(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR.getRelativeKey());

//        assertEquals(C_DOUBLE_SHARP_MAJOR, A_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(D_MAJOR, B_MINOR.getRelativeKey());
        assertEquals(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR.getRelativeKey());

//        assertEquals(F_DOUBLE_SHARP_MAJOR, D_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(G_MAJOR, E_MINOR.getRelativeKey());
        assertEquals(A_DOUBLE_FLAT_MAJOR, F_FLAT_MINOR.getRelativeKey());





        assertEquals(B_SHARP_MINOR, D_SHARP_MAJOR.getRelativeKey());
        assertEquals(C_MINOR, E_FLAT_MAJOR.getRelativeKey());
//        assertEquals(D_DOUBLE_FLAT_MINOR, F_DOUBLE_FLAT_MAJOR.getRelativeKey());

        assertEquals(E_SHARP_MINOR, G_SHARP_MAJOR.getRelativeKey());
        assertEquals(F_MINOR, A_FLAT_MAJOR.getRelativeKey());
//        assertEquals(G_DOUBLE_FLAT_MINOR, B_TRIPLE_FLAT_MAJOR.getRelativeKey());        // Too theoretical

        assertEquals(A_SHARP_MINOR, C_SHARP_MAJOR.getRelativeKey());
        assertEquals(B_FLAT_MINOR, D_FLAT_MAJOR.getRelativeKey());
//        assertEquals(C_DOUBLE_FLAT_MINOR, E_TRIPLE_FLAT_MAJOR.getRelativeKey());        // Too theoretical

        assertEquals(D_SHARP_MINOR, F_SHARP_MAJOR.getRelativeKey());
        assertEquals(E_FLAT_MINOR, G_FLAT_MAJOR.getRelativeKey());
//        assertEquals(F_DOUBLE_FLAT_MINOR, A_TRIPLE_FLAT_MINOR.getRelativeKey());        // Too theoretical

        assertEquals(G_SHARP_MINOR, B_MAJOR.getRelativeKey());
        assertEquals(A_FLAT_MINOR, C_FLAT_MAJOR.getRelativeKey());

//        assertEquals(B_DOUBLE_SHARP_MINOR, D_DOUBLE_SHARP_MAJOR.getRelativeKey());
        assertEquals(C_SHARP_MINOR, E_MAJOR.getRelativeKey());
        assertEquals(D_FLAT_MINOR, F_FLAT_MAJOR.getRelativeKey());

//        assertEquals(E_DOUBLE_SHARP_MINOR, G_DOUBLE_SHARP_MAJOR.getRelativeKey());
        assertEquals(F_SHARP_MINOR, A_MAJOR.getRelativeKey());
        assertEquals(G_FLAT_MINOR, B_DOUBLE_FLAT_MAJOR.getRelativeKey());

//        assertEquals(A_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MAJOR.getRelativeKey());
        assertEquals(B_MINOR, D_MAJOR.getRelativeKey());
        assertEquals(C_FLAT_MINOR, E_DOUBLE_FLAT_MAJOR.getRelativeKey());

//        assertEquals(D_DOUBLE_SHARP_MINOR, F_DOUBLE_SHARP_MAJOR.getRelativeKey());
        assertEquals(E_MINOR, G_MAJOR.getRelativeKey());
        assertEquals(F_FLAT_MINOR, A_DOUBLE_FLAT_MAJOR.getRelativeKey());

        assertEquals(G_DOUBLE_SHARP_MINOR, B_SHARP_MAJOR.getRelativeKey());
        assertEquals(A_MINOR, C_MAJOR.getRelativeKey());
//        assertEquals(B_DOUBLE_FLAT_MINOR, D_DOUBLE_FLAT_MAJOR.getRelativeKey());

        assertEquals(C_DOUBLE_SHARP_MINOR, E_SHARP_MAJOR.getRelativeKey());
        assertEquals(D_MINOR, F_MAJOR.getRelativeKey());
//        assertEquals(E_DOUBLE_FLAT_MINOR, G_DOUBLE_SHARP_MAJOR.getRelativeKey());

        assertEquals(F_DOUBLE_SHARP_MINOR, A_SHARP_MAJOR.getRelativeKey());
        assertEquals(G_MINOR, B_FLAT_MAJOR.getRelativeKey());
//        assertEquals(A_DOUBLE_FLAT_MINOR, C_DOUBLE_FLAT_MAJOR.getRelativeKey());

    }

    @Test
    public void getParallelKey() throws Exception {
        assertEquals(C_FLAT_MAJOR, C_FLAT_MINOR.getParallelKey());
        assertEquals(C_MAJOR, C_MINOR.getParallelKey());
        assertEquals(C_SHARP_MAJOR, C_SHARP_MINOR.getParallelKey());

        assertEquals(D_FLAT_MAJOR, D_FLAT_MINOR.getParallelKey());
        assertEquals(D_MAJOR, D_MINOR.getParallelKey());
        assertEquals(D_SHARP_MAJOR, D_SHARP_MINOR.getParallelKey());

        assertEquals(E_FLAT_MAJOR, E_FLAT_MINOR.getParallelKey());
        assertEquals(E_MAJOR, E_MINOR.getParallelKey());
        assertEquals(E_SHARP_MAJOR, E_SHARP_MINOR.getParallelKey());

        assertEquals(F_FLAT_MAJOR, F_FLAT_MINOR.getParallelKey());
        assertEquals(F_MAJOR, F_MINOR.getParallelKey());
        assertEquals(F_SHARP_MAJOR, F_SHARP_MINOR.getParallelKey());

        assertEquals(G_FLAT_MAJOR, G_FLAT_MINOR.getParallelKey());
        assertEquals(G_MAJOR, G_MINOR.getParallelKey());
        assertEquals(G_SHARP_MAJOR, G_SHARP_MINOR.getParallelKey());

        assertEquals(A_FLAT_MAJOR, A_FLAT_MINOR.getParallelKey());
        assertEquals(A_MAJOR, A_MINOR.getParallelKey());
        assertEquals(A_SHARP_MAJOR, A_SHARP_MINOR.getParallelKey());

        assertEquals(B_FLAT_MAJOR, B_FLAT_MINOR.getParallelKey());
        assertEquals(B_MAJOR, B_MINOR.getParallelKey());
        assertEquals(B_SHARP_MAJOR, B_SHARP_MINOR.getParallelKey());



        assertEquals(C_FLAT_MINOR, C_FLAT_MAJOR.getParallelKey());
        assertEquals(C_MINOR, C_MAJOR.getParallelKey());
        assertEquals(C_SHARP_MINOR, C_SHARP_MAJOR.getParallelKey());

        assertEquals(D_FLAT_MINOR, D_FLAT_MAJOR.getParallelKey());
        assertEquals(D_MINOR, D_MAJOR.getParallelKey());
        assertEquals(D_SHARP_MINOR, D_SHARP_MAJOR.getParallelKey());

        assertEquals(E_FLAT_MINOR, E_FLAT_MAJOR.getParallelKey());
        assertEquals(E_MINOR, E_MAJOR.getParallelKey());
        assertEquals(E_SHARP_MINOR, E_SHARP_MAJOR.getParallelKey());

        assertEquals(F_FLAT_MINOR, F_FLAT_MAJOR.getParallelKey());
        assertEquals(F_MINOR, F_MAJOR.getParallelKey());
        assertEquals(F_SHARP_MINOR, F_SHARP_MAJOR.getParallelKey());

        assertEquals(G_FLAT_MINOR, G_FLAT_MAJOR.getParallelKey());
        assertEquals(G_MINOR, G_MAJOR.getParallelKey());
        assertEquals(G_SHARP_MINOR, G_SHARP_MAJOR.getParallelKey());

        assertEquals(A_FLAT_MINOR, A_FLAT_MAJOR.getParallelKey());
        assertEquals(A_MINOR, A_MAJOR.getParallelKey());
        assertEquals(A_SHARP_MINOR, A_SHARP_MAJOR.getParallelKey());

        assertEquals(B_FLAT_MINOR, B_FLAT_MAJOR.getParallelKey());
        assertEquals(B_MINOR, B_MAJOR.getParallelKey());
        assertEquals(B_SHARP_MINOR, B_SHARP_MAJOR.getParallelKey());
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
        assertEquals(NO_KEY_SIGNATURE, A_DOUBLE_FLAT_MAJOR.getNext());
        assertEquals(NO_KEY_SIGNATURE, B_SHARP_MAJOR.getNext());
        assertEquals(NO_KEY_SIGNATURE, F_FLAT_MINOR.getNext());
        assertEquals(NO_KEY_SIGNATURE, G_DOUBLE_SHARP_MINOR.getNext());
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

        assertEquals(C_MAJOR, F_MAJOR.getPrevious());
        assertEquals(C_MAJOR, G_MAJOR.getPrevious());
        assertEquals(A_MINOR, D_MINOR.getPrevious());
        assertEquals(A_MINOR, E_MINOR.getPrevious());
    }

}