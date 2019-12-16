package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTag;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.ChordTypeTagRepository;
import chordinnate.service.ChordTypeTagService;
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

@Service(ChordTypeTagServiceImpl.SERVICE_NAME)
@Transactional
public class ChordTypeTagServiceImpl implements ChordTypeTagService {

    public static final String SERVICE_NAME = "chordTypeTagService";

    private final ChordTypeTagRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

        if (chordTypeTag == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<ChordTypeTag>> violations = validator.validate(chordTypeTag);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        return repository.save(chordTypeTag);
    }

    @Override
    public void delete(ChordTypeTag chordTypeTag) {

        if (chordTypeTag == null || chordTypeTag.getId() == null) {
            return;
        }

        Optional<ChordTypeTag> optional = repository.findById(chordTypeTag.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            ChordTypeTag toDelete = optional.get();
            if (!chordTypeTag.equals(toDelete)) {
                String errorMessage = "Cannot delete tag #%d (%s): entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, chordTypeTag.getId(), chordTypeTag.getName()));
            }
        }

        repository.delete(chordTypeTag);
    }

}
