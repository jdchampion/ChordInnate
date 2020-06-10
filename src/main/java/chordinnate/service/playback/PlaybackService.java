package chordinnate.service.playback;

import chordinnate.config.MidiConfig;
import chordinnate.midi.producer.SequenceProducer;
import chordinnate.model.playback.Playable;
import chordinnate.model.playback.StaffPlayable;
import chordinnate.util.ContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
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
    private static final SequenceProducer SEQUENCE_PRODUCER = new SequenceProducer(CONFIG);

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

    private static void open(final @NotNull MidiDevice midiDevice) throws MidiUnavailableException, SecurityException {
        if (!midiDevice.isOpen()) {
            midiDevice.open();
            sleep(1000); // allows the device to finish initialization before playing
        }
    }

    private static void playBackSequence(Playable playable, Sequence sequence) {
        Sequencer sequencer;
        Synthesizer synthesizer;

        try {
            sequencer = getSequencer();
            synthesizer = getSynthesizer();

            // Wire the sequencer to send MIDI events to the specified synthesizer(s) for playback
            sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
        } catch (MidiUnavailableException ex) {
            log.error("Failed to initialize and wire MIDI devices", ex);
            return;
        }

        try {
            open(sequencer);
        } catch (MidiUnavailableException ex) {
            log.error("Could not open MIDI sequencer '{}': resource restrictions", sequencer.getDeviceInfo().getName(), ex);
        } catch (SecurityException ex) {
            log.error("Could not open MIDI sequencer '{}': security restrictions", sequencer.getDeviceInfo().getName(), ex);
        }

        if (!sequencer.isOpen()) {
            return;
        }

        try {
            open(synthesizer);
        } catch (MidiUnavailableException ex) {
            log.error("Could not open MIDI synthesizer '{}': resource restrictions", synthesizer.getDeviceInfo().getName(), ex);
        } catch (SecurityException ex) {
            log.error("Could not open MIDI synthesizer '{}': security restrictions", synthesizer.getDeviceInfo().getName(), ex);
        }

        if (!synthesizer.isOpen()) {
            sequencer.close();
            return;
        }

        try {
            if (StaffPlayable.class.isAssignableFrom(playable.getClass())) {
                for (Instrument instrument : ((StaffPlayable) playable).getAllInstruments()) {
                    synthesizer.loadInstrument(instrument);
                }
            }
        } catch (IllegalArgumentException ex) {
            log.error("Unsupported instrument(s) supplied to MIDI synthesizer '{}", synthesizer.getDeviceInfo().getName(), ex);
            sequencer.close();
            synthesizer.close();
            return;
        }

        try {
            sequencer.setSequence(sequence);
            sequencer.start(); // begins streaming to synth
        } catch (IllegalStateException ex) {
            log.error("Error starting the MIDI sequencer '{}': device is closed", sequencer.getDeviceInfo().getName(), ex);
        } catch (InvalidMidiDataException ex) {
            log.error("Invalid or unsupported MIDI sequence", ex);
        } finally {

            // FIXME: listen for a signal rather than using a spinlock?
            while (sequencer.isRunning()) {
                sleep(500);
            }

            boolean didRun = sequencer.getTickLength() > 0;

            if (didRun) {
                sleep(1000); // prevents sound clipping at end of sequence
            }

            MidiChannel[] channels = synthesizer.getChannels();
            for (MidiChannel midiChannel : channels) {
                midiChannel.allSoundOff();
            }

            Instrument[] instruments = synthesizer.getLoadedInstruments();
            for (Instrument instrument : instruments) {
                synthesizer.unloadInstrument(instrument);
            }

            synthesizer.close();
            sequencer.close();
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
        playBackSequence(playable, SEQUENCE_PRODUCER.getSequence(playable));
    }

    public static void setActiveSequencer(Sequencer sequencer) {
        CONFIG.setActiveMidiSequencer(sequencer);
    }

    public static void setActiveSynthesizer(Synthesizer synthesizer) {
        CONFIG.setActiveMidiSynthesizer(synthesizer);
    }

}
