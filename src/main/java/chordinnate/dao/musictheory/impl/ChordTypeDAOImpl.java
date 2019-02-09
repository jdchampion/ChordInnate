package chordinnate.dao.musictheory.impl;

import chordinnate.dao.musictheory.ChordTypeDAO;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("chordTypeDAO")
@Transactional
public class ChordTypeDAOImpl implements ChordTypeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<ChordType> findById(int id) {
        return Optional.ofNullable(em.find(ChordType.class, id));
    }

    @Override
    public Optional<ChordType> findBySymbol(String symbol) {
        TypedQuery<ChordType> q =  em.createNamedQuery("ChordType.findBySymbol", ChordType.class);
        q.setParameter("symbol", symbol);
        q.setMaxResults(1);
        List<ChordType> results = q.getResultList();
        return Optional.ofNullable(!results.isEmpty() ? results.get(0) : null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ChordType> findAll() {
        Query q = em.createQuery("from " + ChordType.class.getName());
        return q.getResultList();
    }
}
