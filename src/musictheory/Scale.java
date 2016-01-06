package musictheory;

import static musictheory.ScaleType.*;
import static musictheory.KeySignature.*;
import static musictheory.Accidental.*;
import static musictheory.Note.*;
import static musictheory.Step.*;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://study.com/academy/lesson/understanding-and-building-musical-scales-definitions-types-of-scales.html
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;
    private KeySignature keySignature;
    private Step[] steps;
    private Note[] notes;
//    Interval[] intervals;

    Scale(Note root, ScaleType scaleType) throws Exception {
        /*
         * TODO Maybe convert the natural Notes into their non-accidental enharmonic equivalent,
         *      instead of throwing an Exception? Would reduce the number of try-catches.
         */
        if (root.isNatural()) {
            throw new Exception("Scale constructor called with Natural Note root. (" + root.getName() + ")");
        }
        if (root.isDoubleAccidental()) {
            throw new Exception("Scale constructor called with Double Accidental Note root. (" + root.getName() + ")");
        }
        this.root = root;
        this.scaleType = scaleType;
        setKeySignature(root);
        if (keySignature == null) {
            throw new Exception(this.getName() + " Scale is not Enharmonically correct.");
        }
        setSteps();
        setNotes();
//        setIntervals();
    }

    Scale getTransposition(Note note) throws Exception {
        return new Scale(note, this.scaleType);
    }

    private void setSteps() {
        steps = new Step[scaleType.sequence.length-1];

        for (int i = 1; i < scaleType.sequence.length; i++) {
            int intervalDistance = scaleType.sequence[i] - scaleType.sequence[i-1];
            switch (intervalDistance) {
                case 1: steps[i-1] = H; break;
                case 2: steps[i-1] = W; break;
                case 3: steps[i-1] = WH; break;
                case 4: steps[i-1] = WW; break;
                default: steps[i-1] = null;
            }
        }
    }

    private void setNotes() {
        int scaleLength = scaleType.sequence.length;
        Note[] notes = new Note[scaleLength];

        // First note in the scale is the scale's root.
        notes[0] = root;

        // TODO Shortcut for Chromatic scales (since it's the only type with all 12 notes)
        if (scaleType.equals(CHROMATIC)) {

            Note[] chromatic = (root.getAccidental().equals(FLAT))
                    ? getFlatChromaticNoteArray()
                    : getSharpChromaticNoteArray();

            // Get the index for the root
            int index = root.getRelativePitch();

            for (int i = 0; i < chromatic.length; i++, index++) {
                notes[i] = chromatic[(index+chromatic.length)%chromatic.length];
            }

            this.notes = notes;
            return;
        }

        Note[] enharmonics;

        for (int i = 1; i < scaleLength; i++) {
            int pitch = (root.getRelativePitch() + scaleType.sequence[i]) % 12;

            // We're not going to create any scales with naturals or double-accidentals
            enharmonics = getFirstPracticalEnharmonicToRelativePitch(pitch)
                    .getEnharmonicEquivalents(false, false);

            if (enharmonics.length == 1) {
                notes[i] = enharmonics[0];
            }
            else {
                notes[i] = notes[i-1].getLetter() == enharmonics[0].getLetter()
                        ? enharmonics[1]
                        : enharmonics[0];
            }

            // Extra check for the first & last indices
            if (i == scaleLength-1) {
                if (notes[i].getLetter() == notes[0].getLetter()) {

                    if (notes[i].equals(Note.A)) {
                        notes[i] = G_DOUBLE_SHARP;

                        if (notes[i-1].equals(G)) {
                            notes[i-1] = F_DOUBLE_SHARP;
                        }
                    }
                    else {
                        enharmonics = getFirstPracticalEnharmonicToRelativePitch(pitch)
                                .getEnharmonicEquivalents(false, true);

                        for (int j = 0; j < enharmonics.length; j++) {
                            if (enharmonics[j].getLetter() < notes[0].getLetter()) {
                                notes[i] = enharmonics[j];
                                break;
                            }
                        }
                    }
                }
            }

            // Fit notes to key signature
            if (keySignature != null) {
                for (int j = 0; j < keySignature.notes.length; j++) {
                    if ((scaleType.sequence[i] + root.getRelativePitch()) % 12 == keySignature.notes[j].getRelativePitch()) {
                        notes[i] = keySignature.notes[j];
                        break;
                    }
                }
            }
        }

        this.notes = notes;
    }

//    private void setIntervals() {
//        // TODO assuming we already know the steps and notes for the scale
//        intervals = new Interval[steps.length];
//
//        for (int i = 1; i < scaleType.sequence.length; i++) {
//            intervals[i-1] = getIntervalForDegree(scaleType.sequence[i], i);
//        }
//    }
//
//    private Interval getIntervalForDegree(int toDegreeRelativePitch, int toDegreeIntervalNumber) {
//        Interval[] toDegreeIntervals = root.getEnharmonicIntervalsToRelativeDegree(toDegreeIntervalNumber);
//
//        // FIXME
//        return toDegreeIntervals[0];
//    }

    private void setKeySignature(Note note) {
        switch (scaleType.tonality) {
            case MAJOR_TONALITY: this.keySignature = getMajorKeySignature(note); break;
            case MINOR_TONALITY: this.keySignature = getMinorKeySignature(note); break;
            case CHROMATIC_TONALITY: this.keySignature = getMajorKeySignature(note); break;
            default:
        }
    }

    public Step[] getSteps() {
        return steps;
    }

    public String getName() {
        return root.getName() + " " + scaleType.name;
    }

    public Note getRoot() {
        return root;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    public Note[] getAscendingNotes() { return notes; }
}
