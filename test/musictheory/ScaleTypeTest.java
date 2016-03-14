package musictheory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */
public class ScaleTypeTest {

    @Test
    public void testScaleType() throws Exception {
        for (ScaleType scaleType : ScaleType.values()) {
            assertNotNull(scaleType.name);
            assertNotNull(scaleType.nashvilleNumbers);
            assertNotNull(scaleType.tonality);

            // ScaleTypes must have their NashvilleNumbers defined in
            // either ascending or descending order
            assertTrue(isAscendingNashvilleNumbers(scaleType)
                    ^ isDescendingNashvilleNumbers(scaleType));
        }
    }

    private boolean isAscendingNashvilleNumbers(ScaleType scaleType) {
        boolean ret = false;
        for (int i = 1; i < scaleType.nashvilleNumbers.length; i++) {
            ret = (scaleType.nashvilleNumbers[i-1].relativePitchDistance
                    < scaleType.nashvilleNumbers[i].relativePitchDistance);
        }

        return ret;
    }

    private boolean isDescendingNashvilleNumbers(ScaleType scaleType) {
        boolean ret = false;
        for (int i = 1; i < scaleType.nashvilleNumbers.length; i++) {
            ret = (scaleType.nashvilleNumbers[i-1].relativePitchDistance
                    > scaleType.nashvilleNumbers[i].relativePitchDistance);
        }

        return ret;
    }
}