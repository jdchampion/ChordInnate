package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.entity.ScaleTypeTagRelation;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Used for basic CRUD operations on relations between {@link ScaleType}s and their corresponding {@link ScaleTypeTag}s.
 */
@Service
public interface ScaleTypeTagRelationService {

    // TODO: not sure if it's useful to expose this
    Optional<ScaleTypeTagRelation> findById(Integer id);

    // TODO: not sure if this is useful since ScaleTypeTagService gets all tags (which is what we really want)
    Iterable<ScaleTypeTagRelation> findAllFor(ScaleType scaleType);

    /**
     * Establishes an association between scale type and tag.
     * @param scaleTypeTagRelation
     * @return
     */
    ScaleTypeTagRelation save(ScaleTypeTagRelation scaleTypeTagRelation);

    /**
     * Removes the association between scale type and tag.
     * @param scaleTypeTagRelation
     */
    void delete(ScaleTypeTagRelation scaleTypeTagRelation);
}
