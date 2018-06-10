package chordinnate.model.musictheory.pitch.interval;

import chordinnate.model.musictheory.notation.Letter;
import chordinnate.model.musictheory.pitch.PitchClass;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * Created by Joseph on 8/16/16.
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 *             https://en.wikipedia.org/wiki/Interval_(music)
 *             http://method-behind-the-music.com/theory/intervals/
 *             http://www.musictheory.net/lessons/31
 *             http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 *             http://music.tutsplus.com/tutorials/music-theory-intervals-and-how-to-derive-them--audio-4559
 */
public enum Interval implements Invertible<Interval> {
    INTERVAL_d1("d1", "d1", "A1", -1, 0),
    INTERVAL_P1("P1", "P1", "P1", 0, 0),
    INTERVAL_A1("A1", "A1", "d1", 1, 0),
    INTERVAL_d2("d2", "d2", "A7", 0, 0),
    INTERVAL_m2("m2", "m2", "M7", 1, 0),
    INTERVAL_M2("M2", "M2", "m7", 2, 0),
    INTERVAL_A2("A2", "A2", "d7", 3, 0),
    INTERVAL_d3("d3", "d3", "A6", 2, 0),
    INTERVAL_m3("m3", "m3", "M6", 3, 0),
    INTERVAL_M3("M3", "M3", "m6", 4, 0),
    INTERVAL_A3("A3", "A3", "d6", 5, 0),
    INTERVAL_d4("d4", "d4", "A5", 4, 0),
    INTERVAL_P4("P4", "P4", "P5", 5, 0),
    INTERVAL_A4("A4", "A4", "d5", 6, 0),
    INTERVAL_d5("d5", "d5", "A4", 6, 0),
    INTERVAL_P5("P5", "P5", "P4", 7, 0),
    INTERVAL_A5("A5", "A5", "d4", 8, 0),
    INTERVAL_d6("d6", "d6", "A3", 7, 0),
    INTERVAL_m6("m6", "m6", "M3", 8, 0),
    INTERVAL_M6("M6", "M6", "m3", 9, 0),
    INTERVAL_A6("A6", "A6", "d3", 10, 0),
    INTERVAL_d7("d7", "d7", "A2", 9, 0),
    INTERVAL_m7("m7", "m7", "M2", 10, 0),
    INTERVAL_M7("M7", "M7", "m2", 11, 0),
    INTERVAL_A7("A7", "A7", "d2", 12, 0),

    INTERVAL_d8("d8", "d1", "A8", 11, 1),
    INTERVAL_P8("P8", "P1", "P8", 12, 1),
    INTERVAL_A8("A8", "A1", "d8", 13, 1),
    INTERVAL_d9("d9", "d2", "A14", 12, 1),
    INTERVAL_m9("m9", "m2", "M14", 13, 1),
    INTERVAL_M9("M9", "M2", "m14", 14, 1),
    INTERVAL_A9("A9", "A2", "d14", 15, 1),
    INTERVAL_d10("d10", "d3", "A13", 14, 1),
    INTERVAL_m10("m10", "m3", "M13", 15, 1),
    INTERVAL_M10("M10", "M3", "m13", 16, 1),
    INTERVAL_A10("A10", "A3", "d13", 17, 1),
    INTERVAL_d11("d11", "d4", "A12", 16, 1),
    INTERVAL_P11("P11", "P4", "P12", 17, 1),
    INTERVAL_A11("A11", "A4", "d12", 18, 1),
    INTERVAL_d12("d12", "d5", "A11", 18, 1),
    INTERVAL_P12("P12", "P5", "P11", 19, 1),
    INTERVAL_A12("A12", "A5", "d11", 20, 1),
    INTERVAL_d13("d13", "d6", "A10", 19, 1),
    INTERVAL_m13("m13", "m6", "M10", 20, 1),
    INTERVAL_M13("M13", "M6", "m10", 21, 1),
    INTERVAL_A13("A13", "A6", "d10", 22, 1),
    INTERVAL_d14("d14", "d7", "A9", 21, 1),
    INTERVAL_m14("m14", "m7", "M9", 22, 1),
    INTERVAL_M14("M14", "M7", "m9", 23, 1),
    INTERVAL_A14("A14", "A7", "d9", 24, 1),

