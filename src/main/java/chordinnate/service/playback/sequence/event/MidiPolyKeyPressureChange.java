package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI POLY_KEY_PRESSURE_CHANGE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiPolyKeyPressureChange extends MidiAfterTouch {
    private static MidiPolyKeyPressureChange instance;

    public static MidiPolyKeyPressureChange getInstance() {
        if (instance == null) {
            instance = new MidiPolyKeyPressureChange();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO: use AnH as the status byte
    }
}
