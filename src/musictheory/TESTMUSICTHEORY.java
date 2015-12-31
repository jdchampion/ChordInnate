package musictheory;

import musictheory.Note.Note;
import musictheory.Note.NoteFactory;

/**
 * Created by Joseph on 12/30/15.
 */
public class TESTMUSICTHEORY {
    public static void main(String[] args) {
        NoteFactory nf = new NoteFactory();
        Note c5 = nf.buildNote("C", 5);
    }
}
