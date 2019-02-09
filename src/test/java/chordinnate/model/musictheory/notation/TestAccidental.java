package chordinnate.model.musictheory.notation;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAccidental {

    @Test
    public void matchesSymbol() throws Exception {
        for (Accidental accidental : Accidental.values()) {
            assertTrue(accidental.matchesSymbol(accidental.symbol));
            assertTrue(accidental.matchesSymbol(accidental.utf8Symbol));
        }
    }

    @Test
    public void simplify() throws Exception {
        String acc = "";

        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "_";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "________";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "b";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "b_";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "_b";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "b______";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "______b";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "___b___";
        assertEquals("b", Accidental.simplify(acc, false, true));
        assertEquals("b", Accidental.simplify(acc, true, true));

        acc = "#";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "#_";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "_#";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "#______";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "______#";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "___#___";
        assertEquals("#", Accidental.simplify(acc, false, true));
        assertEquals("#", Accidental.simplify(acc, true, true));

        acc = "bb";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "bb_";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "_bb";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "bb______";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "______bb";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "b______b";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "__bb____";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "___b__b_";
        assertEquals("bb", Accidental.simplify(acc, false, true));
        assertEquals("bb", Accidental.simplify(acc, true, true));

        acc = "x";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "x_";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "_x";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "x______";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "______x";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "___x___";
        assertEquals("x", Accidental.simplify(acc, false, true));
        assertEquals("x", Accidental.simplify(acc, true, true));

        acc = "bbb";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "bbb_";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "_bbb";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "bbb______";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "______bbb";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "b___b___b";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "__bbb____";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "___b__bb_";
        assertEquals("bbb", Accidental.simplify(acc, false, true));
        assertEquals("bbb", Accidental.simplify(acc, true, true));

        acc = "b#";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "b#_";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "_b#";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "b_#";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "#b";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "#b_";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "_#b";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "#_b";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "xbb";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "bxb";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

        acc = "bbx";
        assertEquals("", Accidental.simplify(acc, false, true));
        assertEquals("_", Accidental.simplify(acc, true, true));

    }
}
