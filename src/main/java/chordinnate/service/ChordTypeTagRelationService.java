package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.entity.Tag;
import org.springframework.stereotype.Service;

/**
 * Used for basic CRUD operations on relations between {@link ChordType}s and their corresponding {@link Tag}s.
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

    void deleteAllByTag(Tag tag);

    void deleteAllByChordType(ChordType chordType);

    void deleteByChordTypeAndTag(ChordType chordType, Tag tag);
}
