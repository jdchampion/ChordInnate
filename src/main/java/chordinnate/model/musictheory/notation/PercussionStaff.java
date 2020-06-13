package chordinnate.model.musictheory.notation;

import chordinnate.config.MidiConfig;

import javax.sound.midi.Instrument;

public class PercussionStaff extends Staff {

    public PercussionStaff() {
        super(MidiConfig.PERCUSSION_CHANNEL);
    }

    public PercussionStaff(Instrument instrument, String staffName) {
        super(instrument, staffName, MidiConfig.PERCUSSION_CHANNEL);
    }

}
