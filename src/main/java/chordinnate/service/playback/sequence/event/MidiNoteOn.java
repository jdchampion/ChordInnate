package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

/**
 * Generates a MIDI NOTE_ON message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiNoteOn extends MidiEventGenerator {

    private static MidiNoteOn instance;

    public static MidiNoteOn getInstance() {
        if (instance == null) {
            instance = new MidiNoteOn();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        ShortMessage noteOn = new ShortMessage();
        noteOn.setMessage(ShortMessage.NOTE_ON, newEventState.getChannel(), newEventState.getNoteValue(), newEventState.getVelocity());
        MidiEvent noteOnEvent = new MidiEvent(noteOn, newEventState.getStartTick());
        getTrack(sequence, newEventState).add(noteOnEvent);
    }
}
