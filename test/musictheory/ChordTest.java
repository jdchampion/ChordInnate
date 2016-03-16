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

    private final boolean PLAYBACK = false;
    private final int PLAYBACK_VOLUME = 127;
    private final int PLAYBACK_NOTE_ON_DURATION = 120;
    private final int PLAYBACK_WAIT_BETWEEN_NOTES = 100;

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
        Chord c1 = new Chord(chord), c2 = new Chord(chord);
        Note[] n1 = c1.getNotes(), n2 = c2.getNotes();
        int oct1, oct2;
        for (int i = chord.minOctave; i < chord.maxOctave; i++) {
            c1.setNoteOctaves(i);
            c2.setOctave(i);

            testSoundChord(c1);
            testSoundChord(c2);

            for (int j = 0; j < notes.length; j++) {
                oct1 = n1[j].getOctave();
                oct2 = n2[j].getOctave();
                assertEquals(oct1, oct2);
                assertTrue(oct1 >= chord.minOctave && oct1 <= chord.maxOctave);
                assertTrue(oct2 >= chord.minOctave && oct2 <= chord.maxOctave);
            }
        }
    }

    @Test
    public void testInvert() throws Exception {
        Note[] notes = chord.getNotes();
        Chord c = new Chord(chord);

        // Test all inversions / wrap-around to non-inverted state
        for (int i = 0; i < notes.length+1; i++) {
            for (int j = 0; j < i; j++) {
                c.invert();
            }

            System.out.print(c.name + " : ");
            for (Note m : c.notes) {
                System.out.print(m.getName() + m.getOctave() + " ");
            }
            System.out.println();

            testSoundChord(c);

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
        if (PLAYBACK) {
            try {
                Note[] notes = chord.notes;
                for (Note n : notes) {
                    channels[0].noteOn(n.getRelativePitch(), volume);
                }
                Thread.sleep(duration);
                for (Note n : notes) {
                    channels[0].noteOff(n.getRelativePitch(), volume);
                }
                Thread.sleep(wait);
            } catch (InterruptedException ex) {}
        }
    }
}