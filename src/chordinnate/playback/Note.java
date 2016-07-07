package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.time.rhythm.Beat;
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
        this.soundedLength = (articulation == null ? 1 : articulation.LENGTH_MODIFIER);
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

    public boolean isTuplet() {
        return beat.isTuplet();
    }

    public boolean isDotted() {
        return beat.isDotted();
    }

    public boolean isTied() {
        return tied;
    }

    double getSoundedLength() {
        return soundedLength;
    }

    static class Builder {
        private Pitch pitch;
        private Beat beat;
        private Articulation articulation;
        private boolean tied = false;

        Builder(@NotNull Pitch pitch, @NotNull Beat beat) {
            this.pitch = pitch;
            this.beat = beat;
        }

        Builder articulation(Articulation articulation) {
            this.articulation = articulation;
            return this;
        }

        Builder tie() {
            this.tied = true;
            return this;
        }

        Note build() {
            return new Note(
                    pitch,
                    beat,
                    articulation,
                    tied
            );
        }
    }
}
