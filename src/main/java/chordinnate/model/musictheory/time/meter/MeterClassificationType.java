package chordinnate.model.musictheory.time.meter;

/**
 * Created by Joseph on 7/1/16.
 *
 * References: http://www.midnightmusic.net/MusicTheory/advanced/UnusualTimeSigs.html
 *             https://en.wikipedia.org/wiki/Time_signature
 *             http://donrathjr.com/compound-time-signatures/
 */
public enum MeterClassificationType {
    /*
     * Time signature is not subdivided, or no time signature is used.
     */
    FREE,

    /*
     * Meter changes more than once.
     */
    MIXED,

    /*
     * Meter alternates between EXACTLY two time signatures.
     */
    ALTERNATING,

    /*
     * Time signature numerator is divisible by 2 OR divisible by 3.
     */
    MULTPLICATIVE,

    /*
     * Time signature numerator is divisible by 2.
     */
    IMPERFECT,

    /*
     * Time signature numerator IS NOT divisible by 2.
     */
    ODD,

    /*
     * Time signature numerator is < 6 AND (divisible by 2 XOR 3).
     */
    SIMPLE,

    /*
     * Further classification of a simple meter.
     * Duple: Beat values can be evenly grouped into 2.
     * Triple: Beat values can be evenly grouped into 3.
     * Quadruple: Beat values can be evenly grouped into 4.
     */
    SIMPLE_DUPLE,
    SIMPLE_TRIPLE,
    SIMPLE_QUADRUPLE,

    /*
     * Time signature numerator is divisible by 3.
     */
    PERFECT,

    /*
     * Time signature numerator is > 3 AND divisible by 3.
     */
    COMPOUND,

    /*
     * Further classification of a compound meter.
     * Duple: Beat values can be evenly grouped into 2.
     * Triple: Beat values can be evenly grouped into 3.
     * Quadruple: Beat values can be evenly grouped into 4.
     */
    COMPOUND_DUPLE,
    COMPOUND_TRIPLE,
    COMPOUND_QUADRUPLE,

    /*
     * Time signature numerator is indicated with a subdivision pattern for the meter.
     * Example: (3 + 2 + 3)/8 --> 8 beats per measure, 8th note gets the beat. Stress pattern = 3-2-3
     */
    ADDITIVE,

    /*
     * Time signature contains both perfect (% 3) AND imperfect (% 2) subdivisions,
     * BUT NOT divisible by 3.
     */
    COMPLEX,

    /*
     * Meter is both odd AND complex.
     */
    IRREGULAR,
    ASYMMETRICAL,

    /*
     * Time signature denominator is NOT a dyadic rational (log2(x) == int).
     */
    IRRATIONAL,

    /*
     * Time signature numerator is written as a floating point number
     * that is a dyadic (log2(x) == int) or triadic (log3(x) == int) rational.
     */
    PARTIAL,

    /*
     * Time signature numerator is written as a fraction
     * whose fractional denominator is a dyadic (log2(x) == int) or triadic (log3(x) == int) rational.
     */
    FRACTIONAL,

    ;
}
