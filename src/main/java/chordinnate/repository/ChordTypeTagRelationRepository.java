package chordinnate.repository;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChordTypeTagRelationRepository extends CrudRepository<ChordTypeTagRelation, Integer> {

    @Query("SELECT cttr FROM ChordTypeTagRelation cttr JOIN FETCH ChordType st ON st.id = cttr.matchingChordType.id JOIN FETCH ChordTypeTag t ON t.id = cttr.matchingTag.id WHERE cttr.matchingChordType = :chordType")
    Iterable<ChordTypeTagRelation> findAllFor(@NotNull @Param("chordType") ChordType chordType);

    @Query(nativeQuery = true,
            value = "SELECT (CASE" +
                    "            WHEN (SELECT COUNT(1)" +
                    "                  FROM CHORD_TYPE_TAG cttr" +
                    "                           JOIN CHORD_TYPE st ON cttr.chord_type_id = st.id" +
                    "                           JOIN TAG t ON cttr.tag_id = t.id" +
                    "                  WHERE st.id = :chordTypeId" +
                    "                    AND t.id = :tagId" +
                    "                    AND cttr.id = :id) > 0 THEN TRUE" +
                    "            ELSE FALSE" +
                    "    END)")
    boolean existsByAllParams(@Param("id") int chordTypeTagId, @Param("chordTypeId") int chordTypeId, @Param("tagId") int tagId);

}
