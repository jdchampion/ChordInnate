package musictheory;

import java.util.*;

import static musictheory.Accidental.*;
import static musictheory.KeySignature.*;
import static musictheory.NoteType.*;
import static musictheory.Step.*;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://study.com/academy/lesson/understanding-and-building-musical-scales-definitions-types-of-scales.html
 */
public class Scale {
    private final NoteType root;
    private final ScaleType scaleType;
    private final KeySignature keySignature;
    private final Step[] steps;
    private final NashvilleInterval[] nashvilleIntervals;

    // TODO these should also be made final,
    // but will require one method for getting noteTypes
    // and another method for getting notes,
    // rather than the current setNoteTypesAndNotes()
    private NoteType[] noteTypes;
    private Note[] notes;

    /*
     * Both data structures hold the same items (i.e., chords that are diatonic to this scale).
     *
     * The SET is better-suited for comparing diatonics of two scales (which might come in handy later).
     *
     * The HASHMAP places all chords with matching roots into the same key value.
     * The key value is identical to the relative pitch of this Scale's note matching the chord root.
     *
     * That sounds confusing, so here's an example:
     *
     * If this scale is C Major:        C D E F G A B
     * Then the relative pitches are:   0 2 4 5 7 9 11
     *
     * After finding the diatonic chords (automatically done so in the Scale class constructor),
     * this is how the HashMap is structured (key: values):
     *
     * 0: Csus2 Cadd9 Csus4 Cmaj Csus2sus4 C5 C6 Cmaj7 Cmaj9 C6add9 Cmaj13
     * 2: Dsus4 Dm6add9 Dsus2sus4 Dm9 Dm D5 D7sus4 Dm7 Dmadd9 Dsus2 Dm11 Dm13 Dm6
     * 4: Em7 E5 E11♭9 Em7♯5 Em Esus4 E7sus4
     * 5: Fmaj Fadd9 Fmaj7♭5 F-5 F6add9 Fmaj13♯11 Fsus2 Fmaj9♯11 F5 F6 Fmaj7 Fmaj9
     * 7: Gsus2 G13 G6 G5 Gadd9 G6add9 G9 Gsus4 G7 G7sus4 G11 Gmaj Gsus2sus4
     * 9: A5 Am7♯5 Asus2sus4 Asus2 Am Am9 Am11 Asus4 Amadd9 Am7 A7sus4
     * 11: Bm7♭5 Bdim Bm7♯5 Bø
     *
     * The HashMap structure will provide faster lookups for chord progression generation,
     * in the event that we want to choose a diatonic chord for C, D, E, etc.
     *
     */
    private final Set<Chord> diatonicChords;
    private final HashMap<Integer, ArrayList<ChordType>> diatonicChordTypesByRelativePitch;


    Scale(NoteType root, ScaleType scaleType) throws Exception {
        // TODO Scales beginning with a double accidental are not currently supported.
        if (root.isDoubleAccidental()) {
            throw new Exception("Scale constructor called with Double Accidental NoteType root. (" + root.name + ")");
        }

        // If the Scale constructor was called with a NoteType containing
        // a natural accidental, just convert the NoteType to its non-accidental equivalent.
        if (root.isNatural()) this.root = getNoteType(root.letter, NONE);
        else this.root = root;

        this.scaleType = scaleType;
        this.keySignature = setKeySignature(this.root);
        this.steps = setSteps();
        this.nashvilleIntervals = scaleType.nashvilleIntervals;

        // TODO this.noteTypes = setNoteTypes();
        // TODO this.notes = setNotes();
        setNoteTypesAndNotes();

        this.diatonicChords = Theory.getAllDiatonicChordTypesForScale(this);
        this.diatonicChordTypesByRelativePitch = setDiatonicHashMap();

        // Default scale octave
        setNoteOctaves(5);
    }

    private KeySignature setKeySignature(NoteType note) {
        KeySignature keySignature;
        switch (scaleType.tonality) {
            case MAJOR_TONALITY: keySignature = getMajorKeySignatureWithRoot(note); break;
            case MINOR_TONALITY: keySignature = getMinorKeySignatureWithRoot(note); break;
            case NO_TONALITY: keySignature = NO_KEY_SIGNATURE; break;
            default: keySignature = NO_KEY_SIGNATURE; break;
        }

        return keySignature;
    }

