package chordinnate.model.musictheory.time.meter;

/**
 * Created by Joseph on 7/3/16.
 */
public class FixedMeterNode {
    int count;
    FixedMeter meter;

    FixedMeterNode(int count, FixedMeter meter) {
        this.count = count;
        this.meter = meter;
    }
}
