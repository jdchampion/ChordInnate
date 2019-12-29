package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.Tag;
import chordinnate.entity.validation.Phase1And2Validation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ScaleTypeRepository;
import chordinnate.service.ScaleTypeService;
import com.ibm.icu.util.Region;
import org.apache.commons.lang3.BooleanUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(ScaleTypeServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

    public static final String SERVICE_NAME = "scaleTypeService";

    private final ScaleTypeRepository repository;
    private final Validator validator;

    @Autowired
    ScaleTypeServiceImpl(ScaleTypeRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public List<ScaleType> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ScaleType> findById(@NotNull Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ScaleType> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<ScaleType> findAllByRegion(Region region, boolean includeSubRegions) {

        if (!includeSubRegions) {
            return repository.findAllByOriginId(region.getNumericCode());
        }

        Set<Region> set = getAllSubRegions(region, new HashSet<>());

        Set<Integer> codes = set.stream()
                .map(Region::getNumericCode)
                .collect(Collectors.toSet());

        return repository.findAllByOriginId(codes);
    }

    private static Set<Region> getAllSubRegions(Region parentRegion, @NotNull Set<Region> set) {
        if (parentRegion == null) {
            return set;
        }

        set.add(parentRegion);

        for (Region r : parentRegion.getContainedRegions()) {
            set = getAllSubRegions(r, set);
        }

        return set;
    }

    @Override
    public Optional<ScaleType> findByIntervals(Interval... intervals) {
        return repository.findByIntervals(intervals);
    }

    // TODO: revisit this method later to refine the API
//    @Override
//    public List<ScaleType> findAllByIntervals(Collection<Interval> intervals, boolean includeDuplicates) {
//        return null;
//    }

    @Override
    public List<ScaleType> findAllBySize(int min, int max) {

        if (min < 0 || max < 0 || max > min) {
            return Collections.emptyList();
        }

        return repository.findAllBySizeRange(min, max);
    }

    @Override
    public List<ScaleType> findAllByTags(Collection<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> tagNames = tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());

        return repository.findAllByTag(tagNames);
    }

    @Override
    public ScaleType save(ScaleType scaleType) {

        if (scaleType == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ScaleType>> violations = validator.validate(scaleType, Phase1And2Validation.class);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        if (scaleType.getId() != null) {

            ScaleType fromDB = repository.findById(scaleType.getId()).orElse(null);

            if (fromDB == null) {
                throw new ChordInnateException("Scale Type ID not found: " + scaleType.getId());
            }

            if (BooleanUtils.isTrue(fromDB.getPreset())) {
                throw new ChordInnateException("Overwriting preset scale types is not allowed");
            }
        }

        if (BooleanUtils.isTrue(scaleType.getPreset())) {
            throw new ChordInnateException("Creating new preset scale types is not allowed");
        }

        return repository.save(scaleType);
    }

    @Override
    public void delete(ScaleType scaleType) {

        if (scaleType == null || scaleType.getId() == null) {
            return;
        }

        Optional<ScaleType> optional = repository.findById(scaleType.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ScaleType toDelete = optional.get();
            if (!scaleType.equals(toDelete)) {
                String errorMessage = "Cannot delete scale type #%d (%s): entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, scaleType.getId(), scaleType.getName()));
            }

            if (BooleanUtils.isTrue(toDelete.getPreset())) {
                String errorMessage = "Cannot delete preset scale types";
                throw new ChordInnateException(errorMessage);
            }
        }

        repository.delete(scaleType);
    }
}
