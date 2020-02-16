package chordinnate.model.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


/**
 * Created by Joseph on 6/16/16.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Note implements Playable {
    private Pitch pitch;
    private Beat beat;
    private Articulation articulation;
    private Dynamic dynamic;
    private Effect effect;
    private boolean tied = false;       // Default to not being tied

    public Note(@NotNull Pitch pitch, @NotNull Beat beat) {
        this.pitch = pitch;
        this.beat = beat;
    }

    public double getSoundedLength() {
        return (articulation == null ? 1 : articulation.lengthModifier);
    }
}
