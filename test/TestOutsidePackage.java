import chordinnate.generator.ChordProgression;
import chordinnate.generator.MajorProgressionGraph1;
import chordinnate.musictheory.general.Accidental;
import chordinnate.musictheory.general.Texture;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchIntervalQuality;
import chordinnate.musictheory.pitch.interval.notation.NashvilleNumber;
import chordinnate.musictheory.pitch.*;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.interval.notation.RomanNumeral;
import chordinnate.musictheory.pitch.interval.set.*;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import chordinnate.musictheory.pitch.notation.KeySignatureType;
import chordinnate.musictheory.pitch.notation.Letter;
import chordinnate.musictheory.time.meter.*;
import chordinnate.musictheory.time.rhythm.Beat;
import chordinnate.musictheory.time.rhythm.Duration;
import chordinnate.musictheory.time.rhythm.Tuplet;
import chordinnate.musictheory.time.tempo.Tempo;
import chordinnate.musictheory.time.tempo.TempoMarking;
import chordinnate.playback.*;
import chordinnate.util.SequentialUtil;
import org.jetbrains.annotations.NotNull;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.util.Set;

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
        Texture texture = Texture.CHORDAL;
        NashvilleNumber nashvilleNumber = NashvilleNumber.ONE;
        RomanNumeral romanNumeral = RomanNumeral.I;
        ChordType chordType = ChordType.MAJOR;
        ScaleType scaleType = ScaleType.MAJOR;
        Octave octave = Octave.OCTAVE_4;
        PitchInterval pitchInterval = PitchInterval.PERFECT_FIFTH;
        PitchIntervalQuality pitchIntervalQuality = PitchIntervalQuality.PERFECT;
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
        Tuplet tuplet = Tuplet.TRIPLET;
        TempoMarking tempoMarking = TempoMarking.ALLEGRO;
        Articulation articulation = Articulation.STACCATO;
        Dynamic dynamic = Dynamic.FORTE;
        Effect effect = Effect.GLISSANDO;

        /*
         * Publicly accessible final class types
         */

        MajorProgressionGraph1 majorProgressionGraph1; // static class -- no constructor necessary
        Chord chord = new Chord(pitchClass, chordType);
        Scale scale = new Scale(pitchClass, scaleType);
        AdditiveCompleteMeter additiveCompleteMeter; // constructor not accessible
        FractionalMeter fractionalMeter; // constructor not accessible
        FreeMeter freeMeter = new FreeMeter();
        NonAdditiveCompleteMeter nonAdditiveCompleteMeter; // constructor not accessible
        PlayBack playBack; // static class -- no constructor necessary
        // FIXME: SequentialUtil should not be accessible outside src.chordinnate
        SequentialUtil sequentialUtil; // static class -- no constructor necessary

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
        IntervalSet intervalSet1 = new Chord(pitchClass, chordType);
        IntervalSet intervalSet2 = new Scale(pitchClass, scaleType);
        NonSerialIntervalSet nonSerialIntervalSet = new Chord(pitchClass, chordType);
        SerialIntervalSet serialIntervalSet = new Scale(pitchClass, scaleType);
        // FIXME: CompleteMeter, FixedMeter, IncompleteMeter, TransientMeter constructors should not be accessible
        CompleteMeter completeMeter = new CompleteMeter() {
            @Override
            public boolean is(@NotNull MeterClassificationType meterClassificationType) {
                return super.is(meterClassificationType);
            }
        };
        FixedMeter fixedMeter = new FixedMeter() {
            @Override
            public boolean is(@NotNull MeterClassificationType meterClassificationType) {
                return super.is(meterClassificationType);
            }
        };
        IncompleteMeter incompleteMeter = new IncompleteMeter() {
            @Override
            public boolean is(@NotNull MeterClassificationType meterClassificationType) {
                return super.is(meterClassificationType);
            }
        };
        TransientMeter transientMeter = new TransientMeter() {
            @Override
            public int getNumberOfMeterChanges() {
                return super.getNumberOfMeterChanges();
            }
        };

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

        keySignature.getNext();
        keySignature.getPrevious();
        keySignature.getParallelMajor();
        keySignature.getParallelMinor();
        keySignature.getRelativeMajor();
        keySignature.getRelativeMinor();
        octave.getNext();
        octave.getPrevious();
        pitch.getEnharmonics();
        pitch.transposeTo(octave);
        pitch.transposeTo(pitch);
        pitch.transposeTo(pitchClass, octave);
        pitch.transposeTo(pitchInterval, true);
        pitchClass.getEnharmonics();
        pitchInterval.getEnharmonics();
        pitchInterval.getInversion();
        scaleType.length();
        chordType.length();

        scaleType.getPitchIntervals();
        chordType.getPitchIntervals();
        chordType.getBaseOctaves();
        scale.getPitchesForOctave(octave);
        chord.getPitchesForOctave(octave);
    }
}
