package chordinnate.model.musictheory.temporal.rhythm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/1/16.
 */
public class BeatTest {
    @Test
    public void getDuration() {
        assertEquals(0.015625, Beat.SIXTY_FOURTH.getDuration(), 0);
        assertEquals(0.03125, Beat.THIRTY_SECOND.getDuration(), 0);
        assertEquals(0.0625, Beat.SIXTEENTH.getDuration(), 0);
        assertEquals(0.125, Beat.EIGHTH.getDuration(), 0);
        assertEquals(0.25, Beat.QUARTER.getDuration(), 0);
        assertEquals(0.5, Beat.HALF.getDuration(), 0);
        assertEquals(1.0, Beat.WHOLE.getDuration(), 0);
        assertEquals(2.0, Beat.DOUBLE_WHOLE.getDuration(), 0);

        Beat DOTTED_HALF = Beat.DOTTED_HALF;
        Beat DOUBLE_DOTTED_HALF = Beat.builder(Beat.VALUE_HALF).dots(2).build();
        Beat TRIPLET_EIGHTH = Beat.TRIPLET_EIGHTH;
        Beat QUADRUPLET_EIGHTH = Beat.builder(Beat.VALUE_EIGHTH).tuplet(4).build();
        Beat TRIPLET_DOTTED_HALF = Beat.builder(Beat.VALUE_HALF).dots(1).tuplet(3).build();

        // Dotted half = 3/4 whole note = 0.75
        assertEquals(0.75, DOTTED_HALF.getDuration(), 0);

        // Double-dotted half = 7/8 whole note = 0.875
        assertEquals(0.875, DOUBLE_DOTTED_HALF.getDuration(), 0);

        // Triplet eighth = 1/3 quarter
        assertEquals((1.0 / 3.0) * 0.25, TRIPLET_EIGHTH.getDuration(), 0);

        // Quadruplet eighth = 1/4 quarter = sixteenth = 0.0625
        assertEquals(0.0625, QUADRUPLET_EIGHTH.getDuration(), 0);

        // Triplet dotted half = 1/3 [2 * dotted half] = 1/3 [six quarters] = 0.5
        assertEquals(0.5, TRIPLET_DOTTED_HALF.getDuration(), 0);
    }

}