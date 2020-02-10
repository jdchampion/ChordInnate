package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.playback.Playable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Period implements Metered, Playable {

    private Phrase phrase1;
    private Phrase phrase2;

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        List<TimeSignature> list = new ArrayList<>();
        list.addAll(phrase1.getAllTimeSignatures());
        list.addAll(phrase2.getAllTimeSignatures());
        return list;
    }

}
