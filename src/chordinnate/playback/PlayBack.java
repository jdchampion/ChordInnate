package chordinnate.playback;

import chordinnate.musictheory.Articulation;
import chordinnate.musictheory.Note;
import chordinnate.musictheory.Pitch;

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
            for (MidiChannel midiChannel: midiChannels) {
                midiChannel.allSoundOff();
            }
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
        double soundedLength = note.getRatio()
                * (articulation == null ? 1 : (articulation.getDelay() - articulation.getAttack()))
                * 4000; // NOTE: "4000" is the assumed full length (in ms) of a Whole Note, at the given Tempo

        try {
            int noteNumber = note.getPitch().getAbsolutePitch();
            midiChannels[0].noteOn(noteNumber, 127);
            System.out.println((long) soundedLength);
            Thread.sleep((long) soundedLength);            // TODO: implement Tempo, TimeSignature, Measure; use Measure.getMillis()
            midiChannels[0].noteOff(noteNumber);
            Thread.sleep((long) (note.getRatio() * 4000) - (long) soundedLength);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
