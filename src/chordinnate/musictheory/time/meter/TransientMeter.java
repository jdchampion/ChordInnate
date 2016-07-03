package chordinnate.musictheory.time.meter;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/2/16.
 */
public abstract class TransientMeter extends Meter {
    ArrayList<FixedMeter> differentMeters;
    int indexOfMeterBeingUsed;

    protected Meter getCurrentMeter() {
        return differentMeters.get(indexOfMeterBeingUsed);
    }

    double updateMeasureDuration() {
        return (differentMeters.size() > 0) ? differentMeters.get(indexOfMeterBeingUsed).measureDuration : -1;
    }

    void changeCurrentMeter() {
        indexOfMeterBeingUsed = (++indexOfMeterBeingUsed) % differentMeters.size();
        measureDuration = updateMeasureDuration();
    }
}
