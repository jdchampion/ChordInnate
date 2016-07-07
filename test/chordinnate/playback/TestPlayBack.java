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

        long fullLength = (long) (note.getFullLength() * 500); // NOTE: "500" is the assumed full length (in ms) of a Quarter Note, at the given Tempo
        long soundedLength = (long) (note.getSoundedLength() * 500);
        Articulation articulation = note.getArticulation();
        System.out.println(
                (articulation == null ? "" : (articulation + " ")) +
                        note.getPitch() + " " + note.getBeat() + " at tempo = 120 bpm:");
        System.out.println("Full length: " + fullLength + " ms");
        System.out.println("Sounded length: " + soundedLength + " ms");
        PlayBack.play(note);
        PlayBack.stop();
    }

}