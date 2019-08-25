package chordinnate.repository.musictheory;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChordTypeRepository extends CrudRepository<ChordType, Integer> {

    Optional<ChordType> findBySymbol(@Param("symbol") String symbol);

    Optional<ChordType> findByIntervals(@Param("intervals") Interval... intervals);

    @Query("SELECT c FROM ChordType c WHERE c.intervals IN :intervals")
    List<ChordType> findAllByIntervals(@Param("intervals") List<Interval[]> intervals);

    @Query("SELECT c FROM ChordType c WHERE c.intervals = :intervals AND c.size = :size AND c.rnPrecedence = :precedence")
    Optional<ChordType> findByRomanNumeralCriteria(@Param("intervals") Interval[] intervals,
                                                   @Param("size") int size,
                                                   @Param("precedence") int precedence);

}
