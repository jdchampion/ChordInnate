package chordinnate.service.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.*;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public final class PlaybackController {
    private static MidiChannel[] midiChannels;
    private static Synthesizer synthesizer;
    private static Tempo currentTempo = new Tempo(Beat.QUARTER, 120);

    static {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            midiChannels = synthesizer.getChannels();
            synthesizer.open();
            Thread.sleep(1000); // allows the synthesizer to finish initialization before playing
        } catch (Exception ex) {
            log.error("Error initializing the synthesizer.", ex);
        }
    }

    private PlaybackController() {}

    /**
     * Restarts the Synthesizer if it has been stopped.
     */
    private static void prepareController() {
        try {
            if (!synthesizer.isOpen()) {
                synthesizer.open();
            }
        } catch (MidiUnavailableException ex) {
            log.error("Error restarting the synthesizer.", ex);
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
        } catch (Exception ex) {
            log.error("Error stopping the synthesizer.", ex);
        }
    }

    /**
     * Sets the current Tempo for playback.
     * @param tempo the Tempo to set
     */
    public static void setTempo(Tempo tempo) {
        currentTempo = tempo;
    }

//    public static <T extends Playable> void play(@NotNull T t) {
//        prepareController();
//
//        try {
//            t.getMidiSequence();
//        } catch (Exception ex) {
//            // TODO - add throws Exception to play(T) ?
//            LOGGER.log(Level.SEVERE, "Error during playback.", ex);
//
//        }
//
//        stop();
//    }

    /**
     * Plays back the specified Pitch for one (1) second.
     * @param pitch the Pitch to play
     * TODO - deprecate this when play(T) works
     */
    public static void play(@NotNull Pitch pitch) {
        prepareController();
        try {
            int noteNumber = pitch.absolutePitch;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(1000);
            midiChannels[0].noteOff(noteNumber);
        } catch (Exception ex) {
            log.error("Error during playback.", ex);
        }
    }

    /**
     * Plays back the specified Note, at the current Tempo.
     * @param note the Note to play
     * TODO - deprecate this when play(T) works
     */
    public static void play(@NotNull Note note) {
        prepareController();
        long fullLength = currentTempo.getMillisFor(note.getBeat());
        long soundedLength = (long) (fullLength * note.getSoundedLength());
        long difference = fullLength - soundedLength;

        try {
            int noteNumber = note.getPitch().absolutePitch;
            midiChannels[0].noteOn(noteNumber, 127);
            Thread.sleep(soundedLength);
            midiChannels[0].noteOff(noteNumber);
            Thread.sleep(difference);
        } catch (Exception ex) {
            log.error("Error during playback.", ex);
        }
    }

    /**
     * TODO - deprecate this when play(T) works
     * @param chord
     */
    public static void play(@NotNull Chord chord) {
        playLayered(chord.getPitchesForOctave(Octave.OCTAVE_4));
    }

    /**
     * TODO - move to Playable interface as a default method?
     * @param pitches
     */
    private static void playLayered(Pitch[] pitches) {
        prepareController();
        try {
            for (Pitch p : pitches) {
                int noteNumber = p.absolutePitch;
                midiChannels[0].noteOn(noteNumber, 127);
            }

            Thread.sleep(1000); // hold the note

            for (Pitch p : pitches) {
                int noteNumber = p.absolutePitch;
                midiChannels[0].noteOff(noteNumber);
            }
        } catch (Exception ex) {
            log.error("Error during playback.", ex);
        }
    }

    /**
     * TODO - deprecate this when play(T) works
     * @param scale
     * @param octave
     */
    public static void play(@NotNull Scale scale, Octave octave) {
        prepareController();
        try {
            for (Pitch p : scale.getPitchesForOctave(octave)) {
                play(p);
            }
        } catch (Exception ex) {
            log.error("Error during playback.", ex);
        }
    }
}
