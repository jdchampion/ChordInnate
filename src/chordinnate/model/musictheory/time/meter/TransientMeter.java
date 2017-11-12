package chordinnate.model.musictheory.time.meter;

import java.util.LinkedList;

/**
 * Any meter that contains more than one (1) change in its measurement of time.
 *
 * Created by Joseph on 7/3/16.
 */
abstract class TransientMeter extends Meter {
    LinkedList<FixedMeterNode> meterPattern;

    public int getNumberOfMeterChanges() {
        return meterPattern.size();
    }
}
