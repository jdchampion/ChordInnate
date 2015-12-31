package musictheory.Note;

/**
 * Created by Joseph on 12/30/15.
 */
public class RegularNote extends Note {
    RegularNote(String s, int relativePitch, int octaveRange) {
        super(NoteType.NON_ACCIDENTAL);
        construct(s, relativePitch, octaveRange);
    }

    @Override
    protected void construct(String s, int relativePitch, int octaveRange) {
        this.name = s;
        this.relativePitch = relativePitch;
        this.octaveRange = octaveRange;
    }
}
