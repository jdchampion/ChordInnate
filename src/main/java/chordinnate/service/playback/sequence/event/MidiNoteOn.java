package chordinnate.service.playback.sequence.event;

import chordinnate.service.playback.sequence.MidiType;
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
        ShortMessage sm = new ShortMessage();
        sm.setMessage(ShortMessage.NOTE_ON, newEventState.getChannel(), newEventState.getNoteValue(), newEventState.getVelocity());
        MidiEvent event = new MidiEvent(sm, newEventState.getStartTick());

        // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
        if (MidiType.TYPE_ZERO.equals(newEventState.getMidiType()) || MidiType.TYPE_ONE.equals(newEventState.getMidiType())) {
            assert newEventState.getTrackNumber() == 0;
        }

        getTrack(sequence, newEventState).add(event);
    }
}
