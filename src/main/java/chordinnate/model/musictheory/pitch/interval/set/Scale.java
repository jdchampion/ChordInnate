package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.RegionEntity;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.service.Services;
import chordinnate.service.musictheory.ScaleTypeService;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 7/15/16.
 * References: http://pianoencyclopedia.com/scales/
 * http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public class Scale extends HorizontalIntervalSet {

    @Getter(AccessLevel.PACKAGE)
    private ScaleType scaleType;

    private static final String SCALE_REGEX = "^([A-Ga-g])((\uD834\uDD2B|\u266d|\u266e|\u266f|\uD834\uDD2A|[b#x])*) (.+)$";
    private static final Pattern PATTERN = Pattern.compile(SCALE_REGEX);

    private static final ScaleTypeService service = Services.getScaleTypeService();

    public Scale(@NotNull String name) {

        Matcher matcher = PATTERN.matcher(name);

        boolean valid = false;

        if (matcher.matches()) {
            String rootName = matcher.group(1).toUpperCase() + (matcher.group(2) == null ? "" : matcher.group(2));
            String scaleTypeName = matcher.group(4);

            PitchClass root = PitchClass.withName(rootName, rootName.contains(Accidental.NATURAL.utf8Symbol));
            Optional<ScaleType> scaleTypeOptional = service.findByName(scaleTypeName);
            if (scaleTypeOptional.isPresent()) {
                super.commonInitializations(root, scaleTypeOptional.get().getIntervals());
                this.scaleType = scaleTypeOptional.get();
                valid = true;
            }
        }

        if (!valid) {
            throw new IllegalArgumentException("Invalid scale name [" + name + "]");
        }
    }

    public Scale(@NotNull PitchClass root, @NotNull ScaleType scaleType) {
        super.commonInitializations(root, scaleType.getIntervals());
        this.scaleType = scaleType;
    }

    public String getName() {
        return root.getName() + " " + scaleType.getName();
    }

    @Nullable
    public String getOrigin() {

        RegionEntity region = scaleType.getOrigin();

        if (region == null) {
            return null;
        }

        return region.getRegionName();
    }

}
