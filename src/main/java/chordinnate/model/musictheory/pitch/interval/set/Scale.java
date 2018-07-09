package chordinnate.model.musictheory.pitch.interval.set;

import static chordinnate.service.BaseService.getScaleTypeService;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.Transposable;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.playback.Playable;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Sequence;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/15/16.
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public class Scale extends HorizontalIntervalSet
        implements Transposable<Void>, Playable {
    private ScaleType SCALE_TYPE;
    private String fullName;

    private static final String SCALE_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*) (.+)$";
    private static final Pattern PATTERN = Pattern.compile(SCALE_REGEX);

    public Scale(@NotNull String name) {

        Matcher matcher = PATTERN.matcher(name);

        boolean valid = false;

        if (matcher.matches()) {
            String rootName = matcher.group(1) + (matcher.group(2) == null ? "" : matcher.group(2));
            String scaleTypeName = matcher.group(4);

            PitchClass root = PitchClass.withName(rootName, rootName.contains(Accidental.NATURAL.UTF8_SYMBOL));
            Optional<ScaleType> scaleType = getScaleTypeService().findByName(scaleTypeName);
            if (scaleType.isPresent()) {
                super.commonInitializations(root, scaleType.get().getIntervals());
                this.SCALE_TYPE = scaleType.get();
                this.fullName = root.getName() + " " + SCALE_TYPE.getName();
                valid = true;
            }
        }

        if (!valid) {
            throw new RuntimeException("Invalid scale name [" + name + "]");
        }
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
            super.commonInitializations(lowestTransposed.PITCH_CLASS, this.SCALE_TYPE.getIntervals());
            this.fullName = super.lowestDiatonic.PITCH_CLASS.getName() + " " + this.SCALE_TYPE.getName();
        }
        return null;
    }

    @Override
    public Void transpose(boolean direction, @NotNull PitchClass pitchClass) {
        if (isTransposable(direction, pitchClass)) {
            Pitch lowestTransposed = super.lowestDiatonic.transpose(pitchClass, this.lowestDiatonic.OCTAVE);
            super.commonInitializations(lowestTransposed.PITCH_CLASS, this.SCALE_TYPE.getIntervals());
            this.fullName = super.lowestDiatonic.PITCH_CLASS.getName() + " " + this.SCALE_TYPE.getName();
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
    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from super.pitchesByOctave)
        Pitch[] source = super.pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    @Override
    public Sequence getMidiSequence() throws Exception {
        //TODO
        return null;
    }

    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public String getFullName() {
        return fullName;
    }

}
