package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI SMPTE_OFFSET message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiSMPTEOffset extends MidiEventGenerator {

    private static MidiSMPTEOffset instance;

    public static MidiSMPTEOffset getInstance() {
        if (instance == null) {
            instance = new MidiSMPTEOffset();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}
