package chordinnate.service;

import chordinnate.entity.ChordType;
import chordinnate.entity.Tag;
import chordinnate.model.musictheory.pitch.interval.Interval;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Joseph on 6/4/17.
 */
@Service
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
     * TODO: revisit this method later to refine the API
     * Retrieves a group of all ChordTypes from the database matching any of the specified intervals
     * @param intervals a collection of interval sequences to match ChordTypes on
     * @param includeDuplicates whether the returned list will contain duplicate ChordTypes,
     *                          if argument {@code intervals} supplied duplicate interval sequences
     * @return {@link Collection}<{@link ChordType}> of all ChordTypes requested
     */
//    Iterable<ChordType> findAllByIntervals(Collection<Interval[]> intervals, boolean includeDuplicates);

    Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence);

    List<ChordType> findAllBySize(int min, int max);

    List<ChordType> findAllByTags(Collection<Tag> tags);

    ChordType save(ChordType chordType);

    void delete(ChordType chordType);

}
