package chordinnate.repository;

import chordinnate.entity.ChordType;
import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ChordTypeRepository extends CrudRepository<ChordType, Integer> {

    Optional<ChordType> findBySymbol(@Param("symbol") String symbol);

    Optional<ChordType> findByIntervals(@Param("intervals") Interval... intervals);

    @Query("SELECT c FROM ChordType c WHERE c.intervals IN :intervals")
    Iterable<ChordType> findAllByIntervals(@NotNull @Param("intervals") Collection<Interval[]> intervals);

    @Query("SELECT c FROM ChordType c WHERE c.intervals = :intervals AND c.size = :size AND c.rnPrecedence = :precedence")
    Optional<ChordType> findByRomanNumeralCriteria(@Param("intervals") Interval[] intervals,
                                                   @Param("size") int size,
                                                   @Param("precedence") int precedence);

    @Query("SELECT c FROM ChordType c WHERE c.size >= :min AND c.size <= :max")
    Iterable<ChordType> findAllBySizeRange(@Param("min") int min, @Param("max") int max);

    @Query("SELECT c FROM ChordType c JOIN FETCH ChordTypeTagRelation cttr ON c.id = cttr.matchingChordType.id JOIN FETCH ChordTypeTag t ON cttr.matchingTag.id = t.id WHERE t.name IN :tags")
    Iterable<ChordType> findAllByTag(@NotNull @Param("tags") Collection<String> tags);

}
