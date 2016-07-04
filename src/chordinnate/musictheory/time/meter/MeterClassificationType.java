package chordinnate.musictheory.time.meter;

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
     * Time signature numerator is odd.
     */
    ODD,

    /*
     * Time signature numerator IS divisible by 2 XOR 3.
     */
    PERFECT,

    /*
     * Time signature numerator IS NOT divisible by 2 AND NOT divisible by 3.
     */
    IMPERFECT,

    /*
     * Time signature numerator IS <= 5 AND (divisible by 2 OR divisible by 3).
     */
    SIMPLE,

    /*
     * Time signature numerator IS divisible by 6.
     */
    COMPOUND,

    /*
     * Includes simple (% 2) AND compound (% 3) stresses in the time signature.
     */
    COMPLEX,
    IRREGULAR,
    ASYMMETRICAL,

    /*
     * Time signature numerator is some form of floating point number.
     */
    FRACTIONAL,
    PARTIAL,

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
     * Meter changes more than once.
     */
    MIXED,

    /*
     * Meter alternates between EXACTLY two time signatures.
     */
    ALTERNATING,

    ;
}
