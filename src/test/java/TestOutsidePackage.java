import chordinnate.service.generator.ChordProgression;
import chordinnate.service.generator.MajorProgressionGraph1;
import chordinnate.service.playback.PlaybackController;
import chordinnate.model.musictheory.general.Texture;
import chordinnate.model.musictheory.notation.*;
import chordinnate.model.musictheory.pitch.interval.*;
import chordinnate.model.musictheory.pitch.*;
import chordinnate.model.musictheory.pitch.interval.set.*;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.pitch.key.KeySignatureType;
import chordinnate.model.musictheory.time.meter.*;
import chordinnate.model.musictheory.time.meter.fixed.incomplete.PartialMeter;
import chordinnate.model.musictheory.time.meter.free.FreeMeter;
import chordinnate.model.musictheory.time.rhythm.Beat;
import chordinnate.model.musictheory.time.rhythm.Duration;
import chordinnate.model.musictheory.time.tempo.Tempo;
import chordinnate.model.musictheory.time.tempo.TempoMarking;
import chordinnate.model.playback.*;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.util.Set;

import static chordinnate.model.musictheory.general.Texture.CHORDAL;

/**
 * Created by Joseph on 5/21/16.
 */
public class TestOutsidePackage {
    @Test
    public void testPackagePrivacy() throws Exception {

        /*
         * Publicly accessible enum types
         */

        Accidental accidental = Accidental.DOUBLE_SHARP;
        Texture texture = CHORDAL;
        NashvilleNumber nashvilleNumber = NashvilleNumber.ONE;
        Octave octave = Octave.OCTAVE_4;
        Interval interval = Interval.PERFECT_1;
        Interval other = Interval.withShortName("P1");
        EnharmonicSpelling enharmonicSpelling = EnharmonicSpelling.C;
        KeySignature keySignature = KeySignature.C_MAJOR;
        KeySignatureType keySignatureType = KeySignatureType.MAJOR;
        Letter letter = Letter.C;
        Pitch pitch = Pitch.C_4;
        PitchClass pitchClass = PitchClass.C;
        MeterClassificationType meterClassificationType = MeterClassificationType.SIMPLE;
        MeterSubdivision meterSubdivision = MeterSubdivision.DUPLE;
        Beat beat = Beat.QUARTER;
        Duration duration = Duration.QUARTER;
        TempoMarking tempoMarking = TempoMarking.ALLEGRO;
        Articulation articulation = Articulation.STACCATO;
        Dynamic dynamic = Dynamic.FORTE;
        Effect effect = Effect.GLISSANDO;

        /*
         * Publicly accessible final class types
         */

        MajorProgressionGraph1 majorProgressionGraph1; // static class -- no constructor necessary
        Chord chord = new Chord("Cmaj");
        Scale scale = new Scale("C Major");
        FreeMeter freeMeter = new FreeMeter();
        PlaybackController playbackController; // static class -- no constructor necessary

        /*
         * Publicly accessible abstract class types
         */

        // TODO: add another layer of abstraction to allow ChordProgression to be constructed without implementing this
        ChordProgression chordProgression = new ChordProgression() {
            @Override
            public Set<DefaultEdge> getAllEdges(NashvilleNumber sourceVertex, NashvilleNumber targetVertex) {
                return super.getAllEdges(sourceVertex, targetVertex);
            }
        };
        IntervalSet intervalSet1 = new Chord("Cmaj");
        IntervalSet intervalSet2 = new Scale("C Major");
        intervalSet1.isDiatonicTo(keySignature);
        intervalSet1.isDiatonicTo(intervalSet2);
        intervalSet1.getPitchesForOctave(octave);
        intervalSet2.isDiatonicTo(keySignature);
        intervalSet2.isDiatonicTo(intervalSet2);
        intervalSet2.getPitchesForOctave(octave);

        /*
         * Publicly accessible class types
         */

        PartialMeter partialMeter = new PartialMeter();
        TimeSignature timeSignature = new TimeSignature(7, 8);
        Tempo tempo = new Tempo(beat, 120);
        Note note = new Note(pitch, beat);

        /*
         * Possible to get and access the item
         */

        keySignature.modulateFlat();
        keySignature.modulateSharp();
        keySignature.getParallelKey();
        keySignature.getRelativeKey();
        octave.getNext();
        octave.getPrevious();
        pitch.transpose(pitch);
        pitch.transpose(pitchClass, octave);
        pitch.transpose(true, interval);
        interval.invert();
        scale.getPitchesForOctave(octave);
        scale.isDiatonicTo(keySignature);
        scale.isDiatonicTo(scale);
        scale.getFullName();
        chord.getPitchesForOctave(octave);
    }
}
