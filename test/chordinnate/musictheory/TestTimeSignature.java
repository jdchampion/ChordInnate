package chordinnate.musictheory;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/1/16.
 */
public class TestTimeSignature {
    @Test
    public void illegalArguments() throws Exception {
        // Negative numerators in constructor

        // Null Object values in constructor
    }

    @Test
    public void freeMeters() throws Exception {
        // Free meters have no MeterGrouping
        assertTrue(new TimeSignature().is(MeterProperty.FREE));
    }

    @Test
    public void oddMeters() throws Exception {
        int[] oddNumeratorsSubset = {1, 3, 5, 7};

        for (int numerator : oddNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertTrue(new TimeSignature(numerator, beat).is(MeterProperty.ODD));
            }
        }
    }

    @Test
    public void perfectMeters() throws Exception {
        int[] validNumeratorsSubset = {2, 3, 4, 8, 9, 10};

        for (int numerator : validNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertTrue(new TimeSignature(numerator, beat).is(MeterProperty.PERFECT));
            }
        }
    }

    @Test
    public void imperfectMeters() throws Exception {
        int[] validNumeratorsSubset = {1, 5, 6, 7, 11, 12};

        for (int numerator : validNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertTrue(new TimeSignature(numerator, beat).is(MeterProperty.IMPERFECT));
            }
        }
    }

    @Test
    public void simpleMeters() throws Exception {
        int[] validNumeratorsSubset = {2, 4};

        for (int numerator : validNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertTrue(new TimeSignature(numerator, beat).is(MeterProperty.SIMPLE));
            }
        }

        int[] invalidNumeratorsSubset = {1, 3, 5, 7, 9};

        for (int numerator : invalidNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertFalse(new TimeSignature(numerator, beat).is(MeterProperty.SIMPLE));
            }
        }
    }

    @Test
    public void compoundMeters() throws Exception {
        int[] validNumeratorsSubset = {3, 9, 15, 21, 27};

        for (int numerator : validNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                assertTrue(new TimeSignature(numerator, beat).is(MeterProperty.COMPOUND));
            }
        }
    }

    @Test
    public void complexMeters() throws Exception {
        TimeSignature ts;

        ts = new TimeSignature(9, Beat.EIGHTH, new boolean[]{true, false, true, false, false, true, false, true, false});
        assertTrue(ts.is(MeterProperty.COMPLEX) && ts.is(MeterProperty.IRREGULAR) && ts.is(MeterProperty.ASYMMETRICAL));

        ts = new TimeSignature(9, Beat.EIGHTH, new int[]{2, 3, 2, 2});
        assertTrue(ts.is(MeterProperty.COMPLEX) && ts.is(MeterProperty.IRREGULAR) && ts.is(MeterProperty.ASYMMETRICAL));
    }

    @Test
    public void fractionalMeters() throws Exception {
        double[] validNumeratorsSubset = {(2.0 / 3.0), 1.25, Math.E, 3.1, Math.PI};

        TimeSignature ts;
        for (double numerator : validNumeratorsSubset) {
            for (Beat beat : Beat.values()) {
                ts = new TimeSignature(numerator, beat);
                assertTrue(ts.is(MeterProperty.FRACTIONAL));
                assertTrue(ts.is(MeterProperty.PARTIAL));

            }
        }
    }

    @Test
    public void irrationalMeters() throws Exception {

        TimeSignature ts;
        for (int numerator = 0; numerator < 10; numerator++) {
            for (Beat beat : Beat.values()) {
                ts = new TimeSignature(numerator, beat);
                if ((1 / beat.getRatio()) % 2 == 0) {
                    assertFalse(ts.is(MeterProperty.IRRATIONAL));
                }
                else {
                    assertTrue(ts.is(MeterProperty.IRRATIONAL));
                }
            }
        }
    }

    @Test
    public void additiveMeters() throws Exception {
        // TODO
    }

    @Test
    public void setStressPattern() throws Exception {
        TimeSignature ts = new TimeSignature();
        assertFalse(ts.setStressPattern(2, 2, 2));

        // TODO: test more cases (TimeSignatures)
        ts = new TimeSignature(8, Beat.EIGHTH);
        boolean[] expected = {true, false, false, true, false, true, false, false};
        ts.setStressPattern(3, 2, 3);
        boolean[] actual = ts.getStressPattern();
        assertArrayEquals(expected, actual);

        ts = new TimeSignature(4, Beat.QUARTER);
        for (boolean b : ts.getStressPattern()) System.out.print(b ? 1 : 0);
    }
}