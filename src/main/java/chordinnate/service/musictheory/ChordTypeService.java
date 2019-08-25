package chordinnate.service.musictheory;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;

import java.util.List;
import java.util.Optional;

/**
 * Created by Joseph on 6/4/17.
 */
public interface ChordTypeService extends BaseService<ChordType, Integer> {

    /**
     * Retrieves the ChordType from the database matching the specified symbol, if possible.
     * @param symbol the ChordType's unique symbol
     * @return {@link Optional}<{@link ChordType}> that was requested
     */
    Optional<ChordType> findBySymbol(String symbol);

    /**
     * Retrieves the ChordType from the database matching the specified intervals, if possible
     * @param intervals the sequence of intervals that are a property of the returned ChordType
     * @return {@link Optional}<{@link ChordType}> that was requested
     */
    Optional<ChordType> findByIntervals(Interval... intervals);

    /**
     * Retrieves a group of all ChordTypes from the database matching any of the specified intervals
     * @param intervals a list of interval sequences to match ChordTypes on
     * @param includeDuplicates whether the returned list will contain duplicate ChordTypes,
     *                          if argument {@code intervals} supplied duplicate interval sequences
     * @return {@link List}<{@link ChordType}> of all ChordTypes requested
     */
    List<ChordType> findAllByIntervals(List<Interval[]> intervals, boolean includeDuplicates);

    Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence);

}
