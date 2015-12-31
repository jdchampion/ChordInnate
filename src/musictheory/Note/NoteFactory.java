package musictheory.Note;

/**
 * Created by Joseph on 12/29/15.
 */

public class NoteFactory {
    private static final int[][] OCTAVES = {
            {0, 12, 24, 36, 48, 60, 72, 84, 96, 108, 120},
            {1, 13, 25, 37, 49, 61, 73, 85, 97, 109, 121},
            {2, 14, 26, 38, 50, 62, 74, 86, 98, 110, 122},
            {3, 15, 27, 39, 51, 63, 75, 87, 99, 111, 123},
            {4, 16, 28, 40, 52, 64, 76, 88, 100, 112, 124},
            {5, 17, 29, 41, 53, 65, 77, 89, 101, 113, 125},
            {6, 18, 30, 42, 54, 66, 78, 90, 102, 114, 126},
            {7, 19, 31, 43, 55, 67, 79, 91, 103, 115, 127},
            {8, 20, 32, 44, 56, 68, 80, 92, 104, 116},
            {9, 21, 33, 45, 57, 69, 81, 93, 105, 117},
            {10, 22, 34, 46, 58, 70, 82, 94, 106, 118},
            {11, 23, 35, 47, 59, 71, 83, 95, 107, 119}
    };

    public static Note buildNote(String noteName, int octave) {
        int relativePitch = -1;
        int nameLength = noteName.length();
        NoteType noteType = null;

        // Determine whether the note is some kind of accidental.
        if (nameLength == 1) {
            noteType = NoteType.NON_ACCIDENTAL;
            relativePitch = nonAccidentalToRelativePitch(noteName.charAt(0));
        }
        else if (nameLength == 2){
            if (noteName.charAt(1) == 'b') {
                noteType = NoteType.ACCIDENTAL;
                relativePitch = (nonAccidentalToRelativePitch(noteName.charAt(0)) + 11) % 12;
            }
            else if (noteName.charAt(1) == '#') {
                noteType = NoteType.ACCIDENTAL;
                relativePitch = (nonAccidentalToRelativePitch(noteName.charAt(0)) + 1) % 12;
            }
            else if (noteName.charAt(1) == 'x') {
                noteType = NoteType.ACCIDENTAL;
                relativePitch = (nonAccidentalToRelativePitch(noteName.charAt(0)) + 2) % 12;
            }
            else {
                // ERROR here
            }
        }
        else if (nameLength == 3) {
            if (noteName.substring(1,3).equals("bb")) {
                noteType = NoteType.ACCIDENTAL;
                relativePitch = (nonAccidentalToRelativePitch(noteName.charAt(0)) + 10) % 12;
            }
            else {
                // ERROR here
            }
        }
        else {
            // ERROR here
        }

        // Return the correct type of Note (i.e., either RegularNote or AccidentalNote).
        switch(relativePitch) {
            default: {
                if (noteType.isAccidental()) {
                    return new AccidentalNote(noteName,
                            relativePitch,
                            octave,
                            OCTAVES[relativePitch][octave]);
                }
                else {
                    return new RegularNote(noteName,
                            relativePitch,
                            octave,
                            OCTAVES[relativePitch][octave]);
                }
            }
        }
    }

    private static int nonAccidentalToRelativePitch(char c) {
        switch(c) {
            case 'C': return 0;
            case 'D': return 2;
            case 'E': return 4;
            case 'F': return 5;
            case 'G': return 7;
            case 'A': return 9;
            case 'B': return 11;
            default: return -1;
        }
    }

    protected int getNumOctavesFor(String s) {
        switch(s) {
            case "B#":
            case "C":
            case "Dbb": return OCTAVES[0].length;
            case "C#":
            case "Db":  return OCTAVES[1].length;
            case "Cx":
            case "D":
            case "Ebb": return OCTAVES[2].length;
            case "D#":
            case "Eb":
            case "Fbb": return OCTAVES[3].length;
            case "Dx":
            case "E":
            case "Fb":  return OCTAVES[4].length;
            case "E#":
            case "F":
            case "Gbb": return OCTAVES[5].length;
            case "Ex":
            case "F#":
            case "Gb":  return OCTAVES[6].length;
            case "Fx":
            case "G":
            case "Abb": return OCTAVES[7].length;
            case "G#":
            case "Ab":  return OCTAVES[8].length;
            case "Gx":
            case "A":
            case "Bbb": return OCTAVES[9].length;
            case "A#":
            case "Bb":
            case "Cbb": return OCTAVES[10].length;
            case "Ax":
            case "B":
            case "Cb":  return OCTAVES[11].length;
            default: return -1;
        }
    }
}