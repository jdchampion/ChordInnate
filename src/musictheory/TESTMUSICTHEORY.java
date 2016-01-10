package musictheory;

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

    //===============================================================================================//

    static MidiChannel[] channels;




    public static void main(String[] args) {
//        SimpleDirectedGraph<NoteType, DefaultEdge> directedGraph =
//                new SimpleDirectedGraph<>(DefaultEdge.class);
//
//        NoteType[] notes = {NoteType.C, NoteType.D, NoteType.E, NoteType.F, NoteType.G, NoteType.A, NoteType.B};
//        directedGraph.addVertex(notes[0]);
//        for (int i = 1; i < notes.length; i++) {
//            directedGraph.addVertex(notes[i]);
//            directedGraph.addEdge(notes[i-1], notes[i]);
//        }
//        System.out.println(directedGraph.toString());
//
//        NoteType n = directedGraph.getEdgeSource(directedGraph.getEdge(NoteType.C, NoteType.D));
//        System.out.println(n);
//        n = directedGraph.getEdgeTarget(directedGraph.getEdge(NoteType.C, NoteType.D));
//        System.out.println(n);

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            Thread.sleep(1000); // To minimize the (annoying) initial sound delay

            // TODO: Tests performed here

            // TODO remove for loop when finished
//            for (NoteType n: ALL_NOTES) {
//
//                testScale(n, scaleType, false);
//
//            }
            for (ScaleType s : ScaleType.values()) {

                System.out.println(s);
                testScaleDiatonicChords(note, s);
                System.out.println();
            }

//            testAscendingNotes();
//
//            testDescendingNotes();
//
//            testIntervalNotes();
//
//            testNextPreviousNotes();
//
//            testEnharmonicNotes();

            synthesizer.close();
        }
        catch (MidiUnavailableException ex) {}
        catch (InterruptedException ex) {}
        catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private static void soundNote(int midiValue, int volume, int wait) {
        if (PLAYBACK)
        try {
            Thread.sleep(100);
            channels[0].noteOn(midiValue, volume);
            Thread.sleep(200);
            channels[0].noteOff(midiValue, volume);
            Thread.sleep(wait);
        }
        catch (InterruptedException ex) {}
    }

    private static void soundChord(Chord chord, int volume, int duration, int wait) {
        if (PLAYBACK)
            try {
                NoteType[] notes = chord.getNotes();
                for (NoteType n : notes) {
                    channels[0].noteOn(60 + n.relativePitch, volume);
                }
                Thread.sleep(duration);
                for (NoteType n : notes) {
                    channels[0].noteOff(60 + n.relativePitch, volume);
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
        NoteType[] upNotes = scale.getAscendingNotes();
        NoteType[] downNotes = scale.getDescendingNotes();

        for (NoteType n : upNotes) {
            if (n != null) {
                /*
                 * FIXME Notes are correct but sound at the incorrect octave.
                 * Need a modifier variable for the octave.
                 */
                soundNote(60 + n.relativePitch, 127, 0);
            }
        }

        // Top octave note (root)
        soundNote(72 + scale.getRoot().relativePitch, 127, 0);

        if (PLAY_SCALES_UP_DOWN) {
            for (NoteType n : downNotes) {
                if (n != null) {
                    /*
                     * FIXME Notes are correct but sound at the incorrect octave.
                     * Need a modifier variable for the octave.
                     */
                    soundNote(60 + n.relativePitch, 127, 0);
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

        NoteType root = scale.getRoot();

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
        NoteType[] notes = scale.getAscendingNotes();
        for (NoteType n : notes) {
            if (n != null) {
                System.out.print(n.name + " ");
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

        System.out.print("Intervals: ");
        for (NashvilleInterval interval : scaleType.intervals) {
            System.out.print(interval + " ");
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
            /*
             * FIXME Notes are correct but sound at the incorrect octave.
             * Need a modifier variable for the octave.
             */
            soundChord(chord, 127, 1200, 100);
        }
    }

    private static void testScaleDiatonicChords(NoteType note, ScaleType scaleType) {
        try {
            Scale scale = new Scale(note, scaleType);
            Map<Integer, ArrayList<Chord>> hm = scale.getDiatonicChordsByRelativePitch();

            for (Integer i : hm.keySet()) {
                System.out.print(i + ": ");
                Collection<Chord> x = hm.get(i);
                for (Chord c : x) {
                    System.out.print(c.getName() + " ");
                    testSoundChord(c);
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
