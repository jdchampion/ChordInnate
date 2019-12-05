package chordinnate.service.impl;

import chordinnate.entity.Tag;
import chordinnate.repository.TagRepository;
import chordinnate.service.TagService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

@Service(TagServiceImpl.SERVICE_NAME)
@Transactional
public class TagServiceImpl implements TagService {

    @PersistenceContext
    EntityManager entityManager;

    public static final String SERVICE_NAME = "tagService";

    private final TagRepository repository;

    @Autowired
    TagServiceImpl(TagRepository repository, EntityManager entityManager) {
        super();
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Tag> findById(Integer id) {

        String sql = "select t.* " +
                "from tag t " +
                "         join TAG_GROUP_REL TGR on t.ID = TGR.TAG_ID " +
                "         join SCALE_TYPE ST on TGR.GROUP_ID = ST.TAG_GROUP_ID " +
                "where t.id = 9";

        Query q = entityManager.createNativeQuery(sql, Tag.class);

        Session session = entityManager.unwrap(Session.class);

        return Optional.of((Tag) q.getResultList().get(0));

//        return repository.findById(id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return repository.findByName(name);
    }

}
