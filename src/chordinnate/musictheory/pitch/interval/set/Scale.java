package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/15/16.
 */
public final class Scale extends SerialIntervalSet implements TransposableIntervalSet {
    ScaleType scaleType;
    String name;

    public Scale(@NotNull EnharmonicSpelling root, @NotNull ScaleType scaleType) {
        super.commonInitializations(root, scaleType.getPitchIntervals());
        this.scaleType = scaleType;
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scaleType.NAME;
    }

    public Scale(@NotNull PitchClass root, @NotNull ScaleType scaleType) {
        this(root.ENHARMONIC_SPELLING, scaleType);
    }

    @Override
    public void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchInterval, direction);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, scaleType.getPitchIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scaleType.NAME;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, scaleType.getPitchIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scaleType.NAME;
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(keySignature)) return false;
        }
        return true;
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(intervalSet)) return false;
        }
        return true;
    }

    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from super.pitchesByOctave)
        Pitch[] source = super.pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }
}
