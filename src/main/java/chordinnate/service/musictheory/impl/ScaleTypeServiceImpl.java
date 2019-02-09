package chordinnate.service.musictheory.impl;

import chordinnate.dao.musictheory.ScaleTypeDAO;
import chordinnate.service.musictheory.ScaleTypeService;
import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("scaleTypeService")
@Transactional
public class ScaleTypeServiceImpl implements ScaleTypeService {

    ScaleTypeServiceImpl() {
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
