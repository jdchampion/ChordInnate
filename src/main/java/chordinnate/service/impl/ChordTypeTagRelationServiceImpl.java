package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.ChordTypeTagRelationRepository;
import chordinnate.service.ChordTypeTagRelationService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(ChordTypeTagRelationServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeTagRelationServiceImpl implements ChordTypeTagRelationService {

    public static final String SERVICE_NAME = "chordTypeTagRelationService";

    private final ChordTypeTagRelationRepository repository;

    @Autowired
    ChordTypeTagRelationServiceImpl(ChordTypeTagRelationRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<ChordTypeTagRelation> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ChordTypeTagRelation> findAllFor(ChordType chordType) {
        return repository.findAllFor(chordType);
    }

    @Override
    public ChordTypeTagRelation save(ChordTypeTagRelation chordTypeTagRelation) {

        Pair<Boolean, String> validationResult = ChordTypeTagRelationValidator.validateBeforeSaving(chordTypeTagRelation, repository);

        if (BooleanUtils.isFalse(validationResult.getLeft())) {
            throw new ChordInnateException(validationResult.getRight());
        }

        return repository.save(chordTypeTagRelation);
    }

    @Override
    public void delete(ChordTypeTagRelation chordTypeTagRelation) {
        repository.delete(chordTypeTagRelation);
    }
}
