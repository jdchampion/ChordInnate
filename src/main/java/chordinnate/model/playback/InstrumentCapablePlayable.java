package chordinnate.model.playback;

import chordinnate.midi.MidiOutputRouter;
import chordinnate.util.ContextProvider;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Instrument;

@Slf4j
public abstract class InstrumentCapablePlayable implements Playable {

    private static final MidiOutputRouter ROUTER = ContextProvider.getContext().getBean(MidiOutputRouter.class);

    public void setInstrument(Instrument instrument) {
        ROUTER.registerProgramChange(this, instrument);
    }
}
