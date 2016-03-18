package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/9/16.
 */
public class Note implements Comparable<Note> {
    private final NoteType noteType;
    private Octave octave;
    private int pitch;

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

        this.pitch = this.octave.height + noteType.relativePitch;
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
    int getPitch() {
        return pitch;
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
        if (this.pitch < otherNote.pitch) return -1;
        else if (this.pitch == otherNote.pitch) return 0;
        else return 1;
    }

    /**
     *
     * @param otherNote the Note to compare the current Note against
     * @return whether this Note is lower in pitch than otherNote
     */
    public boolean isLowerPitchThan(Note otherNote) {
        return compareTo(otherNote) == -1;
    }

    /**
     *
     * @param otherNote the Note to compare the current Note against
     * @return whether this Note is the same pitch as otherNote
     */
    public boolean isSamePitchAs(Note otherNote) {
        return compareTo(otherNote) == 0;
    }

    /**
     *
     * @param otherNote the Note to compare the current Note against
     * @return whether this Note is higher in pitch than otherNote
     */
    public boolean isHigherPitchThan(Note otherNote) {
        return compareTo(otherNote) == 1;
    }

    /**
     *
     * @param otherNote the Note to compare the current Note against
     * @return whether this Note enharmonically matches otherNote
     */
    public boolean isEnharmonicTo(Note otherNote) {
        return this.getNoteType().relativePitch == otherNote.getNoteType().relativePitch;
    }

    /**
     *
     * @param otherNote the Note to compare the current Note against
     * @return whether this Note identically matches otherNote
     */
    public boolean isIdenticalTo(Note otherNote) {
        return isSamePitchAs(otherNote) && isEnharmonicTo(otherNote);
    }
}
