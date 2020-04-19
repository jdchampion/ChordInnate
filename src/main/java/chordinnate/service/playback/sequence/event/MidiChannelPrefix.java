package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI CHANNEL_PREFIX message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiChannelPrefix extends MidiEventGenerator {

    private static MidiChannelPrefix instance;

    public static MidiChannelPrefix getInstance() {
        if (instance == null) {
            instance = new MidiChannelPrefix();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}