    INTERVAL_d15("d15", "d1", "A15", 23, 2),
    INTERVAL_P15("P15", "P1", "P15", 24, 2),
    INTERVAL_A15("A15", "A1", "d15", 25, 2),
    INTERVAL_d16("d16", "d2", "A21", 24, 2),
    INTERVAL_m16("m16", "m2", "M21", 25, 2),
    INTERVAL_M16("M16", "M2", "m21", 26, 2),
    INTERVAL_A16("A16", "A2", "d21", 27, 2),
    INTERVAL_d17("d17", "d3", "A20", 26, 2),
    INTERVAL_m17("m17", "m3", "M20", 27, 2),
    INTERVAL_M17("M17", "M3", "m20", 28, 2),
    INTERVAL_A17("A17", "A3", "d20", 29, 2),
    INTERVAL_d18("d18", "d4", "A19", 28, 2),
    INTERVAL_P18("P18", "P4", "P19", 29, 2),
    INTERVAL_A18("A18", "A4", "d19", 30, 2),
    INTERVAL_d19("d19", "d5", "A18", 30, 2),
    INTERVAL_P19("P19", "P5", "P18", 31, 2),
    INTERVAL_A19("A19", "A5", "d18", 32, 2),
    INTERVAL_d20("d20", "d6", "A17", 31, 2),
    INTERVAL_m20("m20", "m6", "M17", 32, 2),
    INTERVAL_M20("M20", "M6", "m17", 33, 2),
    INTERVAL_A20("A20", "A6", "d17", 34, 2),
    INTERVAL_d21("d21", "d7", "A16", 33, 2),
    INTERVAL_m21("m21", "m7", "M16", 34, 2),
    INTERVAL_M21("M21", "M7", "m16", 35, 2),
    INTERVAL_A21("A21", "A7", "d16", 36, 2),

    INTERVAL_d22("d22", "d1", "A22", 35, 3),
    INTERVAL_P22("P22", "P1", "P22", 36, 3),
    INTERVAL_A22("A22", "A1", "d22", 37, 3),
    INTERVAL_d23("d23", "d2", "A28", 36, 3),
    INTERVAL_m23("m23", "m2", "M28", 37, 3),
    INTERVAL_M23("M23", "M2", "m28", 38, 3),
    INTERVAL_A23("A23", "A2", "d28", 39, 3),
    INTERVAL_d24("d24", "d3", "A27", 38, 3),
    INTERVAL_m24("m24", "m3", "M27", 39, 3),
    INTERVAL_M24("M24", "M3", "m27", 40, 3),
    INTERVAL_A24("A24", "A3", "d27", 41, 3),
    INTERVAL_d25("d25", "d4", "A26", 40, 3),
    INTERVAL_P25("P25", "P4", "P26", 41, 3),
    INTERVAL_A25("A25", "A4", "d26", 42, 3),
    INTERVAL_d26("d26", "d5", "A25", 42, 3),
    INTERVAL_P26("P26", "P5", "P25", 43, 3),
    INTERVAL_A26("A26", "A5", "d25", 44, 3),
    INTERVAL_d27("d27", "d6", "A24", 43, 3),
    INTERVAL_m27("m27", "m6", "M24", 44, 3),
    INTERVAL_M27("M27", "M6", "m24", 45, 3),
    INTERVAL_A27("A27", "A6", "d24", 46, 3),
    INTERVAL_d28("d28", "d7", "A23", 45, 3),
    INTERVAL_m28("m28", "m7", "M23", 46, 3),
    INTERVAL_M28("M28", "M7", "m23", 47, 3),
    INTERVAL_A28("A28", "A7", "d23", 48, 3),

