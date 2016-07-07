package chordinnate.musictheory.pitch.notation;

/**
 * Created by Joseph on 5/21/16.
 */
public enum Letter {
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
}
