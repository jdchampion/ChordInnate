package chordinnate.model.musictheory.notation;

import chordinnate.model.musictheory.time.meter.fixed.FixedMeter;
import chordinnate.model.musictheory.time.meter.fixed.complete.NonAdditiveCompleteMeter;
import chordinnate.model.musictheory.time.rhythm.Duration;

/**
 * Created by Joseph on 7/6/16.
 */
public class TimeSignature {
    FixedMeter meter;

    /**
     * Constructs a TimeSignature whose numerator and denominator are both integers.
     * Therefore, the Meter for this TimeSignature will be a FixedMeter.
     * If the denominator is a power of two, it will be a CompleteMeter.
     * Otherwise, it will be an IncompleteMeter.
     * @param numerator
     * @param denominator
     */
    public TimeSignature(int numerator, int denominator) {
        if (numerator < 1 || denominator < 1) {
            throw new IllegalArgumentException("Numerator or denominator < 1; both must be >= 1");
        }

        double lgDenom = Math.log(denominator) / Math.log(2);
        if (Math.ceil(lgDenom) == Math.floor(lgDenom)) {
            // create a CompleteMeter
            Duration beatValue = null;
            if (numerator == 2 && denominator == 1) {
                beatValue = Duration.DOUBLE_WHOLE; // edge case
            }
            for (Duration duration : Duration.values()) {
                if (beatValue != null) break;
                if ((1 / duration.ratio) == denominator) beatValue = duration;
            }
            if (beatValue == null) {
                // the beat value is a power of 2, but it's too small of a subdivision
                throw new IllegalArgumentException(
                        "Unsupported beat value for the Time Signature (" + denominator + ")");
            }
            this.meter = new NonAdditiveCompleteMeter(numerator, beatValue);
        } else {
            // TODO: create an IncompleteMeter
        }
    }
}
