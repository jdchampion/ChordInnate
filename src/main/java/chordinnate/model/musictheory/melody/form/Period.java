package chordinnate.model.musictheory.melody.form;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.playback.FormPlayable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Period extends FormPlayable implements Metered {

    private Phrase phrase1;
    private Phrase phrase2;

    public Period(Phrase phrase1, Phrase phrase2) {
        setPhrase1(phrase1);
        setPhrase2(phrase2);
    }

    public void setPhrase1(Phrase phrase1) {
        if (this.phrase1 != null) {
            this.phrase1.setParent(null);
        }
        phrase1.setParent(this);
        this.phrase1 = phrase1;
    }

    public void setPhrase2(Phrase phrase2) {
        if (this.phrase2 != null) {
            this.phrase2.setParent(null);
        }
        phrase2.setParent(this);
        this.phrase2 = phrase2;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        list.addAll(phrase1.getAllTimeSignatures());
        list.addAll(phrase2.getAllTimeSignatures());
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
