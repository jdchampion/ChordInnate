package chordinnate.model.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Created by Joseph on 6/16/16.
 */
public class Note implements Playable {
    private Pitch pitch;
    private Beat beat;
    private Articulation articulation;
    private boolean tied = false;       // Default to not being tied
    private double soundedLength;       // The amount of the full Beat value that is actually sounded

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
        this.soundedLength = (articulation == null ? 1 : articulation.lengthModifier);
    }

    public Note(@NotNull Pitch pitch, @NotNull Beat beat) {
        this.pitch = pitch;
        this.beat = beat;
        this.soundedLength = beat.getRatio();
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Beat getBeat() {
        return beat;
    }

    @Nullable
    public Articulation getArticulation() {
        return articulation;
    }

    public boolean isTied() {
        return tied;
    }

    public double getSoundedLength() {
        return soundedLength;
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
