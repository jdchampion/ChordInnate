package chordinnate.dao.musictheory;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;

import java.util.List;
import java.util.Optional;

public interface ChordTypeDAO extends BaseDAO<ChordType> {
    Optional<ChordType> findBySymbol(String symbol);
    Optional<ChordType> findByIntervals(Interval... intervals);
    List<ChordType> findAllByIntervals(List<Interval[]> intervals, boolean includeDuplicates);
    Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence);
}
