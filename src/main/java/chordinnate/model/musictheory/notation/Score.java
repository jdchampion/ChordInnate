package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.Playable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
@Data
public class Score implements Playable {

    @Setter(AccessLevel.NONE)
    private final Set<StaffGroup> staffGroups = new LinkedHashSet<>();

    private String title;
    private String copyright;
    private String author;

    public void add(StaffGroup staffGroup) {
        if (staffGroup != null) {
            staffGroups.add(staffGroup);
        }
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
