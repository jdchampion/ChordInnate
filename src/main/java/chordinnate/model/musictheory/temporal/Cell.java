package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.SequenceGenerator;
import chordinnate.service.playback.sequence.event.MidiEventGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Metered, Playable {

    private Measure measure;

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        return measure.getAllTimeSignatures();
    }

    @Override
    public Sequence accept(SequenceGenerator sequenceGenerator) {
        return sequenceGenerator.getSequence(this);
    }

    @Override
    public void accept(MidiEventGenerator midiEventGenerator) {
        try {
            midiEventGenerator.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }

}