    INTERVAL_d29("d29", "d1", "A29", 47, 4),
    INTERVAL_P29("P29", "P1", "P29", 48, 4),
    INTERVAL_A29("A29", "A1", "d29", 49, 4),
    INTERVAL_d30("d30", "d2", "A35", 48, 4),
    INTERVAL_m30("m30", "m2", "M35", 49, 4),
    INTERVAL_M30("M30", "M2", "m35", 50, 4),
    INTERVAL_A30("A30", "A2", "d35", 51, 4),
    INTERVAL_d31("d31", "d3", "A34", 50, 4),
    INTERVAL_m31("m31", "m3", "M34", 51, 4),
    INTERVAL_M31("M31", "M3", "m34", 52, 4),
    INTERVAL_A31("A31", "A3", "d34", 53, 4),
    INTERVAL_d32("d32", "d4", "A33", 52, 4),
    INTERVAL_P32("P32", "P4", "P33", 53, 4),
    INTERVAL_A32("A32", "A4", "d33", 54, 4),
    INTERVAL_d33("d33", "d5", "A32", 54, 4),
    INTERVAL_P33("P33", "P5", "P32", 55, 4),
    INTERVAL_A33("A33", "A5", "d32", 56, 4),
    INTERVAL_d34("d34", "d6", "A31", 55, 4),
    INTERVAL_m34("m34", "m6", "M31", 56, 4),
    INTERVAL_M34("M34", "M6", "m31", 57, 4),
    INTERVAL_A34("A34", "A6", "d31", 58, 4),
    INTERVAL_d35("d35", "d7", "A30", 57, 4),
    INTERVAL_m35("m35", "m7", "M30", 58, 4),
    INTERVAL_M35("M35", "M7", "m30", 59, 4),
    INTERVAL_A35("A35", "A7", "d30", 60, 4),

    INTERVAL_d36("d36", "d1", "A36", 59, 5),
    INTERVAL_P36("P36", "P1", "P36", 60, 5),
    INTERVAL_A36("A36", "A1", "d36", 61, 5),
    INTERVAL_d37("d37", "d2", "A42", 60, 5),
    INTERVAL_m37("m37", "m2", "M42", 61, 5),
    INTERVAL_M37("M37", "M2", "m42", 62, 5),
    INTERVAL_A37("A37", "A2", "d42", 63, 5),
    INTERVAL_d38("d38", "d3", "A41", 62, 5),
    INTERVAL_m38("m38", "m3", "M41", 63, 5),
    INTERVAL_M38("M38", "M3", "m41", 64, 5),
    INTERVAL_A38("A38", "A3", "d41", 65, 5),
    INTERVAL_d39("d39", "d4", "A40", 64, 5),
    INTERVAL_P39("P39", "P4", "P40", 65, 5),
    INTERVAL_A39("A39", "A4", "d40", 66, 5),
    INTERVAL_d40("d40", "d5", "A39", 66, 5),
    INTERVAL_P40("P40", "P5", "P39", 67, 5),
    INTERVAL_A40("A40", "A5", "d39", 68, 5),
    INTERVAL_d41("d41", "d6", "A38", 67, 5),
    INTERVAL_m41("m41", "m6", "M38", 68, 5),
    INTERVAL_M41("M41", "M6", "m38", 69, 5),
    INTERVAL_A41("A41", "A6", "d38", 70, 5),
    INTERVAL_d42("d42", "d7", "A37", 69, 5),
    INTERVAL_m42("m42", "m7", "M37", 70, 5),
    INTERVAL_M42("M42", "M7", "m37", 71, 5),
    INTERVAL_A42("A42", "A7", "d37", 72, 5),

