package chordinnate.model.musictheory.notation;

import chordinnate.model.playback.FormPlayable;
import chordinnate.model.playback.StaffPlayable;
import chordinnate.midi.producer.MidiEventProducer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Slf4j
@Data
public class Staff implements StaffPlayable {

    @Setter(AccessLevel.NONE)
    private final Map<Long, Instrument> tickInstrumentMap = new TreeMap<>();

    private final Instrument primaryInstrument;
    private final String staffName;

    private FormPlayable playable;

    public Staff(Instrument primaryInstrument, String staffName) {
        this.primaryInstrument = primaryInstrument;
        this.staffName = staffName;

        tickInstrumentMap.put(0L, primaryInstrument);
    }

    @Override
    public Set<Instrument> getAllInstruments() {
        return new HashSet<>(tickInstrumentMap.values());
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
