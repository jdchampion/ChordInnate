package chordinnate.model.playback;

import chordinnate.model.musictheory.temporal.rhythm.Beat;

/**
 * Classes implementing this interface possess rhythmic properties.
 */
public interface Rhythmic extends Playable {
    Beat getBeat();
}
