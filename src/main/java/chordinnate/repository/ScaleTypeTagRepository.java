package chordinnate.repository;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScaleTypeTagRepository extends CrudRepository<ScaleTypeTag, Integer> {

    @Query("SELECT t FROM ScaleTypeTag t WHERE UPPER(t.name) = UPPER(:name)")
    Optional<ScaleTypeTag> findByName(@NotNull @Param("name") String name);

    @Query("SELECT t FROM ScaleTypeTag t JOIN FETCH ScaleTypeTagRelation sttr ON sttr.matchingTag.id = t.id JOIN FETCH ScaleType st ON sttr.matchingScaleType.id = st.id WHERE sttr.matchingScaleType = :scaleType")
    Iterable<ScaleTypeTag> findAllFor(@NotNull @Param("scaleType") ScaleType scaleType);
}
