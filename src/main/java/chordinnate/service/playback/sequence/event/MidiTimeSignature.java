package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI TIME_SIGNATURE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 139)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiTimeSignature extends MidiEventGenerator {

    private static MidiTimeSignature instance;

    public static MidiTimeSignature getInstance() {
        if (instance == null) {
            instance = new MidiTimeSignature();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}
