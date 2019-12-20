package chordinnate.repository;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.entity.ScaleTypeTagRelation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScaleTypeTagRelationRepository extends CrudRepository<ScaleTypeTagRelation, Integer> {

    @Query("SELECT sttr FROM ScaleTypeTagRelation sttr JOIN FETCH ScaleType st ON st.id = sttr.matchingScaleType.id JOIN FETCH ScaleTypeTag t ON t.id = sttr.matchingTag.id WHERE sttr.matchingScaleType = :scaleType")
    List<ScaleTypeTagRelation> findAllByScaleType(@NotNull @Param("scaleType") ScaleType scaleType);

    @Query("SELECT sttr FROM ScaleTypeTagRelation sttr JOIN FETCH ScaleType st ON st.id = sttr.matchingScaleType.id JOIN FETCH ScaleTypeTag t ON t.id = sttr.matchingTag.id WHERE sttr.matchingTag = :scaleTypeTag")
    List<ScaleTypeTagRelation> findAllByTag(@NotNull @Param("scaleTypeTag") ScaleTypeTag tag);

    @Query("DELETE FROM ScaleTypeTagRelation sttr WHERE sttr.matchingTag = :scaleTypeTag")
    void deleteAllByTag(@NotNull @Param("scaleTypeTag") ScaleTypeTag tag);

    @Query("DELETE FROM ScaleTypeTagRelation sttr WHERE sttr.matchingScaleType = :scaleType")
    void deleteAllByScaleType(@NotNull @Param("scaleType") ScaleType scaleType);

    @Query("DELETE FROM ScaleTypeTagRelation sttr WHERE sttr.matchingScaleType = :scaleType AND sttr.matchingTag = :scaleTypeTag")
    void deleteAllByScaleTypeAndTag(@NotNull @Param("scaleType") ScaleType scaleType, @NotNull @Param("scaleTypeTag") ScaleTypeTag tag);

}
