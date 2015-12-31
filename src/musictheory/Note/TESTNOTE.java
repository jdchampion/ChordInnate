package musictheory.Note;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 12/29/15.
 */
public class TESTNOTE {
    public static void main(String[] args) {

        NoteFactory nf = new NoteFactory();

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] channels = synthesizer.getChannels();

            String[] allPossibleLetters = {
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

            for (int i = 0; i < allPossibleLetters.length; i++) {
                boolean tmp = false;
                System.out.print("Playing " + allPossibleLetters[i] + "...\t");
                for (int j = 0; j < nf.getNumOctavesFor(allPossibleLetters[i]); j++) {
                    Note n = nf.buildNote(allPossibleLetters[i], j);

                    tmp = n.isAccidental();

                    System.out.print(" " + n.getMidiValue());

                    Thread.sleep(100);
                    channels[0].noteOn(n.getMidiValue(), 127);
                    Thread.sleep(200);
                    channels[0].noteOff(n.getMidiValue());
                }
                System.out.println(" \t(isAccidental: " + tmp + ")");
            }

            synthesizer.close();
        }
        catch (Exception ex) {}
    }
}
