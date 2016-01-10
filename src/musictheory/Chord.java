package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NoteType.getNoteType;

/**
 * Created by Joseph on 1/8/16.
 */
public class Chord {
    private NoteType root;
    private ChordType chordType;
    private String name;
    private NoteType[] notes;
    // TODO add support for inversions here

    public Chord(NoteType root, ChordType chordType) {
        this.root = root;
        this.chordType = chordType;
        setName();
        setNotes();
    }

    private void setName() {
        this.name = root.name + chordType.chordSymbol;

        // TODO add support for inversions here
    }

    // TODO identical method to Scale.setNotes()
    private void setNotes() {
        int numNotes = chordType.relativePitches.length;
        notes = new NoteType[numNotes];

        notes[0] = root;

        Accidental a = root.accidental;
        for (int i = 1; i < numNotes; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, chordType.relativePitches[i]);
            NoteType candidate = getNoteType(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNoteType(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, chordType.relativePitches[i].quality);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (root.relativePitch + chordType.relativePitches[i].relativePitchDistance) % 12;
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

                if (candidate.relativePitch == (root.relativePitch + chordType.relativePitches[i].relativePitchDistance) % 12) {
//                    System.out.println(scaleType.intervals[i] + " is caught in IF");
                    notes[i] = candidate;
                }
                else {
//                    System.out.println(scaleType.intervals[i] + " is caught in ELSE");
                    notes[i] = getNoteType(candidate.letter, newAccidental);
                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public NoteType getRoot() {
        return root;
    }

    public NoteType[] getNotes() {
        return notes;
    }
}
