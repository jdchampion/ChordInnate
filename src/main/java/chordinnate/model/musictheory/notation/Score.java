package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.StaffPlayable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
@Data
public class Score implements StaffPlayable {

    @Setter(AccessLevel.NONE)
    private final Set<StaffGroup> staffGroups = new LinkedHashSet<>();

    /*
     * TODO: set each of these as new StaffGroups are added.
     *  Adjust the structure of each item in StaffGroup as necessary.
     *
     * FIXME: no validation exists yet to prevent different TimeSignatures / KeySignatures / Tempos
     *  in each StaffGroup, leading to a contradictory (incompatible) music score.
     */
//    @Setter(AccessLevel.NONE)
//    private final Map<Long, TimeSignature> timeSignatureAtTick = new HashMap<>();
//
//    @Setter(AccessLevel.NONE)
//    private final Map<Long, KeySignature> keySignatureAtTick = new HashMap<>();
//
//    @Setter(AccessLevel.NONE)
//    private final Map<Long, Tempo> tempoChangeAtTick = new HashMap<>();

    private String title;
    private String copyright;
    private String author;

    public void add(StaffGroup staffGroup) {
        if (staffGroup != null) {
            staffGroups.add(staffGroup);
        }
    }

    @Override
    public Set<Instrument> getAllInstruments() {
        Set<Instrument> set = new HashSet<>();
        staffGroups.forEach(sg -> set.addAll(sg.getAllInstruments()));
        return set;
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
