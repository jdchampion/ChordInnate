package chordinnate.repository;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTagRelation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScaleTypeTagRelationRepository extends CrudRepository<ScaleTypeTagRelation, Integer> {

    @Query("SELECT sttr FROM ScaleTypeTagRelation sttr JOIN FETCH ScaleType st ON st.id = sttr.matchingScaleType.id JOIN FETCH ScaleTypeTag t ON t.id = sttr.matchingTag.id WHERE sttr.matchingScaleType = :scaleType")
    Iterable<ScaleTypeTagRelation> findAllFor(@NotNull @Param("scaleType") ScaleType scaleType);

    @Query(nativeQuery = true,
            value = "SELECT (CASE" +
            "            WHEN (SELECT COUNT(1)" +
            "                  FROM SCALE_TYPE_TAG sttr" +
            "                           JOIN SCALE_TYPE st ON sttr.scale_type_id = st.id" +
            "                           JOIN TAG t ON sttr.tag_id = t.id" +
            "                  WHERE st.id = :scaleTypeId" +
            "                    AND t.id = :tagId" +
            "                    AND sttr.id = :id) > 0 THEN TRUE" +
            "            ELSE FALSE" +
            "    END)")
    boolean existsByAllParams(@Param("id") int scaleTypeTagId, @Param("scaleTypeId") int scaleTypeId, @Param("tagId") int tagId);

}
