package chordinnate.musictheory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestAccidental {

    @Test
    public void getSymbol() throws Exception {
        assertEquals("\uD834\uDD2B", Accidental.DOUBLE_FLAT.getSymbol());
        assertEquals("\u266d", Accidental.FLAT.getSymbol());
        assertEquals("♮", Accidental.NATURAL.getSymbol());
        assertEquals("", Accidental.NONE.getSymbol());
        assertEquals("\u266f", Accidental.SHARP.getSymbol());
        assertEquals("\uD834\uDD2A", Accidental.DOUBLE_SHARP.getSymbol());
    }

    @Test
    public void getSemitoneModifier() throws Exception {
        assertEquals(-2, Accidental.DOUBLE_FLAT.getSemitoneModifier());
        assertEquals(-1, Accidental.FLAT.getSemitoneModifier());
        assertEquals(0, Accidental.NATURAL.getSemitoneModifier());
        assertEquals(0, Accidental.NONE.getSemitoneModifier());
        assertEquals(1, Accidental.SHARP.getSemitoneModifier());
        assertEquals(2, Accidental.DOUBLE_SHARP.getSemitoneModifier());
    }

    @Test
    public void getNext() throws Exception {
        assertNotNull(Accidental.DOUBLE_FLAT.getNext());
        assertNotNull(Accidental.FLAT.getNext());
        assertNotNull(Accidental.NATURAL.getNext());
        assertNotNull(Accidental.NONE.getNext());
        assertNotNull(Accidental.SHARP.getNext());
        assertNull(Accidental.DOUBLE_SHARP.getNext());
    }

    @Test
    public void getPrevious() throws Exception {
        assertNull(Accidental.DOUBLE_FLAT.getPrevious());
        assertNotNull(Accidental.FLAT.getPrevious());
        assertNotNull(Accidental.NATURAL.getPrevious());
        assertNotNull(Accidental.NONE.getPrevious());
        assertNotNull(Accidental.SHARP.getPrevious());
        assertNotNull(Accidental.DOUBLE_SHARP.getPrevious());
    }

}