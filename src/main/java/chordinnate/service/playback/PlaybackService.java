package chordinnate.service.playback;

import chordinnate.midi.MidiDeviceManager;
import chordinnate.midi.MidiOutputRouter;
import chordinnate.model.playback.Playable;
import chordinnate.util.ContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public final class PlaybackService {

    private static final MidiOutputRouter ROUTER = ContextProvider.getContext().getBean(MidiOutputRouter.class);

    /**
     * Generates a MIDI musical sequence from the data contained
     * in the {@link Playable} object, and performs it.
     * @param playable data structure to generate MIDI with
     */
    public static void play(@NotNull Playable playable) {
        MidiDeviceManager.runSequence(playable);
    }

    public static Sequencer getActiveSequencer() {
        return MidiDeviceManager.getSequencer();
    }

    public static void setActiveSequencer(@NotNull Sequencer sequencer) {
        MidiDeviceManager.setSequencer(sequencer);
    }

    public static Synthesizer getActiveSynthesizer() {
        return MidiDeviceManager.getSynthesizer();
    }

    public static void setActiveSynthesizer(@NotNull Synthesizer synthesizer) {
        MidiDeviceManager.setSynthesizer(synthesizer);
    }

    public static void registerDevice(@NotNull MidiDevice midiDevice) {
        MidiDeviceManager.registerDevice(midiDevice);
    }

    public static void registerInstrument(Instrument instrument, int channel, MidiDevice midiDevice) {
        try {
            registerDevice(midiDevice);
            Receiver receiver = midiDevice.getReceiver();
            ROUTER.registerInstrument(instrument, channel, receiver);
        } catch (MidiUnavailableException ex) {
            log.error("Receiver unavailable for device: resource restrictions", ex);
        }
    }

}
