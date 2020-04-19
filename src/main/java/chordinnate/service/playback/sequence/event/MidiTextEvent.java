package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI TEXT_EVENT message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 137)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiTextEvent extends MidiEventGenerator {

    private static MidiTextEvent instance;

    public static MidiTextEvent getInstance() {
        if (instance == null) {
            instance = new MidiTextEvent();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}

