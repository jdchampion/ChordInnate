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
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.event.MidiEventGenerator;
import chordinnate.service.playback.sequence.event.MidiEventGeneratorImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.util.concurrent.Callable;

@Slf4j
public class SequenceGeneratorImpl implements SequenceGenerator {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MidiEventGeneratorCallable implements Callable<Void> {

        private MidiType midiType = MidiConstants.DEFAULT_MIDI_TYPE;
        private MidiEventGenerator generator;
        private Playable playable;
        private Sequence sequence; // the sequence we're about to create
        private Tempo tempo = MidiConstants.DEFAULT_TEMPO;
        private int trackNumber = MidiConstants.DEFAULT_TRACK;
        private int channel = MidiConstants.DEFAULT_CHANNEL;
        private int instrument = MidiConstants.DEFAULT_INSTRUMENT;
        private long tick = 0;

        @Override
        public Void call() throws Exception {
            playable.accept(generator);
            return null;
        }
    }

    @Override
    public Sequence getSequence(Pitch pitch) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(Note.builder(Beat.QUARTER, pitch).build());
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(HorizontalIntervalSet horizontalIntervalSet) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(horizontalIntervalSet);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(VerticalIntervalSet verticalIntervalSet) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(verticalIntervalSet);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Note note) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(note);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Measure measure) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(measure);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Cell cell) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(cell);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Motif motif) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(motif);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(PhraseMember phraseMember) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(phraseMember);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Phrase phrase) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(phrase);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(PhraseGroup phraseGroup) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(phraseGroup);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(Period period) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(period);
        return buildSequence(callable);
    }

    @Override
    public Sequence getSequence(DoublePeriod doublePeriod) {
        MidiEventGeneratorCallable callable = new MidiEventGeneratorCallable();
        callable.setPlayable(doublePeriod);
        return buildSequence(callable);
    }

    private Sequence buildSequence(MidiEventGeneratorCallable caller) {
        Sequence sequence;
        try {
            sequence = newSequence();
            caller.setSequence(sequence);

            MidiEventGeneratorImpl generator = new MidiEventGeneratorImpl(caller);

            // Always add these to the beginning of every MIDI sequence
            generator.addSequenceNumberEvent(caller.getTrackNumber(), 0);
            generator.addSetTempoEvent(0, caller.getTempo());
            generator.addProgramChangeEvent(0, caller.getTrackNumber(), caller.getChannel(), caller.getInstrument());
            generator.addCopyrightNoticeEvent(caller.getTrackNumber(), ""); // TODO: add only if necessary
            generator.addTrackNameEvent(caller.getTrackNumber(), ""); // TODO: add only if necessary
//            generator.addSMPTEOffsetEvent(); // TODO: add only if necessary
            generator.addTimeSignatureEvent(0, MidiConstants.DEFAULT_TIME_SIGNATURE); // TODO: add this here?
            generator.addKeySignatureEvent(0, MidiConstants.DEFAULT_KEY_SIGNATURE); // TODO: add this here?

            // Pass the generator to the callable, and use the callable to add all custom MIDI events
            caller.setGenerator(generator);
            caller.call();

            generator.addEndOfTrackEvent();

        } catch (Exception ex) {
            return null;
        }
        return sequence;
    }

    private Sequence newSequence() throws InvalidMidiDataException {
        return new Sequence(Sequence.PPQ, MidiConstants.DEFAULT_TICK_RESOLUTION);
    }

}
