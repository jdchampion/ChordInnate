package chordinnate.service.musictheory.impl;

import chordinnate.dao.musictheory.ChordTypeDAO;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.service.musictheory.ChordTypeService;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return dao.findAllByIntervals(intervals, includeDuplicates);
    }

    @Override
    public List<ChordType> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence) {
        return dao.findByRomanNumeralCriteria(intervals, size, precedence);
    }
}
