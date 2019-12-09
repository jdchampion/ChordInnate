package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Used for basic CRUD operations on {@link ChordTypeTag}
 */
@Service
public interface ChordTypeTagService {

    Optional<ChordTypeTag> findById(Integer id);

    Optional<ChordTypeTag> findByName(String name);

    Iterable<ChordTypeTag> findAllFor(ChordType chordType);

    ChordTypeTag save(ChordTypeTag chordTypeTag);

    void delete(ChordTypeTag chordTypeTag);
}
