package chordinnate.service.playback.sequence;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.playback.Note;
import chordinnate.service.playback.Playable;
import org.springframework.context.ApplicationContext;

import javax.sound.midi.Sequence;

public class SequenceGenerator {

    private final MidiEventGeneratorCallable callable;

    public SequenceGenerator(ApplicationContext context) {
        this.callable = new MidiEventGeneratorCallable(context);
    }

    /**
     * Generates a playable MIDI Sequence for a single Pitch.
     * @param pitch
     * @return
     */
    public Sequence getSequence(Pitch pitch) {
        return callable.buildSequence(Note.builder(Beat.QUARTER, pitch).build());
    }

    /**
     * Generates a playable MIDI Sequence for a Playable object.
     * @param playable
     * @return
     */
    public Sequence getSequence(Playable playable) {
        return callable.buildSequence(playable);
    }
}
