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
    Pitch lowestDiatonic, highestDiatonic;
    Octave maxPlayableOctave;
    Interval[] intervals;

    void commonInitializations(PitchClass root, String intervalString) {
        this.intervals = parseIntervals(intervalString);
        this.pitchesByOctave = new EnumMap<>(Octave.class);
        this.lowestDiatonic = Pitch.withName(root.getName() + "0");

        Pitch lastKnownValidPitch = lowestDiatonic;
        Pitch lowestRoot = lowestDiatonic.transpose(lowestDiatonic.PITCH_CLASS, Octave.OCTAVE_0);
        Pitch[] pitches = new Pitch[intervals.length];
        Octave highestOctave = Octave.OCTAVE_0;
        for (int i = 0; i < pitches.length; i++) {
            Pitch p = lowestRoot.transpose(true, intervals[i]);
            if (highestOctave.getNumber() < p.PITCH_CLASS.getOctaveRange().getNumber()) {
                highestOctave = p.PITCH_CLASS.getOctaveRange();
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
                    if (p.isTransposable(true, Interval.P8)) {
                        pitches[i] = p.transpose(true, Interval.P8);
                    }
                }
                Pitch[] ps = new Pitch[pitchesAtCurrentOctave.size()];
                pitchesByOctave.put(octave, pitchesAtCurrentOctave.toArray(ps));
                octave = octave.getNext();
            }
        }

        this.highestDiatonic = lastKnownValidPitch;

        this.diatonics = Arrays.stream(pitchesByOctave.get(lowestDiatonic.OCTAVE))
                .map(p -> p.PITCH_CLASS.getName())
                .collect(Collectors.toCollection(LinkedHashSet::new));

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

    public Set<String> getDiatonics() {
        return Collections.unmodifiableSet(diatonics);
    }
}