    INTERVAL_d43("d43", "d1", "A43", 71, 6),
    INTERVAL_P43("P43", "P1", "P43", 72, 6),
    INTERVAL_A43("A43", "A1", "d43", 73, 6),
    INTERVAL_d44("d44", "d2", "A49", 72, 6),
    INTERVAL_m44("m44", "m2", "M49", 73, 6),
    INTERVAL_M44("M44", "M2", "m49", 74, 6),
    INTERVAL_A44("A44", "A2", "d49", 75, 6),
    INTERVAL_d45("d45", "d3", "A48", 74, 6),
    INTERVAL_m45("m45", "m3", "M48", 75, 6),
    INTERVAL_M45("M45", "M3", "m48", 76, 6),
    INTERVAL_A45("A45", "A3", "d48", 77, 6),
    INTERVAL_d46("d46", "d4", "A47", 76, 6),
    INTERVAL_P46("P46", "P4", "P47", 77, 6),
    INTERVAL_A46("A46", "A4", "d47", 78, 6),
    INTERVAL_d47("d47", "d5", "A46", 78, 6),
    INTERVAL_P47("P47", "P5", "P46", 79, 6),
    INTERVAL_A47("A47", "A5", "d46", 80, 6),
    INTERVAL_d48("d48", "d6", "A45", 79, 6),
    INTERVAL_m48("m48", "m6", "M45", 80, 6),
    INTERVAL_M48("M48", "M6", "m45", 81, 6),
    INTERVAL_A48("A48", "A6", "d45", 82, 6),
    INTERVAL_d49("d49", "d7", "A44", 81, 6),
    INTERVAL_m49("m49", "m7", "M44", 82, 6),
    INTERVAL_M49("M49", "M7", "m44", 83, 6),
    INTERVAL_A49("A49", "A7", "d44", 84, 6),

    INTERVAL_d50("d50", "d1", "A50", 83, 7),
    INTERVAL_P50("P50", "P1", "P50", 84, 7),
    INTERVAL_A50("A50", "A1", "d50", 85, 7),
    INTERVAL_d51("d51", "d2", "A56", 84, 7),
    INTERVAL_m51("m51", "m2", "M56", 85, 7),
    INTERVAL_M51("M51", "M2", "m56", 86, 7),
    INTERVAL_A51("A51", "A2", "d56", 87, 7),
    INTERVAL_d52("d52", "d3", "A55", 86, 7),
    INTERVAL_m52("m52", "m3", "M55", 87, 7),
    INTERVAL_M52("M52", "M3", "m55", 88, 7),
    INTERVAL_A52("A52", "A3", "d55", 89, 7),
    INTERVAL_d53("d53", "d4", "A54", 88, 7),
    INTERVAL_P53("P53", "P4", "P54", 89, 7),
    INTERVAL_A53("A53", "A4", "d54", 90, 7),
    INTERVAL_d54("d54", "d5", "A53", 90, 7),
    INTERVAL_P54("P54", "P5", "P53", 91, 7),
    INTERVAL_A54("A54", "A5", "d53", 92, 7),
    INTERVAL_d55("d55", "d6", "A52", 91, 7),
    INTERVAL_m55("m55", "m6", "M52", 92, 7),
    INTERVAL_M55("M55", "M6", "m52", 93, 7),
    INTERVAL_A55("A55", "A6", "d52", 94, 7),
    INTERVAL_d56("d56", "d7", "A51", 93, 7),
    INTERVAL_m56("m56", "m7", "M51", 94, 7),
    INTERVAL_M56("M56", "M7", "m51", 95, 7),
    INTERVAL_A56("A56", "A7", "d51", 96, 7),

    INTERVAL_d57("d57", "d1", "A57", 95, 8),
    INTERVAL_P57("P57", "P1", "P57", 96, 8),
    INTERVAL_A57("A57", "A1", "d57", 97, 8),
    INTERVAL_d58("d58", "d2", "A63", 96, 8),
    INTERVAL_m58("m58", "m2", "M63", 97, 8),
    INTERVAL_M58("M58", "M2", "m63", 98, 8),
    INTERVAL_A58("A58", "A2", "d63", 99, 8),
    INTERVAL_d59("d59", "d3", "A62", 98, 8),
    INTERVAL_m59("m59", "m3", "M62", 99, 8),
    INTERVAL_M59("M59", "M3", "m62", 100, 8),
    INTERVAL_A59("A59", "A3", "d62", 101, 8),
    INTERVAL_d60("d60", "d4", "A61", 100, 8),
    INTERVAL_P60("P60", "P4", "P61", 101, 8),
    INTERVAL_A60("A60", "A4", "d61", 102, 8),
    INTERVAL_d61("d61", "d5", "A60", 102, 8),
    INTERVAL_P61("P61", "P5", "P60", 103, 8),
    INTERVAL_A61("A61", "A5", "d60", 104, 8),
    INTERVAL_d62("d62", "d6", "A59", 103, 8),
    INTERVAL_m62("m62", "m6", "M59", 104, 8),
    INTERVAL_M62("M62", "M6", "m59", 105, 8),
    INTERVAL_A62("A62", "A6", "d59", 106, 8),
    INTERVAL_d63("d63", "d7", "A58", 105, 8),
    INTERVAL_m63("m63", "m7", "M58", 106, 8),
    INTERVAL_M63("M63", "M7", "m58", 107, 8),
    INTERVAL_A63("A63", "A7", "d58", 108, 8),

