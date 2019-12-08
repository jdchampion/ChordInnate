package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Used for basic CRUD operations on {@link ScaleTypeTag}
 */
@Service
public interface ScaleTypeTagService {

    Optional<ScaleTypeTag> findById(Integer id);

    Optional<ScaleTypeTag> findByName(String name);

    Iterable<ScaleTypeTag> findAllFor(ScaleType scaleType);

    ScaleTypeTag save(ScaleTypeTag scaleTypeTag);

    void delete(ScaleTypeTag scaleTypeTag);
}
