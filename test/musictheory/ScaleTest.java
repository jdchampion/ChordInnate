package musictheory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */

@RunWith(Parameterized.class)
public class ScaleTest {

    private Scale scale;

    public ScaleTest(Scale scale) {
        this.scale = scale;
    }

    @Parameterized.Parameters
    public static Collection<Scale> data() {
        List<Scale> data = new ArrayList<Scale>();
        for (ScaleType st : ScaleType.values()) {
            for (NoteType nt : NoteType.values()) {
                data.add(new Scale(nt, st));
            }
        }

        return data;
    }

    @Test
    public void testSetNoteOctaves() throws Exception {
        Note[] notes = scale.getAscendingNotes();
        for (int i = scale.minOctave; i < scale.maxOctave; i++) {
            scale.setNoteOctaves(i);

            // Ascending notes should have increasing relative pitch
            for (int j = 1; j < notes.length; j++) {
                assertTrue(notes[j - 1].getRelativePitch() < notes[j].getRelativePitch());
            }
        }
    }

    @Test
    public void testSetDiatonicChordTypes() throws Exception {
        Set diatonicChordTypes = scale.getDiatonicChordTypes();
        assertNotNull(diatonicChordTypes);
        assertFalse(diatonicChordTypes.isEmpty());

    }

    @Test
    public void testGetScaleType() throws Exception {
        assertNotNull(scale.getScaleType());
    }

    @Test
    public void testGetKeySignature() throws Exception {
        assertNotNull(scale.getKeySignature());
    }

    @Test
    public void testGetSteps() throws Exception {
        Step[] steps = scale.getSteps();
        assertNotNull(steps);
        assertEquals(scale.getNotes().length - 1, steps.length);
    }

    @Test
    public void testGetAscendingNotes() throws Exception {
        Note[] ascendingNotes = scale.getAscendingNotes();
        assertNotNull(ascendingNotes);
        assertEquals(scale.getNotes().length, ascendingNotes.length);
    }

    @Test
    public void testGetDescendingNotes() throws Exception {
        Note[] descendingNotes = scale.getDescendingNotes();
        assertNotNull(descendingNotes);
        assertEquals(scale.getNotes().length, descendingNotes.length);
    }

    @Test
    public void testGetNashvilleNumbers() throws Exception {
        NashvilleNumber[] nashvilleNumbers = scale.getNashvilleNumbers();
        assertNotNull(nashvilleNumbers);
    }

    @Test
    public void testGetDiatonicChordTypes() throws Exception {
        Set diatonicChordTypes = scale.getDiatonicChordTypes();
        assertNotNull(diatonicChordTypes);
    }

    @Test
    public void testGetDiatonicChordTypesByRelativePitch() throws Exception {
        HashMap<Integer, ArrayList<ChordType>> diatonicChordTypesByRelativePitch = scale.getDiatonicChordTypesByRelativePitch();
        assertNotNull(diatonicChordTypesByRelativePitch);
        Set diatonicChordTypes = scale.getDiatonicChordTypes();
        assertTrue(diatonicChordTypes.containsAll(diatonicChordTypes));
    }

    @Test
    public void testGetNoteTypeWithRelativePitch() throws Exception {

    }
}