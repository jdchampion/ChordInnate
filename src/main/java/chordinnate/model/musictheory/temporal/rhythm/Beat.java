package chordinnate.model.musictheory.temporal.rhythm;


import chordinnate.model.util.MathUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Wrapper class for musical time measurement units.
 *
 * Created by Joseph on 7/1/16.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public final class Beat {

    // TODO: add more static finals
    // TODO: add constructor for dynamic Beat using any value of (2^(-x)) for the beat duration

    public static final Beat SIXTY_FOURTH = new Beat(BeatDuration.SIXTY_FOURTH, 0, 1);
    public static final Beat THIRTY_SECOND = new Beat(BeatDuration.THIRTY_SECOND, 0, 1);
    public static final Beat SIXTEENTH = new Beat(BeatDuration.SIXTEENTH, 0, 1);
    public static final Beat EIGHTH = new Beat(BeatDuration.EIGHTH, 0, 1);
    public static final Beat QUARTER = new Beat(BeatDuration.QUARTER, 0, 1);
    public static final Beat HALF = new Beat(BeatDuration.HALF, 0, 1);
    public static final Beat WHOLE = new Beat(BeatDuration.WHOLE, 0, 1);
    public static final Beat DOUBLE_WHOLE = new Beat(BeatDuration.DOUBLE_WHOLE, 0, 1);
    
    BeatDuration duration;
    private int dots = 0;
    private int tuplet = 1;

    public boolean isDotted() {
        return dots > 0;
    }

    public boolean isTuplet() {
        return tuplet > 1;
    }

    public double getRatio() {
        boolean noDotValue = !isDotted();
        boolean noTuplet = !isTuplet();

        if (noDotValue && noTuplet) return duration.ratio;
        if (noDotValue) return duration.ratio * getTupletRatio();
        if (noTuplet) return duration.ratio * getDotValueRatio();
        return (duration.ratio * getDotValueRatio()) * getTupletRatio();
    }

    private double getTupletRatio() {
        int tmp = tuplet % 2 == 0 ? (tuplet / (tuplet / 2)) : (tuplet - 1);
        return tmp * MathUtils.invert(tuplet);
    }

    private double getDotValueRatio() {
        double sum = 0;
        for (int i = 0; i < dots + 1; i++) {
            sum += Math.pow(0.5, i);
        }
        return sum;
    }
}
