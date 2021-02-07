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

    @NotNull
    private final Beat referenceBeat;

    private final int beatsPerMinute;

    public Tempo(@NotNull Beat subdivision, int beatsPerMinute) {
        MidiConfig config = ContextProvider.getContext().getBean(MidiConfig.class);
        if (beatsPerMinute >= config.getMinBpm() && beatsPerMinute <= config.getMaxBpm()) {
            this.beatsPerMinute = beatsPerMinute;
            this.referenceBeat = subdivision;
        } else {
            throw new IllegalArgumentException("Tempo must be between " + config.getMinBpm() + " and " + config.getMaxBpm() + " BPM (inclusive).");
        }
    }
}
