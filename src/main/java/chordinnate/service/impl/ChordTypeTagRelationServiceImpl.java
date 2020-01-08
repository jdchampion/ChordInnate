package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.entity.Tag;
import chordinnate.entity.validation.Phase1And2Validation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.ChordTypeTagRelationRepository;
import chordinnate.service.ChordTypeTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(ChordTypeTagRelationServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeTagRelationServiceImpl implements ChordTypeTagRelationService {

    public static final String SERVICE_NAME = "chordTypeTagRelationService";

    private final ChordTypeTagRelationRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    ChordTypeTagRelationServiceImpl(ChordTypeTagRelationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChordTypeTagRelation save(ChordTypeTagRelation relation) {

        if (relation == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ChordTypeTagRelation>> violations = validator.validate(relation, Phase1And2Validation.class);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        if (relation.getId() != null) {

            ChordTypeTagRelation fromDB = repository.findById(relation.getId()).orElse(null);

            if (fromDB == null) {
                throw new ChordInnateException("Relation ID not found: " + relation.getId());
            }
        }

        return repository.save(relation);
    }

    @Override
    public void delete(ChordTypeTagRelation relation) {

        if (relation == null || relation.getId() == null) {
            return;
        }

        Optional<ChordTypeTagRelation> optional = repository.findById(relation.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ChordTypeTagRelation toDelete = optional.get();
            if (!relation.equals(toDelete)) {
                String errorMessage = "Cannot delete relation #%d: entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, relation.getId()));
            }
        }

        repository.delete(relation);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllByTag(Tag tag) {
        repository.deleteAllByTag(tag);
    }

    @Override
    public void deleteAllByChordType(ChordType chordType) {
        repository.deleteAllByChordType(chordType);
    }

    @Override
    public void deleteByChordTypeAndTag(ChordType chordType, Tag tag) {
        repository.deleteAllByChordTypeAndTag(chordType, tag);
    }
}
