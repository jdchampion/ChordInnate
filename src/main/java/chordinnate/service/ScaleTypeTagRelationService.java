package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.entity.ScaleTypeTagRelation;
import org.springframework.stereotype.Service;

/**
 * Used for basic CRUD operations on relations between {@link ScaleType}s and their corresponding {@link ScaleTypeTag}s.
 */
@Service
public interface ScaleTypeTagRelationService {

    /**
     * Establishes an association between scale type and tag.
     * @param relation
     * @return
     */
    ScaleTypeTagRelation save(ScaleTypeTagRelation relation);

    /**
     * Removes the association between scale type and tag.
     * @param relation
     */
    void delete(ScaleTypeTagRelation relation);

    void deleteById(Integer id);

    void deleteAllByTag(ScaleTypeTag tag);

    void deleteAllByScaleType(ScaleType scaleType);

    void deleteByScaleTypeAndTag(ScaleType scaleType, ScaleTypeTag tag);
}
