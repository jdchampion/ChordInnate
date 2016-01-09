package musictheory;

import java.util.Vector;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import static musictheory.Note.*;
import static musictheory.Accidental.*;

/**
 * Created by Joseph on 1/7/16.
 */
class Theory {
    static final Note[] getEnharmonicEquivalents(Note note, boolean wantNatural, boolean wantDoubleAccidentals) {
        switch (note.getRelativePitch()) {
            case 0: { // B# | C | CNat | Dbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, C_NATURAL, D_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, D_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {B_SHARP, C, C_NATURAL};
                }
                else {
                    return new Note[] {B_SHARP, C};
                }
            }
            case 1: { // Bx | C# | Db
                if (wantDoubleAccidentals) {
                    return new Note[] {B_DOUBLE_SHARP, C_SHARP, D_FLAT};
                }
                else {
                    return new Note[] {C_SHARP, D_FLAT};
                }
            }
            case 2: { // Cx | D | DNat | Ebb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {C_DOUBLE_SHARP, D, D_NATURAL, E_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {C_DOUBLE_SHARP, D, E_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {D, D_NATURAL};
                }
                else {
                    return new Note[] {D};
                }
            }
            case 3: { // D# | Eb | Fbb
                if (wantDoubleAccidentals) {
                    return new Note[] {D_SHARP, E_FLAT, F_DOUBLE_FLAT};
                }
                else {
                    return new Note[] {D_SHARP, E_FLAT};
                }
            }
            case 4: { // Dx | E | ENat | Fb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {D_DOUBLE_SHARP, E, E_NATURAL, F_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {D_DOUBLE_SHARP, E, F_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {E, E_NATURAL, F_FLAT};
                }
                else {
                    return new Note[] {E, F_FLAT};
                }
            }
            case 5: { // E# | F | FNat | Gbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {E_SHARP, F, F_NATURAL, G_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new  Note[] {E_SHARP, F, G_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new  Note[] {E_SHARP, F, F_NATURAL};
                }
                else {
                    return new Note[] {E_SHARP, F};
                }
            }
            case 6: { // Ex | F# | Gb
                if (wantDoubleAccidentals) {
                    return new Note[] {E_DOUBLE_SHARP, F_SHARP, G_FLAT};
                }
                else {
                    return new Note[] {F_SHARP, G_FLAT};
                }
            }
            case 7: { // Fx | G | GNat | Abb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {F_DOUBLE_SHARP, G, G_NATURAL, A_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {F_DOUBLE_SHARP, G, A_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {G, G_NATURAL};
                }
                else {
                    return new Note[] {G};
                }
            }
            case 8: { // G# | Ab
                return new Note[] {G_SHARP, A_FLAT};
            }
            case 9: { // Gx | A | ANat | Bbb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {G_DOUBLE_SHARP, A, A_NATURAL, B_DOUBLE_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {G_DOUBLE_SHARP, A, B_DOUBLE_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {A, A_NATURAL};
                }
                else {
                    return new Note[] {A};
                }
            }
            case 10: { // A# | Bb | Cbb
                if (wantDoubleAccidentals) {
                    return new Note[] {A_SHARP, B_FLAT, C_DOUBLE_FLAT};
                }
                else {
                    return new Note[] {A_SHARP, B_FLAT};
                }
            }
            case 11: { // Ax | B | BNat | Cb
                if (wantNatural && wantDoubleAccidentals) {
                    return new Note[] {A_DOUBLE_SHARP, B, B_NATURAL, C_FLAT};
                }
                else if (!wantNatural && wantDoubleAccidentals) {
                    return new Note[] {A_DOUBLE_SHARP, B, C_FLAT};
                }
                else if (wantNatural && !wantDoubleAccidentals) {
                    return new Note[] {B, B_NATURAL, C_FLAT};
                }
                else {
                    return new Note[] {B, C_FLAT};
                }
            }
            default: return new Note[] {};
        }
    }

    static final Note getFirstPracticalEnharmonicToRelativePitch(int relativePitch) {
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

    static char getNoteLetterForNashvilleInterval(Note note, NashvilleInterval nashvilleInterval) {

        // Convert the ASCII value to get the correct char
        int comparison = (int) note.getLetter() + nashvilleInterval.intervalNumber - 1;
        if (comparison > 71) comparison = 65 + (comparison - 72);

        return (char) comparison;
    }

    static char getPreviousNoteLetter(Note currentNote) {
        char currentChar = currentNote.getLetter();

        if (currentChar == 'A') return 'G';

        return (char) ((int) currentChar - 1);
    }

    static char getNextNoteLetter(Note currentNote) {
        char currentChar = currentNote.getLetter();

        if (currentChar == 'G') return 'A';

        return (char) ((int) currentChar + 1);
    }

    static boolean noteLettersFollow(Note n1, Note n2) {
        return getNextNoteLetter(n1) == n2.getLetter();
    }

    static final Note applyAccidentalTo(Note note, Accidental accidental) {
        Accidental a = note.getAccidental(), b = accidental;

        // NATURAL && ACCIDENTAL == ACCIDENTAL
        if (note.isNatural()) {
            return getNote(note.getLetter(), b); // whatever Accidental b is
        }

        // FLAT && SHARP == NATURAL
        else if ((a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_SHARP))  ||
                (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_FLAT))   ||
                (a.equals(FLAT) && b.equals(SHARP))                 ||
                (a.equals(SHARP) && b.equals(FLAT)))                    {
            return getNote(note.getLetter(), NATURAL); // flats & sharps cancel out
        }

        // DOUBLE FLAT, DOUBLE SHARP
        else if ((a.equals(FLAT) && b.equals(FLAT)) || (a.equals(SHARP) && b.equals(SHARP))) {
            Accidental doubleAccidental = Enum.valueOf(Accidental.class, "DOUBLE_" + a.name());

            // flat + flat = double flat; sharp + sharp = double sharp
            return getNote(note.getLetter(), doubleAccidental);
        }

        // TRIPLE FLAT
        else if ((a.equals(DOUBLE_FLAT) && b.equals(FLAT)) || (a.equals(FLAT) && b.equals(DOUBLE_FLAT))) {
            char letter = getNoteLetter(note, 6);

            // All results from triple flatting have a single flat, except for Bbb
            if (letter == 'B') return getNote(letter, DOUBLE_FLAT);
            else return getNote(letter, FLAT);
        }

        // TRIPLE SHARP
        else if ((a.equals(DOUBLE_SHARP) && b.equals(SHARP)) || (a.equals(SHARP) && b.equals(DOUBLE_SHARP))) {
            char letter = getNoteLetter(note, 1);

            // All results from triple sharping have a single sharp, except for Cx and Fx
            if (letter == 'C' || letter == 'F') return getNote(letter, DOUBLE_SHARP);
            else return getNote(letter, SHARP);
        }

        // QUADRUPLE FLAT
        else if (a.equals(DOUBLE_FLAT) && b.equals(DOUBLE_FLAT)) {
            char letter = getNoteLetter(note, 5);

            // Results from quadruple flatting: F, G, Ab, Bb, C, Db, Eb
            if (letter == 'F' || letter == 'G' || letter == 'C') return getNote(letter, NONE);
            else return getNote(letter, FLAT);
        }

        // QUADRUPLE SHARP
        else if (a.equals(DOUBLE_SHARP) && b.equals(DOUBLE_SHARP)) {
            char letter = getNoteLetter(note, 2);

            // Results from quadruple sharping: C#, D#, E, F#, G#, A, B
            if (letter == 'E' || letter == 'A' || letter == 'B') return getNote(letter, NONE);
            else return getNote(letter, SHARP);
        }

        else {
//            System.out.println("Debug check: " + note);

            if (note.equals(C_FLAT) || note.equals(B) || note.equals(F_FLAT) || note.equals(G_FLAT)) {
                return getNote(note.getLetter(), SHARP);
            }

            return note;
        }
    }

    private static char getNoteLetter(Note note, int letterShiftValue) {
        int comparison = (int) note.getLetter() + letterShiftValue;
        if (comparison > 71) comparison = 65 + (comparison - 72);
        return (char) comparison;
    }

    static final Scale transpose(Scale scale, Note note) throws Exception {
        return new Scale(note, scale.getScaleType());
    }

    static Set getAllDiatonicChordsForScale(Scale scale) {
        ScaleType scaleType = scale.getScaleType();

        Map<Integer, Note> relativePitchToNote = new HashMap<>(scaleType.intervals.length);
        Note[] notes = scale.getAscendingNotes();
        for (int i = 0; i < notes.length; i++) {
            relativePitchToNote.put(notes[i].getRelativePitch(), notes[i]);
        }

        ChordType[] allChordTypes = ChordType.values();
        Set<Chord> allDiatonicChords = new HashSet<>(allChordTypes.length);
        for (ChordType ct : allChordTypes) {
            Map<Integer, ChordType> m = getChordTypeDiatonicsForScaleType(scaleType, ct);
//            System.out.println(ct + ": " + m.keySet());
            for (Integer i : m.keySet()) {
                Chord c = new Chord(relativePitchToNote.get((i+notes[0].getRelativePitch())%12), ct);
                allDiatonicChords.add(c);
            }
        }
        return allDiatonicChords;
    }

    private static Map getChordTypeDiatonicsForScaleType(ScaleType scaleType, ChordType chordType) {
        Map<Integer, ChordType> enharmonicChordTypes = new HashMap<>(scaleType.intervals.length);

        // Get a Set representation of the scale's relative pitches (used for checking subsets later)
        int scaleLength = scaleType.intervals.length;
        Set<Integer> scaleSet = new HashSet<>(scaleLength);
        for (int i = 0; i < scaleLength; i++) {
            scaleSet.add(scaleType.intervals[i].relativePitchDistance);
        }

        int numPitchesInChord = chordType.relativePitches.length;
        for (int i = 0; i < scaleLength; i++) {
            Set<Integer> candidateChordSet = new HashSet<>(numPitchesInChord);
            int intervalRelativePitch = scaleType.intervals[i].relativePitchDistance;

            // Get the set of pitches that would be made from the ChordType at scale degree i
            for (int j = 0; j < numPitchesInChord; j++) {
                int value = (intervalRelativePitch + chordType.relativePitches[j].relativePitchDistance) % 12;
                candidateChordSet.add(value);
            }

            // If all pitches in the above set are also in the scale,
            // then the chord is diatonic for scale degree i.
            if (scaleSet.containsAll(candidateChordSet)) {
                enharmonicChordTypes.put(intervalRelativePitch, chordType);
            }
        }

        return enharmonicChordTypes;
    }


}


