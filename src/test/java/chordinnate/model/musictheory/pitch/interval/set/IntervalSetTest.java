package chordinnate.model.musictheory.pitch.interval.set;

import static org.junit.Assert.*;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class IntervalSetTest {

    @Test
    public void sanity_verify_constructor_VerticalIntervalSet() {
        IntervalSet vertical = IntervalSet.verticalInstance(PitchClass.C, new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        assertTrue(VerticalIntervalSet.class.isAssignableFrom(vertical.getClass()));
        assertEquals(3, vertical.getVerticalSize());
        assertEquals(1, vertical.getHorizontalSize());
        assertEquals("triad", vertical.getGrouping());
    }

    @Test
    public void sanity_verify_constructor_HorizontalIntervalSet() {
        IntervalSet horizontal = IntervalSet.horizontalInstance(PitchClass.C, new Interval[]{Interval.PERFECT_1, Interval.MINOR_3, Interval.PERFECT_5});
        assertTrue(HorizontalIntervalSet.class.isAssignableFrom(horizontal.getClass()));
        assertEquals(1, horizontal.getVerticalSize());
        assertEquals(3, horizontal.getHorizontalSize());
        assertEquals("trichord", horizontal.getGrouping());
    }

}
