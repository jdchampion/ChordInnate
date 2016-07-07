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
//        PlayBack.stop();
    }

    @Test
    public void playNote() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 60);
        for (Articulation articulation : Articulation.values()) {
            Note
                    n1 = new Note.Builder(Pitch.G_4, Beat.TRIPLET_EIGHTH)
                    .articulation(articulation)
                    .build(),

                    n2 = new Note(Pitch.E_FLAT_4, Beat.DOTTED_HALF),

                    n3 = new Note.Builder(Pitch.F_4, Beat.TRIPLET_EIGHTH)
                            .articulation(articulation)
                            .build(),

                    n4 = new Note(Pitch.D_4, Beat.DOTTED_HALF);

            long fullLength = tempo.getMillisFor(n1.getBeat());
            long soundedLength = (long) (n1.getSoundedLength() * fullLength);
            long difference = fullLength - soundedLength;
            Pitch pitch = n1.getPitch();
            Beat beat = n1.getBeat();
            System.out.println(
                    (articulation == null ? "" : (articulation + " ")) +
                            pitch + " " + beat + " at tempo = 120 bpm:");
            System.out.println("Full length: " + fullLength + " ms");
            System.out.println("Sounded length: " + soundedLength + " ms");
            System.out.println("Unsounded length: " + difference + " ms\n");

            // Play the opening for Beethoven's Symphony No. 5
            PlayBack.play(tempo, n1);
            PlayBack.play(tempo, n1);
            PlayBack.play(tempo, n1);
            PlayBack.play(tempo, n2);
            PlayBack.play(tempo, n3);
            PlayBack.play(tempo, n3);
            PlayBack.play(tempo, n3);
            PlayBack.play(tempo, n4);
        }
//        PlayBack.stop();
    }

}