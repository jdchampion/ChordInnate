package chordinnate.model.musictheory.time.meter;

import chordinnate.model.musictheory.notation.TimeSignature;

import java.util.List;

public interface Metered {
    public List<TimeSignature> getAllTimeSignatures();
}