    private Step[] setSteps() {
        Step[] steps = new Step[scaleType.nashvilleIntervals.length-1];

        // TODO Add support for ScaleTypes that were defined in descending order

        for (int i = 1; i < scaleType.nashvilleIntervals.length; i++) {
            int intervalDistance = scaleType.nashvilleIntervals[i].relativePitchDistance - scaleType.nashvilleIntervals[i-1].relativePitchDistance;
            if (intervalDistance < 0) intervalDistance = (scaleType.nashvilleIntervals[i-1].relativePitchDistance - scaleType.nashvilleIntervals[i].relativePitchDistance) % 4;
            switch (intervalDistance) {
                case 1: steps[i-1] = H; break;
                case 2: steps[i-1] = W; break;
                case 3: steps[i-1] = WH; break;
                case 4: steps[i-1] = WW; break;
                default: steps[i-1] = null;
            }
        }

        return steps;
    }

    private HashMap setDiatonicHashMap() {
        HashMap hashMap = new HashMap<>();

        // Operate on a copy of the set (so as not to remove items from the class member set)
        HashSet<Chord> s = new HashSet<>(diatonicChords);

        for (int i = 0; i < scaleType.nashvilleIntervals.length; i++) {
            ArrayList<ChordType> arrayList = new ArrayList<>();
            for (Iterator<Chord> it = s.iterator(); it.hasNext(); ) {
                Chord c = it.next();
                if (noteTypes[i].letter == c.getRoot().letter &&
                        noteTypes[i].relativePitch == c.getRoot().relativePitch) {
                    arrayList.add(c.getChordType());
                    it.remove();
                }
            }
            hashMap.put((scaleType.nashvilleIntervals[i].relativePitchDistance)%12, arrayList);
        }

        return hashMap;
    }

    private void setNoteTypesAndNotes() {
        int scaleLength = scaleType.nashvilleIntervals.length;
        noteTypes = new NoteType[scaleLength];
        notes = new Note[scaleLength];

        // First note in the scale is the scale's root.
        noteTypes[0] = root;
        notes[0] = new Note(root);

        Accidental a = root.accidental;
        for (int i = 1; i < scaleLength; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, scaleType.nashvilleIntervals[i]);
            NoteType candidate = getNoteType(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNoteType(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, scaleType.nashvilleIntervals[i].quality);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (root.relativePitch + scaleType.nashvilleIntervals[i].relativePitchDistance) % 12;
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
                    default: /*System.out.println("uncaught value of " + offset + " on " + scaleType.intervals[i]);*/
                }

                candidate = Theory.applyAccidentalTo(candidate, newAccidental);

                if (candidate.relativePitch == comparisonRelativePitch) {
//                    System.out.println(scaleType.intervals[i] + " is caught in IF");
                    noteTypes[i] = candidate;
                    notes[i] = new Note(candidate);
                }
                else {
//                    System.out.println(scaleType.intervals[i] + " is caught in ELSE");

                    candidate = getNoteType(candidate.letter, newAccidental);

                    if (candidate.relativePitch == (root.relativePitch + scaleType.nashvilleIntervals[i].relativePitchDistance) % 12) {
                        noteTypes[i] = candidate;
                        notes[i] = new Note(candidate);
                    }
                    else {
                        // TODO this tends to be the problem case for Double Accidental Scales

                        noteTypes[i] = candidate;
                        notes[i] = new Note(candidate);

                    }
                }
            }
        }
    }

    private void setNoteOctaves(int octave) {
        int numNotes = notes.length;

        // Scales with roots F# - B will begin one octave lower
        // (to compensate for octave ranges)
        int rootOctave = root.relativePitch < 6 ? octave : octave-1;
        notes[0].setOctave(rootOctave);

        int currentOctave;
        for (int i = 1; i < numNotes; i++) {
            currentOctave = noteTypes[i].relativePitch < root.relativePitch ? rootOctave+1 : rootOctave;
            notes[i].setOctave(currentOctave);
        }
    }

    public Step[] getSteps() {
        return steps;
    }

    public String getName() {
        return root.name + " " + scaleType.name;
    }

    public NoteType getRootNoteType() {
        return root;
    }

    public Note getRoot() {
        return notes[0];
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    protected NoteType[] getNoteTypes() {
        return noteTypes;
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
        return nashvilleIntervals;
    }

    public Set<Chord> getDiatonicChordSet() {
        return diatonicChords;
    }

    public HashMap<Integer, ArrayList<ChordType>> getDiatonicChordTypesByRelativePitch() {
        return diatonicChordTypesByRelativePitch;
    }
}
