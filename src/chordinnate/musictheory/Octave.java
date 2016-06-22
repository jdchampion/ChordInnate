package chordinnate.musictheory;


/**
 * Created by Joseph on 4/14/16.
 */
public enum Octave {

    /*
     * The purpose for the Octave enumerated type is to
     * keep all playable items within the playable /
     * audible range for MIDI playback.
     *
     * Per MIDI standards, playback must fall within
     * a height range of 0 (C0) and 127 (G10).
     */
    OCTAVE_0(0, 0),
    OCTAVE_1(1, 12),
    OCTAVE_2(2, 24),
    OCTAVE_3(3, 36),
    OCTAVE_4(4, 48),
    OCTAVE_5(5, 60),
    OCTAVE_6(6, 72),
    OCTAVE_7(7, 84),
    OCTAVE_8(8, 96),
    OCTAVE_9(9, 108),
    OCTAVE_10(10, 120);

    private final int OCTAVENUMBER, MIDISTART;
    private static final Octave[] VALUES = Octave.values();

    Octave(int octaveNumber, int midiStart) {
        this.OCTAVENUMBER = octaveNumber;
        this.MIDISTART = midiStart;
    }

    public int getOctaveNumber() {
        return OCTAVENUMBER;
    }

    public int getMidiStart() {
        return MIDISTART;
    }

    public Octave getNext() {
        int ordinal = this.ordinal();
        return ordinal < VALUES.length - 1
                ? VALUES[ordinal + 1]
                : null;
    }

    public Octave getPrevious() {
        int ordinal = this.ordinal();
        return ordinal > 0
                ? VALUES[ordinal - 1]
                : null;
    }

    public static Octave getOctave(int index) {
        return (index >= 0 && index <= VALUES.length - 1)
                ? VALUES[index]
                : null;
    }

    public static int semitoneDistanceBetween(Octave lhs, Octave rhs) {
        return Math.abs(lhs.MIDISTART - rhs.MIDISTART);
    }

    public static int numOctavesBetween(Octave lhs, Octave rhs) {
        return Math.abs(lhs.ordinal() - rhs.ordinal());
    }

}
