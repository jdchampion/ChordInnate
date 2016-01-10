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
    private NoteType root;
    private ScaleType scaleType;
    private KeySignature keySignature;
    private Step[] steps;
    private NashvilleInterval[] intervals;
    private NoteType[] notes;

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
    private Set<Chord> diatonicChords;
    private HashMap<Integer, ArrayList<Chord>> diatonicChordsByRelativePitch;


    Scale(NoteType root, ScaleType scaleType) throws Exception {
        if (root.isDoubleAccidental()) {
            throw new Exception("Scale constructor called with Double Accidental NoteType root. (" + root.name + ")");
        }
        this.root = root;

        // If the Scale constructor was called with a NoteType containing
        // a natural accidental, just convert the NoteType to its non-accidental equivalent.
        if (root.isNatural()) this.root = getNoteType(root.letter, NONE);

        this.scaleType = scaleType;
        setKeySignature(this.root);
//        if (keySignature == null) {
//            throw new Exception(this.getName() + " Scale is not Enharmonically correct.");
//        }
        setSteps();
        setIntervals();
        setNotes();
        setDiatonics();
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

    private void setDiatonics() {
        this.diatonicChords = Theory.getAllDiatonicChordsForScale(this);
        this.diatonicChordsByRelativePitch = new HashMap<>();

        for (int i = 0; i < scaleType.intervals.length; i++) {
            ArrayList<Chord> arrayList = new ArrayList<>();
            for (Iterator<Chord> it = diatonicChords.iterator(); it.hasNext(); ) {
                Chord c = it.next();
                if (notes[i].letter == c.getRoot().letter &&
                        notes[i].relativePitch == c.getRoot().relativePitch) {
                    arrayList.add(c);
                    it.remove();
                }
            }
            diatonicChordsByRelativePitch
                    .put(scaleType.intervals[i].relativePitchDistance, arrayList);
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
        notes = new NoteType[scaleLength];

        // First note in the scale is the scale's root.
        notes[0] = root;

        Accidental a = root.accidental;
        for (int i = 1; i < scaleLength; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleInterval(root, scaleType.intervals[i]);
            NoteType candidate = getNoteType(nextNoteLetter, a);

            if (!root.isNatural()) candidate = getNoteType(nextNoteLetter, a);
            else candidate = Theory.applyAccidentalTo(candidate, scaleType.intervals[i].quality);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (root.relativePitch + scaleType.intervals[i].relativePitchDistance) % 12;
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

                if (candidate.relativePitch == (root.relativePitch + scaleType.intervals[i].relativePitchDistance) % 12) {
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

    private void setKeySignature(NoteType note) {
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
        return root.name + " " + scaleType.name;
    }

    public NoteType getRoot() {
        return root;
    }

    public ScaleType getScaleType() {
        return scaleType;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    public NoteType[] getAscendingNotes() { return notes; }

    public NoteType[] getDescendingNotes() {
        NoteType[] descendingNotes = new NoteType[notes.length];

        for (int i = notes.length-1, j = 0; i >= 0; i--, j++) {
            descendingNotes[j] = notes[i];
        }

        return descendingNotes;
    }

    public NashvilleInterval[] getNashvilleIntervals() {
        return intervals;
    }

    public Set<Chord> getDiatonicChordSet() {
        return diatonicChords;
    }

    public HashMap<Integer, ArrayList<Chord>> getDiatonicChordsByRelativePitch() {
        return diatonicChordsByRelativePitch;
    }
}
