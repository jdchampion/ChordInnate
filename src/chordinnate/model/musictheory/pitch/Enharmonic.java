package chordinnate.model.musictheory.pitch;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public interface Enharmonic<T> {
    boolean isEnharmonicTo(@NotNull T other);
    T[] getEnharmonics();
}
