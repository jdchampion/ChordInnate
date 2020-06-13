package chordinnate.config;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Validated
@Configuration
@PropertySource("classpath:midi.properties")
public class MidiConfig {

    public static final int DEFAULT_USEC_PER_PULSE = 60_000_000; // microseconds, used for computing ratios
    public static final double DEFAULT_TEMPO_BPM = 120.0; // used for computing ratios
    public static final int DEFAULT_TRACK_NUMBER = 0;
    public static final int MAX_CHANNELS_PER_MIDI_PORT = 16; // limit per MIDI spec
    public static final int DEFAULT_CHANNEL = 0;
    public static final int PERCUSSION_CHANNEL = 9; // designated per MIDI spec
    public static final int MAX_INSTRUMENTS_PER_BANK = 128; // limit per MIDI spec
    public static final int MAX_BANKS_PER_DEVICE = 16384; // limit per MIDI spec


    private Sequencer activeMidiSequencer;

    private Synthesizer activeMidiSynthesizer;

    @Min(0)
    @Max(2)
    @Value("${midi.defaults.type}")
    private int midiType; // TODO: current implementation probably can't support MIDI 0 or MIDI 1

    @Min(0)
    @Max(127)
    @Value("${midi.defaults.instrument}")
    private int defaultInstrument;

    @Min(0)
    @Max(127)
    @Value("${midi.defaults.velocity}")
    private int defaultVelocity;

    private Tempo defaultTempo;

    private TimeSignature defaultTimeSignature;

    private KeySignature defaultKeySignature;

    @In(values={"true", "false"})
    @Value("${midi.defaults.mtc_enabled}")
    private boolean midiTimeCodeEnabled;

    @In(values={"0.0", "24.0", "25.0", "29.97", "30.0"})
    @Value("${midi.defaults.frames}")
    private float frames;

    @Min(96)
    @Value("${midi.defaults.resolution}")
    private int tickResolution;

    @Value("${midi.defaults.copyright}")
    private String copyrightNotice;

    public Tempo getDefaultTempo() {
        if (defaultTempo == null) {
            this.defaultTempo = new Tempo(Beat.QUARTER, (int) DEFAULT_TEMPO_BPM);
        }

        return defaultTempo;
    }

    public TimeSignature getDefaultTimeSignature() {
        if (defaultTimeSignature == null) {
            this.defaultTimeSignature = TimeSignature.NONE;
        }

        return defaultTimeSignature;
    }

    public KeySignature getDefaultKeySignature() {
        if (defaultKeySignature == null) {
            this.defaultKeySignature = KeySignature.NO_KEY_SIGNATURE;
        }

        return defaultKeySignature;
    }
}
