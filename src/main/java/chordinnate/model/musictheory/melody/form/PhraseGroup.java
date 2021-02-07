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
public class PhraseGroup extends FormPlayable implements Metered {

    private List<Phrase> phrases;

    public PhraseGroup(List<Phrase> phrases) {
        setPhrases(phrases);
    }

    public void setPhrases(List<Phrase> phrases) {
        if (!CollectionUtils.isEmpty(this.phrases)) {
            this.phrases.forEach(p -> p.setParent(null));
        }
        phrases.forEach(p -> p.setParent(this));
        this.phrases = phrases;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        phrases.forEach(p -> list.addAll(p.getAllTimeSignatures()));
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
