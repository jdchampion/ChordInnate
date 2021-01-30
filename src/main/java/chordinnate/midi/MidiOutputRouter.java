package chordinnate.midi;

import chordinnate.config.MidiConfig;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.playback.InstrumentCapablePlayable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.sound.midi.Instrument;
import javax.sound.midi.Receiver;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A wrapper object containing synchronized time / sound information for MIDI OUT.
 */
@Component
public class MidiOutputRouter {

    private static final Map<Instrument, Receiver> INSTRUMENT_TO_RECEIVER = new HashMap<>();
    private static final Map<Receiver, ReceiverChannelManager> RECEIVER_TO_MANAGER = new HashMap<>();
    private static final Map<InstrumentCapablePlayable, ReceiverChannelManager> PLAYABLE_TO_MANAGER = new HashMap<>();

    public void registerInstrument(@NotNull Instrument instrument, int channel, @NotNull Receiver receiver) {
        if (channel < MidiConfig.MIN_CHANNEL_VALUE || channel > MidiConfig.MAX_CHANNEL_VALUE) {
            throw new ChordInnateException("Channel must be between " + MidiConfig.MIN_CHANNEL_VALUE + " - " + MidiConfig.MAX_CHANNEL_VALUE + " (inclusive)");
        }

        INSTRUMENT_TO_RECEIVER.putIfAbsent(instrument, receiver);
        RECEIVER_TO_MANAGER.compute(receiver, (rcv, rcm) -> {
            if (rcm == null) {
                rcm = new ReceiverChannelManager();
            }
            rcm.instrumentToChannel.putIfAbsent(instrument, channel);
            return rcm;
        });
    }

    public Set<Instrument> getRegisteredInstruments() {
        return INSTRUMENT_TO_RECEIVER.keySet();
    }

    public Set<Receiver> getRegisteredReceivers() {
        return RECEIVER_TO_MANAGER.keySet();
    }

    /**
     * @param playable
     * @param instrument
     */
    public void registerProgramChange(@NotNull InstrumentCapablePlayable playable, @NotNull Instrument instrument) {
        if (!INSTRUMENT_TO_RECEIVER.containsKey(instrument)) {
            throw new ChordInnateException("Instrument must first be registered via MidiOutputRouter::registerInstrument()");
        }

        PLAYABLE_TO_MANAGER.compute(playable, (k, v) -> {
            if (v == null) {
                v = RECEIVER_TO_MANAGER.get(INSTRUMENT_TO_RECEIVER.get(instrument));
            }
            v.playableToInstrument.put(playable, instrument);
            return v;
        });
    }

    @Nullable
    public Instrument getInstrument(@NotNull InstrumentCapablePlayable playable) {
        AtomicReference<Instrument> instrument = new AtomicReference<>();
        PLAYABLE_TO_MANAGER.compute(playable, (k, v) -> {
            if (v != null) {
                instrument.set(v.playableToInstrument.get(playable)); // FIXME: also check if contains any InstrumentCapablePlayable that is the parent (holding) class
            }
            return v;
        });
        return instrument.get();
    }

    public int getChannel(@NotNull InstrumentCapablePlayable playable) {
        if (!PLAYABLE_TO_MANAGER.containsKey(playable)) { // FIXME: also check if contains any InstrumentCapablePlayable that is the parent (holding) class
            return MidiConfig.DEFAULT_CHANNEL;
        }
        ReceiverChannelManager manager = PLAYABLE_TO_MANAGER.get(playable);
        return manager.instrumentToChannel.getOrDefault(manager.playableToInstrument.get(playable), MidiConfig.DEFAULT_CHANNEL);
    }

    private static class ReceiverChannelManager {
        private final Map<InstrumentCapablePlayable, Instrument> playableToInstrument = new LinkedHashMap<>();
        private final Map<Instrument, Integer> instrumentToChannel = new HashMap<>();
    }
}
