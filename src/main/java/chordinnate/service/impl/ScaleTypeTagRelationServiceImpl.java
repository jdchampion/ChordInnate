package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.repository.ScaleTypeTagRelationRepository;
import chordinnate.service.ScaleTypeTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(ScaleTypeTagRelationServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeTagRelationServiceImpl implements ScaleTypeTagRelationService {

    public static final String SERVICE_NAME = "scaleTypeTagRelationService";

    private final ScaleTypeTagRelationRepository repository;

    @Autowired
    ScaleTypeTagRelationServiceImpl(ScaleTypeTagRelationRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ScaleTypeTagRelation> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ScaleTypeTagRelation> findAllFor(ScaleType scaleType) {
        return repository.findAllFor(scaleType);
    }

    @Override
    public ScaleTypeTagRelation save(ScaleTypeTagRelation scaleTypeTagRelation) {
        // Validate against foreign key constraints before saving

        return repository.save(scaleTypeTagRelation);
    }

    @Override
    public void delete(ScaleTypeTagRelation scaleTypeTagRelation) {
        repository.delete(scaleTypeTagRelation);
    }
}
