package chordinnate.model.musictheory.temporal.tempo;

import chordinnate.model.musictheory.temporal.rhythm.Beat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Joseph on 6/22/16.
 */
public class Tempo {
    private static final int MIN_BPM = TempoMarking.slowest().minBpm;
    private static final int MAX_BPM = TempoMarking.fastest().maxBpm;
    private TempoMarking tempoMarking;
    private Beat currentSubdivision;
    private int currentBPM;                 // Beats per minute
    private long millis;                    // Full length (in ms) of the beat duration (unit of measurement) at the current tempo
    private double ratio;                   // Ratio of the beat's total duration to a whole note's total duration

    public Tempo(@NotNull Beat subdivision, int bpm) {
        if (bpm >= MIN_BPM && bpm <= MAX_BPM) {
            this.currentBPM = bpm;
            this.currentSubdivision = subdivision;
            this.millis = bpmToMillis(bpm);
            this.ratio = currentSubdivision.getDuration();
        } else {
            throw new IllegalArgumentException("Tempo must be between " + MIN_BPM + " and " + MAX_BPM + " BPM.");
        }
    }

    private long bpmToMillis(int bpm) {
        return (long) (60000 / bpm);
    }

    public TempoMarking getTempoMarking() {
        return tempoMarking;
    }

    public void setTempoMarking(@NotNull TempoMarking tempoMarking) {
        this.tempoMarking = tempoMarking;

        /*
         * Whenever the tempo is set by this method,
         * we'll always select the minimum BPM
         * for the given TempoMarking. This follows
         * typical metronome conventions.
         */
        this.currentBPM = tempoMarking.minBpm;

        /*
         * Update the lower-level "millis" variable
         * for use by the PlaybackService class.
         */
        this.millis = bpmToMillis(currentBPM);
    }

    public void setSubdivision(@NotNull Beat beat) {
        this.currentSubdivision = beat;
        this.ratio = beat.getDuration();
    }

    public int getCurrentBPM() {
        return currentBPM;
    }

    public long getMillis() {
        return millis;
    }

    public double getRatio() {
        return ratio;
    }

    /**
     * Slightly increases the Tempo (accelerando), if possible.
     * @return whether the Tempo was successfully increased.
     */
    public boolean accel() {
        if (currentBPM + 2 < MAX_BPM) {
            currentBPM += 2;
            millis = bpmToMillis(currentBPM);
            return true;
        } else if (currentBPM + 1 < MAX_BPM) {
            currentBPM += 1;
            millis = bpmToMillis(currentBPM);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Slightly decreases the Tempo (ritardando), if possible.
     * @return whether the Tempo was successfully decreased.
     */
    public boolean rit() {
        if (currentBPM - 2 >= MIN_BPM) {
            currentBPM -= 2;
            millis = bpmToMillis(currentBPM);
            return true;
        } else if (currentBPM - 1 >= MIN_BPM) {
            currentBPM -= 1;
            millis = bpmToMillis(currentBPM);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Nullable
    public TempoMarking getClosestTempoMarking() {
        // The obvious solution: just look at what you already have.
        if (tempoMarking != null) {
            return tempoMarking;
        }

        // In case the TempoMarking is not initialized...
        for (TempoMarking tm : TempoMarking.values()) {
            if (tm.minBpm <= currentBPM) {
                return tm;
            }
        }

        return null; // Returned on error
    }

    public long getMillisFor(Beat beat) {
        return beat == null ? 0 : (long) (millis * (beat.getDuration() / currentSubdivision.getDuration()));
    }
}
