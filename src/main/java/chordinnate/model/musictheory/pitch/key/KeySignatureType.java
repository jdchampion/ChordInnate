package chordinnate.model.musictheory.pitch.key;

import lombok.AllArgsConstructor;

/**
 * Created by Joseph on 4/14/16.
 */
@AllArgsConstructor
public enum KeySignatureType {
    MAJOR("Major"),
    MINOR("Minor"),
    NONE("");

    String label;
}
