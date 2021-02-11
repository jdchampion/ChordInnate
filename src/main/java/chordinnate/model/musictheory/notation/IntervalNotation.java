package chordinnate.model.musictheory.notation;

/**
 * Marker interface for classes that work as an abstract descriptor for musical intervals,
 * but cannot be used as a direct substitute for the interval class.
 */
public interface IntervalNotation {
    String getSymbol();
}
