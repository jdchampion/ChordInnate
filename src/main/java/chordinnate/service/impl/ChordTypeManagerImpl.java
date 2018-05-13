package chordinnate.service.impl;

import chordinnate.dao.ChordTypeDAO;
import chordinnate.service.ChordTypeManager;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("chordTypeManager")
@Transactional
public class ChordTypeManagerImpl implements ChordTypeManager {

    private ChordTypeManagerImpl() {
        super();
    }

    @Autowired
    ChordTypeDAO dao;

    @Override
    public Optional<ChordType> findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Optional<ChordType> findBySymbol(String symbol) {
        return dao.findBySymbol(symbol);
    }

    @Override
    public List<ChordType> findAll() {
        return dao.findAll();
    }
}
