package chordinnate.repository;

import chordinnate.entity.TagGroupRel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagGroupRelRepository extends CrudRepository<TagGroupRel, Integer> {
}
