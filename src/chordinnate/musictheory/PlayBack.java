package chordinnate.musictheory;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 6/16/16.
 */
public class PlayBack {
    private static MidiChannel[] midiChannels;
    private static Synthesizer synthesizer;

    public PlayBack() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            midiChannels = synthesizer.getChannels();
        }
        catch (MidiUnavailableException ex) {}
        catch (Exception ex) {}
    }

    public void play(Pitch pitch) {
        try {
            synthesizer.open();
            int noteNumber = pitch.getAbsolutePitch();
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(1000);
            midiChannels[0].noteOff(noteNumber);
            synthesizer.close();
        }
        catch (MidiUnavailableException ex) {}
        catch (Exception ex) {}
    }

    public void play(Note note) {
        Articulation articulation = note.getArticulation();
        double soundedLength = note.getTotalLength()
                * (articulation == null ? 1 : (articulation.getDelay() - articulation.getAttack()));

        try {
            synthesizer.open();
            int noteNumber = note.getPitch().getAbsolutePitch();
            midiChannels[0].noteOn(noteNumber, 127);
            System.out.println((long) (soundedLength * 1000));
            Thread.sleep((long) (soundedLength * 1000));
            midiChannels[0].noteOff(noteNumber);
            synthesizer.close();
        }
        catch (MidiUnavailableException ex) {}
        catch (Exception ex) {}
    }
}
