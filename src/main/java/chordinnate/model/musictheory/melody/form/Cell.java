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
import java.util.List;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cell extends FormPlayable implements Metered {

    private Measure measure;

    public Cell(Measure measure) {
        setMeasure(measure);
    }

    public void setMeasure(Measure measure) {
        if (this.measure != null) {
            this.measure.setParent(null);
        }
        measure.setParent(this);
        this.measure = measure;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        return measure.getAllTimeSignatures();
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
