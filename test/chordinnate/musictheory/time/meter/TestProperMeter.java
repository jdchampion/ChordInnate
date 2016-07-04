package chordinnate.musictheory.time.meter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/3/16.
 */
public class TestProperMeter {
    @Test
    public void is() throws Exception {
        Meter
                threeEight = new ProperMeter(8, 3),
                sixEight = new ProperMeter(8, 6),
                sevenEight = new ProperMeter(8, 7),
                nineEight = new ProperMeter(8, 9),
                fourFour = new ProperMeter(4, 4),
                elevenSixteen = new ProperMeter(16, 11),
                twelveEight = new ProperMeter(8, 12),
                subDiv98 = new ProperMeter(8, 2,3,2,2),
                subDiv54 = new ProperMeter(4, 2,3);

        print(threeEight, "3/8");
        print(sixEight, "6/8");
        print(sevenEight, "7/8");
        print(nineEight, "9/8");
        print(fourFour, "4/4");
        print(elevenSixteen, "11/16");
        print(twelveEight, "12/8");
        print(subDiv98, "2+3+2+2/8");
        print(subDiv54, "2+3/4");
        print(subDiv54, "2+3/4 " + subDiv54.toggleAdditiveMeterClassification());
        print(subDiv54, "2+3/4 " + subDiv54.toggleAdditiveMeterClassification());
        subDiv54 = subDiv54.setSubdivisionPattern(5);
        print(subDiv54, "5/4");
        print(subDiv54, "5/4 " + subDiv54.toggleAdditiveMeterClassification());
        print(subDiv54, "5/4 " + subDiv54.toggleAdditiveMeterClassification());
    }

    public void print(Meter meter, String sig) {
        System.out.print(sig + ": ");
        for (MeterClassificationType m: MeterClassificationType.values()) {
            if (meter.is(m)) {
                System.out.print(m + " ");
            }
        }
        System.out.println();
    }

}