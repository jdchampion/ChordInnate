package musictheory;

/**
 * Created by Joseph on 3/8/16.
 */
enum IntervalQuality {
    MAJOR("M", ""),
    MINOR("m", ""),
    PERFECT("P", ""),
    AUGMENTED("A", "+"),
    DIMINISHED("d", "˚");

    final String shortNameIdentifier, romanNumeralIdentifier;

    IntervalQuality(String shortNameIdentifier, String romanNumeralIdentifier) {
        this.shortNameIdentifier = shortNameIdentifier;
        this.romanNumeralIdentifier = romanNumeralIdentifier;
    }
}