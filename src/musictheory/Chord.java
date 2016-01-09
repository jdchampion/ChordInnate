package musictheory;

import static musictheory.Accidental.*;
import static musictheory.Note.getNote;

/**
 * Created by Joseph on 1/8/16.
 */
public class Chord {
    private Note root;
    private ChordType chordType;
    private String name;
    private Note[] notes;
    // TODO add support for inversions here

    public Chord(Note root, ChordType chordType) {
        this.root = root;
        this.chordType = chordType;
        setName();
        setNotes();
    }

    private void setName() {
        this.name = root.getName() + chordType.getChordSymbol();

        // TODO add support for inversions here
    }

    // TODO identical method to Scale.setNotes()
    private void setNotes() {
        int numNotes = chordType.relativePitches.length;
        notes = new Note[numNotes];

        notes[0] = root;

        Accidental a = root.getAccidental();
        for (int i = 1; i < numNotes; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, chordType.relativePitches[i]);
            Note candidate = getNote(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNote(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, chordType.relativePitches[i].quality);

            int candidateRelativePitch = candidate.getRelativePitch();
            int comparisonRelativePitch = (root.getRelativePitch() + chordType.relativePitches[i].relativePitchDistance) % 12;
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

                if (candidate.getRelativePitch() == (root.getRelativePitch() + chordType.relativePitches[i].relativePitchDistance) % 12) {
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

    public String getName() {
        return name;
    }

    public Note getRoot() {
        return root;
    }

    public Note[] getNotes() {
        return notes;
    }
}
