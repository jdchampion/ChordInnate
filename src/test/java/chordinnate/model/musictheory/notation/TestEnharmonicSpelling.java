package chordinnate.model.musictheory.notation;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEnharmonicSpelling {
    @Test
    public void testWithName() throws Exception {
        assertEquals(EnharmonicSpelling.C, EnharmonicSpelling.withName("C", false));
        assertNotEquals(EnharmonicSpelling.C, EnharmonicSpelling.withName("C", true));

        assertNotEquals(EnharmonicSpelling.C_NATURAL, EnharmonicSpelling.withName("C", false));
        assertEquals(EnharmonicSpelling.C_NATURAL, EnharmonicSpelling.withName("C", true));

        assertEquals(EnharmonicSpelling.C_DOUBLE_FLAT, EnharmonicSpelling.withName("Cbb", false));
        assertEquals(EnharmonicSpelling.C_DOUBLE_FLAT, EnharmonicSpelling.withName("Cbb", true));

        assertEquals(EnharmonicSpelling.C, EnharmonicSpelling.withName("Cxbb#b", false));
        assertNotEquals(EnharmonicSpelling.C, EnharmonicSpelling.withName("Cxbb#b", true));
    }

    @Test
    public void testApply() throws Exception {
        EnharmonicSpelling cFlat = EnharmonicSpelling.C_FLAT;
        EnharmonicSpelling c = EnharmonicSpelling.C;
        EnharmonicSpelling test = c.apply(Accidental.FLAT, true, false);
        assertEquals(cFlat, test);
    }

    @Test
    public void testIsEnharmonicTo() throws Exception {
        EnharmonicSpelling expected = EnharmonicSpelling.C;

        EnharmonicSpelling test1 = EnharmonicSpelling.withName("C", false);
        EnharmonicSpelling test2 = EnharmonicSpelling.withName("C", true);
        EnharmonicSpelling test3 = EnharmonicSpelling.withName("Cxbb#b", false);
        EnharmonicSpelling test4 = EnharmonicSpelling.withName("Cxbb#b", true);
        assertTrue(test1.isEnharmonicTo(expected));
        assertTrue(test2.isEnharmonicTo(expected));
        assertTrue(test3.isEnharmonicTo(expected));
        assertTrue(test4.isEnharmonicTo(expected));

        EnharmonicSpelling test5 = EnharmonicSpelling.withName("C#", false);
        EnharmonicSpelling test6 = EnharmonicSpelling.withName("C#", true);
        EnharmonicSpelling test7 = EnharmonicSpelling.withName("D", false);
        EnharmonicSpelling test8 = EnharmonicSpelling.withName("D", true);
        assertFalse(test5.isEnharmonicTo(expected));
        assertFalse(test6.isEnharmonicTo(expected));
        assertFalse(test7.isEnharmonicTo(expected));
        assertFalse(test8.isEnharmonicTo(expected));

    }
}
