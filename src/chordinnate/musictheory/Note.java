package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Joseph on 6/16/16.
 */
public class Note {
    private Pitch pitch;
    private Duration duration;
    private Articulation articulation;
    private Tuplet tuplet;
    private DotValue dotValue;
    private double ratio;               // Ratio of the Note's total duration to a whole note's total duration
    private boolean tied = false;       // Default to not being tied

    private Note(
            Pitch pitch,
            Duration duration,
            Articulation articulation,
            Tuplet tuplet,
            DotValue dotValue,
            boolean tied
    ) {
        this.pitch = pitch;
        this.duration = duration;
        this.articulation = articulation;
        this.tuplet = tuplet;
        this.dotValue = dotValue;
        this.tied = tied;
        this.ratio = (duration.getRatio() + getDotSum())
                * (tuplet == null ? 1 : tuplet.getNumber());
    }

    public Note(@NotNull Pitch pitch, @NotNull Duration duration) {
        this.pitch = pitch;
        this.duration = duration;
    }

    private double getDotSum() {
        double sum = 0;
        Duration tmp = duration.getPrevious();
        int numDots = dotValue.ordinal();
        for (int i = 0; i < numDots && tmp != null; tmp = tmp.getPrevious(), i++) {
            sum += tmp.getRatio();
        }
        return sum;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Duration getDuration() {
        return duration;
    }

    @Nullable
    public Articulation getArticulation() {
        return articulation;
    }

    @Nullable
    public Tuplet getTuplet() {
        return tuplet;
    }

    @Nullable
    public DotValue getDotValue() {
        return dotValue;
    }

    public boolean isTied() {
        return tied;
    }

    public double getRatio() {
        return ratio;
    }

    public static class Builder {
        private Pitch pitch;
        private Duration duration;
        private Articulation articulation;
        private Tuplet tuplet;
        private DotValue dotValue;
        private boolean tied = false;

        public Builder(@NotNull Pitch pitch, @NotNull Duration duration) {
            this.pitch = pitch;
            this.duration = duration;
        }

        public Builder articulation(Articulation articulation) {
            this.articulation = articulation;
            return this;
        }

        public Builder tuplet(Tuplet tuplet) {
            this.tuplet = tuplet;
            return this;
        }

        public Builder dotValue(DotValue dotValue) {
            if (this.duration.ordinal() - dotValue.ordinal() >= 0) {
                this.dotValue = dotValue;
            }
            else {
                this.dotValue = DotValue.NONE;
            }
            return this;
        }

        public Builder tie() {
            this.tied = true;
            return this;
        }

        public Note build() {
            return new Note(
                    pitch,
                    duration,
                    articulation,
                    tuplet,
                    dotValue,
                    tied
            );
        }
    }
}
