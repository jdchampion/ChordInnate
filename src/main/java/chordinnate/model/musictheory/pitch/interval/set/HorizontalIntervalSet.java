package chordinnate.model.musictheory.pitch.interval.set;

/**
 * Created by Joseph on 7/15/16.
 */
abstract class HorizontalIntervalSet extends chordinnate.model.musictheory.pitch.interval.set.IntervalSet {

    /**
     * Retrieves the total length of the IntervalSet (in terms of sequential pitches)
     * @return the number of sequential pitches in the IntervalSet
     */
    int length() {
        return super.intervals.length;
    }

}
