package chordinnate.midi.producer;

import chordinnate.config.MidiConfig;
import chordinnate.midi.ControllerChange;
import chordinnate.midi.MidiOutputRouter;
import chordinnate.model.musictheory.melody.form.Cell;
import chordinnate.model.musictheory.melody.form.DoublePeriod;
import chordinnate.model.musictheory.melody.form.Measure;
import chordinnate.model.musictheory.melody.form.Motif;
import chordinnate.model.musictheory.melody.form.Period;
import chordinnate.model.musictheory.melody.form.Phrase;
import chordinnate.model.musictheory.melody.form.PhraseGroup;
import chordinnate.model.musictheory.melody.form.PhraseMember;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.Note;
import chordinnate.model.musictheory.notation.Rest;
import chordinnate.model.musictheory.notation.Score;
import chordinnate.model.musictheory.notation.Staff;
import chordinnate.model.musictheory.notation.StaffGroup;
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
import chordinnate.model.playback.Rhythmic;
import chordinnate.util.ContextProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Instrument;
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
public class MidiEventProducer {

    private static final MidiOutputRouter ROUTER = ContextProvider.getContext().getBean(MidiOutputRouter.class);

    private final Sequence sequence;
    private final MidiConfig config;

    private long currentTick = 0;
    private int currentTrack = MidiConfig.DEFAULT_TRACK_NUMBER;

    /**
     * Gets the specified {@link Track} from the {@link Sequence}, if it exists.
     * If it does not exist, then it will continue to create tracks
     * until {@code trackNumber} tracks have been be created, and then
     * return that {@link Track}.
     * @param sequence the sequence containing the track to get
     * @param trackNumber zero-based index for which track to get from the sequence
     * @return the nth track in the sequence, where {@code n == trackNumber}
     */
    protected final Track getTrack(Sequence sequence, int trackNumber) {
        Track[] tracks = sequence.getTracks();
        int length = tracks.length - 1;
        while (length < trackNumber) {
            sequence.createTrack();
            length++;
        }
        return sequence.getTracks()[trackNumber];
    }

    private void internalAddVoiceEvent(int command, long tick, int trackNumber,
                                       int channel, int data1, int data2) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
//        assert config.getMidiType() != 0 && config.getMidiType() != 1 || trackNumber == 0;

        ShortMessage sm = new ShortMessage();
        sm.setMessage(command, channel, data1, data2);
        MidiEvent event = new MidiEvent(sm, tick);

