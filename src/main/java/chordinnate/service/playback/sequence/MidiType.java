package chordinnate.service.playback.sequence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MidiType {

    TYPE_ZERO(0),
    TYPE_ONE(1),
    TYPE_TWO(2),

    ;

    private final int value;

}
