package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.notation.KeySignature;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Diatonic {
    boolean isDiatonicTo(@NotNull KeySignature keySignature);
    // TODO
    // boolean isDiatonicTo(@NotNull IntervalSet intervalSet);
}
