package chordinnate.model.musictheory.pitch.interval;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.PitchClass;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class Interval implements Invertible<Interval> {
    public static final Interval
        d1 = new Interval("d1", "d1", "A1", -1, 0),
        P1 = new Interval("P1", "P1", "P1", 0, 0),
        A1 = new Interval("A1", "A1", "d1", 1, 0),
        d2 = new Interval("d2", "d2", "A7", 0, 0),
        m2 = new Interval("m2", "m2", "M7", 1, 0),
        M2 = new Interval("M2", "M2", "m7", 2, 0),
        A2 = new Interval("A2", "A2", "d7", 3, 0),
        d3 = new Interval("d3", "d3", "A6", 2, 0),
        m3 = new Interval("m3", "m3", "M6", 3, 0),
        M3 = new Interval("M3", "M3", "m6", 4, 0),
        A3 = new Interval("A3", "A3", "d6", 5, 0),
        d4 = new Interval("d4", "d4", "A5", 4, 0),
        P4 = new Interval("P4", "P4", "P5", 5, 0),
        A4 = new Interval("A4", "A4", "d5", 6, 0),
        d5 = new Interval("d5", "d5", "A4", 6, 0),
        P5 = new Interval("P5", "P5", "P4", 7, 0),
        A5 = new Interval("A5", "A5", "d4", 8, 0),
        d6 = new Interval("d6", "d6", "A3", 7, 0),
        m6 = new Interval("m6", "m6", "M3", 8, 0),
        M6 = new Interval("M6", "M6", "m3", 9, 0),
        A6 = new Interval("A6", "A6", "d3", 10, 0),
        d7 = new Interval("d7", "d7", "A2", 9, 0),
        m7 = new Interval("m7", "m7", "M2", 10, 0),
        M7 = new Interval("M7", "M7", "m2", 11, 0),
        A7 = new Interval("A7", "A7", "d2", 12, 0),

        d8 = new Interval("d8", "d1", "A8", 11, 1),
        P8 = new Interval("P8", "P1", "P8", 12, 1),
        A8 = new Interval("A8", "A1", "d8", 13, 1),
        d9 = new Interval("d9", "d2", "A14", 12, 1),
        m9 = new Interval("m9", "m2", "M14", 13, 1),
        M9 = new Interval("M9", "M2", "m14", 14, 1),
        A9 = new Interval("A9", "A2", "d14", 15, 1),
        d10 = new Interval("d10", "d3", "A13", 14, 1),
        m10 = new Interval("m10", "m3", "M13", 15, 1),
        M10 = new Interval("M10", "M3", "m13", 16, 1),
        A10 = new Interval("A10", "A3", "d13", 17, 1),
        d11 = new Interval("d11", "d4", "A12", 16, 1),
        P11 = new Interval("P11", "P4", "P12", 17, 1),
        A11 = new Interval("A11", "A4", "d12", 18, 1),
        d12 = new Interval("d12", "d5", "A11", 18, 1),
        P12 = new Interval("P12", "P5", "P11", 19, 1),
        A12 = new Interval("A12", "A5", "d11", 20, 1),
        d13 = new Interval("d13", "d6", "A10", 19, 1),
        m13 = new Interval("m13", "m6", "M10", 20, 1),
        M13 = new Interval("M13", "M6", "m10", 21, 1),
        A13 = new Interval("A13", "A6", "d10", 22, 1),
        d14 = new Interval("d14", "d7", "A9", 21, 1),
        m14 = new Interval("m14", "m7", "M9", 22, 1),
        M14 = new Interval("M14", "M7", "m9", 23, 1),
        A14 = new Interval("A14", "A7", "d9", 24, 1),

        d15 = new Interval("d15", "d1", "A15", 23, 2),
        P15 = new Interval("P15", "P1", "P15", 24, 2),
        A15 = new Interval("A15", "A1", "d15", 25, 2),
        d16 = new Interval("d16", "d2", "A21", 24, 2),
        m16 = new Interval("m16", "m2", "M21", 25, 2),
        M16 = new Interval("M16", "M2", "m21", 26, 2),
        A16 = new Interval("A16", "A2", "d21", 27, 2),
        d17 = new Interval("d17", "d3", "A20", 26, 2),
        m17 = new Interval("m17", "m3", "M20", 27, 2),
        M17 = new Interval("M17", "M3", "m20", 28, 2),
        A17 = new Interval("A17", "A3", "d20", 29, 2),
        d18 = new Interval("d18", "d4", "A19", 28, 2),
        P18 = new Interval("P18", "P4", "P19", 29, 2),
        A18 = new Interval("A18", "A4", "d19", 30, 2),
        d19 = new Interval("d19", "d5", "A18", 30, 2),
        P19 = new Interval("P19", "P5", "P18", 31, 2),
        A19 = new Interval("A19", "A5", "d18", 32, 2),
        d20 = new Interval("d20", "d6", "A17", 31, 2),
        m20 = new Interval("m20", "m6", "M17", 32, 2),
        M20 = new Interval("M20", "M6", "m17", 33, 2),
        A20 = new Interval("A20", "A6", "d17", 34, 2),
        d21 = new Interval("d21", "d7", "A16", 33, 2),
        m21 = new Interval("m21", "m7", "M16", 34, 2),
        M21 = new Interval("M21", "M7", "m16", 35, 2),
        A21 = new Interval("A21", "A7", "d16", 36, 2),

        d22 = new Interval("d22", "d1", "A22", 35, 3),
        P22 = new Interval("P22", "P1", "P22", 36, 3),
        A22 = new Interval("A22", "A1", "d22", 37, 3),
        d23 = new Interval("d23", "d2", "A28", 36, 3),
        m23 = new Interval("m23", "m2", "M28", 37, 3),
        M23 = new Interval("M23", "M2", "m28", 38, 3),
        A23 = new Interval("A23", "A2", "d28", 39, 3),
        d24 = new Interval("d24", "d3", "A27", 38, 3),
        m24 = new Interval("m24", "m3", "M27", 39, 3),
        M24 = new Interval("M24", "M3", "m27", 40, 3),
        A24 = new Interval("A24", "A3", "d27", 41, 3),
        d25 = new Interval("d25", "d4", "A26", 40, 3),
        P25 = new Interval("P25", "P4", "P26", 41, 3),
        A25 = new Interval("A25", "A4", "d26", 42, 3),
        d26 = new Interval("d26", "d5", "A25", 42, 3),
        P26 = new Interval("P26", "P5", "P25", 43, 3),
        A26 = new Interval("A26", "A5", "d25", 44, 3),
        d27 = new Interval("d27", "d6", "A24", 43, 3),
        m27 = new Interval("m27", "m6", "M24", 44, 3),
        M27 = new Interval("M27", "M6", "m24", 45, 3),
        A27 = new Interval("A27", "A6", "d24", 46, 3),
        d28 = new Interval("d28", "d7", "A23", 45, 3),
        m28 = new Interval("m28", "m7", "M23", 46, 3),
        M28 = new Interval("M28", "M7", "m23", 47, 3),
        A28 = new Interval("A28", "A7", "d23", 48, 3),

        d29 = new Interval("d29", "d1", "A29", 47, 4),
        P29 = new Interval("P29", "P1", "P29", 48, 4),
        A29 = new Interval("A29", "A1", "d29", 49, 4),
        d30 = new Interval("d30", "d2", "A35", 48, 4),
        m30 = new Interval("m30", "m2", "M35", 49, 4),
        M30 = new Interval("M30", "M2", "m35", 50, 4),
        A30 = new Interval("A30", "A2", "d35", 51, 4),
        d31 = new Interval("d31", "d3", "A34", 50, 4),
        m31 = new Interval("m31", "m3", "M34", 51, 4),
        M31 = new Interval("M31", "M3", "m34", 52, 4),
        A31 = new Interval("A31", "A3", "d34", 53, 4),
        d32 = new Interval("d32", "d4", "A33", 52, 4),
        P32 = new Interval("P32", "P4", "P33", 53, 4),
        A32 = new Interval("A32", "A4", "d33", 54, 4),
        d33 = new Interval("d33", "d5", "A32", 54, 4),
        P33 = new Interval("P33", "P5", "P32", 55, 4),
        A33 = new Interval("A33", "A5", "d32", 56, 4),
        d34 = new Interval("d34", "d6", "A31", 55, 4),
        m34 = new Interval("m34", "m6", "M31", 56, 4),
        M34 = new Interval("M34", "M6", "m31", 57, 4),
        A34 = new Interval("A34", "A6", "d31", 58, 4),
        d35 = new Interval("d35", "d7", "A30", 57, 4),
        m35 = new Interval("m35", "m7", "M30", 58, 4),
        M35 = new Interval("M35", "M7", "m30", 59, 4),
        A35 = new Interval("A35", "A7", "d30", 60, 4),

        d36 = new Interval("d36", "d1", "A36", 59, 5),
        P36 = new Interval("P36", "P1", "P36", 60, 5),
        A36 = new Interval("A36", "A1", "d36", 61, 5),
        d37 = new Interval("d37", "d2", "A42", 60, 5),
        m37 = new Interval("m37", "m2", "M42", 61, 5),
        M37 = new Interval("M37", "M2", "m42", 62, 5),
        A37 = new Interval("A37", "A2", "d42", 63, 5),
        d38 = new Interval("d38", "d3", "A41", 62, 5),
        m38 = new Interval("m38", "m3", "M41", 63, 5),
        M38 = new Interval("M38", "M3", "m41", 64, 5),
        A38 = new Interval("A38", "A3", "d41", 65, 5),
        d39 = new Interval("d39", "d4", "A40", 64, 5),
        P39 = new Interval("P39", "P4", "P40", 65, 5),
        A39 = new Interval("A39", "A4", "d40", 66, 5),
        d40 = new Interval("d40", "d5", "A39", 66, 5),
        P40 = new Interval("P40", "P5", "P39", 67, 5),
        A40 = new Interval("A40", "A5", "d39", 68, 5),
        d41 = new Interval("d41", "d6", "A38", 67, 5),
        m41 = new Interval("m41", "m6", "M38", 68, 5),
        M41 = new Interval("M41", "M6", "m38", 69, 5),
        A41 = new Interval("A41", "A6", "d38", 70, 5),
        d42 = new Interval("d42", "d7", "A37", 69, 5),
        m42 = new Interval("m42", "m7", "M37", 70, 5),
        M42 = new Interval("M42", "M7", "m37", 71, 5),
        A42 = new Interval("A42", "A7", "d37", 72, 5),

        d43 = new Interval("d43", "d1", "A43", 71, 6),
        P43 = new Interval("P43", "P1", "P43", 72, 6),
        A43 = new Interval("A43", "A1", "d43", 73, 6),
        d44 = new Interval("d44", "d2", "A49", 72, 6),
        m44 = new Interval("m44", "m2", "M49", 73, 6),
        M44 = new Interval("M44", "M2", "m49", 74, 6),
        A44 = new Interval("A44", "A2", "d49", 75, 6),
        d45 = new Interval("d45", "d3", "A48", 74, 6),
        m45 = new Interval("m45", "m3", "M48", 75, 6),
        M45 = new Interval("M45", "M3", "m48", 76, 6),
        A45 = new Interval("A45", "A3", "d48", 77, 6),
        d46 = new Interval("d46", "d4", "A47", 76, 6),
        P46 = new Interval("P46", "P4", "P47", 77, 6),
        A46 = new Interval("A46", "A4", "d47", 78, 6),
        d47 = new Interval("d47", "d5", "A46", 78, 6),
        P47 = new Interval("P47", "P5", "P46", 79, 6),
        A47 = new Interval("A47", "A5", "d46", 80, 6),
        d48 = new Interval("d48", "d6", "A45", 79, 6),
        m48 = new Interval("m48", "m6", "M45", 80, 6),
        M48 = new Interval("M48", "M6", "m45", 81, 6),
        A48 = new Interval("A48", "A6", "d45", 82, 6),
        d49 = new Interval("d49", "d7", "A44", 81, 6),
        m49 = new Interval("m49", "m7", "M44", 82, 6),
        M49 = new Interval("M49", "M7", "m44", 83, 6),
        A49 = new Interval("A49", "A7", "d44", 84, 6),

        d50 = new Interval("d50", "d1", "A50", 83, 7),
        P50 = new Interval("P50", "P1", "P50", 84, 7),
        A50 = new Interval("A50", "A1", "d50", 85, 7),
        d51 = new Interval("d51", "d2", "A56", 84, 7),
        m51 = new Interval("m51", "m2", "M56", 85, 7),
        M51 = new Interval("M51", "M2", "m56", 86, 7),
        A51 = new Interval("A51", "A2", "d56", 87, 7),
        d52 = new Interval("d52", "d3", "A55", 86, 7),
        m52 = new Interval("m52", "m3", "M55", 87, 7),
        M52 = new Interval("M52", "M3", "m55", 88, 7),
        A52 = new Interval("A52", "A3", "d55", 89, 7),
        d53 = new Interval("d53", "d4", "A54", 88, 7),
        P53 = new Interval("P53", "P4", "P54", 89, 7),
        A53 = new Interval("A53", "A4", "d54", 90, 7),
        d54 = new Interval("d54", "d5", "A53", 90, 7),
        P54 = new Interval("P54", "P5", "P53", 91, 7),
        A54 = new Interval("A54", "A5", "d53", 92, 7),
        d55 = new Interval("d55", "d6", "A52", 91, 7),
        m55 = new Interval("m55", "m6", "M52", 92, 7),
        M55 = new Interval("M55", "M6", "m52", 93, 7),
        A55 = new Interval("A55", "A6", "d52", 94, 7),
        d56 = new Interval("d56", "d7", "A51", 93, 7),
        m56 = new Interval("m56", "m7", "M51", 94, 7),
        M56 = new Interval("M56", "M7", "m51", 95, 7),
        A56 = new Interval("A56", "A7", "d51", 96, 7),

        d57 = new Interval("d57", "d1", "A57", 95, 8),
        P57 = new Interval("P57", "P1", "P57", 96, 8),
        A57 = new Interval("A57", "A1", "d57", 97, 8),
        d58 = new Interval("d58", "d2", "A63", 96, 8),
        m58 = new Interval("m58", "m2", "M63", 97, 8),
        M58 = new Interval("M58", "M2", "m63", 98, 8),
        A58 = new Interval("A58", "A2", "d63", 99, 8),
        d59 = new Interval("d59", "d3", "A62", 98, 8),
        m59 = new Interval("m59", "m3", "M62", 99, 8),
        M59 = new Interval("M59", "M3", "m62", 100, 8),
        A59 = new Interval("A59", "A3", "d62", 101, 8),
        d60 = new Interval("d60", "d4", "A61", 100, 8),
        P60 = new Interval("P60", "P4", "P61", 101, 8),
        A60 = new Interval("A60", "A4", "d61", 102, 8),
        d61 = new Interval("d61", "d5", "A60", 102, 8),
        P61 = new Interval("P61", "P5", "P60", 103, 8),
        A61 = new Interval("A61", "A5", "d60", 104, 8),
        d62 = new Interval("d62", "d6", "A59", 103, 8),
        m62 = new Interval("m62", "m6", "M59", 104, 8),
        M62 = new Interval("M62", "M6", "m59", 105, 8),
        A62 = new Interval("A62", "A6", "d59", 106, 8),
        d63 = new Interval("d63", "d7", "A58", 105, 8),
        m63 = new Interval("m63", "m7", "M58", 106, 8),
        M63 = new Interval("M63", "M7", "m58", 107, 8),
        A63 = new Interval("A63", "A7", "d58", 108, 8),

        d64 = new Interval("d64", "d1", "A64", 107, 9),
        P64 = new Interval("P64", "P1", "P64", 108, 9),
        A64 = new Interval("A64", "A1", "d64", 109, 9),
        d65 = new Interval("d65", "d2", "A70", 108, 9),
        m65 = new Interval("m65", "m2", "M70", 109, 9),
        M65 = new Interval("M65", "M2", "m70", 110, 9),
        A65 = new Interval("A65", "A2", "d70", 111, 9),
        d66 = new Interval("d66", "d3", "A69", 110, 9),
        m66 = new Interval("m66", "m3", "M69", 111, 9),
        M66 = new Interval("M66", "M3", "m69", 112, 9),
        A66 = new Interval("A66", "A3", "d69", 113, 9),
        d67 = new Interval("d67", "d4", "A68", 112, 9),
        P67 = new Interval("P67", "P4", "P68", 113, 9),
        A67 = new Interval("A67", "A4", "d68", 114, 9),
        d68 = new Interval("d68", "d5", "A67", 114, 9),
        P68 = new Interval("P68", "P5", "P67", 115, 9),
        A68 = new Interval("A68", "A5", "d67", 116, 9),
        d69 = new Interval("d69", "d6", "A66", 115, 9),
        m69 = new Interval("m69", "m6", "M66", 116, 9),
        M69 = new Interval("M69", "M6", "m66", 117, 9),
        A69 = new Interval("A69", "A6", "d66", 118, 9),
        d70 = new Interval("d70", "d7", "A65", 117, 9),
        m70 = new Interval("m70", "m7", "M65", 118, 9),
        M70 = new Interval("M70", "M7", "m65", 119, 9),
        A70 = new Interval("A70", "A7", "d65", 120, 9),

        d71 = new Interval("d71", "d1", "A71", 119, 10),
        P71 = new Interval("P71", "P1", "P71", 120, 10),
        A71 = new Interval("A71", "A1", "d71", 121, 10),
        d72 = new Interval("d72", "d2", "A77", 120, 10), // no inversion possible
        m72 = new Interval("m72", "m2", "M77", 121, 10), // no inversion possible
        M72 = new Interval("M72", "M2", "m77", 122, 10), // no inversion possible
        A72 = new Interval("A72", "A2", "d77", 123, 10), // no inversion possible
        d73 = new Interval("d73", "d3", "A76", 122, 10), // no inversion possible
        m73 = new Interval("m73", "m3", "M76", 123, 10), // no inversion possible
        M73 = new Interval("M73", "M3", "m76", 124, 10), // no inversion possible
        A73 = new Interval("A73", "A3", "d76", 125, 10),
        d74 = new Interval("d74", "d4", "A75", 124, 10), // no inversion possible
        P74 = new Interval("P74", "P4", "P75", 125, 10),
        A74 = new Interval("A74", "A4", "d75", 126, 10),
        d75 = new Interval("d75", "d5", "A74", 126, 10),
        P75 = new Interval("P75", "P5", "P74", 127, 10),
        d76 = new Interval("d76", "d6", "A73", 127, 10);

    private static final String FIRST_PASS_INTERVAL_REGEX = "^[dmMPA]+(([1-9])|([1-6][0-9])|(7[0-6]))$";
    private static final String SECOND_PASS_INTERVAL_REGEX = "^([mM][2367])|(P[145])|((d+|A+)[1-7])$";

    private static final Pattern FIRST_PASS_INTERVAL_PATTERN = Pattern.compile(FIRST_PASS_INTERVAL_REGEX);
    private static final Pattern SECOND_PASS_INTERVAL_PATTERN = Pattern.compile(SECOND_PASS_INTERVAL_REGEX);

    private static final Map<String, Interval> STANDARD_INTERVAL_LOOKUP = Collections.unmodifiableMap(initLookupMap());

    private static Map<String, Interval> initLookupMap() {
        Map<String, Interval> map = new HashMap<>();

        try {
            for (Field field : Class.forName(Interval.class.getName()).getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Interval.class)) {
                    Interval toAdd = (Interval) field.get(Class.forName(Interval.class.getName()));
                    map.put(toAdd.COMPOUND_SHORT_NAME, toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }

    private static final Map<String, Interval> INTERVAL_BETWEEN = new HashMap<String, Interval>() {{
        put("CC", Interval.P1);
        put("DD", Interval.P1);
        put("EE", Interval.P1);
        put("FF", Interval.P1);
        put("GG", Interval.P1);
        put("AA", Interval.P1);
        put("BB", Interval.P1);

        put("CD", Interval.M2);
        put("DE", Interval.M2);
        put("EF", Interval.m2);
        put("FG", Interval.M2);
        put("GA", Interval.M2);
        put("AB", Interval.M2);
        put("BC", Interval.m2);
        put("DC", Interval.m7);
        put("ED", Interval.m7);
        put("FE", Interval.M7);
        put("GF", Interval.m7);
        put("AG", Interval.m7);
        put("BA", Interval.m7);
        put("CB", Interval.M7);

        put("CE", Interval.M3);
        put("DF", Interval.m3);
        put("EG", Interval.m3);
        put("FA", Interval.M3);
        put("GB", Interval.M3);
        put("AC", Interval.m3);
        put("BD", Interval.m3);
        put("EC", Interval.m6);
        put("FD", Interval.M6);
        put("GE", Interval.M6);
        put("AF", Interval.m6);
        put("BG", Interval.m6);
        put("CA", Interval.M6);
        put("DB", Interval.M6);

        put("CF", Interval.P4);
        put("DG", Interval.P4);
        put("EA", Interval.P4);
        put("FB", Interval.A4);
        put("GC", Interval.P4);
        put("AD", Interval.P4);
        put("BE", Interval.P4);
        put("FC", Interval.P5);
        put("GD", Interval.P5);
        put("AE", Interval.P5);
        put("BF", Interval.d5);
        put("CG", Interval.P5);
        put("DA", Interval.P5);
        put("EB", Interval.P5);
    }};

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
        return Integer.parseInt(SIMPLE_SHORT_NAME.replaceAll("\\D", ""));
    }

    public int getCompoundDiatonic() {
        return Integer.parseInt(COMPOUND_SHORT_NAME.replaceAll("\\D", ""));
    }

    public boolean isCompound() {
        return getCompoundDiatonic() >= 8;
    }

    /**
     * Helper method to retrieve the simple Interval between two PitchClasses, if possible.
     * @param begin the starting PitchClass
     * @param end the ending PitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    private static Interval determineIntervalBetween(PitchClass begin, PitchClass end, boolean direction) {

        String beginName = begin.getName(), endName = end.getName();
        String beginLetter = String.valueOf(beginName.charAt(0)), endLetter = String.valueOf(endName.charAt(0));
        String beginAccidentals = (beginName.length() > 1 ? beginName.substring(1) : ""),
                endAccidentals = (endName.length() > 1 ? endName.substring(1) : "");

        /*
         * 1. Simplify and cancel out accidentals on each side,
         * until 'begin' is natural (contains no accidentals).
         */
        beginAccidentals = Accidental.simplify(beginAccidentals, false, true);
        endAccidentals = Accidental.simplify(endAccidentals, false, true);
        while (!beginAccidentals.isEmpty()) {
            if (beginAccidentals.startsWith("b")) {
                endAccidentals += "#";
            } else if (beginAccidentals.startsWith("#")) {
                endAccidentals += "b";
            } else if (beginAccidentals.startsWith("x")) {
                endAccidentals += "bb";
            }

            beginAccidentals = beginAccidentals.replaceFirst("[b#x]", "");
        }
        endAccidentals = Accidental.simplify(endAccidentals, false, true);
        PitchClass newBegin = PitchClass.withName(beginLetter + beginAccidentals, false);
        PitchClass newEnd = PitchClass.withName(endLetter + endAccidentals, false);

        /*
         * 2. Count semitones between the two resulting pitch classes.
         *
         * If going UP: count LEFT TO RIGHT from 'begin' to 'end'
         * If going DOWN: count RIGHT TO LEFT from 'begin' to 'end'.
         */
        int semitonesBetween;
        if (direction) {
            semitonesBetween = PitchClass.getSemitoneDistanceBetween(newBegin, newEnd);
        } else {
            semitonesBetween = PitchClass.getSemitoneDistanceBetween(newEnd, newBegin);
        }

        /*
         * 3. Lookup the "generalized" starting interval from hashmap
         * by combining letters of the two pitch classes.
         */
        String key = direction ? (beginLetter + endLetter) : (endLetter + beginLetter);
        Interval candidate =  INTERVAL_BETWEEN.get(key);

        /*
         * 4. Compare the semitones gathered from step 2,
         * and the semitones on the interval from step 3.
         *
         * If they match, return the interval from step 3.
         * Otherwise, proceed to step 5.
         */
        if (semitonesBetween == candidate.SEMITONES) {
            return candidate;
        }

        /*
         * 5. Continue augmenting / diminishing the interval from step 3
         * and cancelling out the remaining accidentals of 'end'
         * until 'end' is natural.
         *
         * If going UP:
         *      If 'end' is FLAT: diminish
         *      If 'end' is SHARP: augment
         * If going DOWN:
         *      If 'end' is FLAT: augment
         *      If 'end' is SHARP: diminish
         */
        return applyAccidental(candidate, endAccidentals, direction);
    }

    private static Interval applyAccidental(Interval candidate, String accidentals, boolean direction) {
        String candidateQuality = String.valueOf(candidate.SIMPLE_SHORT_NAME.charAt(0));
        int simpleDiatonic = candidate.getSimpleDiatonic();

        boolean isOneFourFive = simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5;

        for (char c : accidentals.toCharArray()) {

            if (direction) {
                candidateQuality = applyAccidentalHelper(candidateQuality, c, isOneFourFive, "d", "m", "P", "M", "A");
            } else {
                candidateQuality = applyAccidentalHelper(candidateQuality, c, isOneFourFive, "A", "M", "P", "m", "d");
            }
        }

        return Interval.withShortName(candidateQuality + simpleDiatonic);
    }
    
    private static String applyAccidentalHelper(String toReturn,
                                                char c,
                                                boolean isOneFourFive,
                                                String s1,
                                                String s2,
                                                String s3,
                                                String s4,
                                                String s5) {

        if (c == 'b') {
            if (toReturn.startsWith(s1)) {
                toReturn += s1;
            } else if (s2.equals(toReturn) || s3.equals(toReturn)) {
                toReturn = s1;
            } else if (s4.equals(toReturn)) {
                toReturn = s2;
            } else if (toReturn.startsWith(s5)) {
                if (toReturn.length() > 1) {
                    toReturn = toReturn.substring(0, toReturn.length() - 1);
                } else {
                    toReturn = isOneFourFive ? s3 : s4;
                }
            }
        } else if (c == '#') {
            if (toReturn.startsWith(s1)) {
                if (toReturn.length() > 1) {
                    toReturn = toReturn.substring(0, toReturn.length() - 1);
                } else {
                    toReturn = isOneFourFive ? s3 : s2;
                }
            } else if (s2.equals(toReturn)) {
                toReturn = s4;
            } else if (s4.equals(toReturn) || s3.equals(toReturn)) {
                toReturn = s5;
            } else if (toReturn.startsWith(s5)) {
                toReturn += s5;
            }
        } else if (c == 'x') {
            if (toReturn.startsWith(s1)) {
                if (toReturn.length() > 2) {
                    toReturn = toReturn.substring(0, toReturn.length() - 2);
                } else if (toReturn.length() > 1) {
                    toReturn = toReturn.substring(0, toReturn.length() - 1);
                } else {
                    toReturn = isOneFourFive ? s3 : s2;
                }
            } else if (s2.equals(toReturn)) {
                toReturn = s4;
            } else if (s4.equals(toReturn) || s3.equals(toReturn)) {
                toReturn = s5;
            } else if (toReturn.startsWith(s5)) {
                toReturn += s5;
            }
        }

        return toReturn;
    }

    /**
     * Retrieves the simple Interval between two PitchClasses, if possible.
     * @param begin the starting BasePitchClass
     * @param end the ending BasePitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    public static Interval getIntervalBetween(@NotNull PitchClass begin, @NotNull PitchClass end, boolean direction) {
        try {
            return determineIntervalBetween(begin, end, direction);
        } catch (RuntimeException ex) {
            throw new RuntimeException(
                    "Could not determine the "
                    + (direction ? "upward " : "downward ")
                    + "interval between pitch classes "
                    + begin.getName()
                    + " and "
                    + end.getName()
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

    public boolean isDiminished() {
        return isMatchingQuality("d", SIMPLE_SHORT_NAME);
    }

    public boolean isAugmented() {
        return isMatchingQuality("A", SIMPLE_SHORT_NAME);
    }

    public boolean isPerfect() {
        return isMatchingQuality("P", SIMPLE_SHORT_NAME);
    }

    public boolean isMajor() {
        return isMatchingQuality("M", SIMPLE_SHORT_NAME);
    }

    public boolean isMinor() {
        return isMatchingQuality("m", SIMPLE_SHORT_NAME);
    }

    private static boolean isMatchingQuality(String quality, String shortName) {
        return shortName.startsWith(quality);
    }

    public String getCompoundShortName() {
        return COMPOUND_SHORT_NAME;
    }

    public String getSimpleShortName() {
        return SIMPLE_SHORT_NAME;
    }

    public String getAbbreviatedQuality() {
        return SIMPLE_SHORT_NAME.substring(0, 1);
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
            String quality = compoundShortName.replaceAll("\\d", "");
            int compoundDiatonic = Integer.parseInt(compoundShortName.replaceAll("\\D", ""));
            int simpleDiatonic = getSimpleDiatonic(compoundDiatonic);

            String simpleShortName = quality + simpleDiatonic;

            if (SECOND_PASS_INTERVAL_PATTERN.matcher(simpleShortName).matches()) {

                Interval cached = STANDARD_INTERVAL_LOOKUP.get(compoundShortName);
                if (cached != null) {
                    return cached;
                }

                // TODO: compute the midi value from compoundShortName. If < 0 || > 127, fail.

                String temp = simpleShortName.charAt(0) + simpleShortName.replaceAll("\\D", "");
                cached = STANDARD_INTERVAL_LOOKUP.get(temp);

                int diff = simpleShortName.length() - temp.length();
                int total = 0;
                if (diff > 0) {
                    String q = String.valueOf(simpleShortName.charAt(0));
                    int modifier;
                    if (isMatchingQuality("d", q)) {
                        modifier = -1;
                    } else if (isMatchingQuality("A", q)) {
                        modifier = 1;
                    } else {
                        modifier = 0;
                    }

                    total = cached.getSemitones() + IntStream.range(0, diff).flatMap(i -> IntStream.of(modifier)).sum();
                }

                String invertedCompoundShortName = determineInvertedCompoundShortName(quality, compoundDiatonic, simpleDiatonic);

                return new Interval(compoundShortName, simpleShortName, invertedCompoundShortName, total, cached.OCTAVES);
            }
        }

        throw new IllegalArgumentException("Invalid interval symbol provided: " + compoundShortName);
    }

    private static String determineInvertedCompoundShortName(String quality, int compoundDiatonic, int simpleDiatonic) {
        final String q;
        if (isMatchingQuality("d", quality)) {
            q = "A";
        } else if (isMatchingQuality("A", quality)) {
            q = "d";
        } else {
            q = quality;
        }

        // Add as many d's / A's to the inversion as there are in the uninverted one.
        String inversion = IntStream.range(0, quality.length())
                .mapToObj(i -> q)
                .collect(Collectors.joining(""));

        // Figure out the distance required to get the diatonic of the inversion (i.e.: 1 <-> 1, 2 <-> 7, 3 <-> 6, 4 <-> 5)
        int modifier = 9 - (2 * simpleDiatonic);

        inversion += (compoundDiatonic + modifier);

        return inversion;
    }

    @Override
    public Interval invert() {
        if (this.equals(d72)
                || this.equals(m72)
                || this.equals(M72)
                || this.equals(A72)
                || this.equals(d73)
                || this.equals(m73)
                || this.equals(M73)
                || this.equals(d74)) {
            throw new RuntimeException(
                    "Cannot invert interval ["
                            + COMPOUND_SHORT_NAME
                            + "]: the inversion ["
                            + INVERTED_COMPOUND_SHORT_NAME
                            + "] would be out of playable range"
            );
        }

        // TODO - log and just return this?
        return Interval.withShortName(INVERTED_COMPOUND_SHORT_NAME);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !other.getClass().isAssignableFrom(Interval.class)) {
            return false;
        }

        Interval comparison = (Interval) other;

        return comparison.COMPOUND_SHORT_NAME.equals(this.COMPOUND_SHORT_NAME)
                && comparison.SIMPLE_SHORT_NAME.equals(this.SIMPLE_SHORT_NAME)
                && comparison.INVERTED_COMPOUND_SHORT_NAME.equals(this.INVERTED_COMPOUND_SHORT_NAME)
                && comparison.OCTAVES == this.OCTAVES
                && comparison.SEMITONES == this.SEMITONES;
    }
}
