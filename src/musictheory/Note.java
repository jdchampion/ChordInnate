package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/9/16.
 */
public class Note implements Comparable<Note> {
    private final NoteType noteType;
    private Octave octave;
    private int relativePitch;

    protected Note(NoteType notetype) {
        this.noteType = notetype;
        setOctave(Octave.ZERO);
    }

    public Note(NoteType noteType, Octave octave) {
        this.noteType = noteType;
        setOctave(octave);
    }

    protected Note(Note other) {
        this.noteType = other.noteType;
        setOctave(other.octave);
    }

    /**
     *
     * @param newOctave
     */
    public void setOctave(Octave newOctave) {
        /*
         *  Octaves must fit within the range for the Note
         *  to be playable. If they are outside of it, assign
         *  the closest playable Octave value to the Note.
         */

        if (newOctave.height >= noteType.minOctave.height
                && newOctave.height <= noteType.maxOctave.height) {
            this.octave = newOctave;
        }
        else if (newOctave.height < noteType.minOctave.height) {
            this.octave = noteType.minOctave;
        }
        else {
            this.octave = noteType.maxOctave;
        }

        this.relativePitch = this.octave.height + noteType.relativePitch;
    }

    /**
     *
     * @return
     */
    public NoteType getNoteType() {
        return noteType;
    }

    /**
     *
     * @return
     */
    public Octave getOctave() {
        return octave;
    }

    /**
     *
     * @return
     */
    String getName() {
        return noteType.name;
    }

    /**
     *
     * @return
     */
    int getRelativePitch() {
        return relativePitch;
    }

    /**
     *
     * @return
     */
    char getLetter() { return noteType.letter; }

    /**
     *
     * @return
     */
    Accidental getAccidental() {
        return noteType.accidental;
    }

    /**
     *
     * @return
     */
    boolean hasAccidentalSymbol() {
        return !noteType.accidental.equals(NONE);
    }

    /**
     *
     * @return
     */
    boolean isNatural() { return noteType.accidental.equals(NATURAL); }

    /**
     *
     * @return
     */
    boolean isDoubleAccidental() { return noteType.accidental.equals(DOUBLE_FLAT)
            || noteType.accidental.equals(DOUBLE_SHARP); }

    @Override
    public int compareTo(Note otherNote) {
        if (this.relativePitch < otherNote.relativePitch) return -1;
        else if (this.relativePitch == otherNote.relativePitch) return 0;
        else return 1;
    }
}
