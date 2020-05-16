package chordinnate.service.playback.sequence;

import chordinnate.config.MidiConfig;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.event.MidiEventGeneratorImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import javax.sound.midi.Sequence;

@Getter
@Setter
@AllArgsConstructor
public class MidiEventGeneratorCallable {

    private MidiConfig config;

    private long tick = 0;

    public MidiEventGeneratorCallable(ApplicationContext context) {
        this.config = context.getBean(MidiConfig.class);
    }

    Sequence buildSequence(Playable playable) {
        Sequence sequence;
        try {
            sequence = new Sequence(config.getFrames(), config.getTickResolution());

            MidiEventGeneratorImpl generator = new MidiEventGeneratorImpl(sequence, this);

            // Always add these to the beginning of every MIDI sequence
            generator.addSequenceNumberEvent(config.getTrack(), 0);
            generator.addSetTempoEvent(0, config.getTempo());
            generator.addProgramChangeEvent(0, config.getTrack(), config.getChannel(), config.getInstrument());
            generator.addCopyrightNoticeEvent(config.getTrack(), ""); // TODO: add only if necessary
            generator.addTrackNameEvent(config.getTrack(), ""); // TODO: add only if necessary
//            generator.addSMPTEOffsetEvent(); // TODO: add only if necessary
            generator.addTimeSignatureEvent(0, config.getTimeSignature()); // TODO: add this here?
            generator.addKeySignatureEvent(0, config.getKeySignature()); // TODO: add this here?

            // add all custom MIDI events
            playable.accept(generator);

            generator.addEndOfTrackEvent();

        } catch (Exception ex) {
            return null;
        }
        return sequence;
    }
}