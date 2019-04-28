package chordinnate.dao.musictheory;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    Optional<T> findById(int id);
    List<T> findAll();
}
