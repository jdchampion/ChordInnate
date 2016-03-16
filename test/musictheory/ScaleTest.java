package musictheory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */

@RunWith(Parameterized.class)
public class ScaleTest {

    private final boolean PLAYBACK = true;
    private final boolean PLAY_SCALES_UP_DOWN = false;
    private final int PLAYBACK_VOLUME = 127;
    private final int PLAYBACK_DURATION = 120;
    private final int PLAYBACK_WAIT = 0;

    private Scale scale;

    static MidiChannel[] channels;
    static Synthesizer synthesizer;

    public ScaleTest(Scale scale) {
        this.scale = scale;
    }

    @BeforeClass
    public static void setUp() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            Thread.sleep(1000); // To minimize the (annoying) initial sound delay
        }
        catch (MidiUnavailableException ex) {}
        catch (InterruptedException ex) {}
        catch (Exception ex) {}
    }

    @AfterClass
    public static void tearDown() {
        try {
            synthesizer.close();
        }
        catch (Exception ex) {}
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
    public void testScaleAttributes() {
        System.out.print(scale.getName() + ": ");

        NoteType root = scale.getRootNoteType();

        System.out.println(root.name + " " + root.name());

        KeySignature keySignature = scale.getKeySignature();

        System.out.print("Key Signature: " + keySignature + "( ");

        if (keySignature != null) {
            for (NoteType n : keySignature.notes) {
                System.out.print(n.name + " ");
            }
        }

        System.out.println(")");

        // Print the scale notes out, and sound them
        System.out.print("Notes: ");
        Note[] notes = scale.getAscendingNotes();
        for (Note n : notes) {
            if (n != null) {
                System.out.print(n.getName() + " ");
            }
            else System.out.print("_ ");
        }

        System.out.println();

        System.out.print("Steps: ");
        for (Step s : scale.getSteps()) {
            System.out.print(s + " ");
        }

        System.out.println();

        System.out.print("Nashville Numbers: ");
        for (NashvilleNumber nn : scale.getNashvilleNumbers()) {
            System.out.print(nn.getShortName() + " ");
        }
        System.out.println();

        testSoundScale(scale);
    }

    @Test
    public void testSetNoteOctaves() throws Exception {
        Note[] notes = scale.getAscendingNotes();
        for (int i = scale.minOctave; i < scale.maxOctave; i++) {
            scale.setNoteOctaves(i);

            testSoundScale(scale);

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
    }

    @Test
    public void testGetNoteTypeWithRelativePitch() throws Exception {
        ArrayList<NoteType> scaleNoteTypes = new ArrayList<NoteType>(Arrays.asList(scale.noteTypes));
        for (NoteType nt : scaleNoteTypes) {
            assertNotNull(scale.getNoteTypeWithRelativePitch(nt.relativePitch));
        }
    }

    private void testSoundScale(Scale scale) {
        Note[] upNotes = scale.getAscendingNotes();
        Note[] downNotes = scale.getDescendingNotes();

        for (Note n : upNotes) {
            if (n != null) {
                soundNote(n.getRelativePitch(), PLAYBACK_VOLUME, PLAYBACK_WAIT);
            }
        }

        // Top octave note (root)
        Note top = new Note(upNotes[0].getNoteType(), upNotes[0].getOctave()+1);
        soundNote(top.getRelativePitch(), PLAYBACK_VOLUME, PLAYBACK_WAIT);

        if (PLAY_SCALES_UP_DOWN) {
            for (Note n : downNotes) {
                if (n != null) {
                    soundNote(n.getRelativePitch(), PLAYBACK_VOLUME, PLAYBACK_WAIT);
                }
            }
        }
    }

    private void soundNote(int midiValue, int volume, int wait) {
        if (PLAYBACK)
            try {
                Thread.sleep(50);
                channels[0].noteOn(midiValue, volume);
                Thread.sleep(50);
                channels[0].noteOff(midiValue, volume);
                Thread.sleep(wait);
            }
            catch (InterruptedException ex) {}
    }
}