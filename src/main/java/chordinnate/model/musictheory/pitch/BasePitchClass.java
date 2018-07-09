package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.notation.BaseEnharmonicSpelling;
import chordinnate.model.musictheory.pitch.interval.Octave;

/**
 * Created by Joseph on 4/14/16.
 * Reference: https://en.wikipedia.org/wiki/Pitch_class
 */
public enum BasePitchClass {
    A_DOUBLE_FLAT(BaseEnharmonicSpelling.A_DOUBLE_FLAT, 7),
    A_FLAT(BaseEnharmonicSpelling.A_FLAT, 8),
    A_NATURAL(BaseEnharmonicSpelling.A_NATURAL, 9),
    A(BaseEnharmonicSpelling.A, 9),
    A_SHARP(BaseEnharmonicSpelling.A_SHARP, 10),
    A_DOUBLE_SHARP(BaseEnharmonicSpelling.A_DOUBLE_SHARP, 11),

    B_DOUBLE_FLAT(BaseEnharmonicSpelling.B_DOUBLE_FLAT, 9),
    B_FLAT(BaseEnharmonicSpelling.B_FLAT, 10),
    B_NATURAL(BaseEnharmonicSpelling.B_NATURAL, 11),
    B(BaseEnharmonicSpelling.B, 11),
    B_SHARP(BaseEnharmonicSpelling.B_SHARP, 12),
    B_DOUBLE_SHARP(BaseEnharmonicSpelling.B_DOUBLE_SHARP, 13),

    C_DOUBLE_FLAT(BaseEnharmonicSpelling.C_DOUBLE_FLAT, -2),
    C_FLAT(BaseEnharmonicSpelling.C_FLAT, -1),
    C_NATURAL(BaseEnharmonicSpelling.C_NATURAL, 0),
    C(BaseEnharmonicSpelling.C, 0),
    C_SHARP(BaseEnharmonicSpelling.C_SHARP, 1),
    C_DOUBLE_SHARP(BaseEnharmonicSpelling.C_DOUBLE_SHARP, 2),

    D_DOUBLE_FLAT(BaseEnharmonicSpelling.D_DOUBLE_FLAT, 0),
    D_FLAT(BaseEnharmonicSpelling.D_FLAT, 1),
    D_NATURAL(BaseEnharmonicSpelling.D_NATURAL, 2),
    D(BaseEnharmonicSpelling.D, 2),
    D_SHARP(BaseEnharmonicSpelling.D_SHARP, 3),
    D_DOUBLE_SHARP(BaseEnharmonicSpelling.D_DOUBLE_SHARP, 4),

    E_DOUBLE_FLAT(BaseEnharmonicSpelling.E_DOUBLE_FLAT, 2),
    E_FLAT(BaseEnharmonicSpelling.E_FLAT, 3),
    E_NATURAL(BaseEnharmonicSpelling.E_NATURAL, 4),
    E(BaseEnharmonicSpelling.E, 4),
    E_SHARP(BaseEnharmonicSpelling.E_SHARP, 5),
    E_DOUBLE_SHARP(BaseEnharmonicSpelling.E_DOUBLE_SHARP, 6),

    F_DOUBLE_FLAT(BaseEnharmonicSpelling.F_DOUBLE_FLAT, 3),
    F_FLAT(BaseEnharmonicSpelling.F_FLAT, 4),
    F_NATURAL(BaseEnharmonicSpelling.F_NATURAL, 5),
    F(BaseEnharmonicSpelling.F, 5),
    F_SHARP(BaseEnharmonicSpelling.F_SHARP, 6),
    F_DOUBLE_SHARP(BaseEnharmonicSpelling.F_DOUBLE_SHARP, 7),

    G_DOUBLE_FLAT(BaseEnharmonicSpelling.G_DOUBLE_FLAT, 5),
    G_FLAT(BaseEnharmonicSpelling.G_FLAT, 6),
    G_NATURAL(BaseEnharmonicSpelling.G_NATURAL, 7),
    G(BaseEnharmonicSpelling.G, 7),
    G_SHARP(BaseEnharmonicSpelling.G_SHARP, 8),
    G_DOUBLE_SHARP(BaseEnharmonicSpelling.G_DOUBLE_SHARP, 9);

    final BaseEnharmonicSpelling ENHARMONIC_SPELLING;
    final int BASE_MIDI_VALUE;
    final Octave OCTAVE_RANGE;

    BasePitchClass(BaseEnharmonicSpelling baseEnharmonicSpelling, int baseMidiValue) {
        this.ENHARMONIC_SPELLING = baseEnharmonicSpelling;
        this.BASE_MIDI_VALUE = baseMidiValue;

        /*
         * The playable MIDI range is 0 (C0) to 127 (G10).
         * Since each Octave spans 12 semitones, only ten can fit completely.
         * The eleventh (highest) Octave is cut off at G10 (highest possible note),
         * so anything higher than G10 must be prevented.
         */
        this.OCTAVE_RANGE = baseMidiValue < 8 ? Octave.OCTAVE_10 : Octave.OCTAVE_9;
    }

}