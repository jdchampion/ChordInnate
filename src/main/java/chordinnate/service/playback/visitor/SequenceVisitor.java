package chordinnate.service.playback.visitor;

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
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

@Slf4j
public class SequenceVisitor implements PlayableVisitor {

    private static final int DEFAULT_CHANNEL = 0;
    private static final int DEFAULT_INSTRUMENT = 0; // Grand Piano
    private static final int DEFAULT_VELOCITY = 64; // mezzo forte
    private static final Tempo DEFAULT_TEMPO = new Tempo(Beat.QUARTER, 120);

    private static final int DEFAULT_TICK_RESOLUTION = 96; // number of ticks per reference beat of tempo
    private static final int USEC_PER_REF_BEAT_AT_60_BPM = 1000000; // microseconds
    private static final double SIXTY_BPM = 60.0; // used for computing tempo ratios

    /*
     * These variables are used for providing a "back reference"
     * for any changes made during the creation of the Sequence.
     *
     * Whenever a Playable object does not contain this data
     * but that data is required for proper playback,
     * these variables can be referenced. This is a safe and
     * completely valid way to assume musical intentions of the piece.
     *
     * Each variable must be updated on an individual basis
     * if the Playable object DOES in fact contain updated data.
     */
    private int currentChannel = DEFAULT_CHANNEL;
    private int currentInstrument = DEFAULT_INSTRUMENT;
    private int currentVelocity = DEFAULT_VELOCITY;
    private Tempo currentTempo = DEFAULT_TEMPO;

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
        } catch (InvalidMidiDataException ex) {
            log.error("Error creating sequence", ex);
            return null;
        }

        try {
            // TODO: use a configured channel / instrument?
            addNote(note, sequence.createTrack(), 0, null, null);
        } catch (InvalidMidiDataException ex) {
            log.error("Error adding events to sequence", ex);
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

    private static Sequence newSequence() throws InvalidMidiDataException {
        return new Sequence(Sequence.PPQ, DEFAULT_TICK_RESOLUTION);
    }

    /**
     * Top-level "hook" helper method for changing the instrument on a sequence track.
     * @param track the sequence track accepting the new note
     * @param startingTick the initial point in the track at which to sound the note
     * @param channel MIDI code (0 - 15) for the channel to use
     * @param instrument MIDI code (0 - 127) for the performing instrument
     * @throws InvalidMidiDataException
     */
    private void setInstrument(@NotNull Track track, long startingTick, @Nullable Integer channel, @Nullable Integer instrument) throws InvalidMidiDataException {
        if (channel == null) channel = currentChannel;
        if (instrument == null) instrument = currentInstrument;

        addProgramChangeEvent(track, startingTick, channel, instrument);
    }

    /**
     * Top-level "hook" helper method for adding a note to a sequence track.
     * @param note the note to add to the track
     * @param track the sequence track accepting the new note
     * @param startingTick the initial point in the track at which to sound the note
     * @param channel MIDI code (0 - 15) for the channel to use
     * @param instrument MIDI code (0 - 127) for the performing instrument
     * @throws InvalidMidiDataException if an internal error occurred
     */
    private void addNote(@NotNull Note note, @NotNull Track track, long startingTick, @Nullable Integer channel, @Nullable Integer instrument) throws InvalidMidiDataException {
        setInstrument(track, startingTick, channel, instrument);
        addNote(note, track, startingTick, channel);
    }

    /**
     * Top-level "hook" helper method for adding a note to a sequence track.
     * @param note the note to add to the track
     * @param track the sequence track accepting the new note
     * @param startingTick the initial point in the track at which to sound the note
     * @param channel MIDI code (0 - 15) for the channel to use
     * @throws InvalidMidiDataException if an internal error occurred
     */
    private void addNote(@NotNull Note note, @NotNull Track track, long startingTick, @Nullable Integer channel) throws InvalidMidiDataException {

        if (channel == null) channel = currentChannel;

        int noteVal = note.getPitch().getMidiValue();
        int velocity = note.getDynamic() == null ? currentVelocity : note.getDynamic().getVelocity();

        addNoteOnEvent(track, startingTick, channel, noteVal, velocity);
        addNoteOffEvent(track, startingTick + getTickCountForBeatAtTempo(note.getBeat(), currentTempo), channel, noteVal);
    }

    /**
     * Changes the instrument of the given track, from the given tick forward.
     * @param track the sequence track accepting the event
     * @param tick the point in the track at which to add the event
     * @param channel MIDI code (0 - 15) for the channel to use
     * @param instrument MIDI code (0 - 127) for the new instrument
     * @throws InvalidMidiDataException if an internal error occurred
     */
    private void addProgramChangeEvent(Track track, long tick, int channel, int instrument) throws InvalidMidiDataException {
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
        MidiEvent event = new MidiEvent(sm, tick);
        track.add(event);

        if (currentChannel != channel) currentChannel = channel;
        if (currentInstrument != instrument) currentInstrument = instrument;
    }

    /**
     * <p>Adds a NOTE_ON event to the given track and channel to start sounding the given note,
     * at the given volume level (velocity), from the given tick forward.</p><br>
     *
     * The note will continue sounding until a corresponding NOTE_OFF event is encountered
     * on the same track and channel, or the sequence is terminated, whichever occurs first.
     * @param track the sequence track accepting the event
     * @param tick the point in the track at which to add the event
     * @param channel MIDI code (0 - 15) for the channel to use
     * @param noteVal MIDI code (0 - 127) for the note to sound
     * @param velocity MIDI code (0 - 127) for the volume level to sound the note
     * @throws InvalidMidiDataException if an internal error occurred
     */
    private void addNoteOnEvent(Track track, long tick, int channel, int noteVal, int velocity) throws InvalidMidiDataException {
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.NOTE_ON, channel, noteVal, velocity);
        MidiEvent event = new MidiEvent(sm, tick);
        track.add(event);

        if (currentChannel != channel) currentChannel = channel;
        if (currentVelocity != velocity) currentVelocity = velocity;
    }

    /**
     * <p>Adds a NOTE_OFF event to the given track and channel to stop a given note.</p><br>
     * If the note is not currently on, the event will have no auditory effect.
     * @param track the sequence track accepting the event
     * @param tick the point in the track at which to add the event
     * @param channel MIDI code (0 - 15) for the channel to use
     * @param noteVal MIDI code (0 - 127) for the note to stop
     * @throws InvalidMidiDataException if an internal error occurred
     */
    private void addNoteOffEvent(Track track, long tick, int channel, int noteVal) throws InvalidMidiDataException {
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.NOTE_OFF, channel, noteVal);
        MidiEvent event = new MidiEvent(sm, tick);
        track.add(event);

        if (currentChannel != channel) currentChannel = channel;
    }

    private long getMicrosecondCountForBeatAtTempo(Beat beat, Tempo tempo) {
        double ratio = tempo.getReferenceBeat().getDuration() / beat.getDuration();
        return Math.round((ratio * USEC_PER_REF_BEAT_AT_60_BPM) / (tempo.getBpm() / SIXTY_BPM));
    }

    private long getMillisecondCountForBeatAtTempo(Beat beat, Tempo tempo) {
        return getMicrosecondCountForBeatAtTempo(beat, tempo) / 1000;
    }

    private long getTickCountForBeatAtTempo(Beat beat, Tempo tempo) {
        double ratio = tempo.getReferenceBeat().getDuration() / beat.getDuration();
        return Math.round(DEFAULT_TICK_RESOLUTION / ratio);
    }

}
