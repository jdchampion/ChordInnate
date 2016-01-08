package musictheory;

import static musictheory.Accidental.*;
import static musictheory.ScaleType.*;
import static musictheory.KeySignature.*;
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
    private NashvilleInterval[] intervals;
    private Note[] notes;


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
//        if (keySignature == null) {
//            throw new Exception(this.getName() + " Scale is not Enharmonically correct.");
//        }
        setSteps();
        setIntervals();
        setNotes();
    }

    Scale getTransposition(Note note) throws Exception {
        return new Scale(note, this.scaleType);
    }

    private void setSteps() {
        steps = new Step[scaleType.intervals.length-1];

        for (int i = 1; i < scaleType.intervals.length; i++) {
            int intervalDistance = scaleType.intervals[i].relativePitchDistance - scaleType.intervals[i-1].relativePitchDistance;
            switch (intervalDistance) {
                case 1: steps[i-1] = H; break;
                case 2: steps[i-1] = W; break;
                case 3: steps[i-1] = WH; break;
                case 4: steps[i-1] = WW; break;
                default: steps[i-1] = null;
            }
        }
    }

    private void setIntervals() {
        intervals = new NashvilleInterval[scaleType.intervals.length-1];

        for (int i = 1; i < intervals.length; i++) {
            intervals[i-1] = scaleType.intervals[i];
        }
    }

    private void setNotes() {
        int scaleLength = scaleType.intervals.length;
        notes = new Note[scaleLength];

        // First note in the scale is the scale's root.
        notes[0] = root;

        Accidental a = root.getAccidental();
        for (int i = 1; i < scaleLength; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, scaleType.intervals[i]);
            Note candidate = getNote(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNote(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, scaleType.intervals[i].quality);

            int candidateRelativePitch = candidate.getRelativePitch();
            int comparisonRelativePitch = (root.getRelativePitch() + scaleType.intervals[i].relativePitchDistance) % 12;
            int offset = comparisonRelativePitch - candidateRelativePitch;

            if (offset == 0) {
                notes[i] = candidate; // they match, so we're done
            }
            else {
                Accidental newAccidental = NONE;
                switch (offset) {
                    case -2: newAccidental = DOUBLE_FLAT; break;
                    case -1: newAccidental = FLAT; break;
                    case 1: newAccidental = SHARP; break;
                    case 2: newAccidental = DOUBLE_SHARP; break;
                    default: /*System.out.println("uncaught value of " + offset + " on " + scaleType.intervals[i]);*/
                }

                candidate = Theory.applyAccidentalTo(candidate, newAccidental);

                if (candidate.getRelativePitch() == (root.getRelativePitch() + scaleType.intervals[i].relativePitchDistance) % 12) {
//                    System.out.println(scaleType.intervals[i] + " is caught in IF");
                    notes[i] = candidate;
                }
                else {
//                    System.out.println(scaleType.intervals[i] + " is caught in ELSE");
                    notes[i] = getNote(candidate.getLetter(), newAccidental);
                }
            }
        }
    }

    @Deprecated
    private void setNotes1() {
        int scaleLength = scaleType.intervals.length;
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
            int pitch = (root.getRelativePitch() + scaleType.intervals[i].relativePitchDistance) % 12;

            // We're not going to create any scales with naturals or double-accidentals
            Note tmp = Theory.getFirstPracticalEnharmonicToRelativePitch(pitch);
            enharmonics = Theory.getEnharmonicEquivalents(tmp, false, false);

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

                    if (notes[i].equals(A)) {
                        notes[i] = G_DOUBLE_SHARP;

                        if (notes[i-1].equals(G)) {
                            notes[i-1] = F_DOUBLE_SHARP;
                        }
                    }
                    else {
                        tmp = Theory.getFirstPracticalEnharmonicToRelativePitch(pitch);
                        enharmonics = Theory.getEnharmonicEquivalents(tmp, false, true);

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
                    if ((scaleType.intervals[i].relativePitchDistance + root.getRelativePitch()) % 12 == keySignature.notes[j].getRelativePitch()) {
                        notes[i] = keySignature.notes[j];
                        break;
                    }
                }
            }
        }

        this.notes = notes;
    }

    @Deprecated
    private void setNotes2() {
        int scaleLength = scaleType.intervals.length;
        notes = new Note[scaleLength];

        // First note in the scale is the scale's root.
        notes[0] = root;

        // Figure out the enharmonic index of this root.
        int enharmonicIndex = 0;
        switch (root.getAccidental()) {
            case DOUBLE_FLAT: enharmonicIndex = 2; break;
            case FLAT: {
                switch (root) {
                    case E_FLAT: case A_FLAT: case B_FLAT: case G_FLAT: enharmonicIndex = 1; break;
                    case D_FLAT: case F_FLAT: case C_FLAT: enharmonicIndex = 2; break;
                }
                break;
            }
            // TODO May add this later, but it will take some adjustments to the other cases.
//            case NATURAL: enharmonicIndex = 2; break;
            case SHARP: {
                switch (root) {
                    case B_SHARP: case D_SHARP: case E_SHARP: case G_SHARP: case A_SHARP: enharmonicIndex = 0; break;
                    case C_SHARP: case F_SHARP: enharmonicIndex = 1; break;
                }
                break;
            }
            case DOUBLE_SHARP: enharmonicIndex = 0; break;
            case NONE: enharmonicIndex = 1; break;
            default: enharmonicIndex = 1; break;
        }

        // Fill the scale in with enharmonic notes.
        for (int i = 1; i < scaleLength; i++) {
            Note candidate = Theory
                    .getFirstPracticalEnharmonicToRelativePitch((root.getRelativePitch() + scaleType.intervals[i].relativePitchDistance) % 12);
            Note[] nextEnharmonics = Theory.getEnharmonicEquivalents(candidate, false, true);

            candidate = nextEnharmonics[enharmonicIndex];

            if (Theory.noteLettersFollow(notes[i-1], candidate)) {
                notes[i] = candidate;
            }
            else {
                for (int j = 0; j < nextEnharmonics.length; j++) {

                    // Case where comparing C* (previous note with any accidental)
                    //                   vs C* (candidate note with any accidental)
                    if (notes[i-1].getLetter() == nextEnharmonics[j].getLetter()) {
                        notes[i] = nextEnharmonics[j+1];
                        enharmonicIndex = j;
                        break;
                    }

                    // Cases where comparing C* (previous note with any accidental)
                    //                    vs B* (candidate note with any accidental)
                    else if (Theory.noteLettersFollow(notes[i-1], nextEnharmonics[j])) {
                        notes[i] = nextEnharmonics[j];
                        break;
                    }

                    // Cases where comparing C* (previous note with any accidental)
                    //                    vs E* | F* | G* | A* (candidate note with any accidental)
                    else {
                        // TODO Final pass: fix any intervals where the relative distance doesn't match the Nashville number
                        // Example: ScaleType.intervals[2] == FLAT_THREE (b3) (C -> Eb),
                        //      but notes[2] at this point is SHARP_TWO (#2) (C -> D#)


                        int relativePitchDistance = nextEnharmonics[j].getRelativePitch() - root.getRelativePitch();

                        Interval[] candidateIntervals = Theory.getAllIntervalsMatchingRelativePitch(relativePitchDistance);
                        for (Interval candidateInterval : candidateIntervals) {
                            if (candidateInterval.equals(scaleType.intervals[i])) {
                                // WE GOT IT
                                System.out.println(scaleType.intervals[i]);
                                notes[i] = nextEnharmonics[j];
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void setKeySignature(Note note) {
        switch (scaleType.tonality) {
            case MAJOR_TONALITY: this.keySignature = getMajorKeySignatureWithRoot(note); break;
            case MINOR_TONALITY: this.keySignature = getMinorKeySignatureWithRoot(note); break;
            case NO_TONALITY: this.keySignature = NO_KEY_SIGNATURE; break;
            default: this.keySignature = NO_KEY_SIGNATURE; break;
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

    public Note[] getDescendingNotes() {
        Note[] descendingNotes = new Note[notes.length];

        for (int i = notes.length-1, j = 0; i >= 0; i--, j++) {
            descendingNotes[j] = notes[i];
        }

        return descendingNotes;
    }

    public NashvilleInterval[] getNashvilleIntervals() {
        return intervals;
    }
}
