package chordinnate.model.musictheory.time.meter;

import chordinnate.model.musictheory.time.rhythm.Duration;
import org.junit.Test;

import static chordinnate.model.musictheory.time.meter.MeterSubdivision.*;

/**
 * Created by Joseph on 7/6/16.
 */
public class TestAdditiveCompleteMeter {
    // TODO: more formal testing
    @Test
    public void is() throws Exception {
        AdditiveCompleteMeter
                fourFour1 = new AdditiveCompleteMeter(4, Duration.QUARTER, DUPLE, DUPLE),
                nineEight = new AdditiveCompleteMeter(9, Duration.EIGHTH, DUPLE, TRIPLE, QUADRUPLE),
                sixEight = new AdditiveCompleteMeter(6, Duration.EIGHTH, DUPLE, DUPLE, DUPLE),
                threeEight = new AdditiveCompleteMeter(3, Duration.EIGHTH, TRIPLE),
                twelveEight = new AdditiveCompleteMeter(12, Duration.EIGHTH, TRIPLE, QUADRUPLE, DUPLE, TRIPLE),
                fourFour2 = new AdditiveCompleteMeter(4, Duration.QUARTER, QUADRUPLE),
                elevenSixteen = new AdditiveCompleteMeter(11, Duration.SIXTEENTH, QUADRUPLE, QUADRUPLE, TRIPLE);

        System.out.print("\n6/8:\n\tClassifications:\t");
        for (MeterClassificationType mct : MeterClassificationType.values()) {
            if (sixEight.isType(mct)) System.out.print(mct + " ");
        }
        System.out.print("\n\t\t\t\t\t\t===================================================================");
        System.out.print("\n\tSubdivisions:\t\t");

        for (MeterSubdivision meterSubdivision : sixEight.subdivisions) {
            System.out.print(meterSubdivision + " ");
        }
    }
}