package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.general.Accidental;
import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    Pitch lowestDiatonic, highestDiatonic;
    Octave maxPlayableOctave;
    String name;

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

    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from this.pitches)
        Pitch[] source = pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }
}
