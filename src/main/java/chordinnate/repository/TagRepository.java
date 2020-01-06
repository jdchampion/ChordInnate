package chordinnate.repository;

import chordinnate.entity.ChordType;
import chordinnate.entity.ScaleType;
import chordinnate.entity.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query("SELECT t FROM Tag t WHERE UPPER(t.name) = UPPER(:name)")
    Optional<Tag> findByName(@NotNull @Param("name") String name);

    @Query("SELECT t FROM Tag t JOIN FETCH ChordTypeTagRelation cttr ON cttr.matchingTag.id = t.id JOIN FETCH ChordType st ON cttr.matchingChordType.id = st.id WHERE cttr.matchingChordType = :chordType")
    List<Tag> findAllFor(@NotNull @Param("chordType") ChordType chordType);

    @Query("SELECT t FROM Tag t JOIN FETCH ScaleTypeTagRelation sttr ON sttr.matchingTag.id = t.id JOIN FETCH ChordType st ON sttr.matchingScaleType.id = st.id WHERE sttr.matchingScaleType = :scaleType")
    List<Tag> findAllFor(@NotNull @Param("scaleType") ScaleType scaleType);
}
