package chordinnate.service.musictheory.impl;

import chordinnate.dao.musictheory.ChordTypeDAO;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.service.musictheory.ChordTypeService;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("chordTypeService")
@Transactional
public class ChordTypeServiceImpl implements ChordTypeService {

    @Autowired
    ChordTypeServiceImpl(ChordTypeDAO dao) {
        super();
        this.dao = dao;
    }

    private final ChordTypeDAO dao;

    @Override
    public List<ChordType> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<ChordType> findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Optional<ChordType> findBySymbol(String symbol) {
        return dao.findBySymbol(symbol);
    }

    @Override
    public Optional<ChordType> findByIntervals(Interval... intervals) {
        return dao.findByIntervals(intervals);
    }

    @Override
    public List<ChordType> findAllByIntervals(List<Interval[]> intervals, boolean includeDuplicates) {

        if (intervals == null) return Collections.emptyList();

        if (includeDuplicates) {

            List<Interval[]> list = intervals.stream()
                    .map(arr -> (Interval[]) Arrays.stream(arr)
                            .map(i -> Interval.withShortName(i.getSimpleShortName()))
                            .toArray()).collect(Collectors.toList());

            return dao.findAllByIntervals(list);
        }

        List<ChordType> list = new ArrayList<>();
        intervals.forEach(arr ->  {
            Optional<ChordType> opt = findByIntervals(arr);
            opt.ifPresent(list::add);
        });

        return list;
    }

    @Override
    public Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence) {
        return dao.findByRomanNumeralCriteria(intervals, size, precedence);
    }
}
