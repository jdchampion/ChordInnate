package chordinnate.musictheory;

/**
 * Created by Joseph on 6/16/16.
 * References: https://en.wikipedia.org/wiki/Texture_(music)
 */
enum Texture {
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

    HETEROPHONIC
}
