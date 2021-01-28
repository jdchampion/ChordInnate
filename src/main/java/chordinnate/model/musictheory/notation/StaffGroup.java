package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.Playable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
@Getter
@NoArgsConstructor
public class StaffGroup implements Playable {

    @Setter(AccessLevel.NONE)
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
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
