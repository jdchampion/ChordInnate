package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.interval.set.ScaleType;
import org.junit.Test;

import static org.junit.Assert.*;
import static chordinnate.musictheory.pitch.Pitch.*;

/**
 * Created by Joseph on 5/20/16.
 */
public class TestScaleType {
    @Test
    public void getName() throws Exception {
        // All ScaleType names should be representative of the ScaleType enum name.
        for (ScaleType scaleType : ScaleType.values()) {
            String
                    expected = scaleType
                    .toString()                             // Get the actual enum value name
                    .replaceAll("[\\W_]*", "")              // Remove special characters
                    .toLowerCase(),                         // Convert it to lower case for comparison

                    actual = scaleType.NAME                 // Get the enum's field NAME
                            .replaceAll("[\\W_]*", "")      // Remove special characters
                            .toLowerCase();                 // Convert it to lower case for comparison

            assertEquals(expected, actual);
        }
    }

    @Test
    public void getPitchIntervals() throws Exception {
        // All ScaleTypes should have valid PitchInterval arrays.
        for (ScaleType scaleType : ScaleType.values()) {
            assertNotNull(scaleType.getPitchIntervals());
        }

        /*
         * I would like to be able to cross-reference the PitchIntervals of each ScaleType,
         * because that would be the most thorough testing method available.
         *
         * However, the PitchInterval array is unique for each ScaleType.
         * Since the ScaleType enum is intended to expand to support future ScaleTypes,
         * the maintenance for such cross-referencing would be so high as to become impractical.
         *
         * Until some other method can be found for verifying the correct array values,
         * the next best method is to do a visual cross-reference on the ScaleType enum.
         */

        // TODO: move these tests to TestScale
        verifyScale(ScaleType.MAJOR, new Pitch[] {C_4, D_4, E_4, F_4, G_4, A_4, B_4});
        verifyScale(ScaleType.MAJOR, new Pitch[] {F_SHARP_4, G_SHARP_4, A_SHARP_4, B_4, C_SHARP_5, D_SHARP_5, E_SHARP_5});
        verifyScale(ScaleType.HARMONIC_MINOR, new Pitch[] {C_4, D_4, E_FLAT_4, F_4, G_4, A_FLAT_4, B_4});
        verifyScale(ScaleType.HARMONIC_MINOR, new Pitch[] {B_FLAT_3, C_4, D_FLAT_4, E_FLAT_4, F_4, G_FLAT_4, A_4});
        verifyScale(ScaleType.BLUES, new Pitch[] {C_4, E_FLAT_4, F_4, F_SHARP_4, G_4, B_FLAT_4});
    }

    @Test
    public void getScaleTypeProperty() throws Exception {
        // All ScaleTypes should have valid ScaleTypeProperties
        for (ScaleType scaleType : ScaleType.values()) {
            assertNotNull(scaleType.SCALE_TYPE_PROPERTY);
        }

        /*
         * Again, there's currently no known way of automatically testing
         * the correct ScaleTypeProperty for a given ScaleType.
         *
         * The current solution is to do a visual cross-reference.
         */
    }

    // TODO: move this method to TestScale
    /**
     * Helper method for testing the returned Pitch values for a given ScaleType.
     * @param scaleType
     * @param expectedPitches
     */
    private void verifyScale(ScaleType scaleType, Pitch[] expectedPitches) {
        PitchInterval[] pitchIntervals = scaleType.getPitchIntervals();
        Pitch root = expectedPitches[0];

        for (int i = 1; i < pitchIntervals.length; i++) {
            assertEquals(expectedPitches[i], root.transposeTo(pitchIntervals[i], true));
        }
    }

}