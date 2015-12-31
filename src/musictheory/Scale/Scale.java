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
    private ArrayList<Note> scaleDegrees = new ArrayList<>();
    private static int octaveRange;
    private static int currentOctave = 0; // Default set to 0

    protected Scale(String rootName, int root, ScaleType scaleType) {
        this.rootName = rootName;
        this.root = root;
        this.scaleType = scaleType;

        int[] sequence = scaleType.getSequence();
        for (int i = 0; i < scaleType.getNumberOfNotes(); i++) {

            // Each note is considered a degree of the scale.
            scaleDegrees.add(i, NoteFactory.buildNote(getScaleDegreeName(sequence[i])));
        }

        // The octave range of the scale == the octave range of the root note.
        this.octaveRange = scaleDegrees.get(0).getOctaveRange();
    }

    protected int getOctaveRange() {
        return octaveRange;
    }

    protected int getCurrentOctave() { return currentOctave; }

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
