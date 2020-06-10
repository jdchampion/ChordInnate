package chordinnate.model.musictheory.notation;

import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.playback.Rhythmic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rest implements Rhythmic {

    @NotNull
    private Beat beat;

    public Rest(@NotNull Beat beat) {
        this.beat = beat;
    }

}
