import musictheory.*;
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
 * Created by Joseph on 3/18/16.
 */

public class PackageTest {

    static MidiChannel[] channels;
    static Synthesizer synthesizer;

    public PackageTest() {}

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

    @Test
    public void testPackage() throws Exception {
        Note note = new Note(NoteType.C, Octave.OCTAVE_FIVE);
        Scale scale = new Scale(NoteType.C, ScaleType.MAJOR);
        Chord chord = new Chord(NoteType.C, ChordType.MAJOR);
    }
}
