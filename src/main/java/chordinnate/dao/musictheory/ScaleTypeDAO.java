package chordinnate.dao.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;

import java.util.Optional;

public interface ScaleTypeDAO extends BaseDAO<ScaleType> {
    Optional<ScaleType> findByName(String symbol);
}
