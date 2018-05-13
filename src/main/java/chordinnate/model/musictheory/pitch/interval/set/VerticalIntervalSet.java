package chordinnate.model.musictheory.pitch.interval.set;

/**
 * Created by Joseph on 7/15/16.
 */
abstract class VerticalIntervalSet extends IntervalSet {

    /**
     * Retrieves the total depth of the IntervalSet (in terms of parallel pitches)
     * @return the number of parallel pitches in the IntervalSet
     */
    int depth() {
        return super.intervals.length;
    }

}
