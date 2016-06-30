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
    public void nullDuration() throws Exception {
        // TODO: maybe change this to IllegalArgumentException
        expectedException.expect(NullPointerException.class);
        Tempo tempo = new Tempo(null, DotValue.NONE, 120);
    }

    @Test
    public void nullDotValue() throws Exception {
        // TODO: maybe change this to IllegalArgumentException
        expectedException.expect(NullPointerException.class);
        Tempo tempo = new Tempo(Duration.QUARTER, null, 120);
    }

    @Test
    public void invalidBPM() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tempo must be between "
                + TempoMarking.slowest().getMinBPM() + " and "
                + TempoMarking.fastest().getMaxBPM() + " BPM.");
        Tempo tempo = new Tempo(Duration.QUARTER, DotValue.NONE, 0);
        tempo = new Tempo(Duration.QUARTER, DotValue.NONE, -1);
    }

    @Test
    public void allTempoMarkingChanges() throws Exception {
        Tempo tempo = new Tempo(Duration.QUARTER, DotValue.NONE, 120);
        for (TempoMarking tm : TempoMarking.values()) {
            tempo.setTempoMarking(tm);

            assertEquals(tm, tempo.getTempoMarking());
            assertEquals(tm.getMinBPM(), tempo.getCurrentBPM());
        }
    }

}