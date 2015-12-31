package musictheory.Scale;
import musictheory.Note.Note;
import musictheory.Note.NoteFactory;

import java.util.ArrayList;

/**
 * Created by Joseph on 12/31/15.
 */
public class Scale {
    private static String rootName;
    private static int root;
    private static ScaleType scaleType;
    private ArrayList<Note> notes;
    private static int octaveRange;
    private static int currentOctave;

    protected Scale(String rootName, int root, ScaleType scaleType) {
        this.rootName = rootName;
        this.root = root;
        this.scaleType = scaleType;

        int[] sequence = scaleType.getSequence();
        for (int i = 0; i < scaleType.getNumberOfNotes(); i++) {
            notes.add(i, NoteFactory.buildNote(getScaleDegreeName(sequence[i])));
        }

//        this.octaveRange = notes.get(0).
    }

    protected int getOctaveRange() {
        return octaveRange;
    }

    protected String getRootName() {
        return rootName;
    }

    public String getScaleTypeName() {
        return scaleType.getName();
    }

    public String getName() {
        return getRootName() + " " + getScaleTypeName();
    }

    // TODO
    private String getScaleDegreeName(int i) {
        return "TODO";
    }
}
