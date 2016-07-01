package chordinnate.musictheory;

import org.junit.Test;

import static chordinnate.musictheory.Beat.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/1/16.
 */
public class TestBeat {
    @Test
    public void getDuration() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.getDuration());
        }
    }

    @Test
    public void getDotValue() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.getDotValue());
        }
    }

    @Test
    public void getTuplet() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.getTuplet());
        }
    }

    @Test
    public void getRatio() throws Exception {
        for (Beat beat: Beat.values()) {
            assertTrue(beat.getRatio() > 0);
        }
    }

    @Test
    public void checkCompoundSums() throws Exception {
        assertEquals(
                HALF.getRatio(),
                DOTTED_QUARTER.getRatio() + EIGHTH.getRatio(),
                0
        );

        assertEquals(
                HALF.getRatio(),
                DOTTED_QUARTER.getRatio() + SIXTEENTH.getRatio() + SIXTEENTH.getRatio(),
                0
        );

        assertEquals(
                QUARTER.getRatio(),
                TRIPLET_EIGHTH.getRatio() + TRIPLET_EIGHTH.getRatio() + TRIPLET_EIGHTH.getRatio(),
                0.001
        );
    }

}