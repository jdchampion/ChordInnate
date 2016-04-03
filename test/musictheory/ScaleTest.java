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

    private final boolean PLAYBACK = false;
    private final boolean PLAY_SCALES_UP_DOWN = false;
    private final int PLAYBACK_VOLUME = 127;
    private final int PLAYBACK_NOTE_ON_DURATION = 100;
    private final int PLAYBACK_WAIT_BETWEEN_NOTES = 0;
    private static final ScaleType[] SCALETYPES_TO_TEST =   /**ScaleType.values();/**/      /**/{ScaleType.MAJOR};/**/
    private static final NoteType[] NOTETYPES_TO_TEST =     /**/NoteType.values();/**/      /**{NoteType.C};/**/
    private static final Octave[] OCTAVES_TO_TEST =         /**/Octave.values();/**/         /**{Octave.FOUR};/**/

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
        for (ScaleType st : SCALETYPES_TO_TEST) {
            for (NoteType nt : NOTETYPES_TO_TEST) {
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

        for (Octave o : OCTAVES_TO_TEST) {
            scale = new Scale(scale.getRootNoteType(), scale.getScaleType(), o);
            if (o.height <= scale.octaveRange.height) {
                testSoundScale(scale);
            }
        }
    }

    @Test
    public void testScaleDiatonicChords() throws Exception {
        Map<Integer, ArrayList<ChordType>> hm = scale.getDiatonicChordTypesByRelativePitch();

        int index = 0;
        for (Integer i : hm.keySet()) {
            System.out.print(i + ": ");
            Collection<ChordType> chordTypeArrayList = hm.get(i);
            for (ChordType ct : chordTypeArrayList) {
                System.out.print(ct.chordSymbol + " ");
                Chord c = new Chord(scale.noteTypes[index], ct);
                testSoundChord(c);
            }
            System.out.println();
            index++;
        }
    }

    @Test
    public void testSetNoteOctaves() throws Exception {
        Note[] notes = scale.getAscendingNotes();
        for (Octave o : OCTAVES_TO_TEST) {
            scale.setNoteOctaves(o);

            if (o.height <= scale.octaveRange.height) {
                testSoundScale(scale);

                // Ascending notes should have increasing pitch
                for (int j = 1; j < notes.length; j++) {
                    if (notes[j - 1].getOctave().ordinal() > notes[j].getOctave().ordinal()) {
                        assertFalse(notes[j - 1].getPitch() < notes[j].getPitch());
                    }
                    else {
                        assertTrue(notes[j - 1].getPitch() < notes[j].getPitch());
                    }
                }
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

        System.out.print(upNotes[0].getOctave() + ": ");

        for (Note n : upNotes) {
            if (n != null) {
//                System.out.print(n.getName() + n.getOctave().number + " ");
                System.out.print(n.getPitch() + " ");
                soundNote(n.getPitch(), PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
            }
        }

        System.out.println();

        // Top octave note (root)
        Note top = new Note(upNotes[0].getNoteType(), upNotes[0].getOctave().raiseBy(1));
        soundNote(top.getPitch(), PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);

        if (PLAY_SCALES_UP_DOWN) {
            for (Note n : downNotes) {
                if (n != null) {
                    soundNote(n.getPitch(), PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
                }
            }
        }
    }

    private void soundNote(int midiValue, int volume, int duration, int wait) {
        if (PLAYBACK) {
            try {
                Thread.sleep(duration);
                channels[0].noteOn(midiValue, volume);
                Thread.sleep(duration);
                channels[0].noteOff(midiValue, volume);
                Thread.sleep(wait);
            } catch (InterruptedException ex) {}
        }
    }

    private void testSoundChord(Chord chord) {
        if (chord != null) {
            soundChord(chord, PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
        }
    }

    private void soundChord(Chord chord, int volume, int duration, int wait) {
        if (PLAYBACK) {
            try {
                Note[] notes = chord.notes;
                for (Note n : notes) {
                    channels[0].noteOn(n.getPitch(), volume);
                }
                Thread.sleep(duration);
                for (Note n : notes) {
                    channels[0].noteOff(n.getPitch(), volume);
                }
                Thread.sleep(wait);
            } catch (InterruptedException ex) {}
        }
    }
}