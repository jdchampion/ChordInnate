package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI KEY_SIGNATURE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 139)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiKeySignature extends MidiEventGenerator {

    private static MidiKeySignature instance;

    public static MidiKeySignature getInstance() {
        if (instance == null) {
            instance = new MidiKeySignature();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}
