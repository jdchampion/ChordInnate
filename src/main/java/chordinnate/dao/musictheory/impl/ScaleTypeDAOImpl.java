package chordinnate.dao.musictheory.impl;

import chordinnate.dao.musictheory.ScaleTypeDAO;
import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ScaleTypeDAOImpl implements ScaleTypeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<ScaleType> findById(int id) {
        return Optional.ofNullable(em.find(ScaleType.class, id));
    }

    @Override
    public Optional<ScaleType> findByName(String name) {
        TypedQuery<ScaleType> q =  em.createNamedQuery("ScaleType.findByName", ScaleType.class);
        q.setParameter("name", name);
        q.setMaxResults(1);
        List<ScaleType> results = q.getResultList();
        return Optional.ofNullable(!results.isEmpty() ? results.get(0) : null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ScaleType> findAll() {
        Query q = em.createQuery("from " + ScaleType.class.getName());
        return q.getResultList();
    }
}
