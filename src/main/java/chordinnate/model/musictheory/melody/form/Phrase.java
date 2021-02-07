package chordinnate.model.musictheory.melody.form;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.playback.FormPlayable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Phrase extends FormPlayable implements Metered {

    private List<PhraseMember> phraseMembers;

    public Phrase(List<PhraseMember> phraseMembers) {
        setPhraseMembers(phraseMembers);
    }

    public void setPhraseMembers(List<PhraseMember> phraseMembers) {
        if (!CollectionUtils.isEmpty(this.phraseMembers)) {
            this.phraseMembers.forEach(p -> p.setParent(null));
        }
        phraseMembers.forEach(p -> p.setParent(this));
        this.phraseMembers = phraseMembers;
    }

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
