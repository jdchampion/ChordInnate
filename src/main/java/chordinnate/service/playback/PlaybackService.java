package chordinnate.service.playback;

import chordinnate.config.MidiConfig;
import chordinnate.util.ContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public final class PlaybackService {

    private static final MidiConfig CONFIG = ContextProvider.getContext().getBean(MidiConfig.class);
    private static final SequenceGenerator SEQUENCE_GENERATOR = new SequenceGenerator(CONFIG);

    private static Sequencer getSequencer() throws MidiUnavailableException {
        return CONFIG.getActiveMidiSequencer() == null
                ? MidiSystem.getSequencer()
                : CONFIG.getActiveMidiSequencer();
    }

    private static Synthesizer getSynthesizer() throws MidiUnavailableException {
        return CONFIG.getActiveMidiSynthesizer() == null
                ? MidiSystem.getSynthesizer()
                : CONFIG.getActiveMidiSynthesizer();
    }

    private static void open(final MidiDevice midiDevice) {
        try {
            if (!midiDevice.isOpen()) {
                midiDevice.open();
                sleep(1000); // allows the device to finish initialization before playing
            }
        } catch (MidiUnavailableException ex) {
            log.error("Could not open MIDI device '{}': resource restrictions", midiDevice.getDeviceInfo().getName(), ex);
        } catch (SecurityException ex) {
            log.error("Could not open MIDI device '{}': security restrictions", midiDevice.getDeviceInfo().getName(), ex);
        }
    }

    private static void stop(final MidiDevice midiDevice) {
        sleep(1000); // prevents sound clipping at end of sequence
        if (midiDevice instanceof Synthesizer) {
            MidiChannel[] channels = ((Synthesizer) midiDevice).getChannels();
            for (MidiChannel midiChannel : channels) {
                midiChannel.allSoundOff();
            }
        }
        midiDevice.close();
    }

    private static void playBackSequence(Sequence sequence) {
        Sequencer sequencer;
        Synthesizer synthesizer;

        try {
            sequencer = getSequencer();
            synthesizer = getSynthesizer();

            // Wire the sequencer to send MIDI events to the specified synthesizer(s) for playback
            sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
        } catch (MidiUnavailableException ex) {
            log.error("Failed to initialize MIDI sequencer", ex);
            return;
        }

        try {
            open(sequencer);
            open(synthesizer);

            sequencer.setSequence(sequence);
            sequencer.start();

            // FIXME: listen for a signal rather than using a spinlock?
            while (sequencer.isRunning()) {
                sleep(500);
            }

            stop(sequencer);
        } catch (IllegalStateException ex) {
            log.error("Error starting the MIDI sequencer '{}': device is closed", sequencer.getDeviceInfo().getName(), ex);
        } catch (InvalidMidiDataException ex) {
            log.error("Invalid or unsupported MIDI sequence", ex);
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            log.error("Interrupted during thread sleep", ex);
        }
    }

    /**
     * Generates a MIDI musical sequence from the data contained
     * in the {@link Playable} object, and performs it.
     * @param playable data structure to generate MIDI with
     */
    public static void play(@NotNull Playable playable) {
        playBackSequence(playable.accept(SEQUENCE_GENERATOR));
    }

    public static void setActiveSequencer(Sequencer sequencer) {
        CONFIG.setActiveMidiSequencer(sequencer);
    }

    public static void setActiveSynthesizer(Synthesizer synthesizer) {
        CONFIG.setActiveMidiSynthesizer(synthesizer);
    }

}