        getTrack(sequence, trackNumber).add(event);
    }

    private void internalAddTextEvent(int command, long tick, int trackNumber, String text) throws InvalidMidiDataException {

        if (StringUtils.isNotBlank(text)) {

            // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
//            assert config.getMidiType() != 0 && config.getMidiType() != 1 || trackNumber == 0;

            byte[] data = text.getBytes(StandardCharsets.US_ASCII);

            MetaMessage mm = new MetaMessage();
            mm.setMessage(command, data, data.length);
            MidiEvent event = new MidiEvent(mm, tick);

            getTrack(sequence, trackNumber).add(event);
        }
    }

    /**
     * Generates a MIDI NOTE_ON message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param note a {@link Note} representing MIDI data to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addNoteOnEvent(long tick, int trackNumber, int channel, @NotNull Note note) throws InvalidMidiDataException {
        for (Pitch pitch : note.getPitches()) {
            addNoteOnEvent(tick, trackNumber, channel, pitch.getMidiValue(), note.getDynamic() != null ? note.getDynamic().getVelocity() : config.getDefaultVelocity());
        }
    }

    /**
     * Generates a MIDI NOTE_ON message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param noteValue the MIDI note value to apply in the event
     * @param noteVelocity the MIDI note velocity to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addNoteOnEvent(long tick, int trackNumber, int channel, int noteValue, int noteVelocity) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.NOTE_ON, tick, trackNumber, channel, noteValue, noteVelocity);
    }

    /**
     * Generates a MIDI NOTE_OFF message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param note a {@link Note} representing MIDI data to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addNoteOffEvent(long tick, int trackNumber, int channel, @NotNull Note note) throws InvalidMidiDataException {
        for (Pitch pitch : note.getPitches()) {
            addNoteOffEvent(tick, trackNumber, channel, pitch.getMidiValue());
        }
    }

    /**
     * Generates a MIDI NOTE_OFF message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param noteValue the MIDI note value to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addNoteOffEvent(long tick, int trackNumber, int channel, int noteValue) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.NOTE_OFF, tick, trackNumber, channel, noteValue, 0);

    }

    /**
     * Generates a MIDI POLY_KEY_PRESSURE_CHANGE message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param noteValue the MIDI note value to apply in the event
     * @param pressureAmount the MIDI pressure amount to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addPolyKeyPressureChangeEvent(long tick, int trackNumber, int channel, int noteValue, int pressureAmount) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.POLY_PRESSURE, tick, trackNumber, channel, noteValue, pressureAmount);
    }

    /**
     * Generates a MIDI CONTROL_CHANGE message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (pp. 41; 102 - 103)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param controllerType the MIDI controller type to apply in the event
     * @param value the value for the MIDI controller type to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addControlChangeEvent(long tick, int trackNumber, int channel, int controllerType, int value) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.CONTROL_CHANGE, tick, trackNumber, channel, controllerType, value);
    }

    /**
     * Generates a MIDI PROGRAM_CHANGE message. Assumes the currently-loaded sound bank is to be used with this event.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param programNumber the MIDI program number (instrument) to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addProgramChangeEvent(long tick, int trackNumber, int channel, int programNumber) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.PROGRAM_CHANGE, tick, trackNumber, channel, programNumber, 0);
    }

    /**
     * Generates a MIDI PROGRAM_CHANGE message, using the bank and program number provided by {@code instrument}.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (pp. 41; 45 - 46)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param channel the MIDI channel to apply the event
     * @param instrument a {@link Instrument} representing MIDI instrument data to apply to the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addProgramChangeEvent(long tick, int channel, Instrument instrument) throws InvalidMidiDataException {
        if (instrument != null) {
//            log.info("PROGRAM CHANGE EVENT. TRACK: {} CHANNEL: {} INSTRUMENT: {}", currentTrack, channel, instrument.getName());
            internalAddVoiceEvent(ShortMessage.CONTROL_CHANGE, tick, currentTrack, channel, ControllerChange.BANK_SELECT_MSB, toMSB(instrument.getPatch().getBank()));
            internalAddVoiceEvent(ShortMessage.CONTROL_CHANGE, tick, currentTrack, channel, ControllerChange.BANK_SELECT_LSB, toLSB(instrument.getPatch().getBank()));
            internalAddVoiceEvent(ShortMessage.PROGRAM_CHANGE, tick, currentTrack, channel, instrument.getPatch().getProgram(), 0);
        }
    }

    /**
     * Generates a MIDI CHANNEL_PRESSURE_CHANGE message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param pressureAmount the MIDI pressure amount to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addChannelPressureChangeEvent(long tick, int trackNumber, int channel, int pressureAmount) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.CHANNEL_PRESSURE, tick, trackNumber, channel, pressureAmount, 0);
    }

    /**
     * Generates a MIDI PITCH_BEND message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 41)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @param pitchBendValue the MIDI pitch bend value to apply in the event (-8192 to 8191)
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addPitchBendChangeEvent(long tick, int trackNumber, int channel, int pitchBendValue) throws InvalidMidiDataException {
        internalAddVoiceEvent(ShortMessage.PITCH_BEND, tick, trackNumber, channel, toLSB(pitchBendValue), toMSB(pitchBendValue));
    }

    /**
     * Generates a MIDI SEQUENCE_NUMBER message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 137)</a>
     *
     * @param trackNumber the sequence track to apply the event
     * @param sequenceNumber the MIDI sequence number to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addSequenceNumberEvent(int trackNumber, int sequenceNumber) throws InvalidMidiDataException {

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
//        assert config.getMidiType() != 0 && config.getMidiType() != 1 || trackNumber == 0;

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
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 137)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addTextEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x01, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI COPYRIGHT_NOTICE message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 137)</a>
     *
     * @param trackNumber the sequence track to apply in the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addCopyrightNoticeEvent(int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x02, 0, trackNumber, text);
    }

    /**
     * Generates a MIDI TRACK_NAME message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param trackNumber the sequence track to apply in the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addTrackNameEvent(int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x03, 0, trackNumber, text);
    }

    /**
     * Generates a MIDI INSTRUMENT_NAME message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addInstrumentNameEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x04, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI LYRIC message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addLyricEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x05, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI MARKER message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addMarkerEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x06, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI CUE_POINT message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param text the text to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addCuePointEvent(long tick, int trackNumber, String text) throws InvalidMidiDataException {
        internalAddTextEvent(0x07, tick, trackNumber, text);
    }

    /**
     * Generates a MIDI CHANNEL_PREFIX message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param trackNumber the sequence track to apply the event
     * @param channel the MIDI channel to apply the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addChannelPrefixEvent(long tick, int trackNumber, int channel) throws InvalidMidiDataException {
        byte[] data = {(byte) channel};

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x20, data, data.length);
        MidiEvent event = new MidiEvent(mm, tick);

        getTrack(sequence, trackNumber).add(event);
    }

    /**
     * Generates a MIDI SET_TEMPO message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 138)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param tempo a {@link Tempo} object representing MIDI tempo data to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addSetTempoEvent(long tick, @NotNull Tempo tempo) throws InvalidMidiDataException {

        long usPerPulse = Math.round((double) MidiConfig.DEFAULT_MICROSECONDS_PER_PULSE / tempo.getBeatsPerMinute());

        byte[] data = {
                (byte) ((usPerPulse & 0xFF000000) >> 24),
                (byte) ((usPerPulse & 0x00FF0000) >> 16),
                (byte) ((usPerPulse & 0x0000FF00) >> 8),
                (byte) (usPerPulse & 0x000000FF)
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
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (pp. 138 - 139)</a>
     *
     * @param time
     * @param subFrame (0 - 99)
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addSMPTEOffsetEvent(LocalTime time, int subFrame) throws InvalidMidiDataException {

        if (config.isMidiTimeCodeEnabled()) {

            float frames = config.getFrames();

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
                    (byte) (subFrame % 100)
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
    }

    /**
     * Generates a MIDI TIME_SIGNATURE message.
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 139)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param timeSignature a {@link TimeSignature} representing MIDI time signature data to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addTimeSignatureEvent(long tick, @NotNull TimeSignature timeSignature) throws InvalidMidiDataException {
        if (timeSignature.getReferenceBeat() != null) {

            double denom = timeSignature.getDenominator().doubleValue();

            Fraction beatValue = timeSignature.getReferenceBeat().getBeatValue();
            Fraction tickCountPerReferenceBeat = beatValue.multiplyBy(Fraction.getFraction(config.getTickResolution() / 4, 1));

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
     *
     * @see <a href=https://www.midi.org/specifications-old/item/the-midi-1-0-specification>The Complete MIDI 1.0 Detailed Specification (p. 139)</a>
     *
     * @param tick the starting MIDI tick for the event
     * @param keySignature a {@link KeySignature} representing MIDI key signature data to apply in the event
     * @throws InvalidMidiDataException if MIDI data would be invalid upon creation of the event
     */
    public final void addKeySignatureEvent(long tick, @NotNull KeySignature keySignature) throws InvalidMidiDataException {

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

    private double calculateTickCount(@NotNull Note note) {
        double articulationDurationFactor = 1.0;
        if (note.getArticulation() != null) {
            articulationDurationFactor = note.getArticulation().getDurationFactor();
        }
        double toReturn = articulationDurationFactor * calculateTickCount(note.getBeat(), false);
        return Math.round(toReturn);
    }

    private  double calculateTickCount(@NotNull Beat beat, boolean round) {
        double ratio = config.getDefaultTempo().getReferenceBeat().getDuration() / beat.getDuration();
        double toReturn = config.getTickResolution() / ratio;
        return round ? Math.round(toReturn) : toReturn;
    }

    private static byte toLSB(int value) {
        return (byte) (value & 0x7F);
    }

    private static byte toMSB(int value) {
        return (byte) ((value >> 7) & 0x7F);
    }

    public final void addEvents(@NotNull Pitch pitch) throws InvalidMidiDataException {
        addNoteOnEvent(currentTick, currentTrack, ROUTER.getChannel(pitch), pitch.getMidiValue(), config.getDefaultVelocity());
        currentTick += calculateTickCount(config.getDefaultTempo().getReferenceBeat(), true);
        addNoteOffEvent(currentTick, currentTrack, ROUTER.getChannel(pitch), pitch.getMidiValue());
    }

    public final void addEvents(@NotNull HorizontalIntervalSet horizontalIntervalSet) throws InvalidMidiDataException {
        Pitch[] pitches = horizontalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5);
        for (Pitch p : pitches) {
            Note note = Note.builder(config.getDefaultTempo().getReferenceBeat(), p).build();
            int channel = ROUTER.getChannel(note);
            addNoteOnEvent(currentTick, currentTrack, channel, note);
            currentTick += calculateTickCount(note);
            addNoteOffEvent(currentTick, currentTrack, channel, note);
        }
    }

    public final void addEvents(@NotNull VerticalIntervalSet verticalIntervalSet) throws InvalidMidiDataException {
        List<Note> notes = Arrays.stream(verticalIntervalSet.getPitchesForOctave(Octave.OCTAVE_5))
                .map(p -> Note.builder(config.getDefaultTempo().getReferenceBeat(), p).build())
                .collect(Collectors.toList());

        long endTick = currentTick + (long) calculateTickCount(config.getDefaultTempo().getReferenceBeat(), true);
        for (Note note : notes) {
            int channel = ROUTER.getChannel(note);
            addNoteOnEvent(currentTick, currentTrack, channel, note);
            addNoteOffEvent(endTick, currentTrack, channel, note);
        }
        currentTick = endTick;
    }

    public final void addEvents(@NotNull Note note) throws InvalidMidiDataException {
//        log.info("NOTE EVENTS. TRACK: {} CHANNEL: {}", currentTrack, ROUTER.getChannel(note));
        internalAddEvents(note, currentTrack, ROUTER.getChannel(note));
    }

    private void internalAddEvents(@NotNull Note note, int track, int channel) throws InvalidMidiDataException {

        // Begin any new, non-tied notes
        for (Pitch pitch : note.getPitches()) {
            if (!note.getSharedPitchesOnLeft().contains(pitch)) {
                addNoteOnEvent(currentTick, track, channel, pitch.getMidiValue(), note.getDynamic() != null ? note.getDynamic().getVelocity() : config.getDefaultVelocity());
            }
        }

        long totalTick = currentTick + (long) calculateTickCount(note);

        // TODO: apply instrument effects. Also take into consideration the effects on tied notes
//        if (InstrumentEffect.BEND.equals(note.getEffect())) {
//            for (long i = tick; i < totalTick; i += 4) {
//                addPitchBendChangeEvent(i, track, channel, (int) (i * 45));
//            }
//        }

        currentTick = totalTick;

        // End any tied notes
        for (Pitch pitch : note.getPitches()) {
            if (!note.getSharedPitchesOnRight().contains(pitch)) {
                addNoteOffEvent(currentTick, track, channel, pitch.getMidiValue());
            }
        }

    }

    public final void addEvents(@NotNull Rest rest) throws InvalidMidiDataException {
        currentTick += calculateTickCount(rest.getBeat(), true);
    }

    public final void addEvents(@NotNull Measure measure) throws InvalidMidiDataException {
//        log.info("MEASURE EVENTS. TRACK: {} CHANNEL: {}", currentTrack, ROUTER.getChannel(measure));

        if (measure.getTempo() != null) {
            addSetTempoEvent(currentTick, measure.getTempo());
        }
        if (!TimeSignature.NONE.equals(measure.getTimeSignature())) {
            addTimeSignatureEvent(currentTick, measure.getTimeSignature());
        }
        if (!KeySignature.NO_KEY_SIGNATURE.equals(measure.getKeySignature())) {
            addKeySignatureEvent(currentTick, measure.getKeySignature());
        }

        for (Rhythmic rhythmic : measure.getRhythm()) {
            rhythmic.accept(this);
        }
    }

    public final void addEvents(@NotNull Cell cell) throws InvalidMidiDataException {
        addEvents(cell.getMeasure());
    }

    public final void addEvents(@NotNull Motif motif) throws InvalidMidiDataException {
        for (Cell cell : motif.getCells()) {
            addEvents(cell);
        }
    }

    public final void addEvents(@NotNull PhraseMember phraseMember) throws InvalidMidiDataException {
        for (Motif motif : phraseMember.getMotifs()) {
            addEvents(motif);
        }
    }

    public final void addEvents(@NotNull Phrase phrase) throws InvalidMidiDataException {
        for (PhraseMember phraseMember : phrase.getPhraseMembers()) {
            addEvents(phraseMember);
        }
    }

    public final void addEvents(@NotNull PhraseGroup phraseGroup) throws InvalidMidiDataException {
        for (Phrase phrase : phraseGroup.getPhrases()) {
            addEvents(phrase);
        }
    }

    public final void addEvents(@NotNull Period period) throws InvalidMidiDataException {
        addEvents(period.getPhrase1());
        addEvents(period.getPhrase2());
    }

    public final void addEvents(@NotNull DoublePeriod doublePeriod) throws InvalidMidiDataException {
        addEvents(doublePeriod.getPeriod1());
        addEvents(doublePeriod.getPeriod2());
    }

    public void addEvents(@NotNull Staff staff) throws InvalidMidiDataException {
        internalAddEvents(staff);
    }

    private void internalAddEvents(@NotNull Staff staff) throws InvalidMidiDataException {
//        addSequenceNumberEvent(trackNumber, currentTrack); TODO
        addTrackNameEvent(currentTrack, staff.getStaffName());
        addProgramChangeEvent(currentTick, ROUTER.getChannel(staff), ROUTER.getInstrument(staff));

        // add all the events for the current Staff. Mutates this.currentTick as a side-effect
        staff.getPlayable().accept(this);

        // reset the tick for the next Staff
        this.currentTick = 0;
    }

    public void addEvents(@NotNull StaffGroup staffGroup) throws InvalidMidiDataException {
        for (Staff staff : staffGroup.getStaves()) {
            internalAddEvents(staff);
            currentTrack++;
        }
    }

    public final void addEvents(@NotNull Score score) throws InvalidMidiDataException {
        for (StaffGroup staffGroup : score.getStaffGroups()) {
            addEvents(staffGroup);
        }
    }
}
