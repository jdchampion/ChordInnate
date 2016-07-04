package chordinnate.musictheory.time.meter;


/**
 * Meters for which there are no measurement (i.e., subdivisions) of time.
 *
 * Created by Joseph on 7/2/16.
 */
public final class FreeMeter extends InfiniteMeter {
    public FreeMeter() {
        meterClassificationTypes.add(MeterClassificationType.FREE);
    }

    @Override
    boolean hasValidArguments(Number[] numerator, Number denominator) {
        return false;
    }
}