    INTERVAL_d64("d64", "d1", "A64", 107, 9),
    INTERVAL_P64("P64", "P1", "P64", 108, 9),
    INTERVAL_A64("A64", "A1", "d64", 109, 9),
    INTERVAL_d65("d65", "d2", "A70", 108, 9),
    INTERVAL_m65("m65", "m2", "M70", 109, 9),
    INTERVAL_M65("M65", "M2", "m70", 110, 9),
    INTERVAL_A65("A65", "A2", "d70", 111, 9),
    INTERVAL_d66("d66", "d3", "A69", 110, 9),
    INTERVAL_m66("m66", "m3", "M69", 111, 9),
    INTERVAL_M66("M66", "M3", "m69", 112, 9),
    INTERVAL_A66("A66", "A3", "d69", 113, 9),
    INTERVAL_d67("d67", "d4", "A68", 112, 9),
    INTERVAL_P67("P67", "P4", "P68", 113, 9),
    INTERVAL_A67("A67", "A4", "d68", 114, 9),
    INTERVAL_d68("d68", "d5", "A67", 114, 9),
    INTERVAL_P68("P68", "P5", "P67", 115, 9),
    INTERVAL_A68("A68", "A5", "d67", 116, 9),
    INTERVAL_d69("d69", "d6", "A66", 115, 9),
    INTERVAL_m69("m69", "m6", "M66", 116, 9),
    INTERVAL_M69("M69", "M6", "m66", 117, 9),
    INTERVAL_A69("A69", "A6", "d66", 118, 9),
    INTERVAL_d70("d70", "d7", "A65", 117, 9),
    INTERVAL_m70("m70", "m7", "M65", 118, 9),
    INTERVAL_M70("M70", "M7", "m65", 119, 9),
    INTERVAL_A70("A70", "A7", "d65", 120, 9),

    INTERVAL_d71("d71", "d1", "A71", 119, 10),
    INTERVAL_P71("P71", "P1", "P71", 120, 10),
    INTERVAL_A71("A71", "A1", "d71", 121, 10),
    INTERVAL_d72("d72", "d2", "A77", 120, 10), // no inversion possible
    INTERVAL_m72("m72", "m2", "M77", 121, 10), // no inversion possible
    INTERVAL_M72("M72", "M2", "m77", 122, 10), // no inversion possible
    INTERVAL_A72("A72", "A2", "d77", 123, 10), // no inversion possible
    INTERVAL_d73("d73", "d3", "A76", 122, 10), // no inversion possible
    INTERVAL_m73("m73", "m3", "M76", 123, 10), // no inversion possible
    INTERVAL_M73("M73", "M3", "m76", 124, 10),
    INTERVAL_A73("A73", "A3", "d76", 125, 10),
    INTERVAL_d74("d74", "d4", "A75", 124, 10),
    INTERVAL_P74("P74", "P4", "P75", 125, 10),
    INTERVAL_A74("A74", "A4", "d75", 126, 10),
    INTERVAL_d75("d75", "d5", "A74", 126, 10),
    INTERVAL_P75("P75", "P5", "P74", 127, 10),
    INTERVAL_A75("A75", "A5", "d74", 128, 10),
    INTERVAL_d76("d76", "d6", "A73", 127, 10),
    INTERVAL_m76("m76", "m6", "M73", 128, 10),
    ;

