package musictheory.Note;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 12/29/15.
 */
public class TESTNOTE {

    // Toggle for hearing Midi playback
    static final boolean PLAYBACK = true;

    static final String[] allPossibleLetters = {
            "B#", "C", "Dbb",
            "C#", "Db",
            "Cx", "D", "Ebb",
            "D#", "Eb", "Fbb",
            "Dx", "E", "Fb",
            "E#", "F", "Gbb",
            "Ex", "F#", "Gb",
            "Fx", "G", "Abb",
            "G#", "Ab",
            "Gx", "A", "Bbb",
            "A#", "Bb", "Cbb",
            "Ax", "B", "Cb"
    };

    static MidiChannel[] channels;

    static boolean accidental;

    public static void main(String[] args) {

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            // TODO: Tests performed here
            testAscendingNotes(PLAYBACK);

            testDescendingNotes(PLAYBACK);

            synthesizer.close();
        }
        catch (Exception ex) {}
    }

    private static void soundNote(int midiValue, int volume) {
        try {
            Thread.sleep(100);
            channels[0].noteOn(midiValue, volume);
            Thread.sleep(200);
            channels[0].noteOff(midiValue, volume);
        }
        catch (Exception x) {}
    }

    private static void testAscendingNotes(boolean soundNotes) {
        for (int i = 0; i < allPossibleLetters.length; i++) {
            System.out.print("Playing " + allPossibleLetters[i] + "...\n");
            for (int j = 0; j < NoteFactory.getNumOctavesFor(allPossibleLetters[i]); j++) {
                Note n = NoteFactory.buildNote(allPossibleLetters[i]);

                n.changeOctave(j);

                accidental = n.isAccidental();

                System.out.print(" " + n.getMidiValue());

                if (soundNotes) soundNote(n.getMidiValue(), 127);

                System.out.println(" \t(isAccidental: " + accidental + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testDescendingNotes(boolean soundNotes) {
        for (int i = 0; i < allPossibleLetters.length; i++) {
            System.out.print("Playing " + allPossibleLetters[i] + "...\n");
            for (int j = NoteFactory.getNumOctavesFor(allPossibleLetters[i]); j >= 0; j--) {
                Note n = NoteFactory.buildNote(allPossibleLetters[i]);

                n.changeOctave(j);

                accidental = n.isAccidental();

                System.out.print(" " + n.getMidiValue());

                if (soundNotes) soundNote(n.getMidiValue(), 127);

                System.out.println(" \t(isAccidental: " + accidental + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }
}
