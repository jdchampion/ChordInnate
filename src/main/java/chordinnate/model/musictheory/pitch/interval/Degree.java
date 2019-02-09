package chordinnate.model.musictheory.pitch.interval;


import lombok.AllArgsConstructor;

/**
 * Created by Joseph on 8/16/16.
 */
@AllArgsConstructor
public enum Degree {
    ONE(1, 0, "tonic", "root", "unison|octave"),
    TWO(2, 2, "supertonic", "second", "second"),
    THREE(3, 4, "mediant", "third", "third"),
    FOUR(4, 5, "subdominant", "fourth", "fourth"),
    FIVE(5, 7, "dominant", "fifth", "fifth"),
    SIX(6, 9, "submediant", "sixth", "sixth"),
    SEVEN(7, 11, "leading tone|subtonic", "seventh", "seventh"),

    ;

    public final int diatonicNumber;
    public final int semitones;
    public final String aliasRnAnalysis;
    public final String aliasNashvilleNumbering;
    public final String aliasIntervalNaming;
}
