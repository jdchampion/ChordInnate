package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.general.Accidental;
import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 7/15/16.
 */
public final class Scale extends SerialIntervalSet implements TransposableIntervalSet {
    ScaleType scaleType;
    Pitch lowest, highest;

    public Scale(EnharmonicSpelling root, ScaleType scaleType) {
        this.scaleType = scaleType;
        EnharmonicSpelling r = root.apply(Accidental.NONE);
        PitchInterval[] pitchIntervals = scaleType.getPitchIntervals();
        this.lowest = Pitch.valueOf(r.name() + "_0");
        Pitch tmp = lowest.transposeTo(pitchIntervals[pitchIntervals.length - 1], true);
        this.maxPlayableOctave = lowest.PITCH_CLASS.isEnharmonicTo(PitchClass.C)
                ? Octave.OCTAVE_10
                : tmp.PITCH_CLASS.OCTAVE_RANGE;
        this.pitches = new Pitch[maxPlayableOctave.NUMBER * pitchIntervals.length];
        int i = 0;
        for (Octave o = Octave.OCTAVE_0; !o.equals(maxPlayableOctave); o = o.getNext()) {
            pitches[i] = lowest.transposeTo(o);
            for (int j = 1; j < pitchIntervals.length; j++) {
                pitches[i + j] = pitches[i].transposeTo(pitchIntervals[j % pitchIntervals.length], true);
            }
            i += pitchIntervals.length;
        }
        this.highest = pitches[pitches.length - 1];
    }

    public Scale(PitchClass root, ScaleType scaleType) {
        this(root.ENHARMONIC_SPELLING, scaleType);
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        Pitch test = highest.transposeTo(octave);
        if (test == null) {
            throw new IllegalArgumentException("Octave " + octave.NUMBER + " is out of range for this Scale");
        }
        else if (test.ABSOLUTE_PITCH >= highest.ABSOLUTE_PITCH) {
            throw new IllegalArgumentException("Octave " + octave.NUMBER + " is out of range for this Scale");
        }
        // Return the desired octave (i.e., a subarray from this.pitches)
        Pitch[] octavePitches = new Pitch[this.scaleType.length()];
        System.arraycopy(this.pitches, octave.NUMBER * octavePitches.length, octavePitches, 0, octavePitches.length);
        return octavePitches;
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        return highest.transposeTo(pitchInterval, direction) != null;
    }

    @Override
    public void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        if (isTransposableTo(pitchInterval, direction)) {
            for (Pitch p : pitches) p.transposeTo(pitchInterval, direction);
        }
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return highest.transposeTo(pitchClass, octave) != null;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        if (isTransposableTo(pitchClass, octave)) {
            for (Pitch p : pitches) p.transposeTo(pitchClass, octave);
        }
    }
}
