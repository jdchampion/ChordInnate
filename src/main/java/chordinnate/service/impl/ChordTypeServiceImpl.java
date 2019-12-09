package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeRepository;
import chordinnate.service.ChordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

        Pair<Boolean, String> validationResult = ChordTypeValidator.validateBeforeSave(chordType, repository);

        if (BooleanUtils.isFalse(validationResult.getLeft())) {
            throw new ChordInnateException(validationResult.getRight());
        }

        /*
         * Auto-set specific columns that
         * should not be manipulated from outside the API or backend,
         * but are required for integrity of the DB.
         */
        chordType.setPreset(Boolean.FALSE); // Existing presets should never reach this
        chordType.setSize(chordType.getIntervals().length); // Setting for consistency

        return repository.save(chordType);
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
