package chordinnate.model.musictheory.pitch.interval;

import chordinnate.ChordInnateException;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.PitchClass;
import lombok.extern.slf4j.Slf4j;
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
 * http://musictheoryblog.blogspot.com/2007/02/roman-numeral-chord-notation.html
 * https://en.wikipedia.org/wiki/Interval_(music)
 * http://method-behind-the-music.com/theory/intervals/
 * http://www.musictheory.net/lessons/31
 * http://musictheory.alcorn.edu/Version2/theory1/interval.htm
 * http://music.tutsplus.com/tutorials/music-theory-intervals-and-how-to-derive-them--audio-4559
 */
@Slf4j
public class Interval implements Invertible<Interval> {
    public static final Interval DIMINISHED_1 = new Interval("d1", "d1", "A1", -1, 0);
    public static final Interval PERFECT_1 = new Interval("P1", "P1", "P1", 0, 0);
    public static final Interval AUGMENTED_1 = new Interval("A1", "A1", "d1", 1, 0);
    public static final Interval DIMINISHED_2 = new Interval("d2", "d2", "A7", 0, 0);
    public static final Interval MINOR_2 = new Interval("m2", "m2", "M7", 1, 0);
    public static final Interval MAJOR_2 = new Interval("M2", "M2", "m7", 2, 0);
    public static final Interval AUGMENTED_2 = new Interval("A2", "A2", "d7", 3, 0);
    public static final Interval DIMINISHED_3 = new Interval("d3", "d3", "A6", 2, 0);
    public static final Interval MINOR_3 = new Interval("m3", "m3", "M6", 3, 0);
    public static final Interval MAJOR_3 = new Interval("M3", "M3", "m6", 4, 0);
    public static final Interval AUGMENTED_3 = new Interval("A3", "A3", "d6", 5, 0);
    public static final Interval DIMINISHED_4 = new Interval("d4", "d4", "A5", 4, 0);
    public static final Interval PERFECT_4 = new Interval("P4", "P4", "P5", 5, 0);
    public static final Interval AUGMENTED_4 = new Interval("A4", "A4", "d5", 6, 0);
    public static final Interval DIMINISHED_5 = new Interval("d5", "d5", "A4", 6, 0);
    public static final Interval PERFECT_5 = new Interval("P5", "P5", "P4", 7, 0);
    public static final Interval AUGMENTED_5 = new Interval("A5", "A5", "d4", 8, 0);
    public static final Interval DIMINISHED_6 = new Interval("d6", "d6", "A3", 7, 0);
    public static final Interval MINOR_6 = new Interval("m6", "m6", "M3", 8, 0);
    public static final Interval MAJOR_6 = new Interval("M6", "M6", "m3", 9, 0);
    public static final Interval AUGMENTED_6 = new Interval("A6", "A6", "d3", 10, 0);
    public static final Interval DIMINISHED_7 = new Interval("d7", "d7", "A2", 9, 0);
    public static final Interval MINOR_7 = new Interval("m7", "m7", "M2", 10, 0);
    public static final Interval MAJOR_7 = new Interval("M7", "M7", "m2", 11, 0);
    public static final Interval AUGMENTED_7 = new Interval("A7", "A7", "d2", 12, 0);

    public static final Interval DIMINISHED_8 = new Interval("d8", "d1", "A8", 11, 1);
    public static final Interval PERFECT_8 = new Interval("P8", "P1", "P8", 12, 1);
    public static final Interval AUGMENTED_8 = new Interval("A8", "A1", "d8", 13, 1);
    public static final Interval DIMINISHED_9 = new Interval("d9", "d2", "A14", 12, 1);
    public static final Interval MINOR_9 = new Interval("m9", "m2", "M14", 13, 1);
    public static final Interval MAJOR_9 = new Interval("M9", "M2", "m14", 14, 1);
    public static final Interval AUGMENTED_9 = new Interval("A9", "A2", "d14", 15, 1);
    public static final Interval DIMINISHED_10 = new Interval("d10", "d3", "A13", 14, 1);
    public static final Interval MINOR_10 = new Interval("m10", "m3", "M13", 15, 1);
    public static final Interval MAJOR_10 = new Interval("M10", "M3", "m13", 16, 1);
    public static final Interval AUGMENTED_10 = new Interval("A10", "A3", "d13", 17, 1);
    public static final Interval DIMINISHED_11 = new Interval("d11", "d4", "A12", 16, 1);
    public static final Interval PERFECT_11 = new Interval("P11", "P4", "P12", 17, 1);
    public static final Interval AUGMENTED_11 = new Interval("A11", "A4", "d12", 18, 1);
    public static final Interval DIMINISHED_12 = new Interval("d12", "d5", "A11", 18, 1);
    public static final Interval PERFECT_12 = new Interval("P12", "P5", "P11", 19, 1);
    public static final Interval AUGMENTED_12 = new Interval("A12", "A5", "d11", 20, 1);
    public static final Interval DIMINISHED_13 = new Interval("d13", "d6", "A10", 19, 1);
    public static final Interval MINOR_13 = new Interval("m13", "m6", "M10", 20, 1);
    public static final Interval MAJOR_13 = new Interval("M13", "M6", "m10", 21, 1);
    public static final Interval AUGMENTED_13 = new Interval("A13", "A6", "d10", 22, 1);
    public static final Interval DIMINISHED_14 = new Interval("d14", "d7", "A9", 21, 1);
    public static final Interval MINOR_14 = new Interval("m14", "m7", "M9", 22, 1);
    public static final Interval MAJOR_14 = new Interval("M14", "M7", "m9", 23, 1);
    public static final Interval AUGMENTED_14 = new Interval("A14", "A7", "d9", 24, 1);

