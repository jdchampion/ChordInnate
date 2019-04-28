package chordinnate.model.musictheory.notation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntervalQuality {
    DIMINISHED("d"),
    MINOR("m"),
    PERFECT("P"),
    MAJOR("M"),
    AUGMENTED("A");

    private final String symbol;
}
