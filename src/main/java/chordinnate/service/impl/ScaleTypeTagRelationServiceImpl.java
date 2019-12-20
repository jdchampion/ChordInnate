package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.ScaleTypeTagRelationRepository;
import chordinnate.service.ScaleTypeTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(ScaleTypeTagRelationServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeTagRelationServiceImpl implements ScaleTypeTagRelationService {

    public static final String SERVICE_NAME = "scaleTypeTagRelationService";

    private final ScaleTypeTagRelationRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    ScaleTypeTagRelationServiceImpl(ScaleTypeTagRelationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ScaleTypeTagRelation save(ScaleTypeTagRelation relation) {

        if (relation == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ScaleTypeTagRelation>> violations = validator.validate(relation);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        if (relation.getId() != null) {

            ScaleTypeTagRelation fromDB = repository.findById(relation.getId()).orElse(null);

            if (fromDB == null) {
                throw new ChordInnateException("Relation ID not found: " + relation.getId());
            }
        }

        return repository.save(relation);
    }

    @Override
    public void delete(ScaleTypeTagRelation relation) {

        if (relation == null || relation.getId() == null) {
            return;
        }

        Optional<ScaleTypeTagRelation> optional = repository.findById(relation.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ScaleTypeTagRelation toDelete = optional.get();
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
    public void deleteAllByTag(ScaleTypeTag tag) {
        repository.deleteAllByTag(tag);
    }

    @Override
    public void deleteAllByScaleType(ScaleType scaleType) {
        repository.deleteAllByScaleType(scaleType);
    }

    @Override
    public void deleteByScaleTypeAndTag(ScaleType scaleType, ScaleTypeTag tag) {
        repository.deleteAllByScaleTypeAndTag(scaleType, tag);
    }
}
