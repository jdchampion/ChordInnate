package chordinnate.model.musictheory.notation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 7/6/16.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeSignature {

    private Number numerator;
    private Number denominator;
    private List<Number> numeratorStressPattern;

    public TimeSignature(@NotNull Number numerator, @NotNull Number denominator) {
        validate(numerator, denominator, null);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a time signature with a customized rhythmic stress pattern
     * @param numeratorStressPattern
     * @param denominator
     */
    public TimeSignature(@NotNull List<Number> numeratorStressPattern, @NotNull Number denominator) {
        double total = numeratorStressPattern.stream().mapToDouble(Number::doubleValue).sum();
        validate(total, denominator, numeratorStressPattern);
        this.numerator = total;
        this.denominator = denominator;
        this.numeratorStressPattern = numeratorStressPattern;
    }

    private void validate(Number numerator, Number denominator, List<Number> numeratorStressPattern) {
        if (numerator.doubleValue() < 1 || denominator.doubleValue() < 1) {
            throw new IllegalArgumentException("Numerator or denominator < 1; both must be >= 1");
        }

        if (numeratorStressPattern != null) {
            boolean additiveFractionsExist = numeratorStressPattern.stream().anyMatch(n -> n instanceof Fraction);
            if (additiveFractionsExist) {
                throw new UnsupportedOperationException("Stress pattern must consist of whole numbers");
            }
        }

        if (denominator instanceof Fraction || denominator.doubleValue() != denominator.intValue()) {
            throw new UnsupportedOperationException("Denominator must be a whole number");
        }
    }

    public String getDisplayString() {

        String numeratorString;
        if (numeratorStressPattern == null) {

            if (numerator instanceof Fraction) {
                numeratorString = "(" + numerator.toString() + ")";
            } else {
                numeratorString = numerator.toString();
            }

        } else {
            numeratorString = "(" + numeratorStressPattern.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" + ")) + ")";
        }

        return numeratorString + " / " + denominator.toString();
    }
}
