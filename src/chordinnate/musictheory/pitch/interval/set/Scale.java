package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/15/16.
 */
public final class Scale extends SerialIntervalSet implements TransposableIntervalSet {
    ScaleType scaleType;

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
}
