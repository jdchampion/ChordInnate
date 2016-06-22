package chordinnate.musictheory;

/**
 * Created by Joseph on 5/21/16.
 */
enum Letter {
    C, D, E, F, G, A, B;

    /**
     * Gets the next Letter from the enumerated list that would result from
     * traversing the list forwards or backwards by the specified distance.
     * @param distance how far left (negative) or right (positive) to traverse
     * @return the Letter from the enum after traversal
     */
    Letter getLetterByVectorTraversal(int distance) {
        // Get the array offset (works with positive or negative index)
        int offset = (distance > 0) ? distance : (7 - ((distance * -1) % 7));
        // Return from the modulus location of currentIndex + offset
        return Letter.values()[(ordinal() + offset) % 7];
    }

    /**
     * Finds the vector distance required to travel left or right on the enumerated list
     * to reach the other Letter.
     * @param other
     * @param direction
     * @return
     */
    int getVectorDistanceTo(Letter other, boolean direction) {
        int thisOrdinal = ordinal(), otherOrdinal = other.ordinal();
        if (thisOrdinal == otherOrdinal) {
            return 0;
        }
        else if (thisOrdinal < otherOrdinal) {
            return direction
                    ? otherOrdinal - thisOrdinal
                    : (7 - (otherOrdinal - thisOrdinal)) * -1;
        }
        else if (thisOrdinal > otherOrdinal) {
            return direction
                    ? 7 - (thisOrdinal - otherOrdinal)
                    : (thisOrdinal - otherOrdinal) * -1;
        }
        else {
            return -1; // This case is actually unreachable
        }
    }
}
