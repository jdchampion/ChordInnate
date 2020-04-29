package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.temporal.meter.MeterSubdivision;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.util.MathUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joseph on 7/6/16.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeSignature {

    public static final TimeSignature NONE = new TimeSignature(Double.POSITIVE_INFINITY, 1);

    private Number numerator;
    private Number denominator;
    private List<MeterSubdivision> stressPattern;
    private boolean displayStressPattern;

    public TimeSignature(@NotNull Number numerator, @NotNull Number denominator) {
        validate(numerator, denominator, null);
        this.numerator = numerator;
        this.denominator = denominator;
        this.stressPattern = Collections.emptyList();
        this.displayStressPattern = false;
    }

    /**
     * Constructs a time signature with a customized rhythmic stress pattern
     * @param stressPattern
     * @param displayStressPattern
     * @param denominator
     */
    public TimeSignature(@NotNull List<MeterSubdivision> stressPattern, boolean displayStressPattern, @NotNull Number denominator) {
        int total = stressPattern.stream().mapToInt(MeterSubdivision::getGrouping).sum();
        this.numerator = total;
        this.denominator = denominator;
        this.stressPattern = stressPattern;
        this.displayStressPattern = displayStressPattern;
        validate(total, denominator, stressPattern);
    }

    private void validate(Number numerator, Number denominator, List<MeterSubdivision> numeratorStressPattern) {
        if (numeratorStressPattern != null && CollectionUtils.isEmpty(numeratorStressPattern)) {
            throw new IllegalArgumentException("Stress pattern for this time signature cannot be empty");
        }

        if (numerator.doubleValue() < 1 || denominator.doubleValue() < 1) {
            throw new IllegalArgumentException("Numerator or denominator < 1; both must be >= 1");
        }

        if (denominator instanceof Fraction || denominator.doubleValue() != denominator.intValue()) {
            throw new IllegalArgumentException("Denominator must be a whole number");
        }
    }

    public String getDisplayString() {

        String numeratorString;
        if (CollectionUtils.isEmpty(stressPattern)) {

            if (numerator instanceof Fraction) {
                numeratorString = "(" + numerator.toString() + ")";
            } else {
                numeratorString = numerator.toString();
            }

        } else {
            if (displayStressPattern) {
                numeratorString = "(" + stressPattern.stream()
                        .map(msd -> String.valueOf(msd.getGrouping()))
                        .collect(Collectors.joining(" + ")) + ")";
            } else {
                numeratorString = numerator.toString();
            }
        }

        return numeratorString + " / " + denominator.toString();
    }

    @Nullable
    public Beat getReferenceBeat() {
        if (denominator.doubleValue() != denominator.intValue() || !MathUtils.isPowerOf(2, denominator.doubleValue())) {
            return null; // time signature is irrational
        }

        return Beat.builder(Fraction.getFraction(1, denominator.intValue())).build();
    }
}
