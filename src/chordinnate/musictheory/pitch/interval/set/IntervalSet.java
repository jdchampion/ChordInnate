package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.Octave;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet {
    Octave maxPlayableOctave;
    Pitch[] pitches;

    public abstract Pitch[] getPitchesForOctave(@NotNull Octave octave);
}
