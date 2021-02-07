package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.playback.Rhythmic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;

@Slf4j
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rest implements Rhythmic {

    @NotNull
    private Beat beat;

    public Rest(@NotNull Beat beat) {
        this.beat = beat;
    }

    @Override
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }

}
