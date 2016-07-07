package chordinnate.musictheory.time.tempo;

import chordinnate.musictheory.time.rhythm.Beat;
import chordinnate.musictheory.time.tempo.Tempo;
import chordinnate.musictheory.time.tempo.TempoMarking;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 6/22/16.
 */
public class TestTempo {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalidBPM() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tempo must be between "
                + TempoMarking.slowest().MIN_BPM + " and "
                + TempoMarking.fastest().MAX_BPM + " BPM.");
        new Tempo(Beat.QUARTER, 0);
        new Tempo(Beat.QUARTER, -1);
    }

    @Test
    public void allTempoMarkingChanges() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 120);
        for (TempoMarking tm : TempoMarking.values()) {
            tempo.setTempoMarking(tm);

            assertEquals(tm, tempo.getTempoMarking());
            assertEquals(tm.MIN_BPM, tempo.getCurrentBPM());
        }
    }

    @Test
    public void getMillis() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 120);
        assertEquals(500, tempo.getMillisFor(Beat.QUARTER));
        tempo = new Tempo(Beat.QUARTER, 240);
        assertEquals(250, tempo.getMillisFor(Beat.QUARTER));
        tempo = new Tempo(Beat.QUARTER, 60);
        assertEquals(1000, tempo.getMillisFor(Beat.QUARTER));
    }

}