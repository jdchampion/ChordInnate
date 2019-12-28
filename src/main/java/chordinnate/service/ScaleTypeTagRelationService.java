package chordinnate.service;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.entity.Tag;
import org.springframework.stereotype.Service;

/**
 * Used for basic CRUD operations on relations between {@link ScaleType}s and their corresponding {@link Tag}s.
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

    void deleteAllByTag(Tag tag);

    void deleteAllByScaleType(ScaleType scaleType);

    void deleteByScaleTypeAndTag(ScaleType scaleType, Tag tag);
}
