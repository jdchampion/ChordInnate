package chordinnate.musictheory;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Articulation_(music)
 */
public enum Articulation {
    TENUTO(0, 1),
    MARCATO(0, 0.66),
    STACCATO(0, 0.33),
    LEGATO(0, 1),

    ;

    private final double ATTACK;
    private final double DELAY;

    Articulation(double attack, double delay) {
        this.ATTACK = attack;
        this.DELAY = delay;
    }

    public double getAttack() {
        return ATTACK;
    }

    public double getDelay() {
        return DELAY;
    }
}
