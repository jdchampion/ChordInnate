package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/17/16.
 */
public final class Chord extends NonSerialIntervalSet
        implements TransposableIntervalSet, Invertible {
    ChordType chordType;
    String name;
    Octave[] uninvertedOctaves;

    public Chord(@NotNull EnharmonicSpelling root, @NotNull ChordType chordType) {
        super.commonInitializations(root, chordType.getPitchIntervals());
        this.chordType = chordType;
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + chordType.SYMBOL;
    }

    public Chord(@NotNull PitchClass root, @NotNull ChordType chordType) {
        this(root.ENHARMONIC_SPELLING, chordType);
    }

    @Override
    public void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchInterval, direction);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, chordType.getPitchIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + chordType.SYMBOL;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, chordType.getPitchIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + chordType.SYMBOL;
    }

    @Override
    public void invert() {
        // TODO
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
}