    private static final String FIRST_PASS_INTERVAL_REGEX = "^[dmMPA](([1-9])|([1-6][0-9])|(7[0-6]))$";
    private static final String SECOND_PASS_INTERVAL_REGEX = "^([mM][2367])|(P[145])|([dA][1-7])$";

    private static final Pattern FIRST_PASS_INTERVAL_PATTERN = Pattern.compile(FIRST_PASS_INTERVAL_REGEX);
    private static final Pattern SECOND_PASS_INTERVAL_PATTERN = Pattern.compile(SECOND_PASS_INTERVAL_REGEX);

    String COMPOUND_SHORT_NAME, SIMPLE_SHORT_NAME, INVERTED_COMPOUND_SHORT_NAME;
    int SEMITONES, OCTAVES;

    Interval(String compoundShortname,
             String simpleShortname,
             String invertedCompoundShortname,
             int semitones,
             int octaves) {

        this.COMPOUND_SHORT_NAME = compoundShortname;
        this.SIMPLE_SHORT_NAME = simpleShortname;
        this.INVERTED_COMPOUND_SHORT_NAME = invertedCompoundShortname;
        this.SEMITONES = semitones;
        this.OCTAVES = octaves;

    }

    public int getSemitones() {
        return SEMITONES;
    }

    public int getOctaves() {
        return OCTAVES;
    }

    public int getSimpleDiatonic() {
        return Integer.parseInt(SIMPLE_SHORT_NAME.substring(1));
    }

    public int getCompoundDiatonic() {
        return Integer.parseInt(COMPOUND_SHORT_NAME.substring(1));
    }

    public boolean isCompound() {
        return getCompoundDiatonic() >= 8;
    }

