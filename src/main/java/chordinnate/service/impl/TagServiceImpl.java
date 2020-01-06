package chordinnate.service.impl;

import chordinnate.entity.ChordType;
import chordinnate.entity.ScaleType;
import chordinnate.entity.Tag;
import chordinnate.entity.validation.Phase1And2Validation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.TagRepository;
import chordinnate.service.TagService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(TagServiceImpl.SERVICE_NAME)
@Transactional
public class TagServiceImpl implements TagService {

    public static final String SERVICE_NAME = "tagService";

    private final TagRepository repository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tag> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Tag> findById(@NotNull Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Tag> findByName(@NotNull String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Tag> findAllFor(@NotNull ChordType chordType) {
        return repository.findAllFor(chordType);
    }

    @Override
    public List<Tag> findAllFor(@NotNull ScaleType scaleType) {
        return repository.findAllFor(scaleType);
    }

    @Override
    public Tag save(Tag tag) {

        if (tag == null) {
            throw new IllegalArgumentException("Cannot save null entities");
        }

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag, Phase1And2Validation.class);

        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\r\n"));

            throw new ChordInnateConstraintViolation(violationMessages);
        }

        return repository.save(tag);
    }

    @Override
    public void delete(Tag tag) {

        if (tag == null || tag.getId() == null) {
            return;
        }

        Optional<Tag> optional = repository.findById(tag.getId());

        if (optional.isPresent()) {
            // Check every field for data integrity before deleting
            Tag toDelete = optional.get();
            if (!tag.equals(toDelete)) {
                String errorMessage = "Cannot delete tag #%d (%s): entity data does not match record data";
                throw new ChordInnateException(String.format(errorMessage, tag.getId(), tag.getName()));
            }
        }

        repository.delete(tag);
    }

}
