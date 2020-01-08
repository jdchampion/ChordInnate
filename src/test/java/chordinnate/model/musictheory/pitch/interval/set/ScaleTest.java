package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.RomanNumeral;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static chordinnate.model.musictheory.pitch.PitchClass.*;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Joseph on 7/15/16.
 */
@Slf4j
public class ScaleTest {
    @Test
    public void sanityCheck() {
        // Basic arbitrary testing
        verifyScale(new Scale("C Major"), C, D, E, F, G, A, B);
        verifyScale(new Scale("F# Major"), F_SHARP, G_SHARP, A_SHARP, B, C_SHARP, D_SHARP, E_SHARP);
        verifyScale(new Scale("G Major"), G, A, B, C, D, E, F_SHARP);
        verifyScale(new Scale("B Major"), B, C_SHARP, D_SHARP, E, F_SHARP, G_SHARP, A_SHARP);
        verifyScale(new Scale("B# Major"), B_SHARP, C_DOUBLE_SHARP, D_DOUBLE_SHARP, E_SHARP, F_DOUBLE_SHARP, G_DOUBLE_SHARP, A_DOUBLE_SHARP);
        verifyScale(new Scale("C Harmonic Minor"), C, D, E_FLAT, F, G, A_FLAT, B);
        verifyScale(new Scale("Bb Harmonic Minor"), B_FLAT, C, D_FLAT, E_FLAT, F, G_FLAT, A);
        verifyScale(new Scale("C Blues"), C, E_FLAT, F, F_SHARP, G, B_FLAT);

        verifyScale(new Scale("C# Major"),
                PitchClass.withName("C#", false),
                PitchClass.withName("D#", false),
                PitchClass.withName("E#", false),
                PitchClass.withName("F#", false),
                PitchClass.withName("G#", false),
                PitchClass.withName("A#", false),
                PitchClass.withName("B#", false));

        verifyScale(new Scale("Cx# Major"),
                PitchClass.withName("Cx#", false),
                PitchClass.withName("Dx#", false),
                PitchClass.withName("Ex#", false),
                PitchClass.withName("Fx#", false),
                PitchClass.withName("Gx#", false),
                PitchClass.withName("Ax#", false),
                PitchClass.withName("Bx#", false));

        verifyScale(new Scale("Fbbx Blues"),
                PitchClass.withName("Fbbx", false),
                PitchClass.withName("Abbbx", false),
                PitchClass.withName("Bbbbx", false),
                PitchClass.withName("Bbbx", false),
                PitchClass.withName("Cbbx", false),
                PitchClass.withName("Ebbbx", false));
    }

    @Test
    public void testCaseInsensitiveConstruction() {
        verifyScale(new Scale("c major"), C, D, E, F, G, A, B);
    }

    @Test
    public void transposeToInterval() {
        Scale transposed = new Scale("C Major");
        transposed.transpose(IntervalDirection.UP, Interval.withShortName("M2"));
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transpose(IntervalDirection.DOWN, Interval.withShortName("M2"));
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void transposeToPitchClass() {
        Scale transposed = new Scale("C Major");
        transposed.transpose(IntervalDirection.UP, PitchClass.D);
        verifyScale(transposed, D, E, F_SHARP, G, A, B, C_SHARP);
        transposed.transpose(IntervalDirection.UP, PitchClass.C);
        verifyScale(transposed, C, D, E, F, G, A, B);
    }

    @Test
    public void isDiatonicToKeySignature() {
        Scale s = new Scale("C Major");

        assertTrue(s.isDiatonicTo(KeySignature.C_MAJOR));
        assertTrue(s.isDiatonicTo(KeySignature.A_MINOR));

        assertFalse(s.isDiatonicTo(KeySignature.D_MAJOR));
    }

    @Test
    public void isDiatonicToIntervalSet() {
        Scale s = new Scale("C Major");
        Chord c = new Chord("Cmaj");
        Scale aNaturalMinor = new Scale("A Melodic Minor descending");

        assertTrue(s.isDiatonicTo(s));
        assertFalse(s.isDiatonicTo(c));     // Scales are not diatonic to Chords that contain fewer Pitches than them
        assertTrue(s.isDiatonicTo(aNaturalMinor));
    }

    @Test
    public void romanNumeralAnalysis() {
        Scale s = new Scale("C Major");

        RomanNumeral[] expected = {
                RomanNumeral.MAJOR_ONE,
                RomanNumeral.MINOR_TWO,
                RomanNumeral.MINOR_THREE,
                RomanNumeral.MAJOR_FOUR,
                RomanNumeral.MAJOR_FIVE,
                RomanNumeral.MINOR_SIX,
                RomanNumeral.DIMINISHED_SEVEN
        };

        RomanNumeral[] actual = s.getRomanNumeralAnalysis();

        assertEquals("Roman Numeral Analysis length is not correct", expected.length, actual.length);

        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < actual.length; i++) {
            assertEquals("Incorrect Roman Numeral Analysis", expected[i], actual[i]);
            sj.add(actual[i].getSymbol());
        }
        log.info(s.getName() + ": " + sj.toString());
    }

    @Test
    public void getOrigin() {
        Scale withOrigin = new Scale("C Aeolian");
        assertNotNull(withOrigin.getOrigin());

        Scale withoutOrigin = new Scale("C Major");
        assertNotNull(withoutOrigin.getOrigin());
    }

    /**
     * Helper method for testing the returned Pitch values for a given Scale.
     * @param scale
     * @param expected
     */
    private static void verifyScale(Scale scale, PitchClass... expected) {

        assertEquals("Scale length for scale [" + scale.getName() + "] is not the expected length (bad SCALE_TYPE.INTERVALS column value?)", scale.getHorizontalSize(), expected.length);

        assertEquals("Scale grouping is incorrect", getExpectedGrouping(scale), scale.getGrouping());

        for (Map.Entry<Octave, Pitch[]> entry : scale.pitchesByOctave.entrySet()) {
            int lastAbsolutePitch = Integer.MIN_VALUE;
            Pitch[] pitches = entry.getValue();
            for (int i = 0; i < pitches.length; i++) {
                assertEquals("Octave " + entry.getKey().getNumber() + " for scale [" + scale.getName() + "] has the wrong values", expected[i].getBaseName(), pitches[i].pitchClass.getBaseName());
                assertTrue("Scale is not ascending", lastAbsolutePitch < pitches[i].absolutePitch);
                lastAbsolutePitch = pitches[i].absolutePitch;
            }
        }

        assertTrue(scale.getScaleType().getPreset());

        assertEquals(scale.lowestDiatonic.pitchClass.getName() + " " + scale.getScaleType().getName(), scale.getName());
    }

    private static String getExpectedGrouping(Scale scale) {
        switch (scale.getHorizontalSize()) {
            case 1: return "monochord";
            case 2: return "dichord";
            case 3: return "trichord";
            case 4: return "tetrachord";
            case 5: return "pentachord";
            case 6: return "hexachord";
            case 7: return "heptachord";
            case 8: return "octachord";
            case 9: return "enneachord";
            case 10: return "decachord";
            case 11: return "hendecachord";
            case 12: return "dodecachord";
            default: return "UNIT TEST OUT OF RANGE";
        }
    }
}