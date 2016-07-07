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
        long fullLength = (long) (note.getFullLength() * 500); // NOTE: "500" is the assumed full length (in ms) of a Quarter Note, at the given Tempo
        long soundedLength = (long) (note.getSoundedLength() * 500);
        long difference = fullLength - soundedLength;

        try {
            Articulation articulation = note.getArticulation();
            System.out.println(
                    (articulation == null ? "" : (articulation + " ")) +
                            note.getPitch() + " " + note.getBeat() + " at tempo = 120 bpm:");
            System.out.println("Full length: " + fullLength + " ms");
            System.out.println("Sounded length: " + soundedLength + " ms");
            int noteNumber = note.getPitch().ABSOLUTE_PITCH;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(soundedLength);            // TODO: implement Tempo, TimeSignature, Measure; use Measure.getMillis()
            midiChannels[0].noteOff(noteNumber);
            Thread.sleep(difference);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
