package chordinnate.service.musictheory;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    /**
     * Retrieves the item from the database matching the specified ID, if possible.
     * @param id the item's unique ID (primary key)
     * @return {@link Optional}<{@link T}> item that was requested
     */
    Optional<T> findById(int id);

    /**
     * Retrieves all items of the specified type from the database.
     * @return {@link List}<{@link T}> of all items
     */
    List<T> findAll();

}
