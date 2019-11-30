package chordinnate.service.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import chordinnate.service.BaseService;

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

    ScaleType save(ScaleType scaleType);

    void delete(ScaleType scaleType);

}
