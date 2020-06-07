package chordinnate.model.playback;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.service.playback.MidiEventGenerator;
import chordinnate.service.playback.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
public class Score implements StaffPlayable {

    @Setter(AccessLevel.NONE)
    private final List<Staff> staves = new ArrayList<>(); // index is track number

    @Setter(AccessLevel.NONE)
    private final Map<Long, TimeSignature> timeSignatureAtTick = new HashMap<>();

    @Setter(AccessLevel.NONE)
    private final Map<Long, KeySignature> keySignatureAtTick = new HashMap<>();

    @Setter(AccessLevel.NONE)
    private final Map<Long, Tempo> tempoChangeAtTick = new HashMap<>();

    private String title;
    private String copyright;
    private String author;

    public void addStaff(@NotNull Staff staff) {
        staves.add(staff);
    }

    public void addStaff(@NotNull Staff staff, int track) {
        staves.add(track, staff);
    }

    @Override
    public Set<Instrument> getAllInstruments() {
        Set<Instrument> set = new HashSet<>();
        staves.forEach(staff -> set.addAll(staff.getAllInstruments()));
        return set;
    }

    @Override
    public Sequence accept(@NotNull SequenceGenerator sequenceGenerator) {
        return sequenceGenerator.getSequence(this);
    }

    @Override
    public void accept(@NotNull MidiEventGenerator midiEventGenerator) {
        try {
            midiEventGenerator.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
