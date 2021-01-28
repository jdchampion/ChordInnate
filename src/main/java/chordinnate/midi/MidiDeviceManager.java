package chordinnate.midi;

import chordinnate.exception.ChordInnateException;
import chordinnate.midi.producer.SequenceProducer;
import chordinnate.model.playback.Playable;
import chordinnate.util.ContextProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MidiDeviceManager {

    private static final SequenceProducer SEQUENCE_PRODUCER = new SequenceProducer();
    private static final MidiOutputRouter ROUTER = ContextProvider.getContext().getBean(MidiOutputRouter.class);

    private static final Set<MidiDevice> ACTIVE_DEVICES = new HashSet<>();
    private static final Set<Receiver> ACTIVE_RECEIVERS = new HashSet<>();
//    private static final Set<Transmitter> ACTIVE_TRANSMITTERS = new HashSet<>();

    @Getter
    @Setter
    private static @NotNull Sequencer sequencer;
    @Getter
    @Setter
    private static @NotNull Synthesizer synthesizer;

    static {
        try {
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            throw new ChordInnateException("Failed to initialize default sequencer.", e);
        }

        try {
            synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            throw new ChordInnateException("Failed to initialize default synthesizer.", e);
        }
    }

    public static void registerDevice(@NotNull MidiDevice midiDevice) {
        ACTIVE_DEVICES.add(midiDevice);
    }

//    public static boolean registerReceiver(@NotNull Receiver receiver) throws MidiUnavailableException {
//        if (RECEIVER_TO_DEVICE.containsKey(receiver)) {
//            return false;
//        }
//    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            log.error("Interrupted during thread sleep", ex);
        }
    }

    @SneakyThrows
    public static void runSequence(Playable playable) {

//        if (ACTIVE_TRANSMITTERS.isEmpty()) {
            if (!sequencer.isOpen()) {
                try {
                    sequencer.open();
                } catch (MidiUnavailableException ex) {
                    log.error("Failed to open sequencer", ex);
                    return;
                }
//            }

//            try {
//                ACTIVE_TRANSMITTERS.add(sequencer.getTransmitter());
//            } catch (MidiUnavailableException ex) {
//                log.error("Transmitter unavailable for sequencer: resource restrictions", ex);
//            }
        }

        if (ACTIVE_RECEIVERS.isEmpty()) {
            if (!synthesizer.isOpen()) {
                try {
                    synthesizer.open();
                    Set<Instrument> instrumentsToLoad = ROUTER.getRegisteredInstruments();
                    for (Instrument instrument : instrumentsToLoad) {
                        synthesizer.loadInstrument(instrument);
                    }
                } catch (MidiUnavailableException ex) {
                    log.error("Failed to open synthesizer. No other MIDI receivers have been made available.", ex);
                    sequencer.close();
                    return;
                } catch (IllegalArgumentException ex) {
                    log.error("Unsupported instrument(s) supplied to synthesizer", ex);
                    sequencer.close();
                    synthesizer.close();
                    return;
                }
            }

            try {
                ACTIVE_RECEIVERS.add(synthesizer.getReceiver());
            } catch (MidiUnavailableException ex) {
                log.error("Receiver unavailable for synthesizer: resource restrictions", ex);
            }
        }

        for (MidiDevice midiDevice : ACTIVE_DEVICES) {
            if (!midiDevice.isOpen()) {
                midiDevice.open();
            }
        }

        // Wire the sequencer to send MIDI events to the specified receiver(s) for playback
        for (Receiver receiver : ACTIVE_RECEIVERS) {
            try {
                sequencer.getTransmitter().setReceiver(receiver);
            } catch (MidiUnavailableException ex) {
                log.error("Transmitter unavailable for sequencer '{}': resource restrictions", sequencer.getDeviceInfo().getName(), ex);
            }
        }

        sleep(1000); // allows devices to finish initialization before playing

        try {
            sequencer.setSequence(SEQUENCE_PRODUCER.getSequence(playable));
            sequencer.start(); // begins streaming to MIDI OUT via transmitters
        } catch (IllegalStateException ex) {
            log.error("Error starting the sequencer: device is closed", ex);
        } catch (InvalidMidiDataException ex) {
            log.error("Invalid or unsupported MIDI sequence", ex);
        } finally {

            while (sequencer.isRunning()) {
                sleep(500);
            }

            boolean didRun = sequencer.getTickLength() > 0;

            if (didRun) {
                sleep(1000); // prevents sound clipping at end of sequence
            }

            for (MidiDevice device : ACTIVE_DEVICES) {
                if (device instanceof Synthesizer) {
                    MidiChannel[] channels = synthesizer.getChannels();
                    for (MidiChannel midiChannel : channels) {
                        midiChannel.allSoundOff();
                    }
                }
            }

            for (Instrument instrument : ROUTER.getRegisteredInstruments()) {
                synthesizer.unloadInstrument(instrument);
            }

            sequencer.close(); // closes all receivers and transmitters tied to the sequencer
            ACTIVE_DEVICES.clear();
            ACTIVE_RECEIVERS.clear();
//            ACTIVE_TRANSMITTERS.clear();
        }
    }

}
