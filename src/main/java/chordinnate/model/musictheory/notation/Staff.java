package chordinnate.model.musictheory.notation;

import chordinnate.config.MidiConfig;
import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.FormPlayable;
import chordinnate.model.playback.StaffPlayable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Data
public class Staff implements StaffPlayable {

    protected Instrument instrument;
    protected String staffName;

    @Setter(AccessLevel.NONE)
    protected int midiChannel;

    protected FormPlayable playable;

    public Staff() {
        this(MidiConfig.DEFAULT_CHANNEL);
    }

    protected Staff(int midiChannel) {
        this.midiChannel = midiChannel;
    }

    public Staff(Instrument instrument, String staffName) {
        this(MidiConfig.DEFAULT_CHANNEL);
        this.instrument = instrument;
        this.staffName = staffName;
    }

    protected Staff(Instrument instrument, String staffName, int midiChannel) {
        this(instrument, staffName);
        this.midiChannel = midiChannel;
    }

    @Override
    public Set<Instrument> getAllInstruments() {
        return instrument == null ? Collections.emptySet() : Collections.singleton(instrument);
    }

    @Override
    public void accept(@NotNull MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
