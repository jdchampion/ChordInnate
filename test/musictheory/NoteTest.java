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

    private final boolean PLAYBACK = false;
    private final int PLAYBACK_VOLUME = 127;
    private final int PLAYBACK_NOTE_ON_DURATION = 120;
    private final int PLAYBACK_WAIT_BETWEEN_NOTES = 500;
    private static final NoteType[] NOTETYPES_TO_TEST = NoteType.values();
    private static final Octave[] OCTAVES_TO_TEST = Octave.values();

    static MidiChannel[] channels;
    static Synthesizer synthesizer;

    private Note note;

    private Random random = new Random();

    public NoteTest(Note note) {
        this.note = note;
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
    public static Collection<Note> data() {
        List<Note> data = new ArrayList<Note>();
        for (NoteType nt : NOTETYPES_TO_TEST) {
            for (Octave o : OCTAVES_TO_TEST) {
                data.add(new Note(nt, o));
            }
        }

        return data;
    }

    @Test
    public void testOctave() throws Exception {
        assertFalse(note.getOctave().height < 0);
        soundNote(note.getRelativePitch(), PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
        note.setOctave(note.getOctave());
        assertFalse(note.getOctave().height < 0);
    }

    @Test
    public void testGetRelativePitch() throws Exception {
        int relativePitch = note.getRelativePitch();
        if (note.getOctave().height <= note.getNoteType().maxOctave.height) {
            assertTrue(relativePitch >= 0 && relativePitch < 128);
        }
        else {
            assertFalse(relativePitch >= 0 && relativePitch < 128);
        }
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
        random.setSeed(System.currentTimeMillis());
        other.setOctave(OCTAVES_TO_TEST[random.nextInt(OCTAVES_TO_TEST.length)]);
        int noteOctave = note.getOctave().height, otherOctave = other.getOctave().height;
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

    @Test
    public void testIntervalNotes() {
        for (Interval interval: Interval.values()) {
            System.out.println(interval.toString());
            System.out.println("Short Name: " + interval.getShortName());
            System.out.println("RomanNumeral: " + interval.getRomanNumeralName());
            System.out.println("=======================================");

            soundNote(note.getRelativePitch(), PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
            soundNote(note.getRelativePitch() + interval.relativePitchDistance, PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
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
}