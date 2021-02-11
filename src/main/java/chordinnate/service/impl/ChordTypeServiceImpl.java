package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.Tag;
import chordinnate.entity.validation.Phase1And2Validation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeRepository;
import chordinnate.service.ChordTypeService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service(ChordTypeServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeServiceImpl implements ChordTypeService {

    public static final String SERVICE_NAME = "chordTypeService";

    private final ChordTypeRepository repository;
    private final Validator validator;

    @Autowired
    ChordTypeServiceImpl(ChordTypeRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<ChordType> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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
    public List<ChordType> findAllBySize(int min, int max) {

        if (min < 0 || max < 0 || max > min) {
            return Collections.emptyList();
        }

        return repository.findAllBySizeRange(min, max);
    }

    @Override
    public List<ChordType> findAllByTags(Collection<Tag> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return Collections.emptyList();
        }

        List<String> tagNames = tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());

        return repository.findAllByTag(tagNames);
    }

    @Override
    public ChordType save(ChordType chordType) {

        if (chordType == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ChordType>> violations = validator.validate(chordType, Phase1And2Validation.class);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        if (chordType.getId() != null) {

            ChordType fromDB = repository.findById(chordType.getId()).orElse(null);

            if (fromDB == null) {
                throw new ChordInnateException("Chord Type ID not found: " + chordType.getId());
            }

            if (BooleanUtils.isTrue(fromDB.getPreset())) {
                throw new ChordInnateException("Overwriting preset chord types is not allowed");
            }
        }

        if (BooleanUtils.isTrue(chordType.getPreset())) {
            throw new ChordInnateException("Creating new preset chord types is not allowed");
        }

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
                String errorMessage = "Cannot delete chord type #%d (%s): entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, chordType.getId(), chordType.getSymbol()));
            }

            if (BooleanUtils.isTrue(toDelete.getPreset())) {
                String errorMessage = "Cannot delete preset chord types";
                throw new ChordInnateException(errorMessage);
            }
        }

        repository.delete(chordType);
    }

}
