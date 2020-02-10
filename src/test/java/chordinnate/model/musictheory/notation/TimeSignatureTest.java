package chordinnate.model.musictheory.notation;

import static chordinnate.model.musictheory.time.meter.MeterSubdivision.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joseph on 7/6/16.
 */
@Slf4j
public class TimeSignatureTest {
    @Test
    public void visualTest() {
        TimeSignature ts = new TimeSignature(2, 4);
        log.info(ts.getDisplayString());

        ts = new TimeSignature(Arrays.asList(DUPLE, TRIPLE, DUPLE, DUPLE), 8);
        log.info(ts.getDisplayString());

        ts = new TimeSignature(Fraction.getFraction(5, 4), 4);
        log.info(ts.getDisplayString());
    }
}