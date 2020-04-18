package chordinnate.model.musictheory.temporal.tempo;

import chordinnate.model.musictheory.temporal.rhythm.Beat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 6/22/16.
 */
public class TempoTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalidBPM() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tempo must be between "
                + TempoMarking.slowest().minBpm + " and "
                + TempoMarking.fastest().maxBpm + " BPM.");
        new Tempo(Beat.QUARTER, 0);
        new Tempo(Beat.QUARTER, -1);
    }

}