package chordinnate.musictheory;

/**
 * Created by Joseph on 6/22/16.
 */
public class Tempo {
    private TempoMarking tempoMarking;
    private int currentBPM;             // Beats per minute
    private long millis;                // Full length (in ms) of a whole note at the current tempo

    public Tempo(TempoMarking tempoMarking) {
        this.tempoMarking = tempoMarking;
        this.currentBPM = tempoMarking.getMinBPM();
        this.millis = bpmToMillis(currentBPM);
    }

    private long bpmToMillis(int bpm) {
        return (long) (60000 / bpm);
    }
}