    public static final Interval DIMINISHED_15 = new Interval("d15", "d1", "A15", 23, 2);
    public static final Interval PERFECT_15 = new Interval("P15", "P1", "P15", 24, 2);
    public static final Interval AUGMENTED_15 = new Interval("A15", "A1", "d15", 25, 2);
    public static final Interval DIMINISHED_16 = new Interval("d16", "d2", "A21", 24, 2);
    public static final Interval MINOR_16 = new Interval("m16", "m2", "M21", 25, 2);
    public static final Interval MAJOR_16 = new Interval("M16", "M2", "m21", 26, 2);
    public static final Interval AUGMENTED_16 = new Interval("A16", "A2", "d21", 27, 2);
    public static final Interval DIMINISHED_17 = new Interval("d17", "d3", "A20", 26, 2);
    public static final Interval MINOR_17 = new Interval("m17", "m3", "M20", 27, 2);
    public static final Interval MAJOR_17 = new Interval("M17", "M3", "m20", 28, 2);
    public static final Interval AUGMENTED_17 = new Interval("A17", "A3", "d20", 29, 2);
    public static final Interval DIMINISHED_18 = new Interval("d18", "d4", "A19", 28, 2);
    public static final Interval PERFECT_18 = new Interval("P18", "P4", "P19", 29, 2);
    public static final Interval AUGMENTED_18 = new Interval("A18", "A4", "d19", 30, 2);
    public static final Interval DIMINISHED_19 = new Interval("d19", "d5", "A18", 30, 2);
    public static final Interval PERFECT_19 = new Interval("P19", "P5", "P18", 31, 2);
    public static final Interval AUGMENTED_19 = new Interval("A19", "A5", "d18", 32, 2);
    public static final Interval DIMINISHED_20 = new Interval("d20", "d6", "A17", 31, 2);
    public static final Interval MINOR_20 = new Interval("m20", "m6", "M17", 32, 2);
    public static final Interval MAJOR_20 = new Interval("M20", "M6", "m17", 33, 2);
    public static final Interval AUGMENTED_20 = new Interval("A20", "A6", "d17", 34, 2);
    public static final Interval DIMINISHED_21 = new Interval("d21", "d7", "A16", 33, 2);
    public static final Interval MINOR_21 = new Interval("m21", "m7", "M16", 34, 2);
    public static final Interval MAJOR_21 = new Interval("M21", "M7", "m16", 35, 2);
    public static final Interval AUGMENTED_21 = new Interval("A21", "A7", "d16", 36, 2);

    public static final Interval DIMINISHED_22 = new Interval("d22", "d1", "A22", 35, 3);
    public static final Interval PERFECT_22 = new Interval("P22", "P1", "P22", 36, 3);
    public static final Interval AUGMENTED_22 = new Interval("A22", "A1", "d22", 37, 3);
    public static final Interval DIMINISHED_23 = new Interval("d23", "d2", "A28", 36, 3);
    public static final Interval MINOR_23 = new Interval("m23", "m2", "M28", 37, 3);
    public static final Interval MAJOR_23 = new Interval("M23", "M2", "m28", 38, 3);
    public static final Interval AUGMENTED_23 = new Interval("A23", "A2", "d28", 39, 3);
    public static final Interval DIMINISHED_24 = new Interval("d24", "d3", "A27", 38, 3);
    public static final Interval MINOR_24 = new Interval("m24", "m3", "M27", 39, 3);
    public static final Interval MAJOR_24 = new Interval("M24", "M3", "m27", 40, 3);
    public static final Interval AUGMENTED_24 = new Interval("A24", "A3", "d27", 41, 3);
    public static final Interval DIMINISHED_25 = new Interval("d25", "d4", "A26", 40, 3);
    public static final Interval PERFECT_25 = new Interval("P25", "P4", "P26", 41, 3);
    public static final Interval AUGMENTED_25 = new Interval("A25", "A4", "d26", 42, 3);
    public static final Interval DIMINISHED_26 = new Interval("d26", "d5", "A25", 42, 3);
    public static final Interval PERFECT_26 = new Interval("P26", "P5", "P25", 43, 3);
    public static final Interval AUGMENTED_26 = new Interval("A26", "A5", "d25", 44, 3);
    public static final Interval DIMINISHED_27 = new Interval("d27", "d6", "A24", 43, 3);
    public static final Interval MINOR_27 = new Interval("m27", "m6", "M24", 44, 3);
    public static final Interval MAJOR_27 = new Interval("M27", "M6", "m24", 45, 3);
    public static final Interval AUGMENTED_27 = new Interval("A27", "A6", "d24", 46, 3);
    public static final Interval DIMINISHED_28 = new Interval("d28", "d7", "A23", 45, 3);
    public static final Interval MINOR_28 = new Interval("m28", "m7", "M23", 46, 3);
    public static final Interval MAJOR_28 = new Interval("M28", "M7", "m23", 47, 3);
    public static final Interval AUGMENTED_28 = new Interval("A28", "A7", "d23", 48, 3);

