package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.interval.RomanNumeral;

/**
 * Created by Joseph on 7/15/16.
 */
abstract class HorizontalIntervalSet extends IntervalSet {

    /**
     * Retrieves the total length of the IntervalSet (in terms of sequential pitches)
     * @return the number of sequential pitches in the IntervalSet
     */
    public int length() {
        return super.intervals.length;
    }

    public abstract RomanNumeral[] getRomanNumeralAnalysis();

}
