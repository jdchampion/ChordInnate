package chordinnate.model.musictheory.time.rhythm;

import org.jetbrains.annotations.NotNull;

/**
 * Wrapper class for musical time measurement units.
 *
 * Created by Joseph on 7/1/16.
 */
public final class Beat {
    public static final Beat SIXTY_FOURTH = new Beat(Duration.SIXTY_FOURTH, 0, 1);
    public static final Beat THIRTY_SECOND = new Beat(Duration.THIRTY_SECOND, 0, 1);
    public static final Beat SIXTEENTH = new Beat(Duration.SIXTEENTH, 0, 1);
    public static final Beat EIGHTH = new Beat(Duration.EIGHTH, 0, 1);
    public static final Beat QUARTER = new Beat(Duration.QUARTER, 0, 1);
    public static final Beat HALF = new Beat(Duration.HALF, 0, 1);
    public static final Beat WHOLE = new Beat(Duration.WHOLE, 0, 1);
    public static final Beat DOUBLE_WHOLE = new Beat(Duration.DOUBLE_WHOLE, 0, 1);
    
    Duration DURATION;
    int DOT_VALUE;
    int TUPLET;
    private double RATIO;
    private boolean isDotted;
    private boolean isTuplet;

    private Beat(Duration duration, int dotValue, int tuplet) {
        this.isDotted = dotValue > 0;
        this.isTuplet = tuplet > 1;
        this.DURATION = duration;
        this.DOT_VALUE = isDotted ? dotValue : 0;
        this.TUPLET = isTuplet ? tuplet : 1;
        this.RATIO = getTotalRatio();
    }

    private double getTotalRatio() {
        boolean noDotValue = !isDotted,
                noTuplet = !isTuplet;

        if (noDotValue && noTuplet) return DURATION.RATIO;
        if (noDotValue) return DURATION.RATIO * getTupletRatio();
        if (noTuplet) return DURATION.RATIO * getDotValueRatio();
        return (DURATION.RATIO * getDotValueRatio()) * getTupletRatio();
    }

    private double getTupletRatio() {
        int tmp = TUPLET % 2 == 0 ? (TUPLET / (TUPLET / 2)) : (TUPLET - 1);
        return tmp * (1.0 / TUPLET);
    }

    private double getDotValueRatio() {
        double sum = 0;
        for (int i = 0; i < DOT_VALUE + 1; i++) {
            sum += Math.pow(0.5, i);
        }
        return sum;
    }

    public double getRatio() {
        return RATIO;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public boolean isTuplet() {
        return isTuplet;
    }

    public static class Builder {
        private Duration duration;
        private int dots;
        private int tuplet;

        public Builder(@NotNull Duration duration) {
            this.duration = duration;
            this.dots = 0;
            this.tuplet = 1;
        }

        public Builder dots(int dots) {
            this.dots = dots;
            return this;
        }

        public Builder tuplet(int tuplet) {
            this.tuplet = tuplet;
            return this;
        }

        public Beat build() {
            return new Beat(duration, dots, tuplet);
        }

    }
}
