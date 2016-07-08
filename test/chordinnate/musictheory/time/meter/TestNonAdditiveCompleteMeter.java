package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Duration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/6/16.
 */
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