package chordinnate.service.playback;

import chordinnate.model.musictheory.melody.form.Cell;
import chordinnate.model.musictheory.melody.form.Measure;
import chordinnate.model.musictheory.melody.form.Motif;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.HorizontalIntervalSet;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import chordinnate.model.musictheory.pitch.interval.set.VerticalIntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.TimeSignature;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.playback.Note;
import chordinnate.model.playback.Rest;
import chordinnate.model.playback.Rhythmic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public class PlaybackServiceIT {

    @Test
    public void play_Pitch() {

    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_HorizontalIntervalSet() {
        HorizontalIntervalSet scale = new Scale("Db Major");

        log.info("PLAYING: Db Major Scale");
        PlaybackService.play(scale);
    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_VerticalIntervalSet() {
        VerticalIntervalSet chord = new Chord("Gm");

        log.info("PLAYING: Gm");
        PlaybackService.play(chord);
    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_Note() {
        Note note = Note.builder(Beat.WHOLE, Pitch.C_5).build();

        log.info("PLAYING: {}", note.toString()); // TODO: better diagnostic string
        PlaybackService.play(note);
    }

    @Test
    public void play_Note_controlChange() {
        // TODO
    }

    @Test
    public void play_Note_programChange() {
        // TODO
    }

    @Test
    public void play_Note_polyKeyPressureChange() {
        // TODO
    }

    @Test
    public void play_Note_channelPressureChange() {
        // TODO
    }

    @Test
    public void play_Note_pitchBendChange() {
        // TODO
    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_Measure() {

        Note q1 = Note.builder(Beat.QUARTER, Pitch.C_5).build();
        Note q2 = Note.builder(Beat.QUARTER, Pitch.C_5).build();

        q1.tieTo(q2);

        Rest halfRest = new Rest(Beat.HALF);

        List<Rhythmic> rhythm = Arrays.asList(q1, q2, halfRest);

        Measure measure = new Measure(TimeSignature.NONE, KeySignature.NO_KEY_SIGNATURE, rhythm);

        log.info("PLAYING: {}", measure.toString()); // TODO: better diagnostic string
        PlaybackService.play(measure);
    }

    @Test
    public void play_Cell() {

    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_Motif() {
        Note a = Note.builder(Beat.QUARTER, Pitch.E_FLAT_5).build();
        Note b = Note.builder(Beat.DOTTED_EIGHTH, Pitch.D_5).build();
        Note c = Note.builder(Beat.SIXTEENTH, Pitch.E_FLAT_5).build();
        Note d = Note.builder(Beat.EIGHTH, Pitch.C_5).build();
        Note e = Note.builder(Beat.EIGHTH, Pitch.E_FLAT_5).build();
        Note f = Note.builder(Beat.EIGHTH, Pitch.E_FLAT_5).build();
        e.tieTo(f);
        Note g = Note.builder(Beat.EIGHTH, Pitch.F_5).build();

        List<Rhythmic> list1 = Arrays.asList(a,b,c,d,e,f,g);
        Measure measure1 = new Measure(new TimeSignature(4, 4), KeySignature.E_FLAT_MAJOR, list1);
        Cell cell1 = new Cell();
        cell1.setMeasure(measure1);

        Note h = Note.builder(Beat.EIGHTH, Pitch.G_FLAT_5).build();
        Note i = Note.builder(Beat.EIGHTH, Pitch.G_5).build();
        Note j = Note.builder(Beat.EIGHTH, Pitch.A_FLAT_5).build();
        Note k = Note.builder(Beat.EIGHTH, Pitch.A_5).build();
        Note l = Note.builder(Beat.EIGHTH, Pitch.B_FLAT_5).build();
        Rest m = new Rest(Beat.EIGHTH);
        Note n = Note.builder(Beat.QUARTER, Pitch.B_FLAT_5).build();

        List<Rhythmic> list2 = Arrays.asList(h,i,j,k,l,m,n);
        Measure measure2 = new Measure(TimeSignature.NONE, KeySignature.NO_KEY_SIGNATURE, list2);
        Cell cell2 = new Cell();
        cell2.setMeasure(measure2);

        Motif motif = new Motif();
        motif.setCells(Arrays.asList(cell1, cell2));

        log.info("PLAYING: {}", motif.toString()); // TODO: better diagnostic string
        PlaybackService.play(motif); // First two measures of 'The Stars and Stripes Forever'
    }

    @Test
    public void play_PhraseMember() {

    }

    @Test
    public void play_Phrase() {

    }

    @Test
    public void play_PhraseGroup() {

    }

    @Test
    public void play_Period() {

    }

    @Test
    public void play_DoublePeriod() {

    }

}