package chordinnate.model.musictheory.temporal.rhythm;


import chordinnate.model.util.MathUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class for musical time measurement units.
 *
 * Created by Joseph on 7/1/16.
 */
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Beat {

    public static final Fraction VALUE_WHOLE = Fraction.ONE;
    public static final Fraction VALUE_HALF = Fraction.ONE_HALF;
    public static final Fraction VALUE_QUARTER = Fraction.ONE_QUARTER;
    public static final Fraction VALUE_EIGHTH = Fraction.getFraction(1, 8);
    public static final Fraction VALUE_SIXTEENTH = Fraction.getFraction(1, 16);
    public static final Fraction VALUE_THIRTY_SECOND = Fraction.getFraction(1, 32);
    public static final Fraction VALUE_SIXTY_FOURTH = Fraction.getFraction(1, 64);

    public static final Beat DOUBLE_WHOLE = new Beat(Fraction.getFraction(2, 1), 0, 1);
    public static final Beat WHOLE = new Beat(VALUE_WHOLE, 0, 1);
    public static final Beat HALF = new Beat(VALUE_HALF, 0, 1);
    public static final Beat QUARTER = new Beat(VALUE_QUARTER, 0, 1);
    public static final Beat EIGHTH = new Beat(VALUE_EIGHTH, 0, 1);
    public static final Beat SIXTEENTH = new Beat(VALUE_SIXTEENTH, 0, 1);
    public static final Beat THIRTY_SECOND = new Beat(VALUE_THIRTY_SECOND, 0, 1);
    public static final Beat SIXTY_FOURTH = new Beat(VALUE_SIXTY_FOURTH, 0, 1);

    public static final Beat DOTTED_HALF = new Beat(VALUE_HALF, 1, 1);
    public static final Beat DOTTED_QUARTER = new Beat(VALUE_QUARTER, 1, 1);
    public static final Beat DOTTED_EIGHTH = new Beat(VALUE_EIGHTH, 1, 1);
    public static final Beat DOTTED_SIXTEENTH = new Beat(VALUE_SIXTEENTH, 1, 1);
    public static final Beat DOTTED_THIRTY_SECOND = new Beat(VALUE_THIRTY_SECOND, 1, 1);

    public static final Beat TRIPLET_HALF = new Beat(VALUE_HALF, 0, 3);
    public static final Beat TRIPLET_QUARTER = new Beat(VALUE_QUARTER, 0, 3);
    public static final Beat TRIPLET_EIGHTH = new Beat(VALUE_EIGHTH, 0, 3);
    public static final Beat TRIPLET_SIXTEENTH = new Beat(VALUE_SIXTEENTH, 0, 3);
    public static final Beat TRIPLET_THIRTY_SECOND = new Beat(VALUE_THIRTY_SECOND, 0, 3);

    private Fraction beatValue;
    private int dotCount = 0;
    private int tupletCount = 1;

    private static final Map<String, Beat> STANDARD_BEAT_LOOKUP = Collections.unmodifiableMap(initMap());

    private static Map<String, Beat> initMap() {
        Map<String, Beat> map = new HashMap<>();

        try {
            for (Field field : Class.forName(Beat.class.getName()).getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Beat.class)) {
                    Beat toAdd = (Beat) field.get(Class.forName(Beat.class.getName()));
                    String key = generateKey(toAdd.beatValue, toAdd.dotCount, toAdd.tupletCount);
                    map.put(key, toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }

    private static String generateKey(Fraction duration, int dots, int tuplet) {
        return duration.toProperString() + "_" + dots + "_" + tuplet;
    }

    public boolean isDotted() {
        return dotCount > 0;
    }

    public boolean isTuplet() {
        return tupletCount > 1;
    }

    public double getDuration() {
        boolean noDotValue = !isDotted();
        boolean noTuplet = !isTuplet();

        if (noDotValue && noTuplet) return beatValue.doubleValue();
        if (noDotValue) return beatValue.doubleValue() * getTupletRatio();
        if (noTuplet) return beatValue.doubleValue() * getDotValueRatio();
        return beatValue.doubleValue() * getDotValueRatio() * getTupletRatio();
    }

    private double getTupletRatio() {
        int tmp = tupletCount % 2 == 0 ? (tupletCount / (tupletCount / 2)) : (tupletCount - 1);
        return tmp * MathUtils.invert(tupletCount);
    }

    private double getDotValueRatio() {
        double sum = 0;
        for (int i = 0; i < dotCount + 1; i++) {
            sum += Math.pow(0.5, i);
        }
        return sum;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder {
        Fraction beatValue;
        private int dotCount = 0;
        private int tupletValue = 1;

        public Builder value(@NotNull Fraction beatValue) {
            this.beatValue = beatValue;
            return this;
        }

        public Builder dots(int dotCount) {
            this.dotCount = dotCount;
            return this;
        }

        public Builder tuplet(int tupletValue) {
            this.tupletValue = tupletValue;
            return this;
        }

        public Beat build() {
            validate();
            String key = generateKey(beatValue, dotCount, tupletValue);
            return STANDARD_BEAT_LOOKUP.getOrDefault(key, new Beat(beatValue, dotCount, tupletValue));
        }

        private void validate() {
            if (beatValue.doubleValue() > 1) {
                throw new IllegalArgumentException("Beat value must be <= 1");
            }
            if (!MathUtils.isPowerOf(2, beatValue.reduce().getDenominator())) {
                throw new IllegalArgumentException("Beat value must be reducible to a fraction whose denominator is a power of 2");
            }
            if (dotCount < 0) {
                throw new IllegalArgumentException("Beat cannot contain a negative dot count");
            }
            if (tupletValue < 1) {
                throw new IllegalArgumentException("Beat cannot contain a tuplet value < 1");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
