package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Joseph on 6/16/16.
 */
public class Note {
    private Pitch pitch;
    private Beat beat;
    private Articulation articulation;
    private boolean tied = false;       // Default to not being tied

    private Note(
            Pitch pitch,
            Beat beat,
            Articulation articulation,
            boolean tied
    ) {
        this.pitch = pitch;
        this.beat = beat;
        this.articulation = articulation;
        this.tied = tied;
    }

    public Note(@NotNull Pitch pitch, @NotNull Beat beat) {
        this.pitch = pitch;
        this.beat = beat;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Beat getBeat() {
        return beat;
    }

    public Duration getDuration() {
        return beat.getDuration();
    }

    @Nullable
    public Articulation getArticulation() {
        return articulation;
    }

    public boolean isTuplet() {
        return getTuplet() != null;
    }

    @Nullable
    public Tuplet getTuplet() {
        return beat.getTuplet();
    }

    public boolean isDotted() {
        return !getDotValue().equals(DotValue.NONE);
    }

    public DotValue getDotValue() {
        return beat.getDotValue();
    }

    public boolean isTied() {
        return tied;
    }

    public double getRatio() {
        return beat.getRatio();
    }

    public static class Builder {
        private Pitch pitch;
        private Beat beat;
        private Articulation articulation;
        private boolean tied = false;

        public Builder(@NotNull Pitch pitch, @NotNull Beat beat) {
            this.pitch = pitch;
            this.beat = beat;
        }

        public Builder articulation(Articulation articulation) {
            this.articulation = articulation;
            return this;
        }

        public Builder tie() {
            this.tied = true;
            return this;
        }

        public Note build() {
            return new Note(
                    pitch,
                    beat,
                    articulation,
                    tied
            );
        }
    }
}
