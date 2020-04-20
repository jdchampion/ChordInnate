package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import java.util.Arrays;

/**
 * Generates a MIDI SET_TEMPO message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiSetTempo extends MidiEventGenerator {

    private static MidiSetTempo instance;

    public static MidiSetTempo getInstance() {
        if (instance == null) {
            instance = new MidiSetTempo();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        long usecPerPulse = newEventState.getMicroSecondsPerTempoPulse();

        byte[] data = {
                (byte) ((usecPerPulse & 0xFF000000) >> 24),
                (byte) ((usecPerPulse & 0x00FF0000) >> 16),
                (byte) ((usecPerPulse & 0x0000FF00) >> 8),
                (byte) (usecPerPulse & 0x000000FF)
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
        mm.setMessage(0x51, data, 3);
        MidiEvent event = new MidiEvent(mm, newEventState.getStartTick());

        // ALL MIDI types (0, 1, 2) should place this event on Track 0.
        assert newEventState.getTrackNumber() == 0;

        getTrack(sequence, newEventState).add(event);
    }
}
