package musictheory;

/**
 * Created by Joseph on 3/8/16.
 */
enum Accidental {
    DOUBLE_FLAT("\u266d\u266d"),
    FLAT("\u266d"),
    NATURAL("\u266e"),
    NONE(""),
    SHARP("\u266f"),
    DOUBLE_SHARP("x");

    final String indicator;

    Accidental(String indicator) {
        this.indicator = indicator;
    }
}