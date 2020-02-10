package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.model.playback.Playable;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class Measure implements Metered, Playable {

    private TimeSignature timeSignature;
    private KeySignature keySignature;
    private Tempo tempo; // TODO: add here or on Note?
    private List<Note> rhythm;

    public Measure(TimeSignature timeSignature, KeySignature keySignature, List<Note> rhythm) {

        // TODO: validate that the rhythm's total duration matches timeSignature

        this.timeSignature = timeSignature;
        this.keySignature = keySignature;
        this.rhythm = rhythm;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        return Collections.singletonList(timeSignature);
    }

}
