package chordinnate.service.musictheory.impl;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import chordinnate.repository.musictheory.ScaleTypeRepository;
import chordinnate.service.musictheory.ScaleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Service("scaleTypeService")
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

    @Autowired
    ScaleTypeServiceImpl(ScaleTypeRepository repository) {
        super();
        this.repository = repository;
    }

    private final ScaleTypeRepository repository;

    @Override
    public Optional<ScaleType> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<ScaleType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ScaleType> findById(Integer integer) {
        return repository.findById(integer);
    }
}
