package chordinnate.model.playback;

import chordinnate.service.playback.MidiEventGenerator;
import chordinnate.service.playback.PlaybackService;
import chordinnate.service.playback.SequenceGenerator;

import javax.sound.midi.Sequence;

/**
 * Musical components that can be played by the {@link PlaybackService}.
 */
public interface Playable {
    Sequence accept(SequenceGenerator sequenceGenerator); // visitor design pattern
    void accept(MidiEventGenerator midiEventGenerator);
}
