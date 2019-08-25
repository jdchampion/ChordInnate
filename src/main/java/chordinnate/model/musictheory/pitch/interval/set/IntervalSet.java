package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Diatonic;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.Transposable;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.service.Services;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 7/15/16.
 */
@Slf4j
public abstract class IntervalSet implements Transposable<Void>, Diatonic {
    EnumMap<Octave, Pitch[]> pitchesByOctave;
    LinkedHashSet<String> diatonics;
    Pitch lowestDiatonic;
    Pitch highestDiatonic;
    Octave maxPlayableOctave;
    Interval[] intervals;

    final void commonInitializations(PitchClass root, Interval[] intervals) {
        this.intervals = intervals;
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
                    if (p.isTransposable(true, Interval.PERFECT_8)) {
                        pitchesAtCurrentOctave.add(p);
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

    final Map<Interval, List<ChordType>> getDiatonicChordTypes() {
        List<ChordType> allChordTypes = (List<ChordType>) Services.getChordTypeService().findAll();

        Map<Interval, List<ChordType>> map = new LinkedHashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            Set<String> adjustedForDegree = new HashSet<>();
            for (int j = i, k = 0; k < intervals.length; k++, j = (j + 1) % intervals.length) {
                adjustedForDegree.add(intervals[j].minus(intervals[i]).getSimpleShortName());
            }

            List<ChordType> diatonicsForDegree = allChordTypes.stream()
                    .filter(c -> {
                        for (Interval interval : c.getIntervals()) {
                            if (!adjustedForDegree.contains(interval.getSimpleShortName())) {
                                return false;
                            }
                        }
                        return true;
                    }).collect(Collectors.toList());

            map.put(intervals[i], diatonicsForDegree);
        }

        return Collections.unmodifiableMap(map);
    }

    public Pitch[] getPitchesForOctave(Octave octave) {
        // Return the desired octave (i.e., a subarray from sourcePitchesByOctave)
        Pitch[] source = getSourcePitchesByOctave(octave);
        Pitch[] destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    Pitch[] getSourcePitchesByOctave(Octave octave) {
        return pitchesByOctave.get(octave);
    }

    public final Set<String> getDiatonics() {
        return Collections.unmodifiableSet(diatonics);
    }

    @Override
    public Void transpose(@NotNull Pitch pitch) {
        if (isTransposable(pitch)) {
            int midpoint = maxPlayableOctave.getNumber() / 2;
            Pitch rootAtMidpoint = getPitchesForOctave(Octave.valueOf("OCTAVE_" + midpoint))[0];
            boolean direction = pitch.absolutePitch > rootAtMidpoint.absolutePitch;
            return transpose(direction, pitch.pitchClass);
        }
        return null;
    }

    @Override
    public Void transpose(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        if (isTransposable(pitchClass, octave)) {
            int midpoint = maxPlayableOctave.getNumber() / 2;
            Octave octaveAtMidPoint = Octave.valueOf("OCTAVE_" + midpoint);
            boolean direction = octave.getNumber() > octaveAtMidPoint.getNumber();
            return transpose(direction, pitchClass);
        }
        return null;
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return Arrays.stream(getPitchesForOctave(lowestDiatonic.octave))
                .allMatch(p -> p.isDiatonicTo(keySignature));
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return Arrays.stream(getPitchesForOctave(lowestDiatonic.octave))
                .allMatch(p -> p.isDiatonicTo(intervalSet));
    }
}
