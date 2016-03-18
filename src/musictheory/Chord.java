package musictheory;


/**
 * Created by Joseph on 1/8/16.
 */
public class Chord extends IntervalSet {
    private final ChordType chordType;

    private int inversion = 0;          // With n notes, there are n-1 possible inversions
    private Octave[] defaultOctaves;    // Default octave for each Note

    public Chord(NoteType root, ChordType chordType) {
        super(root, chordType.nashvilleNumbers, Octave.ZERO, root.name + chordType.chordSymbol);
        this.chordType = chordType;
        setNoteOctaves(octave);
    }

    public Chord(NoteType root, ChordType chordType, Octave octave) {
        super(root, chordType.nashvilleNumbers, octave, root.name + chordType.chordSymbol);
        this.chordType = chordType;
        setNoteOctaves(octave);
    }

    protected Chord(Chord other) {
        super(other.rootNoteType, other.chordType.nashvilleNumbers, other.octave,
                other.rootNoteType.name + other.chordType.chordSymbol);
        this.chordType = other.chordType;
        setNoteOctaves(octave);
    }

    /**
     * Raises or lowers all Notes in the Chord to a specified octave, if it is within the octave range of the Chord.
     * If the desired octave is above the octave range of the Chord, then the Chord will be raised to its highest octave.
     * @param octave the octave number to set the Chord at
     */
    protected void setNoteOctaves(Octave octave) {
        int numNotes = super.notes.length;
        defaultOctaves = new Octave[numNotes];

        super.notes[0].setOctave(octave);
        defaultOctaves[0] = octave;

        for (int i = 1; i < numNotes; i++) {
            /*
             * Keep filling in the Octaves as usual
             * until we encounter a Note whose relativePitch < the previous Note.
             * (This will occur in every case except for Scales whose root Note's relativePitch == 0).
             * If this happens, finish filling in the remaining Octaves with a higher Octave (octave + 1).
             */
            if (super.noteTypes[i].relativePitch < super.noteTypes[i-1].relativePitch) {
                super.notes[i].setOctave(Octave.getNext(octave));
                defaultOctaves[i] = Octave.getNext(octave);
                for (int j = i + 1; j < numNotes; j++) {

                    /*
                     * For Chords, follow the same pattern
                     * to fill in Octaves as Scales, but one level deeper.
                     * This is required because some Chords (ex.: A#9)
                     * need to have its highest Note raised by two Octaves.
                     *
                     * Observe the relativePitch for the Chord:
                     * A#9:             A#   Cx  E#  B#
                     * At Octave 0:     0    1   1   1
                     * relativePitch:   22   26  29  [24]       <-- 24 < 29, so it must be raised again.
                     */

                    if (super.noteTypes[j].relativePitch < super.noteTypes[j-1].relativePitch) {
                        super.notes[j].setOctave(Octave.getNext(Octave.getNext(octave)));
                        defaultOctaves[j] = Octave.getNext(Octave.getNext(octave));

                        for (int k = j + 1; k < numNotes; k++) {
                            super.notes[k].setOctave(Octave.getNext(Octave.getNext(octave)));
                            defaultOctaves[k] = Octave.getNext(Octave.getNext(octave));
                        }
                        break;
                    }
                    else {
                        super.notes[j].setOctave(Octave.getNext(octave));
                        defaultOctaves[j] = Octave.getNext(octave);
                    }
                }
                break;
            }
            else {
                super.notes[i].setOctave(octave);
                defaultOctaves[i] = octave;
            }
        }
    }

    protected NoteType getNoteTypeWithHighestPotential() {
        /*
         * The NoteType with highest potential is the NoteType that, after
         * inversion or transposition of the IntervalSet, will become the
         * Note with the highest relativePitch. We want to make sure that
         * the entire IntervalSet stays within the playable range, so we
         * must consider this Note's maxOctave as the "upper bound" for
         * the entire IntervalSet.
         *
         * Since there are n-1 possible Chord inversions (permutations),
         * we know that the n-2nd note would be what we're looking for.
         *
         * Consider:
         *
         * C E G |          inversion 0
         *   E G | C        inversion 1
         *     G | C E      inversion 2
         *
         * In this case, 'E' is the highest potential NoteType.
         *
         */
        return super.noteTypes[super.noteTypes.length-2];
    }

    private void invertNoteOctaves(Octave octave) {
        super.notes[inversion - 1].setOctave(octave);

        for (int i = 0; i < inversion; i++) {
            super.notes[i].setOctave(octave);
        }
    }

    /**
     * Performs an inversion on the Chord by raising the octave of the lowest Note.
     * Chords with n Notes have n - 1 possible inversions. An inversion after the n - 1st
     * will return the Chord to its original structure and Note octaves.
     */
    public void invert() {
        this.inversion = (++inversion) % super.noteTypes.length;
        super.name = super.rootNoteType.name + chordType.chordSymbol;

        if (inversion != 0) {
            // The chord still has the same name,
            // but has the bass note tacked to the end of it.
            super.name += "/" + super.noteTypes[inversion].name;

            // Raise the octave of notes[inversion-1]
            invertNoteOctaves(defaultOctaves[inversion - 1]);
        }
        else {
            // Return the octaves to their original positions
            for (int i = 0; i < super.notes.length; i++) {
                super.notes[i].setOctave(defaultOctaves[i]);
            }
        }
    }

    /**
     * Resets the Chord to an uninverted state, with its original octave.
     */
    protected void resetInversion() {
        this.inversion = 0;
        super.name = super.rootNoteType.name + chordType.chordSymbol;
        for (int i = 0; i < super.notes.length; i++) {
            super.notes[i].setOctave(defaultOctaves[i]);
        }
    }

    /**
     *
     * @return the ChordType for this Chord
     */
    public ChordType getChordType() {
        return this.chordType;
    }

    /**
     * Returns the current inversion number of the Chord.
     * If the Chord has not been inverted, a value of zero (0) is returned.
     * @return the inversion number of this Chord
     */
    public int getInversionNumber() {
        return inversion;
    }
}
