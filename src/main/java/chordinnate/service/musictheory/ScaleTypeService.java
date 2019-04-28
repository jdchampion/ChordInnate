package chordinnate.service.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Joseph on 6/14/17.
 */
public interface ScaleTypeService extends BaseService<ScaleType> {

    /**
     * Retrieves the ScaleType from the database matching the specified name, if possible.
     * @param name the ScaleType's unique name
     * @return {@link Optional}<{@link ScaleType}> that was requested
     */
    Optional<ScaleType> findByName(String name);

}
