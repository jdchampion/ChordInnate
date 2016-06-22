package chordinnate.musictheory;

/**
 * Created by Joseph on 6/1/16.
 *
 * References: https://en.wikipedia.org/wiki/Dynamics_%28music%29
 */
public enum Dynamic {
    PIANISSISSIMO("ppp", 16),
    PIANISSIMO("pp", 32),
    PIANO("p", 48),
    MEZZO_PIANO("mp", 64),
    MEZZO_FORTE("mf", 80),
    FORTE("f", 96),
    FORTISSIMO("ff", 112),
    FORTISSISSIMO("fff", 127),

    ;

    private final String SYMBOL;
    private final int VOLUME_LEVEL;

    Dynamic(String symbol, int volumeLevel) {
        this.SYMBOL = symbol;
        this.VOLUME_LEVEL = volumeLevel;
    }

    public String getSymbol() {
        return SYMBOL;
    }

    int getVolumeLevel() {
        return VOLUME_LEVEL;
    }
}
