package chordinnate.model.musictheory.time.meter;

/**
 * The InfiniteMeter classification serves as a way to enforce type safety
 * on the Meter class hierarchy.
 *
 * Created by Joseph on 7/3/16.
 */
abstract class InfiniteMeter extends Meter {
    private double measureDuration;

    InfiniteMeter() {
        this.measureDuration = Double.POSITIVE_INFINITY;
    }
}
