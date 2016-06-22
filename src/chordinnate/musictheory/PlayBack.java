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
            synthesizer.open();
        }
        catch (MidiUnavailableException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public void stop() {
        try {
            midiChannels[0].allSoundOff();
            synthesizer.close();
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public void play(Pitch pitch) {
        try {
            int noteNumber = pitch.getAbsolutePitch();
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(1000);
            midiChannels[0].noteOff(noteNumber);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public void play(Note note) {
        Articulation articulation = note.getArticulation();
        double soundedLength = note.getTotalLength()
                * (articulation == null ? 1 : (articulation.getDelay() - articulation.getAttack()));

        try {
            int noteNumber = note.getPitch().getAbsolutePitch();
            midiChannels[0].noteOn(noteNumber, 127);
            System.out.println((long) (soundedLength * 1000));      // NOTE: "1000" is the assumed length (in ms) of the entire measure
            Thread.sleep((long) (soundedLength * 1000));            // TODO: implement Tempo, TimeSignature, Measure; use Measure.getMillis()
            midiChannels[0].noteOff(noteNumber);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
