package chordinnate.service.impl;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.ScaleTypeTagRepository;
import chordinnate.service.ScaleTypeTagService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(ScaleTypeTagServiceImpl.SERVICE_NAME)
@Transactional
public class ScaleTypeTagServiceImpl implements ScaleTypeTagService {

    public static final String SERVICE_NAME = "scaleTypeTagService";

    private final ScaleTypeTagRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    ScaleTypeTagServiceImpl(ScaleTypeTagRepository repository) {
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

        if (scaleTypeTag == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ScaleTypeTag>> violations = validator.validate(scaleTypeTag);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        return repository.save(scaleTypeTag);
    }

    @Override
    public void delete(ScaleTypeTag scaleTypeTag) {

        if (scaleTypeTag == null || scaleTypeTag.getId() == null) {
            return;
        }

        Optional<ScaleTypeTag> optional = repository.findById(scaleTypeTag.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ScaleTypeTag toDelete = optional.get();
            if (!scaleTypeTag.equals(toDelete)) {
                String errorMessage = "Cannot delete tag #%d (%s): entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, scaleTypeTag.getId(), scaleTypeTag.getName()));
            }
        }

        repository.delete(scaleTypeTag);
    }

}
