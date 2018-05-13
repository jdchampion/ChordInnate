package chordinnate.controller;

import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import chordinnate.service.ChordTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller("chordTypeController")
public class ChordTypeController {

    @Autowired
    ChordTypeManager manager;

    public Optional<ChordType> findChordTypeById(int id) {
        return manager.findById(id);
    }

    public Optional<ChordType> findChordTypeBySymbol(String symbol) {
        return manager.findBySymbol(symbol);
    }

    public List<ChordType> findAllChordType() {
        return manager.findAll();
    }
}
