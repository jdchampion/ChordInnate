package chordinnate.model.musictheory.notation;

public enum RomanNumeral {
    DIMINISHED_ONE("i˚"),
    MINOR_ONE("i"),
    PERFECT_ONE("I"),
    MAJOR_ONE("I"),
    AUGMENTED_ONE("I+"),

    DIMINISHED_TWO("ii˚"),
    MINOR_TWO("ii"),
    MAJOR_TWO("II"),
    AUGMENTED_TWO("II+"),

    DIMINISHED_THREE("iii˚"),
    MINOR_THREE("iii"),
    MAJOR_THREE("III"),
    AUGMENTED_THREE("III+"),

    DIMINISHED_FOUR("iv˚"),
    MINOR_FOUR("iv"),
    PERFECT_FOUR("IV"),
    MAJOR_FOUR("IV"),
    AUGMENTED_FOUR("IV+"),

    DIMINISHED_FIVE("v˚"),
    MINOR_FIVE("v"),
    PERFECT_FIVE("V"),
    MAJOR_FIVE("V"),
    AUGMENTED_FIVE("V+"),

    DIMINISHED_SIX("vi˚"),
    MINOR_SIX("vi"),
    MAJOR_SIX("VI"),
    AUGMENTED_SIX("VI+"),

    DIMINISHED_SEVEN("vii˚"),
    MINOR_SEVEN("vii"),
    MAJOR_SEVEN("VII"),
    AUGMENTED_SEVEN("VII+"),
    ;

    public final String SYMBOL;

    RomanNumeral(String symbol) {
        this.SYMBOL = symbol;
    }
}
