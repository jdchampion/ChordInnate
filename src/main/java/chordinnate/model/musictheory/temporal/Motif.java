package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.playback.Playable;
import chordinnate.service.playback.visitor.SequenceVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motif implements Metered, Playable {

    private List<Cell> cells;

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        cells.forEach(c -> list.addAll(c.getAllTimeSignatures()));
        return list;
    }

    @Override
    public Sequence accept(SequenceVisitor sequenceVisitor) {
        return sequenceVisitor.getSequence(this);
    }

}
