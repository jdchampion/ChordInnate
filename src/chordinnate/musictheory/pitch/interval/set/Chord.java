package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.general.Accidental;
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
        // TODO: this constructor is probably refactorable with Scale(EnharmonicSpelling, ChordType)
        this.chordType = chordType;
        EnharmonicSpelling r = root.apply(Accidental.NONE);
        PitchInterval[] pitchIntervals = chordType.getPitchIntervals();
        this.uninvertedOctaves = chordType.getBaseOctaves();
        this.lowest = Pitch.valueOf(r.name() + "_0");
        Pitch[] chordAtLowestOctave = new Pitch[pitchIntervals.length];
        for (int i = 0; i < chordAtLowestOctave.length; i++) {
            chordAtLowestOctave[i] = lowest.transposeTo(uninvertedOctaves[i]);
        }
        Pitch tmp = lowest.transposeTo(pitchIntervals[pitchIntervals.length - 1], true);
        Octave range = lowest.PITCH_CLASS.OCTAVE_RANGE.NUMBER < tmp.PITCH_CLASS.OCTAVE_RANGE.NUMBER
                ? lowest.PITCH_CLASS.OCTAVE_RANGE
                : tmp.PITCH_CLASS.OCTAVE_RANGE;
        this.pitches = new Pitch[range.NUMBER * pitchIntervals.length];
        int i = 0;
        for (Octave o = Octave.OCTAVE_0; !o.equals(range); o = o.getNext()) {
            pitches[i] = chordAtLowestOctave[0].transposeTo(o);
            for (int j = 1; j < pitchIntervals.length; j++) {
                pitches[i + j] = pitches[i].transposeTo(pitchIntervals[j % pitchIntervals.length], true);
            }
            i += pitchIntervals.length;
        }
        this.highest = pitches[pitches.length - 1];
        this.maxPlayableOctave = range.getPrevious();
        this.name = lowest.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + chordType.SYMBOL;
    }

    public Chord(@NotNull PitchClass root, @NotNull ChordType chordType) {
        this(root.ENHARMONIC_SPELLING, chordType);
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // TODO: refactor with Scale.getPitchesForOctave(Octave)
        Pitch test = highest.transposeTo(octave);
        if (test == null) {
            throw new IllegalArgumentException("Octave " + octave.NUMBER + " is out of range for " + name);
        }
        else if (test.OCTAVE.NUMBER > maxPlayableOctave.NUMBER) {
            throw new IllegalArgumentException("Octave " + octave.NUMBER + " is out of range for " + name);
        }
        // Return the desired octave (i.e., a subarray from this.pitches)
        Pitch[] octavePitches = new Pitch[this.chordType.length()];
        System.arraycopy(this.pitches, octave.NUMBER * octavePitches.length, octavePitches, 0, octavePitches.length);
        return octavePitches;
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        // TODO: refactor with Scale.isTransposableTo(PitchInterval, boolean)
        return highest.transposeTo(pitchInterval, direction) != null;
    }

    @Override
    public void transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        // TODO: refactor with Scale.transposeTo(PitchInterval, boolean)
        if (isTransposableTo(pitchInterval, direction)) {
            for (Pitch p : pitches) p.transposeTo(pitchInterval, direction);
        }
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        // TODO: refactor with Scale.isTransposableTo(PitchClass, Octave)
        return highest.transposeTo(pitchClass, octave) != null;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        // TODO: refactor with Scale.transposeTo(PitchClass, Octave)
        if (isTransposableTo(pitchClass, octave)) {
            for (Pitch p : pitches) p.transposeTo(pitchClass, octave);
        }
    }

    @Override
    public void invert() {
        // TODO
    }
}
