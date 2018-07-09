package chordinnate.model.musictheory.notation;

import chordinnate.model.Aliased;
import chordinnate.model.musictheory.pitch.Enharmonic;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EnharmonicSpelling implements Enharmonic<EnharmonicSpelling>, Aliased {

    public static final EnharmonicSpelling C_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.C_DOUBLE_FLAT);
    public static final EnharmonicSpelling C_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.C_FLAT);
    public static final EnharmonicSpelling C_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.C_NATURAL);
    public static final EnharmonicSpelling C = new EnharmonicSpelling(BaseEnharmonicSpelling.C);
    public static final EnharmonicSpelling C_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.C_SHARP);
    public static final EnharmonicSpelling C_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.C_DOUBLE_SHARP);

    public static final EnharmonicSpelling D_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.D_DOUBLE_FLAT);
    public static final EnharmonicSpelling D_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.D_FLAT);
    public static final EnharmonicSpelling D_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.D_NATURAL);
    public static final EnharmonicSpelling D = new EnharmonicSpelling(BaseEnharmonicSpelling.D);
    public static final EnharmonicSpelling D_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.D_SHARP);
    public static final EnharmonicSpelling D_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.D_DOUBLE_SHARP);

    public static final EnharmonicSpelling E_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.E_DOUBLE_FLAT);
    public static final EnharmonicSpelling E_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.E_FLAT);
    public static final EnharmonicSpelling E_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.E_NATURAL);
    public static final EnharmonicSpelling E = new EnharmonicSpelling(BaseEnharmonicSpelling.E);
    public static final EnharmonicSpelling E_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.E_SHARP);
    public static final EnharmonicSpelling E_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.E_DOUBLE_SHARP);

    public static final EnharmonicSpelling F_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.F_DOUBLE_FLAT);
    public static final EnharmonicSpelling F_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.F_FLAT);
    public static final EnharmonicSpelling F_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.F_NATURAL);
    public static final EnharmonicSpelling F = new EnharmonicSpelling(BaseEnharmonicSpelling.F);
    public static final EnharmonicSpelling F_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.F_SHARP);
    public static final EnharmonicSpelling F_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.F_DOUBLE_SHARP);

    public static final EnharmonicSpelling G_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.G_DOUBLE_FLAT);
    public static final EnharmonicSpelling G_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.G_FLAT);
    public static final EnharmonicSpelling G_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.G_NATURAL);
    public static final EnharmonicSpelling G = new EnharmonicSpelling(BaseEnharmonicSpelling.G);
    public static final EnharmonicSpelling G_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.G_SHARP);
    public static final EnharmonicSpelling G_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.G_DOUBLE_SHARP);

    public static final EnharmonicSpelling A_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.A_DOUBLE_FLAT);
    public static final EnharmonicSpelling A_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.A_FLAT);
    public static final EnharmonicSpelling A_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.A_NATURAL);
    public static final EnharmonicSpelling A = new EnharmonicSpelling(BaseEnharmonicSpelling.A);
    public static final EnharmonicSpelling A_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.A_SHARP);
    public static final EnharmonicSpelling A_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.A_DOUBLE_SHARP);

    public static final EnharmonicSpelling B_DOUBLE_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.B_DOUBLE_FLAT);
    public static final EnharmonicSpelling B_FLAT = new EnharmonicSpelling(BaseEnharmonicSpelling.B_FLAT);
    public static final EnharmonicSpelling B_NATURAL = new EnharmonicSpelling(BaseEnharmonicSpelling.B_NATURAL);
    public static final EnharmonicSpelling B = new EnharmonicSpelling(BaseEnharmonicSpelling.B);
    public static final EnharmonicSpelling B_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.B_SHARP);
    public static final EnharmonicSpelling B_DOUBLE_SHARP = new EnharmonicSpelling(BaseEnharmonicSpelling.B_DOUBLE_SHARP);

    private static final Map<String, EnharmonicSpelling> STANDARD_ENHARMONIC_SPELLINGS = new HashMap<String, EnharmonicSpelling>() {{
        put("Cbb", EnharmonicSpelling.C_DOUBLE_FLAT);
        put("Cb", EnharmonicSpelling.C_FLAT);
        put("C", EnharmonicSpelling.C);
        put("C_", EnharmonicSpelling.C_NATURAL);
        put("C#", EnharmonicSpelling.C_SHARP);
        put("Cx", EnharmonicSpelling.C_DOUBLE_SHARP);

        put("Dbb", EnharmonicSpelling.D_DOUBLE_FLAT);
        put("Db", EnharmonicSpelling.D_FLAT);
        put("D", EnharmonicSpelling.D);
        put("D_", EnharmonicSpelling.D_NATURAL);
        put("D#", EnharmonicSpelling.D_SHARP);
        put("Dx", EnharmonicSpelling.D_DOUBLE_SHARP);

        put("Ebb", EnharmonicSpelling.E_DOUBLE_FLAT);
        put("Eb", EnharmonicSpelling.E_FLAT);
        put("E", EnharmonicSpelling.E);
        put("E_", EnharmonicSpelling.E_NATURAL);
        put("E#", EnharmonicSpelling.E_SHARP);
        put("Ex", EnharmonicSpelling.E_DOUBLE_SHARP);

        put("Fbb", EnharmonicSpelling.F_DOUBLE_FLAT);
        put("Fb", EnharmonicSpelling.F_FLAT);
        put("F", EnharmonicSpelling.F);
        put("F_", EnharmonicSpelling.F_NATURAL);
        put("F#", EnharmonicSpelling.F_SHARP);
        put("Fx", EnharmonicSpelling.F_DOUBLE_SHARP);

        put("Gbb", EnharmonicSpelling.G_DOUBLE_FLAT);
        put("Gb", EnharmonicSpelling.G_FLAT);
        put("G", EnharmonicSpelling.G);
        put("G_", EnharmonicSpelling.G_NATURAL);
        put("G#", EnharmonicSpelling.G_SHARP);
        put("Gx", EnharmonicSpelling.G_DOUBLE_SHARP);

        put("Abb", EnharmonicSpelling.A_DOUBLE_FLAT);
        put("Ab", EnharmonicSpelling.A_FLAT);
        put("A", EnharmonicSpelling.A);
        put("A_", EnharmonicSpelling.A_NATURAL);
        put("A#", EnharmonicSpelling.A_SHARP);
        put("Ax", EnharmonicSpelling.A_DOUBLE_SHARP);

        put("Bbb", EnharmonicSpelling.B_DOUBLE_FLAT);
        put("Bb", EnharmonicSpelling.B_FLAT);
        put("B", EnharmonicSpelling.B);
        put("B_", EnharmonicSpelling.B_NATURAL);
        put("B#", EnharmonicSpelling.B_SHARP);
        put("Bx", EnharmonicSpelling.B_DOUBLE_SHARP);
    }};

    private Letter LETTER;
    private List<Accidental> ACCIDENTALS;
    private List<Accidental> SIMPLIFIED_ACCIDENTALS;
    private String NAME;
    private int BASE_MIDI_VALUE;

    private static final String VALID_SYMBOLS_REGEX = "^[A-Ga-g](\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x_])*$";
    public static final Pattern PATTERN = Pattern.compile(VALID_SYMBOLS_REGEX);

    private EnharmonicSpelling() {}

    private EnharmonicSpelling(BaseEnharmonicSpelling baseEnharmonicSpelling) {
        this.LETTER = baseEnharmonicSpelling.LETTER;
        this.ACCIDENTALS = Collections.unmodifiableList(Collections.singletonList(baseEnharmonicSpelling.ACCIDENTAL));
        this.SIMPLIFIED_ACCIDENTALS = Collections.unmodifiableList(Collections.singletonList(baseEnharmonicSpelling.ACCIDENTAL));
        this.NAME = baseEnharmonicSpelling.NAME;
        this.BASE_MIDI_VALUE = baseEnharmonicSpelling.BASE_MIDI_VALUE;
    }

    private EnharmonicSpelling(String alias, boolean wantNaturalSymbol) {
        this.LETTER = Letter.valueOf(String.valueOf(alias.charAt(0)));
        this.ACCIDENTALS = new ArrayList<>();
        Accidental previous = Accidental.NONE;
        String accidentals = alias.substring(1);
        for (int i = 1; i < accidentals.length(); i++) {
            String token = accidentals.substring(i - 1, i);
            Accidental toAdd = Accidental.getBySymbol(token);
            if (Accidental.FLAT.equals(previous) && Accidental.FLAT.equals(toAdd)) {
                ACCIDENTALS.set(i - 1, Accidental.DOUBLE_FLAT);
            } else {
                ACCIDENTALS.add(toAdd);
            }
        }
        this.SIMPLIFIED_ACCIDENTALS = Accidental.simplify(ACCIDENTALS, wantNaturalSymbol);
        this.NAME = LETTER.name() + Accidental.convertToDisplaySymbols(accidentals, true);
        this.BASE_MIDI_VALUE = LETTER.BASE_MIDI_VALUE + Accidental.sumAccidentalsToSemitoneModifier(accidentals);
    }

    public static EnharmonicSpelling withName(String name, boolean wantNaturalSymbol) {

        if (!PATTERN.matcher(name).matches()) {
            throw new RuntimeException("Invalid name for enharmonic spelling provided: " + name);
        } else {
            String letter = String.valueOf(name.charAt(0));
            String simplifiedAccidentals = Accidental.simplify(name.substring(1), wantNaturalSymbol, true);

            // Try looking up a standard enharmonic spelling and return the static instance if possible
            String enharmonicSpellingName = letter + simplifiedAccidentals;
            if (STANDARD_ENHARMONIC_SPELLINGS.get(enharmonicSpellingName) != null) {
                return STANDARD_ENHARMONIC_SPELLINGS.get(enharmonicSpellingName);
            }

            // Create a new (non-standard) EnharmonicSpelling
            try {
                return new EnharmonicSpelling(name, wantNaturalSymbol);
            } catch (Exception e) {
                throw new RuntimeException("Could not create enharmonic spelling with name: " + name);
            }
        }

    }

    public EnharmonicSpelling apply(Accidental toApply, boolean simplify, boolean wantNaturalSymbol) {

        List<Accidental> allAccidentals = new ArrayList<>(this.ACCIDENTALS);
        allAccidentals.add(toApply);

        if (simplify) {
            allAccidentals = Accidental.simplify(allAccidentals, wantNaturalSymbol);
        }

        StringBuilder sb = new StringBuilder();
        allAccidentals.forEach(a -> sb.append(a.UTF8_SYMBOL));

        String newName = this.LETTER.name() + sb.toString();
        return EnharmonicSpelling.withName(newName, wantNaturalSymbol);

    }

    @Override
    public boolean isEnharmonicTo(@NotNull EnharmonicSpelling other) {
        return this.BASE_MIDI_VALUE == other.BASE_MIDI_VALUE;
    }

    @Override
    public String getBaseName() {
        return this.NAME;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        ACCIDENTALS.forEach(a -> sb.append(a.UTF8_SYMBOL));
        return this.LETTER + sb.toString();
    }
}
