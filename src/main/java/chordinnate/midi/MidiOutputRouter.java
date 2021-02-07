package chordinnate.midi;

import chordinnate.config.MidiConfig;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.playback.InstrumentCapablePlayable;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public final class MidiOutputRouter {

    private static final Map<Instrument, Receiver> INSTRUMENT_TO_RECEIVER = new HashMap<>();
    private static final Map<Receiver, ReceiverChannelManager> RECEIVER_TO_MANAGER = new HashMap<>();
    private static final Map<InstrumentCapablePlayable, ReceiverChannelManager> PLAYABLE_TO_MANAGER = new HashMap<>();

    public final void registerInstrument(@NotNull Instrument instrument, int channel, @NotNull Receiver receiver) {
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

//        log.info("REGISTER INSTRUMENT: {} CHANNEL: {}", instrument.getName(), channel);
    }

    public final Set<Instrument> getRegisteredInstruments() {
        return INSTRUMENT_TO_RECEIVER.keySet();
    }

    public final Set<Receiver> getRegisteredReceivers() {
        return RECEIVER_TO_MANAGER.keySet();
    }

    /**
     * @param playable
     * @param instrument
     */
    public final void registerProgramChange(@NotNull InstrumentCapablePlayable playable, @NotNull Instrument instrument) {
        if (!INSTRUMENT_TO_RECEIVER.containsKey(instrument)) {
            throw new ChordInnateException("Instrument must first be registered via MidiOutputRouter::registerInstrument()");
        }

        PLAYABLE_TO_MANAGER.compute(playable, (k, v) -> {
            if (v == null) {
                v = RECEIVER_TO_MANAGER.get(INSTRUMENT_TO_RECEIVER.get(instrument));
            }
            v.playableToInstrument.put(k, instrument);
            return v;
        });

//        log.info("REGISTER PROGRAM CHANGE. PLAYABLE: {} INSTRUMENT: {}", playable.getClass().getName(), instrument.getName());
    }

    @Nullable
    public final Instrument getInstrument(@NotNull InstrumentCapablePlayable playable) {
        AtomicReference<Instrument> instrument = new AtomicReference<>();
        PLAYABLE_TO_MANAGER.compute(playable, (k, v) -> {
            if (v != null) {
                Instrument toUse = v.playableToInstrument.get(k);
                if (toUse == null) {
                    InstrumentCapablePlayable icp = k.getParent();
                    while (toUse == null && icp != null) {
                        toUse = v.playableToInstrument.get(icp);
                        icp = icp.getParent();
                    }
                }
                instrument.set(toUse);
            }
            return v;
        });
        return instrument.get();
    }

    public final int getChannel(@NotNull InstrumentCapablePlayable playable) {
        InstrumentCapablePlayable icp = playable;
        ReceiverChannelManager manager = PLAYABLE_TO_MANAGER.get(icp);

        if (manager == null) {
            while (manager == null && icp.getParent() != null) {
                manager = PLAYABLE_TO_MANAGER.get(icp.getParent());
                icp = icp.getParent();
            }
            if (manager == null) return MidiConfig.DEFAULT_CHANNEL;
        }

        return manager.instrumentToChannel.getOrDefault(manager.playableToInstrument.get(icp), MidiConfig.DEFAULT_CHANNEL);
    }

    private static final class ReceiverChannelManager {
        private final Map<InstrumentCapablePlayable, Instrument> playableToInstrument = new LinkedHashMap<>();
        private final Map<Instrument, Integer> instrumentToChannel = new HashMap<>();
    }
}
