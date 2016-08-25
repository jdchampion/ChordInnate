package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.*;
import chordinnate.musictheory.pitch.interval.Interval;
import chordinnate.musictheory.pitch.interval.Octave;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Joseph on 7/15/16.
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public final class Scale extends SerialIntervalSet implements TransposableIntervalSet {
    final ScaleType SCALE_TYPE;
    String fullName;

    public Scale(@NotNull EnharmonicSpelling root, @NotNull ScaleType scaleType) {
        super.commonInitializations(root, scaleType.getIntervals());
        this.SCALE_TYPE = scaleType;
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + scaleType.NAME;
    }

    public Scale(@NotNull EnharmonicSpelling root, @NotNull String scaleTypeName) {
        this(root, ScaleType.withName(scaleTypeName));
    }

    public Scale(@NotNull PitchClass root, @NotNull String scaleTypeName) {
        this(root.ENHARMONIC_SPELLING, ScaleType.withName(scaleTypeName));
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
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.SCALE_TYPE.NAME;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, this.lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, this.SCALE_TYPE.getIntervals());
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.SCALE_TYPE.NAME;
    }

    @Override
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from super.pitchesByOctave)
        Pitch[] source = super.pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    public static List<String> getSupportedScaleNames() {
        return ScaleType.listSupportedScaleTypes();
    }

    public String getTypeName() {
        return SCALE_TYPE.NAME;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOrigin() {
        return SCALE_TYPE.ORIGIN;
    }

    public int length() {
        return super.pitchesByOctave.get(Octave.OCTAVE_0).length;
    }
}
