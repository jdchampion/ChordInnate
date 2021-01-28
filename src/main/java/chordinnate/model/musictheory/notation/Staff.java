package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.FormPlayable;
import chordinnate.model.playback.InstrumentCapablePlayable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Staff extends InstrumentCapablePlayable {

    private final String staffName;

    protected FormPlayable playable;

    @Override
    public void accept(@NotNull MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
