package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;
    private KeySignature keySignature;

    Scale(Note root, ScaleType scaleType) throws Exception {
        /*
         * TODO Maybe convert the natural Notes into their non-accidental enharmonic equivalent,
         *      instead of throwing an Exception? Would reduce the number of try-catches.
         */
        if (root.isNatural()) {
            throw new Exception("Scale constructor called with Natural Note root.");
        }
        this.root = root;
        this.scaleType = scaleType;
        setKeySignature(root);
        if (keySignature == null) {
            throw new Exception("Scale is not Enharmonically correct.");
        }
    }

    private void setKeySignature(Note note) {
        switch(scaleType.tonality) {
            case MAJOR: this.keySignature = getMajorKeySignature(note); break;
            case MINOR: this.keySignature = getMinorKeySignature(note); break;
            default:
        }
    }

    private KeySignature getMajorKeySignature(Note note) {
        switch(note) {
            // Flat key signatures
            case C_FLAT: return KeySignature.C_FLAT_MAJOR;
            case G_FLAT: return KeySignature.G_FLAT_MAJOR;
            case D_FLAT: return KeySignature.D_FLAT_MAJOR;
            case A_FLAT: return KeySignature.A_FLAT_MAJOR;
            case E_FLAT: return KeySignature.E_FLAT_MAJOR;
            case B_FLAT: return KeySignature.B_FLAT_MAJOR;
            case F: return KeySignature.F_MAJOR;

            //Non-accidental key signatures
            case C: return KeySignature.C_MAJOR;

            // Sharp key signatures
            case G: return KeySignature.G_MAJOR;
            case D: return KeySignature.D_MAJOR;
            case A: return KeySignature.A_MAJOR;
            case E: return KeySignature.E_MAJOR;
            case B: return KeySignature.B_MAJOR;
            case F_SHARP: return KeySignature.F_SHARP_MAJOR;
            case C_SHARP: return KeySignature.C_SHARP_MAJOR;
            default: return null;
        }
    }

    private KeySignature getMinorKeySignature(Note note) {
        switch(note) {
            // Flat key signatures
            case A_FLAT: return KeySignature.A_FLAT_MINOR;
            case E_FLAT: return KeySignature.E_FLAT_MINOR;
            case B_FLAT: return KeySignature.B_FLAT_MINOR;
            case F: return KeySignature.F_MINOR;
            case C: return KeySignature.C_MINOR;
            case G: return KeySignature.G_MINOR;
            case D: return KeySignature.D_MINOR;

            //Non-accidental key signatures
            case A: return KeySignature.A_MINOR;

            // Sharp key signatures
            case E: return KeySignature.E_MINOR;
            case B: return KeySignature.B_MINOR;
            case F_SHARP: return KeySignature.F_SHARP_MINOR;
            case C_SHARP: return KeySignature.C_SHARP_MINOR;
            case G_SHARP: return KeySignature.G_SHARP_MINOR;
            case D_SHARP: return KeySignature.D_SHARP_MINOR;
            case A_SHARP: return KeySignature.A_SHARP_MINOR;
            default: return null;
        }
    }

    public String getName() {
        return root.getName() + " " + scaleType.name;
    }

    public Note getRoot() {
        return root;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    public Note[] getAscendingNotes() {
        int scaleLength = scaleType.sequence.length;
        Note[] notes = new Note[scaleLength];
        notes[0] = root;

        // Fill in the notes from the key signature
        for (int i = 0; i < scaleLength; i++) {
            for (int j = 0; j < keySignature.notes.length; j++) {
                if ((scaleType.sequence[i] + root.getRelativePitch()) % 12 == keySignature.notes[j].getRelativePitch()) {
                    notes[i] = keySignature.notes[j];
                    break;
                }
            }
        }

        // TODO Fill in any non-accidentals
        Note[] nonAccidentals = {Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B};

        for (int i = 1; i < scaleLength && notes[i] == null; i++) {
            for (Note n : nonAccidentals) {
                if ((scaleType.sequence[i] + root.getRelativePitch()) % 12 == n.getRelativePitch()) {
                    notes[i] = n;
                    break;
                }
            }
        }

        // TODO Fill in any extreme accidentals


        return notes;
    }

    private Degree getDegreeFromRoot(int lowRelativePitch, int highRelativePitch, boolean flag) {
        int relativePitchDistance = highRelativePitch - lowRelativePitch;
        if (flag) {
            return getDegreeFromRoot(root.getRelativePitch(), relativePitchDistance, false);
        }
        else {
            int tmp = -1;
            for (int i = 0; i < scaleType.sequence.length; i++) {
                if (relativePitchDistance == scaleType.sequence[i])
                    tmp = i;
                break;
            }
            switch (tmp) {
                // NOTE: There should only be eight possible degrees within a given scale
                case 0: return Degree.ROOT;
                case 1: return Degree.SECOND;
                case 2: return Degree.THIRD;
                case 3: return Degree.FOURTH;
                case 4: return Degree.FIFTH;
                case 5: return Degree.SIXTH;
                case 6: return Degree.SEVENTH;
                default: return null;
            }
        }
    }
}
