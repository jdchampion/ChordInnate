package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.repository.ScaleTypeTagRepository;
import chordinnate.service.ScaleTypeTagService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service(ScaleTypeTagServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeTagServiceImpl implements ScaleTypeTagService {

    public static final String SERVICE_NAME = "scaleTypeTagService";

    private final ScaleTypeTagRepository repository;

    @Autowired
    ScaleTypeTagServiceImpl(ScaleTypeTagRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ScaleTypeTag> findById(@NotNull Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ScaleTypeTag> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<ScaleTypeTag> findAllFor(@NotNull ScaleType scaleType) {
        return repository.findAllFor(scaleType);
    }

    @Override
    public ScaleTypeTag save(ScaleTypeTag scaleTypeTag) {
        return repository.save(scaleTypeTag);
    }

    @Override
    public void delete(ScaleTypeTag scaleTypeTag) {
        repository.delete(scaleTypeTag);
    }

}
