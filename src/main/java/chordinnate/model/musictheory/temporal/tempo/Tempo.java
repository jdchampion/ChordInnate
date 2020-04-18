package chordinnate.model.musictheory.temporal.tempo;

import chordinnate.model.musictheory.temporal.rhythm.Beat;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 6/22/16.
 */
@Getter
public class Tempo {

    private static final int MIN_BPM = 20;
    private static final int MAX_BPM = 240;

    private final Beat referenceBeat;
    private final int bpm;                 // Beats per minute

    public Tempo(@NotNull Beat subdivision, int bpm) {
        if (bpm >= MIN_BPM && bpm <= MAX_BPM) {
            this.bpm = bpm;
            this.referenceBeat = subdivision;
        } else {
            throw new IllegalArgumentException("Tempo must be between " + MIN_BPM + " and " + MAX_BPM + " BPM.");
        }
    }
}
