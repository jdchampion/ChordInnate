package chordinnate.model.musictheory.temporal.tempo;

import chordinnate.model.musictheory.temporal.rhythm.Beat;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 6/22/16.
 */
@Getter
public class Tempo {

    // TODO: make configurable? (MIN_BPM should always be >= 1)
    private static final int MIN_BPM = 20;
    private static final int MAX_BPM = 240;

    @NotNull
    private final Beat referenceBeat;

    private final int beatsPerMinute;

    public Tempo(@NotNull Beat subdivision, int beatsPerMinute) {
        if (beatsPerMinute >= MIN_BPM && beatsPerMinute <= MAX_BPM) {
            this.beatsPerMinute = beatsPerMinute;
            this.referenceBeat = subdivision;
        } else {
            throw new IllegalArgumentException("Tempo must be between " + MIN_BPM + " and " + MAX_BPM + " BPM.");
        }
    }
}
