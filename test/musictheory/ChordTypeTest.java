package musictheory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */
public class ChordTypeTest {

    @Test
    public void testChordType() throws Exception {
        for (ChordType chordType : ChordType.values()) {
            assertNotNull(chordType.chordSymbol);
            assertNotNull(chordType.nashvilleNumbers);

            // ScaleTypes must have their NashvilleNumbers defined in
            // either ascending or descending order
            assertTrue(isAscendingNashvilleNumbers(chordType));
        }
    }

    private boolean isAscendingNashvilleNumbers(ChordType chordType) {
        boolean ret = false;
        for (int i = 1; i < chordType.nashvilleNumbers.length; i++) {
            ret = (chordType.nashvilleNumbers[i-1].relativePitchDistance
                    < chordType.nashvilleNumbers[i].relativePitchDistance);
        }

        return ret;
    }
}