    public static final Interval DIMINISHED_29 = new Interval("d29", "d1", "A29", 47, 4);
    public static final Interval PERFECT_29 = new Interval("P29", "P1", "P29", 48, 4);
    public static final Interval AUGMENTED_29 = new Interval("A29", "A1", "d29", 49, 4);
    public static final Interval DIMINISHED_30 = new Interval("d30", "d2", "A35", 48, 4);
    public static final Interval MINOR_30 = new Interval("m30", "m2", "M35", 49, 4);
    public static final Interval MAJOR_30 = new Interval("M30", "M2", "m35", 50, 4);
    public static final Interval AUGMENTED_30 = new Interval("A30", "A2", "d35", 51, 4);
    public static final Interval DIMINISHED_31 = new Interval("d31", "d3", "A34", 50, 4);
    public static final Interval MINOR_31 = new Interval("m31", "m3", "M34", 51, 4);
    public static final Interval MAJOR_31 = new Interval("M31", "M3", "m34", 52, 4);
    public static final Interval AUGMENTED_31 = new Interval("A31", "A3", "d34", 53, 4);
    public static final Interval DIMINISHED_32 = new Interval("d32", "d4", "A33", 52, 4);
    public static final Interval PERFECT_32 = new Interval("P32", "P4", "P33", 53, 4);
    public static final Interval AUGMENTED_32 = new Interval("A32", "A4", "d33", 54, 4);
    public static final Interval DIMINISHED_33 = new Interval("d33", "d5", "A32", 54, 4);
    public static final Interval PERFECT_33 = new Interval("P33", "P5", "P32", 55, 4);
    public static final Interval AUGMENTED_33 = new Interval("A33", "A5", "d32", 56, 4);
    public static final Interval DIMINISHED_34 = new Interval("d34", "d6", "A31", 55, 4);
    public static final Interval MINOR_34 = new Interval("m34", "m6", "M31", 56, 4);
    public static final Interval MAJOR_34 = new Interval("M34", "M6", "m31", 57, 4);
    public static final Interval AUGMENTED_34 = new Interval("A34", "A6", "d31", 58, 4);
    public static final Interval DIMINISHED_35 = new Interval("d35", "d7", "A30", 57, 4);
    public static final Interval MINOR_35 = new Interval("m35", "m7", "M30", 58, 4);
    public static final Interval MAJOR_35 = new Interval("M35", "M7", "m30", 59, 4);
    public static final Interval AUGMENTED_35 = new Interval("A35", "A7", "d30", 60, 4);

    public static final Interval DIMINISHED_36 = new Interval("d36", "d1", "A36", 59, 5);
    public static final Interval PERFECT_36 = new Interval("P36", "P1", "P36", 60, 5);
    public static final Interval AUGMENTED_36 = new Interval("A36", "A1", "d36", 61, 5);
    public static final Interval DIMINISHED_37 = new Interval("d37", "d2", "A42", 60, 5);
    public static final Interval MINOR_37 = new Interval("m37", "m2", "M42", 61, 5);
    public static final Interval MAJOR_37 = new Interval("M37", "M2", "m42", 62, 5);
    public static final Interval AUGMENTED_37 = new Interval("A37", "A2", "d42", 63, 5);
    public static final Interval DIMINISHED_38 = new Interval("d38", "d3", "A41", 62, 5);
    public static final Interval MINOR_38 = new Interval("m38", "m3", "M41", 63, 5);
    public static final Interval MAJOR_38 = new Interval("M38", "M3", "m41", 64, 5);
    public static final Interval AUGMENTED_38 = new Interval("A38", "A3", "d41", 65, 5);
    public static final Interval DIMINISHED_39 = new Interval("d39", "d4", "A40", 64, 5);
    public static final Interval PERFECT_39 = new Interval("P39", "P4", "P40", 65, 5);
    public static final Interval AUGMENTED_39 = new Interval("A39", "A4", "d40", 66, 5);
    public static final Interval DIMINISHED_40 = new Interval("d40", "d5", "A39", 66, 5);
    public static final Interval PERFECT_40 = new Interval("P40", "P5", "P39", 67, 5);
    public static final Interval AUGMENTED_40 = new Interval("A40", "A5", "d39", 68, 5);
    public static final Interval DIMINISHED_41 = new Interval("d41", "d6", "A38", 67, 5);
    public static final Interval MINOR_41 = new Interval("m41", "m6", "M38", 68, 5);
    public static final Interval MAJOR_41 = new Interval("M41", "M6", "m38", 69, 5);
    public static final Interval AUGMENTED_41 = new Interval("A41", "A6", "d38", 70, 5);
    public static final Interval DIMINISHED_42 = new Interval("d42", "d7", "A37", 69, 5);
    public static final Interval MINOR_42 = new Interval("m42", "m7", "M37", 70, 5);
    public static final Interval MAJOR_42 = new Interval("M42", "M7", "m37", 71, 5);
    public static final Interval AUGMENTED_42 = new Interval("A42", "A7", "d37", 72, 5);

