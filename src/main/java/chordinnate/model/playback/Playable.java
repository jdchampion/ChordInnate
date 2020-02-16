package chordinnate.model.playback;

import chordinnate.service.playback.PlaybackService;

import javax.sound.midi.Sequence;

/**
 * Musical components that can be played by the {@link PlaybackService}.
 */
public interface Playable {

    default Sequence getMidiSequence() {
        return null; // FIXME: either implement the default or force implementation on classes
    }

}
