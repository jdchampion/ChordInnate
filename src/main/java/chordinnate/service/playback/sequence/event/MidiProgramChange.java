package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

/**
 * Generates a MIDI PROGRAM_CHANGE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiProgramChange extends MidiEventGenerator {

    private static MidiProgramChange instance;

    public static MidiProgramChange getInstance() {
        if (instance == null) {
            instance = new MidiProgramChange();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.PROGRAM_CHANGE, newEventState.getChannel(), newEventState.getInstrument(), 0);
        MidiEvent event = new MidiEvent(sm, newEventState.getStartTick());
        getTrack(sequence, newEventState).add(event);
    }
}
