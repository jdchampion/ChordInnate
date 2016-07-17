package chordinnate.musictheory.time.rhythm;

import chordinnate.util.Sequential;
import chordinnate.util.SequentialUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Joseph on 6/1/16.
 */
public enum Duration implements Sequential {
    /*
     * NOTE: Keep these ordered from smallest to largest.
     */
    SIXTY_FOURTH(0.015625),
    THIRTY_SECOND(0.03125),
    SIXTEENTH(0.0625),
    EIGHTH(0.125),
    QUARTER(0.25),
    HALF(0.5),
    WHOLE(1.0),
    DOUBLE_WHOLE(2.0),

    ;

    public final double RATIO;
    private static final Duration[] VALUES = Duration.values();

    Duration(double ratio) {
        this.RATIO = ratio;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public Duration getNext() {
        return (Duration) SequentialUtil.getNext(this, VALUES);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public Duration getPrevious() {
        return (Duration) SequentialUtil.getPrevious(this, VALUES);
    }
}
