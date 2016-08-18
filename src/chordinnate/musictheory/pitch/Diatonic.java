package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.interval.set.IntervalSet;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Diatonic {
    /**
     * Checks whether the implementing instance fits within the specified KeySignature.
     * @param keySignature the desired KeySignature to compare against
     * @return true if diatonic, false otherwise
     */
    boolean isDiatonicTo(@NotNull KeySignature keySignature);

    /**
     * Checks whether the implementing instance fits within the specified IntervalSet.
     * @param intervalSet the desired IntervalSet to compare against
     * @return true if diatonic, false otherwise
     */
    boolean isDiatonicTo(@NotNull IntervalSet intervalSet);
}
