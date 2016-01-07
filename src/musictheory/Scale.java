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
    private Interval[] intervals;
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
        if (keySignature == null) {
            throw new Exception(this.getName() + " Scale is not Enharmonically correct.");
        }
        setSteps();
        setIntervals();
        setNotes();
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

    // TODO Possibly deprecate this method...
    private void setNotes1() {
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

                    if (notes[i].equals(A)) {
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

    private void setIntervals() {
        intervals = new Interval[scaleType.intervals.length-1];

        for (int i = 1; i < intervals.length; i++) {
            intervals[i-1] = scaleType.intervals[i];
        }
    }

    private void setNotes() {
        int scaleLength = scaleType.sequence.length;
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
            // TODO May add this later, but it will take some adjustments to the indices above.
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
            Note[] nextEnharmonics = Note
                    .getFirstPracticalEnharmonicToRelativePitch((root.getRelativePitch() + scaleType.sequence[i]) % 12)
                    .getEnharmonicEquivalents(false, true);

            Note candidate = nextEnharmonics[enharmonicIndex];

            if (noteLettersFollow(notes[i-1], candidate)) {
                notes[i] = candidate;
            }
            else {
                for (int j = 0; j < nextEnharmonics.length; j++) {

                    // Case where C = C
                    if (notes[i-1].getLetter() == nextEnharmonics[j].getLetter()) {
                        notes[i] = nextEnharmonics[j+1];
                        enharmonicIndex = j;
                        break;
                    }

                    // Cases where C = D | E | F | G | A | B
                    else if (noteLettersFollow(notes[i-1], nextEnharmonics[j])) {
                        notes[i] = nextEnharmonics[j];
                        break;
                    }
                }
            }
        }

        // Final pass


    }

    private boolean noteLettersFollow(Note n1, Note n2) {
        return getNextNoteLetter(n1) == n2.getLetter();
    }

    private char getNoteLetterForInterval(Interval nashvilleInterval) {
        char rootNote = root.getLetter();

        // Convert the ASCII value to get the correct char
        int comparison = (int) rootNote + nashvilleInterval.intervalNumber - 1;
        if (comparison > 71) comparison = 65 + (comparison - 72);

        return (char) comparison;
    }

    private char getPreviousNoteLetter(Note currentNote) {
        char currentChar = currentNote.getLetter();

        if (currentChar == 'A') return 'G';

        return (char) ((int) currentChar - 1);
    }

    private char getNextNoteLetter(Note currentNote) {
        char currentChar = currentNote.getLetter();

        if (currentChar == 'G') return 'A';

        return (char) ((int) currentChar + 1);
    }

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

    public Note[] getDescendingNotes() {
        Note[] descendingNotes = new Note[notes.length];

        for (int i = notes.length-1, j = 0; i >= 0; i--, j++) {
            descendingNotes[j] = notes[i];
        }

        return descendingNotes;
    }

    public Interval[] getIntervals() {
        return intervals;
    }
}
