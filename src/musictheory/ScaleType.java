package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public enum ScaleType {
    MAJOR("Major",
            new int[] {0, 2, 4, 5, 7, 9, 11}
    ),
    HARMONIC_MINOR("Harmonic Minor",
            new int[] {0, 2, 3, 5, 7, 8, 11}
    );

    String name;
    int[] sequence;

    ScaleType(String name, int[] sequence) {
        this.name = name;
        this.sequence = sequence;
    }
}
