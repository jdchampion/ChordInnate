package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.Diatonic;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet implements Diatonic {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    Set<PitchClass> diatonics;
    Pitch lowestDiatonic, highestDiatonic;
    Octave maxPlayableOctave;
    Interval[] intervals;

    void commonInitializations(EnharmonicSpelling root, String intervalString) {
        this.intervals = parseIntervals(intervalString);
        this.pitchesByOctave = new EnumMap<>(Octave.class);
        this.lowestDiatonic = Pitch.valueOf(root.apply(Accidental.NONE).name() + "_0");
        Pitch lastKnownValidPitch = lowestDiatonic;
        Pitch octaveBegin = lowestDiatonic.transpose(lowestDiatonic.PITCH_CLASS, Octave.OCTAVE_0);
        if (octaveBegin != null) {
            for (Octave octave : Octave.values()) {
                ArrayList<Pitch> pitchesAtCurrentOctave = new ArrayList<>(intervals.length);
                if (lowestDiatonic.isTransposable(lowestDiatonic.PITCH_CLASS, octave)) {
                    Pitch lowestDiatonicAtOctave = octaveBegin.transpose(lowestDiatonic.PITCH_CLASS, octave);
                    if (lowestDiatonicAtOctave != null) {
                        pitchesAtCurrentOctave.add(lowestDiatonicAtOctave);
                        for (int i = 1; i < intervals.length; i++) {
                            if (lowestDiatonicAtOctave.isTransposable(true, intervals[i])) {
                                lastKnownValidPitch = lowestDiatonicAtOctave.transpose(true, intervals[i]);
                                pitchesAtCurrentOctave.add(lastKnownValidPitch);
                            }
                        }
                    }
                    Pitch[] ps = new Pitch[pitchesAtCurrentOctave.size()];
                    pitchesByOctave.put(octave, pitchesAtCurrentOctave.toArray(ps));
                }
            }
        }
        this.highestDiatonic = lastKnownValidPitch;

        diatonics = Arrays.stream(pitchesByOctave.get(lowestDiatonic.OCTAVE))
                .map(p -> p.PITCH_CLASS)
                .distinct()
                .collect(Collectors.toSet());

        this.maxPlayableOctave = pitchesByOctave.get(highestDiatonic.OCTAVE) == null
                ? highestDiatonic.OCTAVE.getPrevious()
                : highestDiatonic.OCTAVE;
    }

    private Interval[] parseIntervals(String intervalString) {
        String[] intervalShortNames = intervalString.split(", ");
        final Interval[] intervals = new Interval[intervalShortNames.length];
        for (int i = 0; i < intervals.length; i++) {
            intervals[i] = Interval.withShortName(intervalShortNames[i]);
        }
        return intervals;
    }

    public abstract Pitch[] getPitchesForOctave(Octave octave);

    public Set<PitchClass> getDiatonics() {
        return Collections.unmodifiableSet(diatonics);
    }
}
