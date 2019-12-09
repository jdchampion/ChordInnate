package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

final class ChordTypeValidator {

    public static Pair<Boolean, String> validateBeforeSave(ChordType chordType, ChordTypeRepository repository) {
        if (chordType == null || isBlankEntity(chordType)) {
            return Pair.of(Boolean.FALSE, "No chord type to save.");
        }

        StringJoiner errorMessages = new StringJoiner("\r\n\r\n");

        /*
         * 1. Ensure all required data is on the entity before attempting to save
         */
        Pair<Boolean, String> missingColumns = validateNotNull(
                Pair.of("Symbol", chordType.getSymbol()),
                Pair.of("Roman Numeral Symbol", chordType.getRnSymbol()),
                Pair.of("Roman Numeral Capital", chordType.getRnCapital()),
                Pair.of("Roman Numeral Precedence", chordType.getRnPrecedence()),
                Pair.of("Intervals", chordType.getIntervals()));

        if (BooleanUtils.isTrue(missingColumns.getLeft())) {
            errorMessages.add("The following required fields are missing: " + missingColumns.getRight());
        }

        /*
         * 2. Validate the entity's data against DB or logical constraints
         */
        StringJoiner invalidColumns = new StringJoiner(", ");
        if (chordType.getSymbol() != null && StringUtils.isBlank(chordType.getSymbol())) {
            invalidColumns.add("Symbol (cannot be blank)");
        }

        if (chordType.getRnPrecedence() != null && chordType.getRnPrecedence() <= 0) {
            invalidColumns.add("Roman Numeral Precedence (must be > 0)");
        }

        if (chordType.getIntervals() != null) {
            if (chordType.getIntervals().length < 2) {
                invalidColumns.add("Intervals (must contain at least two intervals)");
            } else {
                Interval[] intervals = chordType.getIntervals();

                Set<String> uniques = Arrays.stream(intervals)
                        .map(Interval::getCompoundShortName)
                        .collect(Collectors.toSet());

                if (intervals.length != uniques.size()) {
                    invalidColumns.add("Intervals (cannot contain duplicates)");
                }

                // Not really required for chords, but done for data integrity purposes
                int comparison = intervals[0].compareTo(intervals[1]);
                for (int i = 2; i < intervals.length + 1; i++) {
                    int currentComparison = intervals[i - 2].compareTo(intervals[i - 1]);
                    if (currentComparison == 0 || currentComparison != comparison) {
                        invalidColumns.add("Intervals (must be increasing or decreasing at each step)");
                        break;
                    }
                    comparison = currentComparison;
                }
            }
        }

        if (chordType.getSize() != null && chordType.getSize() != chordType.getIntervals().length) {
            invalidColumns.add("Size (must match the number of intervals)");
        }

        if (invalidColumns.length() > 0) {
            errorMessages.add("The following fields are invalid: " + invalidColumns.toString());
        }

        /*
         * 3. Check the DB's UNIQUE constraints.
         * Verify we're not adding / updating any presets.
         */
        if (chordType.getId() != null) {

            if (chordType.getSymbol() != null) {

                List<ChordType> list = repository.findAnyMatchingUniqueConstraints(chordType.getId(), chordType.getSymbol());

                Pair<Boolean, String> pair = validatePresetAndUniqueConstraints(chordType, list);

                if (BooleanUtils.isFalse(pair.getLeft())) {
                    errorMessages.add(pair.getRight());
                }
            }

        } else {

            if (chordType.getSymbol() != null) {
                Optional<ChordType> matched = repository.findBySymbol(chordType.getSymbol());

                if (matched.isPresent()) {
                    // Existing item
                    Pair<Boolean, String> pair = validatePresetAndUniqueConstraints(chordType, Collections.singletonList(matched.get()));

                    if (BooleanUtils.isFalse(pair.getLeft())) {
                        errorMessages.add(pair.getRight());
                    }

                } else {
                    // New item. Fail any that are defined as a preset
                    if (BooleanUtils.isTrue(chordType.getPreset())) {
                        errorMessages.add("Cannot add new preset chord types.");
                    }
                }
            }

        }

        if (errorMessages.length() > 0) {
            return Pair.of(Boolean.FALSE, errorMessages.toString());
        }

        return Pair.of(Boolean.TRUE, StringUtils.EMPTY);
    }

    /**
     * Helper method to determine whether the entity passed in contains no set member variables.
     * @param entity
     * @return
     */
    private static boolean isBlankEntity(ChordType entity)  {

        for (Field field : ChordType.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException("failed to check fields of ChordType");
            }
        }

        return true;
    }

    /**
     * Helper method to validate that all columns provided to this method
     * are not null on the entity
     *
     * @param columns
     * @return
     */
    @SafeVarargs
    private static Pair<Boolean, String> validateNotNull(Pair<String, Object>... columns) {

        StringJoiner violatedColumns = new StringJoiner(", ");

        for (Pair<String, Object> column : columns) {
            if (column.getRight() == null) {
                violatedColumns.add(column.getLeft());
            }
        }

        return Pair.of(violatedColumns.length() > 0, violatedColumns.toString());
    }

    /**
     * Helper method to test whether a ChordType can be inserted / updated
     * according to UNIQUE constraints and the PRESET column.
     *
     * @param newChordType
     * @param list
     * @return
     */
    private static Pair<Boolean, String> validatePresetAndUniqueConstraints(ChordType newChordType, List<ChordType> list) {

        if (list.isEmpty()) {

            // No items from DB match

            if (BooleanUtils.isTrue(newChordType.getPreset())) {
                return Pair.of(Boolean.FALSE, "Cannot add new preset chord types.");
            }
            return Pair.of(Boolean.TRUE, StringUtils.EMPTY);
        }

        if (list.size() == 1) {

            // Exactly one item from DB matches

            if (list.get(0).getPreset()) {
                return Pair.of(Boolean.FALSE, "Cannot overwrite a preset chord type.");
            } else {
                return Pair.of(Boolean.TRUE, StringUtils.EMPTY);
            }
        } else {

            /*
             * More than one item from DB matches.
             * In this situation, newChordType contains the ID of 'A'
             * but the symbol of 'B', which is not allowed to be stored
             * because doing so would violate the unique constraint of either 'A' or 'B'.
             */

            boolean containsPreset = list.stream().anyMatch(ChordType::getPreset);
            String message = "%s chord type is already defined with this symbol: %s\r\n"
                    + "Please choose a different symbol%s.";

            message = String.format(message,
                    (containsPreset ? "A preset" : "Another"),
                    newChordType.getSymbol(),
                    (containsPreset ? "" : " for one of them")
            );

            return Pair.of(Boolean.FALSE, message);
        }
    }
}
