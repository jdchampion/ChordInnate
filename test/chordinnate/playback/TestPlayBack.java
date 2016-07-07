package chordinnate.playback;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.time.rhythm.Beat;
import chordinnate.musictheory.time.tempo.Tempo;
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
        for (Articulation articulation : Articulation.values()) {
            Note note = new Note.Builder(Pitch.C_4, Beat.QUARTER)
                    .articulation(articulation)
                    .build();

            Tempo tempo = new Tempo(Beat.QUARTER, 120);
            long fullLength = tempo.getMillisFor(note.getBeat());
            long soundedLength = (long) (note.getSoundedLength() * fullLength);
            long difference = fullLength - soundedLength;
//            Articulation articulation = note.getArticulation();
            Pitch pitch = note.getPitch();
            Beat beat = note.getBeat();
            System.out.println(
                    (articulation == null ? "" : (articulation + " ")) +
                            pitch + " " + beat + " at tempo = 120 bpm:");
            System.out.println("Full length: " + fullLength + " ms");
            System.out.println("Sounded length: " + soundedLength + " ms");
            System.out.println("Unsounded length: " + difference + " ms\n");

            PlayBack.play(tempo, note);
            PlayBack.play(tempo, note);
            PlayBack.play(tempo, note);
            PlayBack.play(tempo, note);
        }

        PlayBack.stop();
    }

}