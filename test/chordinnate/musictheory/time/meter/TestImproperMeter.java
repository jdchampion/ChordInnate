package chordinnate.musictheory.time.meter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 7/3/16.
 */
public class TestImproperMeter {
    @Test
    public void is() throws Exception {
        ImproperMeter
                fivePi = new ImproperMeter(Math.PI, 5),
                piFive = new ImproperMeter(5, Math.PI),
                piPi = new ImproperMeter(Math.PI, Math.PI), // mmm, pi...
                subDivFivePi1 = new ImproperMeter(Math.PI, 2.5,1.9,0.6),
                subDivFivePi2 = new ImproperMeter(Math.PI, 2,3);

        print(fivePi, "5/pi");
        print(piFive, "pi/5");
        print(piPi, "pi/pi");
        print(subDivFivePi1, "2.5+1.9+0.6/pi");
        print(subDivFivePi2, "2+3/pi");
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