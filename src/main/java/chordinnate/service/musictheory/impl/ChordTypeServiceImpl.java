package chordinnate.service.musictheory.impl;

import chordinnate.dao.musictheory.ChordTypeDAO;
import chordinnate.service.musictheory.ChordTypeService;
import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("chordTypeService")
@Transactional
public class ChordTypeServiceImpl implements ChordTypeService {

    ChordTypeServiceImpl() {
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
