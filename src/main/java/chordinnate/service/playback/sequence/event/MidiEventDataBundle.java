package chordinnate.service.playback.sequence.event;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.TimeSignature;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.service.playback.sequence.MidiConstants;
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
    private int midiType;

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
     * Updated automatically when a {@link Note} is provided,
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
     * MIDI code: 0 - 127<br>
     * Updated automatically when a {@link Note} is provided.
     *
     * @see MidiEventDataBundle#setNote(Note)
     */
    private int velocity;

    /**
     * MIDI code: 0 - 127<br>
     * Updated automatically when a {@link Note} is provided.
     *
     * @see MidiEventDataBundle#setNote(Note)
     */
    private int noteValue;

    @Nullable
    private Note note;

    /**
     * Updated automatically when a {@link Note} is provided,
     * and optionally, {@link Tempo}.
     *
     * @see MidiEventDataBundle#setNote(Note)
     * @see MidiEventDataBundle#setTempo(Tempo)
     */
    private long microSecondsPerReferenceBeat;

    private void setNote(@NotNull Note note) {
        this.note = note;
        this.noteValue = note.getPitch().getMidiValue();
        if (note.getDynamic() != null) {
            this.velocity = note.getDynamic().getVelocity();
        }
        this.endTick = startTick + calculateTickCount(note.getBeat(), tempo);
        this.microSecondsPerReferenceBeat = calculateMicrosecondsPerReferenceBeat(tempo);
    }

    private void setTempo(@NotNull Tempo tempo) {
        this.tempo = tempo;
        if (this.note != null) {
            this.endTick = startTick + calculateTickCount(note.getBeat(), tempo);
            this.microSecondsPerReferenceBeat = calculateMicrosecondsPerReferenceBeat(tempo);
        }
    }

    private static long calculateTickCount(Beat beat, Tempo tempo) {
        double ratio = tempo.getReferenceBeat().getDuration() / beat.getDuration();
        return Math.round(MidiConstants.DEFAULT_TICK_RESOLUTION / ratio);
    }

    private static long calculateMicrosecondsPerReferenceBeat(Tempo tempo) {
        return Math.round(MidiConstants.USEC_PER_REF_BEAT_AT_60_BPM / (tempo.getBpm() / MidiConstants.SIXTY_BPM));
    }

    private static long calculateMillisecondsPerReferenceBeat(Tempo tempo) {
        return calculateMicrosecondsPerReferenceBeat(tempo) / 1000;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int midiType = MidiConstants.DEFAULT_MIDI_TYPE;
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

        public Builder midiType(int midiType) {
            this.midiType = midiType;
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