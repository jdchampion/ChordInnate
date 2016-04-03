package musictheory;

/**
 * Created by Joseph on 3/16/16.
 */
public enum Octave {

    /*
     * The purpose for the Octave enumerated type is to
     * keep constructed members of the Note class
     * within the playable / audible range for MIDI playback.
     *
     * Per MIDI standards, playback notes must fall within
     * a height range of 0 (C0) and 127 (G9).
     */
    OCTAVE_MIN(0, 12),
    OCTAVE_ONE(1, 24),
    OCTAVE_TWO(2, 36),
    OCTAVE_THREE(3, 48),
    OCTAVE_FOUR(4, 60),
    OCTAVE_FIVE(5, 72),
    OCTAVE_SIX(6, 84),
    OCTAVE_SEVEN(7, 96),
    OCTAVE_MAX(8, 108);

    final int number, height;

    Octave(int number, int height) {
        this.number = number;
        this.height = height;
    }

    /**
     *
     * @return
     */
    final Octave raiseBy(int numOctavesRaised) {
        int ordinal = this.ordinal() + numOctavesRaised;
        if (this.equals(OCTAVE_MAX) || ordinal >= OCTAVE_MAX.ordinal()) {
            return OCTAVE_MAX;
        }
        else {
            Octave[] octaves = Octave.values();
            return octaves[ordinal];
        }
    }

    /**
     *
     * @return
     */
    final Octave lowerBy(int numOctavesLowered) {
        int ordinal = this.ordinal() - numOctavesLowered;
        if (this.equals(OCTAVE_MIN) || ordinal <= OCTAVE_MIN.ordinal()) {
            return OCTAVE_MIN;
        }
        else {
            Octave[] octaves = Octave.values();
            return octaves[ordinal];
        }
    }

    /**
     *
     * @param index
     * @return
     */
    static final Octave getFromIndex(int index) {
        Octave[] octaves = Octave.values();
        return (index > 0 && index < octaves.length - 1)
                ? octaves[index]
                : null;
    }
}
