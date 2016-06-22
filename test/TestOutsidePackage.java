import chordinnate.musictheory.Accidental;
import org.junit.Test;

import chordinnate.musictheory.*;

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
        accidental.getNext();
        accidental.getPrevious();
        keySignature.getNext();
        keySignature.getPrevious();
        keySignature.getParallelMajor();
        keySignature.getParallelMinor();
        keySignature.getRelativeMajor();
        keySignature.getRelativeMinor();
        nashvilleNumber.getAccidental();
        octave.getNext();
        octave.getPrevious();
        pitch.getPitchClass();
        pitch.getOctave();
        pitch.getEnharmonics();
        pitch.transposeTo(octave);
        pitch.transposeTo(pitch);
        pitch.transposeTo(pitchClass, octave);
        pitch.transposeTo(pitchInterval, true);
        pitchClass.getEnharmonics();
        pitchClass.getOctaveRange();
        pitchInterval.getEnharmonics();
        pitchInterval.getInversion();
        scaleType.getPitchIntervals();

        // Possible to get the item, but can't do anything with it from outside package
        keySignature.getKey();
        keySignature.getKeySignatureType();
        pitchClass.getEnharmonicSpelling();
        pitchInterval.getPitchIntervalQuality();
        pitchInterval.getRomanNumeral();
        scaleType.getScaleTypeProperty();

        // Examples of using inaccessible items as arguments outside the package
        keySignature.contains(pitchClass.getEnharmonicSpelling());
    }
}
