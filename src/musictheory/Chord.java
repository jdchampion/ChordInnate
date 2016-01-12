package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NoteType.getNoteType;

/**
 * Created by Joseph on 1/8/16.
 */
public class Chord {
    private final NoteType root;
    private final ChordType chordType;
    private String name;                // Changes with inversion type

    // TODO these should also be made final,
    // but will require one method for getting noteTypes
    // and another method for getting notes,
    // rather than the current setNoteTypesAndNotes()
    private NoteType[] noteTypes;
    private Note[] notes;               // Octaves change for individual notes depending on inversion

    private int inversion = 0;
    private int[] defaultOctaves;       // Default octave for each Note

    public Chord(NoteType root, ChordType chordType) throws Exception {
        // TODO Chords beginning with a double accidental are not currently supported.
        if (root.isDoubleAccidental()) {
            throw new Exception("Chord constructor called with Double Accidental NoteType root. (" + root.name + ")");
        }

        // If the Chord constructor was called with a NoteType containing
        // a natural accidental, just convert the NoteType to its non-accidental equivalent.
        if (root.isNatural()) this.root = getNoteType(root.letter, NONE);
        else this.root = root;

        this.chordType = chordType;
        this.name = root.name + chordType.chordSymbol;

        // TODO this.noteTypes = setNoteTypes();
        // TODO this.notes = setNotes();
        setNoteTypesAndNotes();

        // Default chord octaves
        setNoteOctaves(5);
    }

    private void setNoteOctaves(int octave) {
        int numNotes = notes.length;
        defaultOctaves = new int[numNotes];

        int maxRelativePitch = root.relativePitch;
        for (int i = 1; i < noteTypes.length; i++) {
            if (noteTypes[i].relativePitch > maxRelativePitch) {
                maxRelativePitch = noteTypes[i].relativePitch;
            }
        }

        // Chords with roots F# - B will begin one octave lower
        // (to compensate for octave ranges)
        int rootOctave = (root.relativePitch + maxRelativePitch) < 6 ? octave : octave-1;
        notes[0].setOctave(rootOctave);
        defaultOctaves[0] = rootOctave;

        int currentOctave;
        for (int i = 1; i < numNotes; i++) {
            currentOctave = noteTypes[i].relativePitch < root.relativePitch ? rootOctave+1 : rootOctave;
            notes[i].setOctave(currentOctave);
            defaultOctaves[i] = currentOctave;
        }
    }

    // Slight alteration to Scale.setNoteTypesAndNotes()
    private void setNoteTypesAndNotes() {
        int numNotes = chordType.nashvilleIntervals.length;
        noteTypes = new NoteType[numNotes];
        notes = new Note[numNotes];

        noteTypes[0] = root;
        notes[0] = new Note(root);

        Accidental a = root.accidental;
        for (int i = 1; i < numNotes; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, chordType.nashvilleIntervals[i]);
            NoteType candidate = getNoteType(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNoteType(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, chordType.nashvilleIntervals[i].quality);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (root.relativePitch + chordType.nashvilleIntervals[i].relativePitchDistance) % 12;
            int offset = comparisonRelativePitch - candidateRelativePitch;

            if (offset == 0) {
                noteTypes[i] = candidate; // they match, so we're done
                notes[i] = new Note(candidate);
            }
            else {
                Accidental newAccidental = NONE;
                switch (offset) {
                    case -2: newAccidental = DOUBLE_FLAT; break;
                    case -1: newAccidental = FLAT; break;
                    case 1: newAccidental = SHARP; break;
                    case 2: newAccidental = DOUBLE_SHARP; break;
                    default: /*System.out.println("uncaught value of " + offset + " on " + chordType.relativePitches[i]);*/
                }

                candidate = Theory.applyAccidentalTo(candidate, newAccidental);

                if (candidate.relativePitch == (root.relativePitch + chordType.nashvilleIntervals[i].relativePitchDistance) % 12) {
//                    System.out.println(chordType.relativePitches[i] + " is caught in IF");
                    noteTypes[i] = candidate;
                    notes[i] = new Note(candidate);
                }
                else {
//                    System.out.println(chordType.nashvilleIntervals[i] + " is caught in ELSE");
                    candidate = getNoteType(candidate.letter, newAccidental);

                    if (candidate.relativePitch == (root.relativePitch + chordType.nashvilleIntervals[i].relativePitchDistance) % 12) {
                        noteTypes[i] = candidate;
                        notes[i] = new Note(candidate);
                    }
                    else {
                        // TODO this tends to be the problem case for Double Accidental Chords

                        noteTypes[i] = candidate;
                        notes[i] = new Note(candidate);

                    }
                }
            }
        }
    }

    protected void invert() {
        this.inversion = (++inversion) % noteTypes.length;
        this.name = root.name + chordType.chordSymbol;

        if (inversion != 0) {
            // The chord still has the same name,
            // but has the bass note tacked to the end of it.
            this.name += "/" + noteTypes[inversion].name;

            // TODO change the octave of notes[inversion-1]
            notes[inversion-1].setOctave(defaultOctaves[inversion-1]+1);
        }
        else {
            // TODO return the octaves to their original positions
            for (int i = 0; i < notes.length; i++) {
                notes[i].setOctave(defaultOctaves[i]);
            }
        }
    }

    public String getName() {
        return name;
    }

    public NoteType getRoot() {
        return root;
    }

    public NoteType[] getNoteTypes() {
        return noteTypes;
    }

    public ChordType getChordType() {
        return this.chordType;
    }

    public Note[] getNotes() {
        return notes;
    }

    public void setOctave(int octave) {
        if (octave <= root.octaveRange) {
            setNoteOctaves(octave);
        }
    }
}
