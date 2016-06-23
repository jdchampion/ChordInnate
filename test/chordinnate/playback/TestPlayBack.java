package chordinnate.playback;

import chordinnate.musictheory.Articulation;
import chordinnate.musictheory.Duration;
import chordinnate.musictheory.Note;
import chordinnate.musictheory.Pitch;
import org.junit.Test;

/**
 * Created by Joseph on 6/16/16.
 */
public class TestPlayBack {
    @Test
    public void playPitch() throws Exception {
        PlayBack playBack = new PlayBack();
        for (Pitch pitch : Pitch.values()) {
            System.out.println(pitch.name());
            playBack.play(pitch);
        }
        playBack.stop();
    }

    @Test
    public void playNote() throws Exception {
        PlayBack playBack = new PlayBack();
        Note note = new Note.Builder(Pitch.C_4, Duration.QUARTER)
                .articulation(Articulation.STACCATO)
                .build();

        playBack.play(note);
        playBack.stop();
    }

}