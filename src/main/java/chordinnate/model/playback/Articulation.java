package chordinnate.model.playback;

import lombok.AllArgsConstructor;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Articulation_(music)
 *             https://en.wikipedia.org/wiki/Accent_(music)
 *             http://www.timusic.net/2014/01/jazz-notation/
 */
@AllArgsConstructor
public enum Articulation {
    // Duration Articulations
    TENUTO(1, 1),
    MARCATO(1.0/3.0, 1.3),
    STACCATO(1.0/3.0, 1),
    STACCATISSIMO(1.0/4.5, 1),
    ACCENT(0.5, 1.15),
    LEGATO(1, 1),

    ;

    final double lengthModifier; // The amount of the Note's total length that is to be sounded
    final double volumeModifier; // The percentage of the current volume to sound the Note at

}
