package musictheory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */

@RunWith(Parameterized.class)
public class NoteTest {

    private Note note;
    private NoteType noteType;
    private int octave;

    private Random random = new Random();

    public NoteTest(NoteType noteType, int octave) {
        this.noteType = noteType;
        this.octave = octave;
        this.note = new Note(noteType);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        for (NoteType nt : NoteType.values()) {
            for (int i = 0; i < 12; i++) {
                data.add(new Object[] {nt, i});
            }
        }

        return data;
    }

    @Test
    public void testOctave() throws Exception {
        note.setOctave(octave);
        assertFalse(note.getOctave() < 0);
    }

    @Test
    public void testGetRelativePitch() throws Exception {
        int relativePitch = note.getRelativePitch();
        assertTrue(relativePitch >= 0 && relativePitch < 128);
    }

    @Test
    public void testGetLetter() throws Exception {
        char letter = note.getLetter();
        assertTrue(letter >= 'A' && letter <= 'G');
    }

    @Test
    public void testGetAccidental() throws Exception {
        Accidental accidental = note.getAccidental();
        assertNotNull(accidental);
    }

    @Test
    public void testHasAccidentalSymbol() throws Exception {
        Accidental accidental = note.getAccidental();
        if (accidental.equals(Accidental.NONE)) {
            assertFalse(note.hasAccidentalSymbol());
        }
        else {
            assertTrue(note.hasAccidentalSymbol());
        }
    }

    @Test
    public void testIsNatural() throws Exception {
        Accidental accidental = note.getAccidental();
        if (accidental.equals(Accidental.NATURAL)) {
            assertTrue(note.isNatural());
        }
        else {
            assertFalse(note.isNatural());
        }
    }

    @Test
    public void testIsDoubleAccidental() throws Exception {
        Accidental accidental = note.getAccidental();
        if (accidental.equals(Accidental.DOUBLE_FLAT)
                || accidental.equals(Accidental.DOUBLE_SHARP)) {
            assertTrue(note.isDoubleAccidental());
        }
        else {
            assertFalse(note.isDoubleAccidental());
        }
    }

    @Test
    public void testCompareTo() throws Exception {
        Note other = new Note(note);
        other.setOctave(random.nextInt(12));
        int noteOctave = note.getOctave(), otherOctave = other.getOctave();
        if (noteOctave < otherOctave) {
            assertTrue(note.compareTo(other) == -1);
        }
        else if (noteOctave == otherOctave) {
            assertTrue(note.compareTo(other) == 0);
        }
        else {
            assertTrue(note.compareTo(other) == 1);
        }
    }
}