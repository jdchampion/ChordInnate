package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NoteType.getNoteType;

/**
 * Created by Joseph on 1/13/16.
 */
abstract class IntervalSet {
    protected final NoteType[] noteTypes;
    protected final NoteType root;
    protected final Note[] notes;
    protected String name;
    protected int minOctave;
    protected int maxOctave;

    IntervalSet(NoteType root, NashvilleNumber[] nashvilleNumbers) throws Exception {
        // TODO IntervalSets beginning with a double accidental are not currently supported.
        if (root.isDoubleAccidental()) {
            throw new Exception("Constructor called with Double Accidental NoteType root. (" + root.name + ")");
        }

        // If the IntervalSet constructor was called with a NoteType containing
        // a natural accidental, just convert the NoteType to its non-accidental equivalent.
        if (root.isNatural()) this.root = getNoteType(root.letter, NONE);
        else this.root = root;

        this.noteTypes = setNoteTypes(nashvilleNumbers);

        // IntervalSets have an octave range that is limited by the highest base relative pitch in the set
        this.minOctave = 0;

        this.maxOctave = getNoteTypeWithHighestRelativePitch(noteTypes).octaveRange;
        this.notes = setNotes();
        setNoteOctaves(5);
    }

    protected abstract void setNoteOctaves(int octave);

    private NoteType[] setNoteTypes(NashvilleNumber[] nashvilleNumbers) {
        int numNotes = nashvilleNumbers.length;
        NoteType[] returnedNoteTypes = new NoteType[numNotes];

        returnedNoteTypes[0] = root;

        Accidental a = root.accidental;
        for (int i = 1; i < numNotes; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleNumber(root, nashvilleNumbers[i]);
            NoteType candidate = getNoteType(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNoteType(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, nashvilleNumbers[i].accidental);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (root.relativePitch + nashvilleNumbers[i].relativePitchDistance) % 12;
            int offset = comparisonRelativePitch - candidateRelativePitch;

            if (offset == 0) {
                returnedNoteTypes[i] = candidate; // they match, so we're done
            }
            else {
                Accidental newAccidental = NONE;
                switch (offset) {
                    case -2: newAccidental = DOUBLE_FLAT; break;
                    case -1: newAccidental = FLAT; break;
                    case 1: newAccidental = SHARP; break;
                    case 2: newAccidental = DOUBLE_SHARP; break;
                    default: /*System.out.println("uncaught value of " + offset + " on " + relativePitches[i]);*/
                }

                candidate = Theory.applyAccidentalTo(candidate, newAccidental);

                if (candidate.relativePitch == (root.relativePitch + nashvilleNumbers[i].relativePitchDistance) % 12) {
//                    System.out.println(relativePitches[i] + " is caught in IF");
                    returnedNoteTypes[i] = candidate;
                }
                else {
//                    System.out.println(nashvilleNumbers[i] + " is caught in ELSE");
                    candidate = getNoteType(candidate.letter, newAccidental);

                    if (candidate.relativePitch == (root.relativePitch + nashvilleNumbers[i].relativePitchDistance) % 12) {
                        returnedNoteTypes[i] = candidate;
                    }
                    else {
                        // TODO this tends to be the problem case for Double Accidental Scales / Chords

                        returnedNoteTypes[i] = candidate;

                    }
                }
            }
        }
        return returnedNoteTypes;
    }

    private NoteType getNoteTypeWithHighestRelativePitch(NoteType[] noteTypes) {
        NoteType max = noteTypes[0];
        for (int i = 1; i < noteTypes.length; i++) {
            int x = noteTypes[i].relativePitch;
            if (max.relativePitch < x) {
                max = noteTypes[i];
            }
        }

        return max;
    }

    public void setOctave(int octave) {
        if (octave <= root.octaveRange) {
            setNoteOctaves(octave);
        }
    }

    private Note[] setNotes() {
        Note[] returnedNotes = new Note[this.noteTypes.length];
        for (int i = 0; i < returnedNotes.length; i++) {
            returnedNotes[i] = new Note(this.noteTypes[i]);
        }

        return returnedNotes;
    }

    public NoteType[] getNoteTypes() {
        return noteTypes;
    }

    public NoteType getRootNoteType() {
        return root;
    }

    public Note[] getNotes() {
        return notes;
    }

    public Note getRootNote() {
        return notes[0];
    }

    public String getName() {
        return name;
    }
}
