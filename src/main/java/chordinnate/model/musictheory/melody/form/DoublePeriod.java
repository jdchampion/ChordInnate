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
public class DoublePeriod extends FormPlayable implements Metered {

    private Period period1;
    private Period period2;

    public DoublePeriod(Period period1, Period period2) {
        setPeriod1(period1);
        setPeriod2(period2);
    }

    public void setPeriod1(Period period1) {
        if (this.period1 != null) {
            this.period1.setParent(null);
        }
        period1.setParent(this);
        this.period1 = period1;
    }

    public void setPeriod2(Period period2) {
        if (this.period2 != null) {
            this.period2.setParent(null);
        }
        period2.setParent(this);
        this.period2 = period2;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        list.addAll(period1.getAllTimeSignatures());
        list.addAll(period2.getAllTimeSignatures());
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
