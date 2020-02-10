package chordinnate.model.musictheory.time.meter;

import chordinnate.model.musictheory.time.meter.fixed.complete.AdditiveCompleteMeter;
import chordinnate.model.musictheory.time.rhythm.BeatDuration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static chordinnate.model.musictheory.time.meter.MeterSubdivision.*;

import java.util.StringJoiner;

/**
 * Created by Joseph on 7/6/16.
 */
@Slf4j
public class AdditiveCompleteMeterTest {
    // TODO: more formal testing
//    @Test
//    public void is() throws Exception {
//        AdditiveCompleteMeter
//                fourFour1 = new AdditiveCompleteMeter(4, BeatDuration.QUARTER, DUPLE, DUPLE),
//                nineEight = new AdditiveCompleteMeter(9, BeatDuration.EIGHTH, DUPLE, TRIPLE, QUADRUPLE),
//                sixEight = new AdditiveCompleteMeter(6, BeatDuration.EIGHTH, DUPLE, DUPLE, DUPLE),
//                threeEight = new AdditiveCompleteMeter(3, BeatDuration.EIGHTH, TRIPLE),
//                twelveEight = new AdditiveCompleteMeter(12, BeatDuration.EIGHTH, TRIPLE, QUADRUPLE, DUPLE, TRIPLE),
//                fourFour2 = new AdditiveCompleteMeter(4, BeatDuration.QUARTER, QUADRUPLE),
//                elevenSixteen = new AdditiveCompleteMeter(11, BeatDuration.SIXTEENTH, QUADRUPLE, QUADRUPLE, TRIPLE);
//
//        StringJoiner sj1 = new StringJoiner(" ");
//        for (MeterType mct : MeterType.values()) {
//            if (sixEight.isType(mct)) sj1.add(mct.name());
//        }
//        log.info("6/8:\tClassifications:\t" + sj1.toString());
//
//        StringJoiner sj2 = new StringJoiner(" ");
//        for (MeterSubdivision meterSubdivision : sixEight.getSubdivisions()) {
//            sj2.add(meterSubdivision.name());
//        }
//        log.info("6/8:\tSubdivisions:\t" + sj2.toString());
//    }
}