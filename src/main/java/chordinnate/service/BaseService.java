package chordinnate.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BaseService<T, ID> {

    /**
     * Retrieves all items of the specified type from the database.
     * @return {@link List}<{@link T}> of all items
     */
    Iterable<T> findAll();

    /**
     * Retrieves the item from the database matching the specified ID, if possible.
     * @param id the item's unique ID (primary key)
     * @return {@link Optional}<{@link T}> item that was requested
     */
    Optional<T> findById(ID id);

}
