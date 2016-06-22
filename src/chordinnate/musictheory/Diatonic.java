package chordinnate.musictheory;

import com.sun.istack.internal.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
interface Diatonic {
    boolean isDiatonicTo(@NotNull KeySignature keySignature);
    // TODO
    // boolean isDiatonicTo(@NotNull IntervalSet intervalSet);
}
