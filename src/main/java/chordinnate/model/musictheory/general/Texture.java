package chordinnate.model.musictheory.general;

/**
 * Created by Joseph on 6/16/16.
 *
 * @see <a href=https://en.wikipedia.org/wiki/Texture_(music)>1</a>
 */
public enum Texture {
    MONOPHONIC,

    BIPHONIC,

    // These three are essentially equivalent
    POLYPHONIC,
    COUNTERPOINT,
    CONTRAPUNTAL,

    HOMOPHONIC,

    // These two are essentially equivalent
    HOMORHYTHMIC,
    CHORDAL,

    HETEROPHONIC;
}
