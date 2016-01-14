package musictheory;

import generator.ChordProgression;
import generator.MajorProgressionGraph1;
import org.jgrapht.graph.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.*;

/**
 * Created by Joseph on 12/29/15.
 */
public class TESTMUSICTHEORY {

    //===============================================================================================//
    //  EDITABLE VARIABLES
    //===============================================================================================//

    // Toggle for hearing Midi playback
    static final boolean PLAYBACK = false;
    static final boolean PLAY_SCALES_UP_DOWN = false;

    // All possible note types that this program can play
    static final NoteType[] ALL_NOTES = NoteType.values();

    // Variable for testing notes
    static final NoteType note = NoteType.C;

    // Variables for testing scales
    static final ScaleType scaleType = ScaleType.MAJOR;

    // Variables for testing chords
    static final ChordType chordType = ChordType.MAJOR;

    //===============================================================================================//

    static MidiChannel[] channels;




    public static void main(String[] args) {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            Thread.sleep(1000); // To minimize the (annoying) initial sound delay

            // TODO: Tests performed here

            // TODO JGraphT directed graph example
//            ChordProgression progressionAutomaton = new MajorProgressionGraph1();
//            Scale scale = new Scale(note, scaleType);
//            NashvilleNumber graphVertex = NashvilleNumber.ONE;
//            Random r = new Random();
//            for (int i = 0; i < 16; i++) {
//                int noteTypeIndex = graphVertex.relativePitchDistance;
//
//                ArrayList<ChordType> arrayList = scale.getDiatonicChordTypesByRelativePitch().get(noteTypeIndex);
//
//                Chord c = new Chord(scale.getNoteTypeWithRelativePitch(noteTypeIndex), arrayList.get(r.nextInt(arrayList.size())));
//                System.out.println(c.getName());
//                testSoundChord(c);
//
//                r.setSeed(System.currentTimeMillis());
//
//                Object[] possiblePaths = progressionAutomaton.edgesOf(graphVertex).toArray();
//                int nextVertex = r.nextInt(possiblePaths.length);
//                graphVertex = progressionAutomaton.getEdgeTarget((DefaultEdge)possiblePaths[nextVertex]);
//                r.setSeed(System.currentTimeMillis());
//            }

//            for (NoteType nt : NoteType.values()) {
//                testScaleAttributes(new Scale(nt, scaleType));
//                System.out.println();
//            }

            synthesizer.close();
        }
        catch (MidiUnavailableException ex) {}
        catch (InterruptedException ex) {}
        catch (Exception ex) {}
    }

    private static void testAllChordInversionsForChordType() {
        for (NoteType nt : NoteType.values()) {
            try {
                Chord d = new Chord(nt, chordType);
                testChordInversions(d);
                System.out.println();
            } catch (Exception e) {}
        }
    }

    private static void testAllChordInversionsForNote() {
        for (ChordType ct : ChordType.values()) {
            try {
                Chord d = new Chord(note, ct);
                testChordInversions(d);
                System.out.println();
            } catch (Exception e) {}
        }
    }

    private static void testAllChordInversions() {
        for (NoteType nt : NoteType.values()) {
            for (ChordType ct : ChordType.values()) {
                try {
                    Chord d = new Chord(nt, ct);
                    testChordInversions(d);
                    System.out.println();
                } catch (Exception e) {}
            }
        }
    }

    private static void
    testChordInversions(Chord c) {
        for (Note n : c.getNotes()) {
            System.out.print(c.getName() + " : ");
            for (Note m : c.getNotes()) {
                System.out.print(m.getName() + m.getOctave() + " ");
            }
            System.out.println();
            testSoundChord(c);
            c.invert();
        }
        System.out.print(c.getName() + " : ");
        for (Note m : c.getNotes()) {
            System.out.print(m.getName() + m.getOctave() + " ");
        }
        System.out.println();
        testSoundChord(c);
    }

    private static void soundNote(int midiValue, int volume, int wait) {
        if (PLAYBACK)
            try {
                Thread.sleep(50);
                channels[0].noteOn(midiValue, volume);
                Thread.sleep(50);
                channels[0].noteOff(midiValue, volume);
                Thread.sleep(wait);
            }
            catch (InterruptedException ex) {}
    }

    private static void soundChord(Chord chord, int volume, int duration, int wait) {
        if (PLAYBACK)
            try {
                Note[] notes = chord.getNotes();
                for (Note n : notes) {
                    channels[0].noteOn(n.getRelativePitch(), volume);
                }
                Thread.sleep(duration);
                for (Note n : notes) {
                    channels[0].noteOff(n.getRelativePitch(), volume);
                }
                Thread.sleep(wait);
            }
            catch (InterruptedException ex) {}
    }

    private static void testAscendingNotes() {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].name + "...\n");
            for (int j = 0; j < ALL_NOTES[i].octaveRange; j++) {

                int midiValue = (12 * j) + ALL_NOTES[i].relativePitch;
                System.out.print(" " + midiValue);

                soundNote(midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testDescendingNotes() {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].name + "...\n");
            for (int j = ALL_NOTES[i].octaveRange; j >= 0; j--) {

                int midiValue = (12 * j) + ALL_NOTES[i].relativePitch;
                System.out.print(" " + midiValue);

                soundNote(midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testIntervalNotes() {
        for (Interval interval: Interval.values()) {
            System.out.println(interval.toString());
            System.out.println("Short Name: " + interval.getShortName());
            System.out.println("RomanNumeral: " + interval.getRomanNumeralName());
            System.out.println("=======================================");

            soundNote(60, 127, 0);
            soundNote(60 + interval.relativePitchDistance, 127, 500);
        }
    }

    private static void testSoundScale(Scale scale) {
        Note[] upNotes = scale.getAscendingNotes();
        Note[] downNotes = scale.getDescendingNotes();

        for (Note n : upNotes) {
            if (n != null) {
                soundNote(n.getRelativePitch(), 127, 0);
            }
        }

        // Top octave note (root)
        Note top = new Note(upNotes[0].getNoteType(), upNotes[0].getOctave()+1);
        soundNote(top.getRelativePitch(), 127, 0);

        if (PLAY_SCALES_UP_DOWN) {
            for (Note n : downNotes) {
                if (n != null) {
                    soundNote(n.getRelativePitch(), 127, 0);
                }
            }
        }
    }

    private static void testScale(NoteType note, ScaleType scaleType, boolean testTranspose) {
        System.out.println(note);
        try {
            Scale scale = new Scale(note, scaleType);


            testScaleAttributes(scale);

            if (testTranspose) {
                for (NoteType n : ALL_NOTES) {
                    System.out.println("\n==========================================");
                    try {
                        scale = Theory.transpose(scale, n);
                        System.out.print("Scale transposition from " + note.name + " to ");
                        testScaleAttributes(scale);
                    }
                    catch (Exception e) {
//                        System.out.println(e.getMessage());
                    }
                }
            }

            if (PLAYBACK) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }

        } catch (Exception e) {
            // Skip the natural notes (don't generate scales from them)
//            System.out.println(e.getMessage());
//            System.out.println("\n==========================================");
        }

        System.out.println("\n==========================================");
    }

    private static void testScaleAttributes(Scale scale) {

        System.out.print(scale.getName() + ": ");

        NoteType root = scale.getRootNoteType();

        System.out.println(root.name + " " + root.name());

        KeySignature keySignature = scale.getKeySignature();

        System.out.print("Key Signature: " + keySignature + "( ");

        if (keySignature != null) {
            for (NoteType n : keySignature.notes) {
                System.out.print(n.name + " ");
            }
        }

        System.out.println(")");

        // Print the scale notes out, and sound them
        System.out.print("Notes: ");
        Note[] notes = scale.getAscendingNotes();
        for (Note n : notes) {
            if (n != null) {
                System.out.print(n.getName() + " ");
            }
            else System.out.print("_ ");
        }

        testSoundScale(scale);

        System.out.println();

        System.out.print("Steps: ");
        for (Step s : scale.getSteps()) {
            System.out.print(s + " ");
        }

        System.out.println();

        System.out.print("Nashville Numbers: ");
        for (NashvilleNumber nn : scale.getNashvilleNumbers()) {
            System.out.print(nn.getShortName() + " ");
        }
        System.out.println();
    }

    private static void testNextPreviousNotes() {
        for (NoteType note : ALL_NOTES) {
            System.out.println("NoteType: " + note);
            soundNote(60 + note.relativePitch, 127, 0);
            System.out.println("Next: " + note.getNext());
            if (note.getNext() != null) {
                soundNote(60 + note.getNext().relativePitch, 127, 0);
            }
            System.out.println("Previous: " + note.getPrevious());
            if (note.getPrevious() != null) {
                soundNote(60 + note.getPrevious().relativePitch, 127, 0);
            }

            System.out.println("\n==========================================");
            if (PLAYBACK) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }

    private static void testEnharmonicNotes() {
        for (NoteType note : ALL_NOTES) {
            System.out.println("NoteType: " + note);
            soundNote(60 + note.relativePitch, 127, 200);

            for (NoteType n : Theory.getEnharmonicEquivalents(note, true, true)) {
                System.out.print(n.name + " ");

                soundNote(60 + n.relativePitch, 127, 0);
            }
            System.out.println();
            for (NoteType n : Theory.getEnharmonicEquivalents(note, false, true)) {
                System.out.print(n.name + " ");

                soundNote(60 + n.relativePitch, 127, 0);
            }
            System.out.println("\n==========================================");
            if (PLAYBACK) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }

    private static void testSoundChord(Chord chord) {
        if (chord != null) {
            soundChord(chord, 127, 120, 100);
        }
    }

    private static void testScaleDiatonicChords(NoteType note, ScaleType scaleType) {
        try {
            Scale scale = new Scale(note, scaleType);
            Map<Integer, ArrayList<ChordType>> hm = scale.getDiatonicChordTypesByRelativePitch();

            for (Integer i : hm.keySet()) {
                System.out.print(i + ": ");
                Collection<ChordType> x = hm.get(i);
                for (ChordType ct : x) {
                    System.out.print(ct.chordSymbol + " ");
                    Chord c = new Chord(note, ct);
                    testSoundChord(c);
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testAllScaleDiatonicChords() {
        for (NoteType nt : ALL_NOTES) {
            for (ScaleType s : ScaleType.values()) {
                System.out.println(s);
                testScaleDiatonicChords(nt, s);
                System.out.println();
            }
        }
    }

    private static void testAllScaleDiatonicChordsForNote() {
        for (ScaleType s : ScaleType.values()) {
            System.out.println(s);
            testScaleDiatonicChords(note, s);
            System.out.println();
        }
    }

    private static void testAllScaleAttributes() {
        for (NoteType nt : ALL_NOTES) {
            for (ScaleType st : ScaleType.values()) {
                try {
                    Scale s = new Scale(nt, st);
                    testScaleAttributes(s);
                    System.out.println();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testAllScaleAttributesForNote() {
        for (ScaleType st : ScaleType.values()) {
            try {
                Scale s = new Scale(note, st);
                testScaleAttributes(s);
                System.out.println();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
