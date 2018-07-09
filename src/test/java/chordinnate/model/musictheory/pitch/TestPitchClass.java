package chordinnate.model.musictheory.pitch;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static org.junit.Assert.*;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class TestPitchClass {

    @Test
    public void testWithName() throws Exception {

        String[] letters = {"A", "B", "C", "D", "E", "F", "G"};

        for (String letter : letters) {
            // Basic cases: standard accidentals
            testGeneratedPitchClass(letter + "bb", true);
            testGeneratedPitchClass(letter + "b", true);
            testGeneratedPitchClass(letter + "", true);
            testGeneratedPitchClass(letter + "", false);
            testGeneratedPitchClass(letter + "#", true);
            testGeneratedPitchClass(letter + "x", true);

            // Edge cases: non-standard accidentals
            testGeneratedPitchClass(letter + "b#", false);
            testGeneratedPitchClass(letter + "b#", true);
            testGeneratedPitchClass(letter + "b#xxb#bb#", false);
            testGeneratedPitchClass(letter + "b#xxb#bb#", true);
        }

    }

    @Test
    public void getSemitoneDistanceBetween() throws Exception {
        // Edge cases: 0 -> 11, 11 -> 0
        assertEquals(11, PitchClass.getSemitoneDistanceBetween(C, B));
        assertEquals(1, PitchClass.getSemitoneDistanceBetween(B, C));
        assertEquals(1, PitchClass.getSemitoneDistanceBetween(C, C_SHARP));
        assertEquals(11, PitchClass.getSemitoneDistanceBetween(C, C_FLAT));

        // Arbitrary test for single semitone distance
        assertEquals(1, PitchClass.getSemitoneDistanceBetween(C_FLAT, C));
        assertEquals(1, PitchClass.getSemitoneDistanceBetween(C, C_SHARP));
    }


    @Test
    public void testTransposeInterval() throws Exception {
        Accidental a = Accidental.FLAT;
        Interval i = Interval.d8;
        boolean direction = true;
        String[] prefixes = {"C", "D", "E", "F", "G", "A", "B"};
        StringJoiner sj = new StringJoiner(", ");
        for (String s : prefixes) {
            PitchClass p = PitchClass.withName(s + a.UTF8_SYMBOL, a.equals(Accidental.NATURAL))
                    .transpose(direction, i);
            sj.add(p.getName());
        }

        System.out.println(sj.toString());
    }

    @Test
    public void testIsDiatonic() throws Exception {
        PitchClass pitchClass = C;
        assertTrue(pitchClass.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(pitchClass.isDiatonicTo(KeySignature.A_MINOR));
        assertTrue(pitchClass.isDiatonicTo(new Scale("C Major")));
        assertTrue(pitchClass.isDiatonicTo(new Scale("A Harmonic Minor")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("Cmaj")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("Am")));
        pitchClass = C_FLAT;
        assertTrue(pitchClass.isDiatonicTo(KeySignature.C_FLAT_MAJOR));
        assertTrue(pitchClass.isDiatonicTo(KeySignature.A_FLAT_MINOR));
        assertTrue(pitchClass.isDiatonicTo(new Scale("Cb Major")));
        assertTrue(pitchClass.isDiatonicTo(new Scale("Ab Harmonic Minor")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("Cbmaj")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("Abm")));
        pitchClass = B_SHARP;
        assertTrue(pitchClass.isDiatonicTo(KeySignature.B_SHARP_MAJOR));
        assertTrue(pitchClass.isDiatonicTo(KeySignature.G_DOUBLE_SHARP_MINOR));
        assertTrue(pitchClass.isDiatonicTo(new Scale("B# Major")));
        assertTrue(pitchClass.isDiatonicTo(new Scale("Gx Harmonic Minor")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("B#maj")));
        assertTrue(pitchClass.isDiatonicTo(new Chord("Gxm")));

        pitchClass = PitchClass.withName("C#b", false);
        assertFalse(pitchClass.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(pitchClass.isDiatonicTo(KeySignature.withName("C#b Major")));
    }

    @Test
    public void isDiatonicToKeySignature() throws Exception {
        for (PitchClass p : STANDARD_PITCH_CLASSES.values()) assertTrue(p.isDiatonicTo(KeySignature.NO_KEY_SIGNATURE));

        PitchClass[]
                cMajor = {C, D, E, F, G, A, B},

                fMajor = {F, G, A, B_FLAT, C, D, E},
                bFlatMajor = {B_FLAT, C, D, E_FLAT, F, G, A},
                aFlatMajor = {A_FLAT, B_FLAT, C, D_FLAT, E_FLAT, F, G},
                dFlatMajor = {D_FLAT, E_FLAT, F, G_FLAT, A_FLAT, B_FLAT, C},
                gFlatMajor = {G_FLAT, A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F},
                cFlatMajor = {C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT, A_FLAT, B_FLAT},
                bDoubleFlatMajor = {B_DOUBLE_FLAT, C_FLAT, D_FLAT, E_DOUBLE_FLAT, F_FLAT, G_FLAT, A_FLAT},
                eDoubleFlatMajor = {E_DOUBLE_FLAT, F_FLAT, G_FLAT, A_DOUBLE_FLAT, B_DOUBLE_FLAT, C_FLAT, D_FLAT},
                aDoubleFlatMajor = {A_DOUBLE_FLAT, B_DOUBLE_FLAT, C_FLAT, D_DOUBLE_FLAT, E_DOUBLE_FLAT, F_FLAT, G_FLAT},

                gMajor = {G, A, B, C, D, E, F_SHARP},
                dMajor = {D, E, F_SHARP, G, A, B, C_SHARP},
                aMajor = {A, B, C_SHARP, D, E, F_SHARP, G_SHARP},
                eMajor = {E, F_SHARP, G_SHARP, A, B, C_SHARP, D_SHARP},
                bMajor = {B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP},
                fSharpMajor = {F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP},
                cSharpMajor = {C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP, A_SHARP, B_SHARP},
                gSharpMajor = {G_SHARP, A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_DOUBLE_SHARP},
                dSharpMajor = {D_SHARP, E_SHARP, F_DOUBLE_SHARP, G_SHARP, A_SHARP, B_SHARP, C_DOUBLE_SHARP},
                aSharpMajor = {A_SHARP, B_SHARP, C_DOUBLE_SHARP, D_SHARP, E_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP},
                eSharpMajor = {E_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP, A_SHARP, B_SHARP, C_DOUBLE_SHARP, D_DOUBLE_SHARP},
                bSharpMajor = {B_SHARP, C_DOUBLE_SHARP, D_DOUBLE_SHARP, E_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP, A_DOUBLE_SHARP},

                aMinor = {A, B, C, D, E, F, G},

                dMinor = {D, E, F, G, A, B_FLAT, C},
                gMinor = {G, A, B_FLAT, C, D, E_FLAT, F},
                cMinor = {C, D, E_FLAT, F, G, A_FLAT, B_FLAT},
                fMinor = {F, G, A_FLAT, B_FLAT, C, D_FLAT, E_FLAT},
                bFlatMinor = {B_FLAT, C, D_FLAT, E_FLAT, F, G_FLAT, A_FLAT},
                eFlatMinor = {E_FLAT, F, G_FLAT, A_FLAT, B_FLAT, C_FLAT, D_FLAT},
                aFlatMinor = {A_FLAT, B_FLAT, C_FLAT, D_FLAT, E_FLAT, F_FLAT, G_FLAT},
                dFlatMinor = {D_FLAT, E_FLAT, F_FLAT, G_FLAT, A_FLAT, B_DOUBLE_FLAT, C_FLAT},
                gFlatMinor = {G_FLAT, A_FLAT, B_DOUBLE_FLAT, C_FLAT, D_FLAT, E_DOUBLE_FLAT, F_FLAT},
                cFlatMinor = {C_FLAT, D_FLAT, E_DOUBLE_FLAT, F_FLAT, G_FLAT, A_DOUBLE_FLAT, B_DOUBLE_FLAT},
                fFlatMinor = {F_FLAT, G_FLAT, A_DOUBLE_FLAT, B_DOUBLE_FLAT, C_FLAT, D_DOUBLE_FLAT, E_DOUBLE_FLAT},

                eMinor = {E, F_SHARP, G, A, B, C, D},
                bMinor = {B, C_SHARP, D, E, F_SHARP, G, A},
                fSharpMinor = {F_SHARP, G_SHARP, A, B, C_SHARP, D, E},
                cSharpMinor = {C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A, B},
                gSharpMinor = {G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E, F_SHARP},
                dSharpMinor = {D_SHARP, E_SHARP, F_SHARP, G_SHARP, A_SHARP, B, C_SHARP},
                aSharpMinor = {A_SHARP, B_SHARP, C_SHARP, D_SHARP, E_SHARP, F_SHARP, G_SHARP},
                eSharpMinor = {E_SHARP, F_DOUBLE_SHARP, G_SHARP, A_SHARP, B_SHARP, C_SHARP, D_SHARP},
                bSharpMinor = {B_SHARP, C_DOUBLE_SHARP, D_SHARP, E_SHARP, F_DOUBLE_SHARP, G_SHARP, A_SHARP},
                fDoubleSharpMinor = {F_DOUBLE_SHARP, G_DOUBLE_SHARP, A_SHARP, B_SHARP, C_DOUBLE_SHARP, D_SHARP, E_SHARP},
                cDoubleSharpMinor = {C_DOUBLE_SHARP, D_DOUBLE_SHARP, E_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP, A_SHARP, B_SHARP},
                gDoubleSharpMinor = {G_DOUBLE_SHARP, A_DOUBLE_SHARP, B_SHARP, C_DOUBLE_SHARP, D_DOUBLE_SHARP, E_SHARP, F_DOUBLE_SHARP};

        for (PitchClass p : cMajor) assertTrue(p.isDiatonicTo(KeySignature.C_MAJOR));

        for (PitchClass p : fMajor) assertTrue(p.isDiatonicTo(KeySignature.F_MAJOR));
        for (PitchClass p : bFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.B_FLAT_MAJOR));
        for (PitchClass p : aFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.A_FLAT_MAJOR));
        for (PitchClass p : dFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.D_FLAT_MAJOR));
        for (PitchClass p : gFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.G_FLAT_MAJOR));
        for (PitchClass p : cFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.C_FLAT_MAJOR));
        for (PitchClass p : bDoubleFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.B_DOUBLE_FLAT_MAJOR));
        for (PitchClass p : eDoubleFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.E_DOUBLE_FLAT_MAJOR));
        for (PitchClass p : aDoubleFlatMajor) assertTrue(p.isDiatonicTo(KeySignature.A_DOUBLE_FLAT_MAJOR));

        for (PitchClass p : gMajor) assertTrue(p.isDiatonicTo(KeySignature.G_MAJOR));
        for (PitchClass p : dMajor) assertTrue(p.isDiatonicTo(KeySignature.D_MAJOR));
        for (PitchClass p : aMajor) assertTrue(p.isDiatonicTo(KeySignature.A_MAJOR));
        for (PitchClass p : eMajor) assertTrue(p.isDiatonicTo(KeySignature.E_MAJOR));
        for (PitchClass p : bMajor) assertTrue(p.isDiatonicTo(KeySignature.B_MAJOR));
        for (PitchClass p : fSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.F_SHARP_MAJOR));
        for (PitchClass p : cSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.C_SHARP_MAJOR));
        for (PitchClass p : gSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.G_SHARP_MAJOR));
        for (PitchClass p : dSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.D_SHARP_MAJOR));
        for (PitchClass p : aSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.A_SHARP_MAJOR));
        for (PitchClass p : eSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.E_SHARP_MAJOR));
        for (PitchClass p : bSharpMajor) assertTrue(p.isDiatonicTo(KeySignature.B_SHARP_MAJOR));

        for (PitchClass p : aMinor) assertTrue(p.isDiatonicTo(KeySignature.A_MINOR));

        for (PitchClass p : dMinor) assertTrue(p.isDiatonicTo(KeySignature.D_MINOR));
        for (PitchClass p : gMinor) assertTrue(p.isDiatonicTo(KeySignature.G_MINOR));
        for (PitchClass p : cMinor) assertTrue(p.isDiatonicTo(KeySignature.C_MINOR));
        for (PitchClass p : fMinor) assertTrue(p.isDiatonicTo(KeySignature.F_MINOR));
        for (PitchClass p : bFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.B_FLAT_MINOR));
        for (PitchClass p : eFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.E_FLAT_MINOR));
        for (PitchClass p : aFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.A_FLAT_MINOR));
        for (PitchClass p : dFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.D_FLAT_MINOR));
        for (PitchClass p : gFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.G_FLAT_MINOR));
        for (PitchClass p : cFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.C_FLAT_MINOR));
        for (PitchClass p : fFlatMinor) assertTrue(p.isDiatonicTo(KeySignature.F_FLAT_MINOR));

        for (PitchClass p : eMinor) assertTrue(p.isDiatonicTo(KeySignature.E_MINOR));
        for (PitchClass p : bMinor) assertTrue(p.isDiatonicTo(KeySignature.B_MINOR));
        for (PitchClass p : fSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.F_SHARP_MINOR));
        for (PitchClass p : cSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.C_SHARP_MINOR));
        for (PitchClass p : gSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.G_SHARP_MINOR));
        for (PitchClass p : dSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.D_SHARP_MINOR));
        for (PitchClass p : aSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.A_SHARP_MINOR));

        for (PitchClass p : eSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.E_SHARP_MINOR));
        for (PitchClass p : bSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.B_SHARP_MINOR));
        for (PitchClass p : fDoubleSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.F_DOUBLE_SHARP_MINOR));
        for (PitchClass p : cDoubleSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.C_DOUBLE_SHARP_MINOR));
        for (PitchClass p : gDoubleSharpMinor) assertTrue(p.isDiatonicTo(KeySignature.G_DOUBLE_SHARP_MINOR));
    }

    @Test
    public void isDiatonicToIntervalSet() throws Exception {
        Scale cMajor = new Scale("C Major"),
                dMajor = new Scale("D Major");

        Chord cMaj = new Chord("Cmaj"),
                dMaj = new Chord("Dmaj");

        assertTrue(C.isDiatonicTo(cMajor));
        assertTrue(C.isDiatonicTo(cMaj));
        assertFalse(C.isDiatonicTo(dMajor));
        assertFalse(C.isDiatonicTo(dMaj));
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

    private void testGeneratedPitchClass(String s, boolean wantNaturalSymbol) throws Exception {

        boolean directTest = true;
        PitchClass toTest = PitchClass.withName(s, wantNaturalSymbol);
        String accidentals = s.length() > 1 ? s.substring(1) : "";
        String classPrefix = String.valueOf(s.charAt(0));
        String classSuffix;
        if ("bb".equals(accidentals)) {
            classSuffix = "_DOUBLE_FLAT";
        } else if ("b".equals(accidentals)) {
            classSuffix = "_FLAT";
        } else if ("#".equals(accidentals)) {
            classSuffix = "_SHARP";
        } else if ("x".equals(accidentals)) {
            classSuffix = "_DOUBLE_SHARP";
        } else if (accidentals.isEmpty()) {
            classSuffix = wantNaturalSymbol ? "_NATURAL" : "";
        } else {
            classSuffix = accidentals;
            directTest = false;
        }

        PitchClass toCompare;
        if (directTest) {
            Field field = PitchClass.class.getDeclaredField(classPrefix + classSuffix);
            toCompare = (PitchClass) field.get(PitchClass.class);
            assertEquals(toTest, toCompare);
        } else {
            /*
             * PitchClasses with non-standard accidentals will always be dynamically created,
             * so toTest and toCompare will be two separate objects in memory.
             * We'll have to check each of the pertinent values in order to verify equality.
             */
            toCompare = PitchClass.withName(classPrefix + classSuffix, wantNaturalSymbol);
            assertEquals(toTest.ALIAS_ACCIDENTALS, toCompare.ALIAS_ACCIDENTALS);
            assertEquals(toTest.ALIAS_BASE_MIDI_VALUE, toCompare.ALIAS_BASE_MIDI_VALUE);
            assertEquals(toTest.ALIAS_LETTER, toCompare.ALIAS_LETTER);
            assertEquals(toTest.BASE_PITCH_CLASS, toCompare.BASE_PITCH_CLASS);
        }

    }
}