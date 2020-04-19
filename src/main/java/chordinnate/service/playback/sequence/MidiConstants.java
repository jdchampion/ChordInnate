package chordinnate.service.playback.sequence;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.TimeSignature;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiConstants {

    /*
     * TODO: This class currently serves more like a config class.
     *  Would be better to make most of these fields user-configurable.
     */
    public static final MidiType DEFAULT_MIDI_TYPE = MidiType.TYPE_ZERO;
    public static final int DEFAULT_TRACK = 0;
    public static final int DEFAULT_CHANNEL = 0;
    public static final int DEFAULT_INSTRUMENT = 0; // Grand Piano
    public static final int DEFAULT_VELOCITY = 64; // mezzo forte
    public static final Tempo DEFAULT_TEMPO = new Tempo(Beat.QUARTER, 120);
    public static final TimeSignature DEFAULT_TIME_SIGNATURE = TimeSignature.NONE;
    public static final KeySignature DEFAULT_KEY_SIGNATURE = KeySignature.NO_KEY_SIGNATURE;

    public static final int DEFAULT_TICK_RESOLUTION = 96; // number of ticks per reference beat of tempo
    public static final int USEC_PER_REF_BEAT_AT_60_BPM = 1000000; // microseconds
    public static final double SIXTY_BPM = 60.0; // used for computing tempo ratios
}