    public static final Interval DIMINISHED_43 = new Interval("d43", "d1", "A43", 71, 6);
    public static final Interval PERFECT_43 = new Interval("P43", "P1", "P43", 72, 6);
    public static final Interval AUGMENTED_43 = new Interval("A43", "A1", "d43", 73, 6);
    public static final Interval DIMINISHED_44 = new Interval("d44", "d2", "A49", 72, 6);
    public static final Interval MINOR_44 = new Interval("m44", "m2", "M49", 73, 6);
    public static final Interval MAJOR_44 = new Interval("M44", "M2", "m49", 74, 6);
    public static final Interval AUGMENTED_44 = new Interval("A44", "A2", "d49", 75, 6);
    public static final Interval DIMINISHED_45 = new Interval("d45", "d3", "A48", 74, 6);
    public static final Interval MINOR_45 = new Interval("m45", "m3", "M48", 75, 6);
    public static final Interval MAJOR_45 = new Interval("M45", "M3", "m48", 76, 6);
    public static final Interval AUGMENTED_45 = new Interval("A45", "A3", "d48", 77, 6);
    public static final Interval DIMINISHED_46 = new Interval("d46", "d4", "A47", 76, 6);
    public static final Interval PERFECT_46 = new Interval("P46", "P4", "P47", 77, 6);
    public static final Interval AUGMENTED_46 = new Interval("A46", "A4", "d47", 78, 6);
    public static final Interval DIMINISHED_47 = new Interval("d47", "d5", "A46", 78, 6);
    public static final Interval PERFECT_47 = new Interval("P47", "P5", "P46", 79, 6);
    public static final Interval AUGMENTED_47 = new Interval("A47", "A5", "d46", 80, 6);
    public static final Interval DIMINISHED_48 = new Interval("d48", "d6", "A45", 79, 6);
    public static final Interval MINOR_48 = new Interval("m48", "m6", "M45", 80, 6);
    public static final Interval MAJOR_48 = new Interval("M48", "M6", "m45", 81, 6);
    public static final Interval AUGMENTED_48 = new Interval("A48", "A6", "d45", 82, 6);
    public static final Interval DIMINISHED_49 = new Interval("d49", "d7", "A44", 81, 6);
    public static final Interval MINOR_49 = new Interval("m49", "m7", "M44", 82, 6);
    public static final Interval MAJOR_49 = new Interval("M49", "M7", "m44", 83, 6);
    public static final Interval AUGMENTED_49 = new Interval("A49", "A7", "d44", 84, 6);

    public static final Interval DIMINISHED_50 = new Interval("d50", "d1", "A50", 83, 7);
    public static final Interval PERFECT_50 = new Interval("P50", "P1", "P50", 84, 7);
    public static final Interval AUGMENTED_50 = new Interval("A50", "A1", "d50", 85, 7);
    public static final Interval DIMINISHED_51 = new Interval("d51", "d2", "A56", 84, 7);
    public static final Interval MINOR_51 = new Interval("m51", "m2", "M56", 85, 7);
    public static final Interval MAJOR_51 = new Interval("M51", "M2", "m56", 86, 7);
    public static final Interval AUGMENTED_51 = new Interval("A51", "A2", "d56", 87, 7);
    public static final Interval DIMINISHED_52 = new Interval("d52", "d3", "A55", 86, 7);
    public static final Interval MINOR_52 = new Interval("m52", "m3", "M55", 87, 7);
    public static final Interval MAJOR_52 = new Interval("M52", "M3", "m55", 88, 7);
    public static final Interval AUGMENTED_52 = new Interval("A52", "A3", "d55", 89, 7);
    public static final Interval DIMINISHED_53 = new Interval("d53", "d4", "A54", 88, 7);
    public static final Interval PERFECT_53 = new Interval("P53", "P4", "P54", 89, 7);
    public static final Interval AUGMENTED_53 = new Interval("A53", "A4", "d54", 90, 7);
    public static final Interval DIMINISHED_54 = new Interval("d54", "d5", "A53", 90, 7);
    public static final Interval PERFECT_54 = new Interval("P54", "P5", "P53", 91, 7);
    public static final Interval AUGMENTED_54 = new Interval("A54", "A5", "d53", 92, 7);
    public static final Interval DIMINISHED_55 = new Interval("d55", "d6", "A52", 91, 7);
    public static final Interval MINOR_55 = new Interval("m55", "m6", "M52", 92, 7);
    public static final Interval MAJOR_55 = new Interval("M55", "M6", "m52", 93, 7);
    public static final Interval AUGMENTED_55 = new Interval("A55", "A6", "d52", 94, 7);
    public static final Interval DIMINISHED_56 = new Interval("d56", "d7", "A51", 93, 7);
    public static final Interval MINOR_56 = new Interval("m56", "m7", "M51", 94, 7);
    public static final Interval MAJOR_56 = new Interval("M56", "M7", "m51", 95, 7);
    public static final Interval AUGMENTED_56 = new Interval("A56", "A7", "d51", 96, 7);

