package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.model.musictheory.pitch.interval.Interval;
import com.ibm.icu.util.Region;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Joseph on 6/14/17.
 */
public interface ScaleTypeService extends BaseService<ScaleType, Integer> {

    /**
     * Retrieves the ScaleType from the database matching the specified name, if possible.
     * @param name the ScaleType's unique name
     * @return {@link Optional}<{@link ScaleType}> that was requested
     */
    Optional<ScaleType> findByName(String name);

    Iterable<ScaleType> findAllByRegion(Region region, boolean includeSubRegions);

    Optional<ScaleType> findByIntervals(Interval... intervals);

    // TODO: revisit this method later to refine the API
//    Iterable<ScaleType> findAllByIntervals(Collection<Interval> intervals, boolean includeDuplicates);

    Iterable<ScaleType> findAllBySize(int min, int max);

    Iterable<ScaleType> findAllByTags(Collection<ScaleTypeTag> tags);

    ScaleType save(ScaleType scaleType);

    void delete(ScaleType scaleType);

}
