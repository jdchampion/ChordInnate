package chordinnate.musictheory.time.meter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/3/16.
 */
public class TestFreeMeter {
    @Test
    public void is() throws Exception {
        FreeMeter freeMeter = new FreeMeter();
        for (MeterProperty meterProperty : MeterProperty.values()) {
            if (meterProperty.equals(MeterProperty.FREE)) {
                assertTrue(freeMeter.is(meterProperty));
            }
            else {
                assertFalse(freeMeter.is(meterProperty));
            }
        }
    }

}