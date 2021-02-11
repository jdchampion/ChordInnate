package chordinnate.model.musictheory.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Joseph on 6/1/16.
 *
 * @see <a href=https://en.wikipedia.org/wiki/Articulation_(music)>1</a>
 * @see <a href=https://en.wikipedia.org/wiki/Accent_(music)>2</a>
 * @see <a href=http://www.timusic.net/2014/01/jazz-notation>3</a>
 */
@Getter
@AllArgsConstructor
public enum Articulation {
    TENUTO(1, 1),
    MARCATO(1.0/3.0, 1.3),
    STACCATO(1.0/3.0, 1),
    STACCATISSIMO(1.0/4.5, 1),
    ACCENT(0.5, 1.15),
    LEGATO(1, 1),

    ;

    // TODO: allow these to be configurable? (map the enum with user-supplied values in a config class)
    private final double durationFactor; // The amount of the Note's total length that is to be sounded
    private final double velocityFactor; // The percentage of the current volume to sound the Note at

}
