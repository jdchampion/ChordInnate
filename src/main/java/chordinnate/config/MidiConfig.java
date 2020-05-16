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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Validated
@Configuration
@PropertySource("classpath:midi.properties")
public class MidiConfig {

    public static final int DEFAULT_USEC_PER_PULSE = 60_000_000; // microseconds, used for computing ratios
    public static final double DEFAULT_TEMPO_BPM = 120.0; // used for computing ratios

    @Min(0)
    @Max(2)
    @Value("${midi.defaults.type}")
    private int midiType;

    @Min(0)
    @Value("${midi.defaults.track}")
    private int track;

    @Min(0)
    @Max(15)
    @Value("${midi.defaults.channel}")
    private int channel;

    @Min(0)
    @Max(127)
    @Value("${midi.defaults.instrument}")
    private int instrument;

    @Min(0)
    @Max(127)
    @Value("${midi.defaults.velocity}")
    private int velocity;

    private Tempo tempo;

    private TimeSignature timeSignature;

    private KeySignature keySignature;

    @In(values={"0.0", "24.0", "25.0", "29.97", "30.0"})
    @Value("${midi.defaults.frames}")
    private float frames;

    @Min(96)
    @Value("${midi.defaults.resolution}")
    private int tickResolution;

    public Tempo getTempo() {
        if (tempo == null) {
            this.tempo = new Tempo(Beat.QUARTER, (int) DEFAULT_TEMPO_BPM);
        }

        return tempo;
    }

    public TimeSignature getTimeSignature() {
        if (timeSignature == null) {
            this.timeSignature = TimeSignature.NONE;
        }

        return timeSignature;
    }

    public KeySignature getKeySignature() {
        if (keySignature == null) {
            this.keySignature = KeySignature.NO_KEY_SIGNATURE;
        }

        return keySignature;
    }
}
