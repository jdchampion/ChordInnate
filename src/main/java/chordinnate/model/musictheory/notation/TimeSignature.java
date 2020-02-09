package chordinnate.model.musictheory.notation;

import lombok.Data;

/**
 * Created by Joseph on 7/6/16.
 */
@Data
public class TimeSignature {

    private double numerator;
    private double denominator;

    public TimeSignature(double numerator, double denominator) {
        if (numerator < 1 || denominator < 1) {
            throw new IllegalArgumentException("Numerator or denominator < 1; both must be >= 1");
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }
}
