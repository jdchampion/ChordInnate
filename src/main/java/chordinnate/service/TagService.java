package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Used for basic CRUD operations on {@link Tag}
 */
@Service
public interface TagService extends BaseService<Tag, Integer> {

    Optional<Tag> findByName(String name);

    List<Tag> findAllFor(ChordType chordType);

    Tag save(Tag chordTypeTag);

    void delete(Tag chordTypeTag);
}
