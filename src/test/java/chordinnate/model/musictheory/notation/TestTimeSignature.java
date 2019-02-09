package chordinnate.model.musictheory.notation;

import chordinnate.model.musictheory.time.meter.MeterClassificationType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.StringJoiner;

/**
 * Created by Joseph on 7/6/16.
 */
@Slf4j
public class TestTimeSignature {
    // TODO: more formal testing
    @Test
    public void quickVisualTest() throws Exception {
        TimeSignature timeSignature = new TimeSignature(5, 8);
        assertNotNull(timeSignature.meter);
        StringJoiner sj = new StringJoiner(" ");
        for (MeterClassificationType mct : MeterClassificationType.values()) {
            if (timeSignature.meter.isType(mct)) sj.add(mct.name());
        }
        log.info(sj.toString());
    }
}