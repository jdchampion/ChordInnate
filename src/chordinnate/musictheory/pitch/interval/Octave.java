package chordinnate.musictheory.pitch.interval;


import chordinnate.util.Sequential;
import chordinnate.util.SequentialUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 4/14/16.
 */
public enum Octave implements Sequential {

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

    public final int NUMBER, MIDI_START;

    Octave(int octaveNumber, int midiStart) {
        this.NUMBER = octaveNumber;
        this.MIDI_START = midiStart;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Octave getNext() {
        return (Octave) SequentialUtil.getNext(this, Octave.values());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Octave getPrevious() {
        return (Octave) SequentialUtil.getPrevious(this, Octave.values());
    }

    public static int semitoneDistanceBetween(@NotNull Octave lhs, @NotNull Octave rhs) {
        return Math.abs(lhs.MIDI_START - rhs.MIDI_START);
    }

    public static int numOctavesBetween(@NotNull Octave lhs, @NotNull Octave rhs) {
        return Math.abs(lhs.ordinal() - rhs.ordinal());
    }

}
