package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import chordinnate.entity.ChordTypeTagRelation;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Used for basic CRUD operations on relations between {@link ChordType}s and their corresponding {@link ChordTypeTag}s.
 */
@Service
public interface ChordTypeTagRelationService {

    // TODO: not sure if it's useful to expose this
    Optional<ChordTypeTagRelation> findById(Integer id);

    // TODO: not sure if this is useful since ChordTypeTagService gets all tags (which is what we really want)
    Iterable<ChordTypeTagRelation> findAllFor(ChordType chordType);

    /**
     * Establishes an association between chord type and tag.
     * @param chordTypeTagRelation
     * @return
     */
    ChordTypeTagRelation save(ChordTypeTagRelation chordTypeTagRelation);

    /**
     * Removes the association between chord type and tag.
     * @param chordTypeTagRelation
     */
    void delete(ChordTypeTagRelation chordTypeTagRelation);
}
