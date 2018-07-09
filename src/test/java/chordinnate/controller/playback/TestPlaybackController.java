package chordinnate.controller.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import chordinnate.model.musictheory.time.rhythm.Beat;
import chordinnate.model.musictheory.time.rhythm.Duration;
import chordinnate.model.musictheory.time.tempo.Tempo;
import chordinnate.model.playback.Articulation;
import chordinnate.model.playback.Note;
import org.junit.Test;

/**
 * Created by Joseph on 6/16/16.
 */
public class TestPlaybackController {
    @Test
    public void playPitch() throws Exception {
        System.out.println(Pitch.C_4.toString());
        PlaybackController.play(Pitch.C_4);
    }

    @Test
    public void playNote() throws Exception {
        Tempo tempo = new Tempo(Beat.QUARTER, 60);
        int bpm = tempo.getCurrentBPM();
        Beat TRIPLET_EIGHTH = new Beat.Builder(Duration.EIGHTH).tuplet(3).build(),
                DOTTED_HALF = new Beat.Builder(Duration.HALF).dots(1).build();
        for (Articulation articulation : Articulation.values()) {
            Note
                    n1 = new Note.Builder(Pitch.G_4, TRIPLET_EIGHTH)
                    .articulation(articulation)
                    .build(),

                    n2 = new Note(Pitch.E_FLAT_4, DOTTED_HALF),

                    n3 = new Note.Builder(Pitch.F_4, TRIPLET_EIGHTH)
                            .articulation(articulation)
                            .build(),

                    n4 = new Note(Pitch.D_4, DOTTED_HALF);

            long fullLength = tempo.getMillisFor(n1.getBeat());
            long soundedLength = (long) (n1.getSoundedLength() * fullLength);
            long difference = fullLength - soundedLength;
            Pitch pitch = n1.getPitch();
            Beat beat = n1.getBeat();
            System.out.println(
                    (articulation == null ? "" : (articulation + " ")) +
                            pitch.PITCH_CLASS.getName() + " " + beat + " at tempo = " + bpm + " bpm:");
            System.out.println("Full length: " + fullLength + " ms");
            System.out.println("Sounded length: " + soundedLength + " ms");
            System.out.println("Unsounded length: " + difference + " ms\n");

            PlaybackController.setTempo(tempo);

            // Play the opening for Beethoven's Symphony No. 5
            PlaybackController.play(n1);
            PlaybackController.play(n1);
            PlaybackController.play(n1);
            PlaybackController.play(n2);
            PlaybackController.play(n3);
            PlaybackController.play(n3);
            PlaybackController.play(n3);
            PlaybackController.play(n4);
        }
    }

    @Test
    public void testScale() throws Exception {
        Scale scale = new Scale("C Hira-joshi");
        PlaybackController.play(scale, Octave.OCTAVE_4);
    }

    @Test
    public void testChord() throws Exception {
        Chord c = new Chord("G7");
        PlaybackController.play(c);
    }

}