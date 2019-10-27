package chordinnate.model.musictheory.pitch.interval.set;

import static org.junit.Assert.*;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestIntervalSet {

    @Test
    public void verticalIntervalSet() {
        IntervalSet vertical = IntervalSet.verticalInstance(PitchClass.C, new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        assertTrue(vertical instanceof VerticalIntervalSet);
        assertEquals(3, vertical.getVerticalSize());
        assertEquals(1, vertical.getHorizontalSize());
    }

    @Test
    public void horizontalIntervalSet() {
        IntervalSet horizontal = IntervalSet.horizontalInstance(PitchClass.C, new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        assertTrue(horizontal instanceof HorizontalIntervalSet);
        assertEquals(1, horizontal.getVerticalSize());
        assertEquals(3, horizontal.getHorizontalSize());
    }

}
