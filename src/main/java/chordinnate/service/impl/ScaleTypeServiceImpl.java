package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ScaleTypeRepository;
import chordinnate.service.ScaleTypeService;
import com.ibm.icu.util.Region;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(ScaleTypeServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

    public static final String SERVICE_NAME = "scaleTypeService";

    private final ScaleTypeRepository repository;

    @Autowired
    ScaleTypeServiceImpl(ScaleTypeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Iterable<ScaleType> findAll() {
        return repository.findAll();
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
    public Iterable<ScaleType> findAllByRegion(Region region, boolean includeSubRegions) {

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
//    public Iterable<ScaleType> findAllByIntervals(Collection<Interval> intervals, boolean includeDuplicates) {
//        return null;
//    }

    @Override
    public Iterable<ScaleType> findAllBySize(int min, int max) {

        if (min < 0 || max < 0 || max > min) {
            return Collections.emptyList();
        }

        return repository.findAllBySizeRange(min, max);
    }

    @Override
    public Iterable<ScaleType> findAllByTags(Collection<ScaleTypeTag> tags) {
        if (tags == null || tags.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> tagNames = tags.stream()
                .map(ScaleTypeTag::getName)
                .collect(Collectors.toList());

        return repository.findAllByTag(tagNames);
    }

    @Override
    public ScaleType save(ScaleType scaleType) {

        Pair<Boolean, String> validationResult = ScaleTypeValidator.validateBeforeSave(scaleType, repository);

        if (BooleanUtils.isFalse(validationResult.getLeft())) {
            throw new ChordInnateException(validationResult.getRight());
        }

        /*
         * Auto-set specific columns that
         * should not be manipulated from outside the API or backend,
         * but are required for integrity of the DB.
         */
        scaleType.setPreset(Boolean.FALSE); // Existing presets should never reach this
        scaleType.setSize(scaleType.getIntervals().length); // Setting for consistency

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
                String errorMessage = "Cannot delete scale type #%d (%s): entity data does not match record data.";
                throw new ChordInnateException(String.format(errorMessage, scaleType.getId(), scaleType.getName()));
            }

            if (BooleanUtils.isTrue(toDelete.getPreset())) {
                String errorMessage = "Cannot delete preset scale types.";
                throw new ChordInnateException(errorMessage);
            }
        }

        repository.delete(scaleType);
    }
}
