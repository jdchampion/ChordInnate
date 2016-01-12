package musictheory;

import java.util.*;

import static musictheory.NoteType.*;
import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/7/16.
 */
class Theory {
    static final NoteType[] getEnharmonicEquivalents(NoteType note, boolean wantNatural, boolean wantDoubleAccidentals) {
        switch (note.relativePitch) {
            case 0: { // B# | C | CNat | Dbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, C_NATURAL, D_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, D_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {B_SHARP, C, C_NATURAL};
                }
                else {
                    return new NoteType[] {B_SHARP, C};
                }
            }
            case 1: { // Bx | C# | Db
                if (wantDoubleAccidentals) {
                    return new NoteType[] {B_DOUBLE_SHARP, C_SHARP, D_FLAT};
                }
                else {
                    return new NoteType[] {C_SHARP, D_FLAT};
                }
            }
            case 2: { // Cx | D | DNat | Ebb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {C_DOUBLE_SHARP, D, D_NATURAL, E_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {C_DOUBLE_SHARP, D, E_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {D, D_NATURAL};
                }
                else {
                    return new NoteType[] {D};
                }
            }
            case 3: { // D# | Eb | Fbb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {D_SHARP, E_FLAT, F_DOUBLE_FLAT};
                }
                else {
                    return new NoteType[] {D_SHARP, E_FLAT};
                }
            }
            case 4: { // Dx | E | ENat | Fb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {D_DOUBLE_SHARP, E, E_NATURAL, F_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {D_DOUBLE_SHARP, E, F_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {E, E_NATURAL, F_FLAT};
                }
                else {
                    return new NoteType[] {E, F_FLAT};
                }
            }
            case 5: { // E# | F | FNat | Gbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {E_SHARP, F, F_NATURAL, G_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new  NoteType[] {E_SHARP, F, G_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new  NoteType[] {E_SHARP, F, F_NATURAL};
                }
                else {
                    return new NoteType[] {E_SHARP, F};
                }
            }
            case 6: { // Ex | F# | Gb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {E_DOUBLE_SHARP, F_SHARP, G_FLAT};
                }
                else {
                    return new NoteType[] {F_SHARP, G_FLAT};
                }
            }
            case 7: { // Fx | G | GNat | Abb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {F_DOUBLE_SHARP, G, G_NATURAL, A_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {F_DOUBLE_SHARP, G, A_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {G, G_NATURAL};
                }
                else {
                    return new NoteType[] {G};
                }
            }
            case 8: { // G# | Ab
                return new NoteType[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {G_DOUBLE_SHARP, A, A_NATURAL, B_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {G_DOUBLE_SHARP, A, B_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {A, A_NATURAL};
                }
                else {
                    return new NoteType[] {A};
                }
            }
            case 10: { // A# | Bb | Cbb
                if (wantDoubleAccidentals) {
                    return new NoteType[] {A_SHARP, B_FLAT, C_DOUBLE_FLAT};
                }
                else {
                    return new NoteType[] {A_SHARP, B_FLAT};
                }
            }
            case 11: { // Ax | B | BNat | Cb
                if (wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {A_DOUBLE_SHARP, B, B_NATURAL, C_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new NoteType[] {A_DOUBLE_SHARP, B, C_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new NoteType[] {B, B_NATURAL, C_FLAT};
                }
                else {
                    return new NoteType[] {B, C_FLAT};
                }
            }
            default: return new NoteType[] {};
        }
    }

    static final NoteType getFirstPracticalEnharmonicToRelativePitch(int relativePitch) {
        switch (relativePitch) {
            case 0: return B_SHARP;
            case 1: return C_SHARP;
            case 2: return D;
            case 3: return D_SHARP;
            case 4: return E;
            case 5: return F;
            case 6: return F_SHARP;
            case 7: return G;
            case 8: return G_SHARP;
            case 9: return A;
            case 10: return A_SHARP;
            case 11: return B;
            default: return null;
        }
    }

    static Interval[] getAllIntervalsMatchingRelativePitch(int relativePitch) {
        Vector<Interval> matchingIntervals = new Vector<>(3);
        for (Interval i : Interval.values()) {
            if (i.relativePitchDistance == relativePitch) {
                matchingIntervals.add(i);
            }
        }

        Interval[] returnedIntervals = new Interval[matchingIntervals.size()];
        for (int i = 0; i < returnedIntervals.length; i++) {
            returnedIntervals[i] = matchingIntervals.get(i);
        }

        return returnedIntervals;
    }

    // TODO identical to Interval...
    static NashvilleInterval[] getAllNashvilleIntervalsMatchingRelativePitch(int relativePitch) {
        Vector<NashvilleInterval> matchingIntervals = new Vector<>(3);
        for (NashvilleInterval i : NashvilleInterval.values()) {
            if (i.relativePitchDistance == relativePitch) {
                matchingIntervals.add(i);
            }
        }

        NashvilleInterval[] returnedIntervals = new NashvilleInterval[matchingIntervals.size()];
        for (int i = 0; i < returnedIntervals.length; i++) {
            returnedIntervals[i] = matchingIntervals.get(i);
        }

        return returnedIntervals;
    }

    static char getNoteLetterForNashvilleInterval(NoteType note, NashvilleInterval nashvilleInterval) {

        // Convert the ASCII value to get the correct char
        int offset = nashvilleInterval.isNextOctave
                ? nashvilleInterval.intervalNumber - 7
                : nashvilleInterval.intervalNumber;

        int comparison = (int) note.letter + offset - 1;
        if (comparison > 71) comparison = 65 + (comparison - 72);

        if ((char) comparison > 'G') comparison = (int) 'A' + comparison - (int) 'G';

        return (char) comparison;
    }

    static char getPreviousNoteLetter(NoteType currentNote) {
        char currentChar = currentNote.letter;

        if (currentChar == 'A') return 'G';

        return (char) ((int) currentChar - 1);
    }

    static char getNextNoteLetter(NoteType currentNote) {
        char currentChar = currentNote.letter;

        if (currentChar == 'G') return 'A';

        return (char) ((int) currentChar + 1);
    }

    static boolean noteLettersFollow(NoteType n1, NoteType n2) {
        return getNextNoteLetter(n1) == n2.letter;
    }

    static final NoteType applyAccidentalTo(NoteType note, Accidental accidental) {
        Accidental a = note.accidental, b = accidental;

        // NATURAL && ACCIDENTAL == ACCIDENTAL
        if (note.isNatural()) {
            return getNoteType(note.letter, b); // whatever Accidental b is
        }

        // FLAT && SHARP == NATURAL
        else if ((a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_SHARP))  ||
                (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_FLAT))   ||
                (a.equals(FLAT) && b.equals(SHARP))                 ||
                (a.equals(SHARP) && b.equals(FLAT)))                    {
            return getNoteType(note.letter, NATURAL); // flats & sharps cancel out
        }

        // DOUBLE FLAT, DOUBLE SHARP
        else if ((a.equals(FLAT) && b.equals(FLAT)) || (a.equals(SHARP) && b.equals(SHARP))) {
            Accidental doubleAccidental = Enum.valueOf(Accidental.class, "DOUBLE_" + a.name());

            // flat + flat = double flat; sharp + sharp = double sharp
            return getNoteType(note.letter, doubleAccidental);
        }

        // TRIPLE FLAT
        else if ((a.equals(DOUBLE_FLAT) && b.equals(FLAT)) || (a.equals(FLAT) && b.equals(DOUBLE_FLAT))) {
            char letter = getNoteLetter(note, 6);

            // All results from triple flatting have a single flat, except for Bbb
            if (letter == 'B') return getNoteType(letter, DOUBLE_FLAT);
            else return getNoteType(letter, FLAT);
        }

        // TRIPLE SHARP
        else if ((a.equals(DOUBLE_SHARP) && b.equals(SHARP)) || (a.equals(SHARP) && b.equals(DOUBLE_SHARP))) {
            char letter = getNoteLetter(note, 1);

            // All results from triple sharping have a single sharp, except for Cx and Fx
            if (letter == 'C' || letter == 'F') return getNoteType(letter, DOUBLE_SHARP);
            else return getNoteType(letter, SHARP);
        }

        // QUADRUPLE FLAT
        else if (a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_FLAT)) {
            char letter = getNoteLetter(note, 5);

            // Results from quadruple flatting: F, G, Ab, Bb, C, Db, Eb
            if (letter == 'F' || letter == 'G' || letter == 'C') return getNoteType(letter, NONE);
            else return getNoteType(letter, FLAT);
        }

        // QUADRUPLE SHARP
        else if (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_SHARP)) {
            char letter = getNoteLetter(note, 2);

            // Results from quadruple sharping: C#, D#, E, F#, G#, A, B
            if (letter == 'E' || letter == 'A' || letter == 'B') return getNoteType(letter, NONE);
            else return getNoteType(letter, SHARP);
        }

        else {
//            System.out.println("Debug check: " + note);

            if (note.equals(C_FLAT) || note.equals(B) || note.equals(F_FLAT) || note.equals(G_FLAT)) {
                return getNoteType(note.letter, SHARP);
            }

            return note;
        }
    }

    private static char getNoteLetter(NoteType note, int letterShiftValue) {
        int comparison = (int) note.letter + letterShiftValue;
        if (comparison > 71) comparison = 65 + (comparison - 72);
        return (char) comparison;
    }

    static final Scale transpose(Scale scale, NoteType note) throws Exception {
        return new Scale(note, scale.getScaleType());
    }

    static Set getAllDiatonicChordTypesForScale(Scale scale) {
        ScaleType scaleType = scale.getScaleType();

        Map<Integer, NoteType> relativePitchToNote = new HashMap<>(scaleType.nashvilleIntervals.length);
        NoteType[] notes = scale.getNoteTypes();
        for (int i = 0; i < notes.length; i++) {
            relativePitchToNote.put(notes[i].relativePitch, notes[i]);
        }

        ChordType[] allChordTypes = ChordType.values();
        Set<Chord> allDiatonicChords = new HashSet<>(allChordTypes.length);
        for (ChordType ct : allChordTypes) {
            Map<Integer, ChordType> m = getChordTypeDiatonicsForScaleType(scaleType, ct);
//            System.out.println(ct + ": " + m.keySet());
            for (Integer i : m.keySet()) {
                try {
                    Chord c = new Chord(relativePitchToNote.get((i+notes[0].relativePitch)%12), ct);
                    allDiatonicChords.add(c);
                } catch (Exception e) {}
            }
        }
        return Collections.unmodifiableSet(allDiatonicChords);
    }

    private static Map getChordTypeDiatonicsForScaleType(ScaleType scaleType, ChordType chordType) {
        Map<Integer, ChordType> diatonicChordTypes = new HashMap<>(scaleType.nashvilleIntervals.length);

        // Get a Set representation of the scale's relative pitches (used for checking subsets later)
        int scaleLength = scaleType.nashvilleIntervals.length;
        Set<Integer> scaleSet = new HashSet<>(scaleLength);
        for (int i = 0; i < scaleLength; i++) {
            scaleSet.add(scaleType.nashvilleIntervals[i].relativePitchDistance);
        }

        int numPitchesInChord = chordType.nashvilleIntervals.length;
        for (int i = 0; i < scaleLength; i++) {
            Set<Integer> candidateChordSet = new HashSet<>(numPitchesInChord);

            int intervalRelativePitch = scaleType.nashvilleIntervals[i].relativePitchDistance;

            // Get the set of pitches that would be made from the ChordType at scale degree i
            for (int j = 0; j < numPitchesInChord; j++) {
                int value = (intervalRelativePitch + chordType.nashvilleIntervals[j].relativePitchDistance) % 12;
                candidateChordSet.add(value);
            }

            // If all pitches in the above set are also in the scale,
            // then the chord is diatonic for scale degree i.
            if (scaleSet.containsAll(candidateChordSet)) {
                diatonicChordTypes.put(intervalRelativePitch, chordType);
            }
        }

        return diatonicChordTypes;
    }
}


