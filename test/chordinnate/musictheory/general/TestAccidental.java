package chordinnate.musictheory.general;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestAccidental {

    @Test
    public void getSymbol() throws Exception {
        assertEquals("\uD834\uDD2B", Accidental.DOUBLE_FLAT.SYMBOL);
        assertEquals("♭", Accidental.FLAT.SYMBOL);
        assertEquals("♮", Accidental.NATURAL.SYMBOL);
        assertEquals("", Accidental.NONE.SYMBOL);
        assertEquals("♯", Accidental.SHARP.SYMBOL);
        assertEquals("\uD834\uDD2A", Accidental.DOUBLE_SHARP.SYMBOL);
    }

    @Test
    public void getSemitoneModifier() throws Exception {
        assertEquals(-2, Accidental.DOUBLE_FLAT.SEMITONE_MODIFIER);
        assertEquals(-1, Accidental.FLAT.SEMITONE_MODIFIER);
        assertEquals(0, Accidental.NATURAL.SEMITONE_MODIFIER);
        assertEquals(0, Accidental.NONE.SEMITONE_MODIFIER);
        assertEquals(1, Accidental.SHARP.SEMITONE_MODIFIER);
        assertEquals(2, Accidental.DOUBLE_SHARP.SEMITONE_MODIFIER);
    }

}