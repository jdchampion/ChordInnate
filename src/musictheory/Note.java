package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/9/16.
 */
public class Note {
    private final NoteType noteType;
    private int octave;
    private int relativePitch;

    protected Note(NoteType notetype) {
        this.noteType = notetype;
        this.octave = 0;
        this.relativePitch = notetype.relativePitch;
    }

    public Note(NoteType noteType, int octave) {
        this.noteType = noteType;
        this.octave = octave <= noteType.octaveRange ? octave : noteType.octaveRange/2;
        this.relativePitch = 12 * octave + noteType.relativePitch;
    }

    /**
     *
     * @param newOctave
     */
    public void setOctave(int newOctave) {
        this.octave = newOctave <= noteType.octaveRange ? newOctave : octave;
        this.relativePitch = 12 * octave + noteType.relativePitch;
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
    public int getOctave() {
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
}
