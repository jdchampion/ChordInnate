package chordinnate.service;

import chordinnate.model.musictheory.pitch.interval.set.ChordType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Joseph on 6/4/17.
 */
public interface ChordTypeService {

    /**
     * Retrieves the ChordType from the database matching the specified ID, if possible.
     * @param id the ChordType's unique ID (primary key)
     * @return {@link Optional}<{@link ChordType}> that was requested
     */
    Optional<ChordType> findById(int id);

    /**
     * Retrieves the ChordType from the database matching the specified symbol, if possible.
     * @param symbol the ChordType's unique symbol
     * @return {@link Optional}<{@link ChordType}> that was requested
     */
    Optional<ChordType> findBySymbol(String symbol);

    /**
     * Retrieves all supported ChordTypes from the database.
     * @return {@link List}<{@link ChordType}> of all supported ChordTypes
     */
    List<ChordType> findAll();
}
