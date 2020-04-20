package chordinnate.service.playback.sequence.event;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.TimeSignature;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.service.playback.sequence.MidiConstants;
import chordinnate.service.playback.sequence.MidiType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class works as a context object to provide {@link MidiEventGenerator}
 * any and all required data for processing and creating the desired MIDI event.
 *
 * Since the implementations and intended usage of each {@link MidiEventGenerator} differ,
 * some items instantiated in this class may be extraneous to a particular {@link MidiEventGenerator}.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiEventDataBundle {

    /**
     * MIDI types: 0, 1, 2
     */
    private MidiType midiType;

    private int sequenceNumber;

    private String text;
    private String copyright;
    private String trackName;
    private String instrumentName;
    private String lyric;
    private String marker;
    private String cuePoint;

    private TimeSignature timeSignature;

    private KeySignature keySignature;

    private Tempo tempo;

    private int trackNumber;

    /**
     * MIDI code: 0 - 15
     */
    private int channel;

    private long startTick;

    /**
     * Automatically calculated when a {@link Note} is provided to the {@link MidiEventDataBundle},
     * and optionally, {@link Tempo}.
     *
     * @see MidiEventDataBundle#setNote(Note)
     * @see MidiEventDataBundle#setTempo(Tempo)
     */
    private long endTick;

    /**
     * MIDI code: 0 - 127
     */
    private int instrument;

    /**
     * MIDI code: 0 - 127<p/>
     * Can be provided directly if no {@link Note} is provided to the {@link MidiEventDataBundle}.
     * If a {@link Note} is provided, its value will automatically be calculated.
     *
     * @see MidiEventDataBundle#setNote(Note)
     */
    private int velocity;

    /**
     * MIDI code: 0 - 127<p/>
     * Can be provided directly if no {@link Note} is provided to the {@link MidiEventDataBundle}.
     * If a {@link Note} is provided, its value will automatically be calculated.
     *
     * @see MidiEventDataBundle#setNote(Note)
     */
    private int noteValue;

    @Nullable
    private Note note;

    /**
     * This field defines the length of time (in usec) for a
     * single pulse ("reference beat") for the current {@link Tempo}.<p/>
     *
     * Updated automatically when a {@link Note} is provided to the {@link MidiEventDataBundle},
     * and optionally, {@link Tempo}. If no {@link Tempo} is provided,
     * the system will use the default tempo to calculate the value.
     *
     * @see MidiEventDataBundle#setNote(Note)
     * @see MidiEventDataBundle#setTempo(Tempo)
     */
    private long microSecondsPerTempoPulse;

    private void setNote(@NotNull Note note) {
        this.note = note;
        this.noteValue = note.getPitch().getMidiValue();
        if (note.getDynamic() != null) {
            this.velocity = note.getDynamic().getVelocity();
        }
        this.endTick = startTick + calculateTickCount(note, tempo);
        this.microSecondsPerTempoPulse = calculateMicrosecondsPerReferenceBeat(tempo);
    }

    private void setTempo(@NotNull Tempo tempo) {
        this.tempo = tempo;
        if (this.note != null) {
            this.endTick = startTick + calculateTickCount(note, tempo);
            this.microSecondsPerTempoPulse = calculateMicrosecondsPerReferenceBeat(tempo);
        }
    }

    private static long calculateTickCount(Note note, Tempo tempo) {

        double articulationDurationFactor = 1.0;
        if (note.getArticulation() != null) {
            articulationDurationFactor = note.getArticulation().getDurationFactor();
        }

        double ratio = tempo.getReferenceBeat().getDuration() / note.getBeat().getDuration();
        return Math.round(articulationDurationFactor * (MidiConstants.DEFAULT_TICK_RESOLUTION / ratio));
    }

    private static long calculateMicrosecondsPerReferenceBeat(Tempo tempo) {
        return Math.round(MidiConstants.DEFAULT_USEC_PER_PULSE / (tempo.getBeatsPerMinute() / MidiConstants.DEFAULT_TEMPO_BPM));
    }

    private static long calculateMillisecondsPerReferenceBeat(Tempo tempo) {
        return calculateMicrosecondsPerReferenceBeat(tempo) / 1000;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private MidiType midiType = MidiConstants.DEFAULT_MIDI_TYPE;
        private int sequenceNumber = 0;
        private String text = "";
        private String copyright = "";
        private String trackName = "";
        private String instrumentName = "";
        private String lyric = "";
        private String marker = "";
        private String cuePoint = "";
        private TimeSignature timeSignature = MidiConstants.DEFAULT_TIME_SIGNATURE; // TODO: set based on config
        private KeySignature keySignature = MidiConstants.DEFAULT_KEY_SIGNATURE; // TODO: set based on config
        private Tempo tempo = MidiConstants.DEFAULT_TEMPO; // TODO: set based on config
        private int trackNumber = MidiConstants.DEFAULT_TRACK;
        private int channel = MidiConstants.DEFAULT_CHANNEL;
        private long startTick = 0;
        private long endTick = 0;
        private int instrument = MidiConstants.DEFAULT_INSTRUMENT; // TODO: set based on config
        private int velocity = MidiConstants.DEFAULT_VELOCITY;
        private int noteValue = -1;
        @Nullable
        private Note note;

        public Builder midiType(@NotNull MidiType midiType) {
            this.midiType = midiType;
            return this;
        }

        public Builder sequenceNumber(int sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder copyright(String copyright) {
            this.copyright = copyright;
            return this;
        }

        public Builder trackName(String trackName) {
            this.trackName = trackName;
            return this;
        }

        public Builder instrumentName(String instrumentName) {
            this.instrumentName = instrumentName;
            return this;
        }

        public Builder lyric(String lyric) {
            this.lyric = lyric;
            return this;
        }

        public Builder marker(String marker) {
            this.marker = marker;
            return this;
        }

        public Builder cuePoint(String cuePoint) {
            this.cuePoint = cuePoint;
            return this;
        }

        public Builder timeSignature(@NotNull TimeSignature timeSignature) {
            this.timeSignature = timeSignature;
            return this;
        }

        public Builder keySignature(@NotNull KeySignature keySignature) {
            this.keySignature = keySignature;
            return this;
        }

        public Builder tempo(@NotNull Tempo tempo) {
            this.tempo = tempo;
            return this;
        }

        public Builder trackNumber(int trackNumber) {
            this.trackNumber = trackNumber;
            return this;
        }

        public Builder channel(int channel) {
            this.channel = channel;
            return this;
        }

        public Builder startTick(long startTick) {
            this.startTick = startTick;
            return this;
        }

        public Builder endTick(long endTick) {
            this.endTick = endTick;
            return this;
        }

        public Builder instrument(int instrument) {
            this.instrument = instrument;
            return this;
        }

        public Builder velocity(int velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder noteValue(int noteValue) {
            this.noteValue = noteValue;
            return this;
        }

        public Builder note(@NotNull Note note) {
            this.note = note;
            return this;
        }

        public MidiEventDataBundle build() {
            MidiEventDataBundle bundle = new MidiEventDataBundle();
            bundle.midiType = this.midiType;
            bundle.sequenceNumber = this.sequenceNumber;
            bundle.text = this.text;
            bundle.copyright = this.copyright;
            bundle.trackName = this.trackName;
            bundle.instrumentName = this.instrumentName;
            bundle.lyric = this.lyric;
            bundle.marker = this.marker;
            bundle.cuePoint = this.cuePoint;
            bundle.timeSignature = this.timeSignature;
            bundle.keySignature = this.keySignature;
            bundle.tempo = this.tempo;
            bundle.trackNumber = this.trackNumber;
            bundle.channel = this.channel;
            bundle.startTick = this.startTick;
            bundle.endTick = this.endTick;
            bundle.instrument = this.instrument;
            bundle.velocity = this.velocity;
            bundle.noteValue = this.noteValue;
            bundle.note = this.note;
            if (note != null) {
                bundle.setNote(note);
            }
            bundle.setTempo(tempo);
            return bundle;
        }
    }

}