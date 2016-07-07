package chordinnate.musictheory.time.rhythm;

import org.junit.Test;

import static chordinnate.musictheory.time.rhythm.Beat.*;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/1/16.
 */
public class TestBeat {
    @Test
    public void getDuration() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.DURATION);
        }
    }

    @Test
    public void getDotValue() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.DOT_VALUE);
        }
    }

    @Test
    public void getTuplet() throws Exception {
        for (Beat beat: Beat.values()) {
            assertNotNull(beat.TUPLET);
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
        Beat[] standardLengths = {SIXTY_FOURTH, THIRTY_SECOND, SIXTEENTH, EIGHTH, QUARTER, HALF, WHOLE, DOUBLE_WHOLE};

        /*
         * Test all standard subdivisions.
         * Each subdivision should == 2 * the next largest subdivision.
         */
        for (int i = 1; i < standardLengths.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                assertEquals(
                        standardLengths[i].getRatio(),
                        (Math.pow(2, (i - j))) * standardLengths[j].getRatio(),
                        0
                );
            }
        }

        /*
         * If all subdivisions above pass,
         * then the combination of a
         * dotted Beat and any shorter
         * standard subdivisions should also pass.
         *
         * For example, all of these should equal a 1/2 note
         * (D == dotted):
         *
         * D(1/4) + 1/8
         * D(1/4) + D(1/8) + 1/16
         * D(1/4) + (4 * 1/16)
         * D(1/4) + (8 * 1/32)
         * 1/4 + D(1/8) + 1/16
         *
         * ... and so on.
         *
         * These should pass with exact precision
         * (delta == 0) in the assertion.
         */
        for (int i = 2; i < standardLengths.length; i++) {
            assertEquals(
                    standardLengths[i].getRatio(),
                    Beat.valueOf("DOTTED_" + standardLengths[i - 1].name()).getRatio()
                    + standardLengths[i - 2].getRatio(),
                    0
            );
        }

        /*
         * Test all tuplet subdivisions.
         */
        for (Duration duration : Duration.values()) {
            if (duration.equals(Duration.SIXTY_FOURTH)) continue;
            for (Tuplet tuplet : Tuplet.values()) {
                if (tuplet.equals(Tuplet.NONE)) continue;
                assertEquals(
                        Beat.valueOf(tuplet + "_" + duration).getRatio(),
                        (tuplet.NUMBER - 1) * duration.RATIO * tuplet.RATIO,
                        0
                );
            }
        }

        /*
         * Assuming all tests pass above,
         * any combination of dots or tuplets
         * will return the correct lengths.
         */
    }

}