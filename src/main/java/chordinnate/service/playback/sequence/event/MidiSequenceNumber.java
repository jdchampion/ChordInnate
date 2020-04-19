package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI SEQUENCE_NUMBER message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiSequenceNumber extends MidiEventGenerator {

    private static MidiSequenceNumber instance;

    public static MidiSequenceNumber getInstance() {
        if (instance == null) {
            instance = new MidiSequenceNumber();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}

