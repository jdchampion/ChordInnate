package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.StaffPlayable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Getter
@NoArgsConstructor
public class StaffGroup implements StaffPlayable {

    private final Set<Staff> staves = new LinkedHashSet<>();

    public void add(Staff staff) {
        if (staff != null) {
            staves.add(staff);
        }
    }

    public void remove(Staff staff) {
        if (staff != null) {
            staves.remove(staff);
        }
    }

    public void clearAllStaves() {
        staves.clear();
    }

    @Override
    public Set<Instrument> getAllInstruments() {
        return new HashSet<>(staves.stream().distinct().map(Staff::getInstrument).collect(Collectors.toList()));
    }

    @Override
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
