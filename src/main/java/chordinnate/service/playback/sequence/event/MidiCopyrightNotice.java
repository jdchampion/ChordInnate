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
 * Generates a MIDI COPYRIGHT_NOTICE message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 41)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiCopyrightNotice extends MidiEventGenerator {

    private static MidiCopyrightNotice instance;

    public static MidiCopyrightNotice getInstance() {
        if (instance == null) {
            instance = new MidiCopyrightNotice();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {

        if (StringUtils.isNotBlank(newEventState.getCopyright())) {

            byte[] data = newEventState.getCopyright().getBytes(StandardCharsets.US_ASCII);

            MetaMessage mm = new MetaMessage();
            mm.setMessage(0x02, data, data.length);
            MidiEvent event = new MidiEvent(mm, 0);

            // MIDI 0 and MIDI 1 will only contain Track 0, so this message must go there in those cases
            if (MidiType.TYPE_ZERO.equals(newEventState.getMidiType()) || MidiType.TYPE_ONE.equals(newEventState.getMidiType())) {
                assert newEventState.getTrackNumber() == 0;
            }

            getTrack(sequence, newEventState).add(event);
        }

    }
}

