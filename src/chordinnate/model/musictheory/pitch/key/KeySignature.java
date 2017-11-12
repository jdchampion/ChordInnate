package chordinnate.model.musictheory.pitch.key;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.PitchClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Relative_key
 *            https://en.wikipedia.org/wiki/Theoretical_key
 */
public enum KeySignature {
    // No key signature
    NO_KEY_SIGNATURE(null, KeySignatureType.NONE),

    // Major, non-accidental
    C_MAJOR(EnharmonicSpelling.C, KeySignatureType.MAJOR),

    // Major, flat
    F_MAJOR(EnharmonicSpelling.F, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT),
    B_FLAT_MAJOR(EnharmonicSpelling.B_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT),
    E_FLAT_MAJOR(EnharmonicSpelling.E_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT),
    A_FLAT_MAJOR(EnharmonicSpelling.A_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT),
    D_FLAT_MAJOR(EnharmonicSpelling.D_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT),
    G_FLAT_MAJOR(EnharmonicSpelling.G_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT),
    C_FLAT_MAJOR(EnharmonicSpelling.C_FLAT, KeySignatureType.MAJOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    F_FLAT_MAJOR(EnharmonicSpelling.F_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    B_DOUBLE_FLAT_MAJOR(EnharmonicSpelling.B_DOUBLE_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    E_DOUBLE_FLAT_MAJOR(EnharmonicSpelling.E_DOUBLE_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_DOUBLE_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    A_DOUBLE_FLAT_MAJOR(EnharmonicSpelling.A_DOUBLE_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_DOUBLE_FLAT, EnharmonicSpelling.D_DOUBLE_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),

    // Major, sharp
    G_MAJOR(EnharmonicSpelling.G, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP),
    D_MAJOR(EnharmonicSpelling.D, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP),
    A_MAJOR(EnharmonicSpelling.A, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP),
    E_MAJOR(EnharmonicSpelling.E, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP),
    B_MAJOR(EnharmonicSpelling.B, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP),
    F_SHARP_MAJOR(EnharmonicSpelling.F_SHARP, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP),
    C_SHARP_MAJOR(EnharmonicSpelling.C_SHARP, KeySignatureType.MAJOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    G_SHARP_MAJOR(EnharmonicSpelling.G_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    D_SHARP_MAJOR(EnharmonicSpelling.D_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    A_SHARP_MAJOR(EnharmonicSpelling.A_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    E_SHARP_MAJOR(EnharmonicSpelling.E_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_DOUBLE_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    B_SHARP_MAJOR(EnharmonicSpelling.B_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_DOUBLE_SHARP, EnharmonicSpelling.A_DOUBLE_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),

    // Minor, non-accidental
    A_MINOR(EnharmonicSpelling.A, KeySignatureType.MINOR),

    // Minor, flat
    D_MINOR(EnharmonicSpelling.D, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT),
    G_MINOR(EnharmonicSpelling.G, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT),
    C_MINOR(EnharmonicSpelling.C, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT),
    F_MINOR(EnharmonicSpelling.F, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT),
    B_FLAT_MINOR(EnharmonicSpelling.B_FLAT, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT),
    E_FLAT_MINOR(EnharmonicSpelling.E_FLAT, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT),
    A_FLAT_MINOR(EnharmonicSpelling.A_FLAT, KeySignatureType.MINOR, EnharmonicSpelling.B_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    D_FLAT_MINOR(EnharmonicSpelling.D_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    G_FLAT_MINOR(EnharmonicSpelling.G_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    C_FLAT_MINOR(EnharmonicSpelling.C_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_DOUBLE_FLAT, EnharmonicSpelling.D_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),
    F_FLAT_MINOR(EnharmonicSpelling.F_FLAT, KeySignatureType.THEORETICAL, EnharmonicSpelling.B_DOUBLE_FLAT, EnharmonicSpelling.E_DOUBLE_FLAT, EnharmonicSpelling.A_DOUBLE_FLAT, EnharmonicSpelling.D_DOUBLE_FLAT, EnharmonicSpelling.G_FLAT, EnharmonicSpelling.C_FLAT, EnharmonicSpelling.F_FLAT),

    // Minor, sharp
    E_MINOR(EnharmonicSpelling.E, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP),
    B_MINOR(EnharmonicSpelling.B, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP),
    F_SHARP_MINOR(EnharmonicSpelling.F_SHARP, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP),
    C_SHARP_MINOR(EnharmonicSpelling.C_SHARP, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP),
    G_SHARP_MINOR(EnharmonicSpelling.G_SHARP, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP),
    D_SHARP_MINOR(EnharmonicSpelling.D_SHARP, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP),
    A_SHARP_MINOR(EnharmonicSpelling.A_SHARP, KeySignatureType.MINOR, EnharmonicSpelling.F_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    E_SHARP_MINOR(EnharmonicSpelling.E_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    B_SHARP_MINOR(EnharmonicSpelling.B_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    F_DOUBLE_SHARP_MINOR(EnharmonicSpelling.F_DOUBLE_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    C_DOUBLE_SHARP_MINOR(EnharmonicSpelling.C_DOUBLE_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_DOUBLE_SHARP, EnharmonicSpelling.A_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),
    G_DOUBLE_SHARP_MINOR(EnharmonicSpelling.G_DOUBLE_SHARP, KeySignatureType.THEORETICAL, EnharmonicSpelling.F_DOUBLE_SHARP, EnharmonicSpelling.C_DOUBLE_SHARP, EnharmonicSpelling.G_DOUBLE_SHARP, EnharmonicSpelling.D_DOUBLE_SHARP, EnharmonicSpelling.A_DOUBLE_SHARP, EnharmonicSpelling.E_SHARP, EnharmonicSpelling.B_SHARP),

    ;

    public final EnharmonicSpelling KEY;
    public final KeySignatureType KEY_SIGNATURE_TYPE;
    public final EnharmonicSpelling[] SIGNATURE;

    // There are certainly other ways to get the relatives, but I believe using a HashMap is the most straightforward.
    private static final Map<KeySignature, KeySignature> RELATIVE_KEY_SIGNATURES = new EnumMap<>(KeySignature.class);
        static {
            // Majors
            RELATIVE_KEY_SIGNATURES.put(B_FLAT_MAJOR, G_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_FLAT_MAJOR, C_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_FLAT_MAJOR, F_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_FLAT_MAJOR, B_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_FLAT_MAJOR, E_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_FLAT_MAJOR, A_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_FLAT_MAJOR, D_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_DOUBLE_FLAT_MAJOR, F_FLAT_MINOR);

            RELATIVE_KEY_SIGNATURES.put(B_MAJOR, G_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_MAJOR, C_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_MAJOR, F_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_MAJOR, B_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_MAJOR, E_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_MAJOR, A_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_MAJOR, D_MINOR);

            RELATIVE_KEY_SIGNATURES.put(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR);
            RELATIVE_KEY_SIGNATURES.put(A_SHARP_MAJOR, F_DOUBLE_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(D_SHARP_MAJOR, B_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(G_SHARP_MAJOR, E_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(C_SHARP_MAJOR, A_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(F_SHARP_MAJOR, D_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(B_SHARP_MAJOR, G_DOUBLE_SHARP_MINOR);
            RELATIVE_KEY_SIGNATURES.put(E_SHARP_MAJOR, C_DOUBLE_SHARP_MINOR);

            // Minors
            RELATIVE_KEY_SIGNATURES.put(G_MINOR, B_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_MINOR, E_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_MINOR, A_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_FLAT_MINOR, D_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_FLAT_MINOR, G_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_FLAT_MINOR, C_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_FLAT_MINOR, F_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(G_FLAT_MINOR, B_DOUBLE_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_FLAT_MINOR, E_DOUBLE_FLAT_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_FLAT_MINOR, A_DOUBLE_FLAT_MAJOR);

            RELATIVE_KEY_SIGNATURES.put(G_SHARP_MINOR, B_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_SHARP_MINOR, E_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_SHARP_MINOR, A_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_MINOR, D_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_MINOR, G_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_MINOR, C_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_MINOR, F_MAJOR);

            RELATIVE_KEY_SIGNATURES.put(G_DOUBLE_SHARP_MINOR, B_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(C_DOUBLE_SHARP_MINOR, E_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(F_DOUBLE_SHARP_MINOR, A_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(B_SHARP_MINOR, D_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(E_SHARP_MINOR, G_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(A_SHARP_MINOR, C_SHARP_MAJOR);
            RELATIVE_KEY_SIGNATURES.put(D_SHARP_MINOR, F_SHARP_MAJOR);

            // No key signature
            RELATIVE_KEY_SIGNATURES.put(NO_KEY_SIGNATURE, NO_KEY_SIGNATURE);
        }

    private static final Map<Integer, Accidental> SEMITONE_TO_ACCIDENTAL = new HashMap<>(13);
        static {
            for (PitchClass pitchClass : PitchClass.values()) {
                if (Math.abs(pitchClass.ENHARMONIC_SPELLING.ACCIDENTAL.SEMITONE_MODIFIER) < 2) {
                    SEMITONE_TO_ACCIDENTAL.put(pitchClass.BASE_MIDI_VALUE, pitchClass.ENHARMONIC_SPELLING.ACCIDENTAL);
                }
            }
        }

    KeySignature(EnharmonicSpelling key, KeySignatureType keySignatureType, EnharmonicSpelling... signature) {
        this.KEY = key;
        this.KEY_SIGNATURE_TYPE = keySignatureType;
        this.SIGNATURE = signature;
    }

    public boolean contains(@NotNull EnharmonicSpelling enharmonicSpelling) {
        if (this.equals(NO_KEY_SIGNATURE)) return false;

        for (EnharmonicSpelling e : SIGNATURE) {
            if (enharmonicSpelling.equals(e)) return true;
        }

        return false;
    }

    public KeySignature getRelativeMajor() {
        return this.KEY_SIGNATURE_TYPE == KeySignatureType.MAJOR || (this.KEY_SIGNATURE_TYPE == KeySignatureType.THEORETICAL && this.name().contains("MAJOR"))
                ? this
                : RELATIVE_KEY_SIGNATURES.get(this);
    }

    public KeySignature getRelativeMinor() {
        return this.KEY_SIGNATURE_TYPE == KeySignatureType.MINOR || (this.KEY_SIGNATURE_TYPE == KeySignatureType.THEORETICAL && this.name().contains("MINOR"))
                ? this
                : RELATIVE_KEY_SIGNATURES.get(this);
    }

    public KeySignature getParallelMajor() {
        return this.KEY_SIGNATURE_TYPE == KeySignatureType.MAJOR || (this.KEY_SIGNATURE_TYPE == KeySignatureType.THEORETICAL && this.name().contains("MAJOR"))
                ? this
                : KeySignature.valueOf(KEY.name() + "_" + KeySignatureType.MAJOR.name());
    }

    public KeySignature getParallelMinor() {
        return this.KEY_SIGNATURE_TYPE == KeySignatureType.MINOR || (this.KEY_SIGNATURE_TYPE == KeySignatureType.THEORETICAL && this.name().contains("MINOR"))
                ? this
                : KeySignature.valueOf(KEY.name() + "_" + KeySignatureType.MINOR.name());
    }

    @Nullable
    public KeySignature getNext() {
        switch (KEY_SIGNATURE_TYPE) {
            case MAJOR: {
                switch (this) {
                    case C_MAJOR: return NO_KEY_SIGNATURE;
                    default: return values()[ordinal() + 1];
                }
            }
            case MINOR: {
                switch (this) {
                    case A_MINOR: return NO_KEY_SIGNATURE;
                    default: return values()[ordinal() + 1];
                }
            }
            case THEORETICAL: {
                switch (this) {
                    case A_DOUBLE_FLAT_MAJOR:
                    case B_SHARP_MAJOR:
                    case F_FLAT_MINOR:
                    case G_DOUBLE_SHARP_MINOR: return null;
                    default: return values()[ordinal() + 1];
                }
            }
            case NONE:
                return NO_KEY_SIGNATURE;
            default: return null;
        }
    }

    @Nullable
    public KeySignature getPrevious() {
        switch (KEY_SIGNATURE_TYPE) {
            case MAJOR: {
                switch (this) {
                    case C_MAJOR: return NO_KEY_SIGNATURE;
                    case F_MAJOR:
                    case G_MAJOR: return null;
                    default: return values()[ordinal() - 1];
                }
            }
            case MINOR: {
                switch (this) {
                    case A_MINOR: return NO_KEY_SIGNATURE;
                    case D_MINOR:
                    case E_MINOR: return null;
                    default: return values()[ordinal() - 1];
                }
            }
            case THEORETICAL: {
                return values()[ordinal() - 1];
            }
            case NONE:
                return NO_KEY_SIGNATURE;
            default: return null;
        }
    }

}