    /**
     * Helper method to retrieve the simple Interval between two PitchClasses, if possible.
     * @param semitoneDistance the distance in semitones for the target interval
     * @param letterDistance the distance of letters for the target interval
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    private static Interval determineInterval(int semitoneDistance, int letterDistance, boolean direction) {
        if (letterDistance == 0) {
            if (semitoneDistance == 0) return INTERVAL_P8;
            if (semitoneDistance == 1) return direction ? INTERVAL_A1 : INTERVAL_d8;
            if (semitoneDistance == 11) return direction ? INTERVAL_d8 : INTERVAL_A1;
        }
        if (semitoneDistance == 0) {
            if (letterDistance == 1) return direction ? INTERVAL_d2 : INTERVAL_A7;
            if (letterDistance == 6) return direction ? INTERVAL_A7 : INTERVAL_d2;
        }
        if (semitoneDistance == 1) {
            if (letterDistance == 1) return direction ? INTERVAL_m2 : INTERVAL_M7;
        }
        if (semitoneDistance == 2) {
            if (letterDistance == 1) return direction ? INTERVAL_M2 : INTERVAL_m7;
            if (letterDistance == 2) return direction ? INTERVAL_d3 : INTERVAL_A6;
        }
        if (semitoneDistance == 3) {
            if (letterDistance == 1) return direction ? INTERVAL_A2 : INTERVAL_d7;
            if (letterDistance == 2) return direction ? INTERVAL_m3 : INTERVAL_M6;
        }
        if (semitoneDistance == 4) {
            if (letterDistance == 2) return direction ? INTERVAL_M3 : INTERVAL_m6;
            if (letterDistance == 3) return direction ? INTERVAL_d4 : INTERVAL_A5;
        }
        if (semitoneDistance == 5) {
            if (letterDistance == 2) return direction ? INTERVAL_A3 : INTERVAL_d6;
            if (letterDistance == 3) return direction ? INTERVAL_P4 : INTERVAL_P5;
        }
        if (semitoneDistance == 6) {
            if (letterDistance == 3) return direction ? INTERVAL_A4 : INTERVAL_d5;
            if (letterDistance == 4) return direction ? INTERVAL_d5 : INTERVAL_A4;
        }
        if (semitoneDistance == 7) {
            if (letterDistance == 4) return direction ? INTERVAL_P5 : INTERVAL_P4;
            if (letterDistance == 5) return direction ? INTERVAL_d6 : INTERVAL_A3;
        }
        if (semitoneDistance == 8) {
            if (letterDistance == 4) return direction ? INTERVAL_A5 : INTERVAL_d4;
            if (letterDistance == 5) return direction ? INTERVAL_m6 : INTERVAL_M3;
        }
        if (semitoneDistance == 9) {
            if (letterDistance == 5) return direction ? INTERVAL_M6 : INTERVAL_m3;
            if (letterDistance == 6) return direction ? INTERVAL_d7 : INTERVAL_A2;
        }
        if (semitoneDistance == 10) {
            if (letterDistance == 5) return direction ? INTERVAL_A6 : INTERVAL_d3;
            if (letterDistance == 6) return direction ? INTERVAL_m7 : INTERVAL_M2;
        }
        if (semitoneDistance == 11) {
            if (letterDistance == 6) return direction ? INTERVAL_M7 : INTERVAL_m2;
        }
        throw new RuntimeException("Could not determine interval.");
    }

    /**
     * Retrieves the simple Interval between two PitchClasses, if possible.
     * @param begin the starting PitchClass
     * @param end the ending PitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    public static Interval getIntervalBetween(@NotNull PitchClass begin, @NotNull PitchClass end, boolean direction) {
        int semitoneDistance = PitchClass.getSemitoneDistanceBetween(begin, end);
        int letterDistance = (Letter.getVectorDistanceTo(begin.ENHARMONIC_SPELLING.LETTER, end.ENHARMONIC_SPELLING.LETTER)) % 7;
        try {
            return determineInterval(semitoneDistance, letterDistance, direction);
        } catch (RuntimeException ex) {
            throw new RuntimeException(
                    "Could not determine the "
                    + (direction ? "upward " : "downward ")
                    + "interval between pitch classes "
                    + begin.ENHARMONIC_SPELLING.NAME
                    + " and "
                    + end.ENHARMONIC_SPELLING.NAME
            );
        }
    }

    private static int getSimpleDiatonic(int compoundDiatonic) {
        int temp = compoundDiatonic;
        while (temp >= 8) {
            temp -= 7;
        }
        return temp;
    }

    /**
     * Retrieves the Interval specified by its compound short name.
     * @param compoundShortName the abbreviated name for the interval
     * @return an {@link Interval} matching the short name
     */
    public static Interval withShortName(String compoundShortName) {

        if (FIRST_PASS_INTERVAL_PATTERN.matcher(compoundShortName).matches()) {

            /*
             * It's not enough that we match the regex for an interval.
             * The interval must reduce to something that makes musical sense:
             * - Major / minor intervals must reduce to seconds, thirds, sixths, or sevenths
             * - Perfect intervals must reduce to unison / octaves, fourths, or fifths
             * - Diminished / augmented may be any of the above
             */
            String quality = compoundShortName.substring(0, 1);
            int compoundDiatonic = Integer.parseInt(compoundShortName.substring(1));
            int simpleDiatonic = getSimpleDiatonic(compoundDiatonic);

            String simpleShortName = quality + simpleDiatonic;

            if (SECOND_PASS_INTERVAL_PATTERN.matcher(simpleShortName).matches()) {
                return Interval.valueOf("INTERVAL_" + compoundShortName);
            }
        }

        throw new IllegalArgumentException("Invalid interval symbol provided: " + compoundShortName);
    }

    @Override
    public Interval invert() {
        switch (this) {
            case INTERVAL_d72:
            case INTERVAL_m72:
            case INTERVAL_M72:
            case INTERVAL_A72:
            case INTERVAL_d73:
            case INTERVAL_m73:
                throw new RuntimeException(
                        "Cannot invert interval ["
                                + COMPOUND_SHORT_NAME
                                + "]: the inversion ["
                                + INVERTED_COMPOUND_SHORT_NAME
                                + "] would be out of playable range"
                );
                // TODO - log and just return this?
            default: return Interval.valueOf("INTERVAL_" + INVERTED_COMPOUND_SHORT_NAME);
        }
    }
}
