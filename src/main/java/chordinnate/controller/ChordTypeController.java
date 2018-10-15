package chordinnate.controller;

import chordinnate.model.musictheory.pitch.interval.set.ChordType;
import chordinnate.service.ChordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("chordTypeController")
public class ChordTypeController {

    @Autowired
    ChordTypeService service;

    public Optional<ChordType> findChordTypeById(int id) {
        return service.findById(id);
    }

    public Optional<ChordType> findChordTypeBySymbol(String symbol) {
        return service.findBySymbol(symbol);
    }

    @GetMapping("/chord")
    public @ResponseBody List<ChordType> findAllChordType() {
        return service.findAll();
    }
}
