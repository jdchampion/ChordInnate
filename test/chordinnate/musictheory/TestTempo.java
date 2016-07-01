package chordinnate.musictheory;

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
                + TempoMarking.slowest().getMinBPM() + " and "
                + TempoMarking.fastest().getMaxBPM() + " BPM.");
        new Tempo(Beat.QUARTER, 0);
        new Tempo(Beat.QUARTER, -1);
    }

    @Test
    public void allTempoMarkingChanges() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 120);
        for (TempoMarking tm : TempoMarking.values()) {
            tempo.setTempoMarking(tm);

            assertEquals(tm, tempo.getTempoMarking());
            assertEquals(tm.getMinBPM(), tempo.getCurrentBPM());
        }
    }

}