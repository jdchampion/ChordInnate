package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.util.nomenclature.GreekGrouping;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by Joseph on 7/15/16.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerticalIntervalSet extends InvertibleIntervalSet {

    @Override
    protected void commonInitializations(PitchClass root, Interval[] intervals) {
        super.commonInitializations(root, intervals);
        this.orientation = IntervalOrientation.VERTICAL;
    }

    @Override
    public int getHorizontalSize() {
        return 1;
    }

    @Override
    public int getVerticalSize() {
        return super.intervals.length;
    }

    @Override
    public String getGrouping() {

        int size = getVerticalSize();

        if (size == 2) {
            return "dyad"; // special case because all other 2 prefixes are "di"
        }

        return GreekGrouping.grouping(size) + "ad";
    }
}
