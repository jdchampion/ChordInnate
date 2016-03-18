package musictheory;

/**
 * Created by Joseph on 3/16/16.
 */
public enum Octave {
    TOO_LOW(-1, 0),
    ZERO(0, 12),
    ONE(1, 24),
    TWO(2, 36),
    THREE(3, 48),
    FOUR(4, 60),
    FIVE(5, 72),
    SIX(6, 84),
    SEVEN(7, 96),
    EIGHT(8, 108),
    TOO_HIGH(9, 120);

    final int number, height;

    Octave(int number, int height) {
        this.number = number;
        this.height = height;
    }

    /**
     *
     * @return
     */
    static final Octave getNext(Octave o) {
        Octave[] octaves = Octave.values();
        return octaves[(o.ordinal() + 1) % octaves.length];
    }

    /**
     *
     * @return
     */
    static final Octave getPrevious(Octave o) {
        Octave[] octaves = Octave.values();
        return octaves[(o.ordinal() - 1) % octaves.length];
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

    /**
     *
     * @return all Octaves that can be audibly played back
     */
    final Octave[] withinBounds() {
        return new Octave[] {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT};
    }
}
