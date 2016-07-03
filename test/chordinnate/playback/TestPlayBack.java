package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.time.rhythm.Beat;
import org.junit.Test;

/**
 * Created by Joseph on 6/16/16.
 */
public class TestPlayBack {
    @Test
    public void playPitch() throws Exception {
        for (Pitch pitch : Pitch.values()) {
            System.out.println(pitch.name());
            PlayBack.play(pitch);
        }
        PlayBack.stop();
    }

    @Test
    public void playNote() throws Exception {
        Note note = new Note.Builder(Pitch.C_4, Beat.QUARTER)
                .articulation(Articulation.STACCATO)
                .build();

        PlayBack.play(note);
        PlayBack.stop();
    }

}