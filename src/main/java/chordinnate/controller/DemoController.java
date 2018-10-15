package chordinnate.controller;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("demoController")
public class DemoController {

    @RequestMapping("/")
    public String welcome() {
        return "Welcome -- ChordInnate Demo";
    }

    @GetMapping("/pitch/{name}")
    public @ResponseBody Pitch getPitch(@PathVariable String name) {
        return Pitch.withName(name);
    }

    @GetMapping("/chord/{name}")
    public @ResponseBody Chord getChord(@PathVariable String name) {
        return new Chord(name);
    }

    @GetMapping("/scale/{name}")
    public @ResponseBody Scale getScale(@PathVariable String name) {
        return new Scale(name);
    }

}
