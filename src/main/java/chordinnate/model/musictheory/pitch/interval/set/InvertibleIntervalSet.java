package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Invertible;
import chordinnate.model.musictheory.pitch.interval.Octave;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

abstract class InvertibleIntervalSet extends IntervalSet implements Invertible<InvertibleIntervalSet> {

    @Getter(AccessLevel.PUBLIC)
    private int inversion;

    private EnumMap<Octave, Pitch[]> invertedPitchesByOctave;
    private int possibleInversions;

    @Override
    protected void commonInitializations(PitchClass root, Interval[] intervals) {
        super.commonInitializations(root, intervals);
        this.invertedPitchesByOctave = deepCopyPitchesByOctave();
        this.inversion = 1;
        this.possibleInversions = super.intervals.length;
    }

    @Override
    public InvertibleIntervalSet invert() {
        if (inversion == possibleInversions) {
            // Go back to the root inversion
            invertedPitchesByOctave = deepCopyPitchesByOctave();
            inversion = 1;
        } else {
            for (Map.Entry<Octave, Pitch[]> entry : invertedPitchesByOctave.entrySet()) {
                Octave nextOctave = entry.getKey().getNext();
                if (nextOctave != null) {
                    entry.getValue()[inversion - 1] = entry.getValue()[inversion - 1].transpose(true, Interval.PERFECT_8);
                }
            }
            inversion += 1;
        }

        return this;
    }

    /**
     * Creates a deep copy of IntervalSet.pitchesByOctave.
     * @return the deep-copied EnumMap
     */
    private EnumMap<Octave, Pitch[]> deepCopyPitchesByOctave() {
        EnumMap<Octave, Pitch[]> toReturn = new EnumMap<>(Octave.class);
        for (Map.Entry<Octave, Pitch[]> entry : super.pitchesByOctave.entrySet()) {
            Pitch[] original = entry.getValue();
            Pitch[] copy = new Pitch[original.length];
            System.arraycopy(original, 0, copy, 0, copy.length);
            toReturn.put(entry.getKey(), copy);
        }
        return toReturn;
    }

    @Override
    protected Pitch[] getSourcePitchesByOctave(@NotNull Octave octave) {
        return (inversion == 1 ? super.pitchesByOctave.get(octave) : invertedPitchesByOctave.get(octave));
    }

}
