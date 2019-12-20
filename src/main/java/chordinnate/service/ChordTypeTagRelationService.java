package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import chordinnate.entity.ChordTypeTagRelation;
import org.springframework.stereotype.Service;

/**
 * Used for basic CRUD operations on relations between {@link ChordType}s and their corresponding {@link ChordTypeTag}s.
 */
@Service
public interface ChordTypeTagRelationService {

    /**
     * Establishes an association between chord type and tag.
     * @param relation
     * @return
     */
    ChordTypeTagRelation save(ChordTypeTagRelation relation);

    /**
     * Removes the association between chord type and tag.
     * @param relation
     */
    void delete(ChordTypeTagRelation relation);

    void deleteById(Integer id);

    void deleteAllByTag(ChordTypeTag tag);

    void deleteAllByChordType(ChordType chordType);

    void deleteByChordTypeAndTag(ChordType chordType, ChordTypeTag tag);
}
