package chordinnate.service.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.playback.Note;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public class PlaybackServiceTest {

    @Test
    public void play_Pitch() {

    }

    @Test
    public void play_HorizontalIntervalSet() {

    }

    @Test
    public void play_VerticalIntervalSet() {

    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void play_Note() {
        Note note = Note.builder(Pitch.C_5, Beat.WHOLE).build();

        log.info("PLAYING: {}", note.toString()); // TODO: better diagnostic string
        PlaybackService.play(note);
    }

    @Test
    public void play_Measure() {

    }

    @Test
    public void play_Cell() {

    }

    @Test
    public void play_Motif() {

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