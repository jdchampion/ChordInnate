package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.notation.RomanNumeral;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Invertible;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Optional;

/**
 * Created by Joseph on 7/17/16.
 */
public final class Chord extends VerticalIntervalSet
        implements TransposableIntervalSet, Invertible {
    final ChordType CHORD_TYPE;
    String name;
    EnumMap<Octave, Pitch[]> invertedPitchesByOctave;
    int inversion, possibleInversions;

    public Chord(@NotNull EnharmonicSpelling root, @NotNull String chordTypeName) {
        Optional<ChordType> chordType = chordTypeService.findBySymbol(chordTypeName);
        if (chordType.isPresent()) {
            super.commonInitializations(root, chordType.get().getIntervals());
            this.CHORD_TYPE = chordType.get();
            this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol();
            this.invertedPitchesByOctave = deepCopyPitchesByOctave();
            this.inversion = 0;
            this.possibleInversions = super.intervals.length - 1;
        } else {
            throw new RuntimeException("Error creating chord with type [" + chordTypeName + "]");
        }
    }

    public Chord(@NotNull PitchClass root, @NotNull String chordTypeName) {
        Optional<ChordType> chordType = chordTypeService.findBySymbol(chordTypeName);
        if (chordType.isPresent()) {
            super.commonInitializations(root.ENHARMONIC_SPELLING, chordType.get().getIntervals());
            this.CHORD_TYPE = chordType.get();
            this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol();
            this.invertedPitchesByOctave = deepCopyPitchesByOctave();
            this.inversion = 0;
            this.possibleInversions = super.intervals.length - 1;
        } else {
            throw new RuntimeException("Error creating chord with type [" + chordTypeName + "]");
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
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(keySignature)) return false;
        }
        return true;
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(intervalSet)) return false;
        }
        return true;
    }

    @Override
    public void transposeTo(@NotNull Interval interval, boolean direction) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(interval, direction);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, CHORD_TYPE.getIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol();
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, CHORD_TYPE.getIntervals());
        this.name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol();
    }

    @Override
    public void invert() {
        if (inversion == possibleInversions) {
            // Go back to the root inversion
            invertedPitchesByOctave = deepCopyPitchesByOctave();
            name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol();
            inversion = 0;
        } else {
            for (Octave octave : invertedPitchesByOctave.keySet()) {
                Octave nextOctave = octave.getNext();
                if (nextOctave != null) {
                    invertedPitchesByOctave.get(octave)[inversion] = invertedPitchesByOctave
                            .get(octave)[inversion].transposeTo(Interval.withShortName("P8"), true);
                }
            }
            // Append the bass note to the name
            name = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + CHORD_TYPE.getSymbol()
                    + "/" + super.pitchesByOctave.get(lowestDiatonic.OCTAVE)[++inversion].PITCH_CLASS.ENHARMONIC_SPELLING.NAME;
        }
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from this.invertedPitchesByOctave)
        Pitch[] source = (inversion == 0 ? super.pitchesByOctave.get(octave) : invertedPitchesByOctave.get(octave)),
                destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    public String getSymbol() {
        return CHORD_TYPE.getSymbol();
    }

    public String getName() {
        return name;
    }
}
