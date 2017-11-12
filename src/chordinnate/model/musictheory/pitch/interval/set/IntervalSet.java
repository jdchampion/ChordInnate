package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.service.BaseService;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.Diatonic;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Created by Joseph on 7/15/16.
 */
public abstract class IntervalSet extends BaseService implements Diatonic {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    Pitch lowestDiatonic, highestDiatonic;
    Octave maxPlayableOctave;
    Interval[] intervals;

    void commonInitializations(EnharmonicSpelling root, String intervalString) {
        this.intervals = parseIntervals(intervalString);
        this.pitchesByOctave = new EnumMap<>(Octave.class);
        this.lowestDiatonic = Pitch.valueOf(root.apply(Accidental.NONE).name() + "_0");
        Pitch lastKnownValidPitch = lowestDiatonic;
        Pitch octaveBegin = lowestDiatonic.transposeTo(lowestDiatonic.PITCH_CLASS, Octave.OCTAVE_0);
        if (octaveBegin != null) {
            for (Octave octave : Octave.values()) {
                ArrayList<Pitch> pitchesAtCurrentOctave = new ArrayList<>(intervals.length);
                if (lowestDiatonic.isTransposableTo(lowestDiatonic.PITCH_CLASS, octave)) {
                    Pitch lowestDiatonicAtOctave = octaveBegin.transposeTo(lowestDiatonic.PITCH_CLASS, octave);
                    if (lowestDiatonicAtOctave != null) {
                        pitchesAtCurrentOctave.add(lowestDiatonicAtOctave);
                        for (int i = 1; i < intervals.length; i++) {
                            if (lowestDiatonicAtOctave.isTransposableTo(intervals[i], true)) {
                                lastKnownValidPitch = lowestDiatonicAtOctave.transposeTo(intervals[i], true);
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
}
