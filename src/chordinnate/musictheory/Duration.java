package chordinnate.musictheory;

/**
 * Created by Joseph on 6/1/16.
 */
public enum Duration {
    /*
     * NOTE: Keep these ordered from smallest to largest.
     */
    SIXTY_FOURTH(0.015625),
    THIRTY_SECOND(0.03125),
    SIXTEENTH(0.0625),
    EIGHTH(0.125),
    QUARTER(0.25),
    HALF(0.5),
    WHOLE(1.0),
    DOUBLE_WHOLE(2.0),

    ;

    private final double RATIO;
    private static final Duration[] VALUES = Duration.values();

    Duration(double ratio) {
        this.RATIO = ratio;
    }

    public double getRatio() {
        return RATIO;
    }

    public Duration getNext() {
        int ordinal = this.ordinal();
        return ordinal < VALUES.length - 1
                ? VALUES[ordinal + 1]
                : null;
    }

    public Duration getPrevious() {
        int ordinal = this.ordinal();
        return ordinal > 0
                ? VALUES[ordinal - 1]
                : null;
    }

    public static Duration getDuration(int index) {
        return (index >= 0 && index <= VALUES.length - 1)
                ? VALUES[index]
                : null;
    }

    public static Duration shortest() {
        return VALUES[0];
    }

    public static Duration longest() {
        return VALUES[VALUES.length - 1];
    }

    public boolean isShorterThan(Duration other) {
        return this.RATIO < other.RATIO;
    }

    public boolean isLongerThan(Duration other) {
        return this.RATIO > other.RATIO;
    }
}
