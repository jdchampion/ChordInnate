package chordinnate.model.musictheory.pitch.key;

import chordinnate.model.musictheory.pitch.PitchClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.model.musictheory.pitch.key.KeySignature.*;

/**
 * Created by Joseph on 4/14/16.
 */
public class TestKeySignature {
    @Test
    public void testfield_SIGNATURE() throws Exception {
        KeySignature dMinor = KeySignature.D_MINOR;
        Object[] actual = dMinor.getSignature().toArray();
        PitchClass[] expected = {PitchClass.B_FLAT};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getRelativeKey() throws Exception {
        assertEquals(B_SHARP_MAJOR, G_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(C_MAJOR, A_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Dbb Major"), KeySignature.withName("Bbb Minor").getRelativeKey());
        assertEquals(KeySignature.withName("D#bbb Major"), KeySignature.withName("B#bbb Minor").getRelativeKey());

        assertEquals(E_SHARP_MAJOR, C_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(F_MAJOR, D_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Gbb Major"), KeySignature.withName("Ebb Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Gbxbb Major"), KeySignature.withName("Ebxbb Minor").getRelativeKey());

        assertEquals(A_SHARP_MAJOR, F_DOUBLE_SHARP_MINOR.getRelativeKey());
        assertEquals(B_FLAT_MAJOR, G_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Cbb Major"), KeySignature.withName("Abb Minor").getRelativeKey());
        assertEquals(KeySignature.withName("C#b#bbb Major"), KeySignature.withName("A#b#bbb Minor").getRelativeKey());

        assertEquals(D_SHARP_MAJOR, B_SHARP_MINOR.getRelativeKey());
        assertEquals(E_FLAT_MAJOR, C_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Fbb Major"), KeySignature.withName("Dbb Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Fbb#b Major"), KeySignature.withName("Dbb#b Minor").getRelativeKey());

        assertEquals(G_SHARP_MAJOR, E_SHARP_MINOR.getRelativeKey());
        assertEquals(A_FLAT_MAJOR, F_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("G#b# Major"), KeySignature.withName("E#b# Minor").getRelativeKey());
        assertEquals(KeySignature.withName("A#bb Major"), KeySignature.withName("F#b Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Bx Major"), KeySignature.withName("Gx# Minor").getRelativeKey());
        assertEquals(C_SHARP_MAJOR, A_SHARP_MINOR.getRelativeKey());
        assertEquals(D_FLAT_MAJOR, B_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("C#b# Major"), KeySignature.withName("A#b# Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Dbb# Major"), KeySignature.withName("Bbb# Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Ex Major"), KeySignature.withName("Cx# Minor").getRelativeKey());
        assertEquals(F_SHARP_MAJOR, D_SHARP_MINOR.getRelativeKey());
        assertEquals(G_FLAT_MAJOR, E_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Fbx Major"), KeySignature.withName("Dbx Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Gbxbb Major"), KeySignature.withName("Ebxbb Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Ax Major"), KeySignature.withName("Fx# Minor").getRelativeKey());
        assertEquals(B_MAJOR, G_SHARP_MINOR.getRelativeKey());
        assertEquals(C_FLAT_MAJOR, A_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Bbxb Major"), KeySignature.withName("Gbx Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Cbxbb Major"), KeySignature.withName("Abxbb Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Dx Major"), KeySignature.withName("Bx Minor").getRelativeKey());
        assertEquals(E_MAJOR, C_SHARP_MINOR.getRelativeKey());
        assertEquals(F_FLAT_MAJOR, D_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Ebxb Major"), KeySignature.withName("Cbx Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Fbxbb Major"), KeySignature.withName("Dbxbb Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Gx Major"), KeySignature.withName("Ex Minor").getRelativeKey());
        assertEquals(A_MAJOR, F_SHARP_MINOR.getRelativeKey());
        assertEquals(B_DOUBLE_FLAT_MAJOR, G_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Abxb Major"), KeySignature.withName("Fbx Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Bbb#b Major"), KeySignature.withName("Gbb# Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Cx Major"), KeySignature.withName("Ax Minor").getRelativeKey());
        assertEquals(D_MAJOR, B_MINOR.getRelativeKey());
        assertEquals(E_DOUBLE_FLAT_MAJOR, C_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("Dxbb Major"), KeySignature.withName("Bxbb Minor").getRelativeKey());
        assertEquals(KeySignature.withName("Ebb#b Major"), KeySignature.withName("Cbb# Minor").getRelativeKey());

        assertEquals(KeySignature.withName("Fx Major"), KeySignature.withName("Dx Minor").getRelativeKey());
        assertEquals(G_MAJOR, E_MINOR.getRelativeKey());
        assertEquals(A_DOUBLE_FLAT_MAJOR, F_FLAT_MINOR.getRelativeKey());
        assertEquals(KeySignature.withName("F#bx Major"), KeySignature.withName("D#bx Minor").getRelativeKey());





        assertEquals(B_SHARP_MINOR, D_SHARP_MAJOR.getRelativeKey());
        assertEquals(C_MINOR, E_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Dbb Minor"), KeySignature.withName("Fbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Cbb## Minor"), KeySignature.withName("Ebb# Major").getRelativeKey());
        assertEquals(KeySignature.withName("D#bbb Minor"), KeySignature.withName("F#bbb Major").getRelativeKey());

        assertEquals(E_SHARP_MINOR, G_SHARP_MAJOR.getRelativeKey());
        assertEquals(F_MINOR, A_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Gbb Minor"), KeySignature.withName("Bbbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Fbb## Minor"), KeySignature.withName("Abb# Major").getRelativeKey());
        assertEquals(KeySignature.withName("G#bbbb# Minor"), KeySignature.withName("B#bbbb Major").getRelativeKey());

        assertEquals(A_SHARP_MINOR, C_SHARP_MAJOR.getRelativeKey());
        assertEquals(B_FLAT_MINOR, D_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Cbb Minor"), KeySignature.withName("Ebbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Bbb# Minor"), KeySignature.withName("Dbb# Major").getRelativeKey());
        assertEquals(KeySignature.withName("C#bbbb# Minor"), KeySignature.withName("E#bbbb Major").getRelativeKey());

        assertEquals(D_SHARP_MINOR, F_SHARP_MAJOR.getRelativeKey());
        assertEquals(E_FLAT_MINOR, G_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Fbb Minor"), KeySignature.withName("Abbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Eb#b Minor"), KeySignature.withName("Gb#b Major").getRelativeKey());
        assertEquals(KeySignature.withName("Fbbbxbb# Minor"), KeySignature.withName("Abbbxbb Major").getRelativeKey());

        assertEquals(G_SHARP_MINOR, B_MAJOR.getRelativeKey());
        assertEquals(A_FLAT_MINOR, C_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("G#b# Minor"), KeySignature.withName("B#b Major").getRelativeKey());
        assertEquals(KeySignature.withName("Ab#b Minor"), KeySignature.withName("Cb#b Major").getRelativeKey());

        assertEquals(KeySignature.withName("Bx Minor"), KeySignature.withName("Dx Major").getRelativeKey());
        assertEquals(C_SHARP_MINOR, E_MAJOR.getRelativeKey());
        assertEquals(D_FLAT_MINOR, F_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Cb## Minor"), KeySignature.withName("Eb# Major").getRelativeKey());
        assertEquals(KeySignature.withName("D#bb Minor"), KeySignature.withName("F#bb Major").getRelativeKey());

        assertEquals(KeySignature.withName("Ex Minor"), KeySignature.withName("Gx Major").getRelativeKey());
        assertEquals(F_SHARP_MINOR, A_MAJOR.getRelativeKey());
        assertEquals(G_FLAT_MINOR, B_DOUBLE_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Fb## Minor"), KeySignature.withName("Ab# Major").getRelativeKey());
        assertEquals(KeySignature.withName("Gb#bb# Minor"), KeySignature.withName("Bb#bb Major").getRelativeKey());

        assertEquals(KeySignature.withName("Ax Minor"), KeySignature.withName("Cx Major").getRelativeKey());
        assertEquals(B_MINOR, D_MAJOR.getRelativeKey());
        assertEquals(C_FLAT_MINOR, E_DOUBLE_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Bbbx Minor"), KeySignature.withName("Dbbx Major").getRelativeKey());
        assertEquals(KeySignature.withName("Cb#bb# Minor"), KeySignature.withName("Eb#bb Major").getRelativeKey());

        assertEquals(KeySignature.withName("Dx Minor"), KeySignature.withName("Fx Major").getRelativeKey());
        assertEquals(E_MINOR, G_MAJOR.getRelativeKey());
        assertEquals(F_FLAT_MINOR, A_DOUBLE_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Ebb## Minor"), KeySignature.withName("Gbb## Major").getRelativeKey());
        assertEquals(KeySignature.withName("Fbb#b# Minor"), KeySignature.withName("Abb#b Major").getRelativeKey());

        assertEquals(G_DOUBLE_SHARP_MINOR, B_SHARP_MAJOR.getRelativeKey());
        assertEquals(A_MINOR, C_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Bbb Minor"), KeySignature.withName("Dbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Abxbb# Minor"), KeySignature.withName("Cbxbb# Major").getRelativeKey());
        assertEquals(KeySignature.withName("Bbbb# Minor"), KeySignature.withName("Dbbb# Major").getRelativeKey());

        assertEquals(C_DOUBLE_SHARP_MINOR, E_SHARP_MAJOR.getRelativeKey());
        assertEquals(D_MINOR, F_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Ex Minor"), KeySignature.withName("Gx Major").getRelativeKey());
        assertEquals(KeySignature.withName("Dxbb Minor"), KeySignature.withName("Fxbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("E#bx Minor"), KeySignature.withName("G#bx Major").getRelativeKey());

        assertEquals(F_DOUBLE_SHARP_MINOR, A_SHARP_MAJOR.getRelativeKey());
        assertEquals(G_MINOR, B_FLAT_MAJOR.getRelativeKey());
        assertEquals(KeySignature.withName("Abb Minor"), KeySignature.withName("Cbb Major").getRelativeKey());
        assertEquals(KeySignature.withName("Gb##bb# Minor"), KeySignature.withName("Bb##bb Major").getRelativeKey());
        assertEquals(KeySignature.withName("A#bbb Minor"), KeySignature.withName("C#bbb Major").getRelativeKey());

    }

    @Test
    public void getParallelKey() throws Exception {
        assertEquals(C_FLAT_MAJOR, C_FLAT_MINOR.getParallelKey());
        assertEquals(C_MAJOR, C_MINOR.getParallelKey());
        assertEquals(C_SHARP_MAJOR, C_SHARP_MINOR.getParallelKey());

        assertEquals(D_FLAT_MAJOR, D_FLAT_MINOR.getParallelKey());
        assertEquals(D_MAJOR, D_MINOR.getParallelKey());
        assertEquals(D_SHARP_MAJOR, D_SHARP_MINOR.getParallelKey());

        assertEquals(E_FLAT_MAJOR, E_FLAT_MINOR.getParallelKey());
        assertEquals(E_MAJOR, E_MINOR.getParallelKey());
        assertEquals(E_SHARP_MAJOR, E_SHARP_MINOR.getParallelKey());

        assertEquals(F_FLAT_MAJOR, F_FLAT_MINOR.getParallelKey());
        assertEquals(F_MAJOR, F_MINOR.getParallelKey());
        assertEquals(F_SHARP_MAJOR, F_SHARP_MINOR.getParallelKey());

        assertEquals(G_FLAT_MAJOR, G_FLAT_MINOR.getParallelKey());
        assertEquals(G_MAJOR, G_MINOR.getParallelKey());
        assertEquals(G_SHARP_MAJOR, G_SHARP_MINOR.getParallelKey());

        assertEquals(A_FLAT_MAJOR, A_FLAT_MINOR.getParallelKey());
        assertEquals(A_MAJOR, A_MINOR.getParallelKey());
        assertEquals(A_SHARP_MAJOR, A_SHARP_MINOR.getParallelKey());

        assertEquals(B_FLAT_MAJOR, B_FLAT_MINOR.getParallelKey());
        assertEquals(B_MAJOR, B_MINOR.getParallelKey());
        assertEquals(B_SHARP_MAJOR, B_SHARP_MINOR.getParallelKey());

        assertEquals(KeySignature.withName("Cb# Major"), KeySignature.withName("Cb# Minor").getParallelKey());



        assertEquals(C_FLAT_MINOR, C_FLAT_MAJOR.getParallelKey());
        assertEquals(C_MINOR, C_MAJOR.getParallelKey());
        assertEquals(C_SHARP_MINOR, C_SHARP_MAJOR.getParallelKey());

        assertEquals(D_FLAT_MINOR, D_FLAT_MAJOR.getParallelKey());
        assertEquals(D_MINOR, D_MAJOR.getParallelKey());
        assertEquals(D_SHARP_MINOR, D_SHARP_MAJOR.getParallelKey());

        assertEquals(E_FLAT_MINOR, E_FLAT_MAJOR.getParallelKey());
        assertEquals(E_MINOR, E_MAJOR.getParallelKey());
        assertEquals(E_SHARP_MINOR, E_SHARP_MAJOR.getParallelKey());

        assertEquals(F_FLAT_MINOR, F_FLAT_MAJOR.getParallelKey());
        assertEquals(F_MINOR, F_MAJOR.getParallelKey());
        assertEquals(F_SHARP_MINOR, F_SHARP_MAJOR.getParallelKey());

        assertEquals(G_FLAT_MINOR, G_FLAT_MAJOR.getParallelKey());
        assertEquals(G_MINOR, G_MAJOR.getParallelKey());
        assertEquals(G_SHARP_MINOR, G_SHARP_MAJOR.getParallelKey());

        assertEquals(A_FLAT_MINOR, A_FLAT_MAJOR.getParallelKey());
        assertEquals(A_MINOR, A_MAJOR.getParallelKey());
        assertEquals(A_SHARP_MINOR, A_SHARP_MAJOR.getParallelKey());

        assertEquals(B_FLAT_MINOR, B_FLAT_MAJOR.getParallelKey());
        assertEquals(B_MINOR, B_MAJOR.getParallelKey());
        assertEquals(B_SHARP_MINOR, B_SHARP_MAJOR.getParallelKey());

        assertEquals(KeySignature.withName("Cb# Minor"), KeySignature.withName("Cb# Major").getParallelKey());
    }

    @Test
    public void modulateFlat() throws Exception {
        assertEquals(NO_KEY_SIGNATURE, NO_KEY_SIGNATURE.modulateFlat());

        // Basic test cases
        assertEquals(B_FLAT_MAJOR, F_MAJOR.modulateFlat());
        assertEquals(E_FLAT_MAJOR, B_FLAT_MAJOR.modulateFlat());
        assertEquals(A_FLAT_MAJOR, E_FLAT_MAJOR.modulateFlat());
        assertEquals(D_FLAT_MAJOR, A_FLAT_MAJOR.modulateFlat());
        assertEquals(G_FLAT_MAJOR, D_FLAT_MAJOR.modulateFlat());
        assertEquals(C_FLAT_MAJOR, G_FLAT_MAJOR.modulateFlat());
        assertEquals(F_FLAT_MAJOR, C_FLAT_MAJOR.modulateFlat());
        assertEquals(B_DOUBLE_FLAT_MAJOR, F_FLAT_MAJOR.modulateFlat());
        assertEquals(E_DOUBLE_FLAT_MAJOR, B_DOUBLE_FLAT_MAJOR.modulateFlat());
        assertEquals(A_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MAJOR.modulateFlat());

        assertEquals(F_MAJOR, C_MAJOR.modulateFlat());

        assertEquals(C_MAJOR, G_MAJOR.modulateFlat());
        assertEquals(G_MAJOR, D_MAJOR.modulateFlat());
        assertEquals(D_MAJOR, A_MAJOR.modulateFlat());
        assertEquals(A_MAJOR, E_MAJOR.modulateFlat());
        assertEquals(E_MAJOR, B_MAJOR.modulateFlat());
        assertEquals(B_MAJOR, F_SHARP_MAJOR.modulateFlat());
        assertEquals(F_SHARP_MAJOR, C_SHARP_MAJOR.modulateFlat());
        assertEquals(C_SHARP_MAJOR, G_SHARP_MAJOR.modulateFlat());
        assertEquals(G_SHARP_MAJOR, D_SHARP_MAJOR.modulateFlat());
        assertEquals(D_SHARP_MAJOR, A_SHARP_MAJOR.modulateFlat());
        assertEquals(A_SHARP_MAJOR, E_SHARP_MAJOR.modulateFlat());
        assertEquals(E_SHARP_MAJOR, B_SHARP_MAJOR.modulateFlat());
        assertEquals(B_SHARP_MAJOR, KeySignature.withName("Fx Major").modulateFlat());

        assertEquals(KeySignature.withName("B#bb Major"), KeySignature.withName("F#b Major").modulateFlat());
        assertEquals(KeySignature.withName("Bb#b Major"), KeySignature.withName("Fb# Major").modulateFlat());
        assertEquals(KeySignature.withName("E#bb Major"), KeySignature.withName("B#bb Major").modulateFlat());
        assertEquals(KeySignature.withName("Eb#b Major"), KeySignature.withName("Bb#b Major").modulateFlat());
        assertEquals(KeySignature.withName("Ebb# Major"), KeySignature.withName("Bbb# Major").modulateFlat());

        assertEquals(G_DOUBLE_SHARP_MINOR, KeySignature.withName("Dx Minor").modulateFlat());
        assertEquals(C_DOUBLE_SHARP_MINOR, G_DOUBLE_SHARP_MINOR.modulateFlat());
        assertEquals(F_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MINOR.modulateFlat());
        assertEquals(B_SHARP_MINOR, F_DOUBLE_SHARP_MINOR.modulateFlat());
        assertEquals(E_SHARP_MINOR, B_SHARP_MINOR.modulateFlat());
        assertEquals(A_SHARP_MINOR, E_SHARP_MINOR.modulateFlat());
        assertEquals(D_SHARP_MINOR, A_SHARP_MINOR.modulateFlat());
        assertEquals(G_SHARP_MINOR, D_SHARP_MINOR.modulateFlat());
        assertEquals(C_SHARP_MINOR, G_SHARP_MINOR.modulateFlat());
        assertEquals(F_SHARP_MINOR, C_SHARP_MINOR.modulateFlat());
        assertEquals(B_MINOR, F_SHARP_MINOR.modulateFlat());
        assertEquals(E_MINOR, B_MINOR.modulateFlat());
        assertEquals(A_MINOR, E_MINOR.modulateFlat());
        assertEquals(D_MINOR, A_MINOR.modulateFlat());
        assertEquals(G_MINOR, D_MINOR.modulateFlat());
        assertEquals(C_MINOR, G_MINOR.modulateFlat());
        assertEquals(F_MINOR, C_MINOR.modulateFlat());
        assertEquals(B_FLAT_MINOR, F_MINOR.modulateFlat());
        assertEquals(E_FLAT_MINOR, B_FLAT_MINOR.modulateFlat());
        assertEquals(A_FLAT_MINOR, E_FLAT_MINOR.modulateFlat());
        assertEquals(D_FLAT_MINOR, A_FLAT_MINOR.modulateFlat());
        assertEquals(G_FLAT_MINOR, D_FLAT_MINOR.modulateFlat());
        assertEquals(C_FLAT_MINOR, G_FLAT_MINOR.modulateFlat());
        assertEquals(F_FLAT_MINOR, C_FLAT_MINOR.modulateFlat());
        assertEquals(KeySignature.withName("Bbb Minor"), F_FLAT_MINOR.modulateFlat());

        assertEquals(KeySignature.withName("B#bbb Minor"), KeySignature.withName("F#bb Minor").modulateFlat());
    }

    @Test
    public void modulateSharp() throws Exception {
        assertEquals(NO_KEY_SIGNATURE, NO_KEY_SIGNATURE.modulateSharp());

        // Basic test cases
        assertEquals(B_FLAT_MAJOR, E_FLAT_MAJOR.modulateSharp());
        assertEquals(E_FLAT_MAJOR, A_FLAT_MAJOR.modulateSharp());
        assertEquals(A_FLAT_MAJOR, D_FLAT_MAJOR.modulateSharp());
        assertEquals(D_FLAT_MAJOR, G_FLAT_MAJOR.modulateSharp());
        assertEquals(G_FLAT_MAJOR, C_FLAT_MAJOR.modulateSharp());
        assertEquals(C_FLAT_MAJOR, F_FLAT_MAJOR.modulateSharp());
        assertEquals(F_FLAT_MAJOR, B_DOUBLE_FLAT_MAJOR.modulateSharp());
        assertEquals(B_DOUBLE_FLAT_MAJOR, E_DOUBLE_FLAT_MAJOR.modulateSharp());
        assertEquals(E_DOUBLE_FLAT_MAJOR, A_DOUBLE_FLAT_MAJOR.modulateSharp());
        assertEquals(A_DOUBLE_FLAT_MAJOR, KeySignature.withName("Dbb Major").modulateSharp());
        assertEquals(KeySignature.withName("Dbb Major"), KeySignature.withName("Gbb Major").modulateSharp());
        assertEquals(KeySignature.withName("Gbb Major"), KeySignature.withName("Cbb Major").modulateSharp());
        assertEquals(KeySignature.withName("Cbb Major"), KeySignature.withName("Fbb Major").modulateSharp());
        assertEquals(KeySignature.withName("Fbb Major"), KeySignature.withName("Bbbb Major").modulateSharp());

        assertEquals(F_MAJOR, B_FLAT_MAJOR.modulateSharp());
        assertEquals(C_MAJOR, F_MAJOR.modulateSharp());
        assertEquals(G_MAJOR, C_MAJOR.modulateSharp());
        assertEquals(D_MAJOR, G_MAJOR.modulateSharp());
        assertEquals(A_MAJOR, D_MAJOR.modulateSharp());
        assertEquals(E_MAJOR, A_MAJOR.modulateSharp());
        assertEquals(B_MAJOR, E_MAJOR.modulateSharp());
        assertEquals(F_SHARP_MAJOR, B_MAJOR.modulateSharp());
        assertEquals(C_SHARP_MAJOR, F_SHARP_MAJOR.modulateSharp());
        assertEquals(G_SHARP_MAJOR, C_SHARP_MAJOR.modulateSharp());
        assertEquals(D_SHARP_MAJOR, G_SHARP_MAJOR.modulateSharp());
        assertEquals(A_SHARP_MAJOR, D_SHARP_MAJOR.modulateSharp());
        assertEquals(E_SHARP_MAJOR, A_SHARP_MAJOR.modulateSharp());
        assertEquals(B_SHARP_MAJOR, E_SHARP_MAJOR.modulateSharp());
        assertEquals(KeySignature.withName("Fx Major"), B_SHARP_MAJOR.modulateSharp());

        assertEquals(KeySignature.withName("F#bb# Major"), KeySignature.withName("B#bb Major").modulateSharp());
        assertEquals(KeySignature.withName("Db#b# Major"), KeySignature.withName("Gb#b# Major").modulateSharp());

        assertEquals(F_SHARP_MINOR, B_MINOR.modulateSharp());
        assertEquals(B_MINOR, E_MINOR.modulateSharp());
        assertEquals(E_MINOR, A_MINOR.modulateSharp());
        assertEquals(A_MINOR, D_MINOR.modulateSharp());
        assertEquals(D_MINOR, G_MINOR.modulateSharp());
        assertEquals(G_MINOR, C_MINOR.modulateSharp());
        assertEquals(C_MINOR, F_MINOR.modulateSharp());
        assertEquals(F_MINOR, B_FLAT_MINOR.modulateSharp());
        assertEquals(B_FLAT_MINOR, E_FLAT_MINOR.modulateSharp());
        assertEquals(E_FLAT_MINOR, A_FLAT_MINOR.modulateSharp());
        assertEquals(A_FLAT_MINOR, D_FLAT_MINOR.modulateSharp());
        assertEquals(D_FLAT_MINOR, G_FLAT_MINOR.modulateSharp());
        assertEquals(G_FLAT_MINOR, C_FLAT_MINOR.modulateSharp());
        assertEquals(C_FLAT_MINOR, F_FLAT_MINOR.modulateSharp());


        assertEquals(C_SHARP_MINOR, F_SHARP_MINOR.modulateSharp());
        assertEquals(G_SHARP_MINOR, C_SHARP_MINOR.modulateSharp());
        assertEquals(D_SHARP_MINOR, G_SHARP_MINOR.modulateSharp());
        assertEquals(A_SHARP_MINOR, D_SHARP_MINOR.modulateSharp());
        assertEquals(E_SHARP_MINOR, A_SHARP_MINOR.modulateSharp());
        assertEquals(B_SHARP_MINOR, E_SHARP_MINOR.modulateSharp());
        assertEquals(F_DOUBLE_SHARP_MINOR, B_SHARP_MINOR.modulateSharp());
        assertEquals(C_DOUBLE_SHARP_MINOR, F_DOUBLE_SHARP_MINOR.modulateSharp());
        assertEquals(G_DOUBLE_SHARP_MINOR, C_DOUBLE_SHARP_MINOR.modulateSharp());
        assertEquals(KeySignature.withName("Dx Minor"), G_DOUBLE_SHARP_MINOR.modulateSharp());
        assertEquals(KeySignature.withName("Ax Minor"), KeySignature.withName("Dx Minor").modulateSharp());
        assertEquals(KeySignature.withName("Ex Minor"), KeySignature.withName("Ax Minor").modulateSharp());
        assertEquals(KeySignature.withName("Bx Minor"), KeySignature.withName("Ex Minor").modulateSharp());

        assertEquals(KeySignature.withName("Cb## Minor"), KeySignature.withName("Fb## Minor").modulateSharp());
    }

}