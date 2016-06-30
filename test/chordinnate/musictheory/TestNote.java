package chordinnate.musictheory;

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

    @Test
    public void unsupportedNoteDuration() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        Note note = new Note
                .Builder(Pitch.C_4, Duration.SIXTY_FOURTH)
                .dotValue(DotValue.ONE)
                .build();                                           // 64th note: no dots
        note = new Note
                .Builder(Pitch.C_4, Duration.THIRTY_SECOND)
                .dotValue(DotValue.TWO)
                .build();                                           // 32nd note: 1 or fewer dots
        note = new Note
                .Builder(Pitch.C_4, Duration.SIXTEENTH)
                .dotValue(DotValue.THREE)
                .build();                                           // 16th note: 2 or fewer dots
        note = new Note
                .Builder(Pitch.C_4, Duration.EIGHTH)
                .dotValue(DotValue.FOUR)
                .build();                                           // 8th note: 3 or fewer dots
        note = new Note
                .Builder(Pitch.C_4, Duration.QUARTER)
                .dotValue(DotValue.FIVE)
                .build();                                           // Quarter note: 4 or fewer dots
        note = new Note
                .Builder(Pitch.C_4, Duration.HALF)
                .dotValue(DotValue.SIX)
                .build();                                           // Half note: 5 or fewer dots
        note = new Note
                .Builder(Pitch.C_4, Duration.WHOLE)
                .dotValue(DotValue.SEVEN)
                .build();                                           // Whole note: 6 or fewer dots
    }
}