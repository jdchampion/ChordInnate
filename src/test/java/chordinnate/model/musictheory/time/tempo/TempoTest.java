package chordinnate.model.musictheory.time.tempo;

import chordinnate.model.musictheory.time.rhythm.Beat;
import chordinnate.model.musictheory.time.rhythm.Duration;
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

    @Test
    public void setTempoMarking() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 120);
        for (TempoMarking tm : TempoMarking.values()) {
            tempo.setTempoMarking(tm);

            assertEquals(tm, tempo.getTempoMarking());
            assertEquals(tm.minBpm, tempo.getCurrentBPM());
        }
    }

    @Test
    public void getMillisForBeat() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 120);
        Beat TRIPLET_EIGHTH = new Beat.Builder(Duration.EIGHTH).tuplet(3).build(),
                DOTTED_TRIPLET_QUARTER = new Beat.Builder(Duration.QUARTER).dots(1).tuplet(3).build();
        assertEquals(500, tempo.getMillisFor(Beat.QUARTER));
        assertEquals(250, tempo.getMillisFor(Beat.EIGHTH));
        assertEquals(125, tempo.getMillisFor(Beat.SIXTEENTH));
        assertEquals(62, tempo.getMillisFor(Beat.THIRTY_SECOND));
        assertEquals(166, tempo.getMillisFor(TRIPLET_EIGHTH));
        assertEquals(500, tempo.getMillisFor(DOTTED_TRIPLET_QUARTER));

        tempo = new Tempo(Beat.QUARTER, 240);
        assertEquals(250, tempo.getMillisFor(Beat.QUARTER));
        tempo = new Tempo(Beat.QUARTER, 60);
        assertEquals(1000, tempo.getMillisFor(Beat.QUARTER));
    }

}