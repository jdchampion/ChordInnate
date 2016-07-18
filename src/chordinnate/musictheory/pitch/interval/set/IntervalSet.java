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
    Pitch lowest, highest;
    String name;

    public abstract Pitch[] getPitchesForOctave(@NotNull Octave octave);
}
