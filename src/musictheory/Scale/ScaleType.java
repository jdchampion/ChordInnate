package musictheory.Scale;

/**
 * Created by Joseph on 12/31/15.
 */
public enum ScaleType {
    MAJOR("Major",
            new int[] {0, 2, 4, 5, 7, 9, 11}
    ),
    HARMONIC_MINOR("Harmonic Minor",
            new int[] {0, 2, 3, 5, 7, 8, 11}
    );

    private String name;
    private int[] sequence;

    ScaleType(String name, int[] sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    protected String getName() {
        return name;
    }

    protected int[] getSequence() {
        return sequence.clone();
    }

    protected int getNumberOfNotes() {
        if (sequence == null) return 0;

        return sequence.length;
    }
}