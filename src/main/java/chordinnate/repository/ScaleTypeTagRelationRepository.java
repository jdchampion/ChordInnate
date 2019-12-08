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

//    @Query(value = "SELECT (CASE" +
//            "            WHEN (SELECT COUNT(ScaleTypeTagRelation)" +
//            "                  FROM ScaleTypeTagRelation sttr" +
//            "                           JOIN FETCH ScaleType st ON sttr.matchingScaleType.id = st.id" +
//            "                           JOIN FETCH ScaleTypeTag t ON sttr.matchingTag.id = t.id" +
//            "                  WHERE sttr.matchingScaleType.id = :scaleTypeId" +
//            "                    AND sttr.matchingTag.id = :tagId) > 0 THEN TRUE" +
//            "            ELSE FALSE" +
//            "    END) FROM ScaleTypeTagRelation")
    boolean existsWith(@Param("scaleTypeId") int scaleTypeId, @Param("tagId") int tagId);

}
