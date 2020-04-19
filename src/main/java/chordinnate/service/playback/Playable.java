package chordinnate.service.playback;

import chordinnate.service.playback.sequence.SequenceGenerator;

import javax.sound.midi.Sequence;

/**
 * Musical components that can be played by the {@link PlaybackService}.
 */
public interface Playable {
    Sequence accept(SequenceGenerator sequenceGenerator); // visitor design pattern
}
