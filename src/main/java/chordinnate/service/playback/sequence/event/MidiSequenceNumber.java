package chordinnate.service.playback.sequence.event;

import chordinnate.service.playback.sequence.MidiType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import java.util.Arrays;

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

        int sequenceNumber = newEventState.getSequenceNumber();

        byte[] data = {
                (byte) ((sequenceNumber & 0x00FF0000) >> 16),
                (byte) ((sequenceNumber & 0x0000FF00) >> 8),
                (byte) (sequenceNumber & 0x000000FF)
        };

        // Remove empty data bytes by right-shifting the start index
        int i = 0;
        while (i < data.length - 1 && data[i] == 0) {
            i++;
        }

        if (i != 0) {
            data = Arrays.copyOfRange(data, i, data.length);
        }

        MetaMessage mm = new MetaMessage();
        mm.setMessage(0x00, data, data.length);
        MidiEvent event = new MidiEvent(mm, newEventState.getStartTick());

        // MIDI 0 and MIDI 1 will only contain a single Track, so this message must go there in those cases
        if (MidiType.TYPE_ZERO.equals(newEventState.getMidiType()) || MidiType.TYPE_ONE.equals(newEventState.getMidiType())) {
            assert newEventState.getTrackNumber() == 0;
        }

        getTrack(sequence, newEventState).add(event);
    }
}

