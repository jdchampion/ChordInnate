package chordinnate.dao;

import chordinnate.model.musictheory.pitch.interval.set.ChordType;

import java.util.List;
import java.util.Optional;

public interface ChordTypeDAO {
    Optional<ChordType> findById(int id);
    Optional<ChordType> findBySymbol(String symbol);
    List<ChordType> findAll();
}
