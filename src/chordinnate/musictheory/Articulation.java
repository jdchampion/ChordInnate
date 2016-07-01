package chordinnate.musictheory;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Articulation_(music)
 *             https://en.wikipedia.org/wiki/Accent_(music)
 *             http://www.timusic.net/2014/01/jazz-notation/
 */
public enum Articulation {
    // Duration Articulations
    TENUTO(1, 1),
    MARCATO(0.33, 1.3),
    STACCATO(0.33, 1),
    STACCATISSIMO(0.165, 1),
    ACCENT(0.5, 1.15),
    LEGATO(1, 1),

    // TODO: Effect Articulations
//    FALL(1, 1),
//    QUICK_FALL(0.5, 1),
//    SHAKE(1, 1),
//    GLISSANDO(1, 1),
//    RIP(1, 1),
//    DOIT(1, 1),
//    SCOOP(1, 1),
//    BEND(1, 1),
//    TURN(1, 1),

    ;

    private final double
            LENGTH_MODIFIER, // The amount of the Note's total length that is to be sounded
            VOLUME_MODIFIER; // The percentage of the current volume to sound the Note at

    Articulation(double length, double volume) {
        this.LENGTH_MODIFIER = length;
        this.VOLUME_MODIFIER = volume;
    }

    public double getLengthModifier() {
        return LENGTH_MODIFIER;
    }

    public double getVolumeModifier() {
        return VOLUME_MODIFIER;
    }
}
