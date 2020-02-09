package chordinnate.model.musictheory.time.rhythm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/1/16.
 */
public class BeatTest {
    @Test
    public void getRatio() throws Exception {
        assertEquals(0.015625, Beat.SIXTY_FOURTH.getRatio(), 0);
        assertEquals(0.03125, Beat.THIRTY_SECOND.getRatio(), 0);
        assertEquals(0.0625, Beat.SIXTEENTH.getRatio(), 0);
        assertEquals(0.125, Beat.EIGHTH.getRatio(), 0);
        assertEquals(0.25, Beat.QUARTER.getRatio(), 0);
        assertEquals(0.5, Beat.HALF.getRatio(), 0);
        assertEquals(1.0, Beat.WHOLE.getRatio(), 0);
        assertEquals(2.0, Beat.DOUBLE_WHOLE.getRatio(), 0);

        Beat DOTTED_HALF = new Beat.Builder(BeatDuration.HALF).dots(1).build();
        Beat DOUBLE_DOTTED_HALF = new Beat.Builder(BeatDuration.HALF).dots(2).build();
        Beat TRIPLET_EIGHTH = new Beat.Builder(BeatDuration.EIGHTH).tuplet(3).build();
        Beat QUADRUPLET_EIGHTH = new Beat.Builder(BeatDuration.EIGHTH).tuplet(4).build();
        Beat TRIPLET_DOTTED_HALF = new Beat.Builder(BeatDuration.HALF).dots(1).tuplet(3).build();

        // Dotted half = 3/4 whole note = 0.75
        assertEquals(0.75, DOTTED_HALF.getRatio(), 0);

        // Double-dotted half = 7/8 whole note = 0.875
        assertEquals(0.875, DOUBLE_DOTTED_HALF.getRatio(), 0);

        // Triplet eighth = 1/3 quarter
        assertEquals((1.0 / 3.0) * 0.25, TRIPLET_EIGHTH.getRatio(), 0);

        // Quadruplet eight = 1/4 quarter = sixteenth = 0.0625
        assertEquals(0.0625, QUADRUPLET_EIGHTH.getRatio(), 0);

        // Triplet dotted half = 1/3 [2 * dotted half] = 1/3 [six quarters] = 0.5
        assertEquals(0.5, TRIPLET_DOTTED_HALF.getRatio(), 0);
    }

}