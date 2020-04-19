package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

/**
 * Generates a MIDI NOTE_OFF message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiNoteOff extends MidiEventGenerator {

    private static MidiNoteOff instance;

    public static MidiNoteOff getInstance() {
        if (instance == null) {
            instance = new MidiNoteOff();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.NOTE_OFF, newEventState.getChannel(), newEventState.getNoteValue(), 0);
        MidiEvent event = new MidiEvent(sm, newEventState.getEndTick());
        getTrack(sequence, newEventState).add(event);
    }
}
