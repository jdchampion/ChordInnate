package chordinnate.model.musictheory.temporal.meter;

import chordinnate.util.MathUtils;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Joseph on 7/1/16.
 *
 * @see <a href=http://www.midnightmusic.net/MusicTheory/advanced/UnusualTimeSigs.html>1</a>
 * @see <a href=https://en.wikipedia.org/wiki/Time_signature>2</a>
 * @see <a href=http://donrathjr.com/compound-time-signatures>3</a>
 */
public enum MeterType {

    /**
     * Time signature uses exclusively integers as its measurement of time.
     * The time signature denominator MUST be a dyadic rational (i.e., a power of 2).
     */
    COMPLETE,

    /**
     * Meter is constant throughout the musical structure.
     */
    FIXED,

    /**
     * Time signature is not subdivided, or no time signature is used.
     */
    FREE,

    /**
     * Meter changes more than once within the musical structure.
     */
    MIXED,

    /**
     * Meter alternates between EXACTLY two time signatures.
     */
    ALTERNATING,

    /**
     * Time signature numerator is divisible by 2 OR divisible by 3.
     */
    MULTPLICATIVE,

    /**
     * Time signature numerator is divisible by 2.
     */
    IMPERFECT,

    /**
     * Time signature numerator IS NOT divisible by 2.
     */
    ODD,

    /**
     * Time signature numerator is < 6 AND (divisible by 2 XOR 3).
     */
    SIMPLE,

//    /**
//     * Further classification of a simple meter. Beat values can be evenly grouped into 2.
//     */
//    SIMPLE_DUPLE,
//
//    /**
//     * Further classification of a simple meter. Beat values can be evenly grouped into 3.
//     */
//    SIMPLE_TRIPLE,
//
//    /**
//     * Further classification of a simple meter. Beat values can be evenly grouped into 4.
//     */
//    SIMPLE_QUADRUPLE,

    /**
     * Time signature numerator is divisible by 3.
     */
    PERFECT,

    /**
     * Time signature numerator is > 3 AND divisible by 3.
     */
    COMPOUND,

//    /**
//     * Further classification of a compound meter. Beat values can be evenly grouped into 2.
//     */
//    COMPOUND_DUPLE,
//
//    /**
//     * Further classification of a compound meter. Beat values can be evenly grouped into 3.
//     */
//    COMPOUND_TRIPLE,
//
//    /**
//     * Further classification of a compound meter. Beat values can be evenly grouped into 4.
//     */
//    COMPOUND_QUADRUPLE,

    /**
     * Time signature numerator is indicated with a subdivision pattern for the meter.
     * Example: (3 + 2 + 3) / 8 --> 8 beats per measure, 8th note gets the beat. Stress pattern = 3-2-3
     */
    ADDITIVE,

    /**
     * Time signature contains both perfect (% 3) AND imperfect (% 2) subdivisions,
     * BUT NOT divisible by 3.
     */
    COMPLEX,

    /**
     * Meter is both odd AND complex.
     */
    IRREGULAR,

    /**
     * Meter is both odd AND complex.
     */
    ASYMMETRICAL,

    /**
     * Time signature denominator is NOT a dyadic rational (log2(x) == int).
     */
    IRRATIONAL,

    /**
     * Time signature numerator is written as a floating point number.
     */
    PARTIAL,

    /**
     * Time signature numerator is written as a fraction.
     */
    FRACTIONAL,

    ;

    public static Set<MeterType> classify(Metered metered) {
        Set<MeterType> set = new HashSet<>();

        List<TimeSignature> list = metered.getAllTimeSignatures();

        // classify at the atomic level (single TimeSignature)
        for (TimeSignature ts : list) {
            set.addAll(classify(ts));
        }

        // classify with respect to all TimeSignatures in the structure
        if (CollectionUtils.isEmpty(list)) {
            set.add(FREE);
        } else if (list.size() == 1) {
            set.add(FIXED);
        } else {
            Set<String> uniqueTimeSigs = new HashSet<>();

            String[] alternatePair = new String[2];
            alternatePair[0] = list.get(0).getDisplayString();
            alternatePair[1] = list.get(1).getDisplayString();

            uniqueTimeSigs.add(alternatePair[0]);
            uniqueTimeSigs.add(alternatePair[1]);

            boolean isAlternating = true;
            for (int i = 2, j = 0; i < list.size(); i++, j = (j + 1) % 2) {
                String current = list.get(i).getDisplayString();
                uniqueTimeSigs.add(current);
                if (isAlternating && !current.equals(alternatePair[j])) {
                    isAlternating = false;
                }
            }

            if (isAlternating) {
                set.add(ALTERNATING);
            }

            if (uniqueTimeSigs.size() > 1) {
                set.add(MIXED);
            }
        }

        // TODO: resolve any mutual exclusions

        return Collections.unmodifiableSet(set);
    }

    public static Set<MeterType> classify(TimeSignature timeSignature) {

        if (timeSignature == null || TimeSignature.NONE.equals(timeSignature)) {
            return Collections.singleton(FREE);
        }

        Set<MeterType> meterTypes = new HashSet<>();

        double numerator = timeSignature.getNumerator().doubleValue();
        double denominator = timeSignature.getDenominator().doubleValue();

        if (timeSignature.getNumerator() instanceof Fraction) {
            meterTypes.add(FRACTIONAL);
        } else {
            if (timeSignature.getNumerator() instanceof Double) {
                meterTypes.add(PARTIAL);
            } else {
                meterTypes.add(COMPLETE);
            }
        }

        if (!MathUtils.isPowerOf(2, denominator)) {
            meterTypes.add(IRRATIONAL);
        }

        if (timeSignature.isDisplayStressPattern()
                && timeSignature.getStressPattern() != null
                && timeSignature.getStressPattern().size() > 1) {
            meterTypes.add(ADDITIVE);
        }

        boolean div2 = numerator % 2 == 0;
        boolean div3 = numerator % 3 == 0;

        if (div2 || div3) {
            meterTypes.add(MULTPLICATIVE);
            meterTypes.add(div2 ? IMPERFECT : ODD);
            if (numerator < 6) {
                meterTypes.add(SIMPLE);
            } else if (div3) {
                meterTypes.add(PERFECT);
                if (numerator > 3) {
                    meterTypes.add(COMPOUND);
                }
            } else {
                // numerator >= 5 && !div3
                meterTypes.add(COMPLEX);
            }
        } else {
            meterTypes.add(ODD);
            if (numerator > 1) {
                meterTypes.add(COMPLEX);
                meterTypes.add(IRREGULAR);
                meterTypes.add(ASYMMETRICAL);
            }
        }

        return meterTypes;
    }
}
