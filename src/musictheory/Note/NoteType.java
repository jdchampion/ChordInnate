package musictheory.Note;

/**
 * Created by Joseph on 12/30/15.
 */
public enum NoteType {
    NON_ACCIDENTAL(false), ACCIDENTAL(true);

    private boolean isAccidental;

    NoteType(boolean isAccidental) {
        this.isAccidental = isAccidental;
    }

    protected boolean isAccidental() {
        return isAccidental;
    }
}
