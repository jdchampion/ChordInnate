package chordinnate.musictheory.time.meter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/6/16.
 */
public class TestTimeSignature {
    // TODO: more formal testing
    @Test
    public void quickVisualTest() throws Exception {
        TimeSignature timeSignature = new TimeSignature(5, 8);
        assertNotNull(timeSignature.meter);
        for (MeterClassificationType mct : MeterClassificationType.values()) {
            if (timeSignature.meter.is(mct)) System.out.print(mct + " ");
        }
    }
}