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
public class Phrase implements Metered, Playable {

    private List<PhraseMember> phraseMembers;

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        phraseMembers.forEach(p -> list.addAll(p.getAllTimeSignatures()));
        return list;
    }

    @Override
    public Sequence accept(SequenceVisitor sequenceVisitor) {
        return sequenceVisitor.getSequence(this);
    }

}
