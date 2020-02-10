package chordinnate.model.musictheory.temporal.rhythm;


import lombok.AllArgsConstructor;

/**
 * Created by Joseph on 6/1/16.
 */
@AllArgsConstructor
public enum BeatDuration {
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

    public final double ratio;
    
}
