package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.playback.Playable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
