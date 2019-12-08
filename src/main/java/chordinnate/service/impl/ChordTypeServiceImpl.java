package chordinnate.service.impl;

import chordinnate.entity.ChordTypeTag;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.entity.ChordType;
import chordinnate.repository.ChordTypeRepository;
import chordinnate.service.ChordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Slf4j
@Service(ChordTypeServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeServiceImpl implements ChordTypeService {

    public static final String SERVICE_NAME = "chordTypeService";

    private final ChordTypeRepository repository;

    @Autowired
    ChordTypeServiceImpl(ChordTypeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Iterable<ChordType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ChordType> findById(@NotNull Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ChordType> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    @Override
    public Optional<ChordType> findByIntervals(Interval... intervals) {
        return repository.findByIntervals(intervals);
    }

    // TODO: revisit this method later to refine the API
//    @Override
//    public Iterable<ChordType> findAllByIntervals(Collection<Interval[]> intervals, boolean includeDuplicates) {
//
//        if (intervals == null) return Collections.emptyList();
//
//        if (includeDuplicates) {
//
//            List<Interval[]> list = intervals.stream()
//                    .map(arr -> (Interval[]) Arrays.stream(arr)
//                            .map(i -> Interval.withShortName(i.getSimpleShortName()))
//                            .toArray()).collect(Collectors.toList());
//
//            return repository.findAllByIntervals(list);
//        }
//
//        List<ChordType> list = new ArrayList<>();
//        intervals.forEach(arr ->  {
//            Optional<ChordType> opt = findByIntervals(arr);
//            opt.ifPresent(list::add);
//        });
//
//        return list;
//    }

    @Override
    public Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence) {
        return repository.findByRomanNumeralCriteria(intervals, size, precedence);
    }

    @Override
    public Iterable<ChordType> findAllBySize(int min, int max) {

        if (min < 0 || max < 0 || max > min) {
            return Collections.emptyList();
        }

        return repository.findAllBySizeRange(min, max);
    }

    @Override
    public Iterable<ChordType> findAllByTags(Collection<ChordTypeTag> tags) {
        if (tags == null || tags.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> tagNames = tags.stream()
                .map(ChordTypeTag::getName)
                .collect(Collectors.toList());

        return repository.findAllByTag(tagNames);
    }

    @Override
    public ChordType save(ChordType chordType) {

        if (chordType == null || isBlankEntity(chordType)) {
            throw new ChordInnateException("No chord type to save.");
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
            throw new ChordInnateException(errorMessages.toString());
        }

        /*
         * 4. Auto-set specific columns that
         * should not be manipulated from outside the API or backend,
         * but are required for integrity of the DB.
         */
        chordType.setPreset(Boolean.FALSE); // Existing presets should never reach this
        chordType.setSize(chordType.getIntervals().length); // Setting for consistency

        return repository.save(chordType);
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

            if (newChordType.getPreset()) {
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

    @Override
    public void delete(ChordType chordType) {

        if (chordType == null || chordType.getId() == null) {
            return;
        }

        Optional<ChordType> optional = repository.findById(chordType.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ChordType toDelete = optional.get();
            if (!chordType.equals(toDelete)) {
                String errorMessage = "Cannot delete chord type #%d (%s): entity data does not match record data.";
                throw new ChordInnateException(String.format(errorMessage, chordType.getId(), chordType.getSymbol()));
            }

            if (BooleanUtils.isTrue(toDelete.getPreset())) {
                String errorMessage = "Cannot delete preset chord types.";
                throw new ChordInnateException(errorMessage);
            }
        }

        repository.delete(chordType);
    }

}
