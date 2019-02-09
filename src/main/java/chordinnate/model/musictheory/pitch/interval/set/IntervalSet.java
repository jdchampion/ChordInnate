package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Diatonic;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet implements Diatonic {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    LinkedHashSet<String> diatonics;
    Pitch lowestDiatonic;
    Pitch highestDiatonic;
    Octave maxPlayableOctave;
    Interval[] intervals;

    void commonInitializations(PitchClass root, String intervalString) {
        this.intervals = parseIntervals(intervalString);
        this.pitchesByOctave = new EnumMap<>(Octave.class);
        this.lowestDiatonic = Pitch.withName(root.getName() + "0");

        Pitch lastKnownValidPitch = lowestDiatonic;
        Pitch lowestRoot = lowestDiatonic.transpose(lowestDiatonic.pitchClass, Octave.OCTAVE_0);
        Pitch[] pitches = new Pitch[intervals.length];
        Octave highestOctave = Octave.OCTAVE_0;
        for (int i = 0; i < pitches.length; i++) {
            Pitch p = lowestRoot.transpose(true, intervals[i]);
            if (highestOctave.getNumber() < p.pitchClass.getOctaveRange().getNumber()) {
                highestOctave = p.pitchClass.getOctaveRange();
            }
            pitches[i] = p;
        }
        if (lowestRoot != null) {
            Octave octave = Octave.OCTAVE_0;
            while (octave != null && !octave.equals(highestOctave)) {
                ArrayList<Pitch> pitchesAtCurrentOctave = new ArrayList<>();
                for (int i = 0; i < pitches.length; i++) {
                    Pitch p = pitches[i];
                    pitchesAtCurrentOctave.add(p);
                    if (p.isTransposable(true, Interval.PERFECT_8)) {
                        pitches[i] = p.transpose(true, Interval.PERFECT_8);
                    }
                }
                Pitch[] ps = new Pitch[pitchesAtCurrentOctave.size()];
                pitchesByOctave.put(octave, pitchesAtCurrentOctave.toArray(ps));
                octave = octave.getNext();
            }
        }

        this.highestDiatonic = lastKnownValidPitch;

        this.diatonics = Arrays.stream(pitchesByOctave.get(lowestDiatonic.octave))
                .map(p -> p.pitchClass.getName())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        this.maxPlayableOctave = pitchesByOctave.get(highestDiatonic.octave) == null
                ? highestDiatonic.octave.getPrevious()
                : highestDiatonic.octave;
    }

    private Interval[] parseIntervals(String intervalString) {
        String[] intervalShortNames = intervalString.split(", ");
        final Interval[] parsedIntervals = new Interval[intervalShortNames.length];
        for (int i = 0; i < parsedIntervals.length; i++) {
            parsedIntervals[i] = Interval.withShortName(intervalShortNames[i]);
        }
        return parsedIntervals;
    }

    public abstract Pitch[] getPitchesForOctave(Octave octave);

    public Set<String> getDiatonics() {
        return Collections.unmodifiableSet(diatonics);
    }
}
