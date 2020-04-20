package chordinnate.service.playback.sequence;

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
import chordinnate.service.playback.sequence.event.MidiChannelPrefix;
import chordinnate.service.playback.sequence.event.MidiChannelPressureChange;
import chordinnate.service.playback.sequence.event.MidiControlChange;
import chordinnate.service.playback.sequence.event.MidiCopyrightNotice;
import chordinnate.service.playback.sequence.event.MidiCuePoint;
import chordinnate.service.playback.sequence.event.MidiEndOfTrack;
import chordinnate.service.playback.sequence.event.MidiEventDataBundle;
import chordinnate.service.playback.sequence.event.MidiEventGenerator;
import chordinnate.service.playback.sequence.event.MidiInstrumentName;
import chordinnate.service.playback.sequence.event.MidiKeySignature;
import chordinnate.service.playback.sequence.event.MidiLyric;
import chordinnate.service.playback.sequence.event.MidiMarker;
import chordinnate.service.playback.sequence.event.MidiNoteOff;
import chordinnate.service.playback.sequence.event.MidiNoteOn;
import chordinnate.service.playback.sequence.event.MidiPitchBendChange;
import chordinnate.service.playback.sequence.event.MidiPolyKeyPressureChange;
import chordinnate.service.playback.sequence.event.MidiProgramChange;
import chordinnate.service.playback.sequence.event.MidiSMPTEOffset;
import chordinnate.service.playback.sequence.event.MidiSequenceNumber;
import chordinnate.service.playback.sequence.event.MidiSetTempo;
import chordinnate.service.playback.sequence.event.MidiTextEvent;
import chordinnate.service.playback.sequence.event.MidiTimeSignature;
import chordinnate.service.playback.sequence.event.MidiTrackName;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

@Slf4j
public class SequenceGeneratorImpl implements SequenceGenerator {

    // MIDI Voice Events
    private static final MidiEventGenerator NOTE_OFF = MidiNoteOff.getInstance();
    private static final MidiEventGenerator NOTE_ON = MidiNoteOn.getInstance();
    private static final MidiEventGenerator POLY_KEY_PRESSURE_CHANGE = MidiPolyKeyPressureChange.getInstance();
    private static final MidiEventGenerator CONTROL_CHANGE = MidiControlChange.getInstance();
    private static final MidiEventGenerator PROGRAM_CHANGE = MidiProgramChange.getInstance();
    private static final MidiEventGenerator CHANNEL_PRESSURE_CHANGE = MidiChannelPressureChange.getInstance();
    private static final MidiEventGenerator PITCH_BEND_CHANGE = MidiPitchBendChange.getInstance();

    // MIDI Meta Events
    private static final MidiEventGenerator SEQUENCE_NUMBER = MidiSequenceNumber.getInstance();
    private static final MidiEventGenerator TEXT_EVENT = MidiTextEvent.getInstance();
    private static final MidiEventGenerator COPYRIGHT_NOTICE = MidiCopyrightNotice.getInstance();
    private static final MidiEventGenerator TRACK_NAME = MidiTrackName.getInstance();
    private static final MidiEventGenerator INSTRUMENT_NAME = MidiInstrumentName.getInstance();
    private static final MidiEventGenerator LYRIC = MidiLyric.getInstance();
    private static final MidiEventGenerator MARKER = MidiMarker.getInstance();
    private static final MidiEventGenerator CUE_POINT = MidiCuePoint.getInstance();
    private static final MidiEventGenerator CHANNEL_PREFIX = MidiChannelPrefix.getInstance();
    private static final MidiEventGenerator END_OF_TRACK = MidiEndOfTrack.getInstance();
    private static final MidiEventGenerator SET_TEMPO = MidiSetTempo.getInstance();
    private static final MidiEventGenerator SMPTE_OFFSET = MidiSMPTEOffset.getInstance();
    private static final MidiEventGenerator TIME_SIGNATURE = MidiTimeSignature.getInstance();
    private static final MidiEventGenerator KEY_SIGNATURE = MidiKeySignature.getInstance();

    @Override
    public Sequence getSequence(Pitch pitch) {
        return null;
    }

    @Override
    public Sequence getSequence(HorizontalIntervalSet horizontalIntervalSet) {
        return null;
    }

    @Override
    public Sequence getSequence(VerticalIntervalSet verticalIntervalSet) {
        return null;
    }

    @Override
    public Sequence getSequence(Note note) {
        Sequence sequence;
        try {
            sequence = newSequence();

            MidiEventDataBundle newEventState = MidiEventDataBundle.builder().note(note).build();

            SEQUENCE_NUMBER.addEvent(sequence, newEventState);
            SET_TEMPO.addEvent(sequence, newEventState);
            PROGRAM_CHANGE.addEvent(sequence, newEventState);
            NOTE_ON.addEvent(sequence, newEventState);
            NOTE_OFF.addEvent(sequence, newEventState);
            END_OF_TRACK.addEvent(sequence, newEventState);

        } catch (InvalidMidiDataException ex) {
            log.error("Error creating sequence", ex);
            return null;
        }

        return sequence;
    }

    @Override
    public Sequence getSequence(Measure measure) {
        return null;
    }

    @Override
    public Sequence getSequence(Cell cell) {
        return null;
    }

    @Override
    public Sequence getSequence(Motif motif) {
        return null;
    }

    @Override
    public Sequence getSequence(PhraseMember phraseMember) {
        return null;
    }

    @Override
    public Sequence getSequence(Phrase phrase) {
        return null;
    }

    @Override
    public Sequence getSequence(PhraseGroup phraseGroup) {
        return null;
    }

    @Override
    public Sequence getSequence(Period period) {
        return null;
    }

    @Override
    public Sequence getSequence(DoublePeriod doublePeriod) {
        return null;
    }

    private Sequence newSequence() throws InvalidMidiDataException {
        return new Sequence(Sequence.PPQ, MidiConstants.DEFAULT_TICK_RESOLUTION);
    }

}
