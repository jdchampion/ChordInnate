package chordinnate.model.musictheory.notation;

import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.playback.FormPlayable;
import chordinnate.model.playback.InstrumentCapablePlayable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Staff extends InstrumentCapablePlayable {

    private final String staffName;

    protected FormPlayable playable;

    public Staff(String staffName, FormPlayable playable) {
        this.staffName = staffName;
        setPlayable(playable);
    }

    public void setPlayable(FormPlayable playable) {
        if (this.playable != null) {
            this.playable.setParent(null);
        }
        playable.setParent(this);
        this.playable = playable;
    }

    @Override
    public void accept(@NotNull MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
