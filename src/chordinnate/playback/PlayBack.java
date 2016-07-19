package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.time.rhythm.Beat;
import chordinnate.musictheory.time.tempo.Tempo;
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
    private static Tempo currentTempo;

    static {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            midiChannels = synthesizer.getChannels();
            synthesizer.open();
            Thread.sleep(1000); // allows the synthesizer to finish initialization before playing
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    private PlayBack() {
        currentTempo = new Tempo(Beat.QUARTER, 120);
    }

    /**
     * Restarts the Synthesizer if it has been stopped.
     */
    private static void restart() {
        try {
            if (!synthesizer.isOpen()) synthesizer.open();
        }
        catch (MidiUnavailableException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Stops the Synthesizer.
     */
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

    /**
     * Sets the current Tempo for playback.
     * @param tempo the Tempo to set
     */
    public static void setTempo(Tempo tempo) {
        currentTempo = tempo;
    }

    /**
     * Plays back the specified Pitch for one (1) second.
     * @param pitch the Pitch to play
     */
    public static void play(@NotNull Pitch pitch) {
        restart();
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

    /**
     * Plays back the specified Note, at the current Tempo.
     * @param note the Note to play
     */
    public static void play(@NotNull Note note) {
        restart();
        long fullLength = currentTempo.getMillisFor(note.getBeat());
        long soundedLength = (long) (fullLength * note.getSoundedLength());
        long difference = fullLength - soundedLength;

        try {
            int noteNumber = note.getPitch().ABSOLUTE_PITCH;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(soundedLength);
            midiChannels[0].noteOff(noteNumber);
            Thread.sleep(difference);
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
