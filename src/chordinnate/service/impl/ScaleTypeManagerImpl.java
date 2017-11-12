package chordinnate.service.impl;

import chordinnate.dao.ScaleTypeDAO;
import chordinnate.service.ScaleTypeManager;
import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("scaleTypeManager")
@Transactional
public class ScaleTypeManagerImpl implements ScaleTypeManager {

    private ScaleTypeManagerImpl() {
        super();
    }

    @Autowired
    ScaleTypeDAO dao;

    @Override
    public Optional<ScaleType> findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Optional<ScaleType> findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<ScaleType> findAll() {
        return dao.findAll();
    }
}
