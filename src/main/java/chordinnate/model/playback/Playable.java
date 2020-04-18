package chordinnate.model.playback;

import chordinnate.service.playback.visitor.SequenceVisitor;
import chordinnate.service.playback.PlaybackService;

import javax.sound.midi.Sequence;

/**
 * Musical components that can be played by the {@link PlaybackService}.
 */
public interface Playable {
    Sequence accept(SequenceVisitor sequenceVisitor);
}
