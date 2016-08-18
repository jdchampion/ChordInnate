package chordinnate.musictheory.pitch.interval;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 8/17/16.
 */
class IntervalUtil {
    static final Map<String, Integer> SIMPLE_SHORT_NAME_TO_SEMITONES = new HashMap<>(28);
    static {
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d1", -1);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("P1", 0);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A1", 1);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d2", 0);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("m2", 1);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("M2", 2);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A2", 3);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d3", 2);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("m3", 3);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("M3", 4);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A3", 5);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d4", 4);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("P4", 5);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A4", 6);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d5", 6);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("P5", 7);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A5", 8);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d6", 7);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("m6", 8);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("M6", 9);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A6", 10);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("d7", 9);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("m7", 10);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("M7", 11);
        SIMPLE_SHORT_NAME_TO_SEMITONES.put("A7", 12);
    }

    private IntervalUtil() {}
}
