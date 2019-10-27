package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.RomanNumeral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph on 7/15/16.
 */
class HorizontalIntervalSet extends IntervalSet {

    @Override
    protected void commonInitializations(PitchClass root, Interval[] intervals) {
        super.commonInitializations(root, intervals);
        this.orientation = IntervalOrientation.HORIZONTAL;
    }

    @Override
    public int getHorizontalSize() {
        return super.intervals.length;
    }

    @Override
    public int getVerticalSize() {
        return 1;
    }

    public RomanNumeral[] getRomanNumeralAnalysis() {

        Map<Interval, List<ChordType>> diatonicsByInterval = getDiatonicChordTypes();

        List<RomanNumeral> analysis = new ArrayList<>();

        for (Map.Entry<Interval, List<ChordType>> entry : diatonicsByInterval.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                analysis.add(RomanNumeral.from(entry.getKey().getCompoundDiatonic(), entry.getValue().get(0).getIntervals()));
            }
        }

        return analysis.toArray(new RomanNumeral[0]);
    }
}
