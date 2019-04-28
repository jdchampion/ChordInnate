package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.ChordInnateException;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Invertible;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.playback.Playable;
import chordinnate.service.Services;
import chordinnate.service.musictheory.ChordTypeService;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/17/16.
 */
public class Chord extends VerticalIntervalSet
        implements Invertible<Void>, Playable {
    private ChordType chordType;
    private String name;
    private EnumMap<Octave, Pitch[]> invertedPitchesByOctave;
    private int inversion;
    private int possibleInversions;

    private static final String CHORD_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)([^b#x].*)$";
    private static final Pattern PATTERN = Pattern.compile(CHORD_REGEX);

    private static ChordTypeService service = Services.getChordTypeService();

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
            throw new ChordInnateException("Invalid chord symbol [" + name + "]");
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
            // Append the bass note to the name
            name = super.lowestDiatonic.pitchClass.getName() + chordType.getSymbol()
                    + "/" + super.pitchesByOctave.get(lowestDiatonic.octave)[++inversion].pitchClass.getName();
        }

        return null;
    }

    @Override
    Pitch[] getSourcePitchesByOctave(@NotNull Octave octave) {
        return (inversion == 0 ? super.pitchesByOctave.get(octave) : invertedPitchesByOctave.get(octave));
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
