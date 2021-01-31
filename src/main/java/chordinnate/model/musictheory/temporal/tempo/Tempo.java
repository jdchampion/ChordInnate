package chordinnate.model.musictheory.temporal.tempo;

import chordinnate.config.MidiConfig;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.util.ContextProvider;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Joseph on 6/22/16.
 */
@Getter
public class Tempo {

    private static final MidiConfig CONFIG = ContextProvider.getContext().getBean(MidiConfig.class);

    @NotNull
    private final Beat referenceBeat;

    private final int beatsPerMinute;

    public Tempo(@NotNull Beat subdivision, int beatsPerMinute) {
        if (beatsPerMinute >= CONFIG.getMinBpm() && beatsPerMinute <= CONFIG.getMaxBpm()) {
            this.beatsPerMinute = beatsPerMinute;
            this.referenceBeat = subdivision;
        } else {
            throw new IllegalArgumentException("Tempo must be between " + CONFIG.getMinBpm() + " and " + CONFIG.getMaxBpm() + " BPM (inclusive).");
        }
    }
}
