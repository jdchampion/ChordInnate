package musictheory;


/**
 * Created by Joseph on 1/8/16.
 */
public class Chord extends IntervalSet {
    private final ChordType chordType;

    private int inversion = 0;          // With n notes, there are n-1 possible inversions
    private int[] defaultOctaves;       // Default octave for each Note

    public Chord(NoteType root, ChordType chordType) {
        super(root, chordType.nashvilleNumbers);
        super.name = root.name + chordType.chordSymbol;
        this.chordType = chordType;
    }

    protected Chord(Chord other) {
        super(other.root, other.chordType.nashvilleNumbers);
        super.name = other.root.name + other.chordType.chordSymbol;
        this.chordType = other.chordType;
    }

    /**
     * Raises or lowers all Notes in the Chord to a specified octave, if it is within the octave range of the Chord.
     * Otherwise, this method does nothing.
     * @param octave the octave number to set the Chord at
     */
    protected void setNoteOctaves(int octave) {
        int numNotes = super.notes.length;
        defaultOctaves = new int[numNotes];

        int maxRelativePitch = super.root.relativePitch;
        for (int i = 1; i < super.noteTypes.length; i++) {
            if (super.noteTypes[i].relativePitch > maxRelativePitch) {
                maxRelativePitch = super.noteTypes[i].relativePitch;
            }
        }

        // Chords with roots F# - B will begin one octave lower
        // (to compensate for octave ranges)
        int rootOctave = (super.root.relativePitch + maxRelativePitch) < 6 ? octave : (octave == 0 ? octave : octave-1);
        super.notes[0].setOctave(rootOctave);
        defaultOctaves[0] = rootOctave;

        int currentOctave;
        for (int i = 1; i < numNotes; i++) {
            currentOctave = super.noteTypes[i].relativePitch < super.root.relativePitch ? rootOctave+1 : rootOctave;
            super.notes[i].setOctave(currentOctave);
            defaultOctaves[i] = currentOctave;
        }
    }

    /**
     * Performs an inversion on the Chord by raising the octave of the lowest Note.
     * Chords with n Notes have n - 1 possible inversions. An inversion after the n - 1st
     * will return the Chord to its original structure and Note octaves.
     */
    public void invert() {
        this.inversion = (++inversion) % super.noteTypes.length;
        super.name = super.root.name + chordType.chordSymbol;

        if (inversion != 0) {
            // The chord still has the same name,
            // but has the bass note tacked to the end of it.
            super.name += "/" + super.noteTypes[inversion].name;

            // Change the octave of notes[inversion-1]
            super.notes[inversion-1].setOctave(defaultOctaves[inversion-1]+1);
        }
        else {
            // Return the octaves to their original positions
            for (int i = 0; i < super.notes.length; i++) {
                super.notes[i].setOctave(defaultOctaves[i]);
            }
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
