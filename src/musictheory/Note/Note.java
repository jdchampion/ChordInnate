package musictheory.Note;

/**
 * Created by Joseph on 12/30/15.
 */

public abstract class Note {
    private static NoteType noteType;
    protected static int relativePitch;
    protected static int octave;
    protected static int midiValue;
    protected static String name;

    protected Note(NoteType noteType) {
        this.noteType = noteType;
    }

    protected NoteType getNoteType() { return noteType; }

    public String getName() {
        return name;
    }

    public int getRelativePitch() {
        return relativePitch;
    }

    public int getOctave() {
        return octave;
    }

    public int getMidiValue() {
        return midiValue;
    }

    public boolean isAccidental() {
        return noteType.isAccidental();
    }

    protected abstract void construct(String s, int relativePitch, int octave, int midiValue);
}