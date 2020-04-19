package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

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
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.POLY_PRESSURE, newEventState.getChannel(), newEventState.getNoteValue(), newEventState.getVelocity());
        MidiEvent event = new MidiEvent(sm, newEventState.getStartTick());
        getTrack(sequence, newEventState).add(event);
    }
}
