package chordinnate.model.musictheory.pitch.interval;


/**
 * Created by Joseph on 4/14/16.
 */
public interface Invertible {
    /**
     * Performs an inversion on the implementing class, with side effects.
     */
    void invert();
}
