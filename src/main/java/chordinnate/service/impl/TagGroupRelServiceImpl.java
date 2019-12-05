package chordinnate.service.impl;

import chordinnate.entity.TagGroupRel;
import chordinnate.repository.TagGroupRelRepository;
import chordinnate.service.TagGroupRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(TagGroupRelServiceImpl.SERVICE_NAME)
public class TagGroupRelServiceImpl implements TagGroupRelService {

    public static final String SERVICE_NAME = "tagGroupRelService";

    private final TagGroupRelRepository repository;

    @Autowired
    TagGroupRelServiceImpl(TagGroupRelRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<TagGroupRel> findById(Integer id) {
        return repository.findById(id);
    }
}
