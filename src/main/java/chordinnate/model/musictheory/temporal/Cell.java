package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.Sequence;
import java.util.List;

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

}
