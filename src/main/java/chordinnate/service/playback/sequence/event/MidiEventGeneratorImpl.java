package chordinnate.service.playback.sequence.event;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Octave;
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
import chordinnate.service.playback.sequence.MidiConstants;
import chordinnate.service.playback.sequence.MidiType;
import chordinnate.service.playback.sequence.SequenceGeneratorImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiEventGeneratorImpl implements MidiEventGenerator {

    private SequenceGeneratorImpl.MidiEventGeneratorCallable callee;

    protected Track getTrack(Sequence sequence, int trackNumber) {
        Track[] tracks = sequence.getTracks();
        int length = tracks.length - 1;
        while (length < trackNumber) {
            sequence.createTrack();
            length++;
        }
        return sequence.getTracks()[trackNumber];
    }

    private void addVoiceEventImpl(int command, long tick, int trackNumber,
                                   int channel, int data1, int data2) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
        assert !MidiType.TYPE_ZERO.equals(callee.getMidiType()) && !MidiType.TYPE_ONE.equals(callee.getMidiType()) || trackNumber == 0;

        ShortMessage sm = new ShortMessage();
        sm.setMessage(command, channel, data1, data2);
        MidiEvent event = new MidiEvent(sm, tick);

        getTrack(callee.getSequence(), trackNumber).add(event);
    }

    private void addTextEventImpl(int command, long tick, int trackNumber, String text) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
        assert !MidiType.TYPE_ZERO.equals(callee.getMidiType()) && !MidiType.TYPE_ONE.equals(callee.getMidiType()) || trackNumber == 0;

        if (StringUtils.isNotBlank(text)) {

            byte[] data = text.getBytes(StandardCharsets.US_ASCII);

            MetaMessage mm = new MetaMessage();
            mm.setMessage(command, data, data.length);
            MidiEvent event = new MidiEvent(mm, tick);

            getTrack(callee.getSequence(), trackNumber).add(event);
        }
    }

    /**
     * Generates a MIDI NOTE_ON message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param note
     * @throws InvalidMidiDataException
     */
    public void addNoteOnEvent(long tick, int trackNumber, int channel, @NotNull Note note) throws InvalidMidiDataException {
        addNoteOnEvent(tick, trackNumber, channel, note.getPitch().getMidiValue(), note.getDynamic() != null ? note.getDynamic().getVelocity() : MidiConstants.DEFAULT_VELOCITY);
    }

    /**
     * Generates a MIDI NOTE_ON message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @param noteVelocity
     * @throws InvalidMidiDataException
     */
    public void addNoteOnEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.NOTE_ON, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI NOTE_OFF message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param note
     * @throws InvalidMidiDataException
     */
    public void addNoteOffEvent(long tick, int trackNumber, int channel, @NotNull Note note) throws InvalidMidiDataException {
        addNoteOffEvent(tick, trackNumber, channel, note.getPitch().getMidiValue());
    }

    /**
     * Generates a MIDI NOTE_OFF message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @throws InvalidMidiDataException
     */
    public void addNoteOffEvent(long tick, int trackNumber, int channel, int noteValue) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.NOTE_OFF, tick, trackNumber, channel, noteValue, 0);

    }

    /**
     * Generates a MIDI POLY_KEY_PRESSURE_CHANGE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @param noteVelocity
     * @throws InvalidMidiDataException
     */
    public void addPolyKeyPressureChangeEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.POLY_PRESSURE, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI CONTROL_CHANGE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @param noteVelocity
     * @throws InvalidMidiDataException
     */
    public void addControlChangeEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.CONTROL_CHANGE, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI PROGRAM_CHANGE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param instrument
     * @throws InvalidMidiDataException
     */
    public void addProgramChangeEvent(long tick, int trackNumber, int channel, int instrument) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.PROGRAM_CHANGE, tick, trackNumber, channel, instrument, 0);
    }

    /**
     * Generates a MIDI CHANNEL_PRESSURE_CHANGE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @param noteVelocity
     * @throws InvalidMidiDataException
     */
    public void addChannelPressureChangeEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.CHANNEL_PRESSURE, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI PITCH_BEND message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param noteValue
     * @param noteVelocity
     * @throws InvalidMidiDataException
     */
    public void addPitchBendChangeEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.PITCH_BEND, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI SEQUENCE_NUMBER message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
     *
     * @param tick
     * @param trackNumber
     * @param sequenceNumber
     * @throws InvalidMidiDataException
     */
    public void addSequenceNumberEvent(long tick, int trackNumber, int sequenceNumber) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
        if (MidiType.TYPE_ZERO.equals(callee.getMidiType()) || MidiType.TYPE_ONE.equals(callee.getMidiType())) {
            assert trackNumber == 0;
        }

        byte[] data = {
                (byte) ((sequenceNumber & 0x00FF0000) >> 16),
                (byte) ((sequenceNumber & 0x0000FF00) >> 8),
                (byte) (sequenceNumber & 0x000000FF)
        };

        // Remove empty data bytes by right-shifting the start index
        int i = 0;
        while (i < data.length - 1 && data[i] == 0) {
            i++;
        }

        if (i != 0) {
            data = Arrays.copyOfRange(data, i, data.length);
        }

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x00, data, data.length);
        MidiEvent event = new MidiEvent(mm, tick);

        getTrack(callee.getSequence(), trackNumber).add(event);
    }

    /**
     * Generates a MIDI TEXT_EVENT message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
     *
     * @param tick
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addTextEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x01, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI COPYRIGHT_NOTICE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
     *
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addCopyrightNoticeEvent(int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x02, 0, trackNumber, text);
    }

    /**
     * Generates a MIDI TRACK_NAME message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addTrackNameEvent(int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x03, 0, trackNumber, text);
    }

    /**
     * Generates a MIDI INSTRUMENT_NAME message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addInstrumentNameEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x04, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI LYRIC message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addLyricEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x05, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI MARKER message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addMarkerEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x06, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI CUE_POINT message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param trackNumber
     * @param text
     * @throws InvalidMidiDataException
     */
    public void addCuePointEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        addTextEventImpl(0x07, tick, trackNumber, text);
    }

    public void addChannelPrefixEvent() throws InvalidMidiDataException {
        // TODO
    }

    public void addEndOfTrackEvent() throws InvalidMidiDataException {
        /*
         * TODO: this method is likely unnecessary,
         *  since all constructors for Track internally add and manage
         *  the "end of track" message on every Track.
         */
    }

    /**
     *
     * @param tick
     * @param tempo
     * @throws InvalidMidiDataException
     */
    public void addSetTempoEvent(long tick, Tempo tempo) throws InvalidMidiDataException {
        long usecPerPulse =  Math.round(MidiConstants.DEFAULT_USEC_PER_PULSE / (tempo.getBeatsPerMinute() / MidiConstants.DEFAULT_TEMPO_BPM));

        byte[] data = {
                (byte) ((usecPerPulse & 0xFF000000) >> 24),
                (byte) ((usecPerPulse & 0x00FF0000) >> 16),
                (byte) ((usecPerPulse & 0x0000FF00) >> 8),
                (byte) (usecPerPulse & 0x000000FF)
        };

        // Remove empty data bytes by right-shifting the start index
        int i = 0;
        while (i < data.length - 1 && data[i] == 0) {
            i++;
        }

        if (i != 0) {
            data = Arrays.copyOfRange(data, i, data.length);
        }

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x51, data, 3);
        MidiEvent event = new MidiEvent(mm, tick);

        // ALL MIDI types (0, 1, 2) should place this event on Track 0.
        getTrack(callee.getSequence(), 0).add(event);
    }

    public void addSMPTEOffsetEvent() throws InvalidMidiDataException {
        // TODO
    }

    public void addTimeSignatureEvent() throws InvalidMidiDataException {
        // TODO
    }

    public void addKeySignatureEvent() throws InvalidMidiDataException {
        // TODO
    }

    private static double calculateTickCount(@NotNull Note note, @NotNull Tempo tempo, boolean round) {
        double articulationDurationFactor = 1.0;
        if (note.getArticulation() != null) {
            articulationDurationFactor = note.getArticulation().getDurationFactor();
        }
        double toReturn = articulationDurationFactor * calculateTickCount(note.getBeat(), tempo, false);
        return round ? Math.round(toReturn) : toReturn;
    }

    private static double calculateTickCount(@NotNull Beat beat, @NotNull Tempo tempo, boolean round) {
        double ratio = tempo.getReferenceBeat().getDuration() / beat.getDuration();
        double toReturn = MidiConstants.DEFAULT_TICK_RESOLUTION / ratio;
        return round ? Math.round(toReturn) : toReturn;
    }

    @Override
    public void addEvents(Pitch pitch) throws InvalidMidiDataException {
        long tick = callee.getTick();
        addNoteOnEvent(tick, callee.getTrackNumber(), callee.getChannel(), pitch.getMidiValue(), MidiConstants.DEFAULT_VELOCITY);
        tick += calculateTickCount(callee.getTempo().getReferenceBeat(), callee.getTempo(), true);
        addNoteOffEvent(tick, callee.getTrackNumber(), callee.getChannel(), pitch.getMidiValue());
    }

    @Override
    public void addEvents(HorizontalIntervalSet horizontalIntervalSet) throws InvalidMidiDataException {
        Pitch[] pitches = horizontalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5);
        long tick = callee.getTick();
        for (Pitch p : pitches) {
            Note note = Note.builder(p, Beat.QUARTER).build();
            addNoteOnEvent(tick, callee.getTrackNumber(), callee.getChannel(), note);
            tick += calculateTickCount(note, callee.getTempo(), true);
            addNoteOffEvent(tick, callee.getTrackNumber(), callee.getChannel(), note);
        }
    }

    @Override
    public void addEvents(VerticalIntervalSet verticalIntervalSet) throws InvalidMidiDataException {
        List<Note> notes = Arrays.stream(verticalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5))
                .map(p -> Note.builder(p, Beat.WHOLE).build())
                .collect(Collectors.toList());

        long startTick = callee.getTick();
        long endTick = startTick + (long) calculateTickCount(Beat.WHOLE, callee.getTempo(), true);
        for (Note note : notes) {
            addNoteOnEvent(startTick, callee.getTrackNumber(), callee.getChannel(), note);
            addNoteOffEvent(endTick, callee.getTrackNumber(), callee.getChannel(), note);
        }
    }

    @Override
    public void addEvents(Note note) throws InvalidMidiDataException {
        long tick = callee.getTick();
        addNoteOnEvent(tick, callee.getTrackNumber(), callee.getChannel(), note);
        tick += calculateTickCount(note, callee.getTempo(), true);
        addNoteOffEvent(tick, callee.getTrackNumber(), callee.getChannel(), note);
    }

    @Override
    public void addEvents(Measure measure) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(Cell cell) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(Motif motif) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(PhraseMember phraseMember) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(Phrase phrase) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(PhraseGroup phraseGroup) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(Period period) throws InvalidMidiDataException {
        // TODO
    }

    @Override
    public void addEvents(DoublePeriod doublePeriod) throws InvalidMidiDataException {
        // TODO
    }
}
