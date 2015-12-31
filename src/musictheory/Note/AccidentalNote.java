package musictheory.Note;

/**
 * Created by Joseph on 12/30/15.
 */
public class AccidentalNote extends Note {
    AccidentalNote(String s, int relativePitch, int octave, int midiValue) {
        super(NoteType.ACCIDENTAL);
        construct(s, relativePitch, octave, midiValue);
    }

    @Override
    protected void construct(String s, int relativePitch, int octave, int midiValue) {
        this.name = s;
        this.relativePitch = relativePitch;
        this.octave = octave;
        this.midiValue = midiValue;
    }
}
