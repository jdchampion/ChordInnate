package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by Joseph on 7/15/16.
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public final class Scale extends HorizontalIntervalSet implements TransposableIntervalSet {
    final ScaleType SCALE_TYPE;
    String fullName;

    public Scale(@NotNull EnharmonicSpelling root, @NotNull String scaleTypeName) {
        Optional<ScaleType> scaleType = scaleTypeService.findByName(scaleTypeName);
        if (scaleType.isPresent()) {
            super.commonInitializations(root, scaleType.get().getIntervals());
            this.SCALE_TYPE = scaleType.get();
            this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + SCALE_TYPE.getName();
        } else {
            throw new RuntimeException("Error creating scale with type [" + scaleTypeName + "]");
        }
    }

    public Scale(@NotNull PitchClass root, @NotNull String scaleTypeName) {
        Optional<ScaleType> scaleType = scaleTypeService.findByName(scaleTypeName);
        if (scaleType.isPresent()) {
            super.commonInitializations(root.ENHARMONIC_SPELLING, scaleType.get().getIntervals());
            this.SCALE_TYPE = scaleType.get();
            this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + SCALE_TYPE.getName();
        } else {
            throw new RuntimeException("Error creating scale with type [" + scaleTypeName + "]");
        }
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
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, this.SCALE_TYPE.getIntervals());
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.SCALE_TYPE.getName();
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, this.lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, this.SCALE_TYPE.getIntervals());
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.SCALE_TYPE.getName();
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from super.pitchesByOctave)
        Pitch[] source = super.pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    @Override
    public int length() {
        return super.pitchesByOctave.get(Octave.OCTAVE_2).length;
    }

    public String getTypeName() {
        return SCALE_TYPE.getName();
    }

    public String getFullName() {
        return fullName;
    }

    public String getOrigin() {
        return SCALE_TYPE.getOrigin();
    }

}
