package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;
    private KeySignature keySignature;
    private Step[] steps;

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
        setSteps();
    }

    private void setSteps() {
        steps = new Step[scaleType.sequence.length-1];
        for (int i = 1; i < scaleType.sequence.length; i++) {
            int intervalDistance = scaleType.sequence[i] - scaleType.sequence[i-1];
            switch(intervalDistance) {
                case 1: steps[i-1] = Step.H; break;
                case 2: steps[i-1] = Step.W; break;
                case 3: steps[i-1] = Step.WH; break;
                case 4: steps[i-1] = Step.WW; break;
                default: steps[i-1] = null;
            }
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

    public Step[] getSteps() {
        return steps;
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

        // First note in the scale is the scale's root.
        notes[0] = root;

        // TODO Other cases will be needed here for scales with tonalities that are not MAJOR or MINOR.

        // Choose a set of chromatic notes based on key signature.
        Note[] chromatic;
        if (keySignature.isSharpKeySignature()) {
            chromatic = new Note[] {Note.C, Note.C_SHARP, Note.D, Note.D_SHARP,
                    Note.E, Note.F, Note.F_SHARP, Note.G,
                    Note.G_SHARP, Note.A, Note.A_SHARP, Note.B};
        }
        else {
            chromatic = new Note[] {Note.C, Note.D_FLAT, Note.D, Note.E_FLAT,
                    Note.E, Note.F, Note.G_FLAT, Note.G,
                    Note.A_FLAT, Note.A, Note.B_FLAT, Note.B};
        }

        // First pass: fill according to relative chromatic pitch.
        for (int i = 1; i < scaleLength; i++) {
            notes[i] = chromatic[(root.getRelativePitch() + scaleType.sequence[i]) % 12];
        }

        // Second pass: fix enharmonic spelling to fit this scale's key signature.
        for (int i = 1; i < scaleLength-2; i++) {
            if (notes[i-1].getLetter() == notes[i].getLetter()) {
                Note[] enharmonics = notes[i].getEnharmonicEquivalents(false, false);
                for (Note n : enharmonics) {
                    if (n.getLetter() > notes[i-1].getLetter()) {
                        notes[i] = n;
                        break;
                    }
                }
            }
            else if (notes[i].getLetter() == notes[i+1].getLetter()) {
                Note[] enharmonics = notes[i].getEnharmonicEquivalents(false, false);
                for (Note n : enharmonics) {
                    if (n.getLetter() > notes[i+1].getLetter()) {
                        notes[i] = n;
                        break;
                    }
                }
            }
            else {
                continue;
            }
        }

        // Third pass: fill in the notes from the key signature.
        for (int i = 0; i < scaleLength; i++) {
            for (int j = 0; j < keySignature.notes.length; j++) {
                if ((scaleType.sequence[i] + root.getRelativePitch()) % 12 == keySignature.notes[j].getRelativePitch()) {
                    notes[i] = keySignature.notes[j];
                    break;
                }
            }
        }

        // TODO fix some of the edge case enharmonic spellings (ex: C# Harmonic Minor's 7th degree should be B#, not C)

        return notes;
    }
}
