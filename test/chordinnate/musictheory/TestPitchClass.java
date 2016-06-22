package chordinnate.musictheory;

import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.PitchClass.*;

/**
 * Created by Joseph on 4/15/16.
 */
public class TestPitchClass {
    @Test
    public void getEnharmonicSpelling() throws Exception {

    }

    @Test
    public void getBasePitch() throws Exception {

    }

    @Test
    public void hasAccidental() throws Exception {

    }

    @Test
    public void getIntervallicDistanceBetween() throws Exception {

    }

    @Test
    public void isDiatonicTo() throws Exception {
        // Test in order of flats: Bb Eb Ab Db Gb Cb Fb
        assertTrue(B_FLAT.isDiatonicTo(KeySignature.F_MAJOR));
        assertTrue(E_FLAT.isDiatonicTo(KeySignature.B_FLAT_MAJOR));
        assertTrue(A_FLAT.isDiatonicTo(KeySignature.E_FLAT_MAJOR));
        assertTrue(D_FLAT.isDiatonicTo(KeySignature.A_FLAT_MAJOR));
        assertTrue(G_FLAT.isDiatonicTo(KeySignature.D_FLAT_MAJOR));
        assertTrue(C_FLAT.isDiatonicTo(KeySignature.G_FLAT_MAJOR));
        assertTrue(F_FLAT.isDiatonicTo(KeySignature.C_FLAT_MAJOR));

        // Test in order of sharps: F# C# G# D# A# E# B#
        assertTrue(F_SHARP.isDiatonicTo(KeySignature.G_MAJOR));
        assertTrue(C_SHARP.isDiatonicTo(KeySignature.D_MAJOR));
        assertTrue(G_SHARP.isDiatonicTo(KeySignature.A_MAJOR));
        assertTrue(D_SHARP.isDiatonicTo(KeySignature.E_MAJOR));
        assertTrue(A_SHARP.isDiatonicTo(KeySignature.B_MAJOR));
        assertTrue(E_SHARP.isDiatonicTo(KeySignature.F_SHARP_MAJOR));
        assertTrue(B_SHARP.isDiatonicTo(KeySignature.C_SHARP_MAJOR));
    }

    @Test
    public void isEnharmonicTo() throws Exception {
        // White keys
        assertTrue(C.isEnharmonicTo(B_SHARP));
        assertTrue(C.isEnharmonicTo(D_DOUBLE_FLAT));
        assertTrue(D.isEnharmonicTo(C_DOUBLE_SHARP));
        assertTrue(D.isEnharmonicTo(E_DOUBLE_FLAT));
        assertTrue(E.isEnharmonicTo(D_DOUBLE_SHARP));
        assertTrue(E.isEnharmonicTo(F_FLAT));
        assertTrue(G.isEnharmonicTo(F_DOUBLE_SHARP));
        assertTrue(G.isEnharmonicTo(A_DOUBLE_FLAT));
        assertTrue(A.isEnharmonicTo(G_DOUBLE_SHARP));
        assertTrue(A.isEnharmonicTo(B_DOUBLE_FLAT));

        // Black keys

        // C# == Db
        assertTrue(C_SHARP.isEnharmonicTo(D_FLAT));
        assertTrue(D_FLAT.isEnharmonicTo(C_SHARP));

        // D# == Eb == Fbb
        assertTrue(D_SHARP.isEnharmonicTo(E_FLAT));
        assertTrue(D_SHARP.isEnharmonicTo(F_DOUBLE_FLAT));
        assertTrue(E_FLAT.isEnharmonicTo(D_SHARP));
        assertTrue(E_FLAT.isEnharmonicTo(F_DOUBLE_FLAT));
        assertTrue(F_DOUBLE_FLAT.isEnharmonicTo(D_SHARP));
        assertTrue(F_DOUBLE_FLAT.isEnharmonicTo(E_FLAT));

        // E# == F == Gbb
        assertTrue(E_SHARP.isEnharmonicTo(F));
        assertTrue(E_SHARP.isEnharmonicTo(G_DOUBLE_FLAT));
        assertTrue(F.isEnharmonicTo(E_SHARP));
        assertTrue(F.isEnharmonicTo(G_DOUBLE_FLAT));
        assertTrue(G_DOUBLE_FLAT.isEnharmonicTo(E_SHARP));
        assertTrue(G_DOUBLE_FLAT.isEnharmonicTo(F));

        // Ex == F# == Gb
        assertTrue(E_DOUBLE_SHARP.isEnharmonicTo(F_SHARP));
        assertTrue(E_DOUBLE_SHARP.isEnharmonicTo(G_FLAT));
        assertTrue(F_SHARP.isEnharmonicTo(E_DOUBLE_SHARP));
        assertTrue(F_SHARP.isEnharmonicTo(G_FLAT));
        assertTrue(G_FLAT.isEnharmonicTo(E_DOUBLE_SHARP));
        assertTrue(G_FLAT.isEnharmonicTo(F_SHARP));

        // G# == Ab
        assertTrue(G_SHARP.isEnharmonicTo(A_FLAT));
        assertTrue(A_FLAT.isEnharmonicTo(G_SHARP));

        // A# == Bb == Cbb
        assertTrue(A_SHARP.isEnharmonicTo(B_FLAT));
        assertTrue(A_SHARP.isEnharmonicTo(C_DOUBLE_FLAT));
        assertTrue(B_FLAT.isEnharmonicTo(A_SHARP));
        assertTrue(B_FLAT.isEnharmonicTo(C_DOUBLE_FLAT));
        assertTrue(C_DOUBLE_FLAT.isEnharmonicTo(A_SHARP));
        assertTrue(C_DOUBLE_FLAT.isEnharmonicTo(B_FLAT));
    }

}