package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 6/16/16.
 */
public final class PlayBack {
    private static MidiChannel[] midiChannels;
    private static Synthesizer synthesizer;

    static {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            midiChannels = synthesizer.getChannels();
            synthesizer.open();
        }
        catch (MidiUnavailableException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public static void restart() {
        try {
            if (!synthesizer.isOpen()) synthesizer.open();
        }
        catch (MidiUnavailableException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public static void stop() {
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

    public static void play(@NotNull Pitch pitch) {
        try {
            int noteNumber = pitch.ABSOLUTE_PITCH;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(1000);
            midiChannels[0].noteOff(noteNumber);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    public static void play(@NotNull Note note) {
        Articulation articulation = note.getArticulation();
        double fullLength = note.getRatio() * 2000; // NOTE: "2000" is the assumed full length (in ms) of a Whole Note, at the given Tempo
        double soundedLength = fullLength
                * (articulation == null ? 1 : articulation.LENGTH_MODIFIER);

        try {
            int noteNumber = note.getPitch().ABSOLUTE_PITCH;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep((long) soundedLength);            // TODO: implement Tempo, TimeSignature, Measure; use Measure.getMillis()
            midiChannels[0].noteOff(noteNumber);
            Thread.sleep((long) fullLength - (long) soundedLength);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
