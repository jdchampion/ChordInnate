package chordinnate.service;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Joseph on 6/14/17.
 */
public interface ScaleTypeManager {

    /**
     * Retrieves the ScaleType from the database matching the specified ID, if possible.
     * @param id the ScaleType's unique ID (primary key)
     * @return {@link Optional}<{@link ScaleType}> that was requested
     */
    Optional<ScaleType> findById(int id);

    /**
     * Retrieves the ScaleType from the database matching the specified name, if possible.
     * @param name the ScaleType's unique name
     * @return {@link Optional}<{@link ScaleType}> that was requested
     */
    Optional<ScaleType> findByName(String name);

    /**
     * Retrieves all supported ScaleTypes from the database.
     * @return {@link List}<{@link ScaleType}> of all supported ScaleTypes
     */
    List findAll();
}
