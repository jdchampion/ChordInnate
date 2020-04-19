package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.MeterType;
import chordinnate.model.musictheory.temporal.meter.Metered;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Note;
import chordinnate.model.util.MathUtils;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.SequenceGenerator;
import lombok.Getter;

import javax.sound.midi.Sequence;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
public class Measure implements Metered, Playable {

    private TimeSignature timeSignature;
    private KeySignature keySignature;
    private Tempo tempo;
    private List<Note> rhythm;
    private double duration;

    public Measure(TimeSignature timeSignature, KeySignature keySignature, List<Note> rhythm) {

        this.timeSignature = timeSignature;
        this.keySignature = keySignature;
        this.rhythm = rhythm;

        if (!validate(timeSignature, rhythm)) {
            throw new IllegalArgumentException("Rhythm duration does not satisfy time signature");
        }
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

    private boolean validate(TimeSignature timeSignature, List<Note> rhythm) {

        this.duration = determineDuration(timeSignature, getMeterTypes());

        if (timeSignature.getNumerator().equals(Double.POSITIVE_INFINITY)) {
            return true;
        }

        double total = rhythm.stream().mapToDouble(n -> n.getBeat().getDuration()).sum();

        return total == duration;
    }

    @Override
    public List<TimeSignature> getAllTimeSignatures() {
        return Collections.singletonList(timeSignature);
    }

    @Override
    public Sequence accept(SequenceGenerator sequenceGenerator) {
        return sequenceGenerator.getSequence(this);
    }

}
