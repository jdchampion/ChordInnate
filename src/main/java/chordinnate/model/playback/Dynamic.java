package chordinnate.model.playback;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Dynamics_%28music%29
 *             https://www.cs.cmu.edu/~music/cmsip/readings/MIDI%20tutorial%20for%20programmers.html
 */
@Getter
@AllArgsConstructor
public enum Dynamic {
    PIANISSISSISSIMO("pppp", 8),
    PIANISSISSIMO("ppp", 20),
    PIANISSIMO("pp", 31),
    PIANO("p", 42),
    MEZZO_PIANO("mp", 53),
    MEZZO_FORTE("mf", 64),
    FORTE("f", 80),
    FORTISSIMO("ff", 96),
    FORTISSISSIMO("fff", 112),
    FORTISSISSISSIMO("ffff", 127),

    ;

    private final String symbol;
    private final int velocity;

}
