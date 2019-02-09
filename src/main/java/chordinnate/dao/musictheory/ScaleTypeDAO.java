package chordinnate.dao.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;

import java.util.List;
import java.util.Optional;

public interface ScaleTypeDAO {
    Optional<ScaleType> findById(int id);
    Optional<ScaleType> findByName(String symbol);
    List<ScaleType> findAll();
}
