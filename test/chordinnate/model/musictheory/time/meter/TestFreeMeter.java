package chordinnate.model.musictheory.time.meter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/3/16.
 */
public class TestFreeMeter {
    // TODO: more formal testing
    @Test
    public void is() throws Exception {
        FreeMeter freeMeter = new FreeMeter();
        for (MeterClassificationType meterClassificationType : MeterClassificationType.values()) {
            if (meterClassificationType.equals(MeterClassificationType.FREE)) {
                assertTrue(freeMeter.isType(meterClassificationType));
            }
            else {
                assertFalse(freeMeter.isType(meterClassificationType));
            }
        }
    }

}