package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.entity.ChordType;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.service.Services;
import chordinnate.service.ChordTypeService;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/17/16.
 */
public class Chord extends VerticalIntervalSet {

    @Getter(AccessLevel.PACKAGE)
    private ChordType chordType;

    private static final String CHORD_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*)([^b#x].*)$";
    private static final Pattern PATTERN = Pattern.compile(CHORD_REGEX);

    private static final ChordTypeService service = Services.getChordTypeService();

    public Chord(@NotNull String name) {

        Matcher matcher = PATTERN.matcher(name);

        boolean valid = false;

        if (matcher.matches()) {
            String rootName = matcher.group(1) + (matcher.group(2) == null ? "" : matcher.group(2));
            String chordTypeSymbol = matcher.group(4);

            PitchClass root = PitchClass.withName(rootName, rootName.contains(Accidental.NATURAL.utf8Symbol));
            Optional<ChordType> chordTypeOptional = service.findBySymbol(chordTypeSymbol);

            if (chordTypeOptional.isPresent()) {
                super.commonInitializations(root, chordTypeOptional.get().getIntervals());
                this.chordType = chordTypeOptional.get();
                valid = true;
            }

        }

        if (!valid) {
            throw new ChordInnateException("Invalid chord symbol [" + name + "]");
        }
    }

    public Chord(@NotNull PitchClass root, @NotNull ChordType chordType) {
        super.commonInitializations(root, chordType.getIntervals());
        this.chordType = chordType;
    }

    public String getName() {
        if (getInversion() == 1) {
            return root.getName() + chordType.getSymbol();
        } else {
            // Append the bass note to the name
            return root.getName() + chordType.getSymbol()
                    + "/" + pitchesByOctave.get(lowestDiatonic.octave)[getInversion() - 1].pitchClass.getName();
        }
    }

}
