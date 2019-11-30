package chordinnate.service.musictheory.impl;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import chordinnate.repository.musictheory.ScaleTypeRepository;
import chordinnate.service.musictheory.ScaleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("scaleTypeService")
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

    private final ScaleTypeRepository repository;

    @Autowired
    ScaleTypeServiceImpl(ScaleTypeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ScaleType> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<ScaleType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ScaleType> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ScaleType save(ScaleType scaleType) {
        return repository.save(scaleType);

        // TODO: catch PersistenceException and throw ChordInnateException with friendly message?
    }

    @Override
    public void delete(ScaleType scaleType) {
        repository.delete(scaleType);
    }
}
