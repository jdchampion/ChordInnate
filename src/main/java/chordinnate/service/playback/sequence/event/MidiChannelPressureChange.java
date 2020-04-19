package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI CHANNEL_PRESSURE_CHANGE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiChannelPressureChange extends MidiAfterTouch {
    private static MidiChannelPressureChange instance;

    public static MidiChannelPressureChange getInstance() {
        if (instance == null) {
            instance = new MidiChannelPressureChange();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO: use DnH as the status byte
    }
}
