package chordinnate.model.musictheory.pitch.interval.set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IntervalDirection {
    UP(1),
    DOWN(-1),
    ASCENDING(1),
    DESCENDING(-1),
    STATIONARY(0);

    @Getter
    private int compareTo;
}
