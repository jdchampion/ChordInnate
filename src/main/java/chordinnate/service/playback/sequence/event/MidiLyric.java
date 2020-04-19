package chordinnate.service.playback.sequence.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Generates a MIDI LYRIC message.
 * Reference: The Complete MIDI 1.0 Detailed Specification (p. 138)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MidiLyric extends MidiEventGenerator {

    private static MidiLyric instance;

    public static MidiLyric getInstance() {
        if (instance == null) {
            instance = new MidiLyric();
        }
        return instance;
    }

    @Override
    public void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException {
        // TODO
    }
}
