package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.Transposable;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import org.jetbrains.annotations.NotNull;

interface TransposableIntervalSet extends Transposable {
    boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction);
    void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
    void transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
}
