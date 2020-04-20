package chordinnate.service.playback.sequence.event;

import chordinnate.service.playback.sequence.MidiType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import java.nio.charset.StandardCharsets;

/**
 * Generates a MIDI CUE_POINT message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiCuePoint extends MidiEventGenerator {

    private static MidiCuePoint instance;

    public static MidiCuePoint getInstance() {
        if (instance == null) {
            instance = new MidiCuePoint();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {

        if (StringUtils.isNotBlank(newEventState.getCuePoint())) {

            byte[] data = newEventState.getCuePoint().getBytes(StandardCharsets.US_ASCII);

            MetaMessage mm = new MetaMessage();
            mm.setMessage(0x07, data, data.length);
            MidiEvent event = new MidiEvent(mm, newEventState.getStartTick());

            // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
            if (MidiType.TYPE_ZERO.equals(newEventState.getMidiType()) || MidiType.TYPE_ONE.equals(newEventState.getMidiType())) {
                assert newEventState.getTrackNumber() == 0;
            }

            getTrack(sequence, newEventState).add(event);
        }
    }
}