    public static final Interval DIMINISHED_57 = new Interval("d57", "d1", "A57", 95, 8);
    public static final Interval PERFECT_57 = new Interval("P57", "P1", "P57", 96, 8);
    public static final Interval AUGMENTED_57 = new Interval("A57", "A1", "d57", 97, 8);
    public static final Interval DIMINISHED_58 = new Interval("d58", "d2", "A63", 96, 8);
    public static final Interval MINOR_58 = new Interval("m58", "m2", "M63", 97, 8);
    public static final Interval MAJOR_58 = new Interval("M58", "M2", "m63", 98, 8);
    public static final Interval AUGMENTED_58 = new Interval("A58", "A2", "d63", 99, 8);
    public static final Interval DIMINISHED_59 = new Interval("d59", "d3", "A62", 98, 8);
    public static final Interval MINOR_59 = new Interval("m59", "m3", "M62", 99, 8);
    public static final Interval MAJOR_59 = new Interval("M59", "M3", "m62", 100, 8);
    public static final Interval AUGMENTED_59 = new Interval("A59", "A3", "d62", 101, 8);
    public static final Interval DIMINISHED_60 = new Interval("d60", "d4", "A61", 100, 8);
    public static final Interval PERFECT_60 = new Interval("P60", "P4", "P61", 101, 8);
    public static final Interval AUGMENTED_60 = new Interval("A60", "A4", "d61", 102, 8);
    public static final Interval DIMINISHED_61 = new Interval("d61", "d5", "A60", 102, 8);
    public static final Interval PERFECT_61 = new Interval("P61", "P5", "P60", 103, 8);
    public static final Interval AUGMENTED_61 = new Interval("A61", "A5", "d60", 104, 8);
    public static final Interval DIMINISHED_62 = new Interval("d62", "d6", "A59", 103, 8);
    public static final Interval MINOR_62 = new Interval("m62", "m6", "M59", 104, 8);
    public static final Interval MAJOR_62 = new Interval("M62", "M6", "m59", 105, 8);
    public static final Interval AUGMENTED_62 = new Interval("A62", "A6", "d59", 106, 8);
    public static final Interval DIMINISHED_63 = new Interval("d63", "d7", "A58", 105, 8);
    public static final Interval MINOR_63 = new Interval("m63", "m7", "M58", 106, 8);
    public static final Interval MAJOR_63 = new Interval("M63", "M7", "m58", 107, 8);
    public static final Interval AUGMENTED_63 = new Interval("A63", "A7", "d58", 108, 8);

    public static final Interval DIMINISHED_64 = new Interval("d64", "d1", "A64", 107, 9);
    public static final Interval PERFECT_64 = new Interval("P64", "P1", "P64", 108, 9);
    public static final Interval AUGMENTED_64 = new Interval("A64", "A1", "d64", 109, 9);
    public static final Interval DIMINISHED_65 = new Interval("d65", "d2", "A70", 108, 9);
    public static final Interval MINOR_65 = new Interval("m65", "m2", "M70", 109, 9);
    public static final Interval MAJOR_65 = new Interval("M65", "M2", "m70", 110, 9);
    public static final Interval AUGMENTED_65 = new Interval("A65", "A2", "d70", 111, 9);
    public static final Interval DIMINISHED_66 = new Interval("d66", "d3", "A69", 110, 9);
    public static final Interval MINOR_66 = new Interval("m66", "m3", "M69", 111, 9);
    public static final Interval MAJOR_66 = new Interval("M66", "M3", "m69", 112, 9);
    public static final Interval AUGMENTED_66 = new Interval("A66", "A3", "d69", 113, 9);
    public static final Interval DIMINISHED_67 = new Interval("d67", "d4", "A68", 112, 9);
    public static final Interval PERFECT_67 = new Interval("P67", "P4", "P68", 113, 9);
    public static final Interval AUGMENTED_67 = new Interval("A67", "A4", "d68", 114, 9);
    public static final Interval DIMINISHED_68 = new Interval("d68", "d5", "A67", 114, 9);
    public static final Interval PERFECT_68 = new Interval("P68", "P5", "P67", 115, 9);
    public static final Interval AUGMENTED_68 = new Interval("A68", "A5", "d67", 116, 9);
    public static final Interval DIMINISHED_69 = new Interval("d69", "d6", "A66", 115, 9);
    public static final Interval MINOR_69 = new Interval("m69", "m6", "M66", 116, 9);
    public static final Interval MAJOR_69 = new Interval("M69", "M6", "m66", 117, 9);
    public static final Interval AUGMENTED_69 = new Interval("A69", "A6", "d66", 118, 9);
    public static final Interval DIMINISHED_70 = new Interval("d70", "d7", "A65", 117, 9);
    public static final Interval MINOR_70 = new Interval("m70", "m7", "M65", 118, 9);
    public static final Interval MAJOR_70 = new Interval("M70", "M7", "m65", 119, 9);
    public static final Interval AUGMENTED_70 = new Interval("A70", "A7", "d65", 120, 9);

    public static final Interval DIMINISHED_71 = new Interval("d71", "d1", "A71", 119, 10);
    public static final Interval PERFECT_71 = new Interval("P71", "P1", "P71", 120, 10);
    public static final Interval AUGMENTED_71 = new Interval("A71", "A1", "d71", 121, 10);
    public static final Interval DIMINISHED_72 = new Interval("d72", "d2", "A77", 120, 10);
    public static final Interval MINOR_72 = new Interval("m72", "m2", "M77", 121, 10);
    public static final Interval MAJOR_72 = new Interval("M72", "M2", "m77", 122, 10);
    public static final Interval AUGMENTED_72 = new Interval("A72", "A2", "d77", 123, 10);
    public static final Interval DIMINISHED_73 = new Interval("d73", "d3", "A76", 122, 10);
    public static final Interval MINOR_73 = new Interval("m73", "m3", "M76", 123, 10);
    public static final Interval MAJOR_73 = new Interval("M73", "M3", "m76", 124, 10);
    public static final Interval AUGMENTED_73 = new Interval("A73", "A3", "d76", 125, 10);
    public static final Interval DIMINISHED_74 = new Interval("d74", "d4", "A75", 124, 10);
    public static final Interval PERFECT_74 = new Interval("P74", "P4", "P75", 125, 10);
    public static final Interval AUGMENTED_74 = new Interval("A74", "A4", "d75", 126, 10);
    public static final Interval DIMINISHED_75 = new Interval("d75", "d5", "A74", 126, 10);
    public static final Interval PERFECT_75 = new Interval("P75", "P5", "P74", 127, 10);
    public static final Interval DIMINISHED_76 = new Interval("d76", "d6", "A73", 127, 10);

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
                    map.put(toAdd.compoundShortName, toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }

