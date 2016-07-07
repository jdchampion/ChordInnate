package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/6/16.
 */
public class TestAdditiveCompleteMeter {
    @Test
    public void is() throws Exception {
        AdditiveCompleteMeter
                fourFour1 = new AdditiveCompleteMeter(4, Duration.QUARTER),
                nineEight = new AdditiveCompleteMeter(9, Duration.EIGHTH),
                sixEight = new AdditiveCompleteMeter(6, Duration.EIGHTH),
                threeEight = new AdditiveCompleteMeter(3, Duration.EIGHTH),
                twelveEight = new AdditiveCompleteMeter(12, Duration.EIGHTH),
                fourFour2 = new AdditiveCompleteMeter(4, Duration.QUARTER, MeterSubdivision.QUADRUPLE),
                oneFour = new AdditiveCompleteMeter(1, Duration.QUARTER),
                elevenSixteen = new AdditiveCompleteMeter(11, Duration.SIXTEENTH);

        System.out.print("6/8: ");
        for (MeterClassificationType mct : MeterClassificationType.values()) {
            if (sixEight.is(mct)) System.out.print(mct + " ");
        }
        System.out.println();

        for (MeterSubdivision meterSubdivision : sixEight.subdivisions) {
            System.out.print(meterSubdivision + " ");
        }
    }
}