package musictheory;

import java.util.*;

import static musictheory.KeySignature.*;
import static musictheory.Step.*;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://study.com/academy/lesson/understanding-and-building-musical-scales-definitions-types-of-scales.html
 */
public class Scale extends IntervalSet {
    private final ScaleType scaleType;
    private final KeySignature keySignature;
    private final Step[] steps;

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
    private final Set<Chord> diatonicChordTypes;
    private final HashMap<Integer, ArrayList<ChordType>> diatonicChordTypesByRelativePitch;

    public Scale(NoteType root, ScaleType scaleType) throws Exception {
        super(root, scaleType.nashvilleNumbers, Octave.OCTAVE_MIN, root.name + " " + scaleType.name);
        this.scaleType = scaleType;
        this.keySignature = setKeySignature(this.rootNoteType);
        this.steps = setSteps();
        this.diatonicChordTypes = setDiatonicChordTypes();
        this.diatonicChordTypesByRelativePitch = setDiatonicHashMap();
    }

    public Scale(NoteType root, ScaleType scaleType, Octave octave) throws Exception {
        super(root, scaleType.nashvilleNumbers, octave, root.name + " " + scaleType.name);
        this.scaleType = scaleType;
        this.keySignature = setKeySignature(this.rootNoteType);
        this.steps = setSteps();
        this.diatonicChordTypes = setDiatonicChordTypes();
        this.diatonicChordTypesByRelativePitch = setDiatonicHashMap();
    }

    public Scale(Scale other) throws Exception {
        super(other.rootNoteType, other.scaleType.nashvilleNumbers, other.octave,
                other.rootNoteType.name + " " + other.scaleType.name);
        this.scaleType = other.scaleType;
        this.keySignature = other.keySignature;
        this.steps = other.steps;
        this.diatonicChordTypes = other.diatonicChordTypes;
        this.diatonicChordTypesByRelativePitch = other.diatonicChordTypesByRelativePitch;
    }

    /**
     * Raises or lowers all Notes in the Scale to a specified octave, if it is within the octave range of the Scale.
     * If the desired octave is above the octave range of the Scale, then the Scale will be raised to its highest octave.
     * This function operates in Θ(n) time.
     * @param octave the octave number to set the Scale at
     */
    protected void setNoteOctaves(Octave octave) {
        int numNotes = super.notes.length;

        super.notes[0].setOctave(octave);

        for (int i = 1; i < numNotes; i++) {
            /*
             * Keep filling in the Octaves as usual
             * until we encounter a Note whose relativePitch < the previous Note.
             * (This will occur in every case except for Scales whose root Note's relativePitch == 0).
             * If this happens, finish filling in the remaining Octaves with a higher Octave (octave + 1).
             */
            if (super.noteTypes[i].relativePitch < super.noteTypes[i-1].relativePitch) {
                super.notes[i].setOctave(octave.raiseBy(1));
                for (int j = i + 1; j < numNotes; j++) {
                    super.notes[j].setOctave(octave.raiseBy(1));
                }
                break;
            }
            else {
                super.notes[i].setOctave(octave);
            }
        }
    }

    protected NoteType getNoteTypeWithHighestPotential() {
        // For Scales, this will always be the last NoteType in the list
        return super.noteTypes[super.noteTypes.length-1];
    }

    /**
     *
     * @param note
     * @return
     */
    private KeySignature setKeySignature(NoteType note) {
        KeySignature keySignature;
        switch (scaleType.tonality) {
            case MAJOR_TONALITY: keySignature = getMajorKeySignatureWithRoot(note); break;
            case MINOR_TONALITY: keySignature = getMinorKeySignatureWithRoot(note); break;
            default: keySignature = NO_KEY_SIGNATURE; break;
        }

        return keySignature;
    }

