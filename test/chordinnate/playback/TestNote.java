package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.time.rhythm.Beat;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * Created by Joseph on 6/16/16.
 */
public class TestNote {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    // TODO: add tests
    @Test
    public void getSoundedLength() throws Exception {
        Note note = new Note(Pitch.C_4, Beat.QUARTER);
        assertEquals(Beat.QUARTER.getRatio(), note.getSoundedLength(), 0);

        for (Beat beat : Beat.values()) {
            for (Articulation articulation : Articulation.values()) {
                note = new Note.Builder(Pitch.C_4, beat).articulation(articulation).build();
                assertEquals(beat.getRatio() * articulation.LENGTH_MODIFIER, note.getSoundedLength(), 0);
            }
        }
    }

}