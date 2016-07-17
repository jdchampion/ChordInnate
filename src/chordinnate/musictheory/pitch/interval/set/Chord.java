package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/17/16.
 */
public final class Chord extends NonSerialIntervalSet
        implements TransposableIntervalSet, Invertible {
    ChordType chordType;
    Octave[] uninvertedOctaves;

    public Chord(@NotNull EnharmonicSpelling root, @NotNull ChordType chordType) {
        // TODO: similar constructor to Scale, but keep Chord completely decoupled from the Scale class.
    }

    public Chord(@NotNull PitchClass root, @NotNull ChordType chordType) {
        this(root.ENHARMONIC_SPELLING, chordType);
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // TODO
        return new Pitch[0];
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        // TODO
        return false;
    }

    @Override
    public void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        // TODO
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        // TODO
        return false;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        // TODO
    }

    @Override
    public void invert() {
        // TODO
    }
}