    /**
     *
     * @return
     */
    private Step[] setSteps() {
        Step[] steps = new Step[scaleType.nashvilleNumbers.length-1];

        // TODO Add support for ScaleTypes that were defined in descending order

        for (int i = 1; i < scaleType.nashvilleNumbers.length; i++) {
            int intervalDistance = scaleType.nashvilleNumbers[i].relativePitchDistance - scaleType.nashvilleNumbers[i-1].relativePitchDistance;
            if (intervalDistance < 0) intervalDistance = (scaleType.nashvilleNumbers[i-1].relativePitchDistance - scaleType.nashvilleNumbers[i].relativePitchDistance) % 4;
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

    /**
     *
     * @return
     */
    private Set setDiatonicChordTypes() {
        Map<Integer, NoteType> relativePitchToNote = new HashMap<>(scaleType.nashvilleNumbers.length);
        for (int i = 0; i < super.noteTypes.length; i++) {
            relativePitchToNote.put(super.noteTypes[i].relativePitch, super.noteTypes[i]);
        }

        ChordType[] allChordTypes = ChordType.values();
        Set<Chord> allDiatonicChords = new HashSet<>(allChordTypes.length);
        for (ChordType ct : allChordTypes) {
            Map<Integer, ChordType> m = getChordTypeDiatonicsForScale(ct);
            for (Integer i : m.keySet()) {
                try {
                    Chord c = new Chord(relativePitchToNote.get((i+super.noteTypes[0].relativePitch)%12), ct);
                    allDiatonicChords.add(c);
                } catch (Exception e) {}
            }
        }
        return Collections.unmodifiableSet(allDiatonicChords);
    }

    /**
     *
     * @param chordType
     * @return
     */
    private Map getChordTypeDiatonicsForScale(ChordType chordType) {
        Map<Integer, ChordType> diatonicChordTypes = new HashMap<>(scaleType.nashvilleNumbers.length);

        // Get a Set representation of the scale's relative pitches (used for checking subsets later)
        int scaleLength = scaleType.nashvilleNumbers.length;
        Set<Integer> scaleSet = new HashSet<>(scaleLength);
        for (int i = 0; i < scaleLength; i++) {
            scaleSet.add(scaleType.nashvilleNumbers[i].relativePitchDistance);
        }

        int numPitchesInChord = chordType.nashvilleNumbers.length;
        for (int i = 0; i < scaleLength; i++) {
            Set<Integer> candidateChordSet = new HashSet<>(numPitchesInChord);

            int intervalRelativePitch = scaleType.nashvilleNumbers[i].relativePitchDistance;

            // Get the set of pitches that would be made from the ChordType at scale degree i
            for (int j = 0; j < numPitchesInChord; j++) {
                int value = (intervalRelativePitch + chordType.nashvilleNumbers[j].relativePitchDistance) % 12;
                candidateChordSet.add(value);
            }

            // If all pitches in the above set are also in the scale,
            // then the chord is diatonic for scale degree i.
            if (scaleSet.containsAll(candidateChordSet)) {
                diatonicChordTypes.put(intervalRelativePitch, chordType);
            }
        }

        return diatonicChordTypes;
    }

    /**
     *
     * @return
     */
    private HashMap setDiatonicHashMap() {
        HashMap hashMap = new HashMap<>();

        // Operate on a copy of the set (so as not to remove items from the class member set)
        HashSet<Chord> s = new HashSet<>(diatonicChordTypes);

        for (int i = 0; i < scaleType.nashvilleNumbers.length; i++) {
            ArrayList<ChordType> arrayList = new ArrayList<>();
            for (Iterator<Chord> it = s.iterator(); it.hasNext(); ) {
                Chord c = it.next();
                if (super.noteTypes[i].letter == c.getRootNoteType().letter &&
                        super.noteTypes[i].relativePitch == c.getRootNoteType().relativePitch) {
                    arrayList.add(c.getChordType());
                    it.remove();
                }
            }
            hashMap.put((scaleType.nashvilleNumbers[i].relativePitchDistance)%12, arrayList);
        }

        return hashMap;
    }

    /**
     *
     * @return
     */
    public ScaleType getScaleType() {
        return scaleType;
    }

    /**
     *
     * @return
     */
    public KeySignature getKeySignature() {
        return keySignature;
    }

    /**
     *
     * @return
     */
    public Step[] getSteps() {
        return steps;
    }

    /**
     *
     * @return
     */
    public Note[] getAscendingNotes() { return super.notes; }

    /**
     *
     * @return
     */
    public Note[] getDescendingNotes() {
        Note[] descendingNotes = new Note[super.notes.length];

        for (int i = super.notes.length-1, j = 0; i >= 0; i--, j++) {
            descendingNotes[j] = super.notes[i];
        }

        return descendingNotes;
    }

    /**
     *
     * @return
     */
    public NashvilleNumber[] getNashvilleNumbers() {
        return scaleType.nashvilleNumbers;
    }

    /**
     *
     * @return
     */
    public Set<Chord> getDiatonicChordTypes() {
        return diatonicChordTypes;
    }

    /**
     *
     * @return
     */
    public HashMap<Integer, ArrayList<ChordType>> getDiatonicChordTypesByRelativePitch() {
        return diatonicChordTypesByRelativePitch;
    }

    /**
     *
     * @param relativePitch
     * @return
     */
    public NoteType getNoteTypeWithRelativePitch(int relativePitch) {
        for (NoteType nt : super.noteTypes) {
            if (nt.relativePitch == relativePitch) {
                return nt;
            }
        }

        return null;
    }
}
