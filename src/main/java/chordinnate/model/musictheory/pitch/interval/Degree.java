package chordinnate.model.musictheory.pitch.interval;


/**
 * Created by Joseph on 8/16/16.
 */
public enum Degree {
    ONE(1, 0, "tonic", "root", "unison|octave"),
    TWO(2, 2, "supertonic", "second", "second"),
    THREE(3, 4, "mediant", "third", "third"),
    FOUR(4, 5, "subdominant", "fourth", "fourth"),
    FIVE(5, 7, "dominant", "fifth", "fifth"),
    SIX(6, 9, "submediant", "sixth", "sixth"),
    SEVEN(7, 11, "leading tone|subtonic", "seventh", "seventh"),

    ;

    public final int DIATONIC_NUMBER, SEMITONES;
    public final String ALIAS_RN_ANALYSIS, ALIAS_NASHVILLE_NUMBERING, ALIAS_INTERVAL_NAMING;

    Degree(int number, int semitones, String rnAnalysis, String nashvilleNumbering, String intervalNaming) {
        this.DIATONIC_NUMBER = number;
        this.SEMITONES = semitones;
        this.ALIAS_RN_ANALYSIS = rnAnalysis;
        this.ALIAS_NASHVILLE_NUMBERING = nashvilleNumbering;
        this.ALIAS_INTERVAL_NAMING = intervalNaming;
    }
}