    private static final Map<String, Interval> INTERVAL_BETWEEN = new HashMap<>();

    static {
        INTERVAL_BETWEEN.put("CC", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("DD", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("EE", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("FF", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("GG", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("AA", Interval.PERFECT_1);
        INTERVAL_BETWEEN.put("BB", Interval.PERFECT_1);

        INTERVAL_BETWEEN.put("CD", Interval.MAJOR_2);
        INTERVAL_BETWEEN.put("DE", Interval.MAJOR_2);
        INTERVAL_BETWEEN.put("EF", Interval.MINOR_2);
        INTERVAL_BETWEEN.put("FG", Interval.MAJOR_2);
        INTERVAL_BETWEEN.put("GA", Interval.MAJOR_2);
        INTERVAL_BETWEEN.put("AB", Interval.MAJOR_2);
        INTERVAL_BETWEEN.put("BC", Interval.MINOR_2);
        INTERVAL_BETWEEN.put("DC", Interval.MINOR_7);
        INTERVAL_BETWEEN.put("ED", Interval.MINOR_7);
        INTERVAL_BETWEEN.put("FE", Interval.MAJOR_7);
        INTERVAL_BETWEEN.put("GF", Interval.MINOR_7);
        INTERVAL_BETWEEN.put("AG", Interval.MINOR_7);
        INTERVAL_BETWEEN.put("BA", Interval.MINOR_7);
        INTERVAL_BETWEEN.put("CB", Interval.MAJOR_7);

        INTERVAL_BETWEEN.put("CE", Interval.MAJOR_3);
        INTERVAL_BETWEEN.put("DF", Interval.MINOR_3);
        INTERVAL_BETWEEN.put("EG", Interval.MINOR_3);
        INTERVAL_BETWEEN.put("FA", Interval.MAJOR_3);
        INTERVAL_BETWEEN.put("GB", Interval.MAJOR_3);
        INTERVAL_BETWEEN.put("AC", Interval.MINOR_3);
        INTERVAL_BETWEEN.put("BD", Interval.MINOR_3);
        INTERVAL_BETWEEN.put("EC", Interval.MINOR_6);
        INTERVAL_BETWEEN.put("FD", Interval.MAJOR_6);
        INTERVAL_BETWEEN.put("GE", Interval.MAJOR_6);
        INTERVAL_BETWEEN.put("AF", Interval.MINOR_6);
        INTERVAL_BETWEEN.put("BG", Interval.MINOR_6);
        INTERVAL_BETWEEN.put("CA", Interval.MAJOR_6);
        INTERVAL_BETWEEN.put("DB", Interval.MAJOR_6);

        INTERVAL_BETWEEN.put("CF", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("DG", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("EA", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("FB", Interval.AUGMENTED_4);
        INTERVAL_BETWEEN.put("GC", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("AD", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("BE", Interval.PERFECT_4);
        INTERVAL_BETWEEN.put("FC", Interval.PERFECT_5);
        INTERVAL_BETWEEN.put("GD", Interval.PERFECT_5);
        INTERVAL_BETWEEN.put("AE", Interval.PERFECT_5);
        INTERVAL_BETWEEN.put("BF", Interval.DIMINISHED_5);
        INTERVAL_BETWEEN.put("CG", Interval.PERFECT_5);
        INTERVAL_BETWEEN.put("DA", Interval.PERFECT_5);
        INTERVAL_BETWEEN.put("EB", Interval.PERFECT_5);
    }

    private static final Map<String, Integer> SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE = new HashMap<>();

    static {
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d1", Interval.DIMINISHED_1.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("P1", Interval.PERFECT_1.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A1", Interval.AUGMENTED_1.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d2", Interval.DIMINISHED_2.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("m2", Interval.MINOR_2.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("M2", Interval.MAJOR_2.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A2", Interval.AUGMENTED_2.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d3", Interval.DIMINISHED_3.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("m3", Interval.MINOR_3.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("M3", Interval.MAJOR_3.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A3", Interval.AUGMENTED_3.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d4", Interval.DIMINISHED_4.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("P4", Interval.PERFECT_4.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A4", Interval.AUGMENTED_4.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d5", Interval.DIMINISHED_5.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("P5", Interval.PERFECT_5.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A5", Interval.AUGMENTED_5.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d6", Interval.DIMINISHED_6.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("m6", Interval.MINOR_6.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("M6", Interval.MAJOR_6.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A6", Interval.AUGMENTED_6.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("d7", Interval.DIMINISHED_7.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("m7", Interval.MINOR_7.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("M7", Interval.MAJOR_7.semitones);
        SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.put("A7", Interval.AUGMENTED_7.semitones);
    }

    private final String compoundShortName;
    private final String simpleShortName;
    private final String invertedCompoundShortName;
    private final int semitones;
    private final int octaves;

    Interval(String compoundShortname,
             String simpleShortname,
             String invertedCompoundShortname,
             int semitones,
             int octaves) {

        this.compoundShortName = compoundShortname;
        this.simpleShortName = simpleShortname;
        this.invertedCompoundShortName = invertedCompoundShortname;
        this.semitones = semitones;
        this.octaves = octaves;

    }

    public int getSemitones() {
        return semitones;
    }

    public int getOctaves() {
        return octaves;
    }

    public int getSimpleDiatonic() {
        return Integer.parseInt(simpleShortName.replaceAll("\\D", ""));
    }

    public int getCompoundDiatonic() {
        return Integer.parseInt(compoundShortName.replaceAll("\\D", ""));
    }

    public boolean isCompound() {
        return getCompoundDiatonic() >= 8;
    }

    /**
     * Helper method to retrieve the simple Interval between two PitchClasses, if possible.
     *
     * @param begin     the starting PitchClass
     * @param end       the ending PitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    private static Interval determineIntervalBetween(PitchClass begin, PitchClass end, boolean direction) {

        String beginName = begin.getName();
        String endName = end.getName();
        String beginLetter = String.valueOf(beginName.charAt(0));
        String endLetter = String.valueOf(endName.charAt(0));

        /*
         * 1. Simplify and cancel out accidentals on each side,
         * until 'begin' is natural (contains no accidentals).
         */
        StringBuilder beginAccidentals = new StringBuilder(Accidental.simplify((beginName.length() > 1 ? beginName.substring(1) : ""), false, true));
        StringBuilder endAccidentals = new StringBuilder(Accidental.simplify((endName.length() > 1 ? endName.substring(1) : ""), false, true));
        while (beginAccidentals.length() > 0) {
            if (beginAccidentals.indexOf(Accidental.FLAT.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.SHARP.utf8Symbol);
            } else if (beginAccidentals.indexOf(Accidental.SHARP.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.FLAT.utf8Symbol);
            } else if (beginAccidentals.indexOf(Accidental.DOUBLE_SHARP.utf8Symbol) == 0) {
                endAccidentals.append(Accidental.DOUBLE_FLAT.utf8Symbol);
            }

            beginAccidentals.deleteCharAt(0);
        }
        endAccidentals = new StringBuilder(Accidental.simplify(endAccidentals.toString(), false, true));
        PitchClass newBegin = PitchClass.withName(beginLetter + beginAccidentals, false);
        PitchClass newEnd = PitchClass.withName(endLetter + endAccidentals, false);

        /*
         * 2. Count semitones between the two resulting pitch classes.
         *
         * If going UP: count LEFT TO RIGHT from 'begin' to 'end'
         * If going DOWN: count RIGHT TO LEFT from 'begin' to 'end'.
         */
        int semitonesBetween = direction
                ? PitchClass.getSemitoneDistanceBetween(newBegin, newEnd)
                : PitchClass.getSemitoneDistanceBetween(newEnd, newBegin);

        /*
         * 3. Lookup the "generalized" starting interval from hashmap
         * by combining letters of the two pitch classes.
         */
        String key = direction ? (beginLetter + endLetter) : (endLetter + beginLetter);
        Interval candidate = INTERVAL_BETWEEN.get(key);

        /*
         * 4. Compare the semitones gathered from step 2,
         * and the semitones on the interval from step 3.
         *
         * If they match, return the interval from step 3.
         * Otherwise, proceed to step 5.
         */
        if (semitonesBetween == candidate.semitones) {
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
        return applyAccidental(candidate, endAccidentals.toString(), direction);
    }

    private static Interval applyAccidental(Interval candidate, String accidentals, boolean direction) {
        String candidateQuality = String.valueOf(candidate.simpleShortName.charAt(0));
        int simpleDiatonic = candidate.getSimpleDiatonic();

        boolean isOneFourFive = simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5;

        for (char c : accidentals.toCharArray()) {
            candidateQuality = determineQualityByAccidental(candidateQuality, c, isOneFourFive, direction);
        }

        return Interval.withShortName(candidateQuality + simpleDiatonic);
    }

    private static String determineQualityByAccidental(String toReturn, char accidental, boolean isOneFourFive, boolean direction) {

        String[] qualities = direction
                ? new String[]{"d", "m", "P", "M", "A"}
                : new String[]{"A", "M", "P", "m", "d"};

        if (Accidental.FLAT.equals(Accidental.getBySymbol(accidental))) {
            return handleFlatApplication(toReturn, isOneFourFive, qualities);
        } else if (Accidental.SHARP.equals(Accidental.getBySymbol(accidental))) {
            return handleSharpApplication(toReturn, isOneFourFive, false, qualities);
        } else if (Accidental.DOUBLE_SHARP.equals(Accidental.getBySymbol(accidental))) {
            return handleSharpApplication(toReturn, isOneFourFive, true, qualities);
        }

        return toReturn;
    }

    private static String handleFlatApplication(String toReturn, boolean isOneFourFive, String[] qualities) {

        if (toReturn.startsWith(qualities[0])) {
            toReturn += qualities[0];
        } else if (qualities[1].equals(toReturn) || qualities[2].equals(toReturn)) {
            toReturn = qualities[0];
        } else if (qualities[3].equals(toReturn)) {
            toReturn = qualities[1];
        } else if (toReturn.startsWith(qualities[4])) {
            if (toReturn.length() > 1) {
                toReturn = toReturn.substring(0, toReturn.length() - 1);
            } else {
                toReturn = qualities[isOneFourFive ? 2 : 3];
            }
        }

        return toReturn;
    }

    private static String handleSharpApplication(String toReturn, boolean isOneFourFive, boolean isDoubleSharp, String[] qualities) {
        if (toReturn.startsWith(qualities[0])) {
            if (isDoubleSharp && toReturn.length() > 2) {
                toReturn = toReturn.substring(0, toReturn.length() - 2);
            } else if (toReturn.length() > 1) {
                toReturn = toReturn.substring(0, toReturn.length() - 1);
            } else {
                toReturn = qualities[isOneFourFive ? 2 : 1];
            }
        } else if (qualities[1].equals(toReturn)) {
            toReturn = qualities[3];
        } else if (qualities[3].equals(toReturn) || qualities[2].equals(toReturn)) {
            toReturn = qualities[4];
        } else if (toReturn.startsWith(qualities[4])) {
            toReturn += qualities[4];
        }

        return toReturn;
    }

    /**
     * Retrieves the simple Interval between two PitchClasses, if possible.
     *
     * @param begin     the starting BasePitchClass
     * @param end       the ending BasePitchClass
     * @param direction whether the target interval is ascending (true) or descending (false)
     * @return a static Interval representative of the given arguments (if one is found)
     * @throws RuntimeException if no Interval could be determined
     */
    public static Interval getIntervalBetween(@NotNull PitchClass begin, @NotNull PitchClass end, boolean direction) {
        try {
            return determineIntervalBetween(begin, end, direction);
        } catch (IllegalArgumentException ex) {
            throw new ChordInnateException(
                    "Could not determine the "
                            + (direction ? "upward " : "downward ")
                            + "interval between pitch classes ["
                            + begin.getName()
                            + "] and ["
                            + end.getName()
                            + "]",
                    ex
            );
        }
    }

    private static int getSimpleDiatonic(int compoundDiatonic) {
        return compoundDiatonic % 7 == 0 ? 7 : compoundDiatonic % 7;
    }

    public boolean isDiminished() {
        return simpleShortName.contains("d");
    }

    public boolean isAugmented() {
        return simpleShortName.contains("A");
    }

    public boolean isPerfect() {
        return simpleShortName.charAt(0) == 'P';
    }

    public boolean isMajor() {
        return simpleShortName.charAt(0) == 'M';
    }

    public boolean isMinor() {
        return simpleShortName.charAt(0) == 'm';
    }

    public String getCompoundShortName() {
        return compoundShortName;
    }

    public String getSimpleShortName() {
        return simpleShortName;
    }

    /**
     * Retrieves the Interval specified by its compound short name.
     *
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

                // For super diminished/augmented intervals (e.g., dd1, AAAAA5, etc.)

                String invertedCompoundShortName = determineInvertedCompoundShortName(quality, compoundDiatonic, simpleDiatonic);
                int[] semitonesAndOctaves = determineSemitonesAndOctaves(quality, compoundDiatonic);
                int semitones = semitonesAndOctaves[0];
                int octaves = semitonesAndOctaves[1];

                return new Interval(compoundShortName, simpleShortName, invertedCompoundShortName, semitones, octaves);
            }
        }

        throw new IllegalArgumentException("Invalid interval name [" + compoundShortName + "]");
    }

    private static int[] determineSemitonesAndOctaves(String quality, int compoundDiatonic) {
        int total = IntStream.range(0, quality.length())
                .map(i -> {
                    if (quality.charAt(i) == 'd') {
                        return -1;
                    } else {
                        return 1;
                    }
                }).sum();

        int simpleDiatonic = getSimpleDiatonic(compoundDiatonic);

        int octaves = (compoundDiatonic % 7 == 0 ? ((compoundDiatonic / 7) - 1) : (compoundDiatonic / 7));

        int[] semitonesAndOctaves = new int[2];
        semitonesAndOctaves[1] = octaves;

        if (total < 0) {
            int simpleSemitone = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get("d" + simpleDiatonic);
            semitonesAndOctaves[0] = simpleSemitone + (12 * octaves) + (total + 1);
        } else if (total > 0) {
            int simpleSemitone = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get("A" + simpleDiatonic);
            semitonesAndOctaves[0] = simpleSemitone + (12 * octaves) + (total - 1);
        } else {
            if (simpleDiatonic == 1 || simpleDiatonic == 4 || simpleDiatonic == 5) {
                semitonesAndOctaves[0] = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get("P" + simpleDiatonic);
            } else {
                semitonesAndOctaves[0] = SIMPLE_SHORTNAME_TO_SIMPLE_SEMITONE.get("M" + simpleDiatonic);
            }
        }

        return semitonesAndOctaves;
    }

    private static String determineInvertedCompoundShortName(String quality, int compoundDiatonic, int simpleDiatonic) {

        // Add as many d's / A's to the inversion as there are in the uninverted one.
        String inversion = IntStream.range(0, quality.length())
                .mapToObj(i -> {
                    if (quality.charAt(i) == 'd') {
                        return "A";
                    } else {
                        return "d";
                    }
                })
                .collect(Collectors.joining(""));

        // Figure out the distance required to get the diatonic of the inversion (i.e.: 1 <-> 1, 2 <-> 7, 3 <-> 6, 4 <-> 5)
        int modifier = 9 - (2 * simpleDiatonic);

        inversion += (compoundDiatonic + modifier);

        return inversion;
    }

    @Override
    public Interval invert() {
        return Interval.withShortName(invertedCompoundShortName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Interval comparison = (Interval) other;

        return comparison.compoundShortName.equals(this.compoundShortName)
                && comparison.simpleShortName.equals(this.simpleShortName)
                && comparison.invertedCompoundShortName.equals(this.invertedCompoundShortName)
                && comparison.octaves == this.octaves
                && comparison.semitones == this.semitones;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
