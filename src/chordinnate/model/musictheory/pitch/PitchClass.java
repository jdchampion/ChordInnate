package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.EnharmonicSpelling;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.IntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Pitch_class
 */
public enum PitchClass implements Enharmonic<PitchClass>, Diatonic {
    A_DOUBLE_FLAT(EnharmonicSpelling.A_DOUBLE_FLAT, 7),
    A_FLAT(EnharmonicSpelling.A_FLAT, 8),
    A_NATURAL(EnharmonicSpelling.A_NATURAL, 9),
    A(EnharmonicSpelling.A, 9),
    A_SHARP(EnharmonicSpelling.A_SHARP, 10),
    A_DOUBLE_SHARP(EnharmonicSpelling.A_DOUBLE_SHARP, 11),

    B_DOUBLE_FLAT(EnharmonicSpelling.B_DOUBLE_FLAT, 9),
    B_FLAT(EnharmonicSpelling.B_FLAT, 10),
    B_NATURAL(EnharmonicSpelling.B_NATURAL, 11),
    B(EnharmonicSpelling.B, 11),
    B_SHARP(EnharmonicSpelling.B_SHARP, 0),
    B_DOUBLE_SHARP(EnharmonicSpelling.B_DOUBLE_SHARP, 1),

    C_DOUBLE_FLAT(EnharmonicSpelling.C_DOUBLE_FLAT, 10),
    C_FLAT(EnharmonicSpelling.C_FLAT, 11),
    C_NATURAL(EnharmonicSpelling.C_NATURAL, 0),
    C(EnharmonicSpelling.C, 0),
    C_SHARP(EnharmonicSpelling.C_SHARP, 1),
    C_DOUBLE_SHARP(EnharmonicSpelling.C_DOUBLE_SHARP, 2),

    D_DOUBLE_FLAT(EnharmonicSpelling.D_DOUBLE_FLAT, 0),
    D_FLAT(EnharmonicSpelling.D_FLAT, 1),
    D_NATURAL(EnharmonicSpelling.D_NATURAL, 2),
    D(EnharmonicSpelling.D, 2),
    D_SHARP(EnharmonicSpelling.D_SHARP, 3),
    D_DOUBLE_SHARP(EnharmonicSpelling.D_DOUBLE_SHARP, 4),

    E_DOUBLE_FLAT(EnharmonicSpelling.E_DOUBLE_FLAT, 2),
    E_FLAT(EnharmonicSpelling.E_FLAT, 3),
    E_NATURAL(EnharmonicSpelling.E_NATURAL, 4),
    E(EnharmonicSpelling.E, 4),
    E_SHARP(EnharmonicSpelling.E_SHARP, 5),
    E_DOUBLE_SHARP(EnharmonicSpelling.E_DOUBLE_SHARP, 6),

    F_DOUBLE_FLAT(EnharmonicSpelling.F_DOUBLE_FLAT, 3),
    F_FLAT(EnharmonicSpelling.F_FLAT, 4),
    F_NATURAL(EnharmonicSpelling.F_NATURAL, 5),
    F(EnharmonicSpelling.F, 5),
    F_SHARP(EnharmonicSpelling.F_SHARP, 6),
    F_DOUBLE_SHARP(EnharmonicSpelling.F_DOUBLE_SHARP, 7),

    G_DOUBLE_FLAT(EnharmonicSpelling.G_DOUBLE_FLAT, 5),
    G_FLAT(EnharmonicSpelling.G_FLAT, 6),
    G_NATURAL(EnharmonicSpelling.G_NATURAL, 7),
    G(EnharmonicSpelling.G, 7),
    G_SHARP(EnharmonicSpelling.G_SHARP, 8),
    G_DOUBLE_SHARP(EnharmonicSpelling.G_DOUBLE_SHARP, 9);

    public final EnharmonicSpelling ENHARMONIC_SPELLING;
    public final int BASE_MIDI_VALUE;
    public final Octave OCTAVE_RANGE;

    private static final Map<Integer, ArrayList<PitchClass>> ENHARMONICS = new HashMap<>(17);
    static {
        for (int i = 0; i < 12; i++) {
            ENHARMONICS.put(i, new ArrayList<>());
        }

        for (PitchClass pitchClass : PitchClass.values()) {
            ENHARMONICS.get(pitchClass.BASE_MIDI_VALUE)
                    .add(pitchClass);
        }
    }

    PitchClass(EnharmonicSpelling enharmonicSpelling, int baseMidiValue) {
        this.ENHARMONIC_SPELLING = enharmonicSpelling;
        this.BASE_MIDI_VALUE = baseMidiValue;

        /*
         * The playable MIDI range is 0 (C0) to 127 (G10).
         * Since each Octave spans 12 semitones, only ten can fit completely.
         * The eleventh (highest) Octave is cut off at G10 (highest possible note),
         * so anything higher than G10 must be prevented.
         */
        this.OCTAVE_RANGE = baseMidiValue < 8 ? Octave.OCTAVE_10 : Octave.OCTAVE_9;
    }

    /**
     * Checks whether the PitchClass contains the specified Accidental.
     * @param accidental the Accidental to check
     * @return true if the Accidental is found, otherwise false
     */
    public boolean hasAccidental(@NotNull Accidental accidental) {
        return this.ENHARMONIC_SPELLING.ACCIDENTAL.equals(accidental);
    }

    /**
     * Finds the number of semitones between lhs and rhs.
     * @param lhs the starting PitchClass
     * @param rhs the ending PitchClass
     * @return the difference in semitones between lhs and rhs
     */
    public static int getSemitoneDistanceBetween(@NotNull PitchClass lhs, @NotNull PitchClass rhs) {
        int semitoneDistance = rhs.BASE_MIDI_VALUE - lhs.BASE_MIDI_VALUE;
        return semitoneDistance >= 0 ? semitoneDistance : 12 + semitoneDistance;
    }

    @Override
    public boolean isEnharmonicTo(@NotNull PitchClass other) {
        return this.BASE_MIDI_VALUE == other.BASE_MIDI_VALUE && this.OCTAVE_RANGE == other.OCTAVE_RANGE;
    }

    @Override
    public PitchClass[] getEnharmonics() {
        PitchClass[] enharmonics = new PitchClass[ENHARMONICS.get(BASE_MIDI_VALUE).size()];
        return ENHARMONICS.get(BASE_MIDI_VALUE).toArray(enharmonics);
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        if (keySignature.equals(KeySignature.NO_KEY_SIGNATURE)) {
            return true;
        } else if (keySignature.contains(this.ENHARMONIC_SPELLING)) {
            return true;
        } else {
            if (this.hasAccidental(Accidental.NONE) || this.hasAccidental(Accidental.NATURAL)) { // not found, natural
                if (keySignature.SIGNATURE.size() == 0) { // KeySignature contains no items
                    return true;
                } else { // KeySignature contains 1+ items
                    return !keySignature.SIGNATURE.contains(this.ENHARMONIC_SPELLING);
                }
            } else { // not found, not natural
                return false;
            }
        }
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return intervalSet.getDiatonics().contains(this);
    }
}
