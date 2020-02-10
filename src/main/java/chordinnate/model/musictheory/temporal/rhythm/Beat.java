package chordinnate.model.musictheory.temporal.rhythm;

import org.jetbrains.annotations.NotNull;

/**
 * Wrapper class for musical time measurement units.
 *
 * Created by Joseph on 7/1/16.
 */
public final class Beat {
    public static final Beat SIXTY_FOURTH = new Beat(BeatDuration.SIXTY_FOURTH, 0, 1);
    public static final Beat THIRTY_SECOND = new Beat(BeatDuration.THIRTY_SECOND, 0, 1);
    public static final Beat SIXTEENTH = new Beat(BeatDuration.SIXTEENTH, 0, 1);
    public static final Beat EIGHTH = new Beat(BeatDuration.EIGHTH, 0, 1);
    public static final Beat QUARTER = new Beat(BeatDuration.QUARTER, 0, 1);
    public static final Beat HALF = new Beat(BeatDuration.HALF, 0, 1);
    public static final Beat WHOLE = new Beat(BeatDuration.WHOLE, 0, 1);
    public static final Beat DOUBLE_WHOLE = new Beat(BeatDuration.DOUBLE_WHOLE, 0, 1);
    
    BeatDuration beatDuration;
    int dotValue;
    int tuplet;
    private double ratio;
    private boolean isDotted;
    private boolean isTuplet;

    private Beat(BeatDuration beatDuration, int dotValue, int tuplet) {
        this.isDotted = dotValue > 0;
        this.isTuplet = tuplet > 1;
        this.beatDuration = beatDuration;
        this.dotValue = isDotted ? dotValue : 0;
        this.tuplet = isTuplet ? tuplet : 1;
        this.ratio = getTotalRatio();
    }

    private double getTotalRatio() {
        boolean noDotValue = !isDotted;
        boolean noTuplet = !isTuplet;

        if (noDotValue && noTuplet) return beatDuration.ratio;
        if (noDotValue) return beatDuration.ratio * getTupletRatio();
        if (noTuplet) return beatDuration.ratio * getDotValueRatio();
        return (beatDuration.ratio * getDotValueRatio()) * getTupletRatio();
    }

    private double getTupletRatio() {
        int tmp = tuplet % 2 == 0 ? (tuplet / (tuplet / 2)) : (tuplet - 1);
        return tmp * (1.0 / tuplet);
    }

    private double getDotValueRatio() {
        double sum = 0;
        for (int i = 0; i < dotValue + 1; i++) {
            sum += Math.pow(0.5, i);
        }
        return sum;
    }

    public double getRatio() {
        return ratio;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public boolean isTuplet() {
        return isTuplet;
    }

    public static class Builder {
        private BeatDuration beatDuration;
        private int dots;
        private int tuplet;

        public Builder(@NotNull BeatDuration beatDuration) {
            this.beatDuration = beatDuration;
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
            return new Beat(beatDuration, dots, tuplet);
        }

    }
}
