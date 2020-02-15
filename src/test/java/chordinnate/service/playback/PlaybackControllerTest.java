package chordinnate.service.playback;

import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.Chord;
import chordinnate.model.musictheory.pitch.interval.set.Scale;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.model.musictheory.temporal.tempo.Tempo;
import chordinnate.model.playback.Articulation;
import chordinnate.model.playback.Note;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
public class PlaybackControllerTest {

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void playPitch() {
        log.info("Playing back C4 pitch...");
        PlaybackController.play(Pitch.C_4);
    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void playNote() {
        Tempo tempo = new Tempo(Beat.QUARTER, 60);
        int bpm = tempo.getCurrentBPM();
        Beat TRIPLET_EIGHTH = Beat.TRIPLET_EIGHTH,
                DOTTED_HALF = Beat.DOTTED_HALF;
        for (Articulation articulation : Articulation.values()) {

            Note n1 = new Note.Builder(Pitch.G_4, TRIPLET_EIGHTH)
                    .articulation(articulation)
                    .build();
            Note n2 = new Note(Pitch.E_FLAT_4, DOTTED_HALF);
            Note n3 = new Note.Builder(Pitch.F_4, TRIPLET_EIGHTH)
                    .articulation(articulation)
                    .build();
            Note n4 = new Note(Pitch.D_4, DOTTED_HALF);

            long fullLength = tempo.getMillisFor(n1.getBeat());
            long soundedLength = (long) (n1.getSoundedLength() * fullLength);
            long difference = fullLength - soundedLength;
            Pitch pitch = n1.getPitch();
            Beat beat = n1.getBeat();

            String sb = (articulation == null ? "" : (articulation + " ")) +
                    pitch.pitchClass.getName() + " " + beat + " at tempo = " + bpm + " bpm:" +
                    "\nFull length: " + fullLength + " ms" +
                    "\nSounded length: " + soundedLength + " ms" +
                    "\nUnsounded length: " + difference + " ms";
            log.info("\n" + sb);

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

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void testScale() {
        Scale scale = new Scale("C Hira-joshi");
        log.info("Playing back C Hira-joshi scale...");
        PlaybackController.play(scale, Octave.OCTAVE_4);
    }

    @Ignore("Disabled for Travis CI and faster testing")
    @Test
    public void testChord() {
        Chord c = new Chord("G7");
        log.info("Playing back G7 chord...");
        PlaybackController.play(c);
    }

}