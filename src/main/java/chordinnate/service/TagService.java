package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.ScaleType;
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

    List<Tag> findAllFor(ScaleType scaleType);

    Tag save(Tag tag);

    void delete(Tag tag);
}
