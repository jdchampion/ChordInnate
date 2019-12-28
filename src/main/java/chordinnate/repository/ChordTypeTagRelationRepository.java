package chordinnate.repository;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.entity.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChordTypeTagRelationRepository extends CrudRepository<ChordTypeTagRelation, Integer> {

    @Query("SELECT cttr FROM ChordTypeTagRelation cttr JOIN FETCH ChordType st ON st.id = cttr.matchingChordType.id JOIN FETCH Tag t ON t.id = cttr.matchingTag.id WHERE cttr.matchingChordType = :chordType")
    List<ChordTypeTagRelation> findAllByChordType(@NotNull @Param("chordType") ChordType chordType);

    @Query("SELECT cttr FROM ChordTypeTagRelation cttr JOIN FETCH ChordType st ON st.id = cttr.matchingChordType.id JOIN FETCH Tag t ON t.id = cttr.matchingTag.id WHERE cttr.matchingTag = :chordTypeTag")
    List<ChordTypeTagRelation> findAllByTag(@NotNull @Param("chordTypeTag") Tag tag);

    @Query("DELETE FROM ChordTypeTagRelation cttr WHERE cttr.matchingTag = :chordTypeTag")
    void deleteAllByTag(@NotNull @Param("chordTypeTag") Tag tag);

    @Query("DELETE FROM ChordTypeTagRelation cttr WHERE cttr.matchingChordType = :chordType")
    void deleteAllByChordType(@NotNull @Param("chordType") ChordType chordType);

    @Query("DELETE FROM ChordTypeTagRelation cttr WHERE cttr.matchingChordType = :chordType AND cttr.matchingTag = :chordTypeTag")
    void deleteAllByChordTypeAndTag(@NotNull @Param("chordType") ChordType chordType, @NotNull @Param("chordTypeTag") Tag tag);

}
