package chordinnate.musictheory;

/**
 * Created by Joseph on 7/1/16.
 *
 * References: http://www.midnightmusic.net/MusicTheory/advanced/UnusualTimeSigs.html
 *             https://en.wikipedia.org/wiki/Time_signature
 *             http://donrathjr.com/compound-time-signatures/
 */
public enum MeterProperty {
    /*
     * No time signature is used.
     */
    FREE,

    /*
     * Time signature numerator is odd.
     */
    ODD,

    /*
     * Time signature numerator IS divisible by 2 OR 3.
     */
    PERFECT,

    /*
     * Time signature numerator IS NOT divisible by 2 AND NOT divisible by 3.
     */
    IMPERFECT,

    /*
     * Time signature numerator IS divisible by 2 AND NOT divisible by 3.
     */
    SIMPLE,

    /*
     * Time signature numerator IS divisible by 3 AND NOT divisible by 2.
     */
    COMPOUND,

    /*
     * Includes simple (% 2) AND compound (% 3) beats in the time signature.
     * This implies that the time signature numerator is AT LEAST 5,
     * AND NOT divisible by 2 AND NOT divisible by 3.
     */
    COMPLEX,
    IRREGULAR,
    ASYMMETRICAL,

    /*
     * Time signature numerator is rational BUT NOT an integer.
     */
    FRACTIONAL,

    /*
     * Time signature denominator is NOT a dyadic rational (i.e., a power of 2).
     */
    IRRATIONAL,

    /*
     * Time signature numerator is indicated with a stress pattern for the meter.
     * Example: (3 + 2 + 3)/8 --> 8 beats per measure, 8th note gets the beat. Stress pattern = 3-2-3
     */
    ADDITIVE,

    /*
     * Meter changes time signature on each bar OR nearly each bar.
     */
    MIXED,

    /*
     * Meter alternates between EXACTLY two time signatures.
     */
    ALTERNATING,

    ;
}
