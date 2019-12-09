package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import chordinnate.repository.ChordTypeTagRepository;
import chordinnate.service.ChordTypeTagService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service(ChordTypeTagServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeTagServiceImpl implements ChordTypeTagService {

    public static final String SERVICE_NAME = "chordTypeTagService";

    private final ChordTypeTagRepository repository;

    @Autowired
    ChordTypeTagServiceImpl(ChordTypeTagRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ChordTypeTag> findById(@NotNull Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ChordTypeTag> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<ChordTypeTag> findAllFor(@NotNull ChordType chordType) {
        return repository.findAllFor(chordType);
    }

    @Override
    public ChordTypeTag save(ChordTypeTag chordTypeTag) {
        return repository.save(chordTypeTag);
    }

    @Override
    public void delete(ChordTypeTag chordTypeTag) {
        repository.delete(chordTypeTag);
    }

}
