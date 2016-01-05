package musictheory;

/**
 * Created by Joseph on 1/1/16.
 *
 * References: https://en.wikibooks.org/wiki/Music_Theory/Scales_and_Intervals
 *             http://study.com/academy/lesson/understanding-and-building-musical-scales-definitions-types-of-scales.html
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;
    private KeySignature keySignature;
    private Step[] steps;
    private Note[] notes;
    Interval[] intervals;

    Scale(Note root, ScaleType scaleType) throws Exception {
        /*
         * TODO Maybe convert the natural Notes into their non-accidental enharmonic equivalent,
         *      instead of throwing an Exception? Would reduce the number of try-catches.
         */
        if (root.isNatural()) {
            throw new Exception("Scale constructor called with Natural Note root. (" + root.getName() + ")");
        }
        if (root.isDoubleAccidental()) {
            throw new Exception("Scale constructor called with Double Accidental Note root. (" + root.getName() + ")");
        }
        this.root = root;
        this.scaleType = scaleType;
        setKeySignature(root);
        if (keySignature == null) {
            throw new Exception(this.getName() + " Scale is not Enharmonically correct.");
        }
        setSteps();
        setNotes();
//        setIntervals();
    }

    Scale getTransposition(Note note) throws Exception {
        return new Scale(note, this.scaleType);
    }

    private void setSteps() {
        steps = new Step[scaleType.sequence.length-1];

        for (int i = 1; i < scaleType.sequence.length; i++) {
            int intervalDistance = scaleType.sequence[i] - scaleType.sequence[i-1];
            switch (intervalDistance) {
                case 1: steps[i-1] = Step.H; break;
                case 2: steps[i-1] = Step.W; break;
                case 3: steps[i-1] = Step.WH; break;
                case 4: steps[i-1] = Step.WW; break;
                default: steps[i-1] = null;
            }
        }
    }

    private void setNotes() {
        int scaleLength = scaleType.sequence.length;
        Note[] notes = new Note[scaleLength];

        // First note in the scale is the scale's root.
        notes[0] = root;

        // TODO Shortcut for Chromatic scales (since it's the only type with all 12 notes)
        if (scaleType.equals(ScaleType.CHROMATIC)) {

            Note[] chromatic = (root.getAccidental().equals(Accidental.FLAT))
                    ? Note.getFlatChromaticNoteArray()
                    : Note.getSharpChromaticNoteArray();

            // Get the index for the root
            int index = root.getRelativePitch();

            for (int i = 0; i < chromatic.length; i++, index++) {
                notes[i] = chromatic[(index+chromatic.length)%chromatic.length];
            }

            this.notes = notes;
            return;
        }

        Note[] enharmonics;

        for (int i = 1; i < scaleLength; i++) {
            int pitch = (root.getRelativePitch() + scaleType.sequence[i]) % 12;

            // We're not going to create any scales with naturals or double-accidentals
            enharmonics = Note.getFirstPracticalEnharmonicToRelativePitch(pitch)
                    .getEnharmonicEquivalents(false, false);

            if (enharmonics.length == 1) {
                notes[i] = enharmonics[0];
            }
            else {
                notes[i] = notes[i-1].getLetter() == enharmonics[0].getLetter()
                        ? enharmonics[1]
                        : enharmonics[0];
            }

            // Extra check for the first & last indices
            if (i == scaleLength-1) {
                if (notes[i].getLetter() == notes[0].getLetter()) {

                    if (notes[i].equals(Note.A)) {
                        notes[i] = Note.G_DOUBLE_SHARP;

                        if (notes[i-1].equals(Note.G)) {
                            notes[i-1] = Note.F_DOUBLE_SHARP;
                        }
                    }
                    else {
                        enharmonics = Note.getFirstPracticalEnharmonicToRelativePitch(pitch)
                                .getEnharmonicEquivalents(false, true);

                        for (int j = 0; j < enharmonics.length; j++) {
                            if (enharmonics[j].getLetter() < notes[0].getLetter()) {
                                notes[i] = enharmonics[j];
                                break;
                            }
                        }
                    }
                }
            }

            // Fit notes to key signature
            if (keySignature != null) {
                for (int j = 0; j < keySignature.notes.length; j++) {
                    if ((scaleType.sequence[i] + root.getRelativePitch()) % 12 == keySignature.notes[j].getRelativePitch()) {
                        notes[i] = keySignature.notes[j];
                        break;
                    }
                }
            }
        }

        this.notes = notes;
    }

//    private void setIntervals() {
//        // TODO assuming we already know the steps and notes for the scale
//        intervals = new Interval[steps.length];
//
//        for (int i = 1; i < scaleType.sequence.length; i++) {
//            intervals[i-1] = getIntervalForDegree(scaleType.sequence[i], i);
//        }
//    }
//
//    private Interval getIntervalForDegree(int toDegreeRelativePitch, int toDegreeIntervalNumber) {
//        Interval[] toDegreeIntervals = root.getEnharmonicIntervalsToRelativeDegree(toDegreeIntervalNumber);
//
//        // FIXME
//        return toDegreeIntervals[0];
//    }

    private void setKeySignature(Note note) {
        switch (scaleType.tonality) {
            case MAJOR: this.keySignature = getMajorKeySignature(note); break;
            case MINOR: this.keySignature = getMinorKeySignature(note); break;
            case CHROMATIC: this.keySignature = getMajorKeySignature(note); break;
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

            // Non-accidental key signatures
            case C: return KeySignature.C_MAJOR;

            // Sharp key signatures
            case G: return KeySignature.G_MAJOR;
            case D: return KeySignature.D_MAJOR;
            case A: return KeySignature.A_MAJOR;
            case E: return KeySignature.E_MAJOR;
            case B: return KeySignature.B_MAJOR;
            case F_SHARP: return KeySignature.F_SHARP_MAJOR;
            case C_SHARP: return KeySignature.C_SHARP_MAJOR;

            // TODO weird cases
//            case G_SHARP: return KeySignature.F_MINOR;
//            case D_SHARP: return KeySignature.C_MINOR;
//            case A_SHARP: return KeySignature.F_SHARP_MINOR;
//            case E_SHARP: return KeySignature.D_MINOR;
//
//            case B_SHARP: return KeySignature.C_MAJOR;
//
//            case F_FLAT: return KeySignature.C_SHARP_MINOR;

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

            // Non-accidental key signatures
            case A: return KeySignature.A_MINOR;

            // Sharp key signatures
            case E: return KeySignature.E_MINOR;
            case B: return KeySignature.B_MINOR;
            case F_SHARP: return KeySignature.F_SHARP_MINOR;
            case C_SHARP: return KeySignature.C_SHARP_MINOR;
            case G_SHARP: return KeySignature.G_SHARP_MINOR;
            case D_SHARP: return KeySignature.D_SHARP_MINOR;
            case A_SHARP: return KeySignature.A_SHARP_MINOR;

            // TODO weird cases
//            case D_FLAT: return KeySignature.E_MAJOR;
//            case G_FLAT: return KeySignature.A_MAJOR;
//            case C_FLAT: return KeySignature.D_MAJOR;
//            case F_FLAT: return KeySignature.G_MINOR;
//
//            case B_SHARP: return KeySignature.E_FLAT_MAJOR;
//            case E_SHARP: return KeySignature.A_FLAT_MAJOR;

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

    public Note[] getAscendingNotes() { return notes; }
}
