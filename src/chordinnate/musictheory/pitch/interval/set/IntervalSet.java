package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.notation.Accidental;
import chordinnate.musictheory.pitch.Diatonic;
import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet implements Diatonic {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    Pitch lowestDiatonic, highestDiatonic;
    Octave maxPlayableOctave;

    void commonInitializations(EnharmonicSpelling root, PitchInterval[] pitchIntervals) {
        this.pitchesByOctave = new EnumMap<>(Octave.class);
        this.lowestDiatonic = Pitch.valueOf(root.apply(Accidental.NONE).name() + "_0");
        Pitch lastKnownValidPitch = lowestDiatonic;
        for (Octave octave : Octave.values()) {
            ArrayList<Pitch> pitchesAtCurrentOctave = new ArrayList<>(pitchIntervals.length);
            if (lowestDiatonic.isTransposableTo(octave)) {
                Pitch lowestDiatonicAtOctave = lowestDiatonic.transposeTo(octave);
                pitchesAtCurrentOctave.add(lowestDiatonicAtOctave);
                for (int i = 1; i < pitchIntervals.length; i++) {
                    if (lowestDiatonicAtOctave.isTransposableTo(pitchIntervals[i], true)) {
                        lastKnownValidPitch = lowestDiatonicAtOctave.transposeTo(pitchIntervals[i], true);
                        pitchesAtCurrentOctave.add(lastKnownValidPitch);
                    }
                }
                Pitch[] ps = new Pitch[pitchesAtCurrentOctave.size()];
                pitchesByOctave.put(octave, pitchesAtCurrentOctave.toArray(ps));
            }
        }
        this.highestDiatonic = lastKnownValidPitch;
        this.maxPlayableOctave = pitchesByOctave.get(highestDiatonic.OCTAVE) == null
                ? highestDiatonic.OCTAVE.getPrevious()
                : highestDiatonic.OCTAVE;
    }

    public abstract Pitch[] getPitchesForOctave(Octave octave);
}
