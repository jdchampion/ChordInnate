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
public class PhraseMember extends FormPlayable implements Metered {

    private List<Motif> motifs;

    public PhraseMember(List<Motif> motifs) {
        setMotifs(motifs);
    }

    public void setMotifs(List<Motif> motifs) {
        if (!CollectionUtils.isEmpty(this.motifs)) {
            this.motifs.forEach(c -> c.setParent(null));
        }
        motifs.forEach(m -> m.setParent(this));
        this.motifs = motifs;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        motifs.forEach(m -> list.addAll(m.getAllTimeSignatures()));
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
