package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/16/16.
 */
interface TransposableEnum<T extends Enum<T>> extends Transposable {
    /**
     * Checks the validity of a transposition to the specified Interval.
     * @param pitchInterval the desired Interval to transpose to
     * @return whether the desired transposition is valid
     */
    boolean isTransposableTo(@NotNull Interval pitchInterval, boolean direction);

    /**
     * Performs a transposition to the specified Interval.
     * @param pitchInterval the desired Interval to transpose to
     * @return an instance of the implementing Enum that is representative of the transposition
     */
    Enum<T> transposeTo(@NotNull Interval pitchInterval, boolean direction);

    /**
     * Checks the validity of a transposition to the specified PitchClass and Octave.
     * @param pitchClass the desired PitchClass to transpose to
     * @param octave the desired Octave to transpose to
     * @return whether the desired transposition is valid
     */
    boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);

    /**
     * Performs a transposition to the specified PitchClass and Octave.
     * @param pitchClass the desired PitchClass to transpose to
     * @param octave the desired Octave to transpose to
     * @return an instance of the implementing Enum that is representative of the transposition
     */
    Enum<T> transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave);

    /**
     * Checks the validity of a transposition to the specified Pitch.
     * @param pitch the desired Pitch to transpose to
     * @return whether the desired transposition is valid
     */
    boolean isTransposableTo(@NotNull Pitch pitch);

    /**
     * Performs a transposition to the specified Pitch.
     * @param pitch the desired Pitch to transpose to
     * @return an instance of the implementing Enum that is representative of the transposition
     */
    Enum<T> transposeTo(@NotNull Pitch pitch);
}
