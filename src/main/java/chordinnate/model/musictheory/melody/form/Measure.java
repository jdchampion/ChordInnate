package chordinnate.model.musictheory.melody.form;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.MeterType;
import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.FormPlayable;
import chordinnate.model.playback.Rhythmic;
import chordinnate.util.MathUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Getter
public class Measure extends FormPlayable implements Metered {

    @NotNull
    private TimeSignature timeSignature;

    @NotNull
    private KeySignature keySignature;

    @NotNull
    private List<Rhythmic> rhythm;

    @Nullable
    private Tempo tempo;

    @Nullable
    private String rehearsalMarker;

    @Nullable
    private String comment;

    private double duration;

    public Measure(@NotNull TimeSignature timeSignature, @NotNull KeySignature keySignature, @NotNull List<Rhythmic> rhythm) {
        this.timeSignature = timeSignature;
        this.keySignature = keySignature;
        this.rhythm = rhythm;

        this.duration = determineDuration(timeSignature, getMeterTypes());

        if (!validate(timeSignature, rhythm, duration)) {
            throw new IllegalArgumentException("Rhythm duration does not satisfy time signature");
        }
    }

    @Builder
    private Measure(@NotNull TimeSignature timeSignature, @NotNull KeySignature keySignature, @NotNull List<Rhythmic> rhythm, @Nullable Tempo tempo, @Nullable String rehearsalMarker, @Nullable String comment) {
        this(timeSignature, keySignature, rhythm);
        this.tempo = tempo;
        this.rehearsalMarker = rehearsalMarker;
        this.comment = comment;
    }

    public static MeasureBuilder builder(@NotNull TimeSignature timeSignature, @NotNull KeySignature keySignature) {
        return new MeasureBuilder().timeSignature(timeSignature).keySignature(keySignature);
    }

    private double determineDuration(TimeSignature timeSignature, Set<MeterType> meterTypes) {
        if (meterTypes.contains(MeterType.FREE)) {
            return Double.POSITIVE_INFINITY;
        }

        if (meterTypes.contains(MeterType.IRRATIONAL)) {
            return (timeSignature.getNumerator().doubleValue() / timeSignature.getDenominator().doubleValue());
        }

        return timeSignature.getNumerator().doubleValue() * MathUtils.invert(timeSignature.getDenominator().doubleValue());
    }

    private boolean validate(TimeSignature timeSignature, List<Rhythmic> rhythm, double duration) {
        if (timeSignature.getNumerator().equals(Double.POSITIVE_INFINITY)) {
            return true;
        }

        double total = rhythm.stream().mapToDouble(r -> r.getBeat().getDuration()).sum();

        return total == duration;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        return TimeSignature.NONE.equals(timeSignature) ? Collections.emptyList() : Collections.singletonList(timeSignature);
    }

    @Override
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }

}
