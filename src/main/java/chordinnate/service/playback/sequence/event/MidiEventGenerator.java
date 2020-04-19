package chordinnate.service.playback.sequence.event;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public abstract class MidiEventGenerator {

    public abstract void addEvent(Sequence sequence, MidiEventDataBundle newEventState) throws InvalidMidiDataException;

    public Track getTrack(Sequence sequence, MidiEventDataBundle newEventState) {
        int trackNumber = newEventState.getTrackNumber();
        Track[] tracks = sequence.getTracks();
        int length = tracks.length - 1;
        while (length < trackNumber) {
            sequence.createTrack();
            length++;
        }
        return sequence.getTracks()[trackNumber];
    }
}
