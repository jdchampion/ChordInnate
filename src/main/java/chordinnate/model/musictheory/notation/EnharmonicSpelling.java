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

    private static final Map<String, EnharmonicSpelling> STANDARD_ENHARMONIC_SPELLINGS = new HashMap<>();

    static {
        STANDARD_ENHARMONIC_SPELLINGS.put("Cbb", EnharmonicSpelling.C_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Cb", EnharmonicSpelling.C_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("C", EnharmonicSpelling.C);
        STANDARD_ENHARMONIC_SPELLINGS.put("C_", EnharmonicSpelling.C_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("C#", EnharmonicSpelling.C_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Cx", EnharmonicSpelling.C_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Dbb", EnharmonicSpelling.D_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Db", EnharmonicSpelling.D_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("D", EnharmonicSpelling.D);
        STANDARD_ENHARMONIC_SPELLINGS.put("D_", EnharmonicSpelling.D_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("D#", EnharmonicSpelling.D_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Dx", EnharmonicSpelling.D_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Ebb", EnharmonicSpelling.E_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Eb", EnharmonicSpelling.E_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("E", EnharmonicSpelling.E);
        STANDARD_ENHARMONIC_SPELLINGS.put("E_", EnharmonicSpelling.E_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("E#", EnharmonicSpelling.E_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Ex", EnharmonicSpelling.E_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Fbb", EnharmonicSpelling.F_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Fb", EnharmonicSpelling.F_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("F", EnharmonicSpelling.F);
        STANDARD_ENHARMONIC_SPELLINGS.put("F_", EnharmonicSpelling.F_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("F#", EnharmonicSpelling.F_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Fx", EnharmonicSpelling.F_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Gbb", EnharmonicSpelling.G_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Gb", EnharmonicSpelling.G_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("G", EnharmonicSpelling.G);
        STANDARD_ENHARMONIC_SPELLINGS.put("G_", EnharmonicSpelling.G_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("G#", EnharmonicSpelling.G_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Gx", EnharmonicSpelling.G_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Abb", EnharmonicSpelling.A_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Ab", EnharmonicSpelling.A_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("A", EnharmonicSpelling.A);
        STANDARD_ENHARMONIC_SPELLINGS.put("A_", EnharmonicSpelling.A_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("A#", EnharmonicSpelling.A_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Ax", EnharmonicSpelling.A_DOUBLE_SHARP);

        STANDARD_ENHARMONIC_SPELLINGS.put("Bbb", EnharmonicSpelling.B_DOUBLE_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("Bb", EnharmonicSpelling.B_FLAT);
        STANDARD_ENHARMONIC_SPELLINGS.put("B", EnharmonicSpelling.B);
        STANDARD_ENHARMONIC_SPELLINGS.put("B_", EnharmonicSpelling.B_NATURAL);
        STANDARD_ENHARMONIC_SPELLINGS.put("B#", EnharmonicSpelling.B_SHARP);
        STANDARD_ENHARMONIC_SPELLINGS.put("Bx", EnharmonicSpelling.B_DOUBLE_SHARP);
    }

    private Letter letter;
    private List<Accidental> accidentals;
    private List<Accidental> simplifiedAccidentals;
    private String name;
    private int baseMidiValue;

    private static final String VALID_SYMBOLS_REGEX = "^[A-Ga-g](\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x_])*$";
    public static final Pattern PATTERN = Pattern.compile(VALID_SYMBOLS_REGEX);

    private EnharmonicSpelling() {}

    private EnharmonicSpelling(BaseEnharmonicSpelling baseEnharmonicSpelling) {
        this.letter = baseEnharmonicSpelling.letter;
        this.accidentals = Collections.unmodifiableList(Collections.singletonList(baseEnharmonicSpelling.accidental));
        this.simplifiedAccidentals = Collections.unmodifiableList(Collections.singletonList(baseEnharmonicSpelling.accidental));
        this.name = baseEnharmonicSpelling.baseName;
        this.baseMidiValue = baseEnharmonicSpelling.baseMidiValue;
    }

    private EnharmonicSpelling(String alias, boolean wantNaturalSymbol) {
        this.letter = Letter.valueOf(String.valueOf(alias.charAt(0)));
        this.accidentals = new ArrayList<>();
        String accSymbols = alias.substring(1);
        for (int i = 1; i < accSymbols.length(); i++) {
            String token = accSymbols.substring(i - 1, i);
            Accidental toAdd = Accidental.getBySymbol(token);
            this.accidentals.add(toAdd);
        }
        this.simplifiedAccidentals = Accidental.simplify(this.accidentals, wantNaturalSymbol);
        this.name = letter.name() + Accidental.convertToDisplaySymbols(accSymbols, true);
        this.baseMidiValue = letter.baseMidiValue + Accidental.sumAccidentalsToSemitoneModifier(accSymbols);
    }

    public static EnharmonicSpelling withName(String name, boolean wantNaturalSymbol) {

        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid baseName for enharmonic spelling provided: " + name);
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
                throw new RuntimeException("Could not create enharmonic spelling with baseName: " + name);
            }
        }

    }

    public EnharmonicSpelling apply(Accidental toApply, boolean simplify, boolean wantNaturalSymbol) {

        List<Accidental> allAccidentals = new ArrayList<>(this.accidentals);
        allAccidentals.add(toApply);

        if (simplify) {
            allAccidentals = Accidental.simplify(allAccidentals, wantNaturalSymbol);
        }

        StringBuilder sb = new StringBuilder();
        allAccidentals.forEach(a -> sb.append(a.utf8Symbol));

        String newName = this.letter.name() + sb.toString();
        return EnharmonicSpelling.withName(newName, wantNaturalSymbol);

    }

    @Override
    public boolean isEnharmonicTo(@NotNull EnharmonicSpelling other) {
        return this.baseMidiValue == other.baseMidiValue;
    }

    @Override
    public String getBaseName() {
        return this.name;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        accidentals.forEach(a -> sb.append(a.utf8Symbol));
        return this.letter + sb.toString();
    }
}
