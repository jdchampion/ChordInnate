package chordinnate.service.playback.sequence;

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

import javax.sound.midi.Sequence;

public interface SequenceGenerator {

    /**
     * Generates a playable MIDI Sequence for a single Pitch.
     * @param pitch
     * @return
     */
    Sequence getSequence(Pitch pitch);

    /**
     * Generates a playable MIDI Sequence for a HorizontalIntervalSet.
     * @param horizontalIntervalSet
     * @return
     */
    Sequence getSequence(HorizontalIntervalSet horizontalIntervalSet);

    /**
     * Generates a playable MIDI Sequence for a VerticalIntervalSet.
     * @param verticalIntervalSet
     * @return
     */
    Sequence getSequence(VerticalIntervalSet verticalIntervalSet);

    /**
     * Generates a playable MIDI Sequence for a single Note.
     * @param note
     * @return
     */
    Sequence getSequence(Note note);

    /**
     * Generates a playable MIDI Sequence for a single Measure.
     * @param measure
     * @return
     */
    Sequence getSequence(Measure measure);

    /**
     * Generates a playable MIDI Sequence for a single Cell.
     * @param cell
     * @return
     */
    Sequence getSequence(Cell cell);

    /**
     * Generates a playable MIDI Sequence for a Motif.
     * @param motif
     * @return
     */
    Sequence getSequence(Motif motif);

    /**
     * Generates a playable MIDI Sequence for a PhraseMember.
     * @param phraseMember
     * @return
     */
    Sequence getSequence(PhraseMember phraseMember);

    /**
     * Generates a playable MIDI Sequence for a Phrase.
     * @param phrase
     * @return
     */
    Sequence getSequence(Phrase phrase);

    /**
     * Generates a playable MIDI Sequence for a PhraseGroup.
     * @param phraseGroup
     * @return
     */
    Sequence getSequence(PhraseGroup phraseGroup);

    /**
     * Generates a playable MIDI Sequence for a Period.
     * @param period
     * @return
     */
    Sequence getSequence(Period period);

    /**
     * Generates a playable MIDI Sequence for a DoublePeriod.
     * @param doublePeriod
     * @return
     */
    Sequence getSequence(DoublePeriod doublePeriod);
}
