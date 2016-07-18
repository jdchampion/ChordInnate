package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.Transposable;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import org.jetbrains.annotations.NotNull;

interface TransposableIntervalSet extends Transposable {
    /**
     * Checks the validity of a transposition to the specified PitchInterval and direction.
     * @param pitchInterval the desired PitchInterval to transpose to
     * @param direction the direction of transposition (true = up; false = down)
     * @return whether the desired transposition is valid
     */
    boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction);

    /**
     * Performs a transposition to the specified PitchInterval in the specified direction,
     * with side effects to the IntervalSet.
     * @param pitchInterval the desired PitchInterval to transpose to
     * @param direction the direction of transposition (true = up; false = down)
     */
    void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction);

    /**
     * Checks the validity of a transposition to the specified PitchClass and Octave.
     * @param pitchClass the desired PitchClass to transpose to
     * @param octave the desired Octave to transpose to
     * @return whether the desired transposition is valid
     */
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);

    /**
     * Performs a transposition to the specified PitchClass and Octave,
     * with side effects to the IntervalSet.
     * @param pitchClass the desired PitchClass to transpose to
     * @param octave the desired Octave to transpose to
     * @return an instance of the implementing Enum that is representative of the transposition
     */
    void transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);
}
