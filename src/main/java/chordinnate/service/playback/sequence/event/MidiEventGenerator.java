package chordinnate.service.playback.sequence.event;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.set.HorizontalIntervalSet;
import chordinnate.model.musictheory.pitch.interval.set.VerticalIntervalSet;
import chordinnate.model.musictheory.temporal.Cell;
import chordinnate.model.musictheory.temporal.DoublePeriod;
import chordinnate.model.musictheory.temporal.Measure;
import chordinnate.model.musictheory.temporal.Motif;
import chordinnate.model.musictheory.temporal.Period;
import chordinnate.model.musictheory.temporal.Phrase;
import chordinnate.model.musictheory.temporal.PhraseGroup;
import chordinnate.model.musictheory.temporal.PhraseMember;
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
