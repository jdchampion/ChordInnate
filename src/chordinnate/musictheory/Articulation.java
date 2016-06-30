package chordinnate.musictheory;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Articulation_(music)
 */
public enum Articulation {
    TENUTO(1),
    MARCATO(0.66),
    STACCATO(0.33),
    LEGATO(1),

    ;

    private final double LENGTH;

    Articulation(double length) {
        this.LENGTH = length;
    }

    public double getLength() {
        return LENGTH;
    }
}
