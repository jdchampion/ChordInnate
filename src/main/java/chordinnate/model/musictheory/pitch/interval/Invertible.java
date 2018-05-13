package chordinnate.model.musictheory.pitch.interval;


/**
 * Created by Joseph on 4/14/16.
 */
public interface Invertible<T> {

    /**
     * Performs an inversion on the implementing class.
     * May or may not perform side-effects on the implementing class object(s).
     *
     * @return {@link T} type specified by the implementing class
     */
    T invert();

}
