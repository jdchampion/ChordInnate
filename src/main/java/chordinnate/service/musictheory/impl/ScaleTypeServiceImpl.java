package chordinnate.service.musictheory.impl;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import chordinnate.repository.musictheory.ScaleTypeRepository;
import chordinnate.service.musictheory.ScaleTypeService;
import com.ibm.icu.util.Region;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("scaleTypeService")
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

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
    public Optional<ScaleType> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ScaleType> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Collection<ScaleType> findWithinRegion(Region region) {

        Set<Region> set = getAllSubRegions(region, new HashSet<>());

        Set<Integer> codes = set.stream()
                .map(Region::getNumericCode)
                .collect(Collectors.toSet());


        return repository.findAllByOriginIds(codes);
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
    public ScaleType save(ScaleType scaleType) {
        return repository.save(scaleType);

        // TODO: catch PersistenceException and throw ChordInnateException with friendly message?
    }

    @Override
    public void delete(ScaleType scaleType) {
        repository.delete(scaleType);
    }
}
