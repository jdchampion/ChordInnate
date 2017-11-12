package chordinnate.model.musictheory.pitch.interval.set;

/**
 * Created by Joseph on 7/15/16.
 */
abstract class HorizontalIntervalSet extends IntervalSet {

    /**
     * Retrieves the total length of the IntervalSet (in terms of sequential pitches)
     * @return
     */
    abstract int length();

}
