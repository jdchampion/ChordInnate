package chordinnate.musictheory.time.meter;

import chordinnate.musictheory.time.rhythm.Beat;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Joseph on 7/1/16.
 *
 * References: http://www.midnightmusic.net/MusicTheory/advanced/UnusualTimeSigs.html
 *             https://en.wikipedia.org/wiki/Time_signature
 *             http://donrathjr.com/compound-time-signatures/
 */
@Deprecated
public class TimeSignature {
    private double numerator;
    private Beat denominator;
    private double measureDuration;
    private ArrayList<MeterSubdivision>
            possibleMeterSubdivisions,
            actualMeterSubdivisions;
    private ArrayList<MeterProperty> meterProperties;
    private boolean[] stressPatternBool;
    private int[] stressPatternInt;

    public static final double MAX_NUMERATOR = 1024; // arbitrarily set

    /**
     * Constructor for non-additive metered TimeSignatures (e.g., most TimeSignatures).
     * @param numerator
     * @param denominator
     */
    public TimeSignature(double numerator, @NotNull Beat denominator) {
        if (numerator < 0 || numerator > MAX_NUMERATOR) {
            throw new IllegalArgumentException("Invalid numerator for the TimeSignature.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = (numerator * denominator.getRatio());

        this.possibleMeterSubdivisions = new ArrayList<>();
        inferPossibleMeterGroupings();
        this.actualMeterSubdivisions = new ArrayList<>();
        // TODO: fill in actual MeterGroupings

        this.meterProperties = new ArrayList<>(MeterProperty.values().length);
        addCommonMeterProperties();

        /*
           FIXME: arrays are not correct.
           Expected for 9/8: {3,3,3} (using 3x MeterSubdivision.TRIPLE)
           Actual for 9/8: {3} (using 1x TRIPLE)
           Expected for 4/4: {4} (using 1x MeterSubdivision.QUADRUPLE)
           Actual for 4/4: {2, 4} (using 1x DUPLE + 1x QUADRUPLE)
         */
        // Build the stress pattern from the MeterGroupings
        this.stressPatternInt = new int[this.possibleMeterSubdivisions.size()];
        for (int i = 0; i < this.stressPatternInt.length; i++) {
            this.stressPatternInt[i] = possibleMeterSubdivisions.get(i).GROUPING;
        }
        this.stressPatternBool = boolStressPatternFromIntStressPattern();
    }

    /**
     * Constructor for metered TimeSignatures with a defined stress pattern.
     * @param numerator
     * @param denominator
     * @param stressPattern
     */
    public TimeSignature(double numerator, @NotNull Beat denominator, @NotNull boolean[] stressPattern) {
        if (numerator < 0 || numerator > MAX_NUMERATOR) {
            throw new IllegalArgumentException("Invalid numerator for the TimeSignature.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = (numerator * denominator.getRatio());

        if (!safeToUseStressPattern(stressPattern)) {
            throw new IllegalArgumentException("Stress pattern does not match the TimeSignature.");
        }
        this.stressPatternBool = stressPattern;
        this.stressPatternInt = intStressPatternFromBoolStressPattern();

        this.meterProperties = new ArrayList<>(MeterProperty.values().length);
        addCommonMeterProperties();

        this.possibleMeterSubdivisions = new ArrayList<>();
        inferPossibleMeterGroupings();
        this.actualMeterSubdivisions = new ArrayList<>();
        // TODO: fill in actual MeterGroupings
    }

    /**
     * Constructor for metered TimeSignatures with a defined stress pattern.
     * @param numerator
     * @param denominator
     * @param stressPattern
     */
    public TimeSignature(double numerator, @NotNull Beat denominator, @NotNull int[] stressPattern) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.measureDuration = (numerator * denominator.getRatio());

        if (!safeToUseStressPattern(stressPattern)) {
            throw new IllegalArgumentException("Stress pattern does not match the TimeSignature.");
        }
        this.stressPatternInt = stressPattern;
        this.stressPatternBool = boolStressPatternFromIntStressPattern();

        this.meterProperties = new ArrayList<>(MeterProperty.values().length);
        addCommonMeterProperties();

        this.possibleMeterSubdivisions = new ArrayList<>();
        inferPossibleMeterGroupings();
    }

    public boolean is(MeterProperty meterProperty) {
        return meterProperties.contains(meterProperty);
    }

    private void inferPossibleMeterGroupings() {
        boolean lt5 = numerator < 5.0;
        for (MeterSubdivision meterSubdivision : MeterSubdivision.values()) {
            if (meterSubdivision.GROUPING <= numerator) {
                this.possibleMeterSubdivisions.add(meterSubdivision);
            }
        }
    }

    private boolean isFreeMetered() {
        return this.meterProperties.contains(MeterProperty.FREE);
    }

    private boolean isAdditive() {
        return this.meterProperties.contains(MeterProperty.ADDITIVE);
    }

    private boolean safeToUseStressPattern(int[] stressPatternInt) {
        // Free-metered TimeSignatures should never have a stress pattern
        if (stressPatternInt == null) {
            return false;
        }
        int sum = 0;
        for (int i : stressPatternInt) {
            sum += i;
        }
        return sum == numerator;
    }

    private boolean safeToUseStressPattern(boolean[] stressPatternBool) {
        // Free-metered TimeSignatures should never have a stress pattern
        if (stressPatternBool == null) {
            return false;
        }
        return stressPatternBool.length == this.numerator;
    }

    private boolean[] boolStressPatternFromIntStressPattern() {
        LinkedList<Boolean> bools = new LinkedList<>();
        for (int i : stressPatternInt) {
            bools.add(true);
            int subdivisionBound = i - 1;
            for (int j = 0; j < subdivisionBound; j++) {
                bools.add(false);
            }
        }
        boolean[] ret = new boolean[bools.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = bools.pop(); // removes first element
        }
        return ret;
    }

    private int[] intStressPatternFromBoolStressPattern() {
        ArrayList<Integer> values = new ArrayList<>();
        int value = 1;
        for (int i = 0; i < stressPatternBool.length; i++, value++) {
            if (stressPatternBool[i]) {
                values.add(value);
                value = 1;
            }
        }
        int[] ret = new int[values.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = values.get(i);
        }
        return ret;
    }

    private void addCommonMeterProperties() {
        boolean
                div2 = (numerator % 2.0 == 0.0),
                div3 = (numerator % 3.0 == 0.0);

        addPropertyIfTrue(MeterProperty.ODD, !div2);
        meterProperties.add(div2 ^ div3 ? MeterProperty.PERFECT : MeterProperty.IMPERFECT);
        addPropertyIfTrue(MeterProperty.SIMPLE, div2 && !div3);
        addPropertyIfTrue(MeterProperty.COMPOUND, div3 && !div2);

        boolean isComplex = isComplex();

        if (isComplex) meterProperties.remove(MeterProperty.SIMPLE);    // can't be both simple and complex

        addPropertyIfTrue(MeterProperty.COMPLEX, isComplex);
        addPropertyIfTrue(MeterProperty.IRREGULAR, isComplex);
        addPropertyIfTrue(MeterProperty.ASYMMETRICAL, isComplex);

        boolean isFractional = (Math.ceil(numerator) != Math.floor(numerator));

        addPropertyIfTrue(MeterProperty.FRACTIONAL, isFractional);
        addPropertyIfTrue(MeterProperty.PARTIAL, isFractional);

        boolean denomDiv2 = ((1 / denominator.getRatio()) % 2.0 == 0.0);

        addPropertyIfTrue(MeterProperty.IRRATIONAL, !denomDiv2);
    }


    private boolean isComplex() {
        /*
         * Properties of a complex TimeSignature:
         * - Numerator >= 5
         * - At least two elements, X & Y, in the stressPattern
         * - X != Y
         * - X cannot % 6 and Y cannot % 6
         * - X % 2 and Y % 3
         */
        if (numerator < 5.0 || stressPatternBool == null || stressPatternInt == null) {
            return false;
        }
        if (stressPatternInt.length < 2) return false;
        boolean div2 = false, div3 = false;
        for (int i : stressPatternInt) {
            if (i % 6 == 0) continue;
            if (!div2 && i % 2 == 0) div2 = true;
            if (!div3 && i % 3 == 0) div3 = true;
        }
        return div2 && div3;
    }

    private void addPropertyIfTrue(MeterProperty meterProperty, boolean condition) {
        if (condition) meterProperties.add(meterProperty);
    }

    public double getNumerator() {
        return numerator;
    }

    public Beat getDenominator() {
        return denominator;
    }

    public MeterSubdivision[] getPossibleMeterSubdivisions() {
        return possibleMeterSubdivisions.toArray(new MeterSubdivision[possibleMeterSubdivisions.size()]);
    }

    public MeterProperty[] getMeterProperties() {
        return meterProperties.toArray(new MeterProperty[meterProperties.size()]);
    }

    public double getMeasureDuration() {
        return measureDuration;
    }

    public boolean[] getStressPattern() {
        // Return a copy of the array (to protect against mutation)
        boolean[] copy = new boolean[stressPatternBool.length];
        System.arraycopy(stressPatternBool, 0, copy, 0, stressPatternBool.length);
        return copy;
    }

    public boolean setStressPattern(@NotNull int... stressPattern) {
        if (safeToUseStressPattern(stressPattern) && !isFreeMetered()) {
            stressPatternInt = stressPattern;
            stressPatternBool = boolStressPatternFromIntStressPattern();


            // TODO: ensure the logic below is correct.
            /*
               Example:
                        BEFORE: 9/8 with stress pattern {3,3,3}
                                - MeterSubdivision = {TRIPLE}
                                - MeterProperties = {ODD, PERFECT, COMPOUND}

                        AFTER:  9/8 with stress pattern {2,3,2,2}
                                - MeterSubdivision = {DUPLE, TRIPLE}
                                - MeterProperties = {ODD, PERFECT, COMPOUND, COMPLEX, IRREGULAR, ASYMMETRICAL, ADDITIVE}
             */
            // Automatically update MeterGroupings and MeterProperties
            possibleMeterSubdivisions = new ArrayList<>();
            inferPossibleMeterGroupings();
            meterProperties = new ArrayList<>(MeterProperty.values().length);
            addCommonMeterProperties();

            return true;
        }

        return false;
    }

    public boolean containsMeterGrouping(MeterSubdivision meterSubdivision) {
        if (actualMeterSubdivisions == null) return false;
        return actualMeterSubdivisions.contains(meterSubdivision);
    }

    boolean possiblyContainsMeterGrouping(MeterSubdivision meterSubdivision) {
        if (possibleMeterSubdivisions == null) return false;
        return possibleMeterSubdivisions.contains(meterSubdivision);
    }

}
