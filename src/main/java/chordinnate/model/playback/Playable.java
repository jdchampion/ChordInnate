package chordinnate.model.playback;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.service.playback.PlaybackService;

/**
 * Musical components that can be played by the {@link PlaybackService}.
 */
public interface Playable {
    void accept(MidiEventProducer midiEventProducer);
}
