package chordinnate.musictheory.time.meter;

/**
 * Created by Joseph on 7/3/16.
 */
public abstract class InfiniteMeter extends Meter {
    private double measureDuration;

    InfiniteMeter() {
        this.measureDuration = Double.POSITIVE_INFINITY;
    }
}
