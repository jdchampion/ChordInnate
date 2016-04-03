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

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */

@RunWith(Parameterized.class)
public class ChordTest {

    private final boolean PLAYBACK_COMPOUND = false;
    private final boolean PLAYBACK_SEQUENTIAL = false;
    private final int PLAYBACK_VOLUME = 127;
    private final int PLAYBACK_NOTE_ON_DURATION = 50;
    private final int PLAYBACK_WAIT_BETWEEN_NOTES = 100;
    private static final ChordType[] CHORDTYPES_TO_TEST =   /**/ChordType.values();/**/   /**{ChordType.MAJOR};/**/
    private static final NoteType[] NOTETYPES_TO_TEST =     /**/NoteType.values();/**/   /**{NoteType.C};/**/
    private static final Octave[] OCTAVES_TO_TEST =         /**Octave.values();/**/     /**/{Octave.OCTAVE_ONE};/**/

    private Chord chord;

    static MidiChannel[] channels;
    static Synthesizer synthesizer;

    public ChordTest(Chord chord) {
        this.chord = chord;
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
    public static Collection<Chord> data() {
        List<Chord> data = new ArrayList<Chord>();
        for (Octave o : OCTAVES_TO_TEST) {
            for (ChordType ct : CHORDTYPES_TO_TEST) {
                for (NoteType nt : NOTETYPES_TO_TEST) {
                    data.add(new Chord(nt, ct, o));
                }
            }
        }

        return data;
    }

    @Test
    public void testSetNoteOctaves() throws Exception {
        System.out.print(chord.getName() + ": ");
        for(Note n : chord.getNotes()) {
            System.out.print(n.getName() + n.getOctave().number + " ");
        }
        System.out.println();
        Note[] notes = chord.getNotes();
        Chord c = new Chord(chord);
        for (Octave o : OCTAVES_TO_TEST) {
            c.setNoteOctaves(o);

            if (o.height <= chord.octaveRange.height) {
                testSoundChord(chord);

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
    public void testInvert() throws Exception {
        Note[] original = chord.getNotes();

        // Use a copy constructor to manipulate the same Chord
        Chord c = new Chord(chord);

        // Test all inversions / wrap-around to non-inverted state
        for (int i = 0; i < original.length+1; i++) {
            for (int j = 0; j < i; j++) {
                c.invert();
            }

            Note[] inverted = c.getNotes();

            System.out.print(c.name + " : ");
            for (Note m : c.notes) {
                System.out.print(m.getName() + m.getOctave().number + " ");
            }
            System.out.println();

            testSoundChord(c);

            // Ensure that the correct Notes were raised by one octave
            if (i % original.length+1 == 0) {
                for (int j = 0; j < original.length; j++) {
                    assertEquals(original[j].getPitch(), inverted[j].getPitch());
                }
            }
            else {
                for (int j = 0; j < i; j++) {
                    // TODO: math for the Octave differences between original and inverted
                    assertEquals(original[j].getPitch() % 12, inverted[j].getPitch() % 12);
                }
                for (int j = i; j < original.length; j++) {
                    assertEquals(original[j].getPitch(), inverted[j].getPitch());
                }
            }

            c.resetInversion();
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

    private void testSoundChord(Chord chord) {
        if (chord != null) {
            soundChord(chord, PLAYBACK_VOLUME, PLAYBACK_NOTE_ON_DURATION, PLAYBACK_WAIT_BETWEEN_NOTES);
        }
    }

    private void soundChord(Chord chord, int volume, int duration, int wait) {
        if (PLAYBACK_COMPOUND || PLAYBACK_SEQUENTIAL) {
            try {
                Note[] notes = chord.notes;
                if (PLAYBACK_SEQUENTIAL) {
                    int inversionNumber = chord.getInversionNumber();
                    for (int i = 0; i < notes.length; i++) {
                        soundNote(notes[(i + inversionNumber) % notes.length].getPitch(), volume, duration, wait);
                    }
                }
                if (PLAYBACK_COMPOUND) {
                    Thread.sleep(duration);
                    for (Note n : notes) {
                        channels[0].noteOn(n.getPitch(), volume);
                    }
                    Thread.sleep(duration);
                    for (Note n : notes) {
                        channels[0].noteOff(n.getPitch(), volume);
                    }
                    Thread.sleep(wait * 2);
                }
            } catch (InterruptedException ex) {}
        }
    }

    private void soundNote(int midiValue, int volume, int duration, int wait) {
        if (PLAYBACK_COMPOUND || PLAYBACK_SEQUENTIAL) {
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