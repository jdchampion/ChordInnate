package chordinnate.model.musictheory.pitch.key;

import static chordinnate.model.musictheory.notation.EnharmonicSpelling.*;
import static chordinnate.model.musictheory.pitch.key.KeySignatureType.*;

import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;


/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 *            https://en.wikipedia.org/wiki/Theoretical_key
 */
public enum KeySignature {
    // No key signature
    NO_KEY_SIGNATURE(null, NONE, null, NONE, null, NONE, null, NONE, true),

    // Major, non-accidental
    C_MAJOR(C, MAJOR, A, MINOR, null, NONE, null, NONE, false),

    // Major, flat
    F_MAJOR(F, MAJOR, D, MINOR, B_FLAT, MAJOR, C, MAJOR, false, B_FLAT),
    B_FLAT_MAJOR(B_FLAT, MAJOR, G, MINOR, E_FLAT, MAJOR, F, MAJOR, false, B_FLAT, E_FLAT),
    E_FLAT_MAJOR(E_FLAT, MAJOR, C, MINOR, A_FLAT, MAJOR, B_FLAT, MAJOR, false, B_FLAT, E_FLAT, A_FLAT),
    A_FLAT_MAJOR(A_FLAT, MAJOR, F, MINOR, D_FLAT, MAJOR, E_FLAT, MAJOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT),
    D_FLAT_MAJOR(D_FLAT, MAJOR, B_FLAT, MINOR, G_FLAT, MAJOR, A_FLAT, MAJOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT),
    G_FLAT_MAJOR(G_FLAT, MAJOR, E_FLAT, MINOR, C_FLAT, MAJOR, D_FLAT, MAJOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT),
    C_FLAT_MAJOR(C_FLAT, MAJOR, A_FLAT, MINOR, F_FLAT, MAJOR, G_FLAT, MAJOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    F_FLAT_MAJOR(F_FLAT, MAJOR, D_FLAT, MINOR, B_DOUBLE_FLAT, MAJOR, C_FLAT, MAJOR, true, B_DOUBLE_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    B_DOUBLE_FLAT_MAJOR(B_DOUBLE_FLAT, MAJOR, G_FLAT, MINOR, E_DOUBLE_FLAT, MAJOR, F_FLAT, MAJOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    E_DOUBLE_FLAT_MAJOR(E_DOUBLE_FLAT, MAJOR, C_FLAT, MINOR, A_DOUBLE_FLAT, MAJOR, B_DOUBLE_FLAT, MAJOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    A_DOUBLE_FLAT_MAJOR(A_DOUBLE_FLAT, MAJOR, F_FLAT, MINOR, null, NONE, E_DOUBLE_FLAT, MAJOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_DOUBLE_FLAT, G_FLAT, C_FLAT, F_FLAT),

    // Major, sharp
    G_MAJOR(G, MAJOR, E, MINOR, D, MAJOR, C, MAJOR, false, F_SHARP),
    D_MAJOR(D, MAJOR, B, MINOR, A, MAJOR, G, MAJOR, false, F_SHARP, C_SHARP),
    A_MAJOR(A, MAJOR, F_SHARP, MINOR, E, MAJOR, D, MAJOR, false, F_SHARP, C_SHARP, G_SHARP),
    E_MAJOR(E, MAJOR, C_SHARP, MINOR, B, MAJOR, A, MAJOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP),
    B_MAJOR(B, MAJOR, G_SHARP, MINOR, F_SHARP, MAJOR, E, MAJOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP),
    F_SHARP_MAJOR(F_SHARP, MAJOR, D_SHARP, MINOR, C_SHARP, MAJOR, B, MAJOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP),
    C_SHARP_MAJOR(C_SHARP, MAJOR, A_SHARP, MINOR, G_SHARP, MAJOR, F_SHARP, MAJOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    G_SHARP_MAJOR(G_SHARP, MAJOR, E_SHARP, MINOR, D_SHARP, MAJOR, C_SHARP, MAJOR, true, F_DOUBLE_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    D_SHARP_MAJOR(D_SHARP, MAJOR, B_SHARP, MINOR, A_SHARP, MAJOR, G_SHARP, MAJOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    A_SHARP_MAJOR(A_SHARP, MAJOR, F_DOUBLE_SHARP, MINOR, E_SHARP, MAJOR, D_SHARP, MAJOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    E_SHARP_MAJOR(E_SHARP, MAJOR, C_DOUBLE_SHARP, MINOR, B_SHARP, MAJOR, A_SHARP, MAJOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_SHARP, E_SHARP, B_SHARP),
    B_SHARP_MAJOR(B_SHARP, MAJOR, G_DOUBLE_SHARP, MINOR, null, NONE, E_SHARP, MAJOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_DOUBLE_SHARP, E_SHARP, B_SHARP),

    // Minor, non-accidental
    A_MINOR(A, MINOR, C, MAJOR, null, NONE, null, NONE, false),

    // Minor, flat
    D_MINOR(D, MINOR, F, MAJOR, G, MINOR, A, MINOR, false, B_FLAT),
    G_MINOR(G, MINOR, B_FLAT, MAJOR, C, MINOR, D, MINOR, false, B_FLAT, E_FLAT),
    C_MINOR(C, MINOR, E_FLAT, MAJOR, F, MINOR, G, MINOR, false, B_FLAT, E_FLAT, A_FLAT),
    F_MINOR(F, MINOR, A_FLAT, MAJOR, B_FLAT, MINOR, C, MINOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT),
    B_FLAT_MINOR(B_FLAT, MINOR, D_FLAT, MAJOR, E_FLAT, MINOR, F, MINOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT),
    E_FLAT_MINOR(E_FLAT, MINOR, G_FLAT, MAJOR, A_FLAT, MINOR, B_FLAT, MINOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT),
    A_FLAT_MINOR(A_FLAT, MINOR, C_FLAT, MAJOR, D_FLAT, MINOR, E_FLAT, MINOR, false, B_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    D_FLAT_MINOR(D_FLAT, MINOR, F_FLAT, MAJOR, G_FLAT, MINOR, A_FLAT, MINOR, true, B_DOUBLE_FLAT, E_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    G_FLAT_MINOR(G_FLAT, MINOR, B_DOUBLE_FLAT, MAJOR, C_FLAT, MINOR, D_FLAT, MINOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    C_FLAT_MINOR(C_FLAT, MINOR, E_DOUBLE_FLAT, MAJOR, F_FLAT, MINOR, G_FLAT, MINOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_FLAT, G_FLAT, C_FLAT, F_FLAT),
    F_FLAT_MINOR(F_FLAT, MINOR, A_DOUBLE_FLAT, MAJOR, null, NONE, C_FLAT, MINOR, true, B_DOUBLE_FLAT, E_DOUBLE_FLAT, A_DOUBLE_FLAT, D_DOUBLE_FLAT, G_FLAT, C_FLAT, F_FLAT),

    // Minor, sharp
    E_MINOR(E, MINOR, G, MAJOR, B, MINOR, A, MINOR, false, F_SHARP),
    B_MINOR(B, MINOR, D, MAJOR, F_SHARP, MINOR, E, MINOR, false, F_SHARP, C_SHARP),
    F_SHARP_MINOR(F_SHARP, MINOR, A, MAJOR, C_SHARP, MINOR, B, MINOR, false, F_SHARP, C_SHARP, G_SHARP),
    C_SHARP_MINOR(C_SHARP, MINOR, E, MAJOR, G_SHARP, MINOR, F_SHARP, MINOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP),
    G_SHARP_MINOR(G_SHARP, MINOR, B, MAJOR, D_SHARP, MINOR, C_SHARP, MINOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP),
    D_SHARP_MINOR(D_SHARP, MINOR, F_SHARP, MAJOR, A_SHARP, MINOR, G_SHARP, MINOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP),
    A_SHARP_MINOR(A_SHARP, MINOR, C_SHARP, MAJOR, E_SHARP, MINOR, D_SHARP, MINOR, false, F_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    E_SHARP_MINOR(E_SHARP, MINOR, G_SHARP, MAJOR, B_SHARP, MINOR, A_SHARP, MINOR, true, F_DOUBLE_SHARP, C_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    B_SHARP_MINOR(B_SHARP, MINOR, D_SHARP, MAJOR, F_DOUBLE_SHARP, MINOR, E_SHARP, MINOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    F_DOUBLE_SHARP_MINOR(F_DOUBLE_SHARP, MINOR, A_SHARP, MAJOR, C_DOUBLE_SHARP, MINOR, B_SHARP, MINOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_SHARP, A_SHARP, E_SHARP, B_SHARP),
    C_DOUBLE_SHARP_MINOR(C_DOUBLE_SHARP, MINOR, E_SHARP, MAJOR, G_DOUBLE_SHARP, MINOR, F_DOUBLE_SHARP, MINOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_SHARP, E_SHARP, B_SHARP),
    G_DOUBLE_SHARP_MINOR(G_DOUBLE_SHARP, MINOR, B_SHARP, MAJOR, null, NONE, C_DOUBLE_SHARP, MINOR, true, F_DOUBLE_SHARP, C_DOUBLE_SHARP, G_DOUBLE_SHARP, D_DOUBLE_SHARP, A_DOUBLE_SHARP, E_SHARP, B_SHARP),

    ;

    public final EnharmonicSpelling KEY;
    public final KeySignatureType KEY_SIGNATURE_TYPE;
    public final EnharmonicSpelling RELATIVE_KEY;
    public final KeySignatureType RELATIVE_KEY_SIGNATURE_TYPE;
    public final EnharmonicSpelling NEXT_KEY;
    public final KeySignatureType NEXT_KEY_SIGNATURE_TYPE;
    public final EnharmonicSpelling PREV_KEY;
    public final KeySignatureType PREV_KEY_SIGNATURE_TYPE;
    public final boolean IS_THEORETICAL;
    public final LinkedHashSet<EnharmonicSpelling> SIGNATURE;

    KeySignature(EnharmonicSpelling key,
                 KeySignatureType keySignatureType,
                 EnharmonicSpelling relativeKey,
                 KeySignatureType relativeKeySignatureType,
                 EnharmonicSpelling nextKey,
                 KeySignatureType nextKeySignatureType,
                 EnharmonicSpelling prevKey,
                 KeySignatureType prevKeySignatureType,
                 boolean isTheoretical,
                 EnharmonicSpelling... signature) {
        this.KEY = key;
        this.KEY_SIGNATURE_TYPE = keySignatureType;
        this.RELATIVE_KEY = relativeKey;
        this.RELATIVE_KEY_SIGNATURE_TYPE = relativeKeySignatureType;
        this.NEXT_KEY = nextKey;
        this.NEXT_KEY_SIGNATURE_TYPE = nextKeySignatureType;
        this.PREV_KEY = prevKey;
        this.PREV_KEY_SIGNATURE_TYPE = prevKeySignatureType;
        this.IS_THEORETICAL = isTheoretical;
        this.SIGNATURE = Arrays.stream(signature)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public boolean isTheoretical() {
        return this.IS_THEORETICAL;
    }

    public boolean contains(@NotNull EnharmonicSpelling enharmonicSpelling) {
        return SIGNATURE.contains(enharmonicSpelling);
    }

    public KeySignature getRelativeKey() {
        return KeySignature.valueOf(this.RELATIVE_KEY.name() + "_" + this.RELATIVE_KEY_SIGNATURE_TYPE.name());
    }

    public KeySignature getParallelKey() {
        return KeySignature.valueOf(KEY.name() + "_" + (this.KEY_SIGNATURE_TYPE == MAJOR ? MINOR.name() : MAJOR.name()));
    }

    public KeySignature getNext() {
        // TODO: need to figure out how to get rid of NEXT_KEY and NEXT_KEY_SIGNATURE_TYPE
        // TODO: and infer the next key signature from the current one
        return NEXT_KEY != null
                ? KeySignature.valueOf(NEXT_KEY.name() + "_" + NEXT_KEY_SIGNATURE_TYPE.name())
                : NO_KEY_SIGNATURE;
    }

    public KeySignature getPrevious() {
        // TODO: need to figure out how to get rid of PREV_KEY and PREV_KEY_SIGNATURE_TYPE
        // TODO: and infer the previous key signature from the current one
        return PREV_KEY != null
                ? KeySignature.valueOf(PREV_KEY.name() + "_" + PREV_KEY_SIGNATURE_TYPE.name())
                : NO_KEY_SIGNATURE;
    }

}
