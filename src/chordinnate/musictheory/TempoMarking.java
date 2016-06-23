package chordinnate.musictheory;

/**
 * Created by Joseph on 6/22/16.
 * References: https://en.wikipedia.org/wiki/Tempo
 */
public enum TempoMarking {
    /*
     * NOTE: Keep these ordered from slowest to fastest.
     *
     * Tempo ranges are approximate and gathered from
     * https://en.wikipedia.org/wiki/Tempo
     */
    LARGHISSIMO(20, 24),
    GRAVE(25, 45),
    LARGO(40, 60),
    LENTO(45, 60),
    LARGHETTO(60, 66),
    ADAGIO(66, 76),
    ADAGIETTO(72, 76),
    ANDANTE(76, 108),
    ANDANTINO(80, 108),
    MARCIA_MODERATO(83, 85),
    ANDANTE_MODERATO(92, 112),
    MODERATO(108, 120),
    ALLEGRETTO(112, 120),
    ALLEGRO_MODERATO(116, 120),
    ALLEGRO(120, 168),
    VIVACE(168, 176),
    VIVACISSIMO(172, 176),
    ALLEGRISSIMO(172, 176),
    PRESTO(168, 200),
    PRESTISSIMO(200, 240),

    ;

    private final int MIN_BPM, MAX_BPM;
    private static final TempoMarking[] VALUES = TempoMarking.values();

    TempoMarking(int minBPM, int maxBPM) {
        this.MIN_BPM = minBPM;
        this.MAX_BPM = maxBPM;
    }

    public int getMinBPM() {
        return MIN_BPM;
    }

    public int getMaxBPM() {
        return MAX_BPM;
    }

    public static TempoMarking slowest() {
        return VALUES[0];
    }

    public static TempoMarking fastest() {
        return VALUES[VALUES.length];
    }
}
