package musictheory;

import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/9/16.
 */
public class Note {
    private NoteType noteType;
    private int octave;
    private int relativePitch;

    public Note(NoteType noteType, int octave) {
        this.noteType = noteType;
        this.octave = octave <= noteType.octaveRange ? octave : noteType.relativePitch;
        this.relativePitch = 12 * octave + noteType.relativePitch;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public int getOctave() {
        return octave;
    }

    String getName() {
        return noteType.name;
    }

    int getRelativePitch() {
        return relativePitch;
    }

    char getLetter() { return noteType.letter; }

    Accidental getAccidental() {
        return noteType.accidental;
    }

    boolean hasAccidentalSymbol() {
        return !noteType.accidental.equals(NONE);
    }

    boolean isNatural() { return noteType.accidental.equals(NATURAL); }

    boolean isDoubleAccidental() { return noteType.accidental.equals(DOUBLE_FLAT)
            || noteType.accidental.equals(DOUBLE_SHARP); }
}
