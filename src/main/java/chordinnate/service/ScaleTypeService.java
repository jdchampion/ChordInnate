package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.Tag;
import chordinnate.model.musictheory.pitch.interval.Interval;
import com.ibm.icu.util.Region;

import java.util.Collection;
import java.util.List;
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

    List<ScaleType> findAllByRegion(Region region, boolean includeSubRegions);

    Optional<ScaleType> findByIntervals(Interval... intervals);

    // TODO: revisit this method later to refine the API
//    Iterable<ScaleType> findAllByIntervals(Collection<Interval> intervals, boolean includeDuplicates);

    List<ScaleType> findAllBySize(int min, int max);

    List<ScaleType> findAllByTags(Collection<Tag> tags);

    ScaleType save(ScaleType scaleType);

    void delete(ScaleType scaleType);

}
