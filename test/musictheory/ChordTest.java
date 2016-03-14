package musictheory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */

@RunWith(Parameterized.class)
public class ChordTest {

    private Chord chord;

    public ChordTest(Chord chord) {
        this.chord = chord;
    }

    @Parameterized.Parameters
    public static Collection<Chord> data() {
        List<Chord> data = new ArrayList<Chord>();
        for (ChordType ct : ChordType.values()) {
            for (NoteType nt : NoteType.values()) {
                data.add(new Chord(nt, ct));
            }
        }

        return data;
    }

    @Test
    public void testSetNoteOctaves() throws Exception {
        Note[] notes = chord.getNotes();

        System.out.println(chord.getName());

        for (int i = chord.minOctave; i < chord.maxOctave; i++) {
            chord.setNoteOctaves(i);
            // TODO: Ensure that the lowest note >= chord.minOctave && highest note <= chord.maxOctave
//            assertTrue(chord.getRootNote().getOctave() >= chord.minOctave);
        }
    }

    @Test
    public void testInvert() throws Exception {
        Note[] notes = chord.getNotes();

        // Test all inversions / wrap-around to non-inverted state
        for (int i = 0; i < notes.length+1; i++) {
            Chord c = new Chord(chord);
            for (int j = 0; j < i; j++) {
                c.invert();
            }

            // Ensure that the correct Notes were raised by one octave
            Note[] inverted = c.getNotes();
            if (i % notes.length == 0) {
                for (int j = 0; j < notes.length; j++) {
                    assertEquals(notes[j].getRelativePitch(), inverted[j].getRelativePitch());
                }
            }
            else {
                for (int j = 0; j < i; j++) {
                    assertEquals(notes[j].getRelativePitch() + 12, inverted[j].getRelativePitch());
                }
                for (int j = i; j < notes.length; j++) {
                    assertEquals(notes[j].getRelativePitch(), inverted[j].getRelativePitch());
                }
            }
        }
    }

    @Test
    public void testGetChordType() throws Exception {
        assertNotNull(chord.getChordType());
    }

    @Test
    public void testGetInversionNumber() throws Exception {
        int inversionNumber = chord.getInversionNumber();
        assertNotNull(inversionNumber);
        assertFalse(inversionNumber < 0);
    }
}