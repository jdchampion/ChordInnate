package chordinnate.service.impl;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.entity.ChordType;
import chordinnate.repository.ChordTypeRepository;
import chordinnate.service.ChordTypeService;
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

    private final ChordTypeRepository repository;

    @Autowired
    ChordTypeServiceImpl(ChordTypeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ChordType> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    @Override
    public Optional<ChordType> findByIntervals(Interval... intervals) {
        return repository.findByIntervals(intervals);
    }

    @Override
    public List<ChordType> findAllByIntervals(List<Interval[]> intervals, boolean includeDuplicates) {

        if (intervals == null) return Collections.emptyList();

        if (includeDuplicates) {

            List<Interval[]> list = intervals.stream()
                    .map(arr -> (Interval[]) Arrays.stream(arr)
                            .map(i -> Interval.withShortName(i.getSimpleShortName()))
                            .toArray()).collect(Collectors.toList());

            return repository.findAllByIntervals(list);
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
        return repository.findByRomanNumeralCriteria(intervals, size, precedence);
    }

    @Override
    public Iterable<ChordType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ChordType> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ChordType save(ChordType chordType) {
        return repository.save(chordType);
    }
}
