package chordinnate.model.musictheory.notation;

/**
 * Created by Joseph on 5/21/16.
 */
public enum Letter {
    C(0), D(2), E(4), F(5), G(7), A(9), B(11);

    public int BASE_MIDI_VALUE;

    Letter(int baseMidiValue) {
        this.BASE_MIDI_VALUE = baseMidiValue;
    }

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
     * Finds the vector distance required to travel left to right on the enumerated list
     * to reach the other Letter.
     * @param lhs the starting Letter
     * @param rhs the ending Letter
     * @return the number of indices to move left or right to withShortName to the next Letter
     */
    public static int getVectorDistanceBetween(Letter lhs, Letter rhs) {
        int thisOrdinal = lhs.ordinal(), otherOrdinal = rhs.ordinal();
        return (thisOrdinal < otherOrdinal) ? otherOrdinal - thisOrdinal : 7 - (thisOrdinal - otherOrdinal);
    }
}