package chordinnate.model.musictheory.time.meter;

import chordinnate.model.musictheory.time.meter.fixed.complete.NonAdditiveCompleteMeter;
import chordinnate.model.musictheory.time.rhythm.Duration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.StringJoiner;

/**
 * Created by Joseph on 7/6/16.
 */
@Slf4j
public class TestNonAdditiveCompleteMeter {
    // TODO: more formal testing
    @Test
    public void is() throws Exception {
        NonAdditiveCompleteMeter
                fourFour1 = new NonAdditiveCompleteMeter(4, Duration.QUARTER),
                nineEight = new NonAdditiveCompleteMeter(9, Duration.EIGHTH),
                sixEight = new NonAdditiveCompleteMeter(6, Duration.EIGHTH),
                threeEight = new NonAdditiveCompleteMeter(3, Duration.EIGHTH),
                twelveEight = new NonAdditiveCompleteMeter(12, Duration.EIGHTH),
                fourFour2 = new NonAdditiveCompleteMeter(4, Duration.QUARTER, MeterSubdivision.QUADRUPLE),
                oneFour = new NonAdditiveCompleteMeter(1, Duration.QUARTER),
                elevenSixteen = new NonAdditiveCompleteMeter(11, Duration.SIXTEENTH);

        StringJoiner sj1 = new StringJoiner(" ");
        for (MeterClassificationType mct : MeterClassificationType.values()) {
            if (sixEight.isType(mct)) sj1.add(mct.name());
        }
        log.info("6/8:\tClassifications:\t" + sj1.toString());

        StringJoiner sj2 = new StringJoiner(" ");
        for (MeterSubdivision meterSubdivision : sixEight.getSubdivisions()) {
            sj2.add(meterSubdivision.name());
        }
        log.info("6/8:\tSubdivisions:\t" + sj2.toString());
    }
}