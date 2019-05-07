package chordinnate.dao.musictheory.impl;

import chordinnate.dao.musictheory.ChordTypeDAO;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        TypedQuery<ChordType> q =  em.createNamedQuery("ChordType.findBySymbol", ChordType.class)
                .setParameter("symbol", symbol)
                .setMaxResults(1);
        List<ChordType> results = q.getResultList();
        return Optional.ofNullable(!results.isEmpty() ? results.get(0) : null);
    }

    @Override
    public Optional<ChordType> findByIntervals(Interval... intervals) {
        if (intervals == null) return Optional.empty();
        TypedQuery<ChordType> q = em.createNamedQuery("ChordType.findByIntervals", ChordType.class)
                .setParameter("intervals", intervals)
                .setMaxResults(1);
        List<ChordType> results = q.getResultList();
        return Optional.ofNullable(!results.isEmpty() ? results.get(0) : null);
    }

    public List<ChordType> findAllByIntervals(List<Interval[]> intervals, boolean distinct) {
        if (intervals == null) return Collections.emptyList();

        if (distinct) {

            List<Interval[]> list = intervals.stream()
                    .map(arr -> (Interval[]) Arrays.stream(arr)
                            .map(i -> Interval.withShortName(i.getSimpleShortName()))
                            .toArray()).collect(Collectors.toList());

            TypedQuery<ChordType> q = em.createNamedQuery("ChordType.findAllDistinctByIntervals", ChordType.class)
                    .setParameter("intervals", list);

            return q.getResultList();
        }

        List<ChordType> list = new ArrayList<>();
        intervals.forEach(arr ->  {
            Optional<ChordType> opt = findByIntervals(arr);
            opt.ifPresent(list::add);
        });

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ChordType> findAll() {
        Query q = em.createQuery("from " + ChordType.class.getName());
        return q.getResultList();
    }

    public Optional<ChordType> findByRomanNumeralCriteria(Interval[] intervals, int size, int precedence) {
        Session session = em.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ChordType> cq = cb.createQuery(ChordType.class);
        Root<ChordType> root = cq.from(ChordType.class);

        Predicate conditions = cb.and(
                cb.equal(root.get("size"), size),
                cb.equal(root.get("intervals"), intervals),
                cb.equal(root.get("rnPrecedence"), precedence)
        );

        cq.select(root).where(conditions);

        TypedQuery<ChordType> q = session.createQuery(cq);
        q.setMaxResults(1);
        List<ChordType> results = q.getResultList();
        session.close();
        return Optional.ofNullable(!results.isEmpty() ? results.get(0) : null);
    }
}
