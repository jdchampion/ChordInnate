package chordinnate.service.playback.sequence.event;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.set.HorizontalIntervalSet;
import chordinnate.model.musictheory.pitch.interval.set.VerticalIntervalSet;
import chordinnate.model.musictheory.melody.form.Cell;
import chordinnate.model.musictheory.melody.form.DoublePeriod;
import chordinnate.model.musictheory.melody.form.Measure;
import chordinnate.model.musictheory.melody.form.Motif;
import chordinnate.model.musictheory.melody.form.Period;
import chordinnate.model.musictheory.melody.form.Phrase;
import chordinnate.model.musictheory.melody.form.PhraseGroup;
import chordinnate.model.musictheory.melody.form.PhraseMember;
import chordinnate.model.playback.Note;

import javax.sound.midi.InvalidMidiDataException;

public interface MidiEventGenerator {
    void addEvents(Pitch pitch) throws InvalidMidiDataException;
    void addEvents(HorizontalIntervalSet horizontalIntervalSet) throws InvalidMidiDataException;
    void addEvents(VerticalIntervalSet verticalIntervalSet) throws InvalidMidiDataException;
    void addEvents(Note note) throws InvalidMidiDataException;
    void addEvents(Measure measure) throws InvalidMidiDataException;
    void addEvents(Cell cell) throws InvalidMidiDataException;
    void addEvents(Motif motif) throws InvalidMidiDataException;
    void addEvents(PhraseMember phraseMember) throws InvalidMidiDataException;
    void addEvents(Phrase phrase) throws InvalidMidiDataException;
    void addEvents(PhraseGroup phraseGroup) throws InvalidMidiDataException;
    void addEvents(Period period) throws InvalidMidiDataException;
    void addEvents(DoublePeriod doublePeriod) throws InvalidMidiDataException;
}
