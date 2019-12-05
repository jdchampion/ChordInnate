package chordinnate.service;

import chordinnate.entity.ScaleType;
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

    Collection<ScaleType> findWithinRegion(Region region);

    ScaleType save(ScaleType scaleType);

    void delete(ScaleType scaleType);

}
