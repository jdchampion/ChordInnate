package musictheory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */
public class TheoryTest {

    @Test
    public void testGetEnharmonicEquivalents() throws Exception {

    }

    @Test
    public void testGetNoteLetterForNashvilleNumber() throws Exception {

    }

    @Test
    public void testGetPreviousNoteLetter() throws Exception {

    }

    @Test
    public void testGetNextNoteLetter() throws Exception {

    }

    @Test
    public void testNoteLettersFollow() throws Exception {

    }

    @Test
    public void testApplyAccidentalTo() throws Exception {

        // TODO: this test currently requires visual cross-referencing

        for (NoteType nt : NoteType.values()) {
            System.out.println(nt.name + ":\n===================");
            for (Accidental a : Accidental.values()) {
                System.out.println(a.name() + ": " + Theory.applyAccidentalTo(nt, a).name);
            }
            System.out.println("\n===================");
        }
    }

    @Test
    public void testTranspose() throws Exception {

    }

    @Test
    public void testTranspose1() throws Exception {

    }

    @Test
    public void testGetChordTypeDiatonicsForScaleType() throws Exception {

    }
}