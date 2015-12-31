package musictheory.Note;

/**
 * Created by Joseph on 12/30/15.
 */
public abstract class Note {
    private static NoteType noteType;
    protected static int relativePitch;
    protected static int octaveRange;
    protected static int currentOctave = 0; // Default set to 0
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

    public int getOctaveRange() { return octaveRange; }

    public int getCurrentOctave() {
        return currentOctave;
    }

    public int getMidiValue() { return midiValue; }

    public boolean isAccidental() {
        return noteType.isAccidental();
    }

    protected abstract void construct(String s, int relativePitch, int octaveRange);

    public void changeOctave(int octave) {
        if (octave > -1 && octave <= this.octaveRange) {
            this.currentOctave = octave;
            this.midiValue = 12 * octave + this.relativePitch;
        }
    }
}