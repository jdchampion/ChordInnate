import chordinnate.musictheory.general.Accidental;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.notation.NashvilleNumber;
import chordinnate.musictheory.pitch.*;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.interval.set.ScaleType;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.junit.Test;

/**
 * Created by Joseph on 5/21/16.
 */
public class TestOutsidePackage {
    @Test
    public void testPackagePrivacy() throws Exception {

        // Examples of publicly accessible class types
        Accidental accidental = Accidental.DOUBLE_SHARP;
        KeySignature keySignature = KeySignature.C_MAJOR;
        NashvilleNumber nashvilleNumber = NashvilleNumber.ONE;
        Octave octave = Octave.OCTAVE_4;
        Pitch pitch = Pitch.C_4;
        PitchClass pitchClass = PitchClass.C;
        PitchInterval pitchInterval = PitchInterval.PERFECT_FIFTH;
        ScaleType scaleType = ScaleType.MAJOR;

        // Possible to get and access the item
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
        scaleType.getPitchIntervals();
    }
}
