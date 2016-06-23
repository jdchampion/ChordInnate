package chordinnate.musictheory;

/**
 * Created by Joseph on 6/22/16.
 */
public class Tempo {
    private static final int
            MIN_BPM = TempoMarking.slowest().getMinBPM(),
            MAX_BPM = TempoMarking.fastest().getMaxBPM();
    private TempoMarking tempoMarking;
    private Duration currentBaseDuration;   // The note duration to base the Tempo on
    private DotValue dotValue;              // Modifier for the Duration's length
    private int currentBPM;                 // Beats per minute
    private long millis;                    // Full length (in ms) of the note duration (unit of measurement) at the current tempo
    private double ratio;                   // Ratio of the Note's total duration to a whole note's total duration

    public Tempo(Duration duration, DotValue dotValue, int bpm) {
        if (bpm >= MIN_BPM && bpm <= MAX_BPM) {
            this.currentBPM = bpm;
        }
        else {
            throw new IllegalArgumentException("Tempo must be between " + MIN_BPM + " and " + MAX_BPM + " BPM.");
        }

        this.currentBaseDuration = duration;
        this.dotValue = dotValue;
        this.millis = bpmToMillis(bpm);
        this.ratio = currentBaseDuration.getRatio() + getDotSum();
    }

    private double getDotSum() {
        double sum = 0;
        Duration tmp = currentBaseDuration.getPrevious();
        int numDots = dotValue.ordinal();
        for (int i = 0; i < numDots && tmp != null; tmp = tmp.getPrevious(), i++) {
            sum += tmp.getRatio();
        }
        return sum;
    }

    private long bpmToMillis(int bpm) {
        return (long) (60000 / bpm);
    }

    public TempoMarking getTempoMarking() {
        return tempoMarking;
    }

    public void setTempoMarking(TempoMarking tempoMarking) {
        this.tempoMarking = tempoMarking;

        /*
         * Whenever the tempo is set by this method,
         * we'll always select the minimum BPM
         * for the given TempoMarking. This follows
         * typical metronome conventions.
         */
        this.currentBPM = tempoMarking.getMinBPM();

        /*
         * Update the lower-level "millis" variable
         * for use by the PlayBack class.
         */
        this.millis = bpmToMillis(currentBPM);
    }

    public void setDuration(Duration duration, DotValue dotValue) {
        this.currentBaseDuration = duration;
        this.dotValue = dotValue;
        this.ratio = currentBaseDuration.getRatio() + getDotSum();
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
        if (currentBPM + 2 <= MAX_BPM) {
            millis = bpmToMillis(currentBPM += 2);
            return true;
        }
        else if (currentBPM + 1 <= MAX_BPM) {
            millis = bpmToMillis(currentBPM += 2);
            return true;
        }
        else return false;
    }

    /**
     * Slightly decreases the Tempo (ritardando), if possible.
     * @return whether the Tempo was successfully decreased.
     */
    public boolean rit() {
        if (currentBPM - 2 >= MIN_BPM) {
            millis = bpmToMillis(currentBPM -= 2);
            return true;
        }
        else if (currentBPM - 1 >= MIN_BPM) {
            millis = bpmToMillis(currentBPM -= 1);
            return true;
        }
        else return false;
    }

    /**
     *
     * @return
     */
    public TempoMarking getClosestTempoMarking() {
        // The obvious solution: just look at what you already have.
        if (tempoMarking != null) {
            return tempoMarking;
        }

        // In case the TempoMarking is not initialized...
        for (TempoMarking tempoMarking : TempoMarking.values()) {
            if (tempoMarking.getMinBPM() <= currentBPM) {
                return tempoMarking;
            }
        }

        return null; // returned on error
    }
}
