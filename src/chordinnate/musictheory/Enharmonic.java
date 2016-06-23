package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
interface Enharmonic<T> {
    boolean isEnharmonicTo(@NotNull T other);
    @NotNull T[] getEnharmonics();
}
