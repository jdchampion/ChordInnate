package chordinnate.model.musictheory.melody.form;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.playback.FormPlayable;
import chordinnate.midi.producer.MidiEventProducer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phrase implements Metered, FormPlayable {

    private List<PhraseMember> phraseMembers;

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        phraseMembers.forEach(p -> list.addAll(p.getAllTimeSignatures()));
        return list;
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
