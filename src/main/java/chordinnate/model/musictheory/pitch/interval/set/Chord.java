package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.Transposable;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Invertible;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.playback.Playable;
import chordinnate.service.BaseService;
import chordinnate.service.musictheory.ChordTypeService;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/17/16.
 */
public class Chord extends VerticalIntervalSet
        implements Transposable<Void>, Invertible<Void>, Playable {
    private ChordType chordType;
    private String name;
    private EnumMap<Octave, Pitch[]> invertedPitchesByOctave;
    private int inversion;
    private int possibleInversions;

    private static final String CHORD_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)([^b#x].*)$";
    private static final Pattern PATTERN = Pattern.compile(CHORD_REGEX);

    private static ChordTypeService service = BaseService.getChordTypeService();

    public Chord(@NotNull String name) {

        Matcher matcher = PATTERN.matcher(name);

        boolean valid = false;

        if (matcher.matches()) {
            String rootName = matcher.group(1) + (matcher.group(2) == null ? "" : matcher.group(2));
            String chordTypeSymbol = matcher.group(4);

            PitchClass root = PitchClass.withName(rootName, rootName.contains(Accidental.NATURAL.utf8Symbol));
            Optional<ChordType> chordTypeOptional = service.findBySymbol(chordTypeSymbol);

            if (chordTypeOptional.isPresent()) {
                super.commonInitializations(root, chordTypeOptional.get().getIntervals());
                this.chordType = chordTypeOptional.get();
                this.name = super.lowestDiatonic.pitchClass.getName() + this.chordType.getSymbol();
                this.invertedPitchesByOctave = deepCopyPitchesByOctave();
                this.inversion = 0;
                this.possibleInversions = super.intervals.length - 1;
                valid = true;
            }

        }

        if (!valid) {
            throw new RuntimeException("Invalid chord baseName [" + name + "]");
        }
    }

    /**
     * Creates a deep copy of IntervalSet.pitchesByOctave.
     * @return the deep-copied EnumMap
     */
    private EnumMap<Octave, Pitch[]> deepCopyPitchesByOctave() {
        EnumMap<Octave, Pitch[]> toReturn = new EnumMap<>(Octave.class);
        for (Map.Entry<Octave, Pitch[]> entry : super.pitchesByOctave.entrySet()) {
            Pitch[] original = entry.getValue();
            Pitch[] copy = new Pitch[original.length];
            System.arraycopy(original, 0, copy, 0, copy.length);
            toReturn.put(entry.getKey(), copy);
        }
        return toReturn;
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

    @Override
    public boolean isTransposable(boolean direction, @NotNull Interval interval) {
        return true;
    }

    @Override
    public boolean isTransposable(boolean direction, @NotNull PitchClass pitchClass) {
        return true;
    }

    @Override
    public boolean isTransposable(@NotNull Pitch pitch) {
        return true;
    }

    @Override
    public boolean isTransposable(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return true;
    }

    @Override
    public Void transpose(boolean direction, @NotNull Interval interval) {
        if (isTransposable(direction, interval)) {
            Pitch lowestTransposed = super.lowestDiatonic.transpose(direction, interval);
            super.commonInitializations(lowestTransposed.pitchClass, chordType.getIntervals());
            this.name = super.lowestDiatonic.pitchClass.getName() + chordType.getSymbol();
        }
        return null;
    }

    @Override
    public Void transpose(boolean direction, @NotNull PitchClass pitchClass) {
        if (isTransposable(direction, pitchClass)) {
            Pitch lowestTransposed = super.lowestDiatonic.transpose(pitchClass, lowestDiatonic.octave);
            super.commonInitializations(lowestTransposed.pitchClass, chordType.getIntervals());
            this.name = super.lowestDiatonic.pitchClass.getName() + chordType.getSymbol();
        }
        return null;
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
    public Void invert() {
        if (inversion == possibleInversions) {
            // Go back to the root inversion
            invertedPitchesByOctave = deepCopyPitchesByOctave();
            name = super.lowestDiatonic.pitchClass.getName() + chordType.getSymbol();
            inversion = 0;
        } else {
            for (Map.Entry<Octave, Pitch[]> entry : invertedPitchesByOctave.entrySet()) {
                Octave nextOctave = entry.getKey().getNext();
                if (nextOctave != null) {
                    entry.getValue()[inversion] = entry.getValue()[inversion].transpose(true, Interval.withShortName("P8"));
                }
            }
            // Append the bass note to the baseName
            name = super.lowestDiatonic.pitchClass.getName() + chordType.getSymbol()
                    + "/" + super.pitchesByOctave.get(lowestDiatonic.octave)[++inversion].pitchClass.getName();
        }

        return null;
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from this.invertedPitchesByOctave)
        Pitch[] source = (inversion == 0 ? super.pitchesByOctave.get(octave) : invertedPitchesByOctave.get(octave));
        Pitch[] destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    public ChordType getChordType() {
        return chordType;
    }

    public int getInversion() {
        return inversion;
    }

    public String getName() {
        return name;
    }

    public Pitch[] getInversionForOctave(Octave octave) {
        return invertedPitchesByOctave.get(octave);
    }
}
