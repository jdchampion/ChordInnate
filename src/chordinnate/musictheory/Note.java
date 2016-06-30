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
    private DotValue dotValue = DotValue.NONE; // Note constructor default has no dot
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
        this.ratio = NoteUtils.getRatio(duration, dotValue, tuplet);
    }

    public Note(@NotNull Pitch pitch, @NotNull Duration duration) {
        this.pitch = pitch;
        this.duration = duration;
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

    public boolean isTuplet() {
        return tuplet != null;
    }

    @Nullable
    public Tuplet getTuplet() {
        return tuplet;
    }

    public boolean isDotted() {
        return !dotValue.equals(DotValue.NONE);
    }

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
        private DotValue dotValue = DotValue.NONE;
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
            if (NoteUtils.isSupportedNoteLength(this.duration, dotValue)) {
                this.dotValue = dotValue;
                return this;
            }
            else {
                int numDots = dotValue.ordinal();
                throw new IllegalArgumentException(
                        "A " + duration + " note with " + numDots
                        + (numDots == 1 ? " dot" : " dots")
                        + " is currently not supported."
                );
            }
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
