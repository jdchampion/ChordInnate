package chordinnate.model.playback;

import javax.sound.midi.Instrument;
import java.util.Set;

/**
 * Marker interface indicating that the implementing component
 * is both a {@link Playable} object and also a musical staff component.
 */
public interface StaffPlayable extends Playable {
    Set<Instrument> getAllInstruments();
}
