package chordinnate.service.playback.sequence.event;

import chordinnate.config.MidiConfig;
import chordinnate.model.musictheory.melody.form.Cell;
import chordinnate.model.musictheory.melody.form.DoublePeriod;
import chordinnate.model.musictheory.melody.form.Measure;
import chordinnate.model.musictheory.melody.form.Motif;
import chordinnate.model.musictheory.melody.form.Period;
import chordinnate.model.musictheory.melody.form.Phrase;
import chordinnate.model.musictheory.melody.form.PhraseGroup;
import chordinnate.model.musictheory.melody.form.PhraseMember;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.HorizontalIntervalSet;
import chordinnate.model.musictheory.pitch.interval.set.VerticalIntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.pitch.key.KeySignatureType;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.model.playback.Rest;
import chordinnate.model.playback.Rhythmic;
import chordinnate.service.playback.sequence.MidiEventGeneratorCallable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class MidiEventGeneratorImpl implements MidiEventGenerator {

    private final Sequence sequence;
    private final MidiEventGeneratorCallable callee;

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
        assert callee.getConfig().getMidiType() != 0 && callee.getConfig().getMidiType() != 1 || trackNumber == 0;

        ShortMessage sm = new ShortMessage();
        sm.setMessage(command, channel, data1, data2);
        MidiEvent event = new MidiEvent(sm, tick);

        getTrack(sequence, trackNumber).add(event);
    }

    private void addTextEventImpl(int command, long tick, int trackNumber, String text) throws InvalidMidiDataException {

        if (StringUtils.isNotBlank(text)) {

            // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
            assert callee.getConfig().getMidiType() != 0 && callee.getConfig().getMidiType() != 1 || trackNumber == 0;

            byte[] data = text.getBytes(StandardCharsets.US_ASCII);

            MetaMessage mm = new MetaMessage();
            mm.setMessage(command, data, data.length);
            MidiEvent event = new MidiEvent(mm, tick);

            getTrack(sequence, trackNumber).add(event);
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
        for (Pitch pitch : note.getPitches()) {
            addNoteOnEvent(tick, trackNumber, channel, pitch.getMidiValue(), note.getDynamic() != null ? note.getDynamic().getVelocity() : callee.getConfig().getVelocity());
        }
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
        log.info("NOTE ON: {} {} {} {} {}", tick, trackNumber, channel, noteValue, noteVelocity);
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
        for (Pitch pitch : note.getPitches()) {
            addNoteOffEvent(tick, trackNumber, channel, pitch.getMidiValue());
        }
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
        log.info("NOTE OFF: {} {} {} {} {}", tick, trackNumber, channel, noteValue, 0);
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
     * @param pressureAmount
     * @throws InvalidMidiDataException
     */
    public void addPolyKeyPressureChangeEvent(long tick, int trackNumber, int channel, int noteValue, int pressureAmount) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.POLY_PRESSURE, tick, trackNumber, channel, noteValue, pressureAmount);
    }

    /**
     * Generates a MIDI CONTROL_CHANGE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param controllerType
     * @param value
     * @throws InvalidMidiDataException
     */
    public void addControlChangeEvent(long tick, int trackNumber, int channel, int controllerType, int value) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.CONTROL_CHANGE, tick, trackNumber, channel, controllerType, value);
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
     * @param pressureAmount
     * @throws InvalidMidiDataException
     */
    public void addChannelPressureChangeEvent(long tick, int trackNumber, int channel, int pressureAmount) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.CHANNEL_PRESSURE, tick, trackNumber, channel, pressureAmount, 0);
    }

    /**
     * Generates a MIDI PITCH_BEND message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @param valueLSB
     * @param valueMSB
     * @throws InvalidMidiDataException
     */
    public void addPitchBendChangeEvent(long tick, int trackNumber, int channel, int valueLSB, int valueMSB) throws InvalidMidiDataException {
        addVoiceEventImpl(ShortMessage.PITCH_BEND, tick, trackNumber, channel, valueLSB, valueMSB);
    }

    /**
     * Generates a MIDI SEQUENCE_NUMBER message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
     *
     * @param trackNumber
     * @param sequenceNumber
     * @throws InvalidMidiDataException
     */
    public void addSequenceNumberEvent(int trackNumber, int sequenceNumber) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
        assert callee.getConfig().getMidiType() != 0 && callee.getConfig().getMidiType() != 1 || trackNumber == 0;

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
        MidiEvent event = new MidiEvent(mm, 0);

        getTrack(sequence, trackNumber).add(event);
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

    /**
     * Generates a MIDI CHANNEL_PREFIX message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param trackNumber
     * @param channel
     * @throws InvalidMidiDataException
     */
    public void addChannelPrefixEvent(long tick, int trackNumber, int channel) throws InvalidMidiDataException {
        byte[] data = {(byte) channel};

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x20, data, data.length);
        MidiEvent event = new MidiEvent(mm, tick);

        getTrack(sequence, trackNumber).add(event);
    }

    public void addEndOfTrackEvent() throws InvalidMidiDataException {
        /*
         * TODO: this method is likely unnecessary,
         *  since all constructors for Track internally add and manage
         *  the "end of track" message on every Track.
         */
    }

    /**
     * Generates a MIDI SET_TEMPO message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
     *
     * @param tick
     * @param tempo
     * @throws InvalidMidiDataException
     */
    public void addSetTempoEvent(long tick, @NotNull Tempo tempo) throws InvalidMidiDataException {

        log.info("SET TEMPO: {} {}", tick, tempo.toString());

        long usecPerPulse = Math.round( (double) MidiConfig.DEFAULT_USEC_PER_PULSE / tempo.getBeatsPerMinute());

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
        getTrack(sequence, 0).add(event);
    }

    /**
     * Generates a MIDI SMPTE_OFFSET message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (pp. 138 - 139)
     *
     * @param time
     * @param subFrame (0 - 99)
     * @throws InvalidMidiDataException
     */
    public void addSMPTEOffsetEvent(LocalTime time, byte subFrame) throws InvalidMidiDataException {

        float frames = callee.getConfig().getFrames();

        int bit;
        if (frames == Sequence.PPQ || frames == Sequence.SMPTE_24) {
            bit = 0;
        } else if (frames == Sequence.SMPTE_25) {
            bit = 64;
        } else if (frames == Sequence.SMPTE_30) {
            bit = 128;
        } else if (frames == Sequence.SMPTE_30DROP) {
            bit = 192;
        } else {
            bit = 0;
        }

        byte[] data = {
                (byte) ((bit & 0xFF) | time.getHour()), // Format: 0xrrhhhhh where rr = frame bit, hhhhh = hour
                (byte) time.getMinute(),
                (byte) time.getSecond(),
                (byte) frames,
                subFrame
        };

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x54, data, data.length);
        MidiEvent event = new MidiEvent(mm, 0); // always at the beginning of the track

        /*
         * Per specs, MIDI type 1 must "store this event with the tempo map."
         * Nothing mentioned for MIDI type 0 or 2, so we'll do the same for those.
         */
        getTrack(sequence, 0).add(event);
    }

    /**
     * Generates a MIDI TIME_SIGNATURE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 139)
     *
     * @param tick
     * @param timeSignature
     * @throws InvalidMidiDataException
     */
    public void addTimeSignatureEvent(long tick, @NotNull TimeSignature timeSignature) throws InvalidMidiDataException {
        if (timeSignature.getReferenceBeat() != null) {

            log.info("ADD TIME SIGNATURE: {} {}", tick, timeSignature.toString());

            double denom = timeSignature.getDenominator().doubleValue();

            Fraction beatValue = timeSignature.getReferenceBeat().getBeatValue();
            Fraction tickCountPerReferenceBeat = beatValue.multiplyBy(Fraction.getFraction(callee.getConfig().getTickResolution() / 4, 1));

            int num32nds = beatValue.divideBy(Beat.THIRTY_SECOND.getBeatValue()).intValue();

            int denominator = (int) Math.pow(denom, -2);

            byte[] data = {
                    (byte) timeSignature.getNumerator().intValue(),
                    (byte) denominator,
                    (byte) tickCountPerReferenceBeat.intValue(),
                    (byte) num32nds
            };

            MetaMessage mm = new MetaMessage();
            mm.setMessage(0x58, data, data.length);
            MidiEvent event = new MidiEvent(mm, tick);

            /*
             * MIDI type 2 must add this event at the same time on every track.
             * Since MIDI type 0 and 1 have a single track, this also works for them.
             */
            for (Track track : sequence.getTracks()) {
                track.add(event);
            }
        }
    }

    /**
     * Generates a MIDI KEY_SIGNATURE message.
     * Reference: The Complete MIDI 1.0 Detailed Specification (p. 139)
     *
     * @param tick
     * @param keySignature
     * @throws InvalidMidiDataException
     */
    public void addKeySignatureEvent(long tick, @NotNull KeySignature keySignature) throws InvalidMidiDataException {

        if (KeySignature.NO_KEY_SIGNATURE.equals(keySignature)) {
            return;
        }

        /*
         * MIDI only supports a limited range of key signatures,
         * from 7 flats (-7) to 7 sharps (+7).
         */
        int flats = 0;
        int sharps = 0;
        for (PitchClass pitchClass : keySignature.getSignature()) {
            Set<Accidental> set = new HashSet<>(Accidental.simplify(pitchClass.getAccidentals(), false));
            if (set.contains(Accidental.FLAT) || set.contains(Accidental.DOUBLE_FLAT)) {
                flats++;
            } else if (set.contains(Accidental.SHARP) || set.contains(Accidental.DOUBLE_SHARP)) {
                sharps++;
            }
        }

        int numAccidentals = Math.max(flats, sharps);

        // MIDI only supports major (0) and minor (1) key signatures
        int type = KeySignatureType.MAJOR.equals(keySignature.getType()) ? 0 : 1;

        byte[] data = {
                (byte) (numAccidentals == 0 ? 0 : (numAccidentals == flats ? -numAccidentals : numAccidentals)),
                (byte) type
        };

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x59, data, data.length);
        MidiEvent event = new MidiEvent(mm, tick);

        for (Track track : sequence.getTracks()) {
            track.add(event);
        }
    }

    private static double calculateTickCount(@NotNull Note note, @NotNull MidiConfig config, boolean round) {
        double articulationDurationFactor = 1.0;
        if (note.getArticulation() != null) {
            articulationDurationFactor = note.getArticulation().getDurationFactor();
        }
        double toReturn = articulationDurationFactor * calculateTickCount(note.getBeat(), config, false);
        return round ? Math.round(toReturn) : toReturn;
    }

    private static double calculateTickCount(@NotNull Beat beat, @NotNull MidiConfig config, boolean round) {
        double ratio = config.getTempo().getReferenceBeat().getDuration() / beat.getDuration();
        double toReturn = config.getTickResolution() / ratio;
        return round ? Math.round(toReturn) : toReturn;
    }

    @Override
    public void addEvents(Pitch pitch) throws InvalidMidiDataException {
        long tick = callee.getTick();
        addNoteOnEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), pitch.getMidiValue(), callee.getConfig().getVelocity());
        tick += calculateTickCount(callee.getConfig().getTempo().getReferenceBeat(), callee.getConfig(), true);
        addNoteOffEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), pitch.getMidiValue());
        callee.setTick(tick);
    }

    @Override
    public void addEvents(HorizontalIntervalSet horizontalIntervalSet) throws InvalidMidiDataException {
        Pitch[] pitches = horizontalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5);
        long tick = callee.getTick();
        for (Pitch p : pitches) {
            Note note = Note.builder(Beat.QUARTER, p).build();
            addNoteOnEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
            tick += calculateTickCount(note, callee.getConfig(), true);
            addNoteOffEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
        }
        callee.setTick(tick);

        log.info("RESOLUTION: {}", sequence.getResolution());
    }

    @Override
    public void addEvents(VerticalIntervalSet verticalIntervalSet) throws InvalidMidiDataException {
        List<Note> notes = Arrays.stream(verticalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5))
                .map(p -> Note.builder(Beat.WHOLE, p).build())
                .collect(Collectors.toList());

        long startTick = callee.getTick();
        long endTick = startTick + (long) calculateTickCount(Beat.WHOLE, callee.getConfig(), true);
        for (Note note : notes) {
            addNoteOnEvent(startTick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
            addNoteOffEvent(endTick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
        }
        callee.setTick(endTick);
    }

    @Override
    public void addEvents(Note note) throws InvalidMidiDataException {
        long tick = callee.getTick();
        addNoteOnEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
        tick += calculateTickCount(note, callee.getConfig(), true);
        addNoteOffEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), note);
        callee.setTick(tick);
    }

    @Override
    public void addEvents(Measure measure) throws InvalidMidiDataException {

        long tick = callee.getTick();

        if (measure.getTempo() != null) {
            addSetTempoEvent(tick, measure.getTempo());
        }
        if (!TimeSignature.NONE.equals(measure.getTimeSignature())) {
            addTimeSignatureEvent(tick, measure.getTimeSignature());
        }
        if (!KeySignature.NO_KEY_SIGNATURE.equals(measure.getKeySignature())) {
            addKeySignatureEvent(tick, measure.getKeySignature());
        }

        for (Rhythmic rhythmic : measure.getRhythm()) {

            // Case: rests
            if (rhythmic instanceof Rest) {
                tick += calculateTickCount(rhythmic.getBeat(), callee.getConfig(), true);
                continue;
            }

            // Case: sounded notes
            Note note = (Note) rhythmic;

            for (Pitch pitch : note.getPitches()) {
                if (!note.getSharedPitchesOnLeft().contains(pitch)) {
                    addNoteOnEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), pitch.getMidiValue(), note.getDynamic() != null ? note.getDynamic().getVelocity() : callee.getConfig().getVelocity());
                }
            }

            tick += calculateTickCount(note, callee.getConfig(), true);

            for (Pitch pitch : note.getPitches()) {
                if (!note.getSharedPitchesOnRight().contains(pitch)) {
                    addNoteOffEvent(tick, callee.getConfig().getTrack(), callee.getConfig().getChannel(), pitch.getMidiValue());
                }
            }

            callee.setTick(tick);
        }
    }

    @Override
    public void addEvents(Cell cell) throws InvalidMidiDataException {
        addEvents(cell.getMeasure());
    }

    @Override
    public void addEvents(Motif motif) throws InvalidMidiDataException {
        for (Cell cell : motif.getCells()) {
            addEvents(cell);
        }
    }

    @Override
    public void addEvents(PhraseMember phraseMember) throws InvalidMidiDataException {
        for (Motif motif : phraseMember.getMotifs()) {
            addEvents(motif);
        }
    }

    @Override
    public void addEvents(Phrase phrase) throws InvalidMidiDataException {
        for (PhraseMember phraseMember : phrase.getPhraseMembers()) {
            addEvents(phraseMember);
        }
    }

    @Override
    public void addEvents(PhraseGroup phraseGroup) throws InvalidMidiDataException {
        for (Phrase phrase : phraseGroup.getPhrases()) {
            addEvents(phrase);
        }
    }

    @Override
    public void addEvents(Period period) throws InvalidMidiDataException {
        addEvents(period.getPhrase1());
        addEvents(period.getPhrase2());
    }

    @Override
    public void addEvents(DoublePeriod doublePeriod) throws InvalidMidiDataException {
        addEvents(doublePeriod.getPeriod1());
        addEvents(doublePeriod.getPeriod2());
    }
}
