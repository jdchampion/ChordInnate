package chordinnate.model.musictheory.pitch.interval.set;

import static chordinnate.service.BaseService.getChordTypeService;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.Transposable;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Invertible;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.playback.Playable;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Sequence;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/17/16.
 */
public class Chord extends VerticalIntervalSet
        implements Transposable<Void>, Invertible<Void>, Playable {
    private ChordType CHORD_TYPE;
    private String name;
    private EnumMap<Octave, Pitch[]> invertedPitchesByOctave;
    private int inversion, possibleInversions;

    private static final String CHORD_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)([^b#x].*)$";
    private static final Pattern PATTERN = Pattern.compile(CHORD_REGEX);

    public Chord(@NotNull String name) {

        Matcher matcher = PATTERN.matcher(name);

        boolean valid = false;

        if (matcher.matches()) {
            String rootName = matcher.group(1) + (matcher.group(2) == null ? "" : matcher.group(2));
            String chordTypeName = matcher.group(4);

            PitchClass root = PitchClass.withName(rootName, rootName.contains(Accidental.NATURAL.UTF8_SYMBOL));
            Optional<ChordType> chordType = getChordTypeService().findBySymbol(chordTypeName);

            if (chordType.isPresent()) {
                super.commonInitializations(root, chordType.get().getIntervals());
                this.CHORD_TYPE = chordType.get();
                this.name = super.lowestDiatonic.PITCH_CLASS.getName() + CHORD_TYPE.getSymbol();
                this.invertedPitchesByOctave = deepCopyPitchesByOctave();
                this.inversion = 0;
                this.possibleInversions = super.intervals.length - 1;
                valid = true;
            }

        }

        if (!valid) {
            throw new RuntimeException("Invalid chord name [" + name + "]");
        }
    }

    /**
     * Creates a deep copy of IntervalSet.pitchesByOctave.
     * @return the deep-copied EnumMap
     */
    private EnumMap<Octave, Pitch[]> deepCopyPitchesByOctave() {
        EnumMap<Octave, Pitch[]> toReturn = new EnumMap<>(Octave.class);
        for (Octave octave : super.pitchesByOctave.keySet()) {
            Pitch[] original = super.pitchesByOctave.get(octave),
                    copy = new Pitch[original.length];
            System.arraycopy(original, 0, copy, 0, copy.length);
            toReturn.put(octave, copy);
        }
        return toReturn;
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return Arrays.stream(getPitchesForOctave(lowestDiatonic.OCTAVE))
                .allMatch(p -> p.isDiatonicTo(keySignature));
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return Arrays.stream(getPitchesForOctave(lowestDiatonic.OCTAVE))
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
            super.commonInitializations(lowestTransposed.PITCH_CLASS, CHORD_TYPE.getIntervals());
            this.name = super.lowestDiatonic.PITCH_CLASS.getName() + CHORD_TYPE.getSymbol();
        }
        return null;
    }

    @Override
    public Void transpose(boolean direction, @NotNull PitchClass pitchClass) {
        if (isTransposable(direction, pitchClass)) {
            Pitch lowestTransposed = super.lowestDiatonic.transpose(pitchClass, lowestDiatonic.OCTAVE);
            super.commonInitializations(lowestTransposed.PITCH_CLASS, CHORD_TYPE.getIntervals());
            this.name = super.lowestDiatonic.PITCH_CLASS.getName() + CHORD_TYPE.getSymbol();
        }
        return null;
    }

    @Override
    public Void transpose(@NotNull Pitch pitch) {
        if (isTransposable(pitch)) {
            int midpoint = maxPlayableOctave.getNumber() / 2;
            Pitch rootAtMidpoint = getPitchesForOctave(Octave.valueOf("OCTAVE_" + midpoint))[0];
            boolean direction = pitch.ABSOLUTE_PITCH > rootAtMidpoint.ABSOLUTE_PITCH;
            return transpose(direction, pitch.PITCH_CLASS);
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
            name = super.lowestDiatonic.PITCH_CLASS.getName() + CHORD_TYPE.getSymbol();
            inversion = 0;
        } else {
            for (Octave octave : invertedPitchesByOctave.keySet()) {
                Octave nextOctave = octave.getNext();
                if (nextOctave != null) {
                    invertedPitchesByOctave.get(octave)[inversion] = invertedPitchesByOctave
                            .get(octave)[inversion].transpose(true, Interval.withShortName("P8"));
                }
            }
            // Append the bass note to the name
            name = super.lowestDiatonic.PITCH_CLASS.getName() + CHORD_TYPE.getSymbol()
                    + "/" + super.pitchesByOctave.get(lowestDiatonic.OCTAVE)[++inversion].PITCH_CLASS.getName();
        }

        return null;
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from this.invertedPitchesByOctave)
        Pitch[] source = (inversion == 0 ? super.pitchesByOctave.get(octave) : invertedPitchesByOctave.get(octave)),
                destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    @Override
    public Sequence getMidiSequence() throws Exception {
        //TODO
        return null;
    }

    public ChordType getChordType() {
        return CHORD_TYPE;
    }

    public int getInversion() {
        return inversion;
    }

    public String getName() {
        return name;
    }

    //TODO: add method to Invertible and override?
    public Pitch[] getInversionForOctave(Octave octave) {
        return invertedPitchesByOctave.get(octave);
    }
}
