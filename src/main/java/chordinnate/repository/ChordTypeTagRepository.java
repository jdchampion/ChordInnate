package chordinnate.repository;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChordTypeTagRepository extends CrudRepository<ChordTypeTag, Integer> {

    @Query("SELECT t FROM ChordTypeTag t WHERE UPPER(t.name) = UPPER(:name)")
    Optional<ChordTypeTag> findByName(@NotNull @Param("name") String name);

    @Query("SELECT t FROM ChordTypeTag t JOIN FETCH ChordTypeTagRelation sttr ON sttr.matchingTag.id = t.id JOIN FETCH ChordType st ON sttr.matchingChordType.id = st.id WHERE sttr.matchingChordType = :chordType")
    Iterable<ChordTypeTag> findAllFor(@NotNull @Param("chordType") ChordType chordType);
}